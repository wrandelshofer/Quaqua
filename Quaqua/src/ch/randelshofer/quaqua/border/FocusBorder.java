/*
 * @(#)FocusBorder.java
 * Quaqua Look and Feel. Copyright © 2020 Werner Randelshofer, Switzerland. MIT License.
 */

package ch.randelshofer.quaqua.border;

import ch.randelshofer.quaqua.QuaquaUtilities;

import javax.swing.AbstractButton;
import javax.swing.border.Border;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;

/**
 * A Border which only draws if the component has focus.
 *
 * @author Werner Randelshofer
 * @version $Id$
 */
public class FocusBorder implements Border {
    private Border focusRing;

    /**
     * Creates a new instance.
     */
    public FocusBorder(Border focusRing) {
        this.focusRing = focusRing;
    }

    public Insets getBorderInsets(Component c) {
        return focusRing.getBorderInsets(c);
    }

    public boolean isBorderOpaque() {
        return false;
    }

    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        if (c.isEnabled() &&
                QuaquaUtilities.isFocused(c)
                && (!(c instanceof AbstractButton) || ((AbstractButton) c).isFocusPainted())) {
            focusRing.paintBorder(c, g, x, y, width, height);
        }
    }
}
