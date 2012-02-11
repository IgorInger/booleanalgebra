package de.inger.booleanalgebra.gui.commands;

import org.springframework.richclient.command.support.ApplicationWindowAwareCommand;

public class SaveFileCommand extends ApplicationWindowAwareCommand {

    private static final String ID = "saveFileCommand";

    public SaveFileCommand() {
	super(ID);
    }

    @Override
    protected void doExecuteCommand() {
    }

}
