/*
 * @(#)QuaquaTigerSideBarSelectionBorder.java
 * Quaqua Look and Feel. Copyright © 2020 Werner Randelshofer, Switzerland. MIT License.
 */
package ch.randelshofer.quaqua.tiger;

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
 * QuaquaTigerSideBarSelectionBorder.
 *
 * @author Werner Randelshofer
 * @version $Id$
 */
public class QuaquaTigerSideBarSelectionBorder implements Border, UIResource {

    /**
     * Creates a new instance.
     */
    public QuaquaTigerSideBarSelectionBorder() {
    }

    public void paintBorder(Component c, Graphics gr, int x, int y, int width, int height) {
        Graphics2D g = (Graphics2D) gr;
        if (QuaquaUtilities.isFocused(c)) {
            // top line
            g.setColor(new Color(0xadbbd0));
            g.fillRect(x, y, width, 1);
            g.setPaint(new LinearGradientPaint(
                    x, y + 1, new Color(0xacbacf),
                    x, y + height - 2, new Color(0x98aac4)
            ));
            g.fillRect(x, y + 1, width, height - 1);
            // bottom line
            g.setColor(new Color(0x8c98b0));
            g.fillRect(x, y + height - 1, width, 1);
        } else {
            if (QuaquaUtilities.isOnActiveWindow(c, true)) {
                // top line
                g.setColor(new Color(0xadbbd0));
                g.fillRect(x, y, width, 1);
                g.setPaint(new LinearGradientPaint(
                        x, y + 1, new Color(0xacbacf),
                        x, y + height - 2, new Color(0x98aac4)
                ));
                g.fillRect(x, y + 1, width, height - 1);
                // bottom line
                g.setColor(new Color(0x8c98b0));
                g.fillRect(x, y + height - 1, width, 1);
            } else {
                // top line
                g.setColor(new Color(0xadbbd0));
                g.fillRect(x, y, width, 1);
                g.setPaint(new LinearGradientPaint(
                        x, y + 1, new Color(0xacbacf),
                        x, y + height - 2, new Color(0x98aac4)
                ));
                g.fillRect(x, y + 1, width, height - 1);
                // bottom line
                g.setColor(new Color(0x8c98b0));
                g.fillRect(x, y + height - 1, width, 1);
            }
        }
    }

    public Insets getBorderInsets(Component c) {
        return new Insets(0, 0, 0, 0);
    }

    public boolean isBorderOpaque() {
        return true;
    }
}
