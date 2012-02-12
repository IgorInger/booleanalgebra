package de.inger.booleanalgebra.gui.view;

import java.util.Vector;

import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.springframework.context.ApplicationContext;
import org.springframework.richclient.application.Application;
import org.springframework.richclient.application.support.AbstractView;

public class ApplicationContextView extends AbstractView {

    @Override
    protected JComponent createControl() {
	ApplicationContext applicationContext = Application.instance().getApplicationContext();

	String[] names = applicationContext.getBeanDefinitionNames();

	Vector<Vector<String>> rows = new Vector<Vector<String>>();
	for (String name : names) {
	    Object bean = applicationContext.getBean(name);

	    Vector<String> col = new Vector<String>();
	    col.add(name);
	    col.add(bean.getClass().getSimpleName());
	    rows.add(col);
	}

	Vector<String> header = new Vector<String>();
	header.add("Name");
	header.add("Class");
	TableModel model = new DefaultTableModel(rows, header);

	JTable jTable = getComponentFactory().createTable(model);
	jTable.setEnabled(false);

	return jTable;
    }

}
