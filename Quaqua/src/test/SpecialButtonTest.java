/*
 * @(#)ButtonTest.java  1.0  
 *
 * Copyright (c) 2004 Werner Randelshofer, Switzerland.
 * You may not use, copy or modify this file, except in compliance with the
 * accompanying license terms.
 */

package test;

import ch.randelshofer.quaqua.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
/**
 * ButtonTest.
 *
 * @author  Werner Randelshofer
 * @version $Id$
 */
public class SpecialButtonTest extends javax.swing.JPanel {
    
    /** Creates new form. */
    public SpecialButtonTest() {
        initComponents();
        bevelButton.putClientProperty("JButton.buttonType", "bevel");
        colorWellButton.putClientProperty("JButton.buttonType", "colorWell");
        gradientButton.putClientProperty("JButton.buttonType", "gradient");
        helpButton.putClientProperty("JButton.buttonType", "help");
        recessedButton.putClientProperty("JButton.buttonType", "recessed");
        roundRectButton.putClientProperty("JButton.buttonType", "roundRect");
        squareButton.putClientProperty("JButton.buttonType", "square");
        tableHeaderButton.putClientProperty("JButton.buttonType", "tableHeader");
                texturedButton.putClientProperty("JButton.buttonType", "textured");

        colorWellButton.setBackground(Color.white);
        
    }
    /*
    public void paint(Graphics g) {
        long start = System.currentTimeMillis();
        super.paint(g);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }*/
    
    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(QuaquaManager.getLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        JFrame f = new JFrame("Quaqua Special Button Test");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().add(new SpecialButtonTest());
        ((JComponent) f.getContentPane()).setBorder(new EmptyBorder(9,17,17,17));
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

        sizeVariantGroup = new javax.swing.ButtonGroup();
        squareButton = new javax.swing.JButton();
        squareLabel = new javax.swing.JLabel();
        bevelButton = new javax.swing.JButton();
        bevelLabel = new javax.swing.JLabel();
        colorWellButton = new javax.swing.JButton();
        colorWellLabel = new javax.swing.JLabel();
        tableHeaderButton = new javax.swing.JButton();
        tableHeaderLabel = new javax.swing.JLabel();
        helpButton = new javax.swing.JButton();
        helpLabel = new javax.swing.JLabel();
        gradientButton = new javax.swing.JButton();
        gradientLabel = new javax.swing.JLabel();
        texturedButton = new javax.swing.JButton();
        texturedLabel = new javax.swing.JLabel();
        roundRectButton = new javax.swing.JButton();
        recessedButton = new javax.swing.JButton();
        roundRectLabel = new javax.swing.JLabel();
        recessedLabel = new javax.swing.JLabel();
        coloredButton = new javax.swing.JButton();
        coloredLabel = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        regularRadio = new javax.swing.JRadioButton();
        smallRadio = new javax.swing.JRadioButton();
        miniRadio = new javax.swing.JRadioButton();
        springPanel = new javax.swing.JPanel();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(16, 17, 17, 17));
        setLayout(new java.awt.GridBagLayout());

        squareButton.setText("Ångström H");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        add(squareButton, gridBagConstraints);

        squareLabel.setText("Square");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        add(squareLabel, gridBagConstraints);

        bevelButton.setText("Ångström H");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        add(bevelButton, gridBagConstraints);

        bevelLabel.setText("Bevel");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        add(bevelLabel, gridBagConstraints);

        colorWellButton.setText("Ångström H");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        add(colorWellButton, gridBagConstraints);

        colorWellLabel.setText("Color Well");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        add(colorWellLabel, gridBagConstraints);

        tableHeaderButton.setText("Ångström H");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        add(tableHeaderButton, gridBagConstraints);

        tableHeaderLabel.setText("Table Header");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        add(tableHeaderLabel, gridBagConstraints);

        helpButton.setText("Ångström H");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        add(helpButton, gridBagConstraints);

        helpLabel.setText("Help");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        add(helpLabel, gridBagConstraints);

        gradientButton.setText("Ångström H");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        add(gradientButton, gridBagConstraints);

        gradientLabel.setText("Gradient");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        add(gradientLabel, gridBagConstraints);

        texturedButton.setText("Ångström H");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        add(texturedButton, gridBagConstraints);

        texturedLabel.setText("Textured");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        add(texturedLabel, gridBagConstraints);

        roundRectButton.setText("Ångström H");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        add(roundRectButton, gridBagConstraints);

        recessedButton.setText("Ångström H");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        add(recessedButton, gridBagConstraints);

        roundRectLabel.setText("Round Rect");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        add(roundRectLabel, gridBagConstraints);

        recessedLabel.setText("Recessed");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        add(recessedLabel, gridBagConstraints);

        coloredButton.setBackground(new java.awt.Color(181, 212, 107));
        coloredButton.setText("Ångström H");
        coloredButton.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        add(coloredButton, gridBagConstraints);

        coloredLabel.setText("Colored");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        add(coloredLabel, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(8, 0, 8, 0);
        add(jSeparator1, gridBagConstraints);

        sizeVariantGroup.add(regularRadio);
        regularRadio.setSelected(true);
        regularRadio.setText("Regular");
        regularRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sizeVariantPerformed(evt);
            }
        });
        jPanel1.add(regularRadio);

        sizeVariantGroup.add(smallRadio);
        smallRadio.setText("Small");
        smallRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sizeVariantPerformed(evt);
            }
        });
        jPanel1.add(smallRadio);

        sizeVariantGroup.add(miniRadio);
        miniRadio.setText("Mini");
        miniRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sizeVariantPerformed(evt);
            }
        });
        jPanel1.add(miniRadio);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 2;
        add(jPanel1, gridBagConstraints);

        springPanel.setLayout(new java.awt.BorderLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 99;
        gridBagConstraints.weighty = 1.0;
        add(springPanel, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void sizeVariantPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sizeVariantPerformed
       String sizeVariant="regular";
       if (regularRadio.isSelected()) sizeVariant="regular";
       else if (smallRadio.isSelected()) sizeVariant="small";
      else if (miniRadio.isSelected()) sizeVariant="mini";
       
       for (Component c:getComponents()) {
           JComponent jc=(JComponent)c;
           jc.putClientProperty("JComponent.sizeVariant", sizeVariant);
       }
       
    }//GEN-LAST:event_sizeVariantPerformed
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bevelButton;
    private javax.swing.JLabel bevelLabel;
    private javax.swing.JButton colorWellButton;
    private javax.swing.JLabel colorWellLabel;
    private javax.swing.JButton coloredButton;
    private javax.swing.JLabel coloredLabel;
    private javax.swing.JButton gradientButton;
    private javax.swing.JLabel gradientLabel;
    private javax.swing.JButton helpButton;
    private javax.swing.JLabel helpLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JRadioButton miniRadio;
    private javax.swing.JButton recessedButton;
    private javax.swing.JLabel recessedLabel;
    private javax.swing.JRadioButton regularRadio;
    private javax.swing.JButton roundRectButton;
    private javax.swing.JLabel roundRectLabel;
    private javax.swing.ButtonGroup sizeVariantGroup;
    private javax.swing.JRadioButton smallRadio;
    private javax.swing.JPanel springPanel;
    private javax.swing.JButton squareButton;
    private javax.swing.JLabel squareLabel;
    private javax.swing.JButton tableHeaderButton;
    private javax.swing.JLabel tableHeaderLabel;
    private javax.swing.JButton texturedButton;
    private javax.swing.JLabel texturedLabel;
    // End of variables declaration//GEN-END:variables
    
}
