package ru.hse.spb.interpreter.ast

abstract class ASTVisitor<out R> {

    abstract fun visit(file: File): R

    abstract fun visit(block: Block): R

    abstract fun visit(declaration: FunctionDeclaration): R

    abstract fun visit(declaration: VariableDeclaration): R

    abstract fun visit(statement: ExpressionStatement): R

    abstract fun visit(statement: WhileStatement): R

    abstract fun visit(statement: IfStatement): R

    abstract fun visit(statement: AssignmentStatement): R

    abstract fun visit(statement: ReturnStatement): R

    abstract fun visit(expression: MultiplicativeExpression): R

    abstract fun visit(expression: AdditiveExpression): R

    abstract fun visit(expression: ComparisonExpression): R

    abstract fun visit(expression: LogicalExpression): R

    abstract fun visit(expression: FunctionCallExpression): R

    abstract fun visit(expression: UnarySignedExpression): R

    abstract fun visit(identifier: Identifier): R

    abstract fun visit(literal: Literal): R

}