/*
 * @(#)SidebarTreeModel.java
 * Quaqua Look and Feel. Copyright © 2020 Werner Randelshofer, Switzerland. MIT License.
 */
package ch.randelshofer.quaqua.filechooser;

import ch.randelshofer.quaqua.QuaquaManager;
import ch.randelshofer.quaqua.ext.base64.Base64;
import ch.randelshofer.quaqua.ext.nanoxml.XMLElement;
import ch.randelshofer.quaqua.ext.nanoxml.XMLParseException;
import ch.randelshofer.quaqua.osx.OSXFile;
import ch.randelshofer.quaqua.util.BinaryPListParser;

import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

/**
 * Provides a tree model for the Finder sidebar.
 *
 * @author Werner Randelshofer
 * @version $Id$
 */
public abstract class AbstractLeopardLionSidebarTreeModel extends AbstractSidebarTreeModel {
    /**
     * The file system tree model node that represents the Computer.
     */
    private final FileSystemTreeModel.Node computerNode;

    private final OSXFinderDefaults defaults = new OSXFinderDefaults();
    /**
     * Represents the "Devices" node in the sidebar.
     */
    private final DefaultMutableTreeNode devicesNode;
    /**
     * Represents the "Favorites" node in the sidebar.
     */
    private final DefaultMutableTreeNode favoritesNode;
    private HashMap<String, SystemItemInfo> systemItemsMap = new HashMap<>();

    /**
     * Creates a new instance.
     */
    public AbstractLeopardLionSidebarTreeModel(JFileChooser fileChooser, TreePath volumesPath, TreeModel model) {
        super(new DefaultMutableTreeNode(), true, fileChooser, volumesPath, model);
        computerNode = (FileSystemTreeModel.Node) model.getRoot();
        devicesNode = new DefaultMutableTreeNode(UIManager.getString("FileChooser.devices"));
        favoritesNode = new DefaultMutableTreeNode(UIManager.getString("FileChooser.favorites"));
        devicesNode.setAllowsChildren(true);
        favoritesNode.setAllowsChildren(true);
        initTreeStructure(getRoot(), devicesNode, favoritesNode);
        validate();
    }

    protected abstract void initTreeStructure(DefaultMutableTreeNode root, DefaultMutableTreeNode devicesNode, DefaultMutableTreeNode favoritesNode);

    private void addToDeviceNode(FileSystemTreeModel.Node modelNode, DefaultMutableTreeNode devicesNode, int insertionIndex) {
        SidebarViewToModelNode newNode = new SidebarViewToModelNode(modelNode);
        insertNodeInto(newNode, devicesNode, insertionIndex);
    }

    private boolean isDevice(FileSystemTreeModel.Node modelNode) {
        return !modelNode.isLeaf() && !modelNode.getUserName().equals("Network");
    }

