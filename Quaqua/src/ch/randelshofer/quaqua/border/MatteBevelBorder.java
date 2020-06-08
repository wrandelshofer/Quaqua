/*
 * @(#)MatteBevelBorder.java
 * Quaqua Look and Feel. Copyright © 2020 Werner Randelshofer, Switzerland. MIT License.
 */

package ch.randelshofer.quaqua.border;

import java.awt.*;
import javax.swing.border.*;
/**
 * MatteBevelBorder.
 *
 * @author  Werner Randelshofer
 * @version $Id$
 */
public class MatteBevelBorder implements Border {
    private Insets borderInsets;
    private Border bevelBorder;

    /** Creates a new instance. */
    public MatteBevelBorder(Insets borderInsets, Border bevelBorder) {
        this.borderInsets = borderInsets;
        this.bevelBorder = bevelBorder;
    }


    public Insets getBorderInsets(Component c) {
        return (Insets) borderInsets.clone();
    }

    public boolean isBorderOpaque() {
        return false;
    }

    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        bevelBorder.paintBorder(c, g, x, y, width, height);
    }

}
