/*
 * @(#)QuaquaPasswordFieldUI.java
 * Quaqua Look and Feel. Copyright © 2020 Werner Randelshofer, Switzerland. MIT License.
 */

package ch.randelshofer.quaqua;

import ch.randelshofer.quaqua.border.BackgroundBorder;
import ch.randelshofer.quaqua.util.Debug;
import ch.randelshofer.quaqua.util.Fonts;
import ch.randelshofer.quaqua.util.InsetsUtil;

import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.TextUI;
import javax.swing.plaf.UIResource;
import javax.swing.plaf.basic.BasicPasswordFieldUI;
import javax.swing.text.AbstractDocument;
import javax.swing.text.Caret;
import javax.swing.text.Document;
import javax.swing.text.EditorKit;
import javax.swing.text.Element;
import javax.swing.text.Highlighter;
import javax.swing.text.JTextComponent;
import javax.swing.text.Keymap;
import javax.swing.text.View;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Window;
import java.awt.event.FocusListener;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;

import static ch.randelshofer.quaqua.QuaquaClientProperties.QUAQUA_COMPONENT_VISUAL_MARGIN_CLIENT_PROPERTY;

/**
 * QuaquaPasswordFieldUI.
 *
 * @author Werner Randelshofer
 * @version $Id$
 */
public class QuaquaPasswordFieldUI extends BasicPasswordFieldUI implements VisuallyLayoutable {
    private FocusListener focusListener;
    //private HierarchyListener hierarchyListener;
    private MouseListener popupListener;

    /**
     * Creates a UI for a JPasswordField.
     *
     * @param c the JPasswordField
     * @return the UI
     */
    public static ComponentUI createUI(JComponent c) {
        return new QuaquaPasswordFieldUI();
    }

    @Override
    public void installUI(JComponent c) {
        super.installUI(c);
        QuaquaUtilities.installProperty(c, "opaque", UIManager.get(getPropertyPrefix() + ".opaque"));
    }

    @Override
    protected void installDefaults() {
        super.installDefaults();
        /* Unfortunately we can not set the echo char from the UI. :(
        JPasswordField t = (JPasswordField) getComponent();
        t.setEchoChar('\u2022');
         */
    }

    @Override
    protected void uninstallDefaults() {
        super.uninstallDefaults();
    }

    @Override
    protected void installListeners() {
        focusListener = createFocusListener();
        if (focusListener != null) {
            getComponent().addFocusListener(focusListener);
        }
        popupListener = createPopupListener();
        if (popupListener != null) {
            getComponent().addMouseListener(popupListener);
        }
        QuaquaTextCursorHandler.getInstance().installListeners(getComponent());
        super.installListeners();
    }

    @Override
    protected void uninstallListeners() {
        if (focusListener != null) {
            getComponent().removeFocusListener(focusListener);
            focusListener = null;
        }
        if (popupListener != null) {
            getComponent().removeMouseListener(popupListener);
            popupListener = null;
        }
        QuaquaTextCursorHandler.getInstance().uninstallListeners(getComponent());
        super.uninstallListeners();
    }

    protected FocusListener createFocusListener() {
        return (FocusListener) UIManager.get(getPropertyPrefix() + ".focusHandler");
    }

    protected MouseListener createPopupListener() {
        return (MouseListener) UIManager.get(getPropertyPrefix() + ".popupHandler");
    }

    /**
     * Fetches the EditorKit for the UI.
     *
     * @param tc the text component for which this UI is installed
     * @return the editor capabilities
     * @see TextUI#getEditorKit
     */
    @Override
    public EditorKit getEditorKit(JTextComponent tc) {
        return QuaquaTextFieldUI.defaultKit;
    }

    public Insets getVisualMargin(JTextComponent tc) {
        Insets margin = (Insets) tc.getClientProperty(QUAQUA_COMPONENT_VISUAL_MARGIN_CLIENT_PROPERTY);
        if (margin == null) {
            margin = UIManager.getInsets("Component.visualMargin");
        }
        return (margin == null) ? new Insets(0, 0, 0, 0) : (Insets) margin.clone();
    }

    @Override
    protected void paintSafely(Graphics g) {
        Object oldHints = QuaquaUtilities.beginGraphics((Graphics2D) g);
        JTextComponent editor = getComponent();

        // Paint the background with the background border or the background color
        Border border = editor.getBorder();
        if (border != null && border instanceof BackgroundBorder) {
            Border bb = ((BackgroundBorder) border).getBackgroundBorder();
            bb.paintBorder(editor, g, 0, 0, editor.getWidth(), editor.getHeight());
        } else {
            if (editor.isOpaque()) {
                super.paintBackground(g);
            }
        }

        super.paintSafely(g);
        QuaquaUtilities.endGraphics((Graphics2D) g, oldHints);
        Debug.paint(g, editor, this);
    }

    /**
     * Paints a background for the view.  This will only be
     * called if isOpaque() on the associated component is
     * true.  The default is to paint the background color
     * of the component.
     *
     * @param g the graphics context
     */
    @Override
    protected void paintBackground(Graphics g) {
        // This method is overriden here, to make it do nothing.
        // We already paint the background in method paintSafely();
    }

    @Override
    public void propertyChange(PropertyChangeEvent event) {
        String name = event.getPropertyName();
        if (name.equals("Frame.active")) {
            QuaquaUtilities.repaintBorder(getComponent());
        } else if (name.equals("JComponent.sizeVariant")) {
            QuaquaUtilities.applySizeVariant(getComponent());
        } else {
            super.propertyChange(event);
        }
    }

