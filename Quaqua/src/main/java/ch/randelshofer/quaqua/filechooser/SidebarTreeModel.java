/*
 * @(#)SidebarTreeModel.java
 * Quaqua Look and Feel. Copyright © 2020 Werner Randelshofer, Switzerland. MIT License.
 */

package ch.randelshofer.quaqua.filechooser;

import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import java.beans.PropertyChangeListener;

public interface SidebarTreeModel extends TreeModel {
    /** The name of the valid property.
     * <p>
     * This is a boolean property, which is set to true when the sidebar
     * is valid.
     */
    String VALID_PROPERTY = "valid";

    TreeNode[] getPathToRoot(TreeNode aNode);

    /**
     * Invalidates the sidebar if it is valid.
     */
    void invalidate();
    /**
     * Asynchronously validates the sidebar if it is invalid.
     */
    void validate();

    /**
     * Returns the current value of the "valid" property.
     * @return true if the sidebar has been loaded
     */
    boolean isValid();

    /**
     * Adds a property change listener.
     * @param listener the listener
     */
    void addPropertyChangeListener(PropertyChangeListener listener);
    /**
     * Removes a property change listener.
     * @param listener the listener
     */
    void removePropertyChangeListener(PropertyChangeListener listener);
}
