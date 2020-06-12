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

    public OSXFinderDefaults(){
        String favoriteItemsDictionaryName = "favorites";
        File[] defaultUserItems;
        if (QuaquaManager.isOSX()
                || QuaquaManager.getOS() == QuaquaManager.DARWIN) {
            defaultUserItems = new File[]{
                    new File(QuaquaManager.getProperty("user.home"), "Desktop"),
                    new File(QuaquaManager.getProperty("user.home"), "Documents"),
                    new File(Objects.requireNonNull(QuaquaManager.getProperty("user.home")))
            };

            int osVersion = QuaquaManager.getOS();
            if (QuaquaManager.MAVERICKS <= osVersion
                    && osVersion < QuaquaManager.CATALINA) {
                favoriteItemsDictionaryName = "favoriteitems";
            }
        } else if (QuaquaManager.getOS() == QuaquaManager.WINDOWS) {
            defaultUserItems = new File[]{
                    new File(QuaquaManager.getProperty("user.home"), "Desktop"),
                    // Japanese ideographs for Desktop:
                    new File(QuaquaManager.getProperty("user.home"), "\u684c\u9762"),
                    new File(QuaquaManager.getProperty("user.home"), "My Documents"),
                    new File(Objects.requireNonNull(QuaquaManager.getProperty("user.home")))
            };
        } else if (QuaquaManager.getOS() == QuaquaManager.LINUX) {
            defaultUserItems = new File[]{
                    new File(QuaquaManager.getProperty("user.home"), "Desktop"),
                    new File("/media"),
                    new File(QuaquaManager.getProperty("user.home"), "Documents"),
                    new File(Objects.requireNonNull(QuaquaManager.getProperty("user.home")))
            };
        } else {
            defaultUserItems = new File[]{
                    new File(Objects.requireNonNull(QuaquaManager.getProperty("user.home")))
            };
        }
        this.favoriteItemsDictionaryName = favoriteItemsDictionaryName;
        this.defaultUserItems= Collections.unmodifiableList(Arrays.asList(defaultUserItems));
    }

    public List<File> getDefaultUserItems() {
        return defaultUserItems;
    }

    public String getFavoriteItemsDictionaryName() {
        return favoriteItemsDictionaryName;
    }
}
