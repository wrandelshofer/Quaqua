/*
 * @(#)EmptyIcon.java
 * Quaqua Look and Feel. Copyright 2020 © Werner Randelshofer, Switzerland. MIT License.
 */

package ch.randelshofer.quaqua.icon;

import java.awt.Component;
import java.awt.Graphics;
import java.io.Serializable;
import javax.swing.Icon;

/**
 * {@code EmptyIcon}.
 *
 * @author Werner Randelshofer
 * @version $Id$
 */
public class EmptyIcon implements Icon, Serializable {
    private int width;
    private int height;

    public EmptyIcon(int width, int height) {
        this.width=width;
        this.height=height;
    }

    public void paintIcon(Component c, Graphics g, int x, int y) {
        // empty
    }

    public int getIconWidth() {
        return width;
    }

    public int getIconHeight() {
        return height;
    }

}
