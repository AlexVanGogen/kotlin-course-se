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

    override fun visit(declaration: FunctionDeclaration) {
        printWithIndent("Function declaration", indent)
        indent++
        declaration.name.accept(this)
        declaration.parameters.forEach { it.accept(this) }
        declaration.body.accept(this)
        indent--
    }

    override fun visit(declaration: VariableDeclaration) {
        printWithIndent("Variable declaration", indent)
        indent++
        declaration.name.accept(this)
        declaration.assignedExpression?.accept(this)
        indent--
    }

    override fun visit(statement: ExpressionStatement) {}

    override fun visit(statement: WhileStatement) {
        printWithIndent("While statement", indent)
        indent++
        statement.condition.accept(this)
        statement.body.accept(this)
        indent--
    }

    override fun visit(statement: IfStatement) {
        printWithIndent("If statement", indent)
        indent++
        statement.condition.accept(this)
        statement.trueBlock.accept(this)
        statement.falseBlock?.accept(this)
        indent--
    }

    override fun visit(statement: AssignmentStatement) {
        printWithIndent("Assignment statement", indent)
        indent++
        statement.variableName.accept(this)
        statement.assignedExpression.accept(this)
        indent--
    }

    override fun visit(statement: ReturnStatement) {
        printWithIndent("Return statement", indent)
        indent++
        statement.expression.accept(this)
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
        printWithIndent("Sign: ${expression.sign}", indent)
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