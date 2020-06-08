/*
 * @(#)QuaquaLionNativeTabBorder.java
 * Quaqua Look and Feel. Copyright 2020 © Werner Randelshofer, Switzerland. MIT License.
 */
package ch.randelshofer.quaqua.lion;

import ch.randelshofer.quaqua.border.QuaquaNativeBorder;
import ch.randelshofer.quaqua.osx.OSXAquaPainter.Widget;
import java.awt.Insets;

/**
 * {@code QuaquaLionNativeTabBorder}.
 *
 * @author Werner Randelshofer
 * @version $Id$
 */
public class QuaquaLionNativeTabBorder extends QuaquaNativeBorder {

    public QuaquaLionNativeTabBorder(int cacheSize, Insets imageInsets, Insets borderInsets) {
        super(cacheSize, Widget.tab, imageInsets, borderInsets);
    }

    public QuaquaLionNativeTabBorder(Insets imageInsets, Insets borderInsets) {
        super(Widget.tab,  imageInsets, borderInsets);
    }

    public QuaquaLionNativeTabBorder(int cacheSize) {
        super(cacheSize, Widget.tab);
    }

    public QuaquaLionNativeTabBorder() {
        super(Widget.tab);
    }

}
