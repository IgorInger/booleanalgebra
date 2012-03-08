package de.inger.booleanalgebra.gui.commands;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;

import org.springframework.richclient.application.Application;
import org.springframework.richclient.application.ViewDescriptor;
import org.springframework.richclient.application.docking.vldocking.VLDockingViewDescriptor;
import org.springframework.richclient.application.support.DefaultApplicationServices;
import org.springframework.richclient.command.ActionCommand;
import org.springframework.richclient.command.CommandGroup;
import org.springframework.richclient.command.support.ApplicationWindowAwareCommand;

import de.inger.booleanalgebra.gui.view.InputTextView;

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
	DefaultApplicationServices services = (DefaultApplicationServices) Application.services();

	AutowireCapableBeanFactory factory = services.getApplicationContext()
		.getAutowireCapableBeanFactory();

	GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
	beanDefinition.setBeanClass(VLDockingViewDescriptor.class);
	MutablePropertyValues values = new MutablePropertyValues();
	values.addPropertyValue("viewClass", InputTextView.class.getName());
	beanDefinition.setPropertyValues(values);

	BeanDefinitionRegistry beanDefinitionRegistry = (BeanDefinitionRegistry) factory;
	beanDefinition.setAutowireCandidate(true);
	beanDefinitionRegistry.registerBeanDefinition("xxx", beanDefinition);

	CommandGroup group = Application.instance().getLifecycleAdvisor().getMenuBarCommandGroup();
	CommandGroup ac = (CommandGroup) group.find("windowMenu/showViewMenu");

	ViewDescriptor viewDescriptor = (ViewDescriptor) Application.instance()
		.getApplicationContext().getBean("xxx");
	ActionCommand actionCommand = viewDescriptor.createShowViewCommand(Application.instance()
		.getActiveWindow());
	actionCommand.setLabel("xx");

	ac.add(actionCommand, true);
    }

}
