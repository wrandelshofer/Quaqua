/*
 * @(#)ViewportPainter.java
 * Quaqua Look and Feel. Copyright © 2020 Werner Randelshofer, Switzerland. MIT License.
 */

package ch.randelshofer.quaqua.util;

import java.awt.*;
import javax.swing.*;
/**
 * This interface is implemented by user interface delegates that wish to
 * paint onto the content area of a JViewport.
 *
 * @author  Werner Randelshofer
 * @version $Id$
 */
public interface ViewportPainter {
    /**
     * Paints the viewport of a JViewport, that contains the component of the
     * user interface delegate.
     * This method is invoked by QuaquaViewportUI.
     */
    public void paintViewport(Graphics g, JViewport c);
}
