package de.inger.booleanalgebra.gui.commands;


import org.springframework.richclient.command.support.ApplicationWindowAwareCommand;

public class NewFileCommand extends ApplicationWindowAwareCommand {

    private static final String ID = "newFileCommand";

    public NewFileCommand() {
	super(ID);
    }

    public NewFileCommand(String commandId) {
	super(commandId);
    }

    @Override
    protected void doExecuteCommand() {

    }

}
