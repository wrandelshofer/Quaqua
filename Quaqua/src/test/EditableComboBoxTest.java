/*
 * @(#)EditableComboBoxTest.java
 * Quaqua Look and Feel. Copyright © 2020 Werner Randelshofer, Switzerland. MIT License.
 */

package test;

import javax.swing.DefaultListCellRenderer;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

/**
 * EditableComboBoxTest.
 *
 * @author Werner Randelshofer
 * @version $Id$
 */
public class EditableComboBoxTest extends javax.swing.JPanel {
    private static class ColorIcon implements Icon {
        private Color color = Color.white;

        public void setColor(Color newValue) {
            color = newValue;
        }

        public Color getColor() {
            return color;
        }


        public int getIconHeight() {
            return 12;
        }

        public int getIconWidth() {
            return 24;
        }

        public void paintIcon(Component c, Graphics g, int x, int y) {
            if (color != null) {
                g.setColor(color);
                g.fillRect(x, y, getIconWidth(), getIconHeight());
                g.setColor(color.darker());
                g.drawRect(x, y, getIconWidth() - 1, getIconHeight() - 1);
            }
        }

    }

    private static class ColorComboCellRenderer extends DefaultListCellRenderer {
        private ColorIcon colorIcon = new ColorIcon();

        public ColorComboCellRenderer() {
            setIconTextGap(6);
        }

        public Component getListCellRendererComponent(
                JList list,
                Object value,
                int index,
                boolean isSelected,
                boolean cellHasFocus) {
            JLabel l = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (value instanceof Color) {
                Color color = (Color) value;
                colorIcon.setColor(color);
                l.setIcon(colorIcon);
                l.setText(color.getRed() + "," + color.getGreen() + "," + color.getBlue());
            } else {
                l.setIcon(null);
            }
            return l;
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            /*
            g.setColor(new Color(0xafff00f0,true));
           // g.fillRect(0,0,getWidth(),getHeight());
            Insets i = getInsets();
            System.out.println("ColorComboCellRenderer insets:"+i);
            g.fillRect(i.left,i.top,getWidth()-i.left-i.right,getHeight()-i.top-i.bottom);
             **/
        }
    }


    /**
     * Creates new form.
     */
    public EditableComboBoxTest() {
        initComponents();

        String[] items = new String[100];
        for (int i = 0; i < items.length; i++) {
            items[i] = "Item " + (i + 1);
        }
        jComboBox14.setModel(new javax.swing.DefaultComboBoxModel(items));

        tableCellBox.putClientProperty("JComboBox.isTableCellEditor", Boolean.TRUE);

        for (JComponent c : new JComponent[]{smallBox1, smallBox2, smallBox3, smallLabel}) {
            c.putClientProperty("JComponent.sizeVariant", "small");
        }
        for (JComponent c : new JComponent[]{miniBox1, miniBox2, miniBox3, miniLabel}) {
            c.putClientProperty("JComponent.sizeVariant", "mini");
        }

        // Try to get a better layout with J2SE6
        try {
            int BASELINE_LEADING = GridBagConstraints.class.getDeclaredField("BASELINE_LEADING").getInt(null);
            GridBagLayout layout = (GridBagLayout) getLayout();
            for (Component c : getComponents()) {
                GridBagConstraints gbc = layout.getConstraints(c);
                if (gbc.anchor == GridBagConstraints.WEST) {
                    gbc.anchor = BASELINE_LEADING;
                    layout.setConstraints(c, gbc);
                }
            }
        } catch (Exception ex) {
            // bail
        }
    }

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(TestManager.getLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        JFrame f = new JFrame("Quaqua Editable ComboBox Test");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().add(new EditableComboBoxTest());
        ((JComponent) f.getContentPane()).setBorder(new EmptyBorder(9, 17, 17, 17));
        f.pack();
        f.setVisible(true);
    }

