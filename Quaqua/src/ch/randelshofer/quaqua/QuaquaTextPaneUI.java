/*
 * @(#)QuaquaTextPaneUI.java  1.3  2007-01-16
 *
 * Copyright (c) 2004-2007 Werner Randelshofer
 * Staldenmattweg 2, Immensee, CH-6405, Switzerland.
 * All rights reserved.
 *
 * The copyright of this software is owned by Werner Randelshofer. 
 * You may not use, copy or modify this software, except in  
 * accordance with the license agreement you entered into with  
 * Werner Randelshofer. For details see accompanying license terms. 
 */

package ch.randelshofer.quaqua;

import ch.randelshofer.quaqua.util.*;
import ch.randelshofer.quaqua.util.Debug;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.plaf.*;
import javax.swing.plaf.basic.*;
import javax.swing.text.*;
import javax.swing.border.*;

/**
 * QuaquaTextPaneUI.
 *
 * @author  Werner Randelshofer
 * @version 1.3 2007-01-17 Factored focus listener out into QuaquaViewportView.
 * <br>1.2 2006-04-24 Added support for .popupHandler UIManager property.
 * <br>1.1 2005-07-17 Adapted to changes in interface VisuallyLayoutable.
 * <br>1.0.1 2005-06-30 Fixed NPE in method getVisualBounds.
 * <br>1.0 July 4, 2004  Created.
 */
public class QuaquaTextPaneUI extends BasicTextPaneUI implements VisuallyLayoutable {
    boolean oldDragState = false;
    private MouseListener popupListener;
    
    public static ComponentUI createUI(JComponent jcomponent) {
        return new QuaquaTextPaneUI();
    }
    
    protected void installListeners() {
        popupListener = createPopupListener();
        if (popupListener != null) {
            getComponent().addMouseListener(popupListener);
        }
    }
    
    protected void uninstallListeners() {
        if (popupListener != null) {
            getComponent().removeMouseListener(popupListener);
            popupListener = null;
        }
    }
    protected MouseListener createPopupListener() {
        return (MouseListener) UIManager.get(getPropertyPrefix()+".popupHandler");
    }
    
    protected void installDefaults() {
        if (!QuaquaUtilities.isHeadless()) {
            oldDragState = Methods.invokeGetter(getComponent(), "getDragEnabled", true);
            Methods.invokeIfExists(getComponent(),"setDragEnabled", true);
        }
        super.installDefaults();
    }
    
    protected void uninstallDefaults() {
        if (!QuaquaUtilities.isHeadless()) {
            Methods.invokeIfExists(getComponent(),"setDragEnabled", oldDragState);
            }
        super.uninstallDefaults();
    }
    
    protected void paintSafely(Graphics g) {
        Object object = QuaquaUtilities.beginGraphics((Graphics2D) g);
        super.paintSafely(g);
        QuaquaUtilities.endGraphics((Graphics2D) g, object);
        Debug.paint(g, getComponent(), this);
    }
    
    protected Caret createCaret() {
        Window window = SwingUtilities.getWindowAncestor(getComponent());
        QuaquaCaret caret = new QuaquaCaret(window, getComponent());
        return caret;
    }
    
    protected Highlighter createHighlighter() {
        return new QuaquaHighlighter();
    }
    
    public int getBaseline(JComponent c, int width, int height) {
        JTextComponent tc = (JTextComponent) c;
        Insets insets = tc.getInsets();
        FontMetrics fm = tc.getFontMetrics(tc.getFont());
        return insets.top + fm.getAscent();
    }
    
    public Rectangle getVisualBounds(JComponent c, int type, int width, int height) {
        Rectangle bounds = new Rectangle(0,0,width,height);
        if (type == VisuallyLayoutable.CLIP_BOUNDS) {
            return bounds;
        }
        
        JTextComponent b = (JTextComponent) c;
        if (type == VisuallyLayoutable.COMPONENT_BOUNDS
        && b.getBorder() != null) {
            Border border = b.getBorder();
            if (border instanceof UIResource) {
                //InsetsUtil.subtractInto(getVisualMargin(b), bounds);
                // FIXME - Should derive this value from border
                // FIXME - If we had layout managers that supported baseline alignment,
                //              we wouldn't have to subtract one here
                bounds.height -= 1;
            }
        } else {
            bounds = getVisibleEditorRect();
            FontMetrics fm = c.getFontMetrics(c.getFont());
            
            int baseline = getBaseline(c, width, height);
            Rectangle textBounds = Fonts.getPerceivedBounds(b.getText(), b.getFont(), c);
            if (bounds == null) {
                bounds = textBounds;
                bounds.y += baseline;
            } else {
                bounds.y = baseline + textBounds.y;
                bounds.height = textBounds.height;
            }
            bounds.x += 1;
            bounds.width -= 2;
        }
        return bounds;
    }
}
