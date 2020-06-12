/*
 * @(#)OSXPantherSidebarTreeModel.java
 * Quaqua Look and Feel. Copyright © 2020 Werner Randelshofer, Switzerland. MIT License.
 */

package ch.randelshofer.quaqua.panther.filechooser;

import ch.randelshofer.quaqua.filechooser.AbstractLeopardLionSidebarTreeModel;
import ch.randelshofer.quaqua.lion.filechooser.OSXLionSidebarTreeModel;

import javax.swing.JFileChooser;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import java.util.ArrayList;

/**
 * Provides a tree model for the Finder sidebar in OS X 10.3 Panther.
 * <p>
 * The sidebar has the following structure:
 * <ul>
 *  <li>contents of {@code /Volumes} folder</li>
 *  <li>separator</li>
 *  <li>user defined list of favorite places</li>
 * </ul>
 * The user defined preferences for the sidebar are read from
 * the file {@code ~/Library/Preferences/com.apple.sidebarlists.plist}.
 *
 * @author Werner Randelshofer
 * @version $Id$
 */
public class OSXPantherSidebarTreeModel extends AbstractLeopardLionSidebarTreeModel {
private     DefaultMutableTreeNode separatorNode;
    /**
     * Creates a new instance.
     *
     * @param fileChooser the file chooser
     * @param volumesPath the path to the volumes folder
     * @param model       the file system model that the file chooser uses
     */
    public OSXPantherSidebarTreeModel(JFileChooser fileChooser, TreePath volumesPath, TreeModel model) {
        super(fileChooser, volumesPath, model);
    }

    @Override
    protected void initTreeStructure(DefaultMutableTreeNode root, DefaultMutableTreeNode devicesNode, DefaultMutableTreeNode favoritesNode) {
      separatorNode = new DefaultMutableTreeNode(null);
        root.add(separatorNode);
    }

    @Override
    protected void updateDevices() {
        DefaultMutableTreeNode root = getRoot();
        int separatorIndex = getSeparatorIndex(root);
        updateDevicesInNode(root,separatorIndex+1,root.getChildCount());
    }

    private int getSeparatorIndex(DefaultMutableTreeNode root) {
        int separatorIndex=0;
        for (int i=0,n=root.getChildCount();i<n;i++) {
            if (root.getChildAt(i)==separatorNode) {
                separatorIndex = i;
                break;
            }
        }
        return separatorIndex;
    }

    protected void updateFavorites(ArrayList<DefaultMutableTreeNode> freshFavorites) {
        DefaultMutableTreeNode root = getRoot();
        updateFavoritesInNode(freshFavorites, root,0,getSeparatorIndex(root));
    }

}
