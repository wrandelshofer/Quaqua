/*
 * @(#)QuaquaFocusHandler.java
 * Quaqua Look and Feel. Copyright 2020 © Werner Randelshofer, Switzerland. MIT License.
 */

package ch.randelshofer.quaqua;

import java.awt.event.*;
import javax.swing.*;
/**
 * QuaquaFocusHandler.
 *
 * @author  Werner Randelshofer
 * @version $Id$
 */
public class QuaquaFocusHandler implements FocusListener {
    private static QuaquaFocusHandler instance;

    public static QuaquaFocusHandler getInstance() {
        if (instance == null) {
            instance = new QuaquaFocusHandler();
        }
        return instance;
    }


    /**
     * Prevent instance creation.
     */
    private QuaquaFocusHandler() {
    }

    public void focusGained(FocusEvent event) {
            QuaquaUtilities.repaintBorder((JComponent) event.getComponent());
    }

    public void focusLost(FocusEvent event) {
            QuaquaUtilities.repaintBorder((JComponent) event.getComponent());
    }
}

