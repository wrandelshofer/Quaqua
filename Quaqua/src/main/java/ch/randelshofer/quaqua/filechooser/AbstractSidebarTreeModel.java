/*
 * @(#)AbstractSidebarTreeModel.java
 * Quaqua Look and Feel. Copyright © 2020 Werner Randelshofer, Switzerland. MIT License.
 */

package ch.randelshofer.quaqua.filechooser;

import ch.randelshofer.quaqua.lion.filechooser.OSXLionSidebarTreeModel;
import ch.randelshofer.quaqua.osx.OSXFile;
import ch.randelshofer.quaqua.util.SequentialDispatcher;
import ch.randelshofer.quaqua.util.Worker;

import javax.swing.Icon;
import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.IOException;

public abstract class AbstractSidebarTreeModel extends DefaultTreeModel implements  SidebarTreeModel{
    private final PropertyChangeSupport propertyChangeSupport=new PropertyChangeSupport(this);
    /**
     * The JFileChooser.
     */
    private final JFileChooser fileChooser;

    /**
     * Holds the FileSystemTreeModel.
     */
    private final TreeModel model;
    /**
     * Holds the path to the file system tree model node where volumes are obtained. Currently the computer node.
     */
    private final TreePath volumesPath;

    /**
     * Sequential dispatcher for the lazy creation of icons.
     */
    private final SequentialDispatcher dispatcher = new SequentialDispatcher();
    /**
     * The current validation state of this model.
     */
    private InfoState infoState = InfoState.INVALID;

    private final TreeModelListener treeModelListener =new TreeModelListener() {

        public void treeNodesChanged(TreeModelEvent e) {
            if (e.getTreePath().equals(volumesPath)) {
                updateDevices();
            }
        }

        public void treeNodesInserted(TreeModelEvent e) {
            if (e.getTreePath().equals(volumesPath)) {
                updateDevices();
            }
        }

        public void treeNodesRemoved(TreeModelEvent e) {
            if (e.getTreePath().equals(volumesPath)) {
                updateDevices();
            }
        }

        public void treeStructureChanged(TreeModelEvent e) {
            if (e.getTreePath().equals(volumesPath)) {
                updateDevices();
            }
        }
    };
    public AbstractSidebarTreeModel(TreeNode root, boolean asksAllowsChildren,JFileChooser fileChooser, TreePath volumesPath, TreeModel model) {
        super(root, asksAllowsChildren);
        this.fileChooser=fileChooser;
        this.volumesPath = volumesPath;
        this.model=model;
        this.model.addTreeModelListener(treeModelListener);
    }

    @Override
    public final void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    @Override
    public DefaultMutableTreeNode getRoot() {
        return (DefaultMutableTreeNode)super.getRoot();
    }

    @Override
    public final void invalidate() {
        if (infoState==InfoState.VALID) {
            setInfoState(InfoState.INVALID);
        }
    }

    @Override
    public final boolean isValid() {
        return infoState==InfoState.VALID;
    }

    protected abstract Object[] read()throws IOException;

    @Override
    public final void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    private void setInfoState(OSXLionSidebarTreeModel.InfoState newValue) {
        OSXLionSidebarTreeModel.InfoState oldValue = infoState;
        infoState=newValue;
        propertyChangeSupport.firePropertyChange(VALID_PROPERTY,oldValue== OSXLionSidebarTreeModel.InfoState.VALID,newValue== OSXLionSidebarTreeModel.InfoState.VALID);
    }

    protected abstract void update(Object[] value);

    /**
     * Asynchronously validates the model if it is invalid.
     */
    @Override
    public final void validate() {
        // Prevent multiple invocations of this method by lazyValidate(),
        // while we are validating;
        if (infoState == InfoState.INVALID) {
            setInfoState(InfoState.VALIDATING);

            dispatcher.dispatch(
                    new Worker<Object[]>() {

                        public Object[] construct() throws IOException {
                            return read();
                        }

                        @Override
                        public void done(Object[] value) {
                            update(value);
                        }

                        @Override
                        public void failed(Throwable value) {
                            System.err.println("Warning: SidebarTreeModel failed.");
                            value.printStackTrace();
                            update(null);
                        }


                        @Override
                        protected void finished() {
                            setInfoState(InfoState.VALID);
                        }
                    });
        }
    }


    /**
     * This is used for keeping track of the validation state of this model.
     */
    public enum InfoState {INVALID,VALIDATING,VALID}

    protected static abstract class Node extends DefaultMutableTreeNode implements SidebarTreeFileNode {

        @Override
        public boolean getAllowsChildren() {
            return false;
        }
    }

    public static class SystemItemInfo {

        String name = "";
        int sequenceNumber = 0;
        boolean isVisible = true;

        public String getName() {
            return name;
        }

        public void setName(String value) {
            this.name=value;
        }

        public int getSequenceNumber() {
            return sequenceNumber;
        }

        public void setSequenceNumber(int value) {
            this.sequenceNumber=value;
        }

        public boolean isVisible() {
            return isVisible;
        }

        public void setVisible(boolean value) {
            this.isVisible=value;
        }
    }

