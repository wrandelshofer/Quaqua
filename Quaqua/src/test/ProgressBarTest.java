/*
 * @(#)ProgressBarTest.java
 * Quaqua Look and Feel. Copyright © 2020 Werner Randelshofer, Switzerland. MIT License.
 */

package test;

/**
 * ProgressBarTest.
 *
 * @author Werner Randelshofer
 * @version $Id$
 */
public class ProgressBarTest extends javax.swing.JPanel {

    /** Creates new form. */
    public ProgressBarTest() {
        initComponents();
        try {
        indeterminateBar1.setIndeterminate(true);
        indeterminateBar2.setIndeterminate(true);
        indeterminateBar3.setIndeterminate(true);
        } catch (NoSuchMethodError e) {
            remove(indeterminateBar1);
            remove(indeterminateBar2);
            remove(indeterminateBar3);
            remove(indeterminateLabel);
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

        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel1 = new javax.swing.JLabel();
        jProgressBar2 = new javax.swing.JProgressBar();
        jLabel2 = new javax.swing.JLabel();
        jProgressBar3 = new javax.swing.JProgressBar();
        jLabel3 = new javax.swing.JLabel();
        indeterminateLabel = new javax.swing.JLabel();
        indeterminateBar1 = new javax.swing.JProgressBar();
        jProgressBar12 = new javax.swing.JProgressBar();
        jProgressBar11 = new javax.swing.JProgressBar();
        jProgressBar10 = new javax.swing.JProgressBar();
        indeterminateBar2 = new javax.swing.JProgressBar();
        jLabel6 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jProgressBar16 = new javax.swing.JProgressBar();
        jProgressBar15 = new javax.swing.JProgressBar();
        jProgressBar14 = new javax.swing.JProgressBar();
        indeterminateBar3 = new javax.swing.JProgressBar();
        jLabel7 = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(16, 17, 17, 17));
        setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        add(jProgressBar1, gridBagConstraints);

        jLabel1.setText("0 Percent");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        add(jLabel1, gridBagConstraints);

        jProgressBar2.setValue(50);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        add(jProgressBar2, gridBagConstraints);

        jLabel2.setText("50 Percent");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        add(jLabel2, gridBagConstraints);

        jProgressBar3.setValue(100);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        add(jProgressBar3, gridBagConstraints);

        jLabel3.setText("100 Percent");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        add(jLabel3, gridBagConstraints);

        indeterminateLabel.setText("Indeterminate");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        add(indeterminateLabel, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        add(indeterminateBar1, gridBagConstraints);

        jProgressBar12.setStringPainted(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.insets = new java.awt.Insets(12, 0, 0, 0);
        add(jProgressBar12, gridBagConstraints);

        jProgressBar11.setValue(50);
        jProgressBar11.setStringPainted(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        add(jProgressBar11, gridBagConstraints);

        jProgressBar10.setValue(100);
        jProgressBar10.setStringPainted(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        add(jProgressBar10, gridBagConstraints);

        indeterminateBar2.setString("In Progress");
        indeterminateBar2.setStringPainted(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        add(indeterminateBar2, gridBagConstraints);

        jLabel6.setText("String Painted");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 20, 0, 0);
        add(jLabel6, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(12, 0, 12, 0);
        add(jSeparator1, gridBagConstraints);

        jProgressBar16.setFont(new java.awt.Font("Lucida Grande", 0, 11));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        add(jProgressBar16, gridBagConstraints);

        jProgressBar15.setFont(new java.awt.Font("Lucida Grande", 0, 11));
        jProgressBar15.setValue(50);
        jProgressBar15.setStringPainted(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        add(jProgressBar15, gridBagConstraints);

        jProgressBar14.setFont(new java.awt.Font("Lucida Grande", 0, 11));
        jProgressBar14.setValue(100);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        add(jProgressBar14, gridBagConstraints);

        indeterminateBar3.setFont(new java.awt.Font("Lucida Grande", 0, 11)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weighty = 1.0;
        add(indeterminateBar3, gridBagConstraints);

        jLabel7.setFont(new java.awt.Font("Lucida Grande", 0, 11));
        jLabel7.setText("Small");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        add(jLabel7, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar indeterminateBar1;
    private javax.swing.JProgressBar indeterminateBar2;
    private javax.swing.JProgressBar indeterminateBar3;
    private javax.swing.JLabel indeterminateLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JProgressBar jProgressBar10;
    private javax.swing.JProgressBar jProgressBar11;
    private javax.swing.JProgressBar jProgressBar12;
    private javax.swing.JProgressBar jProgressBar14;
    private javax.swing.JProgressBar jProgressBar15;
    private javax.swing.JProgressBar jProgressBar16;
    private javax.swing.JProgressBar jProgressBar2;
    private javax.swing.JProgressBar jProgressBar3;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables

}
