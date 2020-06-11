/*
 * @(#)QuaquaButtonMarginBorder.java
 * Quaqua Look and Feel. Copyright © 2020 Werner Randelshofer, Switzerland. MIT License.
 */

package ch.randelshofer.quaqua;

import javax.swing.AbstractButton;
import javax.swing.border.AbstractBorder;
import java.awt.Component;
import java.awt.Insets;

/**
 * QuaquaButtonMarginBorder is used to honour the margins between button text
 * and button border.
 *
 * @author Werner Randelshofer
 * @version $Id$
 */
public class QuaquaButtonMarginBorder extends AbstractBorder {

    public Insets getBorderInsets(Component c) {
        return getBorderInsets(c, new Insets(0, 0, 0, 0));
    }

    /**
     * Reinitializes the insets parameter with this Border's current Insets.
     *
     * @param c      the component for which this border insets value applies
     * @param insets the object to be reinitialized
     * @return the <code>insets</code> object
     */
    public Insets getBorderInsets(Component c, Insets insets) {
        if (c instanceof AbstractButton) {
            AbstractButton b = (AbstractButton) c;
            Insets margin = b.getMargin();
            /*
            if (margin == null) {
                margin = getDefaultMargin((JComponent) c);
            }*/
            if (margin != null) {
                insets.top += margin.top;
                insets.left += margin.left;
                insets.bottom += margin.bottom;
                insets.right += margin.right;
            }
        }
        return insets;
    }
}
