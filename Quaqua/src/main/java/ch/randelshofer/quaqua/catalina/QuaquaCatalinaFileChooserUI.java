/*
 * @(#)QuaquaCatalinaFileChooserUI.java
 * Quaqua Look and Feel. Copyright © 2020 Werner Randelshofer, Switzerland. MIT License.
 */

package ch.randelshofer.quaqua.catalina;

import ch.randelshofer.quaqua.catalina.filechooser.OSXCatalinaSidebarTreeModel;
import ch.randelshofer.quaqua.filechooser.CellRenderer;
import ch.randelshofer.quaqua.filechooser.ColumnView;
import ch.randelshofer.quaqua.filechooser.FileSystemTreeModel;
import ch.randelshofer.quaqua.filechooser.SidebarTreeModel;
import ch.randelshofer.quaqua.lion.QuaquaLionFileChooserUI;
import ch.randelshofer.quaqua.mavericks.filechooser.MavericksColumnView;
import ch.randelshofer.quaqua.mavericks.filechooser.MavericksFileRenderer;

import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;
import javax.swing.tree.TreePath;

public class QuaquaCatalinaFileChooserUI extends QuaquaLionFileChooserUI {

    public static ComponentUI createUI(JComponent c) {
        return new QuaquaCatalinaFileChooserUI((JFileChooser) c);
    }

    public QuaquaCatalinaFileChooserUI(JFileChooser filechooser) {
        super(filechooser);
    }

    @Override
    protected CellRenderer createFileRenderer(JFileChooser fc) {
        return new MavericksFileRenderer(
                fc,
                UIManager.getIcon("Browser.expandingIcon"),
                UIManager.getIcon("Browser.expandedIcon"),
                UIManager.getIcon("Browser.selectedExpandingIcon"),
                UIManager.getIcon("Browser.selectedExpandedIcon"),
                UIManager.getIcon("Browser.focusedSelectedExpandingIcon"),
                UIManager.getIcon("Browser.focusedSelectedExpandedIcon"));
    }

    @Override
    protected ColumnView createColumnView(JFileChooser fc) {
        return new MavericksColumnView(fc);
    }

    @Override
    protected SidebarTreeModel createSidebarTreeModel(JFileChooser fc, FileSystemTreeModel model) {
        return new OSXCatalinaSidebarTreeModel(fc, new TreePath(model.getRoot()), model);
    }

}