/*
 * @(#)OSXClipboardTransferable.java
 * 
 * Copyright (c) 2009 Werner Randelshofer
 * Staldenmattweg 2, Immensee, CH-6405, Switzerland.
 * All rights reserved.
 * 
 * The copyright of this software is owned by Werner Randelshofer. 
 * You may not use, copy or modify this software, except in  
 * accordance with the license agreement you entered into with  
 * Werner Randelshofer. For details see accompanying license terms.
 */
package ch.randelshofer.quaqua.osx;

import ch.randelshofer.quaqua.QuaquaManager;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.AccessControlException;

/**
 * OSXClipboardTransferable provides read access to the raw contents
 * of the Mac OS X system clipboard.
 * <p>
 * All data flavors have the mime type {@code application/octet-stream; type=...}.
 * <p>
 * The type parameter of the data flavor holds the UTF-8 URL_encoded native type
 * identifier. The human presentable name field of the data flavor holds the
 * non-encoded native type identifier.
 * <p>
 * The system clipboard data is retrieved using the Cocoa class
 * <a href="http://developer.apple.com/documentation/Cocoa/Reference/ApplicationKit/Classes/NSPasteboard_Class/Reference/Reference.html#//apple_ref/occ/instm/NSPasteboard/dataForType:"
 * >NSPasteboard</a>.
 *
 * @author Werner Randelshofer
 * @version $Id: OSXClipboardTransferable.java 82 2009-06-11 08:57:33Z wrandelshofer $
 */
public class OSXClipboardTransferable implements Transferable {

    /**
     * This variable is set to true, if native code is available.
     */
    private static Boolean isNativeCodeAvailable;
    private static int EXPECTED_NATIVE_CODE_VERSION = 2;

    /**
     * Returns true if native code is available.
     * This method also loads the native code.
     */
    public static boolean isNativeCodeAvailable() {
        if (isNativeCodeAvailable == null) {
            synchronized (OSXClipboardTransferable.class) {
                if (isNativeCodeAvailable == null) {
                    boolean success = false;
                    try {
                        String value = QuaquaManager.getProperty("Quaqua.jniIsPreloaded");
                        if (value == null) {
                            value = QuaquaManager.getProperty("Quaqua.JNI.isPreloaded");
                        }
                        String libraryName = null;
                        if (value != null && value.equals("true")) {
                            success = true;
                        } else {

                            // Use quaqua64 JNI-lib on x86_64 processors on Mac OS X 10.5 and higher
                            libraryName = (QuaquaManager.getOS() >= QuaquaManager.LEOPARD) &&
                                    QuaquaManager.getProperty("os.arch").equals("x86_64") ? "quaqua64" : "quaqua";
                            try {
                                System.loadLibrary(libraryName);
                                success = true;
                            } catch (UnsatisfiedLinkError e) {
                                System.err.println("Warning: " + OSXClipboardTransferable.class + " couldn't load library \"" + libraryName + "\". " + e);
                                success = false;
                            } catch (AccessControlException e) {
                                System.err.println("Warning: " + OSXClipboardTransferable.class + " access controller denied loading library \"" + libraryName + "\". " + e);
                                success = false;
                            } catch (Throwable e) {
                                e.printStackTrace();
                                System.err.println("Warning: " + OSXClipboardTransferable.class + " couldn't load library \"" + libraryName + "\". " + e);
                                success = false;
                            }
                        }

                        if (success) {
                            try {
                                int nativeCodeVersion = nativeGetNativeCodeVersion();
                                if (nativeCodeVersion != EXPECTED_NATIVE_CODE_VERSION) {
                                    System.err.println("Warning: " + OSXClipboardTransferable.class + " can't use library " + libraryName + ". It has version " + nativeCodeVersion + " instead of " + EXPECTED_NATIVE_CODE_VERSION);
                                    success = false;
                                }
                            } catch (UnsatisfiedLinkError e) {
                                System.err.println("Warning: " + OSXClipboardTransferable.class + " could load library " + libraryName + " but can't use it. " + e);
                                success = false;
                            }
                        }

                    } finally {
                        isNativeCodeAvailable = Boolean.valueOf(success);
                    }
                }
            }
        }
        return isNativeCodeAvailable == Boolean.TRUE;
    }

    /** An array of String objects containing the types of data declared for the
     * current contents of the clipboard. The returned types are listed in the
     * order they were declared.
     */
    private native static String[] nativeGetTypes();

    /** Returns the data for the specified type.
     *
     * @param dataType The type of data you want to read from the pasteboard.
     * This value should be one of the types returned by #getTypes.
     */
    private native static byte[] nativeGetDataForType(String dataType);

    /**
     * Returns the version of the native code library. If the version
     * does not match with the version that we expect, we can not use
     * it.
     * @return The version number of the native code.
     */
    private static native int nativeGetNativeCodeVersion();

    /** Returns the data flavors which are currently in the NSPasteboard.
     * The mime type of all flavors is application/octet-stream. The actual
     * type information is in the human presentable name!
     */
    public DataFlavor[] getTransferDataFlavors() {
        String[] types = OSXClipboardTransferable.nativeGetTypes();

        if (types == null) {

            return new DataFlavor[0];
        } else {
            DataFlavor[] flavors = new DataFlavor[types.length];

            for (int i = 0; i < types.length; i++) {
                try {
                    flavors[i] = new DataFlavor("application/octet-stream; type=" + URLEncoder.encode(types[i], "UTF-8"), types[i]);
                } catch (UnsupportedEncodingException ex) {
                    InternalError ie = new InternalError("URLEncoder does not support UTF-8");
                    ie.initCause(ex);
                    throw ie;
                }
            }
            return flavors;
        }
    }

    /** Returns true if the "General Clipboard" Cocoa NSPasteboard currently
     * supports the specified data flavor.
     */
    public boolean isDataFlavorSupported(DataFlavor flavor) {
        DataFlavor[] f = getTransferDataFlavors();
        for (int i = 0; i < f.length; i++) {
            if (f[i].equals(flavor)) {
                return true;
            }
        }
        return false;
    }

    /** Reads the data from the "General Clipboard" Cocoa NSPasteboard.
     * If the data flavor is supported, always returns it as a byte array. */
    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
        if (flavor == null) {
            throw new NullPointerException("flavor");
        }

        String type = URLDecoder.decode(flavor.getParameter("type"), "UTF-8");

        byte[] data = nativeGetDataForType(type);

        if (data == null) {
            throw new UnsupportedFlavorException(flavor);
        }
        return data;
    }

}
