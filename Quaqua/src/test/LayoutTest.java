/*
 * @(#)LayoutTest.java  1.0  May 16, 2005
 *
 * Copyright (c) 2005 Werner Randelshofer
 * Staldenmattweg 2, Immensee, CH-6405, Switzerland.
 * All rights reserved.
 *
 * The copyright of this software is owned by Werner Randelshofer. 
 * You may not use, copy or modify this software, except in  
 * accordance with the license agreement you entered into with  
 * Werner Randelshofer. For details see accompanying license terms. 
 */

package test;

import ch.randelshofer.quaqua.util.Methods;
import javax.swing.*;
/**
 * LayoutTest.
 *
 * @author  Werner Randelshofer
 * @version 1.0 May 16, 2005 Created.
 */
public class LayoutTest extends javax.swing.JPanel {
    
    /**
     * Creates a new instance.
     */
    public LayoutTest() {
        initComponents();
        Methods.invokeIfExists(tabbedPane, "setTabLayoutPolicy", 0); // JTabbedPane.WRAP_TAB_LAYOUT);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        tabbedPane = new javax.swing.JTabbedPane();
        alignmentTest1 = new test.AlignmentTest();
        visualMarginTest1 = new test.VisualMarginTest();

        setLayout(new java.awt.BorderLayout());

        tabbedPane.addTab("Alignment", alignmentTest1);

        tabbedPane.addTab("Margin", visualMarginTest1);

        add(tabbedPane, java.awt.BorderLayout.CENTER);

    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private test.AlignmentTest alignmentTest1;
    private javax.swing.JTabbedPane tabbedPane;
    private test.VisualMarginTest visualMarginTest1;
    // End of variables declaration//GEN-END:variables
    
}
