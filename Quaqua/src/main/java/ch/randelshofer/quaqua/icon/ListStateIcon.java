/*
 * @(#)ListStateIcon.java
 * Quaqua Look and Feel. Copyright © 2020 Werner Randelshofer, Switzerland. MIT License.
 */
package ch.randelshofer.quaqua.icon;

import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.plaf.UIResource;
import java.awt.Component;
import java.awt.Graphics;

/**
 * {@code ListStateIcon} draws two different icons depending on the
 * selection state of a list item.
 * The component must set the client property "Quaqua.selected" to true.
 *
 * @author Werner Randelshofer
 * @version 1.0 2011-08-04 Created.
 */
public class ListStateIcon implements Icon, UIResource {
    protected static final String QUAQUA_SELECTED_CLIENT_PROPERTY = "Quaqua.selected";
    protected static final String QUAQUA_FOCUSED_CLIENT_PROPERTY = "Quaqua.focused";
    private final Icon icon;
    private final Icon selectedIcon;
    private final Icon selectedAndFocusedIcon;

    public ListStateIcon(Icon icon, Icon selectedIcon) {
this(icon,selectedIcon,selectedIcon);
    }

    public ListStateIcon(Icon icon, Icon selectedIcon, Icon selectedAndFocusedIcon) {
        this.icon = icon;
        this.selectedIcon = selectedIcon;
        this.selectedAndFocusedIcon = selectedAndFocusedIcon;
    }

    public void paintIcon(Component c, Graphics g, int x, int y) {
        boolean isSelected = false;
        boolean isFocusOwner=false;
        if (c instanceof JComponent) {
            isSelected = (Boolean) ((JComponent) c).getClientProperty(QUAQUA_SELECTED_CLIENT_PROPERTY);
            isFocusOwner = (Boolean) ((JComponent) c).getClientProperty(QUAQUA_FOCUSED_CLIENT_PROPERTY);
        }
        if (isSelected) {
            if (isFocusOwner) {
                selectedAndFocusedIcon.paintIcon(c, g, x, y);
            } else {
                selectedIcon.paintIcon(c, g, x, y);
            }
        } else {
            icon.paintIcon(c, g, x, y);
        }
    }

    public int getIconWidth() {
        return icon.getIconWidth();
    }

    public int getIconHeight() {
        return icon.getIconHeight();
    }

}
