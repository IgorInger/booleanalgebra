package de.inger.booleanalgebra.antlr3.stubs.exceptions;

import org.antlr.runtime.IntStream;
import org.antlr.runtime.RecognitionException;

public class DuplicateArgumentException extends RecognitionException {

    private static final long serialVersionUID = -7122257524297304417L;

    private String argument;

    public DuplicateArgumentException() {
	super();
    }

    public DuplicateArgumentException(IntStream input) {
	super(input);
    }

    public DuplicateArgumentException(IntStream input, String argument) {
	super(input);
	this.argument = argument;
    }

    @Override
    public String getMessage() {
	String message = "duplicate argument";
	if (argument == null) {
	    return message;
	}
	return message + " '" + argument + "'";
    }

}
