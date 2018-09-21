package ru.hse.spb.interpreter.represent

import ru.hse.spb.interpreter.ast.*

class RepresentationVisitor: ASTVisitor<Unit>() {

    private var indent = 0

    override fun visit(file: File) {
        file.block.accept(this)
    }

    override fun visit(block: Block) {
        printWithIndent("Block", indent)
        indent++
        block.statementList.forEach { it.accept(this) }
        indent--
    }

    override fun visit(expression: FunctionDeclaration) {
        printWithIndent("Function declaration", indent)
        indent++
        expression.name.accept(this)
        expression.parameters.forEach { it.accept(this) }
        expression.body.accept(this)
        indent--
    }

    override fun visit(expression: VariableDeclaration) {
        printWithIndent("Variable declaration", indent)
        indent++
        expression.name.accept(this)
        expression.assignedExpression?.accept(this)
        indent--
    }

    override fun visit(expression: ExpressionStatement) {
//        printWithIndent("Function declaration", indent)
//        indent++
//        expression.
//        expression.parameters.forEach { it.accept(this) }
//        expression.body.accept(this)
//        indent--
    }

    override fun visit(expression: WhileStatement) {
        printWithIndent("While statement", indent)
        indent++
        expression.condition.accept(this)
        expression.body.accept(this)
        indent--
    }

    override fun visit(expression: IfStatement) {
        printWithIndent("If statement", indent)
        indent++
        expression.condition.accept(this)
        expression.trueBlock.accept(this)
        expression.falseBlock?.accept(this)
        indent--
    }

    override fun visit(expression: AssignmentStatement) {
        printWithIndent("Assignment statement", indent)
        indent++
        expression.variableName.accept(this)
        expression.assignedExpression.accept(this)
        indent--
    }

    override fun visit(expression: ReturnStatement) {
        printWithIndent("Return statement", indent)
        indent++
        expression.expression.accept(this)
        indent--
    }

    override fun visit(expression: MultiplicativeExpression) {
        printWithIndent("Multiplicative expression", indent)
        indent++
        printWithIndent("Operator: ${expression.operator}", indent)
        expression.left.accept(this)
        expression.right.accept(this)
        indent--
    }

    override fun visit(expression: AdditiveExpression) {
        printWithIndent("Additive expression", indent)
        indent++
        printWithIndent("Operator: ${expression.operator}", indent)
        expression.left.accept(this)
        expression.right.accept(this)
        indent--
    }

    override fun visit(expression: ComparisonExpression) {
        printWithIndent("Comparison expression", indent)
        indent++
        printWithIndent("Operator: ${expression.operator}", indent)
        expression.left.accept(this)
        expression.right.accept(this)
        indent--
    }

    override fun visit(expression: LogicalExpression) {
        printWithIndent("Logical expression", indent)
        indent++
        printWithIndent("Operator: ${expression.operator}", indent)
        expression.left.accept(this)
        expression.right.accept(this)
        indent--
    }

    override fun visit(expression: FunctionCallExpression) {
        printWithIndent("Function call", indent)
        indent++
        expression.name.accept(this)
        expression.arguments.forEach { it.accept(this) }
        indent--
    }

    override fun visit(expression: UnarySignedExpression) {
        printWithIndent("Unary signed expression", indent)
        indent++
        expression.expression.accept(this)
        indent--
    }

    override fun visit(identifier: Identifier) {
        printWithIndent("Name: ${identifier.name}", indent)
    }

    override fun visit(literal: Literal) {
        printWithIndent("Value: ${literal.valueAsString}", indent)
    }

    private fun printWithIndent(str: String, indent: Int) {
        println("\t".repeat(indent) + str)
    }
}