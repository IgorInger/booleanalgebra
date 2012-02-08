lexer grammar BooleanAlgebraLexer;

@header {
package de.inger.booleanalgebra.antlr3.stubs;

import java.io.PrintStream;
}

@members {

	private PrintStream out = System.out;
	private PrintStream err = System.err;

	public void setOutPrintStream(PrintStream ps) {
		if(ps != null) {
			this.out = ps;
		}
	}

	public void setErrPrintStream(PrintStream ps) {
		if(ps != null) {
			this.err = ps;
		}
	}

	@Override
	public void emitErrorMessage(String message) {
		this.err.println(message);
	}

}

// Operators

AND: '&&';
OR: '||';
NOT: '!';
ASSIGN: '=';
DEFINE: ':=';
EQUAL: '==';
NEQUAL: '!=';

// Controls

LBRACE: '(';
RBRACE: ')';
SEMICOLON: ';';
COMMA: ',';

// Operands & predefined constants
TRUE: 'true';
FALSE: 'false';
ID: LETTER (LETTER | DIGIT | '_')*;

// Fragments

fragment LETTER: UPPER | LOWER;
fragment UPPER: 'A'..'Z';
fragment LOWER: 'a'..'z';
fragment DIGIT: '0'..'9';
fragment SPACE: ' ' | '\t';
fragment NEWLINE: '\r'? '\n';

WHITESPACE: (NEWLINE | SPACE)+ { $channel = HIDDEN; };
