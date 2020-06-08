/*
 * @(#)ClipboardTest.java
 * Quaqua Look and Feel. Copyright © 2020 Werner Randelshofer, Switzerland. MIT License.
 */
package qtest;

import ch.randelshofer.quaqua.osx.OSXClipboardTransferable;
import ch.randelshofer.quaqua.osx.OSXImageIO;
import ch.randelshofer.quaqua.util.Fonts;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.net.URLDecoder;
import java.nio.ByteBuffer;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.rtf.RTFEditorKit;

/**
 * ClipboardTest.
 *
 * @author Werner Randelshofer
 * @version $Id$
 */
public class ClipboardTest extends javax.swing.JPanel {

    private Transferable transferable;

    private static class FlavorRenderer extends DefaultListCellRenderer {

        public Component getListCellRendererComponent(
                JList list,
                Object value,
                int index,
                boolean isSelected,
                boolean cellHasFocus) {


            String stringValue = ((DataFlavor) value).getHumanPresentableName();
            return super.getListCellRendererComponent(list, stringValue, index, isSelected, cellHasFocus);
        }
    }

    /** Creates new form ClipboardTest */
    public ClipboardTest() {
        initComponents();

        Font f = Fonts.getSmallDialogFont();
        dataFlavorsLabel.setFont(f);
        dataFlavorsList.setFont(f);
        transferDataLabel.setFont(f);

        dataFlavorsList.setCellRenderer(new FlavorRenderer());

        dataFlavorsList.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    updateTransferData();
                }
            }
        });

        readNativeClipboardButton.setEnabled(OSXClipboardTransferable.isNativeCodeAvailable());
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        splitPane = new javax.swing.JSplitPane();
        dataFlavorPanel = new javax.swing.JPanel();
        dataFlavorsLabel = new javax.swing.JLabel();
        dataFlavorsScrollPane = new javax.swing.JScrollPane();
        dataFlavorsList = new javax.swing.JList();
        transferDataPanel = new javax.swing.JPanel();
        transferDataLabel = new javax.swing.JLabel();
        transferDataScrollPane = new javax.swing.JScrollPane();
        buttonPanel = new javax.swing.JPanel();
        readNativeClipboardButton = new javax.swing.JButton();
        readJavaClipboardButton = new javax.swing.JButton();

        FormListener formListener = new FormListener();

        setLayout(new java.awt.BorderLayout());

        dataFlavorPanel.setLayout(new java.awt.BorderLayout());

        dataFlavorsLabel.setText("Data Flavors");
        dataFlavorPanel.add(dataFlavorsLabel, java.awt.BorderLayout.NORTH);

        dataFlavorsScrollPane.setViewportView(dataFlavorsList);

        dataFlavorPanel.add(dataFlavorsScrollPane, java.awt.BorderLayout.CENTER);

        splitPane.setLeftComponent(dataFlavorPanel);

        transferDataPanel.setLayout(new java.awt.BorderLayout());

        transferDataLabel.setText("Transfer Data");
        transferDataPanel.add(transferDataLabel, java.awt.BorderLayout.NORTH);
        transferDataPanel.add(transferDataScrollPane, java.awt.BorderLayout.CENTER);

        splitPane.setRightComponent(transferDataPanel);

        add(splitPane, java.awt.BorderLayout.CENTER);

        buttonPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 5));

        readNativeClipboardButton.setText("Read Native Clipboard");
        readNativeClipboardButton.addActionListener(formListener);
        buttonPanel.add(readNativeClipboardButton);

        readJavaClipboardButton.setText("Read Java Clipboard");
        readJavaClipboardButton.addActionListener(formListener);
        buttonPanel.add(readJavaClipboardButton);

        add(buttonPanel, java.awt.BorderLayout.SOUTH);
    }

    // Code for dispatching events from components to event handlers.

    private class FormListener implements java.awt.event.ActionListener {
        FormListener() {}
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            if (evt.getSource() == readNativeClipboardButton) {
                ClipboardTest.this.readNativeClipboard(evt);
            }
            else if (evt.getSource() == readJavaClipboardButton) {
                ClipboardTest.this.readJavaClipboard(evt);
            }
        }
    }// </editor-fold>//GEN-END:initComponents

    private void readNativeClipboard(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_readNativeClipboard
        transferable = new OSXClipboardTransferable();
        updateDataFlavors();
    }//GEN-LAST:event_readNativeClipboard

    private void readJavaClipboard(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_readJavaClipboard
        transferable = getToolkit().getSystemClipboard().getContents(this);
        updateDataFlavors();
    }//GEN-LAST:event_readJavaClipboard

    private void updateDataFlavors() {
        DataFlavor[] flavors = (transferable == null) ? new DataFlavor[0] : transferable.getTransferDataFlavors();
        DefaultListModel lm = new DefaultListModel();
        for (int i = 0; i < flavors.length; i++) {
            lm.addElement(flavors[i]);
        }
        dataFlavorsList.setModel(lm);
        updateTransferData();
    }

    private void updateTransferData() {
        // Get rid of view to ensure that it can be garbage collected
        transferDataScrollPane.setViewportView(null);

        // Create new view
        JComponent view = null;
        if (dataFlavorsList.getSelectedIndex() != -1 && transferable != null) {
            try {
                DataFlavor flavor = (DataFlavor) dataFlavorsList.getSelectedValue();

                // Get OSX Pasteboard Type
                String osxType = null;
                if (flavor.match(new DataFlavor("application/octet-stream"))) {
                    osxType = flavor.getParameter("type");
                }
                osxType = (osxType == null) ? "" : URLDecoder.decode(osxType, "UTF-8");

                // Get transfer data
                Object data = transferable.getTransferData(flavor);


                if (data == null) {
                    // Handle null case
                    view = new JLabel("null");

                } else if ((data instanceof byte[]) || (data instanceof InputStream) || (data instanceof ByteBuffer)) {
                    byte[] bytes;
                    // For simplicity convert input stream to byte array.
                    if (data instanceof InputStream) {
                        InputStream in = (InputStream) data;
                        ByteArrayOutputStream tmp = new ByteArrayOutputStream();
                        byte[] buf = new byte[512];
                        int count;
                        while (-1 != (count = in.read(buf, 0, buf.length))) {
                            tmp.write(buf, 0, count);
                        }
                        bytes = tmp.toByteArray();
                    } else if (data instanceof ByteBuffer) {
                        ByteBuffer bb = (ByteBuffer) data;
                        bytes = new byte[bb.remaining()];
                        bb.get(bytes);
                    } else {
                        bytes = (byte[]) data;
                    }

                    if (osxType.equals("public.utf8-plain-text") ||
                            osxType.equals("NSStringPboardType")) {
                        String str = new String(bytes, "UTF-8");
                        view = new JTextArea(str);

                    } else if (osxType.equals("public.utf16-external-plain-text") ||
                            osxType.equals("NSStringPboardType")) {
                        String str = new String(bytes, "UTF-16");
                        view = new JTextArea(str);

                    } else if (osxType.equals("NeXT plain ascii pasteboard type")) {
                        String str = new String(bytes, "ASCII");
                        view = new JTextArea(str);

                    } else if (flavor.match(new DataFlavor("text/rtf"))) {
                        JTextPane tp = new JTextPane();
                        RTFEditorKit kit = new RTFEditorKit();
                        tp.setEditorKit(kit);
                        kit.read(new ByteArrayInputStream(bytes),tp.getDocument(),0);
                        view = tp;

                    } else if (flavor.match(new DataFlavor("text/plain"))||
                            (flavor.getPrimaryType()+"/"+flavor.getSubType()).equals("text/plain")) {
                        String charset = flavor.getParameter("charset");
                        String str = new String(bytes, charset);
                        JTextArea ta = new JTextArea(str);
                        view = ta;

                    } else if (flavor.match(new DataFlavor("image/x-pict"))) {
                        // Add 512 zero-bytes to beginning of PICT data
                        byte[] b = bytes;
                        byte[] tmp = new byte[b.length + 512];
                        System.arraycopy(b, 0, tmp, 512, b.length);
                        b = tmp;

                        // Try to create an image from the byte array
                        BufferedImage img = null;
                        img = toImage(b);
                        if (img != null) {
                            view = new JLabel(new ImageIcon(img));
                        } else {
                            // Create a hex dump of the byte array
                            view = new JTextArea(toHexDump(bytes));
                            view.setFont(Fonts.getMonospaceFont());
                        }
                    } else {
                        // Try to create an image from the byte array
                        BufferedImage img = null;
                        img = toImage(bytes);
                        if (img != null) {
                            view = new JLabel(new ImageIcon(img));
                        } else {
                            // Create a hex dump of the byte array
                            view = new JTextArea(toHexDump(bytes));
                            view.setFont(Fonts.getMonospaceFont());
                        }
                    }
                } else if (data instanceof String) {
                    view = new JTextArea((String) data);
                } else if (data instanceof char[]) {
                    view = new JTextArea(new String((char[]) data));
                } else if (data instanceof Reader) {
                    // Create a string from the reader
                    StringBuffer sb = new StringBuffer();
                    Reader r = (Reader) data;
                    char[] cbuf = new char[512];
                    for (int count = 0; count != -1; count = r.read(cbuf, 0, cbuf.length)) {
                        sb.append(cbuf, 0, count);
                    }
                    view = new JTextArea(sb.toString());

                } else if (data instanceof Image) {
                    view = new JLabel(new ImageIcon((Image) data));

                } else {
                    view = new JTextArea(data.toString());
                }
            } catch (Throwable ex) {
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                ex.printStackTrace(pw);
                pw.close();
                view = new JTextArea(sw.toString());
                ex.printStackTrace();
            }
            transferDataScrollPane.setViewportView(view);
        }
    }

    private BufferedImage toImage(byte[] b) {
        try {
            return OSXImageIO.read(b);
        } catch (IOException ex) {
            // ex.printStackTrace();
            return null;
        }
    }

    private String toHexDump(byte[] b) {
        StringBuffer sb = new StringBuffer();
        for (int line = 0; line < b.length; line += 16) {
            String hex = Integer.toHexString(line);
            for (int i = hex.length(); i < 8; i++) {
                sb.append('0');
            }
            sb.append(hex);
            sb.append(':');
            for (int column = 0; column < 16; column++) {
                if (column % 4 == 0) {
                    sb.append(' ');
                }
                if (column + line >= b.length) {
                    sb.append("  ");
                } else {
                    hex = Integer.toHexString(b[column + line] & 0xff);
                    for (int i = hex.length(); i < 2; i++) {
                        sb.append('0');
                    }
                    sb.append(hex);
                }
            }
            sb.append(' ');
            for (int column = 0; column < 16 && column + line < b.length; column++) {
                if (column % 4 == 0) {
                    sb.append(' ');
                }
                char chr = (char) (b[column + line] & 0xff);
                if (chr >= ' ' && chr <= '~') {
                    sb.append(chr);
                } else {
                    sb.append('.');
                }
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JPanel dataFlavorPanel;
    private javax.swing.JLabel dataFlavorsLabel;
    private javax.swing.JList dataFlavorsList;
    private javax.swing.JScrollPane dataFlavorsScrollPane;
    private javax.swing.JButton readJavaClipboardButton;
    private javax.swing.JButton readNativeClipboardButton;
    private javax.swing.JSplitPane splitPane;
    private javax.swing.JLabel transferDataLabel;
    private javax.swing.JPanel transferDataPanel;
    private javax.swing.JScrollPane transferDataScrollPane;
    // End of variables declaration//GEN-END:variables
}
