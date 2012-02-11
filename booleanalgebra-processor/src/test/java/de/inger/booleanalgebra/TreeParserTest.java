package de.inger.booleanalgebra;

import org.antlr.runtime.RecognitionException;
import org.hamcrest.core.IsNot;
import org.junit.Assert;
import org.junit.Test;

import de.inger.booleanalgebra.antlr3.stubs.BATreeParser;

public class TreeParserTest {

    public BooleanLogicProcessor getProcessor() {
	return new BooleanLogicProcessor();
    }

    @Test
    public void testSimpleExpression() throws EmptyTreeException, RecognitionException {
	BooleanLogicProcessor processor = getProcessor();
	BATreeParser treeParser = null;
	String text = null;

	text = "a && a;";
	treeParser = processor.getTreeParserForString(text);
	treeParser.script();

	text = "a || a;";
	treeParser = processor.getTreeParserForString(text);
	treeParser.script();
	Assert.assertEquals(0, treeParser.getNumberOfSyntaxErrors());

	text = "!a;";
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

	text = "f(x) := x;";
	treeParser = processor.getTreeParserForString(text);
	treeParser.script();
	Assert.assertEquals(0, treeParser.getNumberOfSyntaxErrors());

	text = "f(x, y) := x || y;";
	treeParser = processor.getTreeParserForString(text);
	treeParser.script();
	Assert.assertEquals(0, treeParser.getNumberOfSyntaxErrors());

	text = "f(x, x) := x || x;";
	treeParser = processor.getTreeParserForString(text);
	treeParser.script();
	Assert.assertThat(treeParser.getNumberOfSyntaxErrors(), IsNot.not(0));

	text = "f() := x || x;";
	treeParser = processor.getTreeParserForString(text);
	treeParser.script();
	Assert.assertEquals(0, treeParser.getNumberOfSyntaxErrors());

	text = "f(x);";
	treeParser = processor.getTreeParserForString(text);
	treeParser.script();
	Assert.assertEquals(0, treeParser.getNumberOfSyntaxErrors());
    }

    @Test
    public void testDefinitions() throws EmptyTreeException, RecognitionException {
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

	// Idempotence

	text = "(a && a) == a;";
	treeParser = processor.getTreeParserForString(text);
	treeParser.script();
	Assert.assertEquals(0, treeParser.getNumberOfSyntaxErrors());

	text = "(a || a) == a;";
	treeParser = processor.getTreeParserForString(text);
	treeParser.script();
	Assert.assertEquals(0, treeParser.getNumberOfSyntaxErrors());

	// Commutativity

	text = "(a || b) == (b || a);";
	treeParser = processor.getTreeParserForString(text);
	treeParser.script();
	Assert.assertEquals(0, treeParser.getNumberOfSyntaxErrors());

	text = "(a && b) == (b && a);";
	treeParser = processor.getTreeParserForString(text);
	treeParser.script();
	Assert.assertEquals(0, treeParser.getNumberOfSyntaxErrors());

	// Absorption

	text = "(a || (a && b)) == a;";
	treeParser = processor.getTreeParserForString(text);
	treeParser.script();
	Assert.assertEquals(0, treeParser.getNumberOfSyntaxErrors());

	text = "(a && (a || b)) == a;";
	treeParser = processor.getTreeParserForString(text);
	treeParser.script();
	Assert.assertEquals(0, treeParser.getNumberOfSyntaxErrors());

	// Distributivity

	text = "(a || (b && c)) == ((a || b) && (a || c));";
	treeParser = processor.getTreeParserForString(text);
	treeParser.script();
	Assert.assertEquals(0, treeParser.getNumberOfSyntaxErrors());

	text = "(a && (b || c)) == ((a && b) || (a && c));";
	treeParser = processor.getTreeParserForString(text);
	treeParser.script();
	Assert.assertEquals(0, treeParser.getNumberOfSyntaxErrors());

	// Complements

	text = "(a || !a) == true;";
	treeParser = processor.getTreeParserForString(text);
	treeParser.script();
	Assert.assertEquals(0, treeParser.getNumberOfSyntaxErrors());

	text = "(a && !a) == false;";
	treeParser = processor.getTreeParserForString(text);
	treeParser.script();
	Assert.assertEquals(0, treeParser.getNumberOfSyntaxErrors());

	// Neutralitätsgesetze

	text = "(a && true) == a;";
	treeParser = processor.getTreeParserForString(text);
	treeParser.script();
	Assert.assertEquals(0, treeParser.getNumberOfSyntaxErrors());

	text = "(a || false) == a;";
	treeParser = processor.getTreeParserForString(text);
	treeParser.script();
	Assert.assertEquals(0, treeParser.getNumberOfSyntaxErrors());

	// Extremalgesetze

	text = "(a && false) == false;";
	treeParser = processor.getTreeParserForString(text);
	treeParser.script();
	Assert.assertEquals(0, treeParser.getNumberOfSyntaxErrors());

	text = "(a || true) == true;";
	treeParser = processor.getTreeParserForString(text);
	treeParser.script();
	Assert.assertEquals(0, treeParser.getNumberOfSyntaxErrors());

	// Involution

	text = "(!!a) == a;";
	treeParser = processor.getTreeParserForString(text);
	treeParser.script();
	Assert.assertEquals(0, treeParser.getNumberOfSyntaxErrors());

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
