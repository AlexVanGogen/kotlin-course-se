package ru.hse.spb.interpreter.ast

import org.antlr.v4.runtime.Token
import ru.hse.spb.interpreter.getLocation

sealed class BasicLanguageElement(open val lineNumber: Int = 0)

interface ASTElement {
    suspend fun <R> accept(visitor: ASTVisitor<R>): R
}

interface Named {
    val correspondingToken: Token
    fun getLocation() = correspondingToken.getLocation()
}

class File(
        val block: Block
): BasicLanguageElement(), ASTElement {
    override suspend fun <R> accept(visitor: ASTVisitor<R>) = visitor.visit(this)
}

class Block(
        val statementList: List<Statement>,
        override val lineNumber: Int = 0
): BasicLanguageElement(), ASTElement {
    override suspend fun <R> accept(visitor: ASTVisitor<R>) = visitor.visit(this)
}

sealed class Statement(
        override val lineNumber: Int = 0
): BasicLanguageElement(lineNumber), ASTElement

class FunctionDeclaration(
        val identifier: Identifier,
        val parameters: List<Identifier>,
        val body: Block,
        override val lineNumber: Int = 0
): Statement(lineNumber), ASTElement {
    val signature: String = "${identifier.name}(${List(parameters.size) { "int" }.joinToString(separator = ", ")})"
    override suspend fun <R> accept(visitor: ASTVisitor<R>) = visitor.visit(this)
}

class ParameterNames(
        val identifiers: List<Identifier>
): BasicLanguageElement()

class VariableDeclaration(
        val identifier: Identifier,
        val assignedExpression: Expression?,
        override val lineNumber: Int = 0
): Statement(lineNumber), ASTElement {
    override suspend fun <R> accept(visitor: ASTVisitor<R>) = visitor.visit(this)
}

class WhileStatement(
        val condition: Expression,
        val body: Block,
        override val lineNumber: Int = 0
): Statement(lineNumber), ASTElement {
    override suspend fun <R> accept(visitor: ASTVisitor<R>) = visitor.visit(this)
}

class IfStatement(
        val condition: Expression,
        val trueBlock: Block,
        val falseBlock: Block?,
        override val lineNumber: Int = 0
): Statement(lineNumber), ASTElement {
    override suspend fun <R> accept(visitor: ASTVisitor<R>) = visitor.visit(this)
}

class AssignmentStatement(
        val variableName: Identifier,
        val assignedExpression: Expression,
        override val lineNumber: Int = 0
): Statement(lineNumber), ASTElement {
    override suspend fun <R> accept(visitor: ASTVisitor<R>) = visitor.visit(this)
}

class ReturnStatement(
        val expression: Expression,
        override val lineNumber: Int = 0
): Statement(lineNumber), ASTElement {
    override suspend fun <R> accept(visitor: ASTVisitor<R>) = visitor.visit(this)
}

sealed class Expression(
        override val lineNumber: Int = 0
): Statement(lineNumber)

sealed class BinaryExpression(
        open val left: Expression,
        open val operator: OperatorType,
        open val right: Expression,
        override val lineNumber: Int = 0
): Expression(lineNumber)

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
        /**
         * The !! operator is null-safe here, because any unsupported operators
         * are detected while that AST is just building.
         * (@see [CustomASTMapper.visitExpression] for the details)
         */
        fun of(operator: String) = values().find { it.symbol == operator }!!
    }
}

class MultiplicativeExpression(
        override val left: Expression,
        override val operator: OperatorType,
        override val right: Expression,
        override val lineNumber: Int = 0
): BinaryExpression(left, operator, right, lineNumber), ASTElement {
    override suspend fun <R> accept(visitor: ASTVisitor<R>) = visitor.visit(this)
}

class AdditiveExpression(
        override val left: Expression,
        override val operator: OperatorType,
        override val right: Expression,
        override val lineNumber: Int = 0
): BinaryExpression(left, operator, right, lineNumber), ASTElement {
    override suspend fun <R> accept(visitor: ASTVisitor<R>) = visitor.visit(this)
}

class ComparisonExpression(
        override val left: Expression,
        override val operator: OperatorType,
        override val right: Expression,
        override val lineNumber: Int = 0
): BinaryExpression(left, operator, right, lineNumber), ASTElement {
    override suspend fun <R> accept(visitor: ASTVisitor<R>) = visitor.visit(this)
}

class LogicalExpression(
        override val left: Expression,
        override val operator: OperatorType,
        override val right: Expression,
        override val lineNumber: Int = 0
): BinaryExpression(left, operator, right, lineNumber), ASTElement {
    override suspend fun <R> accept(visitor: ASTVisitor<R>) = visitor.visit(this)
}

class FunctionCallExpression(
        val identifier: Identifier,
        val arguments: List<Expression>,
        override val lineNumber: Int = 0
): Expression(lineNumber), ASTElement {
    val signature: String = "${identifier.name}(${List(arguments.size) { "int" }.joinToString(separator = ", ")})"
    
    override suspend fun <R> accept(visitor: ASTVisitor<R>) = visitor.visit(this)
}

class Arguments(
        val values: List<Expression>
): BasicLanguageElement()

class UnarySignedExpression(
        val signedSubexpression: Expression,
        val sign: Sign,
        override val lineNumber: Int = 0
): Expression(lineNumber), ASTElement {
    override suspend fun <R> accept(visitor: ASTVisitor<R>) = visitor.visit(this)
}

enum class Sign(val symbol: String) {
    PLUS("+"),
    MINUS("-");

    companion object {
        fun of(sign: String) = values().find { it.symbol == sign }
    }
}

class IdentifierExpression(
        val identifier: Identifier,
        override val lineNumber: Int = 0
): Expression(lineNumber) {
    override suspend fun <R> accept(visitor: ASTVisitor<R>) = identifier.accept(visitor)

    companion object
}

class Identifier(
        val name: String,
        override val correspondingToken: Token
): BasicLanguageElement(correspondingToken.line), ASTElement, Named {
    override suspend fun <R> accept(visitor: ASTVisitor<R>) = visitor.visit(this)
}

class LiteralExpression(
        val literal: Literal,
        override val lineNumber: Int = 0
): Expression(lineNumber) {
    override suspend fun <R> accept(visitor: ASTVisitor<R>) = literal.accept(visitor)

    companion object
}

class Literal(
        val valueAsString: String,
        override val correspondingToken: Token
): BasicLanguageElement(correspondingToken.line), ASTElement, Named {
    override suspend fun <R> accept(visitor: ASTVisitor<R>) = visitor.visit(this)
}