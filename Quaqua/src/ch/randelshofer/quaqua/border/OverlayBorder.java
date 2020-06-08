/*
 * @(#)OverlayBorder.java
 * Quaqua Look and Feel. Copyright 2020 © Werner Randelshofer, Switzerland. MIT License.
 */

package ch.randelshofer.quaqua.border;

import java.awt.*;
import javax.swing.border.*;
/**
 * OverlayBorder.
 *
 * @author  Werner Randelshofer
 * @version $Id$
 */
public class OverlayBorder implements Border {
    private Border[] borders;

    /** Creates a new instance. */
    public OverlayBorder(Border first, Border second) {
        borders = new Border[] { first, second };
    }

    public Insets getBorderInsets(Component c) {
        return (Insets) borders[0].getBorderInsets(c).clone();
    }

    public boolean isBorderOpaque() {
        return false;
    }

    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        for (int i=0; i < borders.length; i++) {
            borders[i].paintBorder(c, g, x, y, width, height);
        }
    }
}
