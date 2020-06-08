/*
 * @(#)QuaquaComboBoxCellRenderer.java
 * Quaqua Look and Feel. Copyright 2020 © Werner Randelshofer, Switzerland. MIT License.
 */

package ch.randelshofer.quaqua;

import java.awt.*;
import javax.swing.*;

/**
 * QuaquaComboBoxCellRenderer.
 *
 * @author  Werner Randelshofer
 * @version $Id$
 */
public class QuaquaComboBoxCellRenderer implements ListCellRenderer {
    private ListCellRenderer valueRenderer;
    private JPanel panel;

    public QuaquaComboBoxCellRenderer(ListCellRenderer valueRenderer, boolean isInTable, boolean isEditable) {
        this.valueRenderer = valueRenderer;
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        if (isInTable) {
            panel.setBorder(null);
        } else {
            if (isEditable) {
                panel.setBorder(BorderFactory.createEmptyBorder(0, 3, 0, 3));
            } else {
                panel.setBorder(BorderFactory.createEmptyBorder(1, 13, 1, 7));
            }
        }
        panel.setOpaque(true);
    }

    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        Component valueComponent = valueRenderer.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        panel.removeAll();

        panel.add(valueComponent);
        panel.setBackground((isSelected) ?
        valueComponent.getBackground() :
           UIManager.getColor("PopupMenu.background")
        );

        return panel;

    }
}
