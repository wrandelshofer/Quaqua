/*
 * @(#)ListView.java
 * Quaqua Look and Feel. Copyright 2020 © Werner Randelshofer, Switzerland. MIT License.
 */
package ch.randelshofer.quaqua.filechooser;

import ch.randelshofer.quaqua.QuaquaManager;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.tree.TreePath;
import java.util.List;

/**
 * The file chooser list view.
 */

public abstract class ListView extends JPanel implements FileChooserView {

    protected SubtreeTreeModel model;

    private ChangeListener changeListener;
    private ChangeEvent changeEvent = new ChangeEvent(this);
    private SelectListener selectListener;

    public static ListView create(int design, JFileChooser fc) {
        switch (design) {
            case QuaquaManager.LION:
            case QuaquaManager.MOUNTAIN_LION:
                return new ch.randelshofer.quaqua.lion.filechooser.ListView(fc);
            case QuaquaManager.MAVERICKS:
            case QuaquaManager.YOSEMITE:
            case QuaquaManager.EL_CAPITAN:
            case QuaquaManager.SIERRA:
                return new ch.randelshofer.quaqua.mavericks.filechooser.ListView(fc);
            default:
                return null;
        }
    }

    @Override
    public void setModel(SubtreeTreeModel m) {
        if (m != model) {
            model = m;
            updateForNewModel();
        }
    }

    @Override
    public void addSelectionChangeListener(ChangeListener l) {
        changeListener = l;
    }

    @Override
    public void addSelectListener(SelectListener l) {
        selectListener = l;
    }

    protected void selectionChanged() {
        if (changeListener != null) {
            changeListener.stateChanged(changeEvent);
        }
    }

    protected abstract void updateForNewModel();

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
