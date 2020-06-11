/*
 * @(#)SidebarTreeFileNode.java
 * Quaqua Look and Feel. Copyright © 2020 Werner Randelshofer, Switzerland. MIT License.
 */
package ch.randelshofer.quaqua.filechooser;

import javax.swing.Icon;
import java.io.File;

/**
 * These are the methods used by other parts of Quaqua on sidebar tree nodes that represent files.
 * (Previously all FileInfo methods were nominally supported.)
 */

public interface SidebarTreeFileNode {
    /**
     * Returns the resolved file object.
     */
    public File getResolvedFile();

    /**
     * Returns the user name of the file.
     */
    public String getUserName();

    /**
     * Returns the icon of the file.
     * Returns a proxy icon if the real icon has not yet been fetched from the
     * file system.
     */
    public Icon getIcon();
}
