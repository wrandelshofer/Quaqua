/*
 * @(#)ColumnView.java
 * Quaqua Look and Feel. Copyright © 2020 Werner Randelshofer, Switzerland. MIT License.
 */
package ch.randelshofer.quaqua.filechooser;

import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.tree.TreePath;
import java.util.List;

/**
 * The file chooser column view.
 */

public abstract class ColumnView extends JPanel implements FileChooserView {

    private ChangeListener changeListener;
    private ChangeEvent changeEvent = new ChangeEvent(this);
    private SelectListener selectListener;

    @Override
    public final void addSelectionChangeListener(ChangeListener l) {
        changeListener = l;
    }

    @Override
    public final void addSelectListener(SelectListener l) {
        selectListener = l;
    }

    protected final void selectionChanged() {
        if (changeListener != null) {
            changeListener.stateChanged(changeEvent);
        }
    }

    protected final void select(TreePath path) {
        if (selectListener != null) {
            selectListener.select(path);
        }
    }

    @Override
    public void ensureSelectionIsVisible() {
        List<TreePath> paths = getSelection();
        if (!paths.isEmpty()) {
            ensurePathIsVisible(paths.get(0));
        }
    }
}
