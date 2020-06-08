/*
 * @(#)LeopardFileRenderer.java
 * Quaqua Look and Feel. Copyright © 2020 Werner Randelshofer, Switzerland. MIT License.
 */
package ch.randelshofer.quaqua.leopard.filechooser;

import ch.randelshofer.quaqua.osx.OSXFile;
import javax.swing.*;

import ch.randelshofer.quaqua.*;
import ch.randelshofer.quaqua.ext.batik.ext.awt.LinearGradientPaint;
import ch.randelshofer.quaqua.filechooser.*;
import ch.randelshofer.quaqua.icon.EmptyIcon;

import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * The FileRenderer is used to render a file in the JBrowser of one of the
 * Quaqua FileChooserUI's.
 *
 * @author  Werner Randelshofer
 * @version $Id$
 */
public class LeopardFileRenderer extends JLabel implements ListCellRenderer, CellRenderer {

    private Color labelForeground, labelDisabledForeground;
    private Icon selectedExpandingIcon;
    private Icon selectedExpandedIcon;
    private Icon focusedSelectedExpandingIcon;
    private Icon focusedSelectedExpandedIcon;
    private Icon expandingIcon;
    private Icon expandedIcon;
    private Icon emptyIcon;
    private Icon aliasBadgeIcon;
    private JFileChooser fileChooser;
    private int textIconGap;
    private int textArrowIconGap;
    private Icon icon;
    private String text;
    private Icon arrowIcon;
    private Color labelColor, labelBrightColor;
    private boolean isSelected;
    private boolean isActive;
    private boolean isGrayed;
    private boolean isAlias;
    private boolean isListView;

    public LeopardFileRenderer(JFileChooser fileChooser,
            Icon expandingIcon, Icon expandedIcon,
            Icon selectedExpandingIcon, Icon selectedExpandedIcon,
            Icon focusedSelectedExpandingIcon, Icon focusedSelectedExpandedIcon) {
        this.fileChooser = fileChooser;
        this.expandingIcon = expandingIcon;
        this.expandedIcon = expandedIcon;
        this.selectedExpandingIcon = selectedExpandingIcon;
        this.selectedExpandedIcon = selectedExpandedIcon;
        this.focusedSelectedExpandingIcon = focusedSelectedExpandingIcon;
        this.focusedSelectedExpandedIcon = focusedSelectedExpandedIcon;
        this.textIconGap = UIManager.getInt("FileChooser.browserCellTextIconGap");
        this.textArrowIconGap = UIManager.getInt("FileChooser.browserCellTextArrowIconGap");

        emptyIcon = new EmptyIcon(expandedIcon.getIconWidth(), expandedIcon.getIconHeight());
        aliasBadgeIcon = UIManager.getIcon("FileView.aliasBadgeIcon");

        labelForeground = UIManager.getColor("Label.foreground");
        labelDisabledForeground = UIManager.getColor("Label.disabledForeground");

        setOpaque(true);
    }

    // Overridden for performance reasons.
    @Override
    public void validate() {
    }

    @Override
    public void revalidate() {
    }

    @Override
    public void repaint(long tm, int x, int y, int width, int height) {
    }

    @Override
    public void repaint(Rectangle r) {
    }

