package de.inger.booleanalgebra;

import java.util.List;

import org.antlr.runtime.RecognitionException;
import org.junit.Test;

import de.inger.booleanalgebra.antlr3.stubs.BATreeParser;
import de.inger.booleanalgebra.antlr3.treenodes.Operand;

public class InterpreterTest {

    @Test
    public void testInterpreter() throws EmptyTreeException, RecognitionException {
        BooleanLogicProcessor processor = new BooleanLogicProcessor();
        BATreeParser treeParser = processor.getTreeParserForString("a = true; true; false; a; f(a, b, c) := a || b || c; f(a || a, a, a);");
        List<Operand> operands = treeParser.script();
        BooleanLogicInterpreter interpreter = new BooleanLogicInterpreter();
        interpreter.interprete(operands);

    }

}
