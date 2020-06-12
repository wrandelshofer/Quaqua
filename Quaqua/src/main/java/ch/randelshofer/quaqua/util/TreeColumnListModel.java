/*
 * @(#)TreeColumnListModel.java
 * Quaqua Look and Feel. Copyright © 2020 Werner Randelshofer, Switzerland. MIT License.
 */

package ch.randelshofer.quaqua.util;

import javax.swing.AbstractListModel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

/**
 * This is the list model used to map a tree node of the {@code treeModel}
 * to a JList displaying its children.
 */
public class TreeColumnListModel  extends AbstractListModel implements TreeModelListener {

        private TreePath path;
        private TreeModel model;
        /**
         * We need to copy the number of children of the underlying tree node
         * into this instance variable, because we have to generate a proper
         * interval added/interval removed even upon a change in the tree structure.
         */
        private int size;

        @Override
        public String toString() {
            StringBuilder buf = new StringBuilder();
            buf.append('{');
            for (int i = 0, n = getSize(); i < n; i++) {
                if (i != 0) {
                    buf.append(',');
                }
                buf.append(getElementAt(i));
            }
            buf.append("} #");
            buf.append(hashCode());
            return buf.toString();
        }

        public TreeColumnListModel(TreePath path, TreeModel model) {
            this.path = path;
            this.model = model;
            model.addTreeModelListener(this);
            updateSize();
        }

        public void setPath(TreePath newValue) {
            if (newValue != path) {
                int oldSize = getSize();
                this.path = newValue;
                updateSize();
                int newSize = getSize();
                if (Math.min(oldSize, newSize) > 0) {
                    fireContentsChanged(this, Math.min(oldSize, newSize), Math.min(oldSize, newSize));
                }
                if (newSize < oldSize) {
                    fireIntervalRemoved(this, newSize, oldSize - 1);
                } else if (newSize > oldSize) {
                    fireIntervalAdded(this, oldSize, newSize - 1);
                }
            }
        }

        public void dispose() {
            model.removeTreeModelListener(this);
        }

        public int getSize() {
            return size;
            //return model.getChildCount(path.getLastPathComponent());
        }

        private void updateSize() {
            this.size = model.getChildCount(path.getLastPathComponent());
        }

        public Object getElementAt(int row) {
           return model.getChild(path.getLastPathComponent(), row);
        }

        public void treeNodesChanged(TreeModelEvent e) {
            if (e.getTreePath().equals(path)) {
                int[] indices = e.getChildIndices();
                fireContentsChanged(this, indices[0], indices[indices.length - 1]);
            }
        }

        public void treeNodesInserted(TreeModelEvent e) {
            if (e.getTreePath().equals(path)) {
                updateSize();

                // We analyze the indices for contiguous intervals
                // and fire interval added events for all intervals we find.
                int[] indices = e.getChildIndices();

                int start = 0;
                int startIndex;
                int end;
                do {
                    startIndex = indices[start];
                    for (end = start + 1; end < indices.length; end++) {
                        if (indices[end] != startIndex + end - start) {
                            break;
                        }
                    }
                    fireIntervalAdded(this, startIndex, indices[end - 1]);
                    start = end;
                } while (start < indices.length);

            }

        }

        public TreePath getPath(){
            return path;
        }

        public void treeNodesRemoved(TreeModelEvent e) {
            if (e.getTreePath().equals(path)) {
                updateSize();

                // We analyze the indices for contiguous intervals
                // and fire interval removed events for all intervals we find.
                int[] indices = e.getChildIndices();
                int start = 0;
                int startIndex;
                int end;
                int offset = 0;
                do {
                    startIndex = indices[start];
                    for (end = start + 1; end < indices.length; end++) {
                        if (indices[end] != startIndex + end - start) {
                            break;
                        }
                    }
                    fireIntervalRemoved(this, startIndex - offset, indices[end - 1] - offset);
                    offset += indices[end - 1] - startIndex + 1;
                    start = end;
                } while (start < indices.length);
            }
        }

        public void treeStructureChanged(TreeModelEvent e) {
            TreePath changedPath = e.getTreePath();
            if (changedPath.equals(path) || path.getPathCount() == 1 && changedPath.getPathCount() == 1) {
                int oldSize = getSize();
                path = changedPath;
                updateSize();
                int newSize = getSize();
                path = changedPath;
                int diff = newSize - oldSize;
                if (diff < 0) {
                    if (newSize > 0) {
                        fireContentsChanged(this, 0, newSize - 1);
                    }
                    fireIntervalRemoved(this, newSize, oldSize - 1);
                } else if (diff > 0) {
                    if (oldSize > 0) {
                        fireContentsChanged(this, 0, oldSize - 1);
                    }
                    fireIntervalAdded(this, oldSize, newSize - 1);
                } else {
                    fireContentsChanged(this, 0, oldSize - 1);
                }

            }
        }

    }
