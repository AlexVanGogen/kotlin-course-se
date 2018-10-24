// Generated from /Users/alexvangogen/Fall/kotlin-course/kotlin-course-se/src/main/resources/ru.hse.spb.antlr/Lalalang.g4 by ANTLR 4.7
package ru.hse.spb.antlr;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link LalalangParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface LalalangVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link LalalangParser#file}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFile(LalalangParser.FileContext ctx);
	/**
	 * Visit a parse tree produced by {@link LalalangParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(LalalangParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link LalalangParser#blockWithBraces}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlockWithBraces(LalalangParser.BlockWithBracesContext ctx);
	/**
	 * Visit a parse tree produced by {@link LalalangParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(LalalangParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link LalalangParser#function}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction(LalalangParser.FunctionContext ctx);
	/**
	 * Visit a parse tree produced by {@link LalalangParser#variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable(LalalangParser.VariableContext ctx);
	/**
	 * Visit a parse tree produced by {@link LalalangParser#parameterNames}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameterNames(LalalangParser.ParameterNamesContext ctx);
	/**
	 * Visit a parse tree produced by {@link LalalangParser#whileStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileStatement(LalalangParser.WhileStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link LalalangParser#ifStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStatement(LalalangParser.IfStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link LalalangParser#assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignment(LalalangParser.AssignmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link LalalangParser#returnStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnStatement(LalalangParser.ReturnStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link LalalangParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(LalalangParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link LalalangParser#unaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryExpression(LalalangParser.UnaryExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link LalalangParser#functionCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionCall(LalalangParser.FunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link LalalangParser#arguments}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArguments(LalalangParser.ArgumentsContext ctx);
}