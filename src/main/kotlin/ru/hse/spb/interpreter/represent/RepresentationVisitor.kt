package ru.hse.spb.interpreter.represent

import ru.hse.spb.interpreter.ast.*

class RepresentationVisitor: ASTVisitor<Unit>() {

    override suspend fun visit(file: File) {
        file.block.accept(this)
    }

    override suspend fun visit(block: Block) {
        printWithIndent("Block", indent)
        withIndent {
            block.statementList.forEach { it.accept(this) }
        }
    }

    override suspend fun visit(declaration: FunctionDeclaration) {
        printWithIndent("Function declaration", indent)
        withIndent {
            declaration.identifier.accept(this)
            printWithIndent("Parameters", indent)
            withIndent {
                declaration.parameters.forEach { it.accept(this) }
            }
            declaration.body.accept(this)
        }
    }

    override suspend fun visit(declaration: VariableDeclaration) {
        printWithIndent("Variable declaration", indent)
        withIndent {
            declaration.identifier.accept(this)
            declaration.assignedExpression?.accept(this)
        }
    }

    override suspend fun visit(statement: WhileStatement) {
        printWithIndent("While statement", indent)
        withIndent {
            statement.condition.accept(this)
            statement.body.accept(this)
        }
    }

    override suspend fun visit(statement: IfStatement) {
        printWithIndent("If statement", indent)
        withIndent {
            statement.condition.accept(this)
            statement.trueBlock.accept(this)
            statement.falseBlock?.accept(this)
        }
    }

    override suspend fun visit(statement: AssignmentStatement) {
        printWithIndent("Assignment statement", indent)
        withIndent {
            statement.variableName.accept(this)
            statement.assignedExpression.accept(this)
        }
    }

    override suspend fun visit(statement: ReturnStatement) {
        printWithIndent("Return statement", indent)
        withIndent {
            statement.expression.accept(this)
        }
    }

    override suspend fun visit(expression: MultiplicativeExpression) {
        printWithIndent("Multiplicative expression", indent)
        withIndent {
            printWithIndent("Operator: ${expression.operator}", indent)
            expression.left.accept(this)
            expression.right.accept(this)
        }
    }

    override suspend fun visit(expression: AdditiveExpression) {
        printWithIndent("Additive expression", indent)
        withIndent {
            printWithIndent("Operator: ${expression.operator}", indent)
            expression.left.accept(this)
            expression.right.accept(this)
        }
    }

    override suspend fun visit(expression: ComparisonExpression) {
        printWithIndent("Comparison expression", indent)
        withIndent {
            printWithIndent("Operator: ${expression.operator}", indent)
            expression.left.accept(this)
            expression.right.accept(this)
        }
    }

    override suspend fun visit(expression: LogicalExpression) {
        printWithIndent("Logical expression", indent)
        withIndent {
            printWithIndent("Operator: ${expression.operator}", indent)
            expression.left.accept(this)
            expression.right.accept(this)
        }
    }

    override suspend fun visit(expression: FunctionCallExpression) {
        printWithIndent("Function call", indent)
        withIndent {
            expression.identifier.accept(this)
            printWithIndent("Arguments", indent)
            withIndent {
                expression.arguments.forEach { it.accept(this) }
            }
        }
    }

    override suspend fun visit(expression: UnarySignedExpression) {
        printWithIndent("Unary signed expression", indent)
        withIndent {
            printWithIndent("Sign: ${expression.sign}", indent)
            expression.signedSubexpression.accept(this)
        }
    }

    override suspend fun visit(identifier: Identifier) {
        printWithIndent("Identifier, name: ${identifier.name}", indent)
    }

    override suspend fun visit(literal: Literal) {
        printWithIndent("Literal, value: ${literal.valueAsString}", indent)
    }

    private fun printWithIndent(str: String, indent: Int) {
        println("\t".repeat(indent) + str)
    }

    private var indent = 0

    private suspend fun withIndent(block: suspend () -> Unit) {
        indent++
        block()
        indent--
    }
}