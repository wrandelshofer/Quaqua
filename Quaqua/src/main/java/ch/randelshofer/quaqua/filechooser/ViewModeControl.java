/*
 * @(#)ViewModeControl.java
 * Quaqua Look and Feel. Copyright © 2020 Werner Randelshofer, Switzerland. MIT License.
 */
package ch.randelshofer.quaqua.filechooser;

import ch.randelshofer.quaqua.QuaquaManager;

import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * A control that allows the user to select a view mode for the file chooser.
 */
public abstract class ViewModeControl extends JPanel {

    public final static int ICON_VIEW = 0;
    public final static int LIST_VIEW = 1;
    public final static int COLUMN_VIEW = 2;
    public final static int FLOW_VIEW = 3;

    private ChangeListener changeListener;
    private ChangeEvent changeEvent = new ChangeEvent(this);

    public static ViewModeControl create(int design) {
        switch (design) {
        case QuaquaManager.LION:
        case QuaquaManager.MOUNTAIN_LION:
        case QuaquaManager.MAVERICKS:
        case QuaquaManager.YOSEMITE:
        case QuaquaManager.EL_CAPITAN:
        case QuaquaManager.SIERRA:
            return new ch.randelshofer.quaqua.lion.filechooser.ViewModeControl();
        default:
            return null;
        }
    }

    public abstract void setSelectedViewMode(int mode);

    public abstract int getSelectedViewMode();

    public void addChangeListener(ChangeListener l) {
        changeListener = l;
    }

    protected void selectedViewModeChanged() {
        if (changeListener != null) {
            changeListener.stateChanged(changeEvent);
        }
    }
}
