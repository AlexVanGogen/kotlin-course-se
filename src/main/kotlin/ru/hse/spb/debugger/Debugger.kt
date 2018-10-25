package ru.hse.spb.debugger

import kotlinx.coroutines.experimental.runBlocking
import ru.hse.spb.interpreter.ASTFactory
import ru.hse.spb.interpreter.InterpretingVisitor
import ru.hse.spb.interpreter.ReturnFoundException
import ru.hse.spb.interpreter.ast.*
import kotlin.coroutines.experimental.*

/**
 * Language debugger.
 * Debugging process encapsulated
 */
class Debugger {

    /**
     * AST of loaded code
     */
    private var codeAST: ASTElement? = null

    /**
     * User-defined list of breakpoints
     */
    private val breakpointsList = BreakpointsList()

    /**
     * @see [ContinuationActor]
     */
    private var continuationActor: ContinuationActor? = null

    /**
     * @see [DebuggingVisitor]
     */
    private val visitor = DebuggingVisitor()

    /**
     * Result of current AST subtree evaluation.
     * Used when need to get condition or expression value passed by user
     * using corresponding REPL commands.
     */
    private var lastComputedValue: Int? = null

    /**
     * @see [ExecutionStatus]
     */
    private var executionStatus: ExecutionStatus = ExecutionStatus.NOT_LOADED

    /**
     * @see [BreakpointVisitingStatus]
     */
    private var breakpointVisitingStatus: BreakpointVisitingStatus = BreakpointVisitingStatus.EXITED_AND_EXECUTED_NEXT_STATEMENT

    /**
     * Executes `load` REPL command
     */
    fun loadFile(fileName: String) {
        if (executionStatus != ExecutionStatus.NOT_LOADED)
            clearState();
        codeAST = ASTFactory.fromFile(fileName)
        executionStatus = ExecutionStatus.LOADED
    }

    /**
     * Executes `add` REPL command
     */
    fun addBreakpoint(lineNumber: Int) = withCheckOfLoadedFile {
        breakpointsList.addBreakpoint(lineNumber)
    }

    /**
     * Executes `condition` REPL command
     */
    fun addConditionalBreakpoint(lineNumber: Int, condition: String) = withCheckOfLoadedFile {
        breakpointsList.addConditionalBreakpoint(lineNumber, condition)
    }

    /**
     * Executes `remove` REPL command
     */
    fun removeBreakpoint(lineNumber: Int): Boolean = withCheckOfLoadedFile {
        breakpointsList.removeBreakpoint(lineNumber)
    }

    /**
     * Executes `list` REPL command
     */
    fun listBreakpoints() = withCheckOfLoadedFile {
        breakpointsList.listBreakpoints().forEach { println(it) }
    }

    /**
     * Executes `evaluate` REPL command
     */
    fun evaluateExpression(expressionAsString: String): String {
        return withCheckOfLoadedFile {
            if (executionStatus != ExecutionStatus.SUSPENDED) {
                throw IllegalStateException("File is not suspended")
            }
            "$expressionAsString = " + runBlocking {
                ASTFactory.fromString(expressionAsString).accept(visitor)
                lastComputedValue
            }
        }
    }

    /**
     * Executes `run` REPL command
     */
    suspend fun run() {
        withCheckOfLoadedFile { }
        if (executionStatus == ExecutionStatus.RUNNING || executionStatus == ExecutionStatus.SUSPENDED) {
            throw IllegalStateException("File is already executing")
        }
        executionStatus = ExecutionStatus.JUST_RUNNED
        continuationActor = ContinuationActor { doEvaluation() }
        continueInterpreting()
    }

    /**
     * Executes `continue` REPL command
     */
    fun continueInterpreting() {
        if (executionStatus !in arrayOf(ExecutionStatus.SUSPENDED, ExecutionStatus.JUST_RUNNED)) {
            throw IllegalStateException("File is not suspended")
        }
        continuationActor?.resumeInterpreting(Unit)
    }

    /**
     * Executes `stop` REPL command
     */
    fun stopInterpreting() {
        withCheckOfLoadedFile { }
        continuationActor?.stop()
        executionStatus = ExecutionStatus.LOADED
    }

    /**
     * Fully clears any changes made during last debugging process
     */
    private fun clearState() {
        clearBreakpointsList()
        continuationActor = null
        codeAST = null
        executionStatus = ExecutionStatus.NOT_LOADED
        breakpointVisitingStatus = BreakpointVisitingStatus.EXITED_AND_EXECUTED_NEXT_STATEMENT
    }

