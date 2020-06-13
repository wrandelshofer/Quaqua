/*
 * @(#)QuaquaTextComponentPopupHandler.java
 * Quaqua Look and Feel. Copyright © 2020 Werner Randelshofer, Switzerland. MIT License.
 */

package ch.randelshofer.quaqua;

import ch.randelshofer.quaqua.util.Methods;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.UIManager;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.JTextComponent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static ch.randelshofer.quaqua.QuaquaClientProperties.QUAQUA_TEXT_COMPONENT_SHOW_POPUP_CLIENT_PROPERTY;

/**
 * TextComponentHandler displays a popup menu on a JTextComponent with the
 * cut/copy and paste actions.
 * The Quaqua text component UI's register a shared instance of
 * QuaquaTextComponentPopupHandler as a mouse listener on all JTextComponent's.
 *
 * @author Werner Randelshofer.
 * @version $Id$
 */
public class QuaquaTextComponentPopupHandler extends MouseAdapter {
    private JPopupMenu popupMenu;
    private AbstractAction cutAction;
    private AbstractAction copyAction;
    private AbstractAction pasteAction;

    /**
     * Creates a new instance.
     */
    public QuaquaTextComponentPopupHandler() {
        popupMenu = new JPopupMenu();
        popupMenu.add(cutAction = new DefaultEditorKit.CutAction());
        popupMenu.add(copyAction = new DefaultEditorKit.CopyAction());
        popupMenu.add(pasteAction = new DefaultEditorKit.PasteAction());

        cutAction.putValue(Action.NAME, UIManager.getString("TextComponent.cut"));
        copyAction.putValue(Action.NAME, UIManager.getString("TextComponent.copy"));
        pasteAction.putValue(Action.NAME, UIManager.getString("TextComponent.paste"));
    }

    public void mouseReleased(MouseEvent e) {
        if (e.isPopupTrigger()) {
            showPopup(e);
        }
    }

    public void mousePressed(MouseEvent e) {
        if (e.isPopupTrigger()) {
            showPopup(e);
        }
    }

    protected void showPopup(MouseEvent e) {
        JTextComponent src = (JTextComponent) e.getSource();

        boolean isFocusable = Methods.invokeGetter(src, "isFocusable", true);

        if (src.getClientProperty(QUAQUA_TEXT_COMPONENT_SHOW_POPUP_CLIENT_PROPERTY) != Boolean.FALSE &&
                src.isEnabled() &&
                isFocusable &&
                Methods.invokeGetter(src, "getComponentPopupMenu", null) == null) {
            cutAction.setEnabled(!(src instanceof JPasswordField) &&
                    src.getSelectionEnd() > src.getSelectionStart() &&
                    src.isEditable()
            );
            copyAction.setEnabled(!(src instanceof JPasswordField) &&
                    src.getSelectionEnd() > src.getSelectionStart()
            );
            pasteAction.setEnabled(src.isEditable()
            );
            src.requestFocus();
            popupMenu.show(src, e.getX(), e.getY());
            e.consume();
        }
    }
}
