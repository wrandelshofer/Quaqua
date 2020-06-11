/*
 * @(#)AnimatedBorder.java
 * Quaqua Look and Feel. Copyright © 2020 Werner Randelshofer, Switzerland. MIT License.
 */

package ch.randelshofer.quaqua.border;

import javax.swing.border.Border;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.util.HashSet;

/**
 * AnimatedBorder takes an array of borders and a delay value, to draw an
 * animated border.
 * The border to be drawn is selected based on the current time.
 *
 * @author Werner Randelshofer
 * @version $Id$
 */
public class AnimatedBorder implements Border {
    /**
     * In this HashSet we store all components that are scheduled for repainting.
     */
    private HashSet scheduledComponents = new HashSet();
    /**
     * Animation borders.
     * All borders must have the same insets.
     */
    private Border[] borders;
    /**
     * Delay time between borders.
     */
    private long delay;

    /**
     * Creates a new instance.
     * <p>
     * Note: For efficiency reasons this method stores the passed in array
     * internally without copying it. Do not modify the array after
     * invoking this method.
     */
    public AnimatedBorder(Border[] borders, long delay) {
        this.borders = borders;
        this.delay = delay;
    }

    public Insets getBorderInsets(Component c) {
        return (Insets) borders[0].getBorderInsets(c).clone();
    }

    public boolean isBorderOpaque() {
        return borders[0].isBorderOpaque();
    }

    public void paintBorder(final Component c, Graphics g, int x, int y, int width, int height) {
        long animTime = System.currentTimeMillis() % (borders.length * delay);
        int frame = (int) (animTime / delay);

        borders[frame].paintBorder(c, g, x, y, width, height);
    }
}


