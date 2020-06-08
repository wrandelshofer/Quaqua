/*
 * @(#)NavigatableTabbedPaneUI.java
 * Quaqua Look and Feel. Copyright 2020 © Werner Randelshofer, Switzerland. MIT License.
 */

package ch.randelshofer.quaqua.util;

/**
 * NavigatableTabbedPaneUI.
 *
 * @author Werner Randelshofer
 * @version 1.0 September 4, 2006 Created.
 */
public interface NavigatableTabbedPaneUI {
    /** Tab Navigation methods. */
    public void navigateSelectedTab(int direction);
    public boolean requestFocusForVisibleComponent();
    public Integer getIndexForMnemonic(int mnemonic);
}
