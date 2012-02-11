tree grammar BATreeParser;

options {
	ASTLabelType = CommonTree;
	tokenVocab = BAParser;
}

@header {
package de.inger.booleanalgebra.antlr3.stubs;

import java.io.PrintStream;

import de.inger.booleanalgebra.antlr3.treenodes.*;
import de.inger.booleanalgebra.antlr3.stubs.exceptions.*;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
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

script returns [List<Operand> result]
@init {
	result = new ArrayList<Operand>();
}
: (e=eval { result.add(e); } )*;

eval returns [Operand result]: e=expression { result = e; };

expression returns [Operand result]:
	^(DEFINE n=name args=arguments r=expression)  { result = new Function(n, args, r); }
	|
	^(DEFINE n=name r=expression) { result = new Function(n, r); }
	|
	^(CALL n=name p=parameters)  { result = new Call(n, p); }
	|
	^(CALL n=name)  { result = new Call(n); }
	|
	^(ASSIGN l=variable r=expression) { result = new AssignmentOperator(l, r); }
	|
	^(AND l=expression r=expression) { result = new AndOperator(l, r); }
	|
	^(OR l=expression r=expression) { result = new OrOperator(l, r); }
	|
	^(NOT r=expression)  { result = new NotOperator(r); }
	|
	^(EQUAL l=expression r=expression) { result = new EqualsOperator(l, r); }
	|
	^(NEQUAL l=expression r=expression) { result = new NotEqualsOperator(l, r); }
	|
	a=atom  { result = a; };

arguments returns [Set<String> result]
@init {
	result = new HashSet<String>();
}:
	^(ARGUMENT (n=name { boolean contains = result.add(n); if(!contains) {throw new DuplicateArgumentException(input, n);} } )+);

parameters returns [List<Operand> result]
@init {
	result = new ArrayList<Operand>();
}:
	^(PARAMETER (e=expression { result.add(e); } )+);

name returns [String result]:
	n=ID {result = n.getText(); };

variable returns [Operand result]:
	n=name { result = new Variable(n); };

atom returns [Operand result]:
	v=variable { result = v; }
	|
	TRUE   { result = new Constant(true); }
	|
	FALSE  { result = new Constant(false); };