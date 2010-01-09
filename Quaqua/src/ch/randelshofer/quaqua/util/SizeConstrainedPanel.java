/*
 * @(#)SizeConstrainedPanel.java  1.0  02 January 2005
 *
 * Copyright (c) 2004-2010 Werner Randelshofer
 * Hausmatt 10, Immensee, CH-6405, Switzerland.
 * All rights reserved.
 *
 * The copyright of this software is owned by Werner Randelshofer. 
 * You may not use, copy or modify this software, except in  
 * accordance with the license agreement you entered into with  
 * Werner Randelshofer. For details see accompanying license terms. 
 */

package ch.randelshofer.quaqua.util;

import java.awt.*;
/**
 * A JPanel which has a fixed preferred width or height.
 *
 * @author  Werner Randelshofer
 * @version 1.0  02 January 2005  Created.
 */
public class SizeConstrainedPanel extends javax.swing.JPanel {
    private int preferredWidth = -1;
    private int preferredHeight = -1;
    
    /** Creates new form. */
    public SizeConstrainedPanel() {
        initComponents();
    }
    
    
    /**
     * Sets the preferred width of the panel, without affecting its preferred
     * height.
     * @param w Preferred width. The value -1 clears the preferred width.
     */
    public void setPreferredWidth(int w) {
        this.preferredWidth = w;
    }
    /**
     * Sets the preferred height of the panel, without affecting its preferred
     * width.
     * @param h Preferred height. The value -1 clears the preferred height.
     */
    public void setPreferredHeight(int h) {
        this.preferredHeight = h;
    }
    
    public Dimension getPreferredSize() {
        Dimension dim = super.getPreferredSize();
        if (preferredWidth != -1) dim.width = preferredWidth;
        if (preferredHeight != -1) dim.height = preferredHeight;
        return dim;
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents

        setLayout(new java.awt.BorderLayout());

    }//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    
}
