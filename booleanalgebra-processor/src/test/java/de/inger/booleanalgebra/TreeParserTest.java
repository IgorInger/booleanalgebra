package de.inger.booleanalgebra;

import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RecognizerSharedState;
import org.hamcrest.core.IsNot;
import org.junit.Assert;
import org.junit.Test;

import de.inger.booleanalgebra.antlr3.stubs.BATreeParser;

public class TreeParserTest {

    public BooleanLogicProcessor getProcessor() {
	return new BooleanLogicProcessor();
    }

    @Test
    public void testFunctiondAndCalls() throws EmptyTreeException, RecognitionException {
	BooleanLogicProcessor processor = getProcessor();
	BATreeParser treeParser = null;
	String text = null;

	text = "f(x) := x;";
	treeParser = processor.getTreeParserForString(text);
	treeParser.script();
	processor.setRecognizerSharedState(new RecognizerSharedState());
	Assert.assertEquals(0, treeParser.getNumberOfSyntaxErrors());

	text = "f(x, y) := x || y;";
	treeParser = processor.getTreeParserForString(text);
	treeParser.script();
	processor.setRecognizerSharedState(new RecognizerSharedState());
	Assert.assertEquals(0, treeParser.getNumberOfSyntaxErrors());

	text = "f(x, y, x) := x || x;";
	treeParser = processor.getTreeParserForString(text);
	treeParser.script();
	processor.setRecognizerSharedState(new RecognizerSharedState());
	Assert.assertThat(treeParser.getNumberOfSyntaxErrors(), IsNot.not(0));

	text = "f(x, true) := x || x;";
	treeParser = processor.getTreeParserForString(text);
	treeParser.script();
	processor.setRecognizerSharedState(new RecognizerSharedState());
	Assert.assertThat(treeParser.getNumberOfSyntaxErrors(), IsNot.not(0));

	text = "f() := y || y;";
	treeParser = processor.getTreeParserForString(text);
	treeParser.script();
	processor.setRecognizerSharedState(new RecognizerSharedState());
	Assert.assertEquals(0, treeParser.getNumberOfSyntaxErrors());

	text = "a = f() := y || y;";
	treeParser = processor.getTreeParserForString(text);
	treeParser.script();
	processor.setRecognizerSharedState(new RecognizerSharedState());
	Assert.assertThat(treeParser.getNumberOfSyntaxErrors(), IsNot.not(0));

	text = "f(x);";
	treeParser = processor.getTreeParserForString(text);
	treeParser.script();
	processor.setRecognizerSharedState(new RecognizerSharedState());
	Assert.assertEquals(0, treeParser.getNumberOfSyntaxErrors());

	text = "f(x, y);";
	treeParser = processor.getTreeParserForString(text);
	treeParser.script();
	processor.setRecognizerSharedState(new RecognizerSharedState());
	Assert.assertEquals(0, treeParser.getNumberOfSyntaxErrors());

	text = "f(x, x);";
	treeParser = processor.getTreeParserForString(text);
	treeParser.script();
	processor.setRecognizerSharedState(new RecognizerSharedState());
	Assert.assertEquals(0, treeParser.getNumberOfSyntaxErrors());

	text = "f(true, true);";
	treeParser = processor.getTreeParserForString(text);
	treeParser.script();
	processor.setRecognizerSharedState(new RecognizerSharedState());
	Assert.assertEquals(0, treeParser.getNumberOfSyntaxErrors());

	text = "f();";
	treeParser = processor.getTreeParserForString(text);
	treeParser.script();
	processor.setRecognizerSharedState(new RecognizerSharedState());
	Assert.assertEquals(0, treeParser.getNumberOfSyntaxErrors());

	text = "f(f(x));";
	treeParser = processor.getTreeParserForString(text);
	treeParser.script();
	processor.setRecognizerSharedState(new RecognizerSharedState());
	Assert.assertEquals(0, treeParser.getNumberOfSyntaxErrors());

	text = "a = f();";
	treeParser = processor.getTreeParserForString(text);
	treeParser.script();
	processor.setRecognizerSharedState(new RecognizerSharedState());
	Assert.assertEquals(0, treeParser.getNumberOfSyntaxErrors());
    }

