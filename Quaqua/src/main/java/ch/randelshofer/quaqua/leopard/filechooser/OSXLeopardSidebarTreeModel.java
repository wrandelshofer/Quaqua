/*
 * @(#)SidebarTreeModel.java
 * Quaqua Look and Feel. Copyright © 2020 Werner Randelshofer, Switzerland. MIT License.
 */
package ch.randelshofer.quaqua.leopard.filechooser;

import ch.randelshofer.quaqua.filechooser.AbstractLeopardLionSidebarTreeModel;

import javax.swing.JFileChooser;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

/**
 * Provides a tree model for the Finder sidebar in OS X 10.5 Leopard.
 * <p>
 * The sidebar has the following structure:
 * <ul>
 *   <li>DEVICES
 *     <ul>
 *       <li>contents of {@code /Volumes} folder</li>
 *     </ul>
 *   </li>
 *   <li>PLACES
 *     <ul>
 *       <li>user defined list of favorite places</li>
 *     </ul>
 *   </li>
 * </ul>
 * The user defined preferences for the sidebar are read from
 * the file {@code ~/Library/Preferences/com.apple.sidebarlists.plist}.
 *
 * @author Werner Randelshofer
 * @version $Id$
 */
public class OSXLeopardSidebarTreeModel extends AbstractLeopardLionSidebarTreeModel {
    /**
     * Creates a new instance.
     *
     * @param fileChooser the file chooser
     * @param volumesPath the path to the volumes folder
     * @param model       the file system model that the file chooser uses
     */
    public OSXLeopardSidebarTreeModel(JFileChooser fileChooser, TreePath volumesPath, TreeModel model) {
        super(fileChooser, volumesPath, model);
    }

    @Override
    protected void initTreeStructure(DefaultMutableTreeNode root, DefaultMutableTreeNode devicesNode, DefaultMutableTreeNode favoritesNode) {
        root.add(devicesNode);
        root.add(favoritesNode);
    }
}
