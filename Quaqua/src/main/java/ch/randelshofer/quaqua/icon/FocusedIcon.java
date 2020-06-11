/*
 * @(#)FocusedIcon.java
 * Quaqua Look and Feel. Copyright © 2020 Werner Randelshofer, Switzerland. MIT License.
 */
package ch.randelshofer.quaqua.icon;

import ch.randelshofer.quaqua.border.AbstractFocusedPainter;

import javax.swing.Icon;
import java.awt.Component;
import java.awt.Graphics;

/**
 * Draws a focus ring around the opaque pixels of an icon.
 * The icon must provide space for the focus ring.
 *
 * @author Werner Randelshofer
 * @version 1.0 2011-07-26 Created.
 */
public class FocusedIcon extends AbstractFocusedPainter implements Icon {

    private Icon actualIcon;
    private final static int slack = 3;

    public FocusedIcon(Icon actualIcon) {
        this.actualIcon = actualIcon;
    }

    @Override
    public int getIconHeight() {
        return actualIcon.getIconHeight();
    }

    @Override
    public int getIconWidth() {
        return actualIcon.getIconWidth();
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        paint(c, g, x - slack, y - slack, getIconWidth() + slack * 2, getIconHeight() + slack * 2);
    }

    @Override
    protected void doPaint(Component c, Graphics g, int x, int y, int w, int h) {
        actualIcon.paintIcon(c, g, x + slack, y + slack);
    }
}
