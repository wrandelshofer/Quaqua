/*
 * @(#)BackgroundBorder.java
 * Quaqua Look and Feel. Copyright 2020 © Werner Randelshofer, Switzerland. MIT License.
 */

package ch.randelshofer.quaqua.border;

import javax.swing.border.*;
/**
 * BackgroundBorder is used by the Quaqua Look And Feel to tag a
 * border which partially needs to be drawn on to the background of a JComponent.
 *
 * @author  Werner Randelshofer
 * @version $Id$
 */
public interface BackgroundBorder extends Border {

    /**
     * Returns the border that needs to be drawn onto the background.
     */
    public Border getBackgroundBorder();
}
