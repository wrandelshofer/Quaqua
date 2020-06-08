/*
 * @(#)NonEDTTest.java
 * Quaqua Look and Feel. Copyright © 2020 Werner Randelshofer, Switzerland. MIT License.
 */
package test;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 * NonEDTTest.
 *
 * @author Werner Randelshofer
 * @version 1.0 May 1, 2008 Created.
 */
public class NonEDTTest {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(TestManager.getQuaquaLookAndFeelClassName());
        } catch (Exception ex) {
            Logger.getLogger(NonEDTTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        JFileChooser fc = new JFileChooser();

        JFrame f = new JFrame("NonEDTest");
        f.getContentPane().add(fc);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setVisible(true);
    }
}
