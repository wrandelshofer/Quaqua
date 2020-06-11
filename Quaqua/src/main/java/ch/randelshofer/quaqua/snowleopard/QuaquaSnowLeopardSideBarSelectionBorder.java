/*
 * @(#)QuaquaSnowLeopardSideBarSelectionBorder.java
 * Quaqua Look and Feel. Copyright © 2020 Werner Randelshofer, Switzerland. MIT License.
 */
package ch.randelshofer.quaqua.snowleopard;

import ch.randelshofer.quaqua.QuaquaUtilities;
import ch.randelshofer.quaqua.ext.batik.ext.awt.LinearGradientPaint;

import javax.swing.border.Border;
import javax.swing.plaf.UIResource;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;

/**
 * QuaquaSnowLeopardSideBarSelectionBorder.
 *
 * @author Werner Randelshofer
 * @version $Id$
 */
public class QuaquaSnowLeopardSideBarSelectionBorder implements Border, UIResource {

    /**
     * Creates a new instance.
     */
    public QuaquaSnowLeopardSideBarSelectionBorder() {
    }

    public void paintBorder(Component c, Graphics gr, int x, int y, int width, int height) {
        Graphics2D g = (Graphics2D) gr;
        if (QuaquaUtilities.isFocused(c)) {
            // top line: 0x5896d0
            g.setColor(new Color(0x5896d0));
            g.fillRect(x, y, width, 1);
            g.setPaint(new LinearGradientPaint(
                    x, y + 1, new Color(0x6ea6d6),
                    x, y + height - 1, new Color(0x216cb7)
            ));
        } else {
            if (QuaquaUtilities.isOnActiveWindow(c, true)) {
                // top line: 0xa2b1cb
                g.setColor(new Color(0xa2b1cb));
                g.fillRect(x, y, width, 1);
                g.setPaint(new LinearGradientPaint(
                        x, y + 1, new Color(0xb1bfd8),
                        x, y + height - 1, new Color(0x8296b8)
                ));

            } else {
                // top line: 0xa8a8a8
                g.setColor(new Color(0xa8a8a8));
                g.fillRect(x, y, width, 1);
                g.setPaint(new LinearGradientPaint(
                        x, y + 1, new Color(0xc1c1c1),
                        x, y + height - 1, new Color(0x9c9c9c)));
            }
        }
        g.fillRect(x, y + 1, width, height - 1);
    }

    public Insets getBorderInsets(Component c) {
        return new Insets(0, 0, 0, 0);
    }

    public boolean isBorderOpaque() {
        return true;
    }
}
