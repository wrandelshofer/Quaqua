/*
 * @(#)QuaquaComboBoxVisualMargin.java
 * Quaqua Look and Feel. Copyright © 2020 Werner Randelshofer, Switzerland. MIT License.
 */

package ch.randelshofer.quaqua;

import ch.randelshofer.quaqua.border.VisualMarginBorder;

import javax.swing.JComboBox;
import javax.swing.UIManager;
import java.awt.Component;
import java.awt.Insets;

/**
 * QuaquaComboBoxVisualMargin.
 *
 * @author Werner Randelshofer
 * @version $Id$
 */
public class QuaquaComboBoxVisualMargin extends VisualMarginBorder {

    /**
     * Creates a new instance.
     */
    public QuaquaComboBoxVisualMargin() {
        super();
    }

    /**
     * Creates a new VisualMarginBorder.
     *
     * @param top    Defines the margin from the clip bounds of the
     *               component to its visual bounds.
     * @param left   Defines the margin from the clip bounds of the
     *               component to its visual bounds.
     * @param bottom Defines the margin from the clip bounds of the
     *               component to its visual bounds.
     * @param right  Defines the margin from the clip bounds of the
     *               component to its visual bounds.
     */
    public QuaquaComboBoxVisualMargin(int top, int left, int bottom, int right) {
        super(top, left, bottom, right);
    }

    public QuaquaComboBoxVisualMargin(int top, int left, int bottom, int right, boolean ftop, boolean fleft, boolean fbottom, boolean fright) {
        super(top, left, bottom, right, ftop, fleft, fbottom, fright);
    }

    public QuaquaComboBoxVisualMargin(boolean ftop, boolean fleft, boolean fbottom, boolean fright) {
        super(ftop, fleft, fbottom, fright);
    }

    /**
     * Creates a new VisualMarginBorder.
     *
     * @param layoutMargin Defines the margin from the clip bounds of the
     *                     component to its visual bounds. The margin has usually negative values!
     */
    public QuaquaComboBoxVisualMargin(Insets layoutMargin) {
        super(layoutMargin);
    }

    /**
     * Reinitializes the insets parameter with this Border's current Insets.
     *
     * @param c      the component for which this border insets value applies
     * @param insets the object to be reinitialized
     * @return the <code>insets</code> object
     */
    protected Insets getVisualMargin(Component c, Insets insets) {
        Insets i = super.getVisualMargin(c, insets);
        if (UIManager.getBoolean("ComboBox.harmonizePreferredHeight")) {
            if (!((JComboBox) c).isEditable()) {
                i.top += 1;
                i.bottom += 1;
            }
        }
        return i;
    }
}
