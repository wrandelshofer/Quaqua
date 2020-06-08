/*
 * @(#)SheetTest.java
 * Quaqua Look and Feel. Copyright 2020 © Werner Randelshofer, Switzerland. MIT License.
 */

package qtest;

import ch.randelshofer.quaqua.*;

import java.awt.event.*;
import java.io.*;
import javax.swing.*;

/**
 * SheetTest.
 *
 * @author  Werner Randelshofer
 * @version $Id$
 */
public class SheetTest extends javax.swing.JPanel {
private JFileChooser saveFile;

    /** Creates new form. */
    public SheetTest() {
        initComponents();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                try {
                    UIManager.setLookAndFeel(QuaquaManager.getLookAndFeelClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                JFrame f = new JFrame("SheetTest");
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f.getContentPane().add(new SheetTest());
                f.pack();
                f.setVisible(true);
            }
        });
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        confirmSheetButton = new javax.swing.JButton();
        inputSheetButton = new javax.swing.JButton();
        messageSheetButton = new javax.swing.JButton();
        saveChangesSheetButton = new javax.swing.JButton();
        saveFileSheetButton = new javax.swing.JButton();
        messageSheetButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        FormListener formListener = new FormListener();

        setLayout(new java.awt.GridBagLayout());

        confirmSheetButton.setText("Confirm Sheet");
        confirmSheetButton.addActionListener(formListener);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 0;
        add(confirmSheetButton, gridBagConstraints);

        inputSheetButton.setText("Input Sheet");
        inputSheetButton.addActionListener(formListener);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 0;
        add(inputSheetButton, gridBagConstraints);

        messageSheetButton.setText("Message Sheet");
        messageSheetButton.addActionListener(formListener);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 0;
        add(messageSheetButton, gridBagConstraints);

        saveChangesSheetButton.setText("Save Changes Sheet");
        saveChangesSheetButton.addActionListener(formListener);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 1;
        add(saveChangesSheetButton, gridBagConstraints);

        saveFileSheetButton.setText("Save File Sheet");
        saveFileSheetButton.addActionListener(formListener);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 1;
        add(saveFileSheetButton, gridBagConstraints);

        messageSheetButton1.setText("Delayed Message Sheet");
        messageSheetButton1.addActionListener(formListener);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 1;
        add(messageSheetButton1, gridBagConstraints);

        jLabel1.setText("<html>Note: The Delayed Message Sheet appears after two seconds, so that you can deactivate the window in order to see the application icon bounce, when the application requests user attention.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        add(jLabel1, gridBagConstraints);
    }

    // Code for dispatching events from components to event handlers.