    /**
     * Note: SidebaViewToModelNode must not implement Comparable and must
     * not override equals()/hashCode(), because this confuses the layout algorithm
     * in JTree.
     */
    protected static class SidebarViewToModelNode extends Node /*implements Comparable*/ {

        private final FileSystemTreeModel.Node target;

        public SidebarViewToModelNode(FileSystemTreeModel.Node target) {
            this.target = target;
        }

        public Icon getIcon() {
            return target.getIcon();
        }

        public File getResolvedFile() {
            return target.getResolvedFile();
        }

        public FileSystemTreeModel.Node getTarget() {
            return target;
        }

        public String getUserName() {
            return target.getUserName();
        }

        @Override
        public String toString() {
            return target.toString();
        }
    }

    protected  class FileNode extends Node {

        private final File file;
        private final boolean isTraversable;
        /**
         * Holds a Finder label for the file represented by this node.
         * The label is a value in the interval from 0 through 7.
         * The value -1 is used, if the label could not be determined.
         */
        protected int fileLabel = -1;
        private Icon icon;
        private String userName;

        public FileNode(File file) {
            this.file = file;
            // userName = fileChooser.getName(file);
            isTraversable = file.isDirectory();
        }

        public boolean allowsChildren() {
            return false;
        }

        public File getFile() {
            return file;
        }

        public String getFileKind() {
            return null;
        }

        public int getFileLabel() {
            return -1;
        }

        public long getFileLength() {
            return -1;
        }

        public Icon getIcon() {
            if (icon == null) {
                icon = (isTraversable())
                        ? UIManager.getIcon("FileView.directoryIcon")
                        : UIManager.getIcon("FileView.fileIcon");
                //
                if (!UIManager.getBoolean("FileChooser.speed")) {
                    dispatcher.dispatch(new Worker<Icon>() {

                        public Icon construct() {
                            return fileChooser.getIcon(file);
                        }

                        @Override
                        public void done(Icon value) {
                            icon = value;
                            int[] changedIndices = {getParent().getIndex(FileNode.this)};
                            Object[] changedChildren = {FileNode.this};
                            TreeNode[] parentPath = ((DefaultMutableTreeNode) getParent()).getPath();
                            AbstractSidebarTreeModel.this.fireTreeNodesChanged(
                                   AbstractSidebarTreeModel.this,
                                    parentPath,
                                    changedIndices, changedChildren);
                        }
                    });
                }
            }
            return icon;
        }

        public File getResolvedFile() {
            return file;
        }

        public String getUserName() {
            if (userName == null) {
                userName = fileChooser.getName(file);
            }
            return userName;
        }

        public boolean isAcceptable() {
            return true;
        }

        public boolean isTraversable() {
            return isTraversable;
        }

        public boolean isValidating() {
            return false;
        }

        public File lazyGetResolvedFile() {
            return file;
        }

        @Override
        public String toString() {
            return getUserName();
        }
    }

    /**
     * An AliasNode is resolved as late as possible.
     */
    protected class AliasNode extends Node {

        private final byte[] serializedAlias;
        private final String aliasName;
        private final boolean isTraversable;
        /**
         * Holds a Finder label for the file represented by this node.
         * The label is a value in the interval from 0 through 7.
         * The value -1 is used, if the label could not be determined.
         */
        protected int fileLabel = -1;
        private File file;
        private Icon icon;
        private String userName;

        public AliasNode(byte[] serializedAlias, String aliasName) {
            this.file = null;
            this.aliasName = aliasName;
            this.serializedAlias = serializedAlias;
            isTraversable = true;
        }

        public Icon getIcon() {
            if (icon == null) {
                // Note: We clear this icon, when we resolve the alias
                icon = (isTraversable())
                        ? UIManager.getIcon("FileView.directoryIcon")
                        : UIManager.getIcon("FileView.fileIcon");
                //
                if (file != null && !UIManager.getBoolean("FileChooser.speed")) {
                    dispatcher.dispatch(new Worker<Icon>() {

                        public Icon construct() {
                            return fileChooser.getIcon(file);
                        }

                        @Override
                        public void done(Icon value) {
                            icon = value;

                            int[] changedIndices = new int[]{getParent().getIndex(AliasNode.this)};
                            Object[] changedChildren = new Object[]{AliasNode.this};
                           AbstractSidebarTreeModel.this.fireTreeNodesChanged(
                                    AbstractSidebarTreeModel.this,
                                    ((DefaultMutableTreeNode) getParent()).getPath(),
                                    changedIndices, changedChildren);
                        }
                    });
                }
            }
            return icon;
        }

        public File getResolvedFile() {
            if (file == null) {
                icon = null; // clear cached icon!
                file = OSXFile.resolveAlias(serializedAlias, false);
            }
            return file;
        }

        public String getUserName() {
            if (userName == null) {
                if (file != null) {
                    userName = fileChooser.getName(file);
                }
            }
            return (userName == null) ? aliasName : userName;
        }

        public boolean isTraversable() {
            return isTraversable;
        }

        @Override
        public String toString() {
            return getUserName();
        }
    }

    protected TreePath getVolumesPath() {
        return volumesPath;
    }
    protected abstract void updateDevices();
}
