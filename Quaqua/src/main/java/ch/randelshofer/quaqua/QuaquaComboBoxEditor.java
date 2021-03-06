/*
 * @(#)QuaquaComboBoxEditor.java
 * Quaqua Look and Feel. Copyright © 2020 Werner Randelshofer, Switzerland. MIT License.
 */

package ch.randelshofer.quaqua;

import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.InputMapUIResource;
import javax.swing.plaf.basic.BasicComboBoxEditor;

/**
 * The default editor for Quaqua editable combo boxes.
 *
 * @author Werner Randelshofer
 * @version $Id$
 */
public class QuaquaComboBoxEditor extends BasicComboBoxEditor {
    public QuaquaComboBoxEditor() {
        super();
        //editor.removeFocusListener(this);
        editor = new JTextField("", 0) {
            // workaround for 4530952
            public void setText(String s) {
                if (getText().equals(s)) {
                    return;
                }
                super.setText(s);
            }
        };

        installKeyboardActions();
    }

    protected void installKeyboardActions() {
        InputMap km = getInputMap();
        if (km != null) {
            SwingUtilities.replaceUIInputMap(editor, JComponent.WHEN_FOCUSED, km);
        }
    }

    /**
     * Get the InputMap to use for the UI.
     */
    InputMap getInputMap() {
        InputMap map = new InputMapUIResource();
        InputMap shared = (InputMap) UIManager.get("ComboBox.editorInputMap");
        if (shared != null) {
            map.setParent(shared);
        }
        return map;
    }

    /**
     * A subclass of BasicComboBoxEditor that implements UIResource.
     * BasicComboBoxEditor doesn't implement UIResource
     * directly so that applications can safely override the
     * cellRenderer property with BasicListCellRenderer subclasses.
     * <p>
     * <strong>Warning:</strong>
     * Serialized objects of this class will not be compatible with
     * future Swing releases. The current serialization support is
     * appropriate for short term storage or RMI between applications running
     * the same version of Swing.  As of 1.4, support for long term storage
     * of all JavaBeans<sup><font size="-2">TM</font></sup>
     * has been added to the <code>java.beans</code> package.
     * Please see {@link java.beans.XMLEncoder}.
     */
    public static class UIResource extends QuaquaComboBoxEditor
            implements javax.swing.plaf.UIResource {
    }
}

