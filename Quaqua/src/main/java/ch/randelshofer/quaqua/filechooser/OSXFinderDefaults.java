/*
 * @(#)OSSpecificDefaults.java
 * Quaqua Look and Feel. Copyright © 2020 Werner Randelshofer, Switzerland. MIT License.
 */

package ch.randelshofer.quaqua.filechooser;

import ch.randelshofer.quaqua.QuaquaManager;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Provides operating system specific default values of the OS X Finder
 * application.
 */
public class OSXFinderDefaults {
    /**
     * The defaultUserItems are used when we fail to read the user items from
     * the sidebarFile.
     */
    private final List<File> defaultUserItems;

    /**
     * The name of the dictionary where favorite items are stored. OS version dependent.
     */
    private final String favoriteItemsDictionaryName;

    /**
     * This file contains information about the system list and holds the aliases
     * for the user list.
     */
    private final File sidebarFile;
    private final File favoriteItemsFile;
    private final File favoriteServersFile;
    private final File favoriteVolumesFile;
    private final File iCloudItemsFile;
    private final File projectItemsFile;


    public OSXFinderDefaults() {
        String favoriteItemsDictionaryName = "favorites";
        File[] defaultUserItems;
        int os = QuaquaManager.getOS();
        if (QuaquaManager.isOSX()
                || os == QuaquaManager.DARWIN) {
            defaultUserItems = new File[]{
                    new File(QuaquaManager.getProperty("user.home"), "Desktop"),
                    new File(QuaquaManager.getProperty("user.home"), "Documents"),
                    new File(Objects.requireNonNull(QuaquaManager.getProperty("user.home")))
            };

            if (QuaquaManager.MAVERICKS <= os
                    && os < QuaquaManager.CATALINA) {
                favoriteItemsDictionaryName = "favoriteitems";
            }
            if (os < QuaquaManager.CATALINA) {
                sidebarFile = new File(QuaquaManager.getProperty("user.home"), "Library/Preferences/com.apple.sidebarlists.plist");
                favoriteItemsFile =
                        favoriteServersFile =
                                favoriteVolumesFile =
                                        iCloudItemsFile =
                                                projectItemsFile = null;
            } else {
                sidebarFile = null;
                favoriteItemsFile = new File(QuaquaManager.getProperty("user.home"), "/Library/Application Support/com.apple.sharedfilelist/com.apple.LSSharedFileList.FavoriteItems.sfl2");
                favoriteServersFile = new File(QuaquaManager.getProperty("user.home"), "/Library/Application Support/com.apple.sharedfilelist/com.apple.LSSharedFileList.FavoriteServers.sfl2");
                favoriteVolumesFile = new File(QuaquaManager.getProperty("user.home"), "/Library/Application Support/com.apple.sharedfilelist/com.apple.LSSharedFileList.FavoriteVolumes.sfl2");
                iCloudItemsFile = new File(QuaquaManager.getProperty("user.home"), "/Library/Application Support/com.apple.sharedfilelist/com.apple.LSSharedFileList.iCloudItems.sfl2");
                projectItemsFile = new File(QuaquaManager.getProperty("user.home"), "/Library/Application Support/com.apple.sharedfilelist/com.apple.LSSharedFileList.ProjectsItems.sfl2");
            }
        } else if (os == QuaquaManager.WINDOWS) {
            defaultUserItems = new File[]{
                    new File(QuaquaManager.getProperty("user.home"), "Desktop"),
                    // Japanese ideographs for Desktop:
                    new File(QuaquaManager.getProperty("user.home"), "\u684c\u9762"),
                    new File(QuaquaManager.getProperty("user.home"), "My Documents"),
                    new File(Objects.requireNonNull(QuaquaManager.getProperty("user.home")))
            };
            sidebarFile = null;
            favoriteItemsFile =
                    favoriteServersFile =
                            favoriteVolumesFile =
                                    iCloudItemsFile =
                                            projectItemsFile = null;
        } else if (os == QuaquaManager.LINUX) {
            defaultUserItems = new File[]{
                    new File(QuaquaManager.getProperty("user.home"), "Desktop"),
                    new File("/media"),
                    new File(QuaquaManager.getProperty("user.home"), "Documents"),
                    new File(Objects.requireNonNull(QuaquaManager.getProperty("user.home")))
            };
            sidebarFile =
                    favoriteItemsFile =
                            favoriteServersFile =
                                    favoriteVolumesFile =
                                            iCloudItemsFile =
                                                    projectItemsFile = null;
        } else {
            defaultUserItems = new File[]{
                    new File(Objects.requireNonNull(QuaquaManager.getProperty("user.home")))
            };

            sidebarFile =
                    favoriteItemsFile =
                            favoriteServersFile =
                                    favoriteVolumesFile =
                                            iCloudItemsFile =
                                                    projectItemsFile = null;
        }
        this.favoriteItemsDictionaryName = favoriteItemsDictionaryName;
        this.defaultUserItems = Collections.unmodifiableList(Arrays.asList(defaultUserItems));
    }

    public List<File> getDefaultUserItems() {
        return defaultUserItems;
    }

    public String getFavoriteItemsDictionaryName() {
        return favoriteItemsDictionaryName;
    }

    public File getSidebarFile() {
        return sidebarFile;
    }
}
