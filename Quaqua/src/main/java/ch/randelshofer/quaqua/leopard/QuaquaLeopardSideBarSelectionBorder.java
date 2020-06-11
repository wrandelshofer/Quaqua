/*
 * @(#)QuaquaLeopardSideBarSelectionBorder.java
 * Quaqua Look and Feel. Copyright © 2020 Werner Randelshofer, Switzerland. MIT License.
 */

package ch.randelshofer.quaqua.leopard;

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
 * QuaquaLeopardSideBarSelectionBorder.
 *
 * @author Werner Randelshofer
 * @version $Id$
 */
public class QuaquaLeopardSideBarSelectionBorder implements Border, UIResource {

    /**
     * Creates a new instance.
     */
    public QuaquaLeopardSideBarSelectionBorder() {
    }

    public void paintBorder(Component c, Graphics gr, int x, int y, int width, int height) {
        Graphics2D g = (Graphics2D) gr;
        if (QuaquaUtilities.isFocused(c)) {
            // top line: 0x4580c8
            g.setColor(new Color(0x4580c8));
            g.fillRect(x, y, width, 1);
            g.setPaint(new LinearGradientPaint(
                    x, y + 1, new Color(0x5c93d5),
                    x, y + height - 1, new Color(0x1a58ad)

            ));
        } else {
            if (QuaquaUtilities.isOnActiveWindow(c, true)) {
                // top line: 0x91a0c0
                g.setColor(new Color(0x91a0c0));
                g.fillRect(x, y, width, 1);
                g.setPaint(new LinearGradientPaint(
                        x, y + 1, new Color(0xa9b1d0),
                        x, y + height - 1, new Color(0x6e81a9)

                ));

            } else {
                // top line: 0x979797
                g.setColor(new Color(0x979797));
                g.fillRect(x, y, width, 1);
                g.setPaint(new LinearGradientPaint(
                        x, y + 1, new Color(0xb4b4b4),
                        x, y + height - 1, new Color(0x8a8a8a)
                ));
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
