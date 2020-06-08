/*
 * @(#)PressedCueBorder.java
 * Quaqua Look and Feel. Copyright © 2020 Werner Randelshofer, Switzerland. MIT License.
 */
package ch.randelshofer.quaqua.border;

import javax.swing.JComponent;
import javax.swing.border.Border;

/**
 * {@code PressedCueBorder}.
 *
 * @author Werner Randelshofer
 * @version 1.0 2011-07-31 Created.
 */
public interface PressedCueBorder extends Border {
    /**
     * Returns true, if this border has a visual cue for the pressed
     * state of the button.
     * If the border has no visual cue, then the ButtonUI has to provide
     * it by some other means.
     */
    public boolean hasPressedCue(JComponent c);
}
