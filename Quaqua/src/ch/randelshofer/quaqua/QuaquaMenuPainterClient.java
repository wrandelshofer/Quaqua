/*
 * @(#)QuaquaMenuPainterClient.java  
 *
 * Copyright (c) 2003-2013 Werner Randelshofer, Switzerland.
 * You may not use, copy or modify this file, except in compliance with the
 * accompanying license terms.
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
