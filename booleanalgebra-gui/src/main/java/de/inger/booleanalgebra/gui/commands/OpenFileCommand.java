package de.inger.booleanalgebra.gui.commands;

import org.springframework.richclient.command.support.ApplicationWindowAwareCommand;

public class OpenFileCommand extends ApplicationWindowAwareCommand {

    private static final String ID = "openFileCommand";

    public OpenFileCommand() {
	super(ID);
    }

    @Override
    protected void doExecuteCommand() {
    }

}
