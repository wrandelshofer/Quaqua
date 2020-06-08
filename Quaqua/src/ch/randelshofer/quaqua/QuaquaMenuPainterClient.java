/*
 * @(#)QuaquaMenuPainterClient.java
 * Quaqua Look and Feel. Copyright 2020 © Werner Randelshofer, Switzerland. MIT License.
 */

package ch.randelshofer.quaqua;

import java.awt.*;
import javax.swing.*;
/**
 * QuaquaMenuPainterClient.
 *
 * @author  Werner Randelshofer
 * @version $Id$
 */
public interface QuaquaMenuPainterClient {

   public void paintBackground(Graphics g, JComponent c, int i, int j);

    //public ThemeMenu getTheme();
}
