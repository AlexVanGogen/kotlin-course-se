package ru.hse.spb.interpreter.represent

import ru.hse.spb.interpreter.ast.*

class RepresentationVisitor: ASTVisitor<Unit>() {

    private var indent = 0

    override fun visit(file: File) {
        file.block.accept(this)
    }

    override fun visit(block: Block) {
        printWithIndent("Block", indent)
        withIndent {
            block.statementList.forEach { it.accept(this) }
        }
    }

    override fun visit(declaration: FunctionDeclaration) {
        printWithIndent("Function declaration", indent)
        withIndent {
            declaration.identifier.accept(this)
            declaration.parameters.forEach { it.accept(this) }
            declaration.body.accept(this)
        }
    }

    override fun visit(declaration: VariableDeclaration) {
        printWithIndent("Variable declaration", indent)
        withIndent {
            declaration.identifier.accept(this)
            declaration.assignedExpression?.accept(this)
        }
    }

    override fun visit(statement: WhileStatement) {
        printWithIndent("While statement", indent)
        withIndent {
            statement.condition.accept(this)
            statement.body.accept(this)
        }
    }

    override fun visit(statement: IfStatement) {
        printWithIndent("If statement", indent)
        withIndent {
            statement.condition.accept(this)
            statement.trueBlock.accept(this)
            statement.falseBlock?.accept(this)
        }
    }

    override fun visit(statement: AssignmentStatement) {
        printWithIndent("Assignment statement", indent)
        withIndent {
            statement.variableName.accept(this)
            statement.assignedExpression.accept(this)
        }
    }

    override fun visit(statement: ReturnStatement) {
        printWithIndent("Return statement", indent)
        withIndent {
            statement.expression.accept(this)
        }
    }

    override fun visit(expression: MultiplicativeExpression) {
        printWithIndent("Multiplicative signedSubexpression", indent)
        withIndent {
            printWithIndent("Operator: ${expression.operator}", indent)
            expression.left.accept(this)
            expression.right.accept(this)
        }
    }

    override fun visit(expression: AdditiveExpression) {
        printWithIndent("Additive signedSubexpression", indent)
        withIndent {
            printWithIndent("Operator: ${expression.operator}", indent)
            expression.left.accept(this)
            expression.right.accept(this)
        }
    }

    override fun visit(expression: ComparisonExpression) {
        printWithIndent("Comparison signedSubexpression", indent)
        withIndent {
            printWithIndent("Operator: ${expression.operator}", indent)
            expression.left.accept(this)
            expression.right.accept(this)
        }
    }

    override fun visit(expression: LogicalExpression) {
        printWithIndent("Logical signedSubexpression", indent)
        withIndent {
            printWithIndent("Operator: ${expression.operator}", indent)
            expression.left.accept(this)
            expression.right.accept(this)
        }
    }

    override fun visit(expression: FunctionCallExpression) {
        printWithIndent("Function call", indent)
        withIndent {
            expression.identifier.accept(this)
            expression.arguments.forEach { it.accept(this) }
        }
    }

    override fun visit(expression: UnarySignedExpression) {
        printWithIndent("Unary signed signedSubexpression", indent)
        withIndent {
            printWithIndent("Sign: ${expression.sign}", indent)
            expression.signedSubexpression.accept(this)
        }
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

    private fun withIndent(block: () -> Unit) {
        indent++
        block()
        indent--
    }
}