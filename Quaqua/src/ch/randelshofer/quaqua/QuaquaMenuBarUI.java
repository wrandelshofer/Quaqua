/*
 * @(#)QuaquaMenuBarUI.java
 * Quaqua Look and Feel. Copyright © 2020 Werner Randelshofer, Switzerland. MIT License.
 */

package ch.randelshofer.quaqua;

import ch.randelshofer.quaqua.border.BackgroundBorder;
import ch.randelshofer.quaqua.color.PaintableColor;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.plaf.*;
import javax.swing.plaf.basic.*;
/**
 * QuaquaMenuBarUI.
 *
 * @author  Werner Randelshofer
 * @version $Id$
 */
public class QuaquaMenuBarUI extends BasicMenuBarUI {

    /** Creates a new instance. */
    public QuaquaMenuBarUI() {
    }

    public static ComponentUI createUI(JComponent x) {
        return new QuaquaMenuBarUI();
    }
    @Override
    public void paint(Graphics gr, JComponent c) {
        Graphics2D g = (Graphics2D) gr;
        int w = c.getWidth();
        int h = c.getHeight();

        g.setPaint(PaintableColor.getPaint(c.getBackground(), c));
        g.fillRect(0, 0, w, h);

        if (c.getBorder() instanceof BackgroundBorder) {
            Border bb = ((BackgroundBorder) c.getBorder()).getBackgroundBorder();
            bb.paintBorder(c, gr, 0, 0, w, h);
        }

        Color shadow = UIManager.getColor("MenuBar.shadow");
        if (shadow != null) {
            g.setColor(shadow);
            g.fillRect(0, h - 1, w, 1);
        }
    }
}
