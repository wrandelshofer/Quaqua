/*
 * @(#)QuaquaTextFieldFocusHandler.java
 * Quaqua Look and Feel. Copyright © 2020 Werner Randelshofer, Switzerland. MIT License.
 */
package ch.randelshofer.quaqua;

import sun.awt.CausedFocusEvent;
import sun.awt.CausedFocusEvent.Cause;

import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JPasswordField;
import javax.swing.UIManager;
import javax.swing.text.JTextComponent;
import java.awt.KeyboardFocusManager;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import static ch.randelshofer.quaqua.QuaquaClientProperties.QUAQUA_TEXT_COMPONENT_AUTO_SELECT_CLIENT_PROPERTY;

/**
 * QuaquaTextFieldFocusHandler. Selects all text of a JTextComponent, if
 * the user used a keyboard focus traversal key to transfer the focus on the
 * JTextComponent.
 *
 * @author Werner Randelshofer
 * @version $Id$
 */
public class QuaquaTextFieldFocusHandler implements FocusListener {

    private static QuaquaTextFieldFocusHandler instance;

    public static QuaquaTextFieldFocusHandler getInstance() {
        if (instance == null) {
            instance = new QuaquaTextFieldFocusHandler();
        }
        return instance;
    }

    /**
     * Allow instance creation by UIManager.
     */
    public QuaquaTextFieldFocusHandler() {
    }

    public void focusGained(FocusEvent event) {
        QuaquaUtilities.repaintBorder((JComponent) event.getComponent());

        final JTextComponent tc = (JTextComponent) event.getSource();
        if (tc.isEditable() && tc.isEnabled()) {

            String uiProperty;
            if (tc instanceof JPasswordField) {
                uiProperty = "PasswordField.autoSelect";
            } else if (tc instanceof JFormattedTextField) {
                uiProperty = "FormattedTextField.autoSelect";
            } else {
                uiProperty = "TextField.autoSelect";
            }

            if (tc.getClientProperty(QUAQUA_TEXT_COMPONENT_AUTO_SELECT_CLIENT_PROPERTY) == Boolean.TRUE ||
                    tc.getClientProperty(QUAQUA_TEXT_COMPONENT_AUTO_SELECT_CLIENT_PROPERTY) == null &&
                            UIManager.getBoolean(uiProperty)) {
                if (event instanceof CausedFocusEvent) {
                    CausedFocusEvent cfEvent = (CausedFocusEvent) event;
                    if (cfEvent.getCause() == Cause.TRAVERSAL_FORWARD ||
                            cfEvent.getCause() == Cause.TRAVERSAL_BACKWARD) {
                        tc.selectAll();
                    }
                }
            }
        }
        if (KeyboardFocusManager.getCurrentKeyboardFocusManager() instanceof QuaquaKeyboardFocusManager) {
            QuaquaKeyboardFocusManager kfm = (QuaquaKeyboardFocusManager) KeyboardFocusManager.getCurrentKeyboardFocusManager();
            kfm.setLastKeyboardTraversingComponent(null);
        }
    }

    public void focusLost(FocusEvent event) {
        QuaquaUtilities.repaintBorder((JComponent) event.getComponent());
    }
}