    @Test
    public void testBinaryOperator() throws EmptyTreeException, RecognitionException {
	BooleanLogicProcessor processor = getProcessor();
	BATreeParser treeParser = null;
	String text = null;

	text = "a && a;";
	treeParser = processor.getTreeParserForString(text);
	treeParser.script();
	Assert.assertEquals(0, treeParser.getNumberOfSyntaxErrors());

	text = "a || a;";
	treeParser = processor.getTreeParserForString(text);
	treeParser.script();
	Assert.assertEquals(0, treeParser.getNumberOfSyntaxErrors());

	text = "a == a;";
	treeParser = processor.getTreeParserForString(text);
	treeParser.script();
	Assert.assertEquals(0, treeParser.getNumberOfSyntaxErrors());

	text = "a != a;";
	treeParser = processor.getTreeParserForString(text);
	treeParser.script();
	Assert.assertEquals(0, treeParser.getNumberOfSyntaxErrors());

	text = "a = b;";
	treeParser = processor.getTreeParserForString(text);
	treeParser.script();
	Assert.assertEquals(0, treeParser.getNumberOfSyntaxErrors());
    }

    @Test
    public void testAssignment() throws EmptyTreeException, RecognitionException {
	BooleanLogicProcessor processor = getProcessor();
	BATreeParser treeParser = null;
	String text = null;

	text = "a = b = true;";
	treeParser = processor.getTreeParserForString(text);
	treeParser.script();
	Assert.assertEquals(0, treeParser.getNumberOfSyntaxErrors());
    }

    @Test
    public void testAssotiativity() throws EmptyTreeException, RecognitionException {
	BooleanLogicProcessor processor = getProcessor();
	BATreeParser treeParser = null;
	String text = null;

	// Assotiativity

	text = "(a || (b || c)) == ((a || b) || c);";
	treeParser = processor.getTreeParserForString(text);
	treeParser.script();
	Assert.assertEquals(0, treeParser.getNumberOfSyntaxErrors());

	text = "(a && (b && c)) == ((a && b) && c);";
	treeParser = processor.getTreeParserForString(text);
	treeParser.script();
	Assert.assertEquals(0, treeParser.getNumberOfSyntaxErrors());
    }

    @Test
    public void testIdempotence() throws EmptyTreeException, RecognitionException {
	BooleanLogicProcessor processor = getProcessor();
	BATreeParser treeParser = null;
	String text = null;

	// Idempotence

	text = "(a && a) == a;";
	treeParser = processor.getTreeParserForString(text);
	treeParser.script();
	Assert.assertEquals(0, treeParser.getNumberOfSyntaxErrors());

	text = "(a || a) == a;";
	treeParser = processor.getTreeParserForString(text);
	treeParser.script();
	Assert.assertEquals(0, treeParser.getNumberOfSyntaxErrors());
    }

    @Test
    public void testCommutativity() throws EmptyTreeException, RecognitionException {
	BooleanLogicProcessor processor = getProcessor();
	BATreeParser treeParser = null;
	String text = null;

	// Commutativity

	text = "(a || b) == (b || a);";
	treeParser = processor.getTreeParserForString(text);
	treeParser.script();
	Assert.assertEquals(0, treeParser.getNumberOfSyntaxErrors());

	text = "(a && b) == (b && a);";
	treeParser = processor.getTreeParserForString(text);
	treeParser.script();
	Assert.assertEquals(0, treeParser.getNumberOfSyntaxErrors());
    }

