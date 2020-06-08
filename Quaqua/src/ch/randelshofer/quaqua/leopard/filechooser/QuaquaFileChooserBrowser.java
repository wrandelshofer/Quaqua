/*
 * @(#)QuaquaFileChooserBrowser.java
 * Quaqua Look and Feel. Copyright © 2020 Werner Randelshofer, Switzerland. MIT License.
 */
package ch.randelshofer.quaqua.leopard.filechooser;

import ch.randelshofer.quaqua.JBrowser;
import ch.randelshofer.quaqua.QuaquaScrollPaneUI;
import ch.randelshofer.quaqua.filechooser.QuaquaFileChooserBrowserListUI;
import ch.randelshofer.quaqua.filechooser.QuaquaFileChooserListMouseBehavior;

import javax.swing.*;
import javax.swing.plaf.ListUI;
import java.io.File;

/**
 * The browser in a file chooser. Implements special behavior for clicking on an ordinary file in a Save panel.
 */
public class QuaquaFileChooserBrowser extends JBrowser {
    protected JFileChooser fc;

    public QuaquaFileChooserBrowser(JFileChooser fc) {
        this.fc = fc;
    }

    @Override
    protected ListUI getColumnListUI(ListUI basicUI) {
        QuaquaFileChooserBrowserListUI ui = new QuaquaFileChooserBrowserListUI(fc);
        ui.setFileSelectionHandler(new QuaquaFileChooserListMouseBehavior.FileSelectionHandler() {
            @Override
            public void fileSelected(File f) {
                QuaquaFileChooserBrowser.this.fileSelectedInSavePanel(f);
            }
        });
        return ui;
    }

    @Override
    protected JScrollPane createScrollPane(JComponent c, int columnIndex) {
        JScrollPane sp = super.createScrollPane(c, columnIndex);
        sp.setUI(new QuaquaScrollPaneUI());
        sp.setBorder(null);
        sp.setFocusable(false);
        sp.getVerticalScrollBar().setFocusable(false);
        sp.getHorizontalScrollBar().setFocusable(false);
        return sp;
    }

    protected void fileSelectedInSavePanel(File f) {
    }
}
