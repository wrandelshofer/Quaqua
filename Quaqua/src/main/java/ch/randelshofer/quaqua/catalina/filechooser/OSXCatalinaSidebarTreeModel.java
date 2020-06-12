/*
 * @(#)SidebarTreeModel.java
 * Quaqua Look and Feel. Copyright © 2020 Werner Randelshofer, Switzerland. MIT License.
 */
package ch.randelshofer.quaqua.catalina.filechooser;

import ch.randelshofer.quaqua.filechooser.AbstractLeopardLionSidebarTreeModel;

import javax.swing.JFileChooser;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

/**
 * Provides a date model for the Finder sidebar in OS X 10.7 Lion.
 * <p>
 * The sidebar has the following structure:
 * <ul>
 *   <li>FAVORITES
 *     <ul>
 *       <li>user defined list of favorite places</li>
 *     </ul>
 *   </li>
 *   <li>DEVICES
 *     <ul>
 *       <li>contents of {@code /Volumes} folder</li>
 *     </ul>
 *   </li>
 * </ul>
 * The user defined preferences for the sidebar are read from
 * the file {@code ~/Library/Preferences/com.apple.sidebarlists.plist}.
 *
 * @author Werner Randelshofer
 * @version $Id$
 */
public class OSXCatalinaSidebarTreeModel extends AbstractLeopardLionSidebarTreeModel{

    /**
     * Creates a new instance.
     *
     * @param fileChooser the file chooser
     * @param volumesPath the path to ??
     * @param model the file system model that the file chooser uses
     */
    public OSXCatalinaSidebarTreeModel(JFileChooser fileChooser, TreePath volumesPath, TreeModel model) {
        super(fileChooser, volumesPath, model);
    }

    @Override
    protected void initTreeStructure(DefaultMutableTreeNode root, DefaultMutableTreeNode devicesNode, DefaultMutableTreeNode favoritesNode){
        root.add(favoritesNode);
        root.add(devicesNode);
    }


}
