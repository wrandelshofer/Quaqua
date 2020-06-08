/*
 * @(#)ComboBoxSeparatorProblem.java
 * Quaqua Look and Feel. Copyright © 2020 Werner Randelshofer, Switzerland. MIT License.
 */

package test;

import java.awt.*;
import javax.swing.*;

/**
 * {@code ComboBoxSeparatorProblem}.
 *
 * @author Werner Randelshofer
 * @version 1.0 2011-07-04 Created.
 */
public class ComboBoxSeparatorProblem {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(TestManager.getLookAndFeelClassName());
				}
				catch (Exception e) {
					e.printStackTrace();
				}

				final JComboBox comboBox = new JComboBox(new Object[] {
						"One", "Two", "Three"
				});
				comboBox.setRenderer(new DefaultListCellRenderer() {
					@Override
					public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
						final Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
						if (index == 1) {
							final JPanel panel = new JPanel(new BorderLayout());
							panel.add(new JLabel("--- a separator in a combobox ---"), BorderLayout.NORTH);
							panel.add(renderer, BorderLayout.CENTER);
							panel.setOpaque(true);
							return panel;
						}
						return renderer;
					}
				});

				final JFrame frame = new JFrame();
				frame.setContentPane(comboBox);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
	}
}