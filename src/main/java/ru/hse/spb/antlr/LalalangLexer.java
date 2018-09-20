// Generated from /Users/alexvangogen/Fall/kotlin-course/kotlin-course-se/src/main/resources/ru.hse.spb.antlr/Lalalang.g4 by ANTLR 4.7
package ru.hse.spb.antlr;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class LalalangLexer extends Lexer {
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
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"NEWLINE", "WHITESPACE", "ONELINE_COMMENT", "MULTILINE_COMMENT", "RETURN", 
		"IF", "ELSE", "WHILE", "VAR", "FUN", "COMMA", "LPAREN", "RPAREN", "LBRACK", 
		"RBRACK", "PLUS", "MINUS", "MUL", "DIV", "MOD", "EQ", "NEQ", "LT", "LEQ", 
		"GT", "GEQ", "AND", "OR", "ASSIGN", "Identifier", "Literal", "LetterOrUnderscore", 
		"ZeroDigit", "DigitWithoutZero", "Digit", "MismatchedSymbol"
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


	public LalalangLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Lalalang.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\"\u00d2\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\3\2\3\2\3\3\6\3O\n\3\r\3\16\3P\3\3\3\3\3"+
		"\4\3\4\3\4\3\4\7\4Y\n\4\f\4\16\4\\\13\4\3\4\3\4\3\5\3\5\3\5\3\5\7\5d\n"+
		"\5\f\5\16\5g\13\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7"+
		"\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3"+
		"\13\3\13\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\21"+
		"\3\21\3\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3\26\3\27\3\27"+
		"\3\27\3\30\3\30\3\31\3\31\3\31\3\32\3\32\3\33\3\33\3\33\3\34\3\34\3\34"+
		"\3\35\3\35\3\35\3\36\3\36\3\37\3\37\3\37\7\37\u00ba\n\37\f\37\16\37\u00bd"+
		"\13\37\3 \3 \3 \7 \u00c2\n \f \16 \u00c5\13 \5 \u00c7\n \3!\3!\3\"\3\""+
		"\3#\3#\3$\3$\3%\3%\3e\2&\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f"+
		"\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63"+
		"\33\65\34\67\359\36;\37= ?!A\2C\2E\2G\2I\"\3\2\b\4\2\f\f\17\17\4\2\13"+
		"\13\"\"\5\2C\\aac|\3\2\62\62\3\2\63;\3\2\62;\2\u00d4\2\3\3\2\2\2\2\5\3"+
		"\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2"+
		"\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3"+
		"\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'"+
		"\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63"+
		"\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2"+
		"?\3\2\2\2\2I\3\2\2\2\3K\3\2\2\2\5N\3\2\2\2\7T\3\2\2\2\t_\3\2\2\2\13m\3"+
		"\2\2\2\rt\3\2\2\2\17w\3\2\2\2\21|\3\2\2\2\23\u0082\3\2\2\2\25\u0086\3"+
		"\2\2\2\27\u008a\3\2\2\2\31\u008c\3\2\2\2\33\u008e\3\2\2\2\35\u0090\3\2"+
		"\2\2\37\u0092\3\2\2\2!\u0094\3\2\2\2#\u0096\3\2\2\2%\u0098\3\2\2\2\'\u009a"+
		"\3\2\2\2)\u009c\3\2\2\2+\u009e\3\2\2\2-\u00a1\3\2\2\2/\u00a4\3\2\2\2\61"+
		"\u00a6\3\2\2\2\63\u00a9\3\2\2\2\65\u00ab\3\2\2\2\67\u00ae\3\2\2\29\u00b1"+
		"\3\2\2\2;\u00b4\3\2\2\2=\u00b6\3\2\2\2?\u00c6\3\2\2\2A\u00c8\3\2\2\2C"+
		"\u00ca\3\2\2\2E\u00cc\3\2\2\2G\u00ce\3\2\2\2I\u00d0\3\2\2\2KL\t\2\2\2"+
		"L\4\3\2\2\2MO\t\3\2\2NM\3\2\2\2OP\3\2\2\2PN\3\2\2\2PQ\3\2\2\2QR\3\2\2"+
		"\2RS\b\3\2\2S\6\3\2\2\2TU\7\61\2\2UV\7\61\2\2VZ\3\2\2\2WY\n\2\2\2XW\3"+
		"\2\2\2Y\\\3\2\2\2ZX\3\2\2\2Z[\3\2\2\2[]\3\2\2\2\\Z\3\2\2\2]^\b\4\2\2^"+
		"\b\3\2\2\2_`\7\61\2\2`a\7,\2\2ae\3\2\2\2bd\13\2\2\2cb\3\2\2\2dg\3\2\2"+
		"\2ef\3\2\2\2ec\3\2\2\2fh\3\2\2\2ge\3\2\2\2hi\7,\2\2ij\7\61\2\2jk\3\2\2"+
		"\2kl\b\5\2\2l\n\3\2\2\2mn\7t\2\2no\7g\2\2op\7v\2\2pq\7w\2\2qr\7t\2\2r"+
		"s\7p\2\2s\f\3\2\2\2tu\7k\2\2uv\7h\2\2v\16\3\2\2\2wx\7g\2\2xy\7n\2\2yz"+
		"\7u\2\2z{\7g\2\2{\20\3\2\2\2|}\7y\2\2}~\7j\2\2~\177\7k\2\2\177\u0080\7"+
		"n\2\2\u0080\u0081\7g\2\2\u0081\22\3\2\2\2\u0082\u0083\7x\2\2\u0083\u0084"+
		"\7c\2\2\u0084\u0085\7t\2\2\u0085\24\3\2\2\2\u0086\u0087\7h\2\2\u0087\u0088"+
		"\7w\2\2\u0088\u0089\7p\2\2\u0089\26\3\2\2\2\u008a\u008b\7.\2\2\u008b\30"+
		"\3\2\2\2\u008c\u008d\7*\2\2\u008d\32\3\2\2\2\u008e\u008f\7+\2\2\u008f"+
		"\34\3\2\2\2\u0090\u0091\7}\2\2\u0091\36\3\2\2\2\u0092\u0093\7\177\2\2"+
		"\u0093 \3\2\2\2\u0094\u0095\7-\2\2\u0095\"\3\2\2\2\u0096\u0097\7/\2\2"+
		"\u0097$\3\2\2\2\u0098\u0099\7,\2\2\u0099&\3\2\2\2\u009a\u009b\7\61\2\2"+
		"\u009b(\3\2\2\2\u009c\u009d\7\'\2\2\u009d*\3\2\2\2\u009e\u009f\7?\2\2"+
		"\u009f\u00a0\7?\2\2\u00a0,\3\2\2\2\u00a1\u00a2\7#\2\2\u00a2\u00a3\7?\2"+
		"\2\u00a3.\3\2\2\2\u00a4\u00a5\7>\2\2\u00a5\60\3\2\2\2\u00a6\u00a7\7>\2"+
		"\2\u00a7\u00a8\7?\2\2\u00a8\62\3\2\2\2\u00a9\u00aa\7@\2\2\u00aa\64\3\2"+
		"\2\2\u00ab\u00ac\7@\2\2\u00ac\u00ad\7?\2\2\u00ad\66\3\2\2\2\u00ae\u00af"+
		"\7(\2\2\u00af\u00b0\7(\2\2\u00b08\3\2\2\2\u00b1\u00b2\7~\2\2\u00b2\u00b3"+
		"\7~\2\2\u00b3:\3\2\2\2\u00b4\u00b5\7?\2\2\u00b5<\3\2\2\2\u00b6\u00bb\5"+
		"A!\2\u00b7\u00ba\5A!\2\u00b8\u00ba\5G$\2\u00b9\u00b7\3\2\2\2\u00b9\u00b8"+
		"\3\2\2\2\u00ba\u00bd\3\2\2\2\u00bb\u00b9\3\2\2\2\u00bb\u00bc\3\2\2\2\u00bc"+
		">\3\2\2\2\u00bd\u00bb\3\2\2\2\u00be\u00c7\5C\"\2\u00bf\u00c3\5E#\2\u00c0"+
		"\u00c2\5G$\2\u00c1\u00c0\3\2\2\2\u00c2\u00c5\3\2\2\2\u00c3\u00c1\3\2\2"+
		"\2\u00c3\u00c4\3\2\2\2\u00c4\u00c7\3\2\2\2\u00c5\u00c3\3\2\2\2\u00c6\u00be"+
		"\3\2\2\2\u00c6\u00bf\3\2\2\2\u00c7@\3\2\2\2\u00c8\u00c9\t\4\2\2\u00c9"+
		"B\3\2\2\2\u00ca\u00cb\t\5\2\2\u00cbD\3\2\2\2\u00cc\u00cd\t\6\2\2\u00cd"+
		"F\3\2\2\2\u00ce\u00cf\t\7\2\2\u00cfH\3\2\2\2\u00d0\u00d1\13\2\2\2\u00d1"+
		"J\3\2\2\2\n\2PZe\u00b9\u00bb\u00c3\u00c6\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}