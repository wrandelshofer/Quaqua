/*
 * @(#)DefaultButtonTest.java
 * Quaqua Look and Feel. Copyright © 2020 Werner Randelshofer, Switzerland. MIT License.
 */

package test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * OkayButtonTest.
 *
 * @author Werner Randelshofer
 * @version 1.0 2013-06-04 Created.
 */
public class DefaultButtonTest extends javax.swing.JPanel {

    /** Creates new form OkayButtonTest */
    public DefaultButtonTest() {
        initComponents();
        okayButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new Throwable().printStackTrace();
            }
        });
    }



    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        okayButton = new javax.swing.JButton();

        FormListener formListener = new FormListener();

        addAncestorListener(formListener);
        setLayout(new java.awt.GridBagLayout());

        okayButton.setMnemonic('O');
        okayButton.setText("Okay");
        add(okayButton, new java.awt.GridBagConstraints());
    }

    // Code for dispatching events from components to event handlers.

    private class FormListener implements javax.swing.event.AncestorListener {
        FormListener() {}
        public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
            if (evt.getSource() == DefaultButtonTest.this) {
                DefaultButtonTest.this.formAncestorAdded(evt);
            }
        }

        public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
        }

        public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
        }
    }// </editor-fold>//GEN-END:initComponents

    private void formAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_formAncestorAdded
        SwingUtilities.getRootPane(this).setDefaultButton(okayButton);
    }//GEN-LAST:event_textFieldPerformed

    /** Exit the Application */
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        System.exit(0);
    }//GEN-LAST:event_formAncestorAdded

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        try {
            UIManager.setLookAndFeel(TestManager.getLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        new DefaultButtonTest().setVisible(true);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton okayButton;
    // End of variables declaration//GEN-END:variables

}
