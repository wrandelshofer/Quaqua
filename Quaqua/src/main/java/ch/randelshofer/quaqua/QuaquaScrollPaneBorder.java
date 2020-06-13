/*
 * @(#)QuaquaScrollPaneBorder.java
 * Quaqua Look and Feel. Copyright © 2020 Werner Randelshofer, Switzerland. MIT License.
 */
package ch.randelshofer.quaqua;

import ch.randelshofer.quaqua.border.VisualMarginBorder;
import ch.randelshofer.quaqua.util.Debug;
import ch.randelshofer.quaqua.util.InsetsUtil;

import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.border.Border;
import javax.swing.text.JTextComponent;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;

import static ch.randelshofer.quaqua.QuaquaClientProperties.QUAQUA_DRAW_FOCUS_BORDER_CLIENT_PROPERTY;

/**
 * QuaquaScrollPaneBorder.
 *
 * @author Werner Randelshofer
 * @version $Id$
 */
public class QuaquaScrollPaneBorder extends VisualMarginBorder {

    /**
     * Location of the border images.
     */
    private String scrollPaneImagesLocation;
    private String textFieldImagesLocation;
    /**
     * Array with image bevel borders.
     * This array is created lazily.
     **/
    private Border[] scrollPaneBorders;
    private Border[] textFieldBorders;

    /**
     * Creates a new instance.
     */
    public QuaquaScrollPaneBorder(String scrollPaneImagesLocation, String textFieldImagesLocation) {
        super(3, 3, 3, 3);
        this.scrollPaneImagesLocation = scrollPaneImagesLocation;
        this.textFieldImagesLocation = textFieldImagesLocation;
    }

    protected boolean isTextBorder(Component component) {
        return getViewportView(component) instanceof JTextComponent;
    }

    protected Component getViewportView(Component component) {
        if (component instanceof JScrollPane) {
            JViewport viewport = ((JScrollPane) component).getViewport();
            if (viewport != null) {
                return viewport.getView();
            }
        }
        return null;
    }

    public Insets getBorderInsets(Component c, Insets insets) {
        insets = getVisualMargin(c, insets);
        Insets inner = (isTextBorder(c)) ? new Insets(5, 4, 4, 4) : new Insets(4, 4, 4, 4);
        InsetsUtil.addTo(inner, insets);
        return insets;
    }

    public boolean isBorderOpaque() {
        return false;
    }

    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Insets margin = getVisualMargin(c, new Insets(0, 0, 0, 0));
        Border border = getBorder(c);

        border.paintBorder(c, g,
                x + margin.left, y + margin.top,
                width - margin.left - margin.right,
                height - margin.top - margin.bottom);

        if (c instanceof JTextComponent) {
            Debug.paint(g, (JTextComponent) c, ((JTextComponent) c).getUI());
        }
    }

    private Border getBorder(Component c) {
        Border[] borders;
        if (isTextBorder(c)) {
            if (textFieldBorders == null) {
                // don't cache border
                textFieldBorders = (Border[]) QuaquaBorderFactory.create(textFieldImagesLocation, new Insets(6, 6, 6, 6), 3, true, true, false);
            }
            borders = textFieldBorders;
        } else {
            if (scrollPaneBorders == null) {
                // don't cache border
                scrollPaneBorders = (Border[]) QuaquaBorderFactory.create(textFieldImagesLocation, new Insets(6, 6, 5, 6), 3, true, true, false);
            }
            borders = scrollPaneBorders;
        }

        Component viewportView = getViewportView(c);
        if (viewportView == null) {
            viewportView = c;
        }

        boolean isEditable;
        if (viewportView instanceof JTextComponent) {
            isEditable = ((JTextComponent) viewportView).isEditable();
        } else {
            isEditable = true;
        }

        if (viewportView.isEnabled() &&
                (QuaquaUtilities.isFocused(viewportView) ||
                        (viewportView instanceof JComponent) &&
                                ((JComponent) viewportView).getClientProperty(QUAQUA_DRAW_FOCUS_BORDER_CLIENT_PROPERTY) == Boolean.TRUE)) {
            return borders[2];
        } else if (c.isEnabled() && viewportView.isEnabled() && isEditable) {
            return borders[0];
        } else {
            return borders[1];
        }
    }

    public static class UIResource extends QuaquaScrollPaneBorder implements javax.swing.plaf.UIResource {

        public UIResource(String scrollPaneImagesLocation, String textFieldImagesLocation) {
            super(scrollPaneImagesLocation, textFieldImagesLocation);
        }
    }
}