    private boolean isInDeviceNode(FileSystemTreeModel.Node modelNode, DefaultMutableTreeNode devicesNode, int from, int to) {
        for (int j = from; j < to; j++) {
            TreeNode child = devicesNode.getChildAt(j);
            if (child instanceof SidebarViewToModelNode) {
                SidebarViewToModelNode viewNode = (SidebarViewToModelNode) child;
                if (viewNode.getTarget() == modelNode) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Reads the sidebar preferences file.
     *
     * @return the user items and the system items
     */
    protected Object[] read() throws IOException {
        if (!OSXFile.canWorkWithAliases()) {
            throw new IOException("Unable to work with aliases");
        }

        HashMap<String, SystemItemInfo> sysItemsMap = new HashMap<>();
        ArrayList<Node> userItems = new ArrayList<>();

        File sidebarFile = defaults.getSidebarFile();
        try (FileReader reader = new FileReader(sidebarFile)) {
            XMLElement xml = new XMLElement();
            try {
                xml.parseFromReader(reader);
            } catch (XMLParseException e) {
                xml = new BinaryPListParser().parse(sidebarFile);
            }
            String key2 = "";
            for (XMLElement xml1 : xml.getChildren()) {
                for (XMLElement xml2 : xml1.getChildren()) {
                    if (xml2.getName().equals("key")) {
                        key2 = xml2.getContent();
                    }

                    if (xml2.getName().equals("dict")) {
                        if (key2.equals("systemitems")) {
                            readSystemItems(sysItemsMap, xml2);
                        } else if (key2.equals(defaults.getFavoriteItemsDictionaryName())) {
                            readUserItems(userItems, xml2);
                        }
                    }
                }
            }
        }
        return new Object[]{userItems, sysItemsMap};
    }

    private void readSystemItems(HashMap<String, SystemItemInfo> sysItemsMap, XMLElement xml2) {
        String key3 = "", key5 = "";
        for (XMLElement xml3 : xml2.getChildren()) {
            if (xml3.getName().equals("key")) {
                key3 = xml3.getContent();
            }
            if (xml3.getName().equals("array") && key3.equals("VolumesList")) {
                for (XMLElement xml4 : xml3.getChildren()) {
                    if (xml4.getName().equals("dict")) {
                        SystemItemInfo info = new SystemItemInfo();
                        int flags = 0;
                        for (XMLElement xml5 : xml4.getChildren()) {
                            if (xml5.getName().equals("key")) {
                                key5 = xml5.getContent();
                            }

                            info.setSequenceNumber(sysItemsMap.size());
                            if (xml5.getName().equals("string") && key5.equals("Name")) {
                                info.setName(xml5.getContent());
                            }
                            if (xml5.getName().equals("string") && key5.equals("Visibility")) {
                                info.setVisible(xml5.getContent().equals("AlwaysVisible"));
                            }
                            if (xml5.getName().equals("integer") && key5.equals("Flags")) {
                                try {
                                    flags = Integer.parseInt(xml5.getContent());
                                } catch (NumberFormatException ex) {
                                    // bail
                                    flags = 0;
                                }
                            }
                        }
                        if (info.getName() != null) {
                            if ((flags & 0x1) != 0) {
                                info.setVisible(false); // special case for Computer ???
                            }
                            sysItemsMap.put(info.getName(), info);
                        }
                    }
                }
            }
        }
    }

    private void readUserItems(ArrayList<Node> userItems, XMLElement xml2) {
        String key5 = "";
        for (XMLElement xml3 : xml2.getChildren()) {
            for (XMLElement xml4 : xml3.getChildren()) {
                String aliasName = null;
                int entryType = 0;
                byte[] serializedAlias = null;
                boolean isVisible = true;
                for (XMLElement xml5 : xml4.getChildren()) {
                    if (xml5.getName().equals("key")) {
                        key5 = xml5.getContent();
                    }
                    if (xml5.getName().equals("string") && key5.equals("Name")) {
                        aliasName = xml5.getContent();
                    }
                    if (!xml5.getName().equals("key") && key5.equals("Alias")) {
                        serializedAlias = Base64.decode(xml5.getContent());
                    }
                    if (key5.equals("EntryType")) {
                        // EntryType marks items which have been added
                        // by the System.
                        try {
                            entryType = Integer.parseInt(xml5.getContent());
                        } catch (NumberFormatException e) {
                            entryType = 1;
                        }
                    }
                    if (key5.equals("Visibility")) {
                        if (xml5.getContent() != null && xml5.getContent().equals("NeverVisible")) {
                            isVisible = false;
                        }

                    }
                }

                if (serializedAlias != null && aliasName != null && entryType == 0 && isVisible) {
                    // Suppress the "All My Files" folder.
                    if (aliasName.equals("All My Files")) {
                        continue;
                    }

                    // Try to resolve the alias without user interaction
                    File f = OSXFile.resolveAlias(serializedAlias, true);
                    if (f != null) {
                        userItems.add(new FileNode(f));
                    } else {
                        userItems.add(new AliasNode(serializedAlias, aliasName));
                    }
                }
            }
        }
    }

    @Override
    protected void update(Object[] value) {
        ArrayList<DefaultMutableTreeNode> freshUserItems;


        if (value == null) {
            System.err.println("Warning: SidebarTreeModel uses default user items.");
            freshUserItems = new ArrayList<>(defaults.getDefaultUserItems().size());
            for (File defaultUserItem : defaults.getDefaultUserItems()) {
                if (defaultUserItem == null) {
                    freshUserItems.add(null);
                } else if (defaultUserItem.exists()) {
                    freshUserItems.add(new FileNode(defaultUserItem));
                }
            }
            systemItemsMap = new HashMap<>();
        } else {
            //noinspection unchecked
            freshUserItems = (ArrayList<DefaultMutableTreeNode>) value[0];
            //noinspection unchecked
            systemItemsMap = (HashMap<String, SystemItemInfo>) value[1];
        }

        updateDevices();
        updateFavorites(freshUserItems);
    }


    public class SideBarViewToModelNodeComparator implements Comparator<TreeNode> {

        public int compare(TreeNode n1, TreeNode n2) {
            return getSequenceNumber(n1) - getSequenceNumber(n2);
        }

        private int getSequenceNumber(TreeNode n) {
            if (!(n instanceof SidebarViewToModelNode)) {
                return -1;
            }
            FileSystemTreeModel.Node target = ((SidebarViewToModelNode) n).getTarget();
            if (target == computerNode) {
                return -1;
            }
            SystemItemInfo si = systemItemsMap.get(target.getUserName());
            return si != null ? si.getSequenceNumber() : -1;
        }
    }

    @Override
    protected void updateDevices() {
        updateDevicesInNode(devicesNode, 0, devicesNode.getChildCount());
    }

    protected void updateDevicesInNode(DefaultMutableTreeNode sidebarDevicesNode, int initialFrom, int initialTo) {
        FileSystemTreeModel.Node modelDevicesNode = (FileSystemTreeModel.Node) getVolumesPath().getLastPathComponent();
        final FileSystemTreeModel.Node computerNode;

        // Determine if the computer node should be visible
        {
            SystemItemInfo info = systemItemsMap.get("Computer");
            if (info != null && !info.isVisible()) {
                computerNode = null;
            } else {
                computerNode = this.computerNode;
            }
        }

        int from=initialFrom;
        int to=initialTo;

        // Remove nodes from the view which are not present in the file system model
        for (int i = to - 1; i >= from; i--) {
            TreeNode childNode = sidebarDevicesNode.getChildAt(i);
            if (childNode instanceof SidebarTreeFileNode) {
                SidebarViewToModelNode viewNode = (SidebarViewToModelNode) childNode;
                FileSystemTreeModel.Node target = viewNode.getTarget();
                if (target.getParent() != modelDevicesNode && target != computerNode) {
                    removeNodeFromParent(viewNode);
                    to--;
                }
            }
        }

        // Add nodes to the view, which are present in the model, but not
        // in the view. Only add non-leaf nodes

        if (computerNode != null && !isInDeviceNode(computerNode, sidebarDevicesNode, from, to)) {
            if (!isInDeviceNode(computerNode, sidebarDevicesNode, from ,to)) {
                addToDeviceNode(computerNode, sidebarDevicesNode, to++);
            }
        }

        for (int i = 0, n = modelDevicesNode.getChildCount(); i < n; i++) {
            FileSystemTreeModel.Node modelNode = (FileSystemTreeModel.Node) modelDevicesNode.getChildAt(i);
            if (isDevice(modelNode)) {
                if (!isInDeviceNode(modelNode, sidebarDevicesNode, from, to)) {
                    addToDeviceNode(modelNode,sidebarDevicesNode, to++);
                }
            }
        }

        //  We need to sort the entire collection every time because we do not
        //  know when the preferences file will be read (and the preferences
        //  file determines the order).
        int count = to- from;
        if (count > 0) {
            MutableTreeNode[] children = new MutableTreeNode[count];
            for (int i = 0; i < count; i++) {
                TreeNode childNode = sidebarDevicesNode.getChildAt(i+from);
                children[i] = (MutableTreeNode) childNode;
            }
            Arrays.sort(children, new AbstractLeopardLionSidebarTreeModel.SideBarViewToModelNodeComparator());
            for (int i=0;i<count;i++) {
                sidebarDevicesNode.remove(from);
            }
            for (int i = 0; i < count; i++) {
                sidebarDevicesNode.add(children[i]);
            }
        }

        // Update the view
        if (to-from>0) {
            int[] childIndices = new int[to-from];
            Object[] childNodes = new Object[to-from];
            for (int i = from; i < to; i++) {
                childIndices[i-from] = i;
                childNodes[i-from] = sidebarDevicesNode.getChildAt(i);
            }
            fireTreeNodesChanged(this, sidebarDevicesNode.getPath(), childIndices, childNodes);
        }
    }


    protected void updateFavorites(ArrayList<DefaultMutableTreeNode> freshFavorites) {
        updateFavoritesInNode(freshFavorites,favoritesNode,0,favoritesNode.getChildCount());
    }

   protected void updateFavoritesInNode(ArrayList<DefaultMutableTreeNode> freshFavorites, DefaultMutableTreeNode favoritesNode, int from, int initialTo) {
        int to=initialTo;
        int oldCount = to-from;
        if (oldCount > 0) {
            int[] removedIndices = new int[oldCount];
            Object[] removedChildren = new Object[oldCount];
            for (int i = 0; i < oldCount; i++) {
                removedIndices[i] = i+from;
                removedChildren[i] = favoritesNode.getChildAt(i+from);
                favoritesNode.remove(to--);
            }
            fireTreeNodesRemoved(
                    this,
                    favoritesNode.getPath(),
                    removedIndices,
                    removedChildren);
        }
        if (freshFavorites.size() > 0) {
            int[] insertedIndices = new int[freshFavorites.size()];
            Object[] insertedChildren = new Object[freshFavorites.size()];
            for (int i = 0; i < freshFavorites.size(); i++) {
                insertedIndices[i] = i+from;
                insertedChildren[i] = freshFavorites.get(i);
                if (freshFavorites.get(i) == null) {
                    favoritesNode.insert(new DefaultMutableTreeNode("null?"),to++);
                } else {
                    favoritesNode.insert(freshFavorites.get(i),to++);
                }
            }
            fireTreeNodesInserted(
                    this,
                    favoritesNode.getPath(),
                    insertedIndices,
                    insertedChildren);
        }
    }
}