    /**
     * Removes all defined breakpoints
     */
    private fun clearBreakpointsList() = breakpointsList.clear()

    /**
     * Wraps some action; stops execution if debugger has not any file to proceed
     */
    private fun <R> withCheckOfLoadedFile(block: () -> R): R {
        if (executionStatus != ExecutionStatus.NOT_LOADED)
            return block()
        else
            throw FileNotLoadedException()
    }

    /**
     * Wraps some action (visit some AST node, actually); suspend action if current line
     * is marked by one of breakpoints and its condition does not prevent suspension.
     * Suspension happens before than action on current line will be evaluated.
     */
    private suspend fun <R> withSuspendingOnBreakpoint(lineNumber: Int, block: suspend () -> R): R {
        if (breakpointVisitingStatus == BreakpointVisitingStatus.JUST_EXITED) {
            return block()
        }

        val breakpoint = breakpointsList.getBreakpointByLineNumber(lineNumber)
        if (breakpoint != null) {
            if (breakpoint.isConditionPassedOrNotDefined(visitor)) {
                executionStatus = ExecutionStatus.SUSPENDED
                continuationActor?.suspendInterpreting { println("Suspended at line $lineNumber") }
            }
        }
        return block()
    }

    /**
     * Main action that will be debugged
     */
    private suspend fun doEvaluation() {
        codeAST?.accept(visitor) ?: throw ASTNotExistsException()
    }

    /**
     * Checks if program must be suspended by current breakpoint
     */
    private suspend fun Breakpoint.isConditionPassedOrNotDefined(currentVisitor: InterpretingVisitor): Boolean {
        if (condition == null)
            return true
        condition?.accept(currentVisitor)
        return lastComputedValue != 0
    }

    /**
     * Status to control consistency of user commands sequence
     */
    private enum class ExecutionStatus {
        NOT_LOADED,
        LOADED,
        JUST_RUNNED,
        RUNNING,
        SUSPENDED
    }

    /**
     * Status to control repeated entering to suspended state
     */
    private enum class BreakpointVisitingStatus {
        JUST_EXITED,
        EXITED_AND_EXECUTED_NEXT_STATEMENT
    }

    /**
     * Logic of all suspensions and continuations in debugging process.
     * Main action will be executed as a coroutine, and will be suspended every time the executions reaches line
     * marked by one of breakpoints.
     * @param [suspendableAction] action that is supposed to be affected by suspensions and continuations
     */
    private inner class ContinuationActor(suspendableAction: suspend () -> Unit) : Continuation<Unit> {
        private var interpretingAction: Continuation<Unit>? = suspendableAction.createCoroutine(this)

        override val context: CoroutineContext
            get() = EmptyCoroutineContext

        /**
         * End debugging process, prepare for other possible processes
         */
        override fun resume(value: Unit) {
            executionStatus = ExecutionStatus.LOADED
            println("Evaluation completed")
        }

        /**
         * Restore interpreting action from suspended state
         */
        fun resumeInterpreting(value: Unit) {
            executionStatus = ExecutionStatus.RUNNING
            breakpointVisitingStatus = BreakpointVisitingStatus.JUST_EXITED
            interpretingAction?.resume(value) ?: throw IllegalStateException("(internal) Interpreting continuation not exists")
        }

        /**
         * Suspend running interpreting action
         */
        suspend fun suspendInterpreting(withAction: () -> Unit) {
            executionStatus = ExecutionStatus.SUSPENDED
            suspendCoroutine<Unit> {
                interpretingAction = it
                withAction()
            }
        }

        /**
         * Immediately stop debugging process, prepare for other possible processes
         */
        fun stop() {
            interpretingAction = null
            executionStatus = ExecutionStatus.LOADED
            println("Evaluation was immediately stopped")
        }

        override fun resumeWithException(exception: Throwable) {
            throw exception
        }
    }

