/*
 * @(#)SubtreeFileChooserUI.java
 * Quaqua Look and Feel. Copyright 2020 © Werner Randelshofer, Switzerland. MIT License.
 */

package ch.randelshofer.quaqua.filechooser;

import java.io.File;

/**
 * SubtreeFileChooserUI for filechoosers which can change their filesystem root or select a directory within that
 * subtree.
 *
 * @author Werner Randelshofer
 * @version 1.0 2010-08-20 Created.
 */
public interface SubtreeFileChooserUI {
    /** Sets the root directory of the subtree. */
    public void setRootDirectory(File file);

    /**
     * Set the current directory. If possible, the subtree root is not changed. Otherwise, the specified directory
     * becomes the new subtree root.
     */
    public void selectDirectory(File file);
}
