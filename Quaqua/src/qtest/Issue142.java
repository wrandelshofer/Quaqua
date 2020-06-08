/*
 * @(#)Issue142.java
 * Quaqua Look and Feel. Copyright © 2020 Werner Randelshofer, Switzerland. MIT License.
 */
package qtest;


import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

import java.awt.BorderLayout;
import javax.swing.SwingUtilities;


public class Issue142 {


    private static void createAndShowGUI() {
        final JFrame frame = new JFrame("JComboBox dropdown problem");
        final BorderLayout layout = new BorderLayout();
        final JPanel contentPane = new JPanel(layout);
        contentPane.setOpaque(true);
        contentPane.add(createComboBox(), BorderLayout.NORTH);
        contentPane.add(createComboBox(), BorderLayout.SOUTH);
        frame.setContentPane(contentPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


    private static JComboBox createComboBox() {
        final JComboBox comboBox = new JComboBox();
        comboBox.setEditable(true);
        final String[] items = new String[]{"Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6"};
        comboBox.setModel(new javax.swing.DefaultComboBoxModel(items));
        return comboBox;
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {


            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel("ch.randelshofer.quaqua.leopard.Quaqua15LeopardCrossPlatformLookAndFeel");
                    //UIManager.setLookAndFeel(new QuaquaLookAndFeel());
                    createAndShowGUI();
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }


        });
    }


}