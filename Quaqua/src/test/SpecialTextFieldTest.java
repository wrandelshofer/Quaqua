/*
 * @(#)TextFieldTest.java  1.0  13 February 2005
 *
 * Copyright (c) 2004 Werner Randelshofer, Switzerland.
 * All rights reserved.
 *
 * You may not use, copy or modify this file, except in compliance with the
 * license agreement you entered into with Werner Randelshofer.
 * For details see accompanying license terms.
 */

package test;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JComponent;
import javax.swing.text.JTextComponent;

/**
 * TextFieldTest.
 *
 * @author  Werner Randelshofer
 * @version 1.0  13 February 2005  Created.
 */
public class SpecialTextFieldTest extends javax.swing.JPanel {
    
    /** Creates new form. */
    public SpecialTextFieldTest() {
        initComponents();
        
        searchField.putClientProperty("Quaqua.TextField.style","search");
        smallSearchField.putClientProperty("Quaqua.TextField.style","search");
        searchField.putClientProperty("JTextField.variant","search");
        smallSearchField.putClientProperty("JTextField.variant","search");
        
        
   
        /*
        JPopupMenu m = new JPopupMenu();
        m.add(new DefaultEditorKit.CutAction());
        m.add(new DefaultEditorKit.CopyAction());
        m.add(new DefaultEditorKit.PasteAction());
        jTextField1.setComponentPopupMenu(m);
         */
        
        // Prevent text components from becoming too small in GridBagLayout
        for (Component c : getComponents()) {
            if (c instanceof JTextComponent) {
                c.setMinimumSize(c.getPreferredSize());
            }
        }
        
        
        // Try to get a better layout with J2SE6
        try {
            int BASELINE_LEADING = GridBagConstraints.class.getDeclaredField("BASELINE_LEADING").getInt(null);
            GridBagLayout layout = (GridBagLayout) getLayout();
            for (Component c : getComponents()) {
                GridBagConstraints gbc = layout.getConstraints(c);
                if (gbc.anchor == GridBagConstraints.WEST) {
                    gbc.anchor = BASELINE_LEADING;
                    layout.setConstraints(c, gbc);
                }
            }
        } catch (Exception ex) {
            // bail
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

        jTextField7 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        searchField = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        smallSearchField = new javax.swing.JTextField();
        smallSearchLabel = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(16, 17, 17, 17));
        setLayout(new java.awt.GridBagLayout());

        jTextField7.setText("Ångström H");
        jTextField7.setMargin(new java.awt.Insets(0, 20, 0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        add(jTextField7, gridBagConstraints);

        jLabel11.setText("Margin");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        add(jLabel11, gridBagConstraints);

        searchField.setText("Ångström H");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        add(searchField, gridBagConstraints);

        jLabel12.setText("Search Field");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        add(jLabel12, gridBagConstraints);

        smallSearchField.setFont(new java.awt.Font("Lucida Grande", 0, 11));
        smallSearchField.setText("Ångström H");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        add(smallSearchField, gridBagConstraints);

        smallSearchLabel.setFont(new java.awt.Font("Lucida Grande", 0, 11));
        smallSearchLabel.setText("Small Search Field");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        add(smallSearchLabel, gridBagConstraints);

        jTextField8.setBackground(new java.awt.Color(181, 212, 107));
        jTextField8.setText("Ångström H");
        jTextField8.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        add(jTextField8, gridBagConstraints);

        jLabel13.setText("Colored");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        add(jLabel13, gridBagConstraints);

        jTextField9.setBackground(new java.awt.Color(181, 212, 107));
        jTextField9.setText("Ångström H");
        jTextField9.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jTextField9.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        add(jTextField9, gridBagConstraints);

        jLabel14.setText("Borderless & Colored");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        add(jLabel14, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 99;
        gridBagConstraints.weighty = 1.0;
        add(jPanel1, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JTextField searchField;
    private javax.swing.JTextField smallSearchField;
    private javax.swing.JLabel smallSearchLabel;
    // End of variables declaration//GEN-END:variables
    
}
