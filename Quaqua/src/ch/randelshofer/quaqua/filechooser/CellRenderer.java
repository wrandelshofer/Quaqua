/*
 * @(#)CellRenderer.java
 * Quaqua Look and Feel. Copyright 2020 © Werner Randelshofer, Switzerland. MIT License.
 */
package ch.randelshofer.quaqua.filechooser;

import javax.swing.*;
import java.awt.*;

/**
 * A cell renderer that does not care what kind of container it is used in.
 */

public interface CellRenderer {
    Component getCellRendererComponent(JComponent container,
                                       Object value,
                                       boolean isSelected,
                                       boolean cellHasFocus);
}
