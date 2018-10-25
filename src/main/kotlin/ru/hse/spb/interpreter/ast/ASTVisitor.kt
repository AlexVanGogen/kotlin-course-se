package ru.hse.spb.interpreter.ast

abstract class ASTVisitor<out R> {

    abstract suspend fun visit(file: File): R

    abstract suspend fun visit(block: Block): R

    abstract suspend fun visit(declaration: FunctionDeclaration): R

    abstract suspend fun visit(declaration: VariableDeclaration): R

    abstract suspend fun visit(statement: WhileStatement): R

    abstract suspend fun visit(statement: IfStatement): R

    abstract suspend fun visit(statement: AssignmentStatement): R

    abstract suspend fun visit(statement: ReturnStatement): R

    abstract suspend fun visit(expression: MultiplicativeExpression): R

    abstract suspend fun visit(expression: AdditiveExpression): R

    abstract suspend fun visit(expression: ComparisonExpression): R

    abstract suspend fun visit(expression: LogicalExpression): R

    abstract suspend fun visit(expression: FunctionCallExpression): R

    abstract suspend fun visit(expression: UnarySignedExpression): R

    abstract suspend fun visit(identifier: Identifier): R

    abstract suspend fun visit(literal: Literal): R

}