package de.inger.booleanalgebra.gui.view;

import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import javax.swing.JTextArea;

public class TextAreaPanel extends JPanel {

	/** TODO: Add a comment. */
	private static final long serialVersionUID = -6032380959673922910L;

	private JTextArea textArea;

	public TextAreaPanel() {
		initializeComponents();
	}

	private void initializeComponents() {
		setLayout(new BorderLayout());

		textArea = new JTextArea();
		textArea.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				super.keyReleased(e);
				int keyCode = e.getKeyCode();

				if (e.isControlDown() && (keyCode == KeyEvent.VK_ENTER)) {
					System.out.println(e);
				}
			}
		});
		JScrollPane scrollPane = new JScrollPane(textArea);
		add(scrollPane, BorderLayout.CENTER);
	}

	public JTextArea getTextArea() {
		return textArea;
	}

}
