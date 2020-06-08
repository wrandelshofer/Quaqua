/*
 * @(#)ColorChooserTest.java
 * Quaqua Look and Feel. Copyright © 2020 Werner Randelshofer, Switzerland. MIT License.
 */
package test;

import java.awt.*;
import java.awt.event.AWTEventListener;
import java.awt.event.MouseEvent;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * ColorChooserTest.
 *
 * @author  Werner Randelshofer
 * @version $Id$
 */
public class ColorChooserTest extends javax.swing.JPanel {

    private JColorChooser chooser;
    private Color color = Color.white;
    private JPopupMenu popupMenu;

    /** Creates new form. */
    public ColorChooserTest() {
        initComponents();
        dialogButton.putClientProperty("Quaqua.Button.style", "colorWell");
        dialogButton.setBackground(color);
        popupButton.putClientProperty("Quaqua.Button.style", "colorWell");
        popupButton.setBackground(color);
    }

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(TestManager.getLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        JFrame f = new JFrame("Quaqua ColorChooser Test");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().add(new ColorChooserTest());
        ((JComponent) f.getContentPane()).setBorder(new EmptyBorder(9, 17, 17, 17));
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

        dialogButton = new javax.swing.JButton();
        dialogLabel = new javax.swing.JLabel();
        popupButton = new javax.swing.JButton();
        popupLabel = new javax.swing.JLabel();

        setLayout(new java.awt.GridBagLayout());

        dialogButton.setText("   ");
        dialogButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dialogActionPerformed(evt);
            }
        });
        add(dialogButton, new java.awt.GridBagConstraints());

        dialogLabel.setText("Color Chooser Dialog");
        add(dialogLabel, new java.awt.GridBagConstraints());

        popupButton.setText("   ");
        popupButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                popupActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 1;
        add(popupButton, gridBagConstraints);

        popupLabel.setText("Color Chooser Popup");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 1;
        add(popupLabel, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void dialogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dialogActionPerformed
        // TODO add your handling code here:
        try {
            //      System.out.println("chooserButtonActionPerformed "+evt);
            if (chooser == null) {
                chooser = new JColorChooser();
                /*
                chooser.setSelectionModel(new DefaultColorSelectionModel() {
                public void setSelectedColor(Color c) {
                new Throwable().printStackTrace();
                super.setSelectedColor(c);
                }
                }
                );*/
                //JOptionPane.showMessageDialog(this, "Made a new chooser");
            }
            color = chooser.showDialog(this, "Color Chooser", color);
            dialogButton.setBackground(color);
        } catch (Throwable t) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            JOptionPane.showMessageDialog(this, "JColorChooser Failed " + sw.toString());
            t.printStackTrace();
        }
    }//GEN-LAST:event_dialogActionPerformed



    private void popupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_popupActionPerformed
        if (popupMenu == null) {
            // Create a JPopupMenu which can hold a component which in turn
            // creates JPopupMenus.
            popupMenu = new JPopupMenu() {
                 class MouseGrabber implements AWTEventListener, Serializable {

                    public void eventDispatched(AWTEvent ev) {
                        if (!(ev instanceof MouseEvent) || !(ev.getSource() instanceof Component)) {
                            // We are interested in MouseEvents only
                            return;
                        }
                        MouseEvent me = (MouseEvent) ev;
                        Component src = (Component) ev.getSource();

                        if (me.getID() == MouseEvent.MOUSE_PRESSED) {
                            // Close popup if the mouse press occured on a component which is
                            // not descending from this popup menu, but has the same
                            // window ancestor.
                            if (!SwingUtilities.isDescendingFrom(src, popupMenu)
                                    && SwingUtilities.getWindowAncestor(src)
                                    == SwingUtilities.getWindowAncestor(popupMenu.getInvoker())) {
                                JLayeredPane srcLP = (JLayeredPane) SwingUtilities.getAncestorOfClass(JLayeredPane.class, src);
                                Component srcLPChild = src;
                                while (srcLPChild != null && srcLPChild.getParent() != srcLP) {
                                    srcLPChild = srcLPChild.getParent();
                                }
                                if (srcLPChild == null || srcLP.getLayer(srcLPChild) < JLayeredPane.POPUP_LAYER) {

                                    popupMenu.setVisible(false);
                                }
                            }
                        } else if (me.getID()==MouseEvent.MOUSE_CLICKED //
                                && me.getClickCount()==2) {
                            if (SwingUtilities.isDescendingFrom(src, popupMenu)) {
                                popupMenu.setVisible(false);
                            }
                        }
                    }
                };

                private MouseGrabber mouseGrabber = new MouseGrabber();

                @Override
                public void menuSelectionChanged(boolean isIncluded) {
                    // Don't let the MenuSelectionManager hide this popup.
                    return;
                }

                @Override
                public void setVisible(boolean newValue) {
                    // Attach/detach AWTEventListener on "visible" property change.
                    if (isVisible() != newValue) {
                        if (newValue) {
                            Toolkit.getDefaultToolkit().addAWTEventListener(mouseGrabber, AWTEvent.MOUSE_EVENT_MASK);
                        } else {
                            Toolkit.getDefaultToolkit().removeAWTEventListener(mouseGrabber);
                        }
                        super.setVisible(newValue);
                    }
                }
            };

            popupMenu.putClientProperty("Quaqua.PopupMenu.windowAlpha", 1.0f);
            final JColorChooser c = new JColorChooser();
            c.setPreviewPanel(new JPanel());
            popupMenu.add(c);
            c.getSelectionModel().addChangeListener(new ChangeListener() {

                public void stateChanged(ChangeEvent e) {
                    popupButton.setBackground(c.getColor());
                }
            });


        }
        popupMenu.show(popupButton, 0, popupButton.getHeight());

    }//GEN-LAST:event_popupActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton dialogButton;
    private javax.swing.JLabel dialogLabel;
    private javax.swing.JButton popupButton;
    private javax.swing.JLabel popupLabel;
    // End of variables declaration//GEN-END:variables
}
