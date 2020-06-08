/*
 * @(#)TextIcon.java
 * Quaqua Look and Feel. Copyright © 2020 Werner Randelshofer, Switzerland. MIT License.
 */

package test;

import java.awt.*;
import javax.swing.*;
/**
 * TextIcon.
 *
 * @author  Werner Randelshofer
 * @version 1.0 June 6, 2005 Created.
 */
public class TextIcon implements Icon {
    private JLabel label;
    /**
     * Creates a new instance.
     */
    public TextIcon(String text) {
        this.label = new JLabel(text);
        label.setSize(label.getPreferredSize());
    }

    public int getIconHeight() {
        return label.getHeight();
    }

    public int getIconWidth() {
        return label.getWidth();
    }

    public void paintIcon(Component c, Graphics g, int x, int y) {
        g.translate(x, y);
        label.paint(g);
        g.translate(-x, -y);
    }
}
