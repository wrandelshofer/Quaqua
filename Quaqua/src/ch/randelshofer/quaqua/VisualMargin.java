/*
 * @(#)VisualMargin.java
 * Quaqua Look and Feel. Copyright © 2020 Werner Randelshofer, Switzerland. MIT License.
 */
package ch.randelshofer.quaqua;

import javax.swing.border.Border;
import java.awt.Component;
import java.awt.Insets;

/**
 * {@code VisualMargin}.
 *
 * @author Werner Randelshofer
 * @version 1.0 2011-07-28 Created.
 */
public interface VisualMargin extends Border {
    public Insets getVisualMargin(Component c);
}
