/*
 * @(#)FocusedBorder.java
 * Quaqua Look and Feel. Copyright © 2020 Werner Randelshofer, Switzerland. MIT License.
 */
package ch.randelshofer.quaqua.border;

import ch.randelshofer.quaqua.VisualMargin;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import javax.swing.JComponent;
import javax.swing.border.Border;

/**
 * Draws a focus ring around the opaque pixels of a border.
 * The border must provide space for the focus ring.
 *
 * @author Werner Randelshofer
 * @version 1.0 2011-07-26 Created.
 */
public class FocusedBorder extends AbstractFocusedPainter implements PressedCueBorder, VisualMargin {

    private Border actualBorder;

    public FocusedBorder(Border actualBorder) {
        this.actualBorder = actualBorder;
    }

    @Override
    public void paintBorder(Component c, Graphics cgx, int x, int y, int width, int height) {
        paint(c, cgx, x, y, width, height);
    }

    @Override
    protected void doPaint(Component c, Graphics cgx, int x, int y, int width, int height) {
        actualBorder.paintBorder(c, cgx, x, y, width, height);
    }

    public Insets getBorderInsets(Component c) {
        return actualBorder.getBorderInsets(c);
    }

    public boolean isBorderOpaque() {
        return false;
    }

    public Insets getVisualMargin(Component c) {
        if (actualBorder instanceof VisualMargin) {
            return ((VisualMargin) actualBorder).getVisualMargin(c);
        }
        return new Insets(0, 0, 0, 0);
    }

    public boolean hasPressedCue(JComponent c) {
        if (actualBorder instanceof PressedCueBorder) {
            return ((PressedCueBorder) actualBorder).hasPressedCue(c);
        }
        return true;
    }

    public static class UIResource extends FocusedBorder implements javax.swing.plaf.UIResource {

        public UIResource(Border actualBorder) {
            super(actualBorder);
        }


    }
}
