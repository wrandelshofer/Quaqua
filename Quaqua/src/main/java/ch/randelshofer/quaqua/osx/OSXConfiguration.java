/*
 * @(#)OSXConfiguration.java
 * Quaqua Look and Feel. Copyright © 2020 Werner Randelshofer, Switzerland. MIT License.
 */
package ch.randelshofer.quaqua.osx;

import ch.randelshofer.quaqua.QuaquaManager;

/**
 * UI configuration options
 */
public class OSXConfiguration {

    private static boolean isRequestFocusEnabled;
    private static boolean isFullKeyboardAccess;
    private static boolean isFileHidingEnabled;
    private static boolean isQuickLookEnabled;
    private static boolean isInitialized;

    public static boolean isRequestFocusEnabled() {
        ensureInitialized();
        return isRequestFocusEnabled;
    }

    /**
     * Indicate whether full keyboard access is enabled. When enabled, all controls are focusable, not just lists and
     * text fields.
     */
    public static boolean isFullKeyboardAccess() {
        ensureInitialized();
        return isFullKeyboardAccess;
    }


    public static boolean isFileHidingEnabled() {
        ensureInitialized();
        return isFileHidingEnabled;
    }

    public static boolean isIsQuickLookEnabled() {
        ensureInitialized();
        return isQuickLookEnabled;
    }


    private static void ensureInitialized() {

        if (isInitialized) {
            return;
        }

        isInitialized = true;

        {
            String prefValue = QuaquaManager.getProperty("Quaqua.requestFocusEnabled", "false");
            isRequestFocusEnabled = Boolean.valueOf(prefValue);
        }

        {
            String prefValue = OSXPreferences.getString(OSXPreferences.GLOBAL_PREFERENCES, "AppleKeyboardUIMode", "0");
            int intValue;
            try {
                intValue = Integer.valueOf(prefValue);
            } catch (NumberFormatException e) {
                intValue = 0;    // default: Full Keyboard Access is OFF
            }
            isFullKeyboardAccess = isRequestFocusEnabled || ((intValue & 2) == 2);
        }

        {
            String prefValue = OSXPreferences.getString(
                    OSXPreferences.FINDER_PREFERENCES, "AppleShowAllFiles", "false")
                    .toLowerCase();
            isFileHidingEnabled = prefValue.equals("false") || prefValue.equals("no");
        }

        isQuickLookEnabled = Boolean.valueOf(QuaquaManager.getProperty("Quaqua.FileChooser.quickLookEnabled", "true"));
    }
}
