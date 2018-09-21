package ru.hse.spb.interpreter.ast

import org.antlr.v4.runtime.tree.ErrorNode
import org.antlr.v4.runtime.tree.ParseTree
import org.antlr.v4.runtime.tree.RuleNode
import org.antlr.v4.runtime.tree.TerminalNode
import ru.hse.spb.antlr.LalalangParser
import ru.hse.spb.antlr.LalalangVisitor
import ru.hse.spb.antlr.LalalangLexer.*

class LalalangASTMakingVisitor: LalalangVisitor<BasicElement> {

    override fun visitFile(ctx: LalalangParser.FileContext) = File(visitBlock(ctx.block()))

    override fun visitBlock(ctx: LalalangParser.BlockContext) = Block(ctx.statement().map { visitStatement(it) })

    override fun visitBlockWithBraces(ctx: LalalangParser.BlockWithBracesContext) = visitBlock(ctx.block())

    override fun visitStatement(ctx: LalalangParser.StatementContext): Statement =
            when {
                ctx.function() != null -> visitFunction(ctx.function())
                ctx.variable() != null -> visitVariable(ctx.variable())
                ctx.expression() != null -> visitExpression(ctx.expression())
                ctx.whileStatement() != null -> visitWhileStatement(ctx.whileStatement())
                ctx.ifStatement() != null -> visitIfStatement(ctx.ifStatement())
                ctx.assignment() != null -> visitAssignment(ctx.assignment())
                ctx.returnStatement() != null -> visitReturnStatement(ctx.returnStatement())
                else -> throw InvalidStatementException()
            }

    override fun visitFunction(ctx: LalalangParser.FunctionContext) = FunctionDeclaration(
            visitIdentifierExpression(ctx.Identifier()).identifier,
            if (ctx.parameterNames() != null) visitParameterNames(ctx.parameterNames()).names else listOf(),
            visitBlockWithBraces(ctx.blockWithBraces())
    )

    override fun visitVariable(ctx: LalalangParser.VariableContext) = VariableDeclaration(
            visitIdentifierExpression(ctx.Identifier()).identifier,
            ctx.expression()?.let { visitExpression(ctx.expression()) }
    )

    override fun visitParameterNames(ctx: LalalangParser.ParameterNamesContext) = ParameterNames(
            ctx.Identifier().map { visitIdentifierExpression(it).identifier }
    )

    override fun visitWhileStatement(ctx: LalalangParser.WhileStatementContext) = WhileStatement(
            visitExpression(ctx.expression()),
            visitBlockWithBraces(ctx.blockWithBraces())
    )

    override fun visitIfStatement(ctx: LalalangParser.IfStatementContext) = IfStatement(
            visitExpression(ctx.expression()),
            visitBlockWithBraces(ctx.ifTrueBody),
            ctx.ifFalseBody?.let { visitBlockWithBraces(ctx.ifFalseBody) }
    )

    override fun visitAssignment(ctx: LalalangParser.AssignmentContext) = AssignmentStatement(
            visitIdentifierExpression(ctx.Identifier()).identifier,
            visitExpression(ctx.expression())
    )

    override fun visitReturnStatement(ctx: LalalangParser.ReturnStatementContext) = ReturnStatement(
            visitExpression(ctx.expression())
    )

    override fun visitExpression(ctx: LalalangParser.ExpressionContext): Expression =
            when {
                ctx.nested != null -> visitExpression(ctx.nested)
                ctx.functionCall() != null -> visitFunctionCall(ctx.functionCall())
                ctx.Identifier() != null -> visitIdentifierExpression(ctx.Identifier())
                ctx.Literal() != null -> visitLiteralExpression(ctx.Literal())
                else -> {
                    val left = visitExpression(ctx.left)
                    val right = visitExpression(ctx.right)
                    val operator = OperatorType.of(ctx.operator.text) ?: throw Exception()
                    when (ctx.operator.type) {
                        MUL, DIV, MOD -> MultiplicativeExpression(left, operator, right)
                        PLUS, MINUS -> AdditiveExpression(left, operator, right)
                        LT, LEQ, GT, GEQ, EQ, NEQ -> ComparisonExpression(left, operator, right)
                        AND, OR -> LogicalExpression(left, operator, right)
                        else -> throw InvalidExpressionException()
                    }
                }
            }

    fun visitIdentifierExpression(ctx: TerminalNode) = IdentifierExpression(visitTerminal(ctx) as Identifier)
    fun visitLiteralExpression(ctx: TerminalNode) = LiteralExpression(visitTerminal(ctx) as Literal)

    override fun visit(tree: ParseTree?) = null

    override fun visitUnaryExpression(ctx: LalalangParser.UnaryExpressionContext) = UnarySignedExpression(
            visitExpression(ctx.expression())
    )

    override fun visitChildren(node: RuleNode?) = null

    override fun visitErrorNode(node: ErrorNode?) = null

    override fun visitFunctionCall(ctx: LalalangParser.FunctionCallContext) = FunctionCallExpression(
            visitIdentifierExpression(ctx.Identifier()).identifier,
            if (ctx.arguments() != null) visitArguments(ctx.arguments()).values else listOf()
    )

    override fun visitArguments(ctx: LalalangParser.ArgumentsContext) = Arguments(
            ctx.expression().map { visitExpression(it) }
    )

    override fun visitTerminal(node: TerminalNode): BasicElement {
        when (node.symbol.type) {
            Identifier -> return Identifier(node.toString(), node.symbol)
            Literal -> return Literal(node.toString(), node.symbol)
            else -> throw InvalidTerminalException()
        }
    }
}