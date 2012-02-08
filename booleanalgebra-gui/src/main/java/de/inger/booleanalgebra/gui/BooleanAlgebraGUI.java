package de.inger.booleanalgebra.gui;

import org.springframework.richclient.application.ApplicationLauncher;

public class BooleanAlgebraGUI {

	public static void main(String[] args) {
		String applicationContextPath = "de/inger/booleanalgebra/gui/application-context.xml";
		new ApplicationLauncher(applicationContextPath);
	}

}
