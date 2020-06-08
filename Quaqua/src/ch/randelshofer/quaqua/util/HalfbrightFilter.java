/*
 * @(#)HalfbrightFilter.java
 * Quaqua Look and Feel. Copyright © 2020 Werner Randelshofer, Switzerland. MIT License.
 */

package ch.randelshofer.quaqua.util;

import java.awt.*;
import java.awt.image.*;
/**
 * HalfbrightFilter reduces the brightness of an image by 50 percent.
 * Leaves the alpha channel untouched.
 * This is used by the Quaqua Look and Feel, to create a pressed image.
 *
 * @author  Werner Randelshofer
 * @version 1.0  28 March 2005  Created.
 */
public class HalfbrightFilter extends RGBImageFilter {
    /** Creates a new instance. */
    public HalfbrightFilter() {
        canFilterIndexColorModel = true;
    }

    /**
     * Creates a halfbright image
     */
    public static Image createHalfbrightImage (Image i) {
	ImageFilter filter = new HalfbrightFilter();
	ImageProducer prod = new FilteredImageSource(i.getSource(), filter);
	Image filteredImage = Toolkit.getDefaultToolkit().createImage(prod);
	return filteredImage;
    }

    public int filterRGB(int x, int y, int rgb) {
        return rgb & 0xff000000 // preserve alpha channel
        | (rgb & 0xfefefe) >>> 1;
    }

}
