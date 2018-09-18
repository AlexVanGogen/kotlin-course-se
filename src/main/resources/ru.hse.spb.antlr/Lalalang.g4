grammar Lalalang;

file            : block EOF;
block           : NEWLINE* (statement NEWLINE+)* (statement NEWLINE?)? NEWLINE*;
blockWithBraces : LBRACK block RBRACK;
statement       : function | variable | expression | whileStatement | ifStatement | assignment | returnStatement;
function        : FUN Identifier LPAREN parameterNames RPAREN functionBody=blockWithBraces;
variable        : VAR Identifier (ASSIGN expression)?;
parameterNames  : Identifier (COMMA Identifier)*;
whileStatement  : WHILE LPAREN expression RPAREN loopBody=blockWithBraces;
ifStatement     : IF LPAREN expression RPAREN ifTrueBody=blockWithBraces (ELSE ifFalseBody=blockWithBraces)?;
assignment      : Identifier ASSIGN expression;
returnStatement : RETURN expression;
expression      : LPAREN expression RPAREN
                | functionCall
                | Identifier
                | Literal
                | left=expression operator=(MUL | DIV | MOD)     right=expression
                | left=expression operator=(PLUS | MINUS)        right=expression
                | left=expression operator=(GT | GEQ | LT | LEQ) right=expression
                | left=expression operator=(EQ | NEQ)            right=expression
                | left=expression operator=(AND | OR)            right=expression
                | unaryExpression;
unaryExpression : PLUS expression | MINUS expression;

functionCall    : Identifier LPAREN arguments RPAREN ;
arguments       : expression (COMMA expression)* ;

NEWLINE             : [\r\n] ;
WHITESPACE          : [\t ]+ -> skip;

ONELINE_COMMENT     : '//' ~[\r\n]* -> skip;

MULTILINE_COMMENT   : '/*' .*? '*/' -> skip;

RETURN : 'return' ;
IF     : 'if';
ELSE   : 'else';
WHILE  : 'while';
VAR    : 'var';
FUN    : 'fun';

COMMA  : ',';
LPAREN : '(';
RPAREN : ')';
LBRACK : '{';
RBRACK : '}';

PLUS   : '+';
MINUS  : '-';
MUL    : '*';
DIV    : '/';
MOD    : '%';
EQ     : '==';
NEQ    : '!=';
LT     : '<';
LEQ    : '<=';
GT     : '>';
GEQ    : '>=';
AND    : '&&';
OR     : '||';
ASSIGN : '=';

/* Идентификатор как в Си */
Identifier : LetterOrUnderscore (LetterOrUnderscore | Digit)*;

/* Десятичный целочисленный литерал без ведущих нулей */
Literal : ZeroDigit | (DigitWithoutZero Digit*) ;

fragment LetterOrUnderscore : [_a-zA-Z] ;

fragment ZeroDigit : [0];

fragment DigitWithoutZero : [1-9];

fragment Digit : [0-9] ;

MismatchedSymbol: . ;