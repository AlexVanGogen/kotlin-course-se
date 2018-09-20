package ru.hse.spb.interpreter.ast

abstract class ASTVisitor<out R> {

    abstract fun visit(t: ASTElement): R

    abstract fun visit(file: File): R

    abstract fun visit(block: Block): R

    abstract fun visit(expression: FunctionDeclaration): R

    abstract fun visit(expression: VariableDeclaration): R

    abstract fun visit(expression: ExpressionStatement): R

    abstract fun visit(expression: WhileStatement): R

    abstract fun visit(expression: IfStatement): R

    abstract fun visit(expression: AssignmentStatement): R

    abstract fun visit(expression: ReturnStatement): R

    abstract fun visit(expression: MultiplicativeExpression): R

    abstract fun visit(expression: AdditiveExpression): R

    abstract fun visit(expression: ComparisonExpression): R

    abstract fun visit(expression: LogicalExpression): R

    abstract fun visit(expression: FunctionCallExpression): R

    abstract fun visit(expression: UnarySignedExpression): R

    abstract fun visit(identifier: Identifier): R

    abstract fun visit(literal: Literal): R

}