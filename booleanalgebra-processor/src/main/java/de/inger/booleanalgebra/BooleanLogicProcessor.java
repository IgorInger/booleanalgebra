package de.inger.booleanalgebra;

import java.io.IOException;

import org.antlr.runtime.ANTLRFileStream;

import org.antlr.runtime.ANTLRStringStream;

import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.Lexer;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RecognizerSharedState;
import org.antlr.runtime.TokenStream;
import org.antlr.runtime.tree.CommonTreeNodeStream;
import org.antlr.runtime.tree.TreeNodeStream;

import de.inger.booleanalgebra.antlr3.stubs.BALexer;
import de.inger.booleanalgebra.antlr3.stubs.BAParser;
import de.inger.booleanalgebra.antlr3.stubs.BATreeParser;
import de.inger.booleanalgebra.antlr3.stubs.BAParser.script_return;

public class BooleanLogicProcessor implements Processor<BALexer, BAParser, BATreeParser> {

    private RecognizerSharedState state = new RecognizerSharedState();

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
    public BAParser getParser(TokenStream tokenStream) {
	return new BAParser(tokenStream, state);
    }

    @Override
    public BALexer getLexer(CharStream charStream) {
	BALexer lexer = new BALexer(charStream, state);
	return lexer;
    }

    public BAParser getParserForString(String text) {
	CharStream charStream = getCharStreamFromString(text);
	BALexer lexer = getLexer(charStream);
	TokenStream tokenStream = getTokenStream(lexer);
	BAParser parser = getParser(tokenStream);
	return parser;
    }

    public BAParser getParserForFile(String fileName) throws IOException {
	CharStream charStream = getCharStreamFromFile(fileName);
	BALexer lexer = getLexer(charStream);
	TokenStream tokenStream = getTokenStream(lexer);
	BAParser parser = getParser(tokenStream);
	return parser;
    }

    @Override
    public Object getTree(BAParser parser) throws RecognitionException, EmptyTreeException {
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
    public BATreeParser getTreeParser(TreeNodeStream treeNodeStream) {
	BATreeParser treeParser = new BATreeParser(treeNodeStream, state);
	return treeParser;
    }

    public BATreeParser getTreeParserForString(String text) throws RecognitionException,
	    EmptyTreeException {
	BAParser parser = getParserForString(text);
	Object tree = getTree(parser);
	TreeNodeStream treeNodeStream = getTreeNodeStream(tree);
	BATreeParser treeParser = getTreeParser(treeNodeStream);
	return treeParser;
    }

    public BATreeParser getTreeParserForFile(String fileName) throws IOException,
	    RecognitionException, EmptyTreeException {
	BAParser parser = getParserForFile(fileName);
	Object tree = getTree(parser);
	TreeNodeStream treeNodeStream = getTreeNodeStream(tree);
	BATreeParser treeParser = getTreeParser(treeNodeStream);
	return treeParser;
    }

    public void setRecognizerSharedState(RecognizerSharedState state) {
	this.state = state;
    }

}
