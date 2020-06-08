/*
 * @(#)QuaquaPopupMenuBorder.java
 * Quaqua Look and Feel. Copyright 2020 © Werner Randelshofer, Switzerland. MIT License.
 */

package ch.randelshofer.quaqua;

import java.awt.*;
import javax.swing.border.*;
/**
 * A replacement for the AquaMenuBorder.
 * <p>
 * This class provides the following workaround for a bug in Apple's
 * implementation of the Aqua Look and Feel in Java 1.4.1:
 * <ul>
 * <li>Draws a border at the top and the bottom of JPopupMenu's.
 * </ul>
 *
 * @author Werner Randelshofer
 * @version $Id$
 */
public class QuaquaPopupMenuBorder implements Border {
    public void paintBorder(Component component, Graphics g, int x,
    int y, int width, int height) {
        /* empty */
    }

    public Insets getBorderInsets(Component component) {
return new Insets(4, 0, 4, 0);
    }

    public boolean isBorderOpaque() {
        return false;
    }
}