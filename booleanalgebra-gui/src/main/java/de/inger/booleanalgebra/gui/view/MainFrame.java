package de.inger.booleanalgebra.gui.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JSplitPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class MainFrame extends JFrame {

	/** TODO: Add a comment. */
	private static final long serialVersionUID = -864599079678052820L;

	private TextAreaPanel inputPanel;

	private TextAreaPanel outputPanel;

	private JSplitPane splitPane;

	public MainFrame() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		initializeLookAndFeel();
		initializeComponents();
	}

	private void initializeLookAndFeel() {
		String systemLookAndFeel = UIManager.getSystemLookAndFeelClassName();
		try {
			UIManager.setLookAndFeel(systemLookAndFeel);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}

	private void initializeComponents() {
		setLayout(new BorderLayout());

		inputPanel = new TextAreaPanel();
		inputPanel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				super.keyReleased(e);
			}
		});
		outputPanel = new TextAreaPanel();
		outputPanel.getTextArea().setEditable(false);

		splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, inputPanel, outputPanel);
		splitPane.setDividerLocation(0.5);

		add(splitPane, BorderLayout.CENTER);

		setMinimumSize(new Dimension(300, 400));

		pack();
	}

	public TextAreaPanel getInputPanel() {
		return inputPanel;
	}

	public TextAreaPanel getOutputPanel() {
		return outputPanel;
	}

	public JSplitPane getSplitPane() {
		return splitPane;
	}

}
