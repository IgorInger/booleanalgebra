package de.inger.booleanalgebra;

import java.util.List;

import org.antlr.runtime.RecognitionException;
import org.junit.Test;

import de.inger.booleanalgebra.antlr3.treenodes.Operand;
import de.inger.booleanalgebra.antlr3.stubs.BATreeParser;


public class TreeParserTest {

	public BooleanLogicProcessor getProcessor() {
		return new BooleanLogicProcessor();
	}

	@Test
	public void testSimpleExpression() throws EmptyTreeException, RecognitionException {
		BooleanLogicProcessor processor = getProcessor();
		BATreeParser treeParser = null;
		List<Operand> statements = null;
		String text = null;

		text = "a && a;";
		treeParser = processor.getTreeParserForString(text);
		statements = treeParser.script();
		System.out.println(statements);

		text = "a || a;";
		treeParser = processor.getTreeParserForString(text);
		statements = treeParser.script();
		System.out.println(statements);

		text = "!a;";
		treeParser = processor.getTreeParserForString(text);
		statements = treeParser.script();
		System.out.println(statements);

		text = "a == a;";
		treeParser = processor.getTreeParserForString(text);
		statements = treeParser.script();
		System.out.println(statements);

		text = "a != a;";
		treeParser = processor.getTreeParserForString(text);
		statements = treeParser.script();
		System.out.println(statements);

		text = "a = b;";
		treeParser = processor.getTreeParserForString(text);
		statements = treeParser.script();
		System.out.println(statements);

		text = "f(x) := x;";
		treeParser = processor.getTreeParserForString(text);
		statements = treeParser.script();
		System.out.println(statements);

		text = "f(x);";
		treeParser = processor.getTreeParserForString(text);
		statements = treeParser.script();
		System.out.println(statements);
	}

	@Test
	public void testDefinitions() throws EmptyTreeException, RecognitionException {
		BooleanLogicProcessor processor = getProcessor();
		BATreeParser treeParser = null;
		List<Operand> statements = null;
		String text = null;

		// Assotiativity

		text = "(a || (b || c)) == ((a || b) || c);";
		treeParser = processor.getTreeParserForString(text);
		statements = treeParser.script();
		System.out.println(statements);

		text = "(a && (b && c)) == ((a && b) && c);";
		treeParser = processor.getTreeParserForString(text);
		statements = treeParser.script();
		System.out.println(statements);

		// Idempotence

		text = "(a && a) == a;";
		treeParser = processor.getTreeParserForString(text);
		statements = treeParser.script();
		System.out.println(statements);

		text = "(a || a) == a;";
		treeParser = processor.getTreeParserForString(text);
		statements = treeParser.script();
		System.out.println(statements);

		// Commutativity

		text = "(a || b) == (b || a);";
		treeParser = processor.getTreeParserForString(text);
		statements = treeParser.script();
		System.out.println(statements);

		text = "(a && b) == (b && a);";
		treeParser = processor.getTreeParserForString(text);
		statements = treeParser.script();
		System.out.println(statements);

		// Absorption

		text = "(a || (a && b)) == a;";
		treeParser = processor.getTreeParserForString(text);
		statements = treeParser.script();
		System.out.println(statements);

		text = "(a && (a || b)) == a;";
		treeParser = processor.getTreeParserForString(text);
		statements = treeParser.script();
		System.out.println(statements);

		// Distributivity

		text = "(a || (b && c)) == ((a || b) && (a || c));";
		treeParser = processor.getTreeParserForString(text);
		statements = treeParser.script();
		System.out.println(statements);

		text = "(a && (b || c)) == ((a && b) || (a && c));";
		treeParser = processor.getTreeParserForString(text);
		statements = treeParser.script();
		System.out.println(statements);

		// Complements

		text = "(a || !a) == true;";
		treeParser = processor.getTreeParserForString(text);
		statements = treeParser.script();
		System.out.println(statements);

		text = "(a && !a) == false;";
		treeParser = processor.getTreeParserForString(text);
		statements = treeParser.script();
		System.out.println(statements);

		// Neutralitätsgesetze

		text = "(a && true) == a;";
		treeParser = processor.getTreeParserForString(text);
		statements = treeParser.script();
		System.out.println(statements);

		text = "(a || false) == a;";
		treeParser = processor.getTreeParserForString(text);
		statements = treeParser.script();
		System.out.println(statements);

		// Extremalgesetze

		text = "(a && false) == false;";
		treeParser = processor.getTreeParserForString(text);
		statements = treeParser.script();
		System.out.println(statements);

		text = "(a || true) == true;";
		treeParser = processor.getTreeParserForString(text);
		statements = treeParser.script();
		System.out.println(statements);

		// Involution

		text = "(!!a) == a;";
		treeParser = processor.getTreeParserForString(text);
		statements = treeParser.script();
		System.out.println(statements);

		// De Morgansche Gesetze

		text = "(!(a && b)) == (!a || !b);";
		treeParser = processor.getTreeParserForString(text);
		statements = treeParser.script();
		System.out.println(statements);

		text = "(!(a || b)) == (!a && !b);";
		treeParser = processor.getTreeParserForString(text);
		statements = treeParser.script();
		System.out.println(statements);

	}

}
