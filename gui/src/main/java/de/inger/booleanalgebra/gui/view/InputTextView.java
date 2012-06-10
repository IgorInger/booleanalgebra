package de.inger.booleanalgebra.gui.view;

import javax.swing.JComponent;
import javax.swing.JTextArea;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

import org.springframework.richclient.application.support.AbstractView;

public class InputTextView extends AbstractView {

    private Document document;

    public Document getDocument() {
	return document;
    }

    public void setDocument(Document document) {
	this.document = document;
    }

    public InputTextView() {
	document = new PlainDocument();
    }

    @Override
    protected JComponent createControl() {
	JTextArea area = getComponentFactory().createTextArea();
	area.setDocument(document);
	return area;
    }

}
