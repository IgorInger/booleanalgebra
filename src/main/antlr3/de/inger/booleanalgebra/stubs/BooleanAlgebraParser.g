parser grammar BooleanAlgebraParser;

options {
	output = AST;
	ASTLabelType = CommonTree;
	tokenVocab = BooleanAlgebraLexer;
}

tokens {
	CALL;
	PARAMETER;
	ARGUMENT;
}


@header {
package de.inger.booleanalgebra.stubs;	

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


	void out(Object o) {
		this.out.println(o);
	}
	
	private boolean isLeftToRight(int type) {
		boolean ltr = true;
		if(type == ASSIGN) {
			ltr = false;
		}
		return ltr;
	}
	
	private int getOperatorPrecedence(int type) {
		int precedence = 0;
		switch(type) {
			case NOT:
				precedence = 3;
				break;				
			case AND:
				precedence = 13;
				break;
			case OR:
				precedence = 14;
				break;
			case EQUAL:
				precedence = 9;
				break;
			case NEQUAL:
				precedence = 9;
				break;
			case ASSIGN:
				precedence = 16;
				break;
				
			default:							
		}
		return precedence;
	}

    private int findPivot(List<Token> operators, int startIndex, int stopIndex) {
	    int pivot = startIndex;
	    int pivotRank = getOperatorPrecedence(operators.get(pivot).getType());
	    for (int i = startIndex + 1; i <= stopIndex; i++) {
	        int type = operators.get(i).getType();
	        int current = getOperatorPrecedence(type);
	        boolean ltr = isLeftToRight(type);
	        if (current > pivotRank || ((current == pivotRank) && ltr)) {
	            pivot = i;
	            pivotRank = current;
	        }
	    }
	    return pivot;
    }

	private Tree createPrecedenceTree(List<Tree> expressions, List<Token> operators, int startIndex, int stopIndex) {
	    if (stopIndex == startIndex) {
	    	return expressions.get(startIndex);
	    }
	    int pivot = findPivot(operators, startIndex, stopIndex - 1);
	    Tree root = (Tree) adaptor.nil();
	    Object root_1 = adaptor.nil();
	    root_1 = (Object)adaptor.becomeRoot( operators.get(pivot), root_1);
	    adaptor.addChild(root_1, createPrecedenceTree(expressions, operators, startIndex, pivot));
	    adaptor.addChild(root_1, createPrecedenceTree(expressions, operators, pivot + 1, stopIndex));
	    adaptor.addChild(root, root_1);
	    return root;	
	}

	// Create Precedence Tree
	private Tree createPT(List<Tree> expressions, List<Token> operators) {
		return createPrecedenceTree(expressions, operators, 0, expressions.size() - 1);	
	}
	
	private Tree createParameterTree(List<Tree> expressions) {
		adaptor.getToken(PARAMETER);
		Token parameter = new CommonToken(PARAMETER);
		CommonTree parameters = new CommonTree(parameter);
		for(Tree t : expressions) {
			parameters.addChild(t);
		}
		return parameters;
	}

}

// Start
script: 
	statement* EOF!;

// Chunks
variable: 
	ID;

constant: 
	TRUE | FALSE;

terminator: 
	SEMICOLON! | EOF!;

// Binary operatpr
b_op: 
	AND | OR | ASSIGN | EQUAL | NEQUAL;

// Unary operatpr
u_op: 
	NOT;

atom: 
	variable | constant;

// Logic

statement: 
	eval | define;

define:
	define_expr terminator;
	
define_expr:
	ID LBRACE arguments RBRACE DEFINE expr -> ^(DEFINE ID arguments expr);

arguments:
	variable (COMMA variable)* -> ^(ARGUMENT variable+); 		 

eval:
	expr terminator;

// Expression
expr 
@init {
// Expressions 
List<Tree>  exprs = new ArrayList<Tree>();
// Operators 
List<Token> ops   = new ArrayList<Token>(); 
} :
	( l=sub_expr { exprs.add(l.tree); } )	
	( o=b_op r=sub_expr { ops.add(o.start); exprs.add(r.tree);	} )* -> { createPT(exprs, ops) };

// Sub expression	
sub_expr:
	u_op^ sub_expr | term | (ID LBRACE parameters RBRACE) -> ^(CALL ID parameters);

parameters:
	expr (COMMA expr)* -> ^(PARAMETER expr+); 

term: 
	atom
	| 
	LBRACE! expr RBRACE!;
