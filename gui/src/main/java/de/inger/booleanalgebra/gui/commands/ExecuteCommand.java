package de.inger.booleanalgebra.gui.commands;

import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import org.antlr.runtime.RecognitionException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.richclient.application.Application;
import org.springframework.richclient.command.support.ApplicationWindowAwareCommand;

import de.inger.booleanalgebra.BooleanLogicProcessor;
import de.inger.booleanalgebra.EmptyTreeException;
import de.inger.booleanalgebra.antlr3.stubs.BATreeParser;
import de.inger.booleanalgebra.gui.view.InputTextView;

public class ExecuteCommand extends ApplicationWindowAwareCommand {

    private static final String ID = "executeCommand";

    private static final Logger LOGGER = LogManager.getLogger(ExecuteCommand.class);

    public ExecuteCommand() {
	super(ID);
    }

    @Override
    protected void doExecuteCommand() {
	InputTextView view = (InputTextView) Application.instance().getActiveWindow().getPage().getActiveComponent();
	Document document = view.getDocument();
	int offset = document.getStartPosition().getOffset();
	int length = document.getLength();
	String text = null;
	try {
	    text = document.getText(offset, length);
	} catch (BadLocationException e) {
	    LOGGER.error(e);
	}

	BooleanLogicProcessor processor = new BooleanLogicProcessor();
	try {
	    BATreeParser parser = processor.getTreeParserForString(text);
	    LOGGER.debug(parser.script());
	} catch (EmptyTreeException e) {
	    LOGGER.error(e);
	} catch (RecognitionException e) {
	    LOGGER.error(e);
	}



    }

}
