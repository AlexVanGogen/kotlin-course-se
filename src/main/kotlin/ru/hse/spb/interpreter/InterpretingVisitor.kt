package ru.hse.spb.interpreter

import kotlinx.coroutines.experimental.coroutineScope
import ru.hse.spb.interpreter.ast.*
import ru.hse.spb.interpreter.naming.Namespace
import java.util.*

/**
 * Visitor that interprets given program represented as AST.
 *
 * Semantics of interpretation is following:
 * 1) Every language construction (i.e. node of AST) has an integer value.
 *    For example, for signedSubexpression it's just its calculated value;
 *    for variable declaration it's calculated value of assigned signedSubexpression,
 *    or 0 if nothing is assigned;
 *    for `if` statement it's a result of corresponding block (of true or false branch).
 * 2) Result of block interpreting is a calculated value of the first `return` statement
 *    in statement list, or 0, if there are not any `return` statements inside the block.
 *    Statements after the first `return` statements are ignored.
 * 3) Every block has its namespace; only those variables and functions are considered visible
 *    that were declared in the current namespace or inside any enclosing namespace.
 *    Corresponding declarations must be declared earlier in code.
 * 4) Functions overloading is not allowed.
 * 5) Duplicated variable declarations inside the same namespace are not allowed.
 * 6) One variable declaration shadows another declaration for variable with the same identifier
 *    if the latter declared in enclosing namespace or as function parameter.
 *    Function parameter shadows declarations inside enclosing namespaces.
 * 7) When interpreter sees an assignment, it first calculates an assigned signedSubexpression,
 *    and just after that checks if variable an signedSubexpression is assigned to, causes no errors.
 */
open class InterpretingVisitor: ASTVisitor<Int>() {

    private val topLevelNamespace = Namespace()
    private var currentNamespace = topLevelNamespace

    override suspend fun visit(file: File): Int {
        return try {
            file.block.accept(this)
        } catch (e: ReturnFoundException) {
            e.value
        }
    }

    override suspend fun visit(block: Block): Int {
        for (nextStatement in block.statementList) {
            if (nextStatement is ReturnStatement) {
                throw ReturnFoundException(nextStatement.accept(this))
            }
            nextStatement.accept(this)
        }
        return 0
    }

    override suspend fun visit(declaration: FunctionDeclaration): Int {
        currentNamespace.addFunction(declaration)
        return 0
    }

    override suspend fun visit(declaration: VariableDeclaration): Int {
        val assignedExpressionValue = declaration.assignedExpression?.accept(this) ?: 0
        currentNamespace.addVariable(declaration.identifier, assignedExpressionValue)
        return assignedExpressionValue
    }

    override suspend fun visit(statement: WhileStatement): Int {
        var loopResult = 0

        while (statement.condition.accept(this) != 0) {
            loopResult = inNewNamespace {
                statement.body.accept(this)
            }
        }

        return loopResult
    }

    override suspend fun visit(statement: IfStatement): Int {
        val conditionResult = statement.condition.accept(this)
        return if (conditionResult != 0) {
            inNewNamespace { statement.trueBlock.accept(this) }
        } else {
            inNewNamespace { statement.falseBlock?.accept(this) ?: 0 }
        }
    }

    override suspend fun visit(statement: AssignmentStatement): Int {
        currentNamespace.getVariableValue(statement.variableName)
        currentNamespace.updateVariableValue(statement.variableName, statement.assignedExpression.accept(this))
        return 0
    }

    override suspend fun visit(statement: ReturnStatement): Int {
        return statement.expression.accept(this)
    }

    override suspend fun visit(expression: MultiplicativeExpression): Int {
        val leftValue = expression.left.accept(this)
        val rightValue = expression.right.accept(this)
        return when (expression.operator) {
            OperatorType.MUL -> leftValue * rightValue
            OperatorType.DIV -> leftValue / rightValue
            OperatorType.MOD -> leftValue % rightValue
            else -> 0
        }
    }

    override suspend fun visit(expression: AdditiveExpression): Int {
        val leftValue = expression.left.accept(this)
        val rightValue = expression.right.accept(this)
        return when (expression.operator) {
            OperatorType.PLUS -> leftValue + rightValue
            OperatorType.MINUS -> leftValue - rightValue
            else -> 0
        }
    }

    override suspend fun visit(expression: ComparisonExpression): Int {
        val leftValue = expression.left.accept(this)
        val rightValue = expression.right.accept(this)
        return when (expression.operator) {
            OperatorType.LT -> (leftValue < rightValue).toInt()
            OperatorType.LEQ -> (leftValue <= rightValue).toInt()
            OperatorType.GT -> (leftValue > rightValue).toInt()
            OperatorType.GEQ -> (leftValue >= rightValue).toInt()
            OperatorType.EQ -> (leftValue == rightValue).toInt()
            OperatorType.NEQ -> (leftValue != rightValue).toInt()
            else -> 0
        }
    }

    override suspend fun visit(expression: LogicalExpression): Int {
        val leftValue = expression.left.accept(this)
        val rightValue = expression.right.accept(this)
        return when (expression.operator) {
            OperatorType.AND -> (leftValue.toBool() && rightValue.toBool()).toInt()
            OperatorType.OR -> (leftValue.toBool() || rightValue.toBool()).toInt()
            else -> 0
        }
    }

    override suspend fun visit(expression: FunctionCallExpression): Int {
        if (expression.identifier.name == "println")
            return visitPrintlnFunction(expression)

        val declaration = currentNamespace.getFunctionByCall(expression)
        return inNewNamespace {
            for (i in declaration.parameters.indices) {
                val av = expression.arguments[i].accept(this)
                currentNamespace.addVariable(declaration.parameters[i], av)
            }
            inNewNamespace {
                try {
                    declaration.body.accept(this)
                } catch (e: ReturnFoundException) {
                    e.value
                }
            }
        }
    }

    override suspend fun visit(expression: UnarySignedExpression): Int {
        val expressionResult = expression.signedSubexpression.accept(this)
        return when (expression.sign) {
            Sign.PLUS -> expressionResult
            Sign.MINUS -> -expressionResult
        }
    }

    override suspend fun visit(identifier: Identifier): Int = currentNamespace.getVariableValue(identifier)

    override suspend fun visit(literal: Literal): Int {
        return literal.valueAsString.toInt()
    }

    private suspend fun visitPrintlnFunction(call: FunctionCallExpression): Int {
        println(call.arguments.map { it.accept(this).toString() }.joinToString(" "))
        return 0
    }

    private fun Int.toBool() = this != 0
    private fun Boolean.toInt() = if (this) 1 else 0

    /**
     * The !! operator is null-safe here, because enclosing namespace
     * links with the current namespace in the first line of that function.
     */
    private suspend fun inNewNamespace(block: suspend () -> Int): Int {
        currentNamespace = Namespace(currentNamespace)
        try {
            return block()
        } finally {
            currentNamespace = currentNamespace.enclosingNamespace!!
        }
    }
}