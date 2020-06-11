/*
 * @(#)MutableColorUIResource.java
 * Quaqua Look and Feel. Copyright © 2020 Werner Randelshofer, Switzerland. MIT License.
 */

package ch.randelshofer.quaqua.color;

import javax.swing.plaf.UIResource;
import java.awt.Color;
import java.awt.PaintContext;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.ColorModel;

/**
 * A ColorUIResource which can change its color.
 *
 * @author Werner Randelshofer
 * @version $Id$
 */
public class MutableColorUIResource extends Color implements UIResource {
    private int argb;

    /**
     * Creates a new instance.
     */
    public MutableColorUIResource(int rgb) {
        this(rgb, false);
    }

    public MutableColorUIResource(int argb, boolean hasAlpha) {
        super((hasAlpha) ? argb : 0xff000000 | argb, true);
        this.argb = argb;
    }

    public void setColor(Color newValue) {
        setRGB(newValue.getRGB());
    }

    public void setRGB(int newValue) {
        argb = newValue;
    }

    public int getRGB() {
        return argb;
    }

    public PaintContext createContext(ColorModel cm, Rectangle r, Rectangle2D r2d, AffineTransform xform, RenderingHints hints) {
        return new Color(argb, true).createContext(cm, r, r2d, xform, hints);
    }
}
