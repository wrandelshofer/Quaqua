/*
 * @(#)JNILoader.java
 * Quaqua Look and Feel. Copyright 2020 © Werner Randelshofer, Switzerland. MIT License.
 */
package ch.randelshofer.quaqua.osx;

/**
 * {@code JNILoader}.
 *
 * @author Werner Randelshofer
 * @version 1.0 2013-03-21 Created.
 */
public class JNILoader {
    public static void loadLibrary(String libName) {
        //Try to load the native library assuming the java.library.path was
        //set correctly at launch.
        //try {
        System.loadLibrary(libName);
        /*} catch (Error e) {
            JFrame f=new JFrame();
            JTextArea l=new JTextArea();
            StringWriter w=new StringWriter();
            e.printStackTrace(new PrintWriter(w));
            l.setText(w.toString());
            f.add(new JScrollPane(l));
            f.pack();
            f.show();
            throw e;
        }*/
    }
}