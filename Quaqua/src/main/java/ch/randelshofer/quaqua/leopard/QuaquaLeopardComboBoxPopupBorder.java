/*
 * @(#)QuaquaLeopardComboBoxPopupBorder.java
 * Quaqua Look and Feel. Copyright © 2020 Werner Randelshofer, Switzerland. MIT License.
 */
package ch.randelshofer.quaqua.leopard;

import ch.randelshofer.quaqua.QuaquaUtilities;

import javax.swing.border.Border;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Component;
import java.awt.Composite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;

/**
 * A replacement for the AquaComboBoxPopupBorder.
 *
 * @author Werner Randelshofer
 * @version $Id$
 */
public class QuaquaLeopardComboBoxPopupBorder implements Border {

    protected static Insets popupBorderInsets;
    protected static Insets itemBorderInsets;

    public void paintBorder(Component component, Graphics gr, int x,
                            int y, int width, int height) {
        Graphics2D g = (Graphics2D) gr;
        Object oldHints = QuaquaUtilities.beginGraphics(g);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        // Punch out a hole and then draw a rounded rectangle over it
        Composite composite = g.getComposite();
        g.setComposite(AlphaComposite.Src);
        g.setColor(new Color(0xffffff, true));
        g.fillRect(x, y, width, height);
        g.setComposite(composite);
        g.setColor(Color.WHITE);
        g.fill(new RoundRectangle2D.Float(x, y, width, height, 10f, 10f));
        QuaquaUtilities.endGraphics(g, oldHints);
    }

    public Insets getBorderInsets(Component component) {
        return new Insets(4, 0, 4, 0);
    }

    public boolean isBorderOpaque() {
        return false;
    }
}
