/*
 * @(#)ColorTrackImageProducer.java
 * Quaqua Look and Feel. Copyright © 2020 Werner Randelshofer, Switzerland. MIT License.
 */

package ch.randelshofer.quaqua.colorchooser;

import java.awt.image.ColorModel;
import java.awt.image.MemoryImageSource;

/**
 * ColorTrackImageProducer creates the image for the track of a
 * color slider.
 *
 * @author Werner Randelshofer
 * @version $Id$
 * @see ColorSliderUI
 */
public class ColorTrackImageProducer extends MemoryImageSource {
    private int[] pixels;
    private int w, h;
    private int trackBuffer;
    private ColorSliderModel colorizer = new RGBColorSliderModel();
    private boolean isDirty = true;
    private int componentIndex = 0;
    private boolean isHorizontal;

    /**
     * Creates a new instance.
     */
    public ColorTrackImageProducer(int w, int h, int trackBuffer, boolean isHorizontal) {
        super(w, h, null, 0, w);
        pixels = new int[w * h];
        this.w = w;
        this.h = h;
        // trackBuffer must be even
        this.trackBuffer = ((trackBuffer & 1) == 0) ? trackBuffer - 1 : trackBuffer;
        this.isHorizontal = isHorizontal;
        newPixels(pixels, ColorModel.getRGBdefault(), 0, w);
        setAnimated(true);
    }

    public int getWidth() {
        return w;
    }

    public int getHeight() {
        return h;
    }

    public void markAsDirty() {
        isDirty = true;
    }

    public boolean needsGeneration() {
        return isDirty;
    }

    public void regenerateColorTrack() {
        if (isDirty) {
            generateColorTrack();
        }
    }

    public void generateColorTrack() {
        if (isHorizontal) {
            generateHorizontalColorTrack();
        } else {
            generateVerticalColorTrack();
        }
        newPixels();
        isDirty = false;
    }

    private void generateHorizontalColorTrack() {
        int offset = trackBuffer / 2;
        for (int x = 0, n = w - trackBuffer - 1; x <= n; x++) {
            pixels[x + offset] = colorizer.getInterpolatedRGB(componentIndex, x / (float) n);
        }
        for (int x = 0; x < offset; x++) {
            pixels[x] = pixels[offset];
            pixels[w - x - 1] = pixels[w - offset - 1];
        }
        for (int y = w, n = w * h; y < n; y += w) {
            System.arraycopy(pixels, 0, pixels, y, w);
        }
    }

    private void generateVerticalColorTrack() {
        int offset = trackBuffer / 2;
        for (int y = 0, n = h - trackBuffer - 1; y <= n; y++) {
            pixels[(y + offset) * w] = colorizer.getInterpolatedRGB(componentIndex, 1 - y / (float) n);
        }
        for (int y = 0; y < offset; y++) {
            pixels[y * w] = pixels[offset * w];
            pixels[(h - y - 1) * w] = pixels[(h - offset - 1) * w];
        }
        for (int x = 1; x < w; x++) {
            for (int y = 0, n = w * h; y < n; y += w) {
                pixels[x + y] = pixels[x - 1 + y];
            }
        }
    }

    public void setColorSliderModel(ColorSliderModel colorizer) {
        this.colorizer = colorizer;
        isDirty = true;
    }

    public void setColorComponentIndex(int index) {
        this.componentIndex = index;
        isDirty = true;
    }

    public void componentChanged(int index) {
        isDirty |= this.componentIndex != index;
    }
}