    /**
     * This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        box1 = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jComboBox14 = new javax.swing.JComboBox();
        jComboBox3 = new javax.swing.JComboBox();
        jSeparator1 = new javax.swing.JSeparator();
        smallBox1 = new javax.swing.JComboBox();
        smallLabel = new javax.swing.JLabel();
        smallBox2 = new javax.swing.JComboBox();
        smallBox3 = new javax.swing.JComboBox();
        jSeparator3 = new javax.swing.JSeparator();
        miniBox1 = new javax.swing.JComboBox();
        miniLabel = new javax.swing.JLabel();
        miniBox2 = new javax.swing.JComboBox();
        miniBox3 = new javax.swing.JComboBox();
        jSeparator2 = new javax.swing.JSeparator();
        largeBox = new javax.swing.JComboBox();
        largeLabel = new javax.swing.JLabel();
        tableCellBox = new javax.swing.JComboBox();
        tableCellLabel = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        disabledLabel = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(16, 17, 17, 17));
        setLayout(new java.awt.GridBagLayout());

        box1.setEditable(true);
        box1.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6"}));
        box1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        add(box1, gridBagConstraints);

        jLabel1.setText("Enabled");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        add(jLabel1, gridBagConstraints);

        jComboBox14.setEditable(true);
        jComboBox14.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6", "Item 7", "Item 8", "Item 9", "Item 10"}));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        add(jComboBox14, gridBagConstraints);

        jComboBox3.setEditable(true);
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6"}));
        jComboBox3.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        add(jComboBox3, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(12, 0, 12, 0);
        add(jSeparator1, gridBagConstraints);

        smallBox1.setEditable(true);
        smallBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6"}));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        add(smallBox1, gridBagConstraints);

        smallLabel.setText("Small");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        add(smallLabel, gridBagConstraints);

        smallBox2.setEditable(true);
        smallBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6", "Item 7", "Item 8", "Item 9", "Item 10"}));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        add(smallBox2, gridBagConstraints);

        smallBox3.setEditable(true);
        smallBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6"}));
        smallBox3.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        add(smallBox3, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(12, 0, 12, 0);
        add(jSeparator3, gridBagConstraints);

        miniBox1.setEditable(true);
        miniBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6"}));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        add(miniBox1, gridBagConstraints);

        miniLabel.setText("Mini");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        add(miniLabel, gridBagConstraints);

        miniBox2.setEditable(true);
        miniBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6", "Item 7", "Item 8", "Item 9", "Item 10"}));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        add(miniBox2, gridBagConstraints);

        miniBox3.setEditable(true);
        miniBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6"}));
        miniBox3.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        add(miniBox3, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(12, 0, 12, 0);
        add(jSeparator2, gridBagConstraints);

        largeBox.setEditable(true);
        largeBox.setFont(new java.awt.Font("Lucida Grande", 0, 18));
        largeBox.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6"}));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        add(largeBox, gridBagConstraints);

        largeLabel.setText("Large Font");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        add(largeLabel, gridBagConstraints);

        tableCellBox.setEditable(true);
        tableCellBox.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"Item 1", "Item 2", "Item 3", "Item 4"}));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        add(tableCellBox, gridBagConstraints);

        tableCellLabel.setText("Table Cell");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        add(tableCellLabel, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 99;
        gridBagConstraints.weighty = 1.0;
        add(jPanel1, gridBagConstraints);

        disabledLabel.setText("Disabled");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        add(disabledLabel, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void comboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxActionPerformed
        //System.out.println("EditableComboBoxTest.comboBoxActionPerformed:"+evt);
    }//GEN-LAST:event_comboBoxActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox box1;
    private javax.swing.JLabel disabledLabel;
    private javax.swing.JComboBox jComboBox14;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JComboBox largeBox;
    private javax.swing.JLabel largeLabel;
    private javax.swing.JComboBox miniBox1;
    private javax.swing.JComboBox miniBox2;
    private javax.swing.JComboBox miniBox3;
    private javax.swing.JLabel miniLabel;
    private javax.swing.JComboBox smallBox1;
    private javax.swing.JComboBox smallBox2;
    private javax.swing.JComboBox smallBox3;
    private javax.swing.JLabel smallLabel;
    private javax.swing.JComboBox tableCellBox;
    private javax.swing.JLabel tableCellLabel;
    // End of variables declaration//GEN-END:variables

}
