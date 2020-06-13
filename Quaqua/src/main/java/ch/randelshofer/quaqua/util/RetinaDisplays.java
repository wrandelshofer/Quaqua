/*
 * @(#)Retina.java
 * Quaqua Look and Feel. Copyright © 2020 Werner Randelshofer, Switzerland. MIT License.
 */

package ch.randelshofer.quaqua.util;

import sun.awt.CGraphicsDevice;

import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import static java.lang.Math.max;

public class RetinaDisplays {

    private static Integer deviceScaleFactor;


    /**
     * Returns the scale factor of the underlying device of the given graphics object.
     * <p>
     * The scale factor is used to scale to increase the resolution of
     * intermediate bitmap images, and to render native OS X widgets at
     * a higher resolution.
     *
     * @param g a graphics object
     * @return an integral scale factor, {@literal >= 1}.
     */
    public static int getDeviceScaleFactor(Graphics g) {
        // XXX g.getDeviceConfiguration().getDefaultTransform() only works on Java 9 or higher
        /*
        if (g instanceof Graphics2D) {
            double scaleX = ((Graphics2D) g).getDeviceConfiguration().getDefaultTransform().getScaleX();
            return max(1,(int)scaleX);
        }
        */
        return getDeviceScaleFactor();
    }


    /**
     * Returns the scale factor for retina displays.
     * <p>
     * The scale factor is used to scale to increase the resolution of
     * intermediate bitmap images, and to render native OS X widgets at
     * a higher resolution.
     *
     * @return an integral scale factor, {@literal >= 1}.
     */
    public static int getDeviceScaleFactor() {
        if (deviceScaleFactor == null) {
            // find the display device of interest
            final GraphicsDevice defaultScreenDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

            // on OS X, it would be CGraphicsDevice
            if (defaultScreenDevice instanceof CGraphicsDevice) {
                final CGraphicsDevice device = (CGraphicsDevice) defaultScreenDevice;

                // this is the missing correction factor, it's equal to 2 on HiDPI a.k.a. Retina displays
                deviceScaleFactor = device.getScaleFactor();

            } else {
                deviceScaleFactor = 1;
            }
        }
        // FIXME enable device scale factor once we support it everywhere
        return  1;//retinaScaleFactor;
    }
}
