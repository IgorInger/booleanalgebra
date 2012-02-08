package de.inger.booleanalgebra;

import java.io.IOException;

import org.antlr.runtime.CharStream;
import org.antlr.runtime.Lexer;
import org.antlr.runtime.Parser;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.TokenStream;
import org.antlr.runtime.tree.TreeNodeStream;
import org.antlr.runtime.tree.TreeParser;

public interface Processor<L extends Lexer, P extends Parser, TP extends TreeParser> {

    CharStream getCharStreamFromString(String text);

    CharStream getCharStreamFromFile(String fileName) throws IOException;

    L getLexer(CharStream charStream);

    TokenStream getTokenStream(Lexer lexer);

    P getParser(TokenStream tokenStream);

    Object getTree(P parser) throws RecognitionException, BooleanLogicException;

    TreeNodeStream getTreeNodeStream(Object tree);
    
    TP getTreeParser(TreeNodeStream treeNodeStream);

}
