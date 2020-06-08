/*
 * @(#)SplitPaneTest2.java
 * Quaqua Look and Feel. Copyright © 2020 Werner Randelshofer, Switzerland. MIT License.
 */

package test;

import javax.swing.*;
import javax.swing.border.*;

/**
 * SplitPaneTest2.
 *
 * @author  Werner Randelshofer
 * @version 1.0 November 28, 2005 Created.
 */
public class SplitPaneTest2 extends javax.swing.JPanel {

    /**
     * Creates a new instance.
     */
    public SplitPaneTest2() {
        initComponents();

        horizontalSplitPane.putClientProperty("Quaqua.SplitPane.style","bar");
        horizontalSplitPane.setDividerSize(1);
        verticalSplitPane.putClientProperty("Quaqua.SplitPane.style","bar");

        messageScrollPane.setBorder(new EmptyBorder(0,0,0,0));
        messagesScrollPane.setBorder(new EmptyBorder(0,0,0,0));
        foldersScrollPane.setBorder(new EmptyBorder(0,0,0,0));
        /*
        horizontalSplitPane.setContinuousLayout(true);
        verticalSplitPane.setContinuousLayout(true);
         */

    }

    public static void main(String[] args) {
        try {
       UIManager.setLookAndFeel(TestManager.getLookAndFeelClassName());
        } catch (Exception e) {

        }

        JFrame f = new JFrame("Inbox (1 message)");
        f.getContentPane().add(new SplitPaneTest2());
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(600,400);
        f.setVisible(true);
    }


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        horizontalSplitPane = new javax.swing.JSplitPane();
        foldersScrollPane = new javax.swing.JScrollPane();
        foldersList = new javax.swing.JList();
        verticalSplitPane = new javax.swing.JSplitPane();
        messagesScrollPane = new javax.swing.JScrollPane();
        messagesTable = new javax.swing.JTable();
        messageScrollPane = new javax.swing.JScrollPane();
        messageTextArea = new javax.swing.JTextArea();

        setLayout(new java.awt.BorderLayout());

        horizontalSplitPane.setBorder(null);
        foldersScrollPane.setMinimumSize(new java.awt.Dimension(0, 0));
        foldersList.setBackground(new java.awt.Color(230, 237, 246));
        foldersList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "  Inbox  ", "  Outbox  ", "  Drafts  ", "  Sent  ", "  Trash  " };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        foldersList.setFixedCellHeight(24);
        foldersList.setSelectionBackground(new java.awt.Color(159, 176, 200));
        foldersScrollPane.setViewportView(foldersList);

        horizontalSplitPane.setLeftComponent(foldersScrollPane);

        verticalSplitPane.setBorder(null);
        verticalSplitPane.setDividerLocation(50);
        verticalSplitPane.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        verticalSplitPane.setMinimumSize(new java.awt.Dimension(0, 0));
        messagesScrollPane.setBorder(null);
        messagesScrollPane.setVerticalScrollBarPolicy(javax.swing.JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        messagesScrollPane.setMinimumSize(new java.awt.Dimension(0, 0));
        messagesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Werner Randelshofer", "Quaqua 3.5", "28.11.05"}
            },
            new String [] {
                "From", "Subject", "Date Received"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        messagesScrollPane.setViewportView(messagesTable);

        verticalSplitPane.setLeftComponent(messagesScrollPane);

        messageScrollPane.setBorder(null);
        messageScrollPane.setVerticalScrollBarPolicy(javax.swing.JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        messageScrollPane.setMinimumSize(new java.awt.Dimension(0, 0));
        messageTextArea.setLineWrap(true);
        messageTextArea.setText("The Quaqua Look and Feel 3.5 supports split panes with two different styles.\nThe \"plain\" style only draws a round knob at the center. \nThe \"bar\" style draws a beveled bar.\n\nThis demo screenshot features two \"bar\" styled split panes.\nThe split pane, which separates the blue folders list on the left from the other components, has a divider width of 1.\nThe split pane, which separates the messages on the top from this text, has a regular divider width of 10.\n\nAll scroll panes on this screenshot have an empty border of size zero. Their borders are unwanted, because the split panes and the window borders already do a great job for visually tiling the window.");
        messageTextArea.setWrapStyleWord(true);
        messageScrollPane.setViewportView(messageTextArea);

        verticalSplitPane.setRightComponent(messageScrollPane);

        horizontalSplitPane.setRightComponent(verticalSplitPane);

        add(horizontalSplitPane, java.awt.BorderLayout.CENTER);

    }//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList foldersList;
    private javax.swing.JScrollPane foldersScrollPane;
    private javax.swing.JSplitPane horizontalSplitPane;
    private javax.swing.JScrollPane messageScrollPane;
    private javax.swing.JTextArea messageTextArea;
    private javax.swing.JScrollPane messagesScrollPane;
    private javax.swing.JTable messagesTable;
    private javax.swing.JSplitPane verticalSplitPane;
    // End of variables declaration//GEN-END:variables

}
