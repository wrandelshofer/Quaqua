/*
 * @(#)DefaultCellRenderer.java
 * Quaqua Look and Feel. Copyright © 2020 Werner Randelshofer, Switzerland. MIT License.
 */

//package ch.randelshofer.gui;

package test;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableCellRenderer;
import java.awt.Component;

/**
 * DefaultCellRenderer.
 *
 * @author Werner Randelshofer
 * @version 1.0 April 11, 2004 Created.
 */
public class DefaultCellRenderer implements TableCellRenderer, ListCellRenderer {
    /**
     * The Swing component being rendered.
     */
    protected JComponent renderComponent;

    /**
     * The delegate class which handles all methods sent from the
     * <code>CellEditor</code>.
     */
    protected RenderDelegate delegate;
    protected static final Border noFocusBorder = new EmptyBorder(1, 1, 1, 1);

    /**
     * Constructs a <code>DefaultCellRenderer</code> that uses a text field.
     *
     * @param textField a <code>JTextField</code> object
     */
    public DefaultCellRenderer(final JTextField textField) {
        renderComponent = textField;
        delegate = new RenderDelegate() {
            public void setValue(Object value) {
                textField.setText((value != null) ? value.toString() : "");
            }
        };
    }

    /**
     * Constructs a <code>DefaultCellRenderer</code> object that uses a check box.
     *
     * @param checkBox a <code>JCheckBox</code> object
     */
    public DefaultCellRenderer(final JCheckBox checkBox) {
        renderComponent = checkBox;
        delegate = new RenderDelegate() {
            public void setValue(Object value) {
                boolean selected = false;
                if (value instanceof Boolean) {
                    selected = ((Boolean) value).booleanValue();
                } else if (value instanceof String) {
                    selected = value.equals("true");
                }
                checkBox.setSelected(selected);
            }
        };
    }

    /**
     * Constructs a <code>DefaultCellRenderer</code> object that uses a
     * combo box.
     *
     * @param comboBox a <code>JComboBox</code> object
     */
    public DefaultCellRenderer(final JComboBox comboBox) {
        renderComponent = comboBox;
        comboBox.putClientProperty("JComboBox.isTableCellEditor", Boolean.TRUE);
        delegate = new RenderDelegate() {
            public void setValue(Object value) {
                comboBox.setSelectedItem(value);
            }
        };
    }

    public Component getTableCellRendererComponent(JTable parent, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        delegate.setValue(value);
        if (hasFocus) {
            renderComponent.setBackground(UIManager.getColor("Table.focusCellBackground"));
            renderComponent.setForeground(UIManager.getColor("Table.focusCellForeground"));
            renderComponent.setBorder(UIManager.getBorder("Table.focusCellHighlightBorder"));
        } else if (isSelected) {
            renderComponent.setBackground(parent.getSelectionBackground());
            renderComponent.setForeground(parent.getSelectionForeground());
            renderComponent.setBorder(noFocusBorder);
        } else {
            renderComponent.setBackground(parent.getBackground());
            renderComponent.setForeground(parent.getForeground());
            renderComponent.setBorder(noFocusBorder);
        }
        renderComponent.setFont(parent.getFont());

        return renderComponent;
    }

    public Component getListCellRendererComponent(JList parent, Object value, int index, boolean isSelected, boolean hasFocus) {
        delegate.setValue(value);

        if (isSelected) {
            renderComponent.setBackground(parent.getSelectionBackground());
            renderComponent.setForeground(parent.getSelectionForeground());
        } else {
            renderComponent.setBackground(parent.getBackground());
            renderComponent.setForeground(parent.getSelectionForeground());
        }
        renderComponent.setFont(parent.getFont());

        return renderComponent;
    }


    /**
     * The protected <code>RenderDelegate</code> class.
     */
    protected interface RenderDelegate {
        /**
         * Sets the value of this cell.
         *
         * @param value the new value of this cell
         */
        public void setValue(Object value);
    }
}