    private class FormListener implements java.awt.event.ActionListener {
        FormListener() {}
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            if (evt.getSource() == confirmSheetButton) {
                SheetTest.this.confirmSheet(evt);
            }
            else if (evt.getSource() == inputSheetButton) {
                SheetTest.this.inputSheet(evt);
            }
            else if (evt.getSource() == messageSheetButton) {
                SheetTest.this.messageSheet(evt);
            }
            else if (evt.getSource() == saveChangesSheetButton) {
                SheetTest.this.saveChangesSheet(evt);
            }
            else if (evt.getSource() == saveFileSheetButton) {
                SheetTest.this.saveFileSheet(evt);
            }
            else if (evt.getSource() == messageSheetButton1) {
                SheetTest.this.delayedMessageSheet(evt);
            }
        }
    }// </editor-fold>//GEN-END:initComponents

    private void delayedMessageSheet(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delayedMessageSheet
 Timer t = new Timer(2000, new ActionListener() {
           public void actionPerformed(ActionEvent evt) {
                       JSheet.showMessageSheet(SheetTest.this, "You have got a message.");
           }
       });
       t.setRepeats(false);
       t.start();
    }//GEN-LAST:event_delayedMessageSheet

    private void saveFileSheet(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveFileSheet
        if (saveFile == null) {
            JFileChooser chooser = new JFileChooser();
            chooser.setDialogTitle("This is some title text");
            //fc.setSelectedFile(new File("/Users/werni"));
            chooser.setCurrentDirectory(new File("/Users/werni"));
            chooser.setApproveButtonText("btnText");
            chooser.setApproveButtonMnemonic("btnText".charAt(0));
            chooser.setMultiSelectionEnabled(false);
            chooser.setAcceptAllFileFilterUsed(false);
            chooser.setFileHidingEnabled(true);
            //chooser.setSelectedFile(new File(resetTextField.getText()));
            saveFile = chooser;
        }
        //int option = saveFile.showSaveDialog(this);
        JSheet.showSaveSheet(saveFile, this, new SheetListener() {
            public void optionSelected(SheetEvent evt) {
                System.out.println("optionSelected "+ evt.getOption());
                //analyzeOption(saveFile, evt.getOption());
            }
        });
    }//GEN-LAST:event_saveFileSheet

    private void messageSheet(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_messageSheet

        JSheet.showMessageSheet(this, "You have got a message.");
    }//GEN-LAST:event_messageSheet

    private void inputSheet(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputSheet
        JSheet.showInputSheet(this, "Enter your name.", new SheetListener() {
            public void optionSelected(SheetEvent evt) {
                System.out.println("user entered "+evt.getInputValue());
            }
        });
    }//GEN-LAST:event_inputSheet

    private void confirmSheet(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmSheet
        JSheet.showConfirmSheet(this, "Do you want to confirm this dialog?", new SheetListener() {
            public void optionSelected(SheetEvent evt) {
                analyzeOption(evt.getOption());
            }
        });
    }//GEN-LAST:event_confirmSheet

    private void saveChangesSheet(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveChangesSheet
        // Example taken from
        // http://developer.apple.com/documentation/UserExperience/Conceptual/OSXHIGuidelines/XHIGWindows/chapter_17_section_6.html#//apple_ref/doc/uid/20000961-TPXREF11
        JOptionPane pane = new JOptionPane(
        "<html>"+
        "<head>"+
        "<style type=\"text/css\">"+
        "b { font: 13pt \"Lucida Grande\" }"+
        "p { font: 11pt \"Lucida Grande\"; margin-top: 8px }"+
        "</style>"+
        "</head>"+
        "<b>Do you want to save changes to this document<br>"+
        "before closing?</b><p>"+
        "If you don't save, your changes will be lost.",
        JOptionPane.WARNING_MESSAGE
        );
        Object[] options = { "Save", "Cancel", "Don't Save" };
        pane.setOptions(options);
        pane.setInitialValue(options[0]);
        pane.putClientProperty("Quaqua.OptionPane.destructiveOption", 2);
        JSheet.showSheet(pane, this, new SheetListener() {
            public void optionSelected(SheetEvent evt) {
                analyzeValue(evt.getValue());
            }
        });
    }//GEN-LAST:event_saveChangesSheet

    private void experimentalSheetChange(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_experimentalSheetChange
//        UIManager.put("Sheet.experimentalSheet", new Boolean(experimentalSheetCheckbox.isSelected()));
    }//GEN-LAST:event_experimentalSheetChange

    private void analyzeOption(int option) {
        switch (option) {
            case JOptionPane.CANCEL_OPTION:
                System.out.println("user canceled option pane");
                break;
            case JOptionPane.CLOSED_OPTION :
                System.out.println("user closed option pane");
                break;
            case JOptionPane.NO_OPTION :
                System.out.println("user chose no");
                break;
            case JOptionPane.OK_OPTION :
                //case JOptionPane.YES_OPTION : (same as OK_OPTION)
                System.out.println("user chose ok or yes");
                break;
            default :
                System.out.println("user chose "+option);
                break;
        }
    }
    private void analyzeValue(Object value) {
        System.out.println("user chose "+value);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton confirmSheetButton;
    private javax.swing.JButton inputSheetButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton messageSheetButton;
    private javax.swing.JButton messageSheetButton1;
    private javax.swing.JButton saveChangesSheetButton;
    private javax.swing.JButton saveFileSheetButton;
    // End of variables declaration//GEN-END:variables

}
