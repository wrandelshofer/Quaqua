/*
 * @(#)BorderTest.java
 * Quaqua Look and Feel. Copyright © 2020 Werner Randelshofer, Switzerland. MIT License.
 */

package test;

import javax.swing.border.TitledBorder;

/**
 * BorderTest.
 *
 * @author  Werner Randelshofer
 * @version 1.0 September 25, 2005 Created.
 */
public class BorderTest extends javax.swing.JPanel {

    /**
     * Creates a new instance.
     */
    public BorderTest() {
        initComponents();
        groupBoxPanel1.setOpaque(true);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        groupBoxPanel1 = new javax.swing.JPanel();
        groupBoxPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        groupBoxPanel3 = new javax.swing.JPanel();
        groupBoxPanel4 = new javax.swing.JPanel();

        setLayout(new java.awt.GridLayout(0, 2));

        groupBoxPanel1.setLayout(new java.awt.BorderLayout());

        groupBoxPanel1.setBorder(new javax.swing.border.TitledBorder("Titled Border"));
        add(groupBoxPanel1);

        groupBoxPanel2.setLayout(new java.awt.BorderLayout());

        groupBoxPanel2.setBorder(new javax.swing.border.TitledBorder(null, "Outer Border above Top", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.ABOVE_TOP));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel1.setBorder(new javax.swing.border.TitledBorder(null, "Inner Border", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Grande", 0, 11)));
        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel2.setBorder(new javax.swing.border.TitledBorder(""));
        jLabel1.setText("jLabel1");
        jPanel2.add(jLabel1, java.awt.BorderLayout.SOUTH);

        jTextField1.setText("jTextField1");
        jPanel2.add(jTextField1, java.awt.BorderLayout.NORTH);

        jPanel1.add(jPanel2, java.awt.BorderLayout.CENTER);

        groupBoxPanel2.add(jPanel1, java.awt.BorderLayout.CENTER);

        add(groupBoxPanel2);

        groupBoxPanel3.setBorder(new javax.swing.border.TitledBorder(null, "Title centered below top", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BELOW_TOP));
        add(groupBoxPanel3);

        groupBoxPanel4.setBorder(new javax.swing.border.TitledBorder(null, "Title below border", javax.swing.border.TitledBorder.RIGHT, javax.swing.border.TitledBorder.BELOW_BOTTOM));
        add(groupBoxPanel4);

    }//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel groupBoxPanel1;
    private javax.swing.JPanel groupBoxPanel2;
    private javax.swing.JPanel groupBoxPanel3;
    private javax.swing.JPanel groupBoxPanel4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables

}
