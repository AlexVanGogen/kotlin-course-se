package ru.hse.spb.interpreter.ast

import org.antlr.v4.runtime.Token
import ru.hse.spb.interpreter.getLocation

sealed class BasicElement

interface ASTElement {
    fun <R> accept(visitor: ASTVisitor<R>): R
}

interface Named {
    val correspondingToken: Token
    fun getLocation() = correspondingToken.getLocation()
}

class File(
        val block: Block
): BasicElement(), ASTElement {
    override fun <R> accept(visitor: ASTVisitor<R>) = visitor.visit(this)
}

class Block(
        val statementList: List<Statement>
): BasicElement(), ASTElement {
    override fun <R> accept(visitor: ASTVisitor<R>) = visitor.visit(this)
}

sealed class Statement: BasicElement(), ASTElement

class FunctionDeclaration(
        val name: Identifier,
        val parameters: List<Identifier>,
        val body: Block
): Statement(), ASTElement {
    val signature: String = "$name(${List(parameters.size) { "int" }.joinToString(separator = ", ")})"
    override fun <R> accept(visitor: ASTVisitor<R>) = visitor.visit(this)
}

class ParameterNames(
        val names: List<Identifier>
): BasicElement()

class VariableDeclaration(
        val name: Identifier,
        val assignedExpression: Expression?
): Statement(), ASTElement {
    override fun <R> accept(visitor: ASTVisitor<R>) = visitor.visit(this)
}

class ExpressionStatement: Statement() {
    override fun <R> accept(visitor: ASTVisitor<R>) = visitor.visit(this)
}

class WhileStatement(
        val condition: Expression,
        val body: Block
): Statement(), ASTElement {
    override fun <R> accept(visitor: ASTVisitor<R>) = visitor.visit(this)
}

class IfStatement(
        val condition: Expression,
        val trueBlock: Block,
        val falseBlock: Block?
): Statement(), ASTElement {
    override fun <R> accept(visitor: ASTVisitor<R>) = visitor.visit(this)
}

class AssignmentStatement(
        val variableName: Identifier,
        val assignedExpression: Expression
): Statement(), ASTElement {
    override fun <R> accept(visitor: ASTVisitor<R>) = visitor.visit(this)
}

class ReturnStatement(
        val expression: Expression
): Statement(), ASTElement {
    override fun <R> accept(visitor: ASTVisitor<R>) = visitor.visit(this)
}

sealed class Expression: Statement()

sealed class BinaryExpression(
        open val left: Expression,
        open val operator: OperatorType,
        open val right: Expression
): Expression()

enum class OperatorType(val symbol: String) {
    PLUS("+"),
    MINUS("-"),
    MUL("*"),
    DIV("/"),
    MOD("%"),
    LT("<"),
    LEQ("<="),
    GT(">"),
    GEQ(">="),
    EQ("=="),
    NEQ("!="),
    AND("&&"),
    OR("||");

    companion object {
        fun of(operator: String) = values().find { it.symbol == operator }
    }
}

class MultiplicativeExpression(
        override val left: Expression,
        override val operator: OperatorType,
        override val right: Expression
): BinaryExpression(left, operator, right), ASTElement {
    override fun <R> accept(visitor: ASTVisitor<R>) = visitor.visit(this)
}

class AdditiveExpression(
        override val left: Expression,
        override val operator: OperatorType,
        override val right: Expression
): BinaryExpression(left, operator, right), ASTElement {
    override fun <R> accept(visitor: ASTVisitor<R>) = visitor.visit(this)
}

class ComparisonExpression(
        override val left: Expression,
        override val operator: OperatorType,
        override val right: Expression
): BinaryExpression(left, operator, right), ASTElement {
    override fun <R> accept(visitor: ASTVisitor<R>) = visitor.visit(this)
}

class LogicalExpression(
        override val left: Expression,
        override val operator: OperatorType,
        override val right: Expression
): BinaryExpression(left, operator, right), ASTElement {
    override fun <R> accept(visitor: ASTVisitor<R>) = visitor.visit(this)
}

class FunctionCallExpression(
        val name: Identifier,
        val arguments: List<Expression>
): Expression(), ASTElement {
    val signature: String = "$name(${List(arguments.size) { "int" }.joinToString(separator = ", ")})"
    
    override fun <R> accept(visitor: ASTVisitor<R>) = visitor.visit(this)
}

class Arguments(
        val values: List<Expression>
): BasicElement()

class UnarySignedExpression(
        val expression: Expression,
        val sign: Sign
): Expression(), ASTElement {
    override fun <R> accept(visitor: ASTVisitor<R>) = visitor.visit(this)
}

enum class Sign(val symbol: String) {
    PLUS("+"),
    MINUS("-");

    companion object {
        fun of(sign: String) = values().find { it.symbol == sign }
    }
}
class IdentifierExpression(
        val identifier: Identifier
): Expression() {
    override fun <R> accept(visitor: ASTVisitor<R>) = identifier.accept(visitor)
}

class Identifier(
        val name: String,
        override val correspondingToken: Token
): BasicElement(), ASTElement, Named {
    override fun <R> accept(visitor: ASTVisitor<R>) = visitor.visit(this)
}

class LiteralExpression(
        val literal: Literal
): Expression() {
    override fun <R> accept(visitor: ASTVisitor<R>) = literal.accept(visitor)
}

class Literal(
        val valueAsString: String,
        override val correspondingToken: Token
): BasicElement(), ASTElement, Named {
    override fun <R> accept(visitor: ASTVisitor<R>) = visitor.visit(this)
}