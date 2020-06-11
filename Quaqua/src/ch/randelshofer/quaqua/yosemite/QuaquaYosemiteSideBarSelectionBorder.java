/*
 * @(#)QuaquaYosemiteSideBarSelectionBorder.java
 * Quaqua Look and Feel. Copyright © 2020 Werner Randelshofer, Switzerland. MIT License.
 */
package ch.randelshofer.quaqua.yosemite;

import ch.randelshofer.quaqua.*;
import ch.randelshofer.quaqua.color.ActivatableUIResource;
import ch.randelshofer.quaqua.color.FocusableUIResource;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.border.*;
import javax.swing.plaf.UIResource;

/**
 * QuaquaYosemiteSideBarSelectionBorder.
 *
 * @author Werner Randelshofer
 * @version $Id$
 */
public class QuaquaYosemiteSideBarSelectionBorder implements Border, UIResource {
    private final Color color;

    /** Creates a new instance. */
    public QuaquaYosemiteSideBarSelectionBorder() {
        this(new Color(0xcecece));
    }
    public QuaquaYosemiteSideBarSelectionBorder(Color color) {
        this.color = color;
    }

    public void paintBorder(Component c, Graphics gr, int x, int y, int width, int height) {
        Graphics2D g = (Graphics2D) gr;
        if (color instanceof ActivatableUIResource) {
            ((ActivatableUIResource)color).setActive(QuaquaUtilities.isOnActiveWindow(c));
        }
        if (color instanceof FocusableUIResource) {
            ((FocusableUIResource)color).setFocused(c.isFocusOwner());
        }
        g.setColor(color);
        g.fillRect(x,y,width,height);
    }

    public Insets getBorderInsets(Component c) {
        return new Insets(0, 0, 0, 0);
    }

    public boolean isBorderOpaque() {
        return true;
    }
}
