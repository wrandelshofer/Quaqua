/*
 * @(#)QuaquaDefaultButtonAnimator.java
 * Quaqua Look and Feel. Copyright © 2020 Werner Randelshofer, Switzerland. MIT License.
 */
package ch.randelshofer.quaqua;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Iterator;
import javax.swing.JButton;
import javax.swing.Timer;

/**
 * Animates the default button.
 *
 * @author Werner Randelshofer
 * @version 1.0 2011-08-31 Created.
 */
public class QuaquaDefaultButtonAnimator {

    private final static HashSet<JButton> defaultButtons = new HashSet<JButton>();
    private final static Timer timer = new Timer(30, new ActionListener() {

        public void actionPerformed(ActionEvent e) {
            for (Iterator<JButton> i = defaultButtons.iterator(); i.hasNext();) {
                JButton b = i.next();
                if (b.isDefaultButton() && QuaquaUtilities.isOnActiveWindow(b)) {
                    b.repaint();
                } else {
                    i.remove();
                }
            }
            if (defaultButtons.isEmpty()) {
                timer.stop();
            }
        }
    });

    public static void addDefaultButton(JButton b) {
        if (b.isDefaultButton() && QuaquaUtilities.isOnActiveWindow(b)) {
            if (defaultButtons.add((JButton) b)) {
                timer.start();
            }
        }
    }
}
