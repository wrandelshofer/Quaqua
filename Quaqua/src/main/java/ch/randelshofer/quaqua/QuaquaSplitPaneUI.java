/*
 * @(#)QuaquaSplitPaneUI.java
 * Quaqua Look and Feel. Copyright © 2020 Werner Randelshofer, Switzerland. MIT License.
 */
package ch.randelshofer.quaqua;

import ch.randelshofer.quaqua.color.PaintableColor;
import ch.randelshofer.quaqua.util.Debug;

import javax.swing.JComponent;
import javax.swing.JSplitPane;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicSplitPaneDivider;
import javax.swing.plaf.basic.BasicSplitPaneUI;
import java.awt.Canvas;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * QuaquaSplitPaneUI.
 *
 * @author Werner Randelshofer
 * @version $Id$
 */
public class QuaquaSplitPaneUI extends BasicSplitPaneUI {

    /**
     * Creates a new instance.
     */
    public QuaquaSplitPaneUI() {
    }

    /**
     * Creates a new BasicSplitPaneUI instance
     */
    public static ComponentUI createUI(JComponent x) {
        return new QuaquaSplitPaneUI();
    }

    /**
     * Installs the UI defaults.
     */
    protected void installDefaults() {
        super.installDefaults();
        QuaquaUtilities.installProperty(splitPane, "opaque", UIManager.get("SplitPane.opaque"));
        //splitPane.setOpaque(QuaquaManager.getBoolean("SplitPane.opaque"));
        /*
        splitPane.setContinuousLayout(true);
        setContinuousLayout(splitPane.isContinuousLayout());
         */
        splitPane.setFocusable(UIManager.getBoolean("SplitPane.focusable"));
    }

    /**
     * Creates the default divider.
     */
    public BasicSplitPaneDivider createDefaultDivider() {
        return new QuaquaSplitPaneDivider(this);
    }

    public void paint(Graphics gr, JComponent c) {
        Graphics2D g = (Graphics2D) gr;
        Object oldHints = QuaquaUtilities.beginGraphics(g);
        if (c.isOpaque()) {
            g.setPaint(PaintableColor.getPaint(c.getBackground(), c));
            g.fillRect(0, 0, c.getWidth(), c.getHeight());
        }
        super.paint(gr, c);
        Debug.paint(gr, c, this);
        QuaquaUtilities.endGraphics((Graphics2D) g, oldHints);
    }

    /**
     * Messaged after the JSplitPane the receiver is providing the look
     * and feel for paints its children.
     */
    public void finishedPaintingChildren(JSplitPane jc, Graphics g) {
        if (jc == splitPane && getLastDragLocation() != -1 &&
                !isContinuousLayout() && !draggingHW) {
            Dimension size = splitPane.getSize();

            g.setColor(UIManager.getColor("SplitPaneDivider.draggingColor"));
            if (getOrientation() == JSplitPane.HORIZONTAL_SPLIT) {
                g.fillRect(getLastDragLocation(), 0, dividerSize,
                        size.height);
            } else {
                g.fillRect(0, getLastDragLocation(), size.width,
                        dividerSize);
            }
        }
    }

    /**
     * Returns the default non continuous layout divider, which is an
     * instanceof Canvas that fills the background in dark gray.
     */
    protected Component createDefaultNonContinuousLayoutDivider() {
        return new Canvas() {

            public void paint(Graphics g) {
                if (!isContinuousLayout() && getLastDragLocation() != -1) {
                    Dimension size = splitPane.getSize();
                    g.setColor(UIManager.getColor("SplitPaneDivider.draggingColor"));
                    if (getOrientation() == JSplitPane.HORIZONTAL_SPLIT) {
                        g.fillRect(0, 0, dividerSize, size.height);
                    } else {
                        g.fillRect(0, 0, size.width, dividerSize);
                    }
                }
            }
        };
    }
}
