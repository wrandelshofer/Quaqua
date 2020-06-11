/*
 * @(#)QuaquaScrollPaneLayout.java
 * Quaqua Look and Feel. Copyright © 2020 Werner Randelshofer, Switzerland. MIT License.
 */

package ch.randelshofer.quaqua;

import javax.swing.JRootPane;
import javax.swing.JViewport;
import javax.swing.ScrollPaneLayout;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Insets;

/**
 * The QuaquaScrollPaneLayout ensures that the vertical and the horizontal
 * scroll bar of a JScrollPane do not intersect with the grow-box of a
 * JFrame or a JDialog.
 * <p>
 * Note: This layout manager is only used, when we know that the grow-box
 * intrudes into frames and dialogs. Therefore we do not have to explicitly
 * check for this in this layout manager.
 *
 * @author Werner Randelshofer
 * @version $Id$
 */
public class QuaquaScrollPaneLayout extends ScrollPaneLayout {
    /**
     * Creates a new instance.
     */
    public QuaquaScrollPaneLayout() {
    }

    public void layoutContainer(Container parent) {
        super.layoutContainer(parent);

        // In case of a regular sized scroll pane, check if only one of the
        // scroll bars is visible
        // In case of a small or mini sized scroll pane, check if at least
        // one of the scrollbars is visible
        boolean vsbVisible = vsb != null && vsb.isVisible();
        boolean hsbVisible = hsb != null && hsb.isVisible();
        int fontSize = Math.min(parent.getFont().getSize(),
                (vsb != null && vsb.getFont() != null) ? vsb.getFont().getSize() : Integer.MAX_VALUE);
        if (fontSize >= 13 && vsbVisible != hsbVisible
                || fontSize < 13 && (vsbVisible || hsbVisible)) {

            // Check if the parent is on a frame or a dialog with an intruding grow-box
            JRootPane rootPane = SwingUtilities.getRootPane(parent);
            if (rootPane != null
                    && (rootPane.getParent() instanceof Frame && ((Frame) rootPane.getParent()).isResizable()
                    || rootPane.getParent() instanceof Dialog && ((Dialog) rootPane.getParent()).isResizable()
            )
            ) {
                // Check if the bounds of the parent intersects with the bounds
                // of the grow-box
                int x = 0;
                int y = 0;
                for (Component c = parent; c != rootPane; c = c.getParent()) {
                    // Don't avoid grow-box if we are on a JViewport
                    if (c instanceof JViewport) {
                        return;
                    }
                    x += c.getX();
                    y += c.getY();
                }

                Dimension growBox = UIManager.getDimension("ScrollPane.growBoxSize");
                if (growBox == null) {
                    growBox = new Dimension(0, 0);
                }

                if (x + parent.getWidth() > rootPane.getWidth() - growBox.width
                        && y + parent.getHeight() > rootPane.getHeight() - growBox.height) {
                    Insets insets = parent.getInsets();

                    if (vsbVisible) {
                        vsb.setSize(
                                vsb.getWidth(),
                                parent.getHeight() - insets.top - Math.max(insets.bottom, growBox.height)
                        );
                    }
                    if (hsbVisible) {
                        hsb.setSize(
                                parent.getWidth() - insets.left - Math.max(insets.right, growBox.width),
                                hsb.getHeight()
                        );
                    }
                }
            }
        }
    }


    /**
     * The UI resource version of <code>ScrollPaneLayout</code>.
     */
    public static class UIResource extends QuaquaScrollPaneLayout implements javax.swing.plaf.UIResource {
    }
}


