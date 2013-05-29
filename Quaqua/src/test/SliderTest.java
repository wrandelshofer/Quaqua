/*
 * @(#)SliderTest.java  1.0  13 February 2005
 *
 * Copyright (c) 2004 Werner Randelshofer, Switzerland.
 * All rights reserved.
 *
 * You may not use, copy or modify this file, except in compliance with the
 * license agreement you entered into with Werner Randelshofer.
 * For details see accompanying license terms.
 */

package test;

import ch.randelshofer.quaqua.util.Fonts;
import java.awt.*;
import javax.swing.*;

/**
 * SliderTest.
 *
 * @author  Werner Randelshofer
 * @version 1.0  13 February 2005  Created.
 */
public class SliderTest extends javax.swing.JPanel {
    
    /** Creates new form. */
    public SliderTest() {
        initComponents();
        
        horizontalTickedLabeledSlider.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        verticalLabeledSlider.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        
        Font smallFont = Fonts.getSmallDialogFont();
        JSlider[] smallSliders = {
            smallSlider, smallLabeledSlider, smallTickedSlider
        };
        for (int i=0; i < smallSliders.length; i++) {
            smallSliders[i].setFont(smallFont);
            smallSliders[i].putClientProperty("JComponent.sizeVariant", "small");
        }
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jSlider7 = new javax.swing.JSlider();
        jSlider13 = new javax.swing.JSlider();
        jSlider8 = new javax.swing.JSlider();
        horizontalTickedLabeledSlider = new javax.swing.JSlider();
        horizontalLabeledSlider = new javax.swing.JSlider();
        jSlider10 = new javax.swing.JSlider();
        jSlider15 = new javax.swing.JSlider();
        jSlider11 = new javax.swing.JSlider();
        verticalLabeledSlider = new javax.swing.JSlider();
        jSlider16 = new javax.swing.JSlider();
        smallSlider = new javax.swing.JSlider();
        smallTickedSlider = new javax.swing.JSlider();
        smallLabeledSlider = new javax.swing.JSlider();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(16, 17, 17, 17));
        setLayout(new java.awt.GridBagLayout());

        jPanel1.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        jPanel1.add(jSlider7, gridBagConstraints);

        jSlider13.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        jPanel1.add(jSlider13, gridBagConstraints);

        jSlider8.setMajorTickSpacing(50);
        jSlider8.setMinorTickSpacing(10);
        jSlider8.setPaintTicks(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        jPanel1.add(jSlider8, gridBagConstraints);

        horizontalTickedLabeledSlider.setMajorTickSpacing(50);
        horizontalTickedLabeledSlider.setMinorTickSpacing(10);
        horizontalTickedLabeledSlider.setPaintLabels(true);
        horizontalTickedLabeledSlider.setPaintTicks(true);
        horizontalTickedLabeledSlider.setSnapToTicks(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        jPanel1.add(horizontalTickedLabeledSlider, gridBagConstraints);

        horizontalLabeledSlider.setMajorTickSpacing(50);
        horizontalLabeledSlider.setMinorTickSpacing(10);
        horizontalLabeledSlider.setPaintLabels(true);
        horizontalLabeledSlider.setSnapToTicks(true);
        horizontalLabeledSlider.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        jPanel1.add(horizontalLabeledSlider, gridBagConstraints);

        jSlider10.setOrientation(javax.swing.JSlider.VERTICAL);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = java.awt.GridBagConstraints.REMAINDER;
        jPanel1.add(jSlider10, gridBagConstraints);

        jSlider15.setOrientation(javax.swing.JSlider.VERTICAL);
        jSlider15.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = java.awt.GridBagConstraints.REMAINDER;
        jPanel1.add(jSlider15, gridBagConstraints);

        jSlider11.setMajorTickSpacing(50);
        jSlider11.setMinorTickSpacing(10);
        jSlider11.setOrientation(javax.swing.JSlider.VERTICAL);
        jSlider11.setPaintTicks(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = java.awt.GridBagConstraints.REMAINDER;
        jPanel1.add(jSlider11, gridBagConstraints);

        verticalLabeledSlider.setMajorTickSpacing(50);
        verticalLabeledSlider.setMinorTickSpacing(10);
        verticalLabeledSlider.setOrientation(javax.swing.JSlider.VERTICAL);
        verticalLabeledSlider.setPaintLabels(true);
        verticalLabeledSlider.setPaintTicks(true);
        verticalLabeledSlider.setSnapToTicks(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = java.awt.GridBagConstraints.REMAINDER;
        jPanel1.add(verticalLabeledSlider, gridBagConstraints);

        jSlider16.setMajorTickSpacing(50);
        jSlider16.setMinorTickSpacing(10);
        jSlider16.setOrientation(javax.swing.JSlider.VERTICAL);
        jSlider16.setPaintLabels(true);
        jSlider16.setPaintTicks(true);
        jSlider16.setSnapToTicks(true);
        jSlider16.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = java.awt.GridBagConstraints.REMAINDER;
        jPanel1.add(jSlider16, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        jPanel1.add(smallSlider, gridBagConstraints);

        smallTickedSlider.setMajorTickSpacing(50);
        smallTickedSlider.setMinorTickSpacing(10);
        smallTickedSlider.setPaintTicks(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        jPanel1.add(smallTickedSlider, gridBagConstraints);

        smallLabeledSlider.setMajorTickSpacing(50);
        smallLabeledSlider.setMinorTickSpacing(10);
        smallLabeledSlider.setPaintLabels(true);
        smallLabeledSlider.setSnapToTicks(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        jPanel1.add(smallLabeledSlider, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(jPanel1, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSlider horizontalLabeledSlider;
    private javax.swing.JSlider horizontalTickedLabeledSlider;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSlider jSlider10;
    private javax.swing.JSlider jSlider11;
    private javax.swing.JSlider jSlider13;
    private javax.swing.JSlider jSlider15;
    private javax.swing.JSlider jSlider16;
    private javax.swing.JSlider jSlider7;
    private javax.swing.JSlider jSlider8;
    private javax.swing.JSlider smallLabeledSlider;
    private javax.swing.JSlider smallSlider;
    private javax.swing.JSlider smallTickedSlider;
    private javax.swing.JSlider verticalLabeledSlider;
    // End of variables declaration//GEN-END:variables
    
}
