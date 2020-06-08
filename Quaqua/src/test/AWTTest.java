/*
 * @(#)AWTTest.java
 * Quaqua Look and Feel. Copyright © 2020 Werner Randelshofer, Switzerland. MIT License.
 */

/*
 * AWTTest.java
 *
 * Created on February 21, 2006, 7:49 PM
 */

package test;

/**
 *
 * @author  werni
 */
public class AWTTest extends java.awt.Frame {

    /** Creates new form AWTTest */
    public AWTTest() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        button1 = new java.awt.Button();
        scrollPane1 = new java.awt.ScrollPane();
        panel1 = new java.awt.Panel();
        button2 = new java.awt.Button();
        label1 = new java.awt.Label();
        textField1 = new java.awt.TextField();
        checkbox1 = new java.awt.Checkbox();
        choice1 = new java.awt.Choice();

        FormListener formListener = new FormListener();

        addWindowListener(formListener);

        button1.setLabel("button1");
        add(button1, java.awt.BorderLayout.NORTH);

        button2.setLabel("button2");
        panel1.add(button2);

        label1.setText("label1");
        panel1.add(label1);

        textField1.setText("textField1");
        panel1.add(textField1);

        checkbox1.setLabel("checkbox1");
        panel1.add(checkbox1);

        panel1.add(choice1);

        scrollPane1.add(panel1);

        add(scrollPane1, java.awt.BorderLayout.CENTER);

        pack();
    }

    // Code for dispatching events from components to event handlers.

    private class FormListener implements java.awt.event.WindowListener {
        public void windowActivated(java.awt.event.WindowEvent evt) {
        }

        public void windowClosed(java.awt.event.WindowEvent evt) {
        }

        public void windowClosing(java.awt.event.WindowEvent evt) {
            if (evt.getSource() == AWTTest.this) {
                AWTTest.this.exitForm(evt);
            }
        }

        public void windowDeactivated(java.awt.event.WindowEvent evt) {
        }

        public void windowDeiconified(java.awt.event.WindowEvent evt) {
        }

        public void windowIconified(java.awt.event.WindowEvent evt) {
        }

        public void windowOpened(java.awt.event.WindowEvent evt) {
        }
    }//GEN-END:initComponents

    /** Exit the Application */
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        System.exit(0);
    }//GEN-LAST:event_exitForm

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        new AWTTest().setVisible(true);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button button1;
    private java.awt.Button button2;
    private java.awt.Checkbox checkbox1;
    private java.awt.Choice choice1;
    private java.awt.Label label1;
    private java.awt.Panel panel1;
    private java.awt.ScrollPane scrollPane1;
    private java.awt.TextField textField1;
    // End of variables declaration//GEN-END:variables

}
