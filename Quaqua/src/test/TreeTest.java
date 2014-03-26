/*
 * @(#)TreeTest.java
 *
 * Copyright (c) 2004 Werner Randelshofer, Switzerland.
 * You may not use, copy or modify this file, except in compliance with the
 * accompanying license terms.
 */

package test;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.tree.*;

/**
 * TreeTest.
 *
 * @author  Werner Randelshofer
 * @version $Id$
 */
public class TreeTest extends javax.swing.JPanel {

    /** Creates new form. */
    public TreeTest() {
        initComponents();

        tree1.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        tree2.putClientProperty("Quaqua.Tree.style","striped");
        tree3.putClientProperty("Quaqua.Tree.style","sideBar");
        tree4.setSelectionRow(3);
        tree4.setEnabled(false);
    }
    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(TestManager.getLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        JFrame f = new JFrame("Quaqua Tree Test");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().add(new TreeTest());
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

        scrollPane1 = new javax.swing.JScrollPane();
        tree1 = new javax.swing.JTree();
        scrollPane2 = new javax.swing.JScrollPane();
        tree2 = new javax.swing.JTree();
        scrollPane3 = new javax.swing.JScrollPane();
        tree3 = new javax.swing.JTree();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tree4 = new javax.swing.JTree();
        textField = new javax.swing.JTextField();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(16, 17, 17, 17));
        setLayout(new java.awt.GridLayout(2, 2));

        tree1.setEditable(true);
        tree1.setRootVisible(false);
        tree1.setShowsRootHandles(true);
        scrollPane1.setViewportView(tree1);

        add(scrollPane1);

        tree2.setRootVisible(false);
        scrollPane2.setViewportView(tree2);

        add(scrollPane2);

        tree3.setRootVisible(false);
        tree3.setShowsRootHandles(true);
        scrollPane3.setViewportView(tree3);

        add(scrollPane3);

        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.Y_AXIS));

        tree4.setRootVisible(false);
        tree4.setShowsRootHandles(true);
        jScrollPane1.setViewportView(tree4);

        jPanel1.add(jScrollPane1);

        textField.setText("Ångström");
        textField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldActionPerformed(evt);
            }
        });
        jPanel1.add(textField);

        add(jPanel1);
    }// </editor-fold>//GEN-END:initComponents

    private void textFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane scrollPane1;
    private javax.swing.JScrollPane scrollPane2;
    private javax.swing.JScrollPane scrollPane3;
    private javax.swing.JTextField textField;
    private javax.swing.JTree tree1;
    private javax.swing.JTree tree2;
    private javax.swing.JTree tree3;
    private javax.swing.JTree tree4;
    // End of variables declaration//GEN-END:variables

}
