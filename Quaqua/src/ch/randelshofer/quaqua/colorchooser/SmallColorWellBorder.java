/*
 * @(#)SmallColorWellBorder.java
 * Quaqua Look and Feel. Copyright © 2020 Werner Randelshofer, Switzerland. MIT License.
 */

package ch.randelshofer.quaqua.colorchooser;

import java.awt.*;
import javax.swing.border.*;
/**
 * SmallColorWellBorder.
 *
 * @author  werni
 */
public class SmallColorWellBorder implements Border {
    private static Color inner = Color.white;
    private static Color outer = new Color(0x949494);
    /** Creates a new instance of QuaquaSquareButtonBorder */
    public SmallColorWellBorder() {
    }

    public Insets getBorderInsets(Component c) {
        return new Insets(1, 1, 1, 1);
    }

    public boolean isBorderOpaque() {
        return true;
    }

    public void paintBorder(Component c, Graphics gr, int x, int y, int width, int height) {
        gr.setColor(c.getBackground());
        gr.fillRect(x + 2, y + 2, width - 4, height - 4);
        gr.setColor(inner);
        gr.drawRect(x + 1, y + 1, width - 3, height - 3);
        gr.setColor(outer);
        gr.drawRect(x, y, width - 1, height - 1);
    }
}