    @Test
    public void testAbsorption() throws EmptyTreeException, RecognitionException {
	BooleanLogicProcessor processor = getProcessor();
	BATreeParser treeParser = null;
	String text = null;

	// Absorption

	text = "(a || (a && b)) == a;";
	treeParser = processor.getTreeParserForString(text);
	treeParser.script();
	Assert.assertEquals(0, treeParser.getNumberOfSyntaxErrors());

	text = "(a && (a || b)) == a;";
	treeParser = processor.getTreeParserForString(text);
	treeParser.script();
	Assert.assertEquals(0, treeParser.getNumberOfSyntaxErrors());
    }

    @Test
    public void testDistributivity() throws EmptyTreeException, RecognitionException {
	BooleanLogicProcessor processor = getProcessor();
	BATreeParser treeParser = null;
	String text = null;

	// Distributivity

	text = "(a || (b && c)) == ((a || b) && (a || c));";
	treeParser = processor.getTreeParserForString(text);
	treeParser.script();
	Assert.assertEquals(0, treeParser.getNumberOfSyntaxErrors());

	text = "(a && (b || c)) == ((a && b) || (a && c));";
	treeParser = processor.getTreeParserForString(text);
	treeParser.script();
	Assert.assertEquals(0, treeParser.getNumberOfSyntaxErrors());
    }

    @Test
    public void testComplements() throws EmptyTreeException, RecognitionException {
	BooleanLogicProcessor processor = getProcessor();
	BATreeParser treeParser = null;
	String text = null;

	// Complements

	text = "(a || !a) == true;";
	treeParser = processor.getTreeParserForString(text);
	treeParser.script();
	Assert.assertEquals(0, treeParser.getNumberOfSyntaxErrors());

	text = "(a && !a) == false;";
	treeParser = processor.getTreeParserForString(text);
	treeParser.script();
	Assert.assertEquals(0, treeParser.getNumberOfSyntaxErrors());
    }

    @Test
    public void testNeutrality() throws EmptyTreeException, RecognitionException {
	BooleanLogicProcessor processor = getProcessor();
	BATreeParser treeParser = null;
	String text = null;

	// Neutralitätsgesetze

	text = "(a && true) == a;";
	treeParser = processor.getTreeParserForString(text);
	treeParser.script();
	Assert.assertEquals(0, treeParser.getNumberOfSyntaxErrors());

	text = "(a || false) == a;";
	treeParser = processor.getTreeParserForString(text);
	treeParser.script();
	Assert.assertEquals(0, treeParser.getNumberOfSyntaxErrors());
    }

    @Test
    public void testExtremality() throws EmptyTreeException, RecognitionException {
	BooleanLogicProcessor processor = getProcessor();
	BATreeParser treeParser = null;
	String text = null;

	// Extremalgesetze

	text = "(a && false) == false;";
	treeParser = processor.getTreeParserForString(text);
	treeParser.script();
	Assert.assertEquals(0, treeParser.getNumberOfSyntaxErrors());

	text = "(a || true) == true;";
	treeParser = processor.getTreeParserForString(text);
	treeParser.script();
	Assert.assertEquals(0, treeParser.getNumberOfSyntaxErrors());
    }

    @Test
    public void testInvolution() throws EmptyTreeException, RecognitionException {
	BooleanLogicProcessor processor = getProcessor();
	BATreeParser treeParser = null;
	String text = null;

	// Involution

	text = "(!!a) == a;";
	treeParser = processor.getTreeParserForString(text);
	treeParser.script();
	Assert.assertEquals(0, treeParser.getNumberOfSyntaxErrors());
    }

    @Test
    public void testDeMorgansLaws() throws EmptyTreeException, RecognitionException {
	BooleanLogicProcessor processor = getProcessor();
	BATreeParser treeParser = null;
	String text = null;

	// De Morgansche Gesetze

	text = "(!(a && b)) == (!a || !b);";
	treeParser = processor.getTreeParserForString(text);
	treeParser.script();
	Assert.assertEquals(0, treeParser.getNumberOfSyntaxErrors());

	text = "(!(a || b)) == (!a && !b);";
	treeParser = processor.getTreeParserForString(text);
	treeParser.script();
	Assert.assertEquals(0, treeParser.getNumberOfSyntaxErrors());
    }

}
