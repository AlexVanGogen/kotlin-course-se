package ru.hse.spb.interpreter.represent

import ru.hse.spb.interpreter.ast.*

class PrettyPrintingVisitor: ASTVisitor<Unit>() {

    override fun visit(file: File) {
        file.block.accept(this)
    }

    override fun visit(block: Block) {
        block.statementList.forEach { withIndent { it.accept(this) }; println() }
    }

    override fun visit(declaration: FunctionDeclaration) {
        print("fun ${declaration.identifier.name}")
        withParentheses { print(declaration.parameters.joinToString(", ") { it.name }) }
        withBrackets { declaration.body.accept(this) }
    }

    override fun visit(declaration: VariableDeclaration) {
        print("var ${declaration.identifier.name}")
        if (declaration.assignedExpression != null) {
            print(" = ")
            declaration.assignedExpression.accept(this)
        }
    }

    override fun visit(statement: WhileStatement) {
        print("while ")
        withParentheses { statement.condition.accept(this) }
        withBrackets { statement.body.accept(this) }
    }

    override fun visit(statement: IfStatement) {
        print("if ")
        withParentheses { statement.condition.accept(this) }
        withBrackets { statement.trueBlock.accept(this) }
        if (statement.falseBlock != null) {
            print(" else")
            withBrackets { statement.falseBlock.accept(this) }
        }
    }

    override fun visit(statement: AssignmentStatement) {
        print("${statement.variableName.name} = ")
        statement.assignedExpression.accept(this)
    }

    override fun visit(statement: ReturnStatement) {
        print("return ")
        statement.expression.accept(this)
    }

    override fun visit(expression: MultiplicativeExpression) {
        visitBinaryExpression(expression)
    }

    override fun visit(expression: AdditiveExpression) {
        visitBinaryExpression(expression)
    }

    override fun visit(expression: ComparisonExpression) {
        visitBinaryExpression(expression)
    }

    override fun visit(expression: LogicalExpression) {
        visitBinaryExpression(expression)
    }

    override fun visit(expression: FunctionCallExpression) {
        print(expression.identifier.name)
        withParentheses {
            if (expression.arguments.isNotEmpty())
                expression.arguments[0].accept(this)
            for (i in 1 until expression.arguments.size) {
                print(", ")
                expression.arguments[i].accept(this)
            }
        }
    }

    override fun visit(expression: UnarySignedExpression) {
        print(expression.sign.symbol)
        if (expression.signedSubexpression is BinaryExpression)
            withParentheses { expression.signedSubexpression.accept(this) }
        else
            expression.signedSubexpression.accept(this)
    }

    override fun visit(identifier: Identifier) {
        print(identifier.name)
    }

    override fun visit(literal: Literal) {
        print(literal.valueAsString)
    }

    private fun visitBinaryExpression(expression: BinaryExpression) {
        if (expression.left is BinaryExpression)
            withParentheses { expression.left.accept(this) }
        else
            expression.left.accept(this)
        print(" ${expression.operator.symbol} ")
        if (expression.right is BinaryExpression)
            withParentheses { expression.right.accept(this) }
        else
            expression.right.accept(this)
    }

    private var indent = -1

    private fun printlnWithIndent(str: String) {
        println("\t".repeat(indent) + str)
    }

    private fun printWithIndent(str: String) {
        print("\t".repeat(indent) + str)
    }

    private fun withIndent(block: () -> Unit) {
        indent++
        printWithIndent("")
        block()
        indent--
    }

    private fun withParentheses(block: () -> Unit) {
        print("(")
        block()
        print(")")
    }

    private fun withBrackets(block: () -> Unit) {
        println(" {")
        block()
        printWithIndent("}")
    }
}