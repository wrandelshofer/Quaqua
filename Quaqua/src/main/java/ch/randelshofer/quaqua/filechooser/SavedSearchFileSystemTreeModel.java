/*
 * @(#)SavedSearchFileSystemTreeModel.java
 * Quaqua Look and Feel. Copyright © 2020 Werner Randelshofer, Switzerland. MIT License.
 */
/*
 * Copyright © 2014 Alan Snyder.
 */
package ch.randelshofer.quaqua.filechooser;

import ch.randelshofer.quaqua.osx.OSXFile;

import javax.swing.JFileChooser;
import javax.swing.tree.TreePath;
import java.io.File;

/**
 * A file system model for the results of a saved search.
 */
public class SavedSearchFileSystemTreeModel extends FileSystemTreeModel {

    private File savedSearchFile;

    public SavedSearchFileSystemTreeModel(JFileChooser fc, File savedSearchFile) {
        super(fc);
        this.savedSearchFile = savedSearchFile;
        root = new SavedSearchNode(savedSearchFile, false);
    }

    public TreePath toPath(File file, TreePath templatePath) {

        /*
          When this method is called, templatePath should be null or the root path and file should be absolute.
        */

        if (savedSearchFile.equals(file)) {
            return new TreePath(getRoot());
        }

        return searchForFile(root, file);
    }

    private TreePath searchForFile(FileSystemTreeModel.Node node, File f) {
        File nf = node.getResolvedFile();
        if (nf != null) {
            if (f.equals(nf)) {
                return new TreePath(node.getPath());
            }
            int count = node.getChildCount();
            for (int index = 0; index < count; index++) {
                FileSystemTreeModel.Node child = (FileSystemTreeModel.Node) node.getChildAt(index);
                if (child != null) {
                    TreePath path = searchForFile(child, f);
                    if (path != null) {
                        return path;
                    }
                }
            }
        }
        return null;
    }

    /**
     * A node representing a saved search. A saved search is represented as a file. However, to the user it appears
     * on the sidebar and acts like a root directory.
     */
    public class SavedSearchNode extends DirectoryNode {

        public SavedSearchNode(File savedSearchFile, boolean isHidden) {
            super(savedSearchFile, isHidden,true);
        }

        @Override
        public String getFileKind() {
            return "saved search";
        }

        @Override
        public boolean isAlias() {
            return false;
        }

        @Override
        protected File[] getFiles() {
            File savedSearchFile = getFile();

            File[] files = OSXFile.executedSavedSearch(savedSearchFile);

            if (DEBUG) {
                String msg = "SavedSearchNode getFiles " + savedSearchFile;
                if (files != null) {
                    msg += " returned " + files.length + " file(s)";
                } else {
                    msg += " failed";
                }

                System.out.println(msg);
            }

            return files;
        }
    }
}
