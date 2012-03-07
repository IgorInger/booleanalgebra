package de.inger.booleanalgebra.antlr3.stubs.exceptions;

import org.antlr.runtime.RecognitionException;

public class ErrorHeaderHelper {

    public static String getErrorHeader(RecognitionException e) {
	return e.line + ":" + e.charPositionInLine;
    }

}
