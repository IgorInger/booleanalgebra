package de.inger.booleanalgebra.gui.view;

import javax.swing.JComponent;
import javax.swing.JTextArea;

import org.springframework.richclient.application.support.AbstractView;

public class InputTextView extends AbstractView {

    @Override
    protected JComponent createControl() {
	JTextArea area = getComponentFactory().createTextArea();
	return area;
    }

}
