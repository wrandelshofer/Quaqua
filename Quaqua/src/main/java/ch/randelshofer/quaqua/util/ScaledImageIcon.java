/*
 * @(#)ScaledImageIcon.java
 * Quaqua Look and Feel. Copyright © 2020 Werner Randelshofer, Switzerland. MIT License.
 */

package ch.randelshofer.quaqua.util;

import javax.swing.Icon;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;

/**
 * Renders the provided image scaled to the specified width and height.
 * <p>
 * ScaledImageIcon can be used to render a high-resolution buffered image
 * on a retina display.
 * <p>
 * Is there a way to encode this information in the image?
 */
public class ScaledImageIcon implements Icon {
    private final BufferedImage image;
    private final int width, height;

    public ScaledImageIcon(BufferedImage image, int width, int height) {
        this.image = image;
        this.width = width;
        this.height = height;
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        g.drawImage(image,x,y,width,height,null);

    }

    @Override
    public int getIconWidth() {
        return width;
    }

    @Override
    public int getIconHeight() {
        return height;
    }
}
