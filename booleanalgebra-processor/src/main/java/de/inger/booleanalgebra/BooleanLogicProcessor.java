package de.inger.booleanalgebra;

import java.io.IOException;

import org.antlr.runtime.ANTLRFileStream;

import org.antlr.runtime.ANTLRStringStream;

import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.Lexer;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.TokenStream;
import org.antlr.runtime.tree.CommonTreeNodeStream;
import org.antlr.runtime.tree.TreeNodeStream;

import de.inger.booleanalgebra.stubs.BooleanAlgebraLexer;
import de.inger.booleanalgebra.stubs.BooleanAlgebraParser;
import de.inger.booleanalgebra.stubs.BooleanAlgebraTreeParser;
import de.inger.booleanalgebra.stubs.BooleanAlgebraParser.script_return;

public class BooleanLogicProcessor implements
		Processor<BooleanAlgebraLexer, BooleanAlgebraParser, BooleanAlgebraTreeParser> {

	@Override
	public CharStream getCharStreamFromString(String text) {
		return new ANTLRStringStream(text);
	}

	@Override
	public CharStream getCharStreamFromFile(String fileName) throws IOException {
		return new ANTLRFileStream(fileName);
	}

	@Override
	public TokenStream getTokenStream(Lexer lexer) {
		return new CommonTokenStream(lexer);
	}

	@Override
	public BooleanAlgebraParser getParser(TokenStream tokenStream) {
		return new BooleanAlgebraParser(tokenStream);
	}

	@Override
	public BooleanAlgebraLexer getLexer(CharStream charStream) {
		BooleanAlgebraLexer lexer = new BooleanAlgebraLexer(charStream);
		return lexer;
	}

	public BooleanAlgebraParser getParserForString(String text) {
		CharStream charStream = getCharStreamFromString(text);
		BooleanAlgebraLexer lexer = getLexer(charStream);
		TokenStream tokenStream = getTokenStream(lexer);
		BooleanAlgebraParser parser = getParser(tokenStream);
		return parser;
	}

	public BooleanAlgebraParser getParserForFile(String fileName) throws IOException {
		CharStream charStream = getCharStreamFromFile(fileName);
		BooleanAlgebraLexer lexer = getLexer(charStream);
		TokenStream tokenStream = getTokenStream(lexer);
		BooleanAlgebraParser parser = getParser(tokenStream);
		return parser;
	}

	@Override
	public Object getTree(BooleanAlgebraParser parser) throws RecognitionException, EmptyTreeException {
		script_return result = parser.script();
		Object tree = result.getTree();
		if (tree == null) {
			throw new EmptyTreeException();
		}
		return tree;
	}

	@Override
	public TreeNodeStream getTreeNodeStream(Object tree) {
		TreeNodeStream treeNodeStream = new CommonTreeNodeStream(tree);
		return treeNodeStream;
	}

	@Override
	public BooleanAlgebraTreeParser getTreeParser(TreeNodeStream treeNodeStream) {
		BooleanAlgebraTreeParser treeParser = new BooleanAlgebraTreeParser(treeNodeStream);
		return treeParser;
	}

	public BooleanAlgebraTreeParser getTreeParserForString(String text) throws RecognitionException, EmptyTreeException {
		BooleanAlgebraParser parser = getParserForString(text);
		Object tree = getTree(parser);
		TreeNodeStream treeNodeStream = getTreeNodeStream(tree);
		BooleanAlgebraTreeParser treeParser = getTreeParser(treeNodeStream);
		return treeParser;
	}

	public BooleanAlgebraTreeParser getTreeParserForFile(String fileName) throws IOException, RecognitionException,
			EmptyTreeException {
		BooleanAlgebraParser parser = getParserForFile(fileName);
		Object tree = getTree(parser);
		TreeNodeStream treeNodeStream = getTreeNodeStream(tree);
		BooleanAlgebraTreeParser treeParser = getTreeParser(treeNodeStream);
		return treeParser;
	}

}
