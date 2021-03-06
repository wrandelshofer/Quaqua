/*
 * @(#)QQToolBarTest16.java
 * Quaqua Look and Feel. Copyright © 2020 Werner Randelshofer, Switzerland. MIT License.
 */
package test;

import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author: Thomas Singer
 */
public class QQToolBarTest16 {

    public static void main(String[] args) throws Exception {
        UIManager.setLookAndFeel("ch.randelshofer.quaqua.QuaquaLookAndFeel");

        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                final JFrame frame = new JFrame("Toolbar test");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.getRootPane().putClientProperty("apple.awt.brushMetalLook", Boolean.TRUE);
                frame.setContentPane(createContentPane());
                frame.pack();
                frame.setMinimumSize(frame.getMinimumSize());
                frame.setSize(400, 300);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

    private static JComponent createContentPane() {
        final JLabel label1 = new JLabel("A status bar");
        final JLabel label2 = new JLabel("Another label");
        final JToolBar statusBar = new JToolBar();
        statusBar.setFloatable(false);
        statusBar.putClientProperty("Quaqua.ToolBar.style", "bottom");
        statusBar.add(label1);
        statusBar.add(label2);

        final JToolBar mainToolBar = new JToolBar();
        mainToolBar.setFloatable(false);
        mainToolBar.add(createToolBarButton("Open", "/ch/randelshofer/quaqua/images/Tree.openIcon.png", new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                JOptionPane.showConfirmDialog(mainToolBar, "message");
                label1.setText(label1.getText() + "f");
            }
        }));
        mainToolBar.add(createToolBarButton("Leaf", "/ch/randelshofer/quaqua/images/Tree.leafIcon.png", null));
        mainToolBar.setOpaque(false);
        mainToolBar.putClientProperty("Quaqua.ToolBar.style", "title");

        final JPanel panel = new JPanel(new BorderLayout());
        panel.add(mainToolBar, BorderLayout.NORTH);
        panel.add(createInnerComponent(), BorderLayout.CENTER);
        panel.add(statusBar, BorderLayout.SOUTH);
        return panel;
    }

    private static JComponent createInnerComponent() {
        final JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(true);
        final JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, createLeftComponent(), createRightComponent());
        //		splitPane.setDividerSize(5);
        splitPane.putClientProperty("Quaqua.SplitPane.style", "bar");
        panel.add(splitPane, BorderLayout.CENTER);
        return panel;
    }

    private static JComponent createToolBarButton(String text, String iconResourceName, ActionListener listener) {
        final Icon icon = new ImageIcon(QQToolBarTest16.class.getResource(iconResourceName));
        final JButton button = new JButton(text, icon);
        if (listener != null) {
            button.addActionListener(listener);
        }
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setFocusable(false);
        return button;
    }

    private static JComponent createLeftComponent() {
        final JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);
        toolBar.add(createToolBarButton(null, "/ch/randelshofer/quaqua/images/Tree.openIcon.png", null));
        toolBar.add(createToolBarButton(null, "/ch/randelshofer/quaqua/images/Tree.leafIcon.png", null));
        toolBar.setOpaque(false);
        toolBar.putClientProperty("Quaqua.ToolBar.isDividerDrawn", Boolean.FALSE);

        JScrollPane treeScroller = new JScrollPane(new JTree(new Object[]{"foo", "bar"}));
        treeScroller.setBorder(null);
        return createComponent("Directories", toolBar, treeScroller);
    }

    private static JComponent createRightComponent() {
        final JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);
        toolBar.add(createToolBarButton(null, "/ch/randelshofer/quaqua/images/Tree.openIcon.png", null));
        toolBar.add(createToolBarButton(null, "/ch/randelshofer/quaqua/images/Tree.leafIcon.png", null));
        toolBar.setOpaque(false);
        toolBar.putClientProperty("Quaqua.ToolBar.isDividerDrawn", Boolean.FALSE);

        JScrollPane tableScroller = new JScrollPane(new JTable(new Object[][]{
                {"a", "bc"},
                {"d", "ef"}
        }, new Object[]{
                "bla", "blub"
        }));
        tableScroller.setBorder(new EmptyBorder(0, 0, 0, 0));
        JComponent c = createComponent("Files", toolBar, tableScroller);
        return c;
    }

    private static JComponent createComponent(String title, JToolBar toolBar, JComponent scroller) {
        final JComponent label = new JLabel(title);

        final JPanel panel = new JPanel();
        final GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup().addGroup(
                        layout.createSequentialGroup().addComponent(label).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(toolBar)).addComponent(scroller));
        layout.setVerticalGroup(
                layout.createSequentialGroup().addGroup(
                        layout.createParallelGroup().addComponent(label).addComponent(toolBar)).addComponent(scroller));
        return panel;
    }
}
