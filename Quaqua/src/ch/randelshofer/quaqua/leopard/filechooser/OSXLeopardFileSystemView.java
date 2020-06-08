/*
 * @(#)OSXLeopardFileSystemView.java
 * Quaqua Look and Feel. Copyright © 2020 Werner Randelshofer, Switzerland. MIT License.
 */
package ch.randelshofer.quaqua.leopard.filechooser;

import ch.randelshofer.quaqua.filechooser.*;
import java.util.*;

/**
 * OSXLeopardFileSystemView.
 *
 * @author Werner Randelshofer
 * @version $Id$
 */
public class OSXLeopardFileSystemView extends BasicOSXFileSystemView {

    public OSXLeopardFileSystemView() {

        String[] names = {
            "AppleShare PDS",
            "automount",
            "bin",
            "Cleanup At Startup",
            "cores",
            "Desktop DB",
            "Desktop DF",
            "dev",
            "etc",
            "home",
            "mach",
            "mach_kernel",
            "mach_kernel.ctfsys",
            "mach.sym",
            "net",
            "opt",
            "private",
            "sbin",
            "Temporary Items",
            "TheVolumeSettingsFolder",
            "TheFindByContentFolder",
            "tmp",
            "Trash",
            "usr",
            "var",
            "Volumes",
            "\u0003\u0002\u0001Move&Rename",};

        hiddenTopLevelNames.addAll(Arrays.asList(names));

        names = new String[]{
                    "$RECYCLE.BIN",
                    "Thumbs.db",
                    "desktop.ini",};

        hiddenDirectoryNames.addAll(Arrays.asList(names));
    }
}
