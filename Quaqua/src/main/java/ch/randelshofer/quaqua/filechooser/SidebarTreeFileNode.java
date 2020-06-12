/*
 * @(#)SidebarTreeFileNode.java
 * Quaqua Look and Feel. Copyright © 2020 Werner Randelshofer, Switzerland. MIT License.
 */
package ch.randelshofer.quaqua.filechooser;

import ch.randelshofer.quaqua.osx.OSXFile;

import javax.swing.Icon;
import java.io.File;

/**
 * These are the methods used by other parts of Quaqua on sidebar tree nodes that represent files.
 * (Previously all FileInfo methods were nominally supported.)
 * <p>
 * A SidebarTreeFileNode must provide information about a file without blocking
 * the AWT event dispatcher thread. All methods in this class return values
 * without performing IO. It is possible that subsequent calls to the same
 * method return different values, because they are updated asynchronously.
 */

public interface SidebarTreeFileNode {


    /**
     * Returns the resolved file object.
     */
    File getResolvedFile();

    /**
     * Returns the user name of the file.
     */
    String getUserName();

    /**
     * Returns the icon of the file.
     * Returns a proxy icon if the real icon has not yet been fetched from the
     * file system.
     */
    Icon getIcon();

    /**
     * Returns true if the file is traversable, and should thus be treated
     * like a folder.
     *
     * @return true if traversable
     */
    boolean isTraversable();

    /**
     * Returns the extended file type, so that we can display a special
     * icon in the sidebar.
     *
     * @return the extended file type or null
     */
     OSXFile.ExtendedFileType getExtendedFileType();


}