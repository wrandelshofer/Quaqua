/*
 * @(#)ButtonFocusIcon.java
 * Quaqua Look and Feel. Copyright © 2020 Werner Randelshofer, Switzerland. MIT License.
 */

package ch.randelshofer.quaqua.icon;


import ch.randelshofer.quaqua.QuaquaUtilities;

import javax.swing.AbstractButton;
import javax.swing.ButtonModel;
import javax.swing.Icon;
import java.awt.Component;
import java.awt.Image;

/**
 * A focus ring icon with different visuals reflecting the selected state of an
 * abstract button.
 *
 * @author Werner Randelshofer
 * @version $Id$
 */
public class ButtonFocusIcon extends MultiIcon {
    private final static int E = 0;
    private final static int S = 1;

    /**
     * Creates a new instance.
     * All icons must have the same dimensions.
     * If an icon is null, nothing is drawn for this state.
     */
    public ButtonFocusIcon(Icon e, Icon s) {
        super(new Icon[]{e, s});
    }

    /**
     * Creates a new instance.
     * All icons must have the same dimensions.
     * If an icon is null, nothing is drawn for this state.
     */
    public ButtonFocusIcon(Image[] images) {
        super(images);
    }

    /**
     * Creates a new instance.
     * All icons must have the same dimensions.
     * If an icon is null, nothing is drawn for this state.
     */
    public ButtonFocusIcon(Icon[] icons) {
        super(icons);
    }

    /**
     * Creates a new instance.
     * The icon representations are created lazily from the image.
     */
    public ButtonFocusIcon(Image tiledImage, int tileCount, boolean isTiledHorizontaly) {
        super(tiledImage, tileCount, isTiledHorizontaly);
    }

    protected Icon getIcon(Component c) {
        Icon icon = null;
        if (QuaquaUtilities.isFocused(c) && c.isEnabled()
                && (!(c instanceof AbstractButton) || ((AbstractButton) c).isFocusPainted())) {
            ButtonModel model = ((AbstractButton) c).getModel();
            if (model.isSelected()) {
                icon = icons[S];
            } else {
                icon = icons[E];
            }
        }
        return icon;
    }

    protected void generateMissingIcons() {
        if (icons.length != 2) {
            Icon[] newIcons = new Icon[2];
            System.arraycopy(icons, 0, newIcons, 0, Math.min(icons.length, 2));
            icons = newIcons;
        }

        if (icons[S] == null) {
            icons[S] = icons[E];
        }
    }
}
