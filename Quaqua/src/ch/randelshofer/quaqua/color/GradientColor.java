/*
 * @(#)GradientColor.java
 * Quaqua Look and Feel. Copyright © 2020 Werner Randelshofer, Switzerland. MIT License.
 */
package ch.randelshofer.quaqua.color;

import ch.randelshofer.quaqua.ext.batik.ext.awt.LinearGradientPaint;

import java.awt.Color;
import java.awt.Component;
import java.awt.Paint;


/**
 * GradientColor.
 *
 * @author Werner Randelshofer
 *  @version $Id$
 */
public class GradientColor extends PaintableColor {

    protected Color color1;
    protected Color color2;

    public GradientColor(int plainColor, int gradientNorth, int gradientSouth) {
        super(plainColor, (plainColor & 0xff000000) != 0xff000000);
        this.color1 = new Color(gradientNorth);
        this.color2 = new Color(gradientSouth);
    }

    public GradientColor(Color plainColor, Color gradientNorth, Color gradientSouth) {
        super(plainColor.getRGB(), plainColor.getAlpha() != 255);
        this.color1 = gradientNorth;
        this.color2 = gradientSouth;
    }

    public Paint getPaint(Component c, int x, int y, int widht, int height) {
        return new LinearGradientPaint(x, y, color1, x, y + height, color2);
    }

    public static class UIResource extends GradientColor implements javax.swing.plaf.UIResource {

        public UIResource(int plainColor, int gradientNorth, int gradientSouth) {
            super(new Color(plainColor), new Color(gradientNorth), new Color(gradientSouth));
        }
    }
}
