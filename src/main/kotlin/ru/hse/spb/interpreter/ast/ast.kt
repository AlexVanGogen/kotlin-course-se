package ru.hse.spb.interpreter.ast

import org.antlr.v4.runtime.Token
import ru.hse.spb.interpreter.getLocation

sealed class BasicElement

interface ASTElement {
    fun <R> accept(visitor: ASTVisitor<R>) = visitor.visit(this)
}

interface Named {
    val correspondingToken: Token
    fun getLocation() = correspondingToken.getLocation()
}

class File(
        val block: Block
): BasicElement(), ASTElement

class Block(
        val statementList: List<Statement>
): BasicElement(), ASTElement

sealed class Statement: BasicElement()

class FunctionDeclaration(
        val name: Identifier,
        val parameters: List<Identifier>,
        val body: Block
): Statement(), ASTElement {
    val signature: String = "$name(${List(parameters.size) { "int" }.joinToString(separator = ", ")})"
}

class ParameterNames(
        val names: List<Identifier>
): BasicElement()

class VariableDeclaration(
        val name: Identifier,
        val assignedExpression: Expression?
): Statement(), ASTElement

class ExpressionStatement: Statement()

class WhileStatement(
        val condition: Expression,
        val body: Block
): Statement(), ASTElement

class IfStatement(
        val condition: Expression,
        val trueBlock: Block,
        val falseBlock: Block?
): Statement(), ASTElement

class AssignmentStatement(
        val variableName: Identifier,
        val assignedExpression: Expression
): Statement(), ASTElement

class ReturnStatement(
        val expression: Expression
): Statement(), ASTElement

sealed class Expression: Statement()

sealed class BinaryExpression(
        open val left: Expression,
        open val operator: OperatorType,
        open val right: Expression
): Expression()

enum class OperatorType { PLUS, MINUS, MUL, DIV, MOD, LT, LEQ, GT, GEQ, EQ, NEQ, AND, OR }

class MultiplicativeExpression(
        override val left: Expression,
        override val operator: OperatorType,
        override val right: Expression
): BinaryExpression(left, operator, right), ASTElement

class AdditiveExpression(
        override val left: Expression,
        override val operator: OperatorType,
        override val right: Expression
): BinaryExpression(left, operator, right), ASTElement

class ComparisonExpression(
        override val left: Expression,
        override val operator: OperatorType,
        override val right: Expression
): BinaryExpression(left, operator, right), ASTElement

class LogicalExpression(
        override val left: Expression,
        override val operator: OperatorType,
        override val right: Expression
): BinaryExpression(left, operator, right), ASTElement

class FunctionCallExpression(
        val name: Identifier,
        val arguments: List<Expression>
): Expression(), ASTElement {
    val signature: String = "$name(${List(arguments.size) { "int" }.joinToString(separator = ", ")})"
}

class Arguments(
        val values: List<Expression>
): BasicElement()

class UnarySignedExpression(
        val expression: Expression
): Expression(), ASTElement

class IdentifierExpression(
        val identifier: Identifier
): Expression()

class Identifier(
        val name: String,
        override val correspondingToken: Token
): BasicElement(), ASTElement, Named

class LiteralExpression(
        val literal: Literal
): Expression()

class Literal(
        val valueAsString: String,
        override val correspondingToken: Token
): BasicElement(), ASTElement, Named