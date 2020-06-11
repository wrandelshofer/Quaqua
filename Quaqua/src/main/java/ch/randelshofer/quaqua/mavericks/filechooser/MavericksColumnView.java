/*
 * @(#)MavericksColumnView.java
 * Quaqua Look and Feel. Copyright © 2020 Werner Randelshofer, Switzerland. MIT License.
 */
package ch.randelshofer.quaqua.mavericks.filechooser;

import ch.randelshofer.quaqua.lion.filechooser.LionColumnView;

import javax.swing.JFileChooser;

/**
 * The file chooser column view for Mavericks.
 */

public class MavericksColumnView extends LionColumnView {

    public MavericksColumnView(JFileChooser fc) {
        super(fc);

        browser.setFixedCellWidth(207);
    }
}
