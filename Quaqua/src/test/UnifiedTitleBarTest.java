/*
 * UnifiedTitleBarTest.java
 *
 * Created on August 10, 2008, 6:18 PM
 */

package test;

import ch.randelshofer.quaqua.QuaquaManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author  werni
 */
public class UnifiedTitleBarTest extends javax.swing.JFrame {

    /** Creates new form UnifiedTitleBarTest */
    public UnifiedTitleBarTest() {
        getRootPane().putClientProperty("apple.awt.brushMetalLook", Boolean.TRUE);
        initComponents();
        scrollPane.setBorder(null);
        try {

            wait(4, 4);
        } catch (InterruptedException ex) {
            Logger.getLogger(UnifiedTitleBarTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
   // @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        toolBar = new javax.swing.JToolBar();
        button1 = new javax.swing.JButton();
        button2 = new javax.swing.JButton();
        scrollPane = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        toolBar.setFloatable(false);
        toolBar.setRollover(true);

        button1.setText("Undo");
        button1.setFocusable(false);
        button1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        button1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBar.add(button1);

        button2.setText("Redo");
        button2.setFocusable(false);
        button2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        button2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBar.add(button2);

        getContentPane().add(toolBar, java.awt.BorderLayout.PAGE_START);

        scrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        scrollPane.setViewportView(jTextArea1);

        getContentPane().add(scrollPane, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(QuaquaManager.getLookAndFeel());
                } catch (Exception ex) {
                    // do nothing
                }
                new UnifiedTitleBarTest().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button1;
    private javax.swing.JButton button2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JToolBar toolBar;
    // End of variables declaration//GEN-END:variables

}