    @Override
    protected void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
    }

    @Override
    public void firePropertyChange(String propertyName, short oldValue, short newValue) {
    }

    @Override
    public void firePropertyChange(String propertyName, int oldValue, int newValue) {
    }

    @Override
    public void firePropertyChange(String propertyName, long oldValue, long newValue) {
    }

    @Override
    public void firePropertyChange(String propertyName, float oldValue, float newValue) {
    }

    @Override
    public void firePropertyChange(String propertyName, double oldValue, double newValue) {
    }

    @Override
    public void firePropertyChange(String propertyName, boolean oldValue, boolean newValue) {
    }

    public Component getListCellRendererComponent(JList list, Object value,
            int index, boolean isSelected,
            boolean cellHasFocus) {
        return getCellRendererComponent(list, value, isSelected, cellHasFocus, false);
    }

    @Override
    public Component getCellRendererComponent(JComponent container, Object value, boolean isSelected, boolean cellHasFocus) {
        return getCellRendererComponent(container, value, isSelected, cellHasFocus, true);
    }

    protected Component getCellRendererComponent(JComponent container,
        Object value,
        boolean isSelected,
        boolean cellHasFocus,
        boolean isListView) {

        this.isListView = isListView;

        if (!(value instanceof FileInfo)) {
            return this;
        }

        FileInfo info = (FileInfo) value;

        isGrayed = !info.isAcceptable() && !info.isTraversable();

        labelColor = OSXFile.getLabelColor(info.getFileLabel(), (isGrayed) ? 2 : 0);
        labelBrightColor = OSXFile.getLabelColor(info.getFileLabel(), (isGrayed) ? 3 : 1);

        this.isSelected = isSelected;
        this.isActive = container.isEnabled() && QuaquaUtilities.isFocused(container);

        if (this.isSelected) {
            if (isActive) {
                setBackground(UIManager.getColor("Browser.selectionBackground"));
                setForeground(UIManager.getColor("Browser.selectionForeground"));
            } else {
                setBackground(UIManager.getColor("Browser.inactiveSelectionBackground"));
                setForeground(UIManager.getColor("Browser.inactiveSelectionForeground"));
            }
        } else {
            //setBackground((labelColor == null) ? container.getBackground() : labelColor);
            setBackground(container.getBackground());
            setForeground((isGrayed) ? labelDisabledForeground : labelForeground);
        }

        if (!isListView) {
            boolean useUnselectedArrow = UIManager.getBoolean("FileChooser.browserUseUnselectedExpandIconForLabeledFile");
            if (this.isSelected && (!useUnselectedArrow || labelColor == null)) {
                if (QuaquaUtilities.isFocused(container)) {
                    arrowIcon = (info.isValidating()) ? focusedSelectedExpandingIcon : focusedSelectedExpandedIcon;
                } else {
                    arrowIcon = (info.isValidating()) ? selectedExpandingIcon : selectedExpandedIcon;
                }
            } else {
                arrowIcon = (info.isValidating()) ? expandingIcon : expandedIcon;
            }

            /*
              Special case: no arrow is displayed for a package even if the package is traversable (an option).
            */

            if (!info.isTraversable() || OSXFile.isVirtualFile(info.lazyGetResolvedFile())) {
                arrowIcon = (labelColor == null) ? null : emptyIcon;
            }
        } else {
            arrowIcon = null;
        }

        text = info.getUserName();
        icon = info.getIcon();

        isAlias = false;
        if (info instanceof FileSystemTreeModel.Node) {
            FileSystemTreeModel.Node n = (FileSystemTreeModel.Node) info;
            isAlias = n.isAlias();
        }

        setOpaque(!isListView);
        setEnabled(container.isEnabled());
        setFont(container.getFont());
        setBorder(isListView ? null : (cellHasFocus) ? UIManager.getBorder(isGrayed ? "FileChooser.browserCellFocusBorderGrayed" : "FileChooser.browserCellFocusBorder") : UIManager.getBorder("FileChooser.browserCellBorder"));
        return this;
    }

    @Override
    protected void paintComponent(Graphics gr) {
        Object oldHints = QuaquaUtilities.beginGraphics((Graphics2D) gr);
        Graphics2D g = (Graphics2D) gr;
        int width = getWidth();

        int height = getHeight();
        Insets insets = getInsets();

        resetRects();

        viewRect.setBounds(0, 0, width, height);
        viewRect.x += insets.left;
        viewRect.y += insets.top;
        viewRect.width -= insets.left + insets.right;
        viewRect.height -= insets.top + insets.bottom;

        Font textFont = getFont();
        g.setFont(textFont);
        FontMetrics textFM = g.getFontMetrics(textFont);
        if (isOpaque()) {
            g.setColor(getBackground());
            g.fillRect(0, 0, width, height);
        }

        String clippedText = layoutRenderer(
                textFM, text,
                icon, arrowIcon,
                viewRect, iconRect, textRect, arrowIconRect, labelRect,
                text == null ? 0 : textIconGap, textArrowIconGap);

        if (labelColor != null) {
            if (isSelected) {
                Insets i = UIManager.getInsets("FileChooser.browserCellSelectedColorLabelInsets");
                if (i == null) {
                    i = new Insets(0, 0, 0, 0);
                }
                r.y = viewRect.y + i.top;
                r.width = r.height = viewRect.height - 1;
                r.x = arrowIconRect.x - (arrowIconRect.width - r.width) / 2 + i.left;
                //r.x = viewRect.width - r.width;
                //g.fillOval(r.x, r.y, r.width, r.height);
            } else {
                Insets i = UIManager.getInsets("FileChooser.browserCellColorLabelInsets");
                if (i == null) {
                    i = new Insets(0, 0, 0, 0);
                }
                r.x = textRect.x - textIconGap + i.left;
                r.y = viewRect.y + i.top;
                r.width = viewRect.width - r.x + viewRect.x - i.right;
                r.height = viewRect.height - r.y + viewRect.y - i.bottom;
            }
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.setPaint(new LinearGradientPaint(r.x, r.y, labelBrightColor, r.x, r.y + r.height, labelColor));
            //g.setColor(labelColor);
            g.fillRoundRect(r.x, r.y, r.width, r.height, r.height, r.height);
        }

        if (icon != null) {
            icon.paintIcon(this, g, iconRect.x, iconRect.y);
        }

        if (isAlias && aliasBadgeIcon != null) {
            aliasBadgeIcon.paintIcon(this, g, iconRect.x, iconRect.y);
        }

        if (clippedText != null && !clippedText.equals("")) {
            g.setColor(getForeground());
            g.drawString(clippedText, textRect.x, textRect.y + textFM.getAscent());
        }

        if (arrowIcon != null) {
            arrowIcon.paintIcon(this, g, arrowIconRect.x, arrowIconRect.y);
        }

        QuaquaUtilities.endGraphics((Graphics2D) g, oldHints);
    }
    /**
     * The following variables are used for laying out the renderer.
     * This variables are static, because FileRenderer is always called
     * from the EventDispatcherThread, and because we do not use them in a
     * reentrant context, where a FileRenderer instance enters a method of
     * another FileRenderer instance.
     */
    private static final Rectangle zeroRect = new Rectangle(0, 0, 0, 0);
    private static Rectangle iconRect = new Rectangle();
    private static Rectangle textRect = new Rectangle();
    private static Rectangle arrowIconRect = new Rectangle();
    private static Rectangle viewRect = new Rectangle();
    private static Rectangle labelRect = new Rectangle();
    /** r is used in getPreferredSize and in paintComponent. It must not be
     * used in any method called by one of these.
     */
    private static Rectangle r = new Rectangle();

    private void resetRects() {
        iconRect.setBounds(zeroRect);
        textRect.setBounds(zeroRect);
        arrowIconRect.setBounds(zeroRect);
        labelRect.setBounds(zeroRect);
        viewRect.setBounds(0, 0, 32767, 32767);
        r.setBounds(zeroRect);
    }

    @Override
    public Dimension getPreferredSize() {
        Font textFont = getFont();
        FontMetrics textFM = getFontMetrics(textFont);

        resetRects();

        layoutRenderer(
                textFM, text,
                icon, arrowIcon,
                viewRect,
                iconRect,
                textRect,
                arrowIconRect,
                labelRect,
                text == null ? 0 : textIconGap, textArrowIconGap);

        r.setBounds(textRect);
        r = SwingUtilities.computeUnion(iconRect.x, iconRect.y, iconRect.width,
                iconRect.height, r);

        boolean isUseArrow = arrowIcon != null;
        if (isUseArrow) {
            r.width += arrowIconRect.width + textArrowIconGap;
        }

        Insets insets = getInsets();
        if (insets != null) {
            r.width += insets.left + insets.right;
            r.height += insets.top + insets.bottom;
        }

        return r.getSize();
    }

    /**
     * Layouts the components of the renderer.
     */
    private String layoutRenderer(
            FontMetrics textFM, String text,
            Icon icon, Icon arrowIcon,
            Rectangle viewRect, Rectangle iconRect,
            Rectangle textRect,
            Rectangle arrowIconRect,
            Rectangle labelRect,
            int textIconGap, int textArrowIconGap) {

        boolean isUseArrow = arrowIcon != null;

        if (isUseArrow) {
            arrowIconRect.width = arrowIcon.getIconWidth();
            arrowIconRect.height = arrowIcon.getIconHeight();
            arrowIconRect.x = viewRect.x + viewRect.width - arrowIconRect.width;
            viewRect.width -= arrowIconRect.width + textArrowIconGap;
        }

        text = QuaquaUtilities.layoutCompoundLabel(
                this, textFM, text,
                icon, SwingConstants.CENTER, SwingConstants.LEFT,
                SwingConstants.CENTER, SwingConstants.RIGHT,
                viewRect, iconRect, textRect,
                textIconGap);

        if (isUseArrow) {
            viewRect.width += arrowIconRect.width + textArrowIconGap;
        }

        Rectangle jLabelRect = iconRect.union(textRect);

        if (isUseArrow) {
            arrowIconRect.y = (viewRect.y + jLabelRect.height / 2 - arrowIconRect.height / 2);
        }

        if (!QuaquaUtilities.isLeftToRight(this)) {
            int width = viewRect.width;
            iconRect.x = width - (iconRect.x + iconRect.width);
            textRect.x = width - (textRect.x + textRect.width);
            arrowIconRect.x = width - (arrowIconRect.x + arrowIconRect.width);
            labelRect.x = width - (labelRect.x + labelRect.width);
        }

        return text;
    }
}
