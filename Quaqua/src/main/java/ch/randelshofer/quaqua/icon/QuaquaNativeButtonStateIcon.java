/*
 * @(#)QuaquaNativeButtonStateIcon.java
 * Quaqua Look and Feel. Copyright © 2020 Werner Randelshofer, Switzerland. MIT License.
 */
package ch.randelshofer.quaqua.icon;

import ch.randelshofer.quaqua.QuaquaUtilities;
import ch.randelshofer.quaqua.osx.OSXAquaPainter;
import ch.randelshofer.quaqua.util.CachedPainter;
import ch.randelshofer.quaqua.util.RetinaDisplays;

import javax.swing.AbstractButton;
import javax.swing.ButtonModel;
import javax.swing.Icon;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Collections;
import java.util.Map;

import static ch.randelshofer.quaqua.osx.OSXAquaPainter.Key;
import static ch.randelshofer.quaqua.osx.OSXAquaPainter.Size;
import static ch.randelshofer.quaqua.osx.OSXAquaPainter.State;
import static ch.randelshofer.quaqua.osx.OSXAquaPainter.Widget;

/**
 * Native Aqua icon for an {@code AbstractButton}.
 * This icon draws everything except the focus ring. To draw the focus
 * wring, wrap this border into a {@link ch.randelshofer.quaqua.icon.FocusedIcon}.
 *
 * @author Werner Randelshofer
 * @version $Id$
 */
public class QuaquaNativeButtonStateIcon extends CachedPainter implements Icon {
    private final OSXAquaPainter painter;
    private final int width;
    private final int height;
    private final int xoffset;
    private final int yoffset;
    private final static int ARG_ACTIVE = 0;
    private final static int ARG_PRESSED = 1;
    private final static int ARG_DISABLED = 2;
    private final static int ARG_ROLLOVER = 3;
    private final static int ARG_SELECTED = 4;
    private final static int ARG_FOCUSED = 5;
    private final static int ARG_SIZE_VARIANT = 6;//2 bits
    private final static int ARG_SEGPOS = 8;
    private final static int ARG_WIDGET = 11;// 7 bits
    private final static int ARG_TRAILING_SEPARATOR = 18;

    public QuaquaNativeButtonStateIcon(Widget widget, int width, int height) {
        this(widget, 0, 0, width, height, Collections.emptyMap());

    }

    public QuaquaNativeButtonStateIcon(Widget widget, int xoffset, int yoffset, int width, int height, Map<Key,Double> properties) {
        super(12);
        painter = new OSXAquaPainter();
        painter.setWidget(widget);
        for (Map.Entry<Key, Double> entry : properties.entrySet()) {
            painter.setValueByKey(entry.getKey(),entry.getValue());
        }

        this.xoffset = xoffset;
        this.yoffset = yoffset;
        this.width = width;
        this.height = height;
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        AbstractButton b = null;
        ButtonModel bm = null;
        if (c instanceof AbstractButton) {
            b = (AbstractButton) c;
            bm = b.getModel();
        }

        int args = 0;
        State state;
        if (QuaquaUtilities.isOnActiveWindow(c)) {
            state = State.active;
            args |= 1 << ARG_ACTIVE;
        } else {
            state = State.inactive;
        }
        if (bm != null) {
            if (bm.isArmed() && bm.isPressed()) {
                state = State.pressed;
                args |= 1 << ARG_PRESSED;
            }
            if (!bm.isEnabled()) {
                state = State.disabled;
                args |= 1 << ARG_DISABLED;
            }
            if (bm.isRollover()) {
                state = State.rollover;
                args |= 1 << ARG_ROLLOVER;
            }
        }
        painter.setState(state);

        int value = b == null ? 1 : (b.isSelected() ? 1 : 0);
        painter.setValueByKey(Key.value, value);
        args |= value << ARG_SELECTED;

        boolean isFocused = QuaquaUtilities.isFocused(c);
        args |= (isFocused) ? 1 << ARG_FOCUSED : 0;
        painter.setValueByKey(Key.focused, isFocused ? 1 : 0);

        Size size;
        switch (QuaquaUtilities.getSizeVariant(c)) {
        case REGULAR:
        default:
            size = OSXAquaPainter.Size.regular;
            break;
        case SMALL:
            size = OSXAquaPainter.Size.small;
            break;
        case MINI:
            size = OSXAquaPainter.Size.mini;
            break;
        }
        painter.setSize(size);
        args |= size.getId() << ARG_SIZE_VARIANT;

        args |= painter.getWidget().getId() << ARG_WIDGET;

        paint(c, g, x, y, width, height, args);
    }

    @Override
    protected Image createImage(Component c, int w, int h,
                                GraphicsConfiguration config) {
        return new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB_PRE);
    }

    @Override
    protected void paintToImage(Component c, Image img, int w, int h, Object args) {
        Graphics2D ig = (Graphics2D) img.getGraphics();
        ig.setColor(new Color(0x0, true));
        ig.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC));
        ig.fillRect(0, 0, img.getWidth(null), img.getHeight(null));
        ig.dispose();
        int scaleFactor = RetinaDisplays.getDeviceScaleFactor();
        painter.paint((BufferedImage) img,//
                xoffset, yoffset,//
                w, //
                h, scaleFactor);
    }

    @Override
    protected void paintToImage(Component c, Graphics g, int w, int h, Object args) {
        int scaleFactor = RetinaDisplays.getDeviceScaleFactor();
        BufferedImage img = (BufferedImage) createImage(c, width * scaleFactor, height * scaleFactor, null);
        paintToImage(c, img, width, height, args);
        g.drawImage(img, 0, 0, width, height, null);
        img.flush();
    }


    public int getIconWidth() {
        return width;
    }

    public int getIconHeight() {
        return height;
    }

    public boolean isBorderOpaque() {
        return false;
    }

    public static class UIResource extends QuaquaNativeButtonStateIcon implements javax.swing.plaf.UIResource {

        public UIResource(Widget widget, int offsetx, int offsety, int width, int height,Map<OSXAquaPainter.Key,Double> p) {
            super(widget, offsetx, offsety, width, height,p);
        }

        public UIResource(Widget widget, int width, int height) {
            super(widget, width, height);
        }


    }
}
