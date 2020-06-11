/*
 * @(#)InactivatableColorUIResource.java
 * Quaqua Look and Feel. Copyright © 2020 Werner Randelshofer, Switzerland. MIT License.
 */

package ch.randelshofer.quaqua.color;

import javax.swing.plaf.UIResource;
import java.awt.Color;
import java.awt.Component;
import java.awt.Paint;
import java.awt.PaintContext;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.ColorModel;

/**
 * ActivatableColorUIResource is a color, that can be rendered using an
 * an active state and an inactive state.
 *
 * @author Werner Randelshofer
 * @version $Id$
 */
public class ActivatableColorUIResource extends PaintableColor
        implements UIResource, ActivatableUIResource, FocusableUIResource {
    private boolean isActive;
    private boolean isFocused;
    private Color activeAndFocused;
    private Color inactiveAndFocused;
    private Color activeAndUnfocused;
    private Color inactiveAndUnfocused;
    private final static Color transparentColor = new Color(0, true);
    private boolean isTransparent;

    /**
     * Creates a new instance.
     */
    public ActivatableColorUIResource(int activeRGB, int inactiveRGB) {
        this(activeRGB, inactiveRGB, false);
    }

    public ActivatableColorUIResource(int activeAndFocusedRGB, int inactiveAndFocusedRGB, int activeAndUnfocusedRGB, int inactiveAndUnfocusedRGB) {
        this(new Color(activeAndFocusedRGB), new Color(inactiveAndFocusedRGB), new Color(activeAndUnfocusedRGB), new Color(inactiveAndUnfocusedRGB));
    }

    public ActivatableColorUIResource(int activeRGB, int inactiveRGB, boolean hasAlpha) {
        this(new Color(activeRGB, hasAlpha), new Color(inactiveRGB, hasAlpha));
    }

    public ActivatableColorUIResource(Color active, Color inactive) {
        this(active, inactive, active, inactive);
    }

    public ActivatableColorUIResource(Color activeAndFocused, Color inactiveAndFocused,
                                      Color activeAndUnfocused, Color inactiveAndUnfocused) {
        super(activeAndFocused.getRGB(), activeAndFocused.getTransparency() != Paint.OPAQUE);
        this.activeAndFocused = activeAndFocused;
        this.inactiveAndFocused = inactiveAndFocused;
        this.activeAndUnfocused = activeAndUnfocused;
        this.inactiveAndUnfocused = inactiveAndUnfocused;
    }

    public void setActive(boolean newValue) {
        isActive = newValue;
    }

    public void setFocused(boolean newValue) {
        isFocused = newValue;
    }

    private Color getColor() {
        if (isTransparent) {
            return transparentColor;
        }
        if (isActive) {
            return (isFocused) ? activeAndFocused : activeAndUnfocused;
        } else {
            return (isFocused) ? inactiveAndFocused : inactiveAndUnfocused;
        }
    }

    public void setTransparent(boolean newValue) {
        isTransparent = newValue;
    }

    @Override
    public int getTransparency() {
        return getColor().getTransparency();
    }

    @Override
    public int getAlpha() {
        return getColor().getAlpha();
    }

    @Override
    public int getRGB() {
        return getColor().getRGB();

    }

    @Override
    public PaintContext createContext(ColorModel cm, Rectangle r, Rectangle2D r2d, AffineTransform xform, RenderingHints hints) {
        return getColor().createContext(cm, r, r2d, xform, hints);
    }

    @Override
    public Paint getPaint(Component c, int x, int y, int width, int height) {
        Color clr = getColor();
        if (clr instanceof PaintableColor) {
            return ((PaintableColor) clr).getPaint(c, x, y, width, height);
        } else {
            return clr;
        }
    }
}
