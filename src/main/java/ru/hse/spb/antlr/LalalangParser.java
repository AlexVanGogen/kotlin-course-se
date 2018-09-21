// Generated from /Users/alexvangogen/Fall/kotlin-course/kotlin-course-se/src/main/resources/ru.hse.spb.antlr/Lalalang.g4 by ANTLR 4.7
package ru.hse.spb.antlr;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class LalalangParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		NEWLINE=1, WHITESPACE=2, ONELINE_COMMENT=3, MULTILINE_COMMENT=4, RETURN=5, 
		IF=6, ELSE=7, WHILE=8, VAR=9, FUN=10, COMMA=11, LPAREN=12, RPAREN=13, 
		LBRACK=14, RBRACK=15, PLUS=16, MINUS=17, MUL=18, DIV=19, MOD=20, EQ=21, 
		NEQ=22, LT=23, LEQ=24, GT=25, GEQ=26, AND=27, OR=28, ASSIGN=29, Identifier=30, 
		Literal=31, MismatchedSymbol=32;
	public static final int
		RULE_file = 0, RULE_block = 1, RULE_blockWithBraces = 2, RULE_statement = 3, 
		RULE_function = 4, RULE_variable = 5, RULE_parameterNames = 6, RULE_whileStatement = 7, 
		RULE_ifStatement = 8, RULE_assignment = 9, RULE_returnStatement = 10, 
		RULE_expression = 11, RULE_unaryExpression = 12, RULE_functionCall = 13, 
		RULE_arguments = 14;
	public static final String[] ruleNames = {
		"file", "block", "blockWithBraces", "statement", "function", "variable", 
		"parameterNames", "whileStatement", "ifStatement", "assignment", "returnStatement", 
		"expression", "unaryExpression", "functionCall", "arguments"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, null, null, null, "'return'", "'if'", "'else'", "'while'", 
		"'var'", "'fun'", "','", "'('", "')'", "'{'", "'}'", "'+'", "'-'", "'*'", 
		"'/'", "'%'", "'=='", "'!='", "'<'", "'<='", "'>'", "'>='", "'&&'", "'||'", 
		"'='"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "NEWLINE", "WHITESPACE", "ONELINE_COMMENT", "MULTILINE_COMMENT", 
		"RETURN", "IF", "ELSE", "WHILE", "VAR", "FUN", "COMMA", "LPAREN", "RPAREN", 
		"LBRACK", "RBRACK", "PLUS", "MINUS", "MUL", "DIV", "MOD", "EQ", "NEQ", 
		"LT", "LEQ", "GT", "GEQ", "AND", "OR", "ASSIGN", "Identifier", "Literal", 
		"MismatchedSymbol"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Lalalang.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public LalalangParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class FileContext extends ParserRuleContext {
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public TerminalNode EOF() { return getToken(LalalangParser.EOF, 0); }
		public FileContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_file; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LalalangListener ) ((LalalangListener)listener).enterFile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LalalangListener ) ((LalalangListener)listener).exitFile(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LalalangVisitor ) return ((LalalangVisitor<? extends T>)visitor).visitFile(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FileContext file() throws RecognitionException {
		FileContext _localctx = new FileContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_file);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(30);
			block();
			setState(31);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlockContext extends ParserRuleContext {
		public List<TerminalNode> NEWLINE() { return getTokens(LalalangParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(LalalangParser.NEWLINE, i);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LalalangListener ) ((LalalangListener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LalalangListener ) ((LalalangListener)listener).exitBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LalalangVisitor ) return ((LalalangVisitor<? extends T>)visitor).visitBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_block);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(36);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(33);
					match(NEWLINE);
					}
					} 
				}
				setState(38);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			}
			setState(47);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(39);
					statement();
					setState(41); 
					_errHandler.sync(this);
					_alt = 1;
					do {
						switch (_alt) {
						case 1:
							{
							{
							setState(40);
							match(NEWLINE);
							}
							}
							break;
						default:
							throw new NoViableAltException(this);
						}
						setState(43); 
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
					} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
					}
					} 
				}
				setState(49);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			}
			setState(54);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << RETURN) | (1L << IF) | (1L << WHILE) | (1L << VAR) | (1L << FUN) | (1L << LPAREN) | (1L << PLUS) | (1L << MINUS) | (1L << Identifier) | (1L << Literal))) != 0)) {
				{
				setState(50);
				statement();
				setState(52);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
				case 1:
					{
					setState(51);
					match(NEWLINE);
					}
					break;
				}
				}
			}

			setState(59);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NEWLINE) {
				{
				{
				setState(56);
				match(NEWLINE);
				}
				}
				setState(61);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlockWithBracesContext extends ParserRuleContext {
		public TerminalNode LBRACK() { return getToken(LalalangParser.LBRACK, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public TerminalNode RBRACK() { return getToken(LalalangParser.RBRACK, 0); }
		public BlockWithBracesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_blockWithBraces; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LalalangListener ) ((LalalangListener)listener).enterBlockWithBraces(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LalalangListener ) ((LalalangListener)listener).exitBlockWithBraces(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LalalangVisitor ) return ((LalalangVisitor<? extends T>)visitor).visitBlockWithBraces(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlockWithBracesContext blockWithBraces() throws RecognitionException {
		BlockWithBracesContext _localctx = new BlockWithBracesContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_blockWithBraces);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(62);
			match(LBRACK);
			setState(63);
			block();
			setState(64);
			match(RBRACK);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementContext extends ParserRuleContext {
		public FunctionContext function() {
			return getRuleContext(FunctionContext.class,0);
		}
		public ReturnStatementContext returnStatement() {
			return getRuleContext(ReturnStatementContext.class,0);
		}
		public WhileStatementContext whileStatement() {
			return getRuleContext(WhileStatementContext.class,0);
		}
		public IfStatementContext ifStatement() {
			return getRuleContext(IfStatementContext.class,0);
		}
		public AssignmentContext assignment() {
			return getRuleContext(AssignmentContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LalalangListener ) ((LalalangListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LalalangListener ) ((LalalangListener)listener).exitStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LalalangVisitor ) return ((LalalangVisitor<? extends T>)visitor).visitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_statement);
		try {
			setState(73);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(66);
				function();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(67);
				returnStatement();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(68);
				whileStatement();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(69);
				ifStatement();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(70);
				assignment();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(71);
				expression(0);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(72);
				variable();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionContext extends ParserRuleContext {
		public BlockWithBracesContext functionBody;
		public TerminalNode FUN() { return getToken(LalalangParser.FUN, 0); }
		public TerminalNode Identifier() { return getToken(LalalangParser.Identifier, 0); }
		public TerminalNode LPAREN() { return getToken(LalalangParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(LalalangParser.RPAREN, 0); }
		public BlockWithBracesContext blockWithBraces() {
			return getRuleContext(BlockWithBracesContext.class,0);
		}
		public ParameterNamesContext parameterNames() {
			return getRuleContext(ParameterNamesContext.class,0);
		}
		public FunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LalalangListener ) ((LalalangListener)listener).enterFunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LalalangListener ) ((LalalangListener)listener).exitFunction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LalalangVisitor ) return ((LalalangVisitor<? extends T>)visitor).visitFunction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionContext function() throws RecognitionException {
		FunctionContext _localctx = new FunctionContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_function);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(75);
			match(FUN);
			setState(76);
			match(Identifier);
			setState(77);
			match(LPAREN);
			setState(79);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Identifier) {
				{
				setState(78);
				parameterNames();
				}
			}

			setState(81);
			match(RPAREN);
			setState(82);
			((FunctionContext)_localctx).functionBody = blockWithBraces();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariableContext extends ParserRuleContext {
		public TerminalNode VAR() { return getToken(LalalangParser.VAR, 0); }
		public TerminalNode Identifier() { return getToken(LalalangParser.Identifier, 0); }
		public TerminalNode ASSIGN() { return getToken(LalalangParser.ASSIGN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public VariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LalalangListener ) ((LalalangListener)listener).enterVariable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LalalangListener ) ((LalalangListener)listener).exitVariable(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LalalangVisitor ) return ((LalalangVisitor<? extends T>)visitor).visitVariable(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableContext variable() throws RecognitionException {
		VariableContext _localctx = new VariableContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_variable);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(84);
			match(VAR);
			setState(85);
			match(Identifier);
			setState(88);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ASSIGN) {
				{
				setState(86);
				match(ASSIGN);
				setState(87);
				expression(0);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParameterNamesContext extends ParserRuleContext {
		public List<TerminalNode> Identifier() { return getTokens(LalalangParser.Identifier); }
		public TerminalNode Identifier(int i) {
			return getToken(LalalangParser.Identifier, i);
		}
		public List<TerminalNode> COMMA() { return getTokens(LalalangParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(LalalangParser.COMMA, i);
		}
		public ParameterNamesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameterNames; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LalalangListener ) ((LalalangListener)listener).enterParameterNames(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LalalangListener ) ((LalalangListener)listener).exitParameterNames(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LalalangVisitor ) return ((LalalangVisitor<? extends T>)visitor).visitParameterNames(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParameterNamesContext parameterNames() throws RecognitionException {
		ParameterNamesContext _localctx = new ParameterNamesContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_parameterNames);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(90);
			match(Identifier);
			setState(95);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(91);
				match(COMMA);
				setState(92);
				match(Identifier);
				}
				}
				setState(97);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class WhileStatementContext extends ParserRuleContext {
		public BlockWithBracesContext loopBody;
		public TerminalNode WHILE() { return getToken(LalalangParser.WHILE, 0); }
		public TerminalNode LPAREN() { return getToken(LalalangParser.LPAREN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(LalalangParser.RPAREN, 0); }
		public BlockWithBracesContext blockWithBraces() {
			return getRuleContext(BlockWithBracesContext.class,0);
		}
		public WhileStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whileStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LalalangListener ) ((LalalangListener)listener).enterWhileStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LalalangListener ) ((LalalangListener)listener).exitWhileStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LalalangVisitor ) return ((LalalangVisitor<? extends T>)visitor).visitWhileStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WhileStatementContext whileStatement() throws RecognitionException {
		WhileStatementContext _localctx = new WhileStatementContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_whileStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(98);
			match(WHILE);
			setState(99);
			match(LPAREN);
			setState(100);
			expression(0);
			setState(101);
			match(RPAREN);
			setState(102);
			((WhileStatementContext)_localctx).loopBody = blockWithBraces();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IfStatementContext extends ParserRuleContext {
		public BlockWithBracesContext ifTrueBody;
		public BlockWithBracesContext ifFalseBody;
		public TerminalNode IF() { return getToken(LalalangParser.IF, 0); }
		public TerminalNode LPAREN() { return getToken(LalalangParser.LPAREN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(LalalangParser.RPAREN, 0); }
		public List<BlockWithBracesContext> blockWithBraces() {
			return getRuleContexts(BlockWithBracesContext.class);
		}
		public BlockWithBracesContext blockWithBraces(int i) {
			return getRuleContext(BlockWithBracesContext.class,i);
		}
		public TerminalNode ELSE() { return getToken(LalalangParser.ELSE, 0); }
		public IfStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LalalangListener ) ((LalalangListener)listener).enterIfStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LalalangListener ) ((LalalangListener)listener).exitIfStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LalalangVisitor ) return ((LalalangVisitor<? extends T>)visitor).visitIfStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfStatementContext ifStatement() throws RecognitionException {
		IfStatementContext _localctx = new IfStatementContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_ifStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(104);
			match(IF);
			setState(105);
			match(LPAREN);
			setState(106);
			expression(0);
			setState(107);
			match(RPAREN);
			setState(108);
			((IfStatementContext)_localctx).ifTrueBody = blockWithBraces();
			setState(111);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ELSE) {
				{
				setState(109);
				match(ELSE);
				setState(110);
				((IfStatementContext)_localctx).ifFalseBody = blockWithBraces();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AssignmentContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(LalalangParser.Identifier, 0); }
		public TerminalNode ASSIGN() { return getToken(LalalangParser.ASSIGN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public AssignmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LalalangListener ) ((LalalangListener)listener).enterAssignment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LalalangListener ) ((LalalangListener)listener).exitAssignment(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LalalangVisitor ) return ((LalalangVisitor<? extends T>)visitor).visitAssignment(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignmentContext assignment() throws RecognitionException {
		AssignmentContext _localctx = new AssignmentContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_assignment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(113);
			match(Identifier);
			setState(114);
			match(ASSIGN);
			setState(115);
			expression(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ReturnStatementContext extends ParserRuleContext {
		public TerminalNode RETURN() { return getToken(LalalangParser.RETURN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ReturnStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_returnStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LalalangListener ) ((LalalangListener)listener).enterReturnStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LalalangListener ) ((LalalangListener)listener).exitReturnStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LalalangVisitor ) return ((LalalangVisitor<? extends T>)visitor).visitReturnStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReturnStatementContext returnStatement() throws RecognitionException {
		ReturnStatementContext _localctx = new ReturnStatementContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_returnStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(117);
			match(RETURN);
			setState(118);
			expression(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public ExpressionContext left;
		public ExpressionContext nested;
		public Token operator;
		public ExpressionContext right;
		public TerminalNode LPAREN() { return getToken(LalalangParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(LalalangParser.RPAREN, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public FunctionCallContext functionCall() {
			return getRuleContext(FunctionCallContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(LalalangParser.Identifier, 0); }
		public TerminalNode Literal() { return getToken(LalalangParser.Literal, 0); }
		public UnaryExpressionContext unaryExpression() {
			return getRuleContext(UnaryExpressionContext.class,0);
		}
		public TerminalNode MUL() { return getToken(LalalangParser.MUL, 0); }
		public TerminalNode DIV() { return getToken(LalalangParser.DIV, 0); }
		public TerminalNode MOD() { return getToken(LalalangParser.MOD, 0); }
		public TerminalNode PLUS() { return getToken(LalalangParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(LalalangParser.MINUS, 0); }
		public TerminalNode GT() { return getToken(LalalangParser.GT, 0); }
		public TerminalNode GEQ() { return getToken(LalalangParser.GEQ, 0); }
		public TerminalNode LT() { return getToken(LalalangParser.LT, 0); }
		public TerminalNode LEQ() { return getToken(LalalangParser.LEQ, 0); }
		public TerminalNode EQ() { return getToken(LalalangParser.EQ, 0); }
		public TerminalNode NEQ() { return getToken(LalalangParser.NEQ, 0); }
		public TerminalNode AND() { return getToken(LalalangParser.AND, 0); }
		public TerminalNode OR() { return getToken(LalalangParser.OR, 0); }
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LalalangListener ) ((LalalangListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LalalangListener ) ((LalalangListener)listener).exitExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LalalangVisitor ) return ((LalalangVisitor<? extends T>)visitor).visitExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 22;
		enterRecursionRule(_localctx, 22, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(129);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				{
				setState(121);
				match(LPAREN);
				setState(122);
				((ExpressionContext)_localctx).nested = expression(0);
				setState(123);
				match(RPAREN);
				}
				break;
			case 2:
				{
				setState(125);
				functionCall();
				}
				break;
			case 3:
				{
				setState(126);
				match(Identifier);
				}
				break;
			case 4:
				{
				setState(127);
				match(Literal);
				}
				break;
			case 5:
				{
				setState(128);
				unaryExpression();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(148);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(146);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
					case 1:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.left = _prevctx;
						_localctx.left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(131);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(132);
						((ExpressionContext)_localctx).operator = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MUL) | (1L << DIV) | (1L << MOD))) != 0)) ) {
							((ExpressionContext)_localctx).operator = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(133);
						((ExpressionContext)_localctx).right = expression(10);
						}
						break;
					case 2:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.left = _prevctx;
						_localctx.left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(134);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(135);
						((ExpressionContext)_localctx).operator = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==PLUS || _la==MINUS) ) {
							((ExpressionContext)_localctx).operator = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(136);
						((ExpressionContext)_localctx).right = expression(9);
						}
						break;
					case 3:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.left = _prevctx;
						_localctx.left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(137);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(138);
						((ExpressionContext)_localctx).operator = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LT) | (1L << LEQ) | (1L << GT) | (1L << GEQ))) != 0)) ) {
							((ExpressionContext)_localctx).operator = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(139);
						((ExpressionContext)_localctx).right = expression(8);
						}
						break;
					case 4:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.left = _prevctx;
						_localctx.left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(140);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(141);
						((ExpressionContext)_localctx).operator = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==EQ || _la==NEQ) ) {
							((ExpressionContext)_localctx).operator = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(142);
						((ExpressionContext)_localctx).right = expression(7);
						}
						break;
					case 5:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.left = _prevctx;
						_localctx.left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(143);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(144);
						((ExpressionContext)_localctx).operator = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==AND || _la==OR) ) {
							((ExpressionContext)_localctx).operator = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(145);
						((ExpressionContext)_localctx).right = expression(6);
						}
						break;
					}
					} 
				}
				setState(150);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class UnaryExpressionContext extends ParserRuleContext {
		public TerminalNode PLUS() { return getToken(LalalangParser.PLUS, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode MINUS() { return getToken(LalalangParser.MINUS, 0); }
		public UnaryExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unaryExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LalalangListener ) ((LalalangListener)listener).enterUnaryExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LalalangListener ) ((LalalangListener)listener).exitUnaryExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LalalangVisitor ) return ((LalalangVisitor<? extends T>)visitor).visitUnaryExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnaryExpressionContext unaryExpression() throws RecognitionException {
		UnaryExpressionContext _localctx = new UnaryExpressionContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_unaryExpression);
		try {
			setState(155);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PLUS:
				enterOuterAlt(_localctx, 1);
				{
				setState(151);
				match(PLUS);
				setState(152);
				expression(0);
				}
				break;
			case MINUS:
				enterOuterAlt(_localctx, 2);
				{
				setState(153);
				match(MINUS);
				setState(154);
				expression(0);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionCallContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(LalalangParser.Identifier, 0); }
		public TerminalNode LPAREN() { return getToken(LalalangParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(LalalangParser.RPAREN, 0); }
		public ArgumentsContext arguments() {
			return getRuleContext(ArgumentsContext.class,0);
		}
		public FunctionCallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionCall; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LalalangListener ) ((LalalangListener)listener).enterFunctionCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LalalangListener ) ((LalalangListener)listener).exitFunctionCall(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LalalangVisitor ) return ((LalalangVisitor<? extends T>)visitor).visitFunctionCall(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionCallContext functionCall() throws RecognitionException {
		FunctionCallContext _localctx = new FunctionCallContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_functionCall);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(157);
			match(Identifier);
			setState(158);
			match(LPAREN);
			setState(160);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LPAREN) | (1L << PLUS) | (1L << MINUS) | (1L << Identifier) | (1L << Literal))) != 0)) {
				{
				setState(159);
				arguments();
				}
			}

			setState(162);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArgumentsContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(LalalangParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(LalalangParser.COMMA, i);
		}
		public ArgumentsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arguments; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LalalangListener ) ((LalalangListener)listener).enterArguments(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LalalangListener ) ((LalalangListener)listener).exitArguments(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LalalangVisitor ) return ((LalalangVisitor<? extends T>)visitor).visitArguments(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgumentsContext arguments() throws RecognitionException {
		ArgumentsContext _localctx = new ArgumentsContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_arguments);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(164);
			expression(0);
			setState(169);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(165);
				match(COMMA);
				setState(166);
				expression(0);
				}
				}
				setState(171);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 11:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 9);
		case 1:
			return precpred(_ctx, 8);
		case 2:
			return precpred(_ctx, 7);
		case 3:
			return precpred(_ctx, 6);
		case 4:
			return precpred(_ctx, 5);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\"\u00af\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\3\2\3\2\3\2\3\3\7\3"+
		"%\n\3\f\3\16\3(\13\3\3\3\3\3\6\3,\n\3\r\3\16\3-\7\3\60\n\3\f\3\16\3\63"+
		"\13\3\3\3\3\3\5\3\67\n\3\5\39\n\3\3\3\7\3<\n\3\f\3\16\3?\13\3\3\4\3\4"+
		"\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5L\n\5\3\6\3\6\3\6\3\6\5\6R\n\6"+
		"\3\6\3\6\3\6\3\7\3\7\3\7\3\7\5\7[\n\7\3\b\3\b\3\b\7\b`\n\b\f\b\16\bc\13"+
		"\b\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5\nr\n\n\3\13\3"+
		"\13\3\13\3\13\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\5\r\u0084"+
		"\n\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\7\r\u0095"+
		"\n\r\f\r\16\r\u0098\13\r\3\16\3\16\3\16\3\16\5\16\u009e\n\16\3\17\3\17"+
		"\3\17\5\17\u00a3\n\17\3\17\3\17\3\20\3\20\3\20\7\20\u00aa\n\20\f\20\16"+
		"\20\u00ad\13\20\3\20\2\3\30\21\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36"+
		"\2\7\3\2\24\26\3\2\22\23\3\2\31\34\3\2\27\30\3\2\35\36\2\u00bb\2 \3\2"+
		"\2\2\4&\3\2\2\2\6@\3\2\2\2\bK\3\2\2\2\nM\3\2\2\2\fV\3\2\2\2\16\\\3\2\2"+
		"\2\20d\3\2\2\2\22j\3\2\2\2\24s\3\2\2\2\26w\3\2\2\2\30\u0083\3\2\2\2\32"+
		"\u009d\3\2\2\2\34\u009f\3\2\2\2\36\u00a6\3\2\2\2 !\5\4\3\2!\"\7\2\2\3"+
		"\"\3\3\2\2\2#%\7\3\2\2$#\3\2\2\2%(\3\2\2\2&$\3\2\2\2&\'\3\2\2\2\'\61\3"+
		"\2\2\2(&\3\2\2\2)+\5\b\5\2*,\7\3\2\2+*\3\2\2\2,-\3\2\2\2-+\3\2\2\2-.\3"+
		"\2\2\2.\60\3\2\2\2/)\3\2\2\2\60\63\3\2\2\2\61/\3\2\2\2\61\62\3\2\2\2\62"+
		"8\3\2\2\2\63\61\3\2\2\2\64\66\5\b\5\2\65\67\7\3\2\2\66\65\3\2\2\2\66\67"+
		"\3\2\2\2\679\3\2\2\28\64\3\2\2\289\3\2\2\29=\3\2\2\2:<\7\3\2\2;:\3\2\2"+
		"\2<?\3\2\2\2=;\3\2\2\2=>\3\2\2\2>\5\3\2\2\2?=\3\2\2\2@A\7\20\2\2AB\5\4"+
		"\3\2BC\7\21\2\2C\7\3\2\2\2DL\5\n\6\2EL\5\26\f\2FL\5\20\t\2GL\5\22\n\2"+
		"HL\5\24\13\2IL\5\30\r\2JL\5\f\7\2KD\3\2\2\2KE\3\2\2\2KF\3\2\2\2KG\3\2"+
		"\2\2KH\3\2\2\2KI\3\2\2\2KJ\3\2\2\2L\t\3\2\2\2MN\7\f\2\2NO\7 \2\2OQ\7\16"+
		"\2\2PR\5\16\b\2QP\3\2\2\2QR\3\2\2\2RS\3\2\2\2ST\7\17\2\2TU\5\6\4\2U\13"+
		"\3\2\2\2VW\7\13\2\2WZ\7 \2\2XY\7\37\2\2Y[\5\30\r\2ZX\3\2\2\2Z[\3\2\2\2"+
		"[\r\3\2\2\2\\a\7 \2\2]^\7\r\2\2^`\7 \2\2_]\3\2\2\2`c\3\2\2\2a_\3\2\2\2"+
		"ab\3\2\2\2b\17\3\2\2\2ca\3\2\2\2de\7\n\2\2ef\7\16\2\2fg\5\30\r\2gh\7\17"+
		"\2\2hi\5\6\4\2i\21\3\2\2\2jk\7\b\2\2kl\7\16\2\2lm\5\30\r\2mn\7\17\2\2"+
		"nq\5\6\4\2op\7\t\2\2pr\5\6\4\2qo\3\2\2\2qr\3\2\2\2r\23\3\2\2\2st\7 \2"+
		"\2tu\7\37\2\2uv\5\30\r\2v\25\3\2\2\2wx\7\7\2\2xy\5\30\r\2y\27\3\2\2\2"+
		"z{\b\r\1\2{|\7\16\2\2|}\5\30\r\2}~\7\17\2\2~\u0084\3\2\2\2\177\u0084\5"+
		"\34\17\2\u0080\u0084\7 \2\2\u0081\u0084\7!\2\2\u0082\u0084\5\32\16\2\u0083"+
		"z\3\2\2\2\u0083\177\3\2\2\2\u0083\u0080\3\2\2\2\u0083\u0081\3\2\2\2\u0083"+
		"\u0082\3\2\2\2\u0084\u0096\3\2\2\2\u0085\u0086\f\13\2\2\u0086\u0087\t"+
		"\2\2\2\u0087\u0095\5\30\r\f\u0088\u0089\f\n\2\2\u0089\u008a\t\3\2\2\u008a"+
		"\u0095\5\30\r\13\u008b\u008c\f\t\2\2\u008c\u008d\t\4\2\2\u008d\u0095\5"+
		"\30\r\n\u008e\u008f\f\b\2\2\u008f\u0090\t\5\2\2\u0090\u0095\5\30\r\t\u0091"+
		"\u0092\f\7\2\2\u0092\u0093\t\6\2\2\u0093\u0095\5\30\r\b\u0094\u0085\3"+
		"\2\2\2\u0094\u0088\3\2\2\2\u0094\u008b\3\2\2\2\u0094\u008e\3\2\2\2\u0094"+
		"\u0091\3\2\2\2\u0095\u0098\3\2\2\2\u0096\u0094\3\2\2\2\u0096\u0097\3\2"+
		"\2\2\u0097\31\3\2\2\2\u0098\u0096\3\2\2\2\u0099\u009a\7\22\2\2\u009a\u009e"+
		"\5\30\r\2\u009b\u009c\7\23\2\2\u009c\u009e\5\30\r\2\u009d\u0099\3\2\2"+
		"\2\u009d\u009b\3\2\2\2\u009e\33\3\2\2\2\u009f\u00a0\7 \2\2\u00a0\u00a2"+
		"\7\16\2\2\u00a1\u00a3\5\36\20\2\u00a2\u00a1\3\2\2\2\u00a2\u00a3\3\2\2"+
		"\2\u00a3\u00a4\3\2\2\2\u00a4\u00a5\7\17\2\2\u00a5\35\3\2\2\2\u00a6\u00ab"+
		"\5\30\r\2\u00a7\u00a8\7\r\2\2\u00a8\u00aa\5\30\r\2\u00a9\u00a7\3\2\2\2"+
		"\u00aa\u00ad\3\2\2\2\u00ab\u00a9\3\2\2\2\u00ab\u00ac\3\2\2\2\u00ac\37"+
		"\3\2\2\2\u00ad\u00ab\3\2\2\2\23&-\61\668=KQZaq\u0083\u0094\u0096\u009d"+
		"\u00a2\u00ab";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}