    @Override
    protected Caret createCaret() {
        Window window = SwingUtilities.getWindowAncestor(getComponent());
        QuaquaCaret caret = new QuaquaCaret(window, getComponent());
        return caret;
    }

    @Override
    protected Highlighter createHighlighter() {
        return new QuaquaHighlighter();
    }

    /**
     * Creates a view (PasswordView) for an element.
     *
     * @param elem the element
     * @return the view
     */
    @Override
    public View create(Element elem) {
        return new QuaquaPasswordView(elem);
    }

    /**
     * Creates the keymap to use for the text component, and installs
     * any necessary bindings into it.  By default, the keymap is
     * shared between all instances of this type of TextUI. The
     * keymap has the name defined by the getKeymapName method.  If the
     * keymap is not found, then DEFAULT_KEYMAP from JTextComponent is used.
     * <p>
     * The set of bindings used to create the keymap is fetched
     * from the UIManager using a key formed by combining the
     * {@link #getPropertyPrefix} method
     * and the name <code>.keyBindings</code>.  The type is expected
     * to be <code>JTextComponent.KeyBinding[]</code>.
     *
     * @return the keymap
     * @see #getKeymapName
     * @see javax.swing.text.JTextComponent
     */
    @Override
    protected Keymap createKeymap() {
        String nm = getKeymapName();
        Keymap map = JTextComponent.getKeymap(nm);
        if (map == null) {
            Keymap parent = JTextComponent.getKeymap(JTextComponent.DEFAULT_KEYMAP);
            map = JTextComponent.addKeymap(nm, parent);
            String prefix = getPropertyPrefix();
            Object o = UIManager.get(prefix + ".keyBindings");
            if ((o != null) && (o instanceof JTextComponent.KeyBinding[])) {
                JTextComponent.KeyBinding[] bindings = (JTextComponent.KeyBinding[]) o;
                JTextComponent.loadKeymap(map, bindings, getComponent().getActions());
            }
        }
        return map;
    }

    @Override
    public int getBaseline(JComponent c, int width, int height) {
        JTextComponent textComponent = (JTextComponent) c;
        View rootView = textComponent.getUI().getRootView(textComponent);
        if (rootView.getViewCount() > 0) {
            Insets insets = textComponent.getInsets();
            int h = height - insets.top - insets.bottom;
            int y = insets.top;
            View fieldView = rootView.getView(0);
            int vspan = (int) fieldView.getPreferredSpan(View.Y_AXIS);
            if (height != vspan) {
                int slop = h - vspan;
                y += slop / 2;
            }
            FontMetrics fm = textComponent.getFontMetrics(
                    textComponent.getFont());
            y += fm.getAscent();
            return y;
        }
        return -1;
    }

    public Rectangle getVisualBounds(JComponent c, int type, int width, int height) {
        Rectangle bounds = new Rectangle(0, 0, width, height);
        if (type == VisuallyLayoutable.CLIP_BOUNDS) {
            return bounds;
        }

        JTextComponent b = (JTextComponent) c;
        if (type == VisuallyLayoutable.COMPONENT_BOUNDS
                && b.getBorder() != null) {
            Border border = b.getBorder();
            if (border instanceof UIResource) {
                InsetsUtil.subtractInto(getVisualMargin(b), bounds);
                // FIXME - Should derive this value from border
                // FIXME - If we had layout managers that supported baseline alignment,
                //              we wouldn't have to subtract one here
                bounds.height -= 1;
            }
        } else {
            bounds = getVisibleEditorRect();

            int baseline = getBaseline(b, width, height);
            Rectangle textBounds = Fonts.getPerceivedBounds(b.getText(), b.getFont(), c);
            if (bounds == null) {
                bounds = textBounds;
                bounds.y += baseline;
            } else {
                bounds.y = baseline + textBounds.y;
                bounds.height = textBounds.height;
            }
            bounds.x += 1;
            bounds.width -= 2;
        }
        return bounds;
    }

    @Override
    public Dimension getPreferredSize(JComponent c) {
        // The following code has been derived from BasicTextUI.
        Document doc = ((JTextComponent) c).getDocument();
        Insets i = c.getInsets();
        Dimension d = c.getSize();
        View rootView = getRootView((JTextComponent) c);

        if (doc instanceof AbstractDocument) {
            ((AbstractDocument) doc).readLock();
        }
        try {
            if ((d.width > (i.left + i.right)) && (d.height > (i.top + i.bottom))) {
                rootView.setSize(d.width - i.left - i.right, d.height - i.top - i.bottom);
            } else if (d.width == 0 && d.height == 0) {
                // Probably haven't been layed out yet, force some sort of
                // initial sizing.
                rootView.setSize(Integer.MAX_VALUE, Integer.MAX_VALUE);
            }
            d.width = (int) Math.min((long) rootView.getPreferredSpan(View.X_AXIS) +
                    (long) i.left + (long) i.right, Integer.MAX_VALUE);
            d.height = (int) Math.min((long) rootView.getPreferredSpan(View.Y_AXIS) +
                    (long) i.top + (long) i.bottom, Integer.MAX_VALUE);
        } finally {
            if (doc instanceof AbstractDocument) {
                ((AbstractDocument) doc).readUnlock();
            }
        }

        // Fix: The preferred width is always two pixels too small
        // on a Mac.
        d.width += 2;

        return d;
    }
}
