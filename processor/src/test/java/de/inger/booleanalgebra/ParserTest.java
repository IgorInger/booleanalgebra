package de.inger.booleanalgebra;

import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RecognizerSharedState;
import org.antlr.runtime.tree.Tree;
import org.junit.Test;

import de.inger.booleanalgebra.antlr3.stubs.BAParser;

import static org.junit.Assert.assertEquals;

public class ParserTest {

	public BooleanLogicProcessor getProcessor() {
		return new BooleanLogicProcessor();
	}

	@Test
	public void testSimpleExpresison() throws RecognitionException {
		BooleanLogicProcessor processor = getProcessor();
		BAParser parser = null;
		String text = null;
		Tree tree = null;

		text = "a";
		parser = processor.getParserForString(text);
		tree = (Tree) parser.script().getTree();
		processor.setRecognizerSharedState(new RecognizerSharedState());
		assertEquals("a", tree.toStringTree());

		text = "true; false; a; b;";
		parser = processor.getParserForString(text);
		tree = (Tree) parser.script().getTree();
		processor.setRecognizerSharedState(new RecognizerSharedState());
		assertEquals("true false a b", tree.toStringTree());

		text = "a && b; a || b; !a; a == b; a != b;";
		parser = processor.getParserForString(text);
		tree = (Tree) parser.script().getTree();
		processor.setRecognizerSharedState(new RecognizerSharedState());
		assertEquals("(&& a b) (|| a b) (! a) (== a b) (!= a b)", tree.toStringTree());

		text = "a = true; b = a;";
		parser = processor.getParserForString(text);
		tree = (Tree) parser.script().getTree();
		processor.setRecognizerSharedState(new RecognizerSharedState());
		assertEquals("(= a true) (= b a)", tree.toStringTree());

		text = "(a && b) || (b && c);";
		parser = processor.getParserForString(text);
		tree = (Tree) parser.script().getTree();
		processor.setRecognizerSharedState(new RecognizerSharedState());
		assertEquals("(|| (&& a b) (&& b c))", tree.toStringTree());

		text = "f(x) := x; f(a);";
		parser = processor.getParserForString(text);
		tree = (Tree) parser.script().getTree();
		processor.setRecognizerSharedState(new RecognizerSharedState());
		assertEquals("(:= f (ARGUMENT x) x) (CALL f (PARAMETER a))", tree.toStringTree());
	}

	@Test
	public void testOrderOfOperations() throws RecognitionException {
		BooleanLogicProcessor processor = getProcessor();
		BAParser parser = null;
		String text = null;
		Tree tree = null;

		text = "a || b && c;";
		parser = processor.getParserForString(text);
		tree = (Tree) parser.script().getTree();
		processor.setRecognizerSharedState(new RecognizerSharedState());
		assertEquals("(|| a (&& b c))", tree.toStringTree());

		text = "(a || b) && c;";
		parser = processor.getParserForString(text);
		tree = (Tree) parser.script().getTree();
		processor.setRecognizerSharedState(new RecognizerSharedState());
		assertEquals("(&& (|| a b) c)", tree.toStringTree());

		text = "a = b || c;";
		parser = processor.getParserForString(text);
		tree = (Tree) parser.script().getTree();
		processor.setRecognizerSharedState(new RecognizerSharedState());
		assertEquals("(= a (|| b c))", tree.toStringTree());

		text = "a && b && c;";
		parser = processor.getParserForString(text);
		tree = (Tree) parser.script().getTree();
		processor.setRecognizerSharedState(new RecognizerSharedState());
		assertEquals("(&& (&& a b) c)", tree.toStringTree());

		text = "a = b = c;";
		parser = processor.getParserForString(text);
		tree = (Tree) parser.script().getTree();
		processor.setRecognizerSharedState(new RecognizerSharedState());
		assertEquals("(= a (= b c))", tree.toStringTree());
	}

}
