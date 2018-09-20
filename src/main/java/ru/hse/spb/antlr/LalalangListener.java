// Generated from /Users/alexvangogen/Fall/kotlin-course/kotlin-course-se/src/main/resources/ru.hse.spb.antlr/Lalalang.g4 by ANTLR 4.7
package ru.hse.spb.antlr;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link LalalangParser}.
 */
public interface LalalangListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link LalalangParser#file}.
	 * @param ctx the parse tree
	 */
	void enterFile(LalalangParser.FileContext ctx);
	/**
	 * Exit a parse tree produced by {@link LalalangParser#file}.
	 * @param ctx the parse tree
	 */
	void exitFile(LalalangParser.FileContext ctx);
	/**
	 * Enter a parse tree produced by {@link LalalangParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(LalalangParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link LalalangParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(LalalangParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link LalalangParser#blockWithBraces}.
	 * @param ctx the parse tree
	 */
	void enterBlockWithBraces(LalalangParser.BlockWithBracesContext ctx);
	/**
	 * Exit a parse tree produced by {@link LalalangParser#blockWithBraces}.
	 * @param ctx the parse tree
	 */
	void exitBlockWithBraces(LalalangParser.BlockWithBracesContext ctx);
	/**
	 * Enter a parse tree produced by {@link LalalangParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(LalalangParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link LalalangParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(LalalangParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link LalalangParser#function}.
	 * @param ctx the parse tree
	 */
	void enterFunction(LalalangParser.FunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link LalalangParser#function}.
	 * @param ctx the parse tree
	 */
	void exitFunction(LalalangParser.FunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link LalalangParser#variable}.
	 * @param ctx the parse tree
	 */
	void enterVariable(LalalangParser.VariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link LalalangParser#variable}.
	 * @param ctx the parse tree
	 */
	void exitVariable(LalalangParser.VariableContext ctx);
	/**
	 * Enter a parse tree produced by {@link LalalangParser#parameterNames}.
	 * @param ctx the parse tree
	 */
	void enterParameterNames(LalalangParser.ParameterNamesContext ctx);
	/**
	 * Exit a parse tree produced by {@link LalalangParser#parameterNames}.
	 * @param ctx the parse tree
	 */
	void exitParameterNames(LalalangParser.ParameterNamesContext ctx);
	/**
	 * Enter a parse tree produced by {@link LalalangParser#whileStatement}.
	 * @param ctx the parse tree
	 */
	void enterWhileStatement(LalalangParser.WhileStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link LalalangParser#whileStatement}.
	 * @param ctx the parse tree
	 */
	void exitWhileStatement(LalalangParser.WhileStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link LalalangParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void enterIfStatement(LalalangParser.IfStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link LalalangParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void exitIfStatement(LalalangParser.IfStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link LalalangParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignment(LalalangParser.AssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link LalalangParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignment(LalalangParser.AssignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link LalalangParser#returnStatement}.
	 * @param ctx the parse tree
	 */
	void enterReturnStatement(LalalangParser.ReturnStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link LalalangParser#returnStatement}.
	 * @param ctx the parse tree
	 */
	void exitReturnStatement(LalalangParser.ReturnStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link LalalangParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(LalalangParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link LalalangParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(LalalangParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link LalalangParser#unaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterUnaryExpression(LalalangParser.UnaryExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link LalalangParser#unaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitUnaryExpression(LalalangParser.UnaryExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link LalalangParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void enterFunctionCall(LalalangParser.FunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link LalalangParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void exitFunctionCall(LalalangParser.FunctionCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link LalalangParser#arguments}.
	 * @param ctx the parse tree
	 */
	void enterArguments(LalalangParser.ArgumentsContext ctx);
	/**
	 * Exit a parse tree produced by {@link LalalangParser#arguments}.
	 * @param ctx the parse tree
	 */
	void exitArguments(LalalangParser.ArgumentsContext ctx);
}