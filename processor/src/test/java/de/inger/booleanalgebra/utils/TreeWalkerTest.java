package de.inger.booleanalgebra.utils;

import java.util.List;

import org.antlr.runtime.RecognitionException;
import org.junit.Test;

import de.inger.booleanalgebra.BooleanLogicProcessor;
import de.inger.booleanalgebra.EmptyTreeException;
import de.inger.booleanalgebra.antlr3.stubs.BATreeParser;
import de.inger.booleanalgebra.antlr3.treenodes.Operand;
import de.inger.booleanalgebra.lang.utils.IdempotenceSimplifier;

public class TreeWalkerTest {

    @Test
    public void simpleTest() throws EmptyTreeException, RecognitionException {
	BooleanLogicProcessor processor = new BooleanLogicProcessor();
	BATreeParser parser = processor.getTreeParserForString("a && a == a");
	List<Operand> operands = parser.script();
	Operand operand = IdempotenceSimplifier.simplify(operands.get(0));
	System.out.println(operand);
    }

}
