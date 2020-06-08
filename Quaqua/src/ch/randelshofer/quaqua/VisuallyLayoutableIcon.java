/*
 * @(#)VisuallyLayoutableIcon.java
 * Quaqua Look and Feel. Copyright © 2020 Werner Randelshofer, Switzerland. MIT License.
 */

package ch.randelshofer.quaqua;

import java.awt.*;
import javax.swing.*;
/**
 * VisuallyLayoutableIcon takes an icon and a layout rectangle. The layout rectangle is
 * relative to the upper left corner of the icon.
 *
 * @author  Werner Randelshofer
 * @version $Id$
 */
public class VisuallyLayoutableIcon implements Icon {
    private Icon icon;
    private Rectangle layoutRect;

    /**
     * Creates a new instance.
     */
    public VisuallyLayoutableIcon(Icon icon, int x, int y, int width, int height) {
        this(icon, new Rectangle(x, y, width, height));
    }

    /**
     * Creates a new instance.
     */
    public VisuallyLayoutableIcon(Icon icon, Rectangle layoutRect) {
        this.icon = icon;
        this.layoutRect = layoutRect;
    }

    public int getIconHeight() {
        return layoutRect.height;
    }

    public int getIconWidth() {
        return layoutRect.width;
    }

    public void paintIcon(Component c, Graphics g, int x, int y) {
        icon.paintIcon(c, g, x - layoutRect.x, y - layoutRect.y);
    }

}
