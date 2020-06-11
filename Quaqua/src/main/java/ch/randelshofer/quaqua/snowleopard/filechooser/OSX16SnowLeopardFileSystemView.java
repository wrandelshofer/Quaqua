/*
 * @(#)OSX16SnowLeopardFileSystemView.java
 * Quaqua Look and Feel. Copyright © 2020 Werner Randelshofer, Switzerland. MIT License.
 */
package ch.randelshofer.quaqua.snowleopard.filechooser;

import ch.randelshofer.quaqua.filechooser.BasicOSXFileSystemView;

import java.io.File;
import java.util.Arrays;
import java.util.List;

/**
 * OSX16SnowLeopardFileSystemView.
 *
 * @author Werner Randelshofer
 * @version $Id$
 */
public class OSX16SnowLeopardFileSystemView extends BasicOSXFileSystemView {

    public OSX16SnowLeopardFileSystemView() {

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
                "\u0003\u0002\u0001Move&Rename",
        };

        hiddenTopLevelNames.addAll(Arrays.asList(names));
        names = new String[]{
                "$RECYCLE.BIN",
                "Thumbs.db",
                "desktop.ini",
        };

        hiddenDirectoryNames.addAll(Arrays.asList(names));
    }

    @Override
    protected List<File> getRootList() {

        /*
          Network folder was not included. Is this intentional?
        */

        List<File> roots = super.getRootList();
        roots.remove(networkFolder);
        return roots;
    }
}
