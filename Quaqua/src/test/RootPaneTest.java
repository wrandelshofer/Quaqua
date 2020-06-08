/*
 * @(#)RootPaneTest.java
 * Quaqua Look and Feel. Copyright © 2020 Werner Randelshofer, Switzerland. MIT License.
 */

package test;

import java.awt.event.*;
import javax.swing.*;
/**
 * RootPaneTest.
 *
 * @author  Werner Randelshofer
 * @version 1.0 June 6, 2005 Created.
 */
public class RootPaneTest extends javax.swing.JPanel {

    /**
     * Creates a new instance.
     */
    public RootPaneTest() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        windowModifiedCheckBox = new javax.swing.JCheckBox();

        setLayout(new java.awt.BorderLayout());

        windowModifiedCheckBox.setText("Window Modified");
        windowModifiedCheckBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                windowModifiedChanged(evt);
            }
        });

        add(windowModifiedCheckBox, java.awt.BorderLayout.CENTER);

    }//GEN-END:initComponents

    private void windowModifiedChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_windowModifiedChanged
       JRootPane rootPane = SwingUtilities.getRootPane(this);
       if (rootPane != null) {
           rootPane.putClientProperty("windowModified", evt.getStateChange() == ItemEvent.SELECTED);
       }
    }//GEN-LAST:event_windowModifiedChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox windowModifiedCheckBox;
    // End of variables declaration//GEN-END:variables

}
