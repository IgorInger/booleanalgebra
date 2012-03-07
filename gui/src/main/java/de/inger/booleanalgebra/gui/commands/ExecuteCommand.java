package de.inger.booleanalgebra.gui.commands;

import org.springframework.richclient.command.support.ApplicationWindowAwareCommand;

public class ExecuteCommand extends ApplicationWindowAwareCommand {

    private static final String ID = "executeCommand";

    public ExecuteCommand() {
	super(ID);
    }

    @Override
    protected void doExecuteCommand() {
    }

}
