/*
 * @(#)QuaquaToolBarButtonStateBorder.java
 * Quaqua Look and Feel. Copyright © 2020 Werner Randelshofer, Switzerland. MIT License.
 */

package ch.randelshofer.quaqua;

import ch.randelshofer.quaqua.border.ButtonStateBorder;

import javax.swing.AbstractButton;
import javax.swing.ButtonModel;
import javax.swing.border.Border;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;

/**
 * ToolbarButtonStateBorder.
 *
 * @author Werner Randelshofer
 * @version 1.1 2005-11-30 Constructor with tiled image added.
 * <br>1.0  29 March 2005  Created.
 */
public class QuaquaToolBarButtonStateBorder extends ButtonStateBorder {
    private boolean isRollover;

    public QuaquaToolBarButtonStateBorder(Border e, Border ep, Border es, Border eps, Border d, Border ds, Border i, Border is, Border di, Border dis, boolean isRollover) {
        super(e, ep, es, eps, d, ds, i, is, di, dis);
    }

    /**
     * Creates a new instance.
     * All borders must have the same insets.
     */
    public QuaquaToolBarButtonStateBorder(Border[] borders, boolean isRollover) {
        super(borders);
        this.isRollover = isRollover;
    }

    /**
     * Creates a new instance.
     * All icons must have the same dimensions.
     */
    public QuaquaToolBarButtonStateBorder(Image[] images, Insets borderInsets, Insets imageInsets, boolean fill, boolean isRollover) {
        super(images, borderInsets, imageInsets, fill);
        this.isRollover = isRollover;
    }

    /**
     * Creates a new instance.
     * All borders must have the same dimensions.
     */
    public QuaquaToolBarButtonStateBorder(Image tiledImage, int tileCount, boolean isTiledHorizontaly,
                                          Insets imageInsets, Insets borderInsets, boolean fill, boolean isRollover) {
        super(tiledImage, tileCount, isTiledHorizontaly, imageInsets, borderInsets, fill);
        this.isRollover = isRollover;
    }

    public void paintBorder(Component c, Graphics gr, int x, int y, int width, int height) {
        boolean paint = false;
        if (c instanceof AbstractButton) {
            ButtonModel model = ((AbstractButton) c).getModel();

            if (isRollover) {
                paint = model.isRollover() || model.isSelected();
            } else {
                paint = model.isSelected();
            }
        }
        if (paint) {
            super.paintBorder(c, gr, x, y, width, height);
        }
    }
}
