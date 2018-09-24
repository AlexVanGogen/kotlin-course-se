package ru.hse.spb.interpreter

import ru.hse.spb.interpreter.ast.*
import ru.hse.spb.interpreter.naming.Namespace
import kotlin.math.exp

class EvaluatingVisitor: ASTVisitor<Int>() {

    val topLevelNamespace = Namespace()
    var currentNamespace = topLevelNamespace

    override fun visit(file: File): Int {
        return file.block.accept(this)
    }

    override fun visit(block: Block): Int {
        return block.statementList.map { it.accept(this) }.last()
    }

    override fun visit(expression: FunctionDeclaration): Int {
        expression.name.accept(this)
        expression.parameters.forEach { it.accept(this) }
        currentNamespace = Namespace(currentNamespace)
        expression.body.accept(this)
        currentNamespace = currentNamespace.enclosingNamespace!!
        return 0
    }

    override fun visit(expression: VariableDeclaration): Int {
        val assignedExpressionValue = expression.assignedExpression?.accept(this) ?: 0
        currentNamespace.addVariable(expression.name, assignedExpressionValue)
        return assignedExpressionValue
    }

    override fun visit(expression: ExpressionStatement): Int = 0

    override fun visit(expression: WhileStatement): Int {
        var loopResult = 0

        while (expression.condition.accept(this) != 0) {
            currentNamespace = Namespace(currentNamespace)
            loopResult = expression.body.accept(this)
            currentNamespace = currentNamespace.enclosingNamespace!!
        }

        return loopResult
    }

    override fun visit(expression: IfStatement): Int {
        val conditionResult = expression.condition.accept(this)
        return if (conditionResult != 0) {
            currentNamespace = Namespace(currentNamespace)
            val evaluationResult = expression.trueBlock.accept(this)
            currentNamespace = currentNamespace.enclosingNamespace!!
            evaluationResult
        }
        else {
            currentNamespace = Namespace(currentNamespace)
            val evaluationResult = expression.falseBlock?.accept(this) ?: 0
            currentNamespace = currentNamespace.enclosingNamespace!!
            evaluationResult
        }
    }

    override fun visit(expression: AssignmentStatement): Int {
        currentNamespace.getVariableValue(expression.variableName)
        currentNamespace.updateVariableValue(expression.variableName, expression.assignedExpression.accept(this))
        return 0
    }

    override fun visit(expression: ReturnStatement): Int {
        return expression.expression.accept(this)
    }

    override fun visit(expression: MultiplicativeExpression): Int {
        val leftValue = expression.left.accept(this)
        val rightValue = expression.right.accept(this)
        return when (expression.operator) {
            OperatorType.MUL -> leftValue * rightValue
            OperatorType.DIV -> leftValue / rightValue
            OperatorType.MOD -> leftValue % rightValue
            else -> 0
        }
    }

    override fun visit(expression: AdditiveExpression): Int {
        val leftValue = expression.left.accept(this)
        val rightValue = expression.right.accept(this)
        return when (expression.operator) {
            OperatorType.PLUS -> leftValue + rightValue
            OperatorType.MINUS -> leftValue - rightValue
            else -> 0
        }
    }

    override fun visit(expression: ComparisonExpression): Int {
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

    override fun visit(expression: LogicalExpression): Int {
        val leftValue = expression.left.accept(this)
        val rightValue = expression.right.accept(this)
        return when (expression.operator) {
            OperatorType.AND -> (leftValue.toBool() && rightValue.toBool()).toInt()
            OperatorType.OR -> (leftValue.toBool() || rightValue.toBool()).toInt()
            else -> 0
        }
    }

    override fun visit(expression: FunctionCallExpression): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(expression: UnarySignedExpression): Int {
        val expressionResult = expression.expression.accept(this)
        return when (expression.sign) {
            Sign.PLUS -> expressionResult
            Sign.MINUS -> -expressionResult
        }
    }

    override fun visit(identifier: Identifier): Int = currentNamespace.getVariableValue(identifier)

    override fun visit(literal: Literal): Int {
        return literal.valueAsString.toInt()
    }

    private fun Int.toBool() = if (this != 0) true else false
    private fun Boolean.toInt() = if (this) 1 else 0
}