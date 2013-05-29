/*
 * @(#)LayoutAHIG16_17Test.java  
 *
 * Copyright (c) 2007-2013 Werner Randelshofer, Switzerland.
 * All rights reserved.
 *
 * You may not use, copy or modify this file, except in compliance with the
 * license agreement you entered into with Werner Randelshofer.
 * For details see accompanying license terms.
 */

package test;

import ch.randelshofer.quaqua.*;
import ch.randelshofer.quaqua.panther.Quaqua15PantherLookAndFeel;
import java.awt.Dimension;
import javax.swing.*;
import org.jdesktop.layout.*;

/**
 * LayoutAHIG16_17Test: "Grouping with White Space".
 *
 * http://developer.apple.com/documentation/UserExperience/Conceptual/OSXHIGuidelines/XHIGLayout/chapter_20_section_4.html#//apple_ref/doc/uid/TP30000360-SW2
 *
 * @author Werner Randelshofer
 * @version $Id$
 */
public class LayoutAHIG16_17Test extends javax.swing.JPanel {
    /** Creates new instance. */
    public LayoutAHIG16_17Test() {
        initComponents();
        windowColorWellButton.putClientProperty("Quaqua.Button.style", "colorWell");
        drawerColorWellButton.putClientProperty("Quaqua.Button.style", "colorWell");
        Dimension dim = sizeSlider.getPreferredSize();
        dim.width = 120;
        sizeSlider.setPreferredSize(dim);
         dim = okayButton.getPreferredSize();
        dim.width = Math.max(dim.width, cancelButton.getPreferredSize().width);
        okayButton.setPreferredSize(dim);
        /*
        UIManager.put("Quaqua.Debug.showClipBounds", true);
        UIManager.put("Quaqua.Debug.showVisualBounds", true);
         */
    }
    
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new Quaqua15PantherLookAndFeel());
            // UIManager.put("Quaqua.Debug.showVisualBounds", true);
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }
        JFrame f = new JFrame("Imagetype Changer Preferences");
        f.add(new LayoutAHIG16_17Test());
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setVisible(true);
    }
    
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        showGroup = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        generalPanel = new javax.swing.JPanel();
        thumbnailsLabel = new javax.swing.JLabel();
        imageAndNameRadioButton = new javax.swing.JRadioButton();
        imageOnlyRadioButton = new javax.swing.JRadioButton();
        nameOnlyRadioButton = new javax.swing.JRadioButton();
        sizePanel = new javax.swing.JPanel();
        sizeLabel = new javax.swing.JLabel();
        sizeSlider = new javax.swing.JSlider();
        loadAllAutomaticallyCheckBox = new javax.swing.JCheckBox();
        backgroundColorsLabel = new javax.swing.JLabel();
        backgroundColorsPanel = new javax.swing.JPanel();
        windowLabel = new javax.swing.JLabel();
        windowColorWellButton = new javax.swing.JButton();
        drawerLabel = new javax.swing.JLabel();
        drawerColorWellButton = new javax.swing.JButton();
        imagesPanel = new javax.swing.JPanel();
        pdfPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        cancelButton = new javax.swing.JButton();
        okayButton = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(9, 17, 17, 17));
        setRequestFocusEnabled(false);
        setLayout(new java.awt.BorderLayout());

        generalPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(9, 17, 17, 17));
        generalPanel.setLayout(new java.awt.GridBagLayout());

        thumbnailsLabel.setFont(new java.awt.Font("Lucida Grande", 1, 13));
        thumbnailsLabel.setText("Thumbnails:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        generalPanel.add(thumbnailsLabel, gridBagConstraints);

        showGroup.add(imageAndNameRadioButton);
        imageAndNameRadioButton.setSelected(true);
        imageAndNameRadioButton.setText("Show image and name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 0);
        generalPanel.add(imageAndNameRadioButton, gridBagConstraints);

        showGroup.add(imageOnlyRadioButton);
        imageOnlyRadioButton.setText("Show image only");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 0);
        generalPanel.add(imageOnlyRadioButton, gridBagConstraints);

        showGroup.add(nameOnlyRadioButton);
        nameOnlyRadioButton.setText("Show name only");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 0);
        generalPanel.add(nameOnlyRadioButton, gridBagConstraints);

        sizePanel.setLayout(new java.awt.GridBagLayout());

        sizeLabel.setText("Size:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        sizePanel.add(sizeLabel, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 0);
        sizePanel.add(sizeSlider, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        generalPanel.add(sizePanel, gridBagConstraints);

        loadAllAutomaticallyCheckBox.setText("Automatically load all thumbnails");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        generalPanel.add(loadAllAutomaticallyCheckBox, gridBagConstraints);

        backgroundColorsLabel.setFont(new java.awt.Font("Lucida Grande", 1, 13));
        backgroundColorsLabel.setText("Colors:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.RELATIVE;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        generalPanel.add(backgroundColorsLabel, gridBagConstraints);

        backgroundColorsPanel.setLayout(new java.awt.GridBagLayout());

        windowLabel.setFont(new java.awt.Font("Lucida Grande", 0, 11));
        windowLabel.setText("Window background:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        backgroundColorsPanel.add(windowLabel, gridBagConstraints);

        windowColorWellButton.setBackground(new java.awt.Color(255, 255, 255));
        windowColorWellButton.setFont(new java.awt.Font("Lucida Grande", 0, 11));
        windowColorWellButton.setText("   ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        backgroundColorsPanel.add(windowColorWellButton, gridBagConstraints);

        drawerLabel.setFont(new java.awt.Font("Lucida Grande", 0, 11));
        drawerLabel.setText("Drawer background");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 16, 0, 0);
        backgroundColorsPanel.add(drawerLabel, gridBagConstraints);

        drawerColorWellButton.setBackground(new java.awt.Color(204, 204, 204));
        drawerColorWellButton.setFont(new java.awt.Font("Lucida Grande", 0, 11));
        drawerColorWellButton.setText("   ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        backgroundColorsPanel.add(drawerColorWellButton, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(-3, 0, 0, 0);
        generalPanel.add(backgroundColorsPanel, gridBagConstraints);

        jTabbedPane1.addTab("General", generalPanel);
        jTabbedPane1.addTab("Images", imagesPanel);
        jTabbedPane1.addTab("PDF", pdfPanel);

        add(jTabbedPane1, java.awt.BorderLayout.CENTER);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        cancelButton.setText("Cancel");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        jPanel1.add(cancelButton, gridBagConstraints);

        okayButton.setText("OK");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(10, 6, 0, 0);
        jPanel1.add(okayButton, gridBagConstraints);

        add(jPanel1, java.awt.BorderLayout.SOUTH);
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel backgroundColorsLabel;
    private javax.swing.JPanel backgroundColorsPanel;
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton drawerColorWellButton;
    private javax.swing.JLabel drawerLabel;
    private javax.swing.JPanel generalPanel;
    private javax.swing.JRadioButton imageAndNameRadioButton;
    private javax.swing.JRadioButton imageOnlyRadioButton;
    private javax.swing.JPanel imagesPanel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JCheckBox loadAllAutomaticallyCheckBox;
    private javax.swing.JRadioButton nameOnlyRadioButton;
    private javax.swing.JButton okayButton;
    private javax.swing.JPanel pdfPanel;
    private javax.swing.ButtonGroup showGroup;
    private javax.swing.JLabel sizeLabel;
    private javax.swing.JPanel sizePanel;
    private javax.swing.JSlider sizeSlider;
    private javax.swing.JLabel thumbnailsLabel;
    private javax.swing.JButton windowColorWellButton;
    private javax.swing.JLabel windowLabel;
    // End of variables declaration//GEN-END:variables
    
}