    /**
     * Successor of [InterpretingVisitor] with extra semantics of comparing every statement's line number
     * with predefined breakpoints locations.
     * [breakpointVisitingStatus] is used to distinguish statements and whole-line expressions, and subexpressions
     * (i.e. `1 + 1` is a subexpression of `return fib(1 + 1 + 2)` statement),
     * with the purpose of avoiding repeated suspensions when entering to subexpressions, but suspend program
     * when entering the same statement again (i.e. if breakpoint is inside loop or called function body).
     */
    private inner class DebuggingVisitor: InterpretingVisitor() {

        override suspend fun visit(file: File): Int {
            breakpointVisitingStatus = BreakpointVisitingStatus.EXITED_AND_EXECUTED_NEXT_STATEMENT
            return withSuspendingOnBreakpoint(file.lineNumber) { super.visit(file) }
        }

        override suspend fun visit(block: Block): Int {
            for (nextStatement in block.statementList) {
                breakpointVisitingStatus = BreakpointVisitingStatus.EXITED_AND_EXECUTED_NEXT_STATEMENT
                withSuspendingOnBreakpoint(block.lineNumber) {
                    if (nextStatement is ReturnStatement) {
                        throw ReturnFoundException(nextStatement.accept(this))
                    }
                    nextStatement.accept(this)
                }
            }
            return 0
        }

        override suspend fun visit(declaration: FunctionDeclaration): Int {
            breakpointVisitingStatus = BreakpointVisitingStatus.EXITED_AND_EXECUTED_NEXT_STATEMENT
            return withSuspendingOnBreakpoint(declaration.lineNumber) { super.visit(declaration) }
        }

        override suspend fun visit(declaration: VariableDeclaration): Int {
            breakpointVisitingStatus = BreakpointVisitingStatus.EXITED_AND_EXECUTED_NEXT_STATEMENT
            return withSuspendingOnBreakpoint(declaration.lineNumber) { super.visit(declaration) }
        }

        override suspend fun visit(statement: WhileStatement): Int {
            breakpointVisitingStatus = BreakpointVisitingStatus.EXITED_AND_EXECUTED_NEXT_STATEMENT
            return withSuspendingOnBreakpoint(statement.lineNumber) { super.visit(statement) }
        }

        override suspend fun visit(statement: IfStatement): Int {
            breakpointVisitingStatus = BreakpointVisitingStatus.EXITED_AND_EXECUTED_NEXT_STATEMENT
            return withSuspendingOnBreakpoint(statement.lineNumber) { super.visit(statement) }
        }

        override suspend fun visit(statement: AssignmentStatement): Int {
            breakpointVisitingStatus = BreakpointVisitingStatus.EXITED_AND_EXECUTED_NEXT_STATEMENT
            return withSuspendingOnBreakpoint(statement.lineNumber) { super.visit(statement) }
        }

        override suspend fun visit(statement: ReturnStatement): Int {
            breakpointVisitingStatus = BreakpointVisitingStatus.EXITED_AND_EXECUTED_NEXT_STATEMENT
            return withSuspendingOnBreakpoint(statement.lineNumber) { super.visit(statement) }
        }

        override suspend fun visit(expression: MultiplicativeExpression): Int {
            return withSuspendingOnBreakpoint(expression.lineNumber) {
                val result = super.visit(expression)
                lastComputedValue = result
                result
            }
        }

        override suspend fun visit(expression: AdditiveExpression): Int {
            return withSuspendingOnBreakpoint(expression.lineNumber) {
                val result = super.visit(expression)
                lastComputedValue = result
                result
            }
        }

        override suspend fun visit(expression: ComparisonExpression): Int {
            return withSuspendingOnBreakpoint(expression.lineNumber) {
                val result = super.visit(expression)
                lastComputedValue = result
                result
            }
        }

        override suspend fun visit(expression: LogicalExpression): Int {
            return withSuspendingOnBreakpoint(expression.lineNumber) {
                val result = super.visit(expression)
                lastComputedValue = result
                result
            }
        }

        override suspend fun visit(expression: FunctionCallExpression): Int {
            return withSuspendingOnBreakpoint(expression.lineNumber) {
                val result = super.visit(expression)
                lastComputedValue = result
                result
            }
        }

        override suspend fun visit(expression: UnarySignedExpression): Int {
            return withSuspendingOnBreakpoint(expression.lineNumber) {
                val result = super.visit(expression)
                lastComputedValue = result
                result
            }
        }

        override suspend fun visit(identifier: Identifier): Int {
            return withSuspendingOnBreakpoint(identifier.lineNumber) {
                val result = super.visit(identifier)
                lastComputedValue = result
                result
            }
        }

        override suspend fun visit(literal: Literal): Int {
            return withSuspendingOnBreakpoint(literal.lineNumber) { super.visit(literal) }
        }
    }
}