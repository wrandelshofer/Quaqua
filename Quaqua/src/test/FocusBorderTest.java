/*
 * @(#)FocusBorderTest.java
 * Quaqua Look and Feel. Copyright 2020 © Werner Randelshofer, Switzerland. MIT License.
 */

package test;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * FocusBorderTest.
 *
 * @author Werner Randelshofer
 * @version 1.0 Jan 4, 2008 Created.
 */
public class FocusBorderTest {
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("ch.randelshofer.quaqua.QuaquaLookAndFeel");
		}
		catch (Exception e) {
			return;
		}

		final JRadioButton option1 = new JRadioButton("Option1");
		final JRadioButton option2 = new JRadioButton("Option2");
		final JTextField textField1 = new JTextField("Foo");
		final JTextField textField2 = new JTextField("Bar");
		final JTextField textField3 = new JTextField("Bazzzzzzzzz");
		final JTextField textField4 = new JTextField("Bazzzzzzzzz2");
		final ButtonGroup group = new ButtonGroup();
		group.add(option1);
		group.add(option2);
		option1.setSelected(true);
		final ActionListener actionListener = new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				final boolean firstOptionSelected = option1.isSelected();
				textField1.setEnabled(firstOptionSelected);
				textField2.setEnabled(firstOptionSelected);
				textField3.setEnabled(!firstOptionSelected);
				textField4.setEnabled(!firstOptionSelected);
			}
		};
		actionListener.actionPerformed(null);
		option1.addActionListener(actionListener);
		option2.addActionListener(actionListener);
		final JPanel panel = new JPanel(new BorderLayout());
		panel.add(createPanel(option1, textField1, textField2), BorderLayout.NORTH);
		panel.add(createPanel(option2, textField3, textField4), BorderLayout.SOUTH);
		panel.add(new JLabel("Click on 'Option2'-radio button to see the disabled 'Bar' text field with focus border"), BorderLayout.CENTER);
		final JFrame frame = new JFrame("Selection test");
		frame.setContentPane(panel);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		textField1.requestFocusInWindow();
	}

	private static JComponent createPanel(JRadioButton radioBtn, JTextField textField1, JTextField textField2) {
		final JPanel panel = new JPanel();
		panel.add(radioBtn);
		panel.add(textField1);
		panel.add(textField2);
		return panel;
	}
}