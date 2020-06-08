/*
 * @(#)ColorSliderHexTextFieldHandler.java
 * Quaqua Look and Feel. Copyright © 2020 Werner Randelshofer, Switzerland. MIT License.
 */

package ch.randelshofer.quaqua.colorchooser;

import javax.swing.*;
import javax.swing.event.ChangeEvent;

/**
 * ColorSliderHexTextFieldHandler.
 *
 * @author Werner Randelshofer
 * @version $Id$
 */
public class ColorSliderHexTextFieldHandler extends ColorSliderTextFieldHandler {

    /** Creates a new instance. */
    public ColorSliderHexTextFieldHandler(JTextField textField, ColorSliderModel ccModel, int component) {
        super(textField, ccModel, component);
    }

    protected void docChanged() {
        if (textField.hasFocus()) {
            BoundedRangeModel brm = ccModel.getBoundedRangeModel(component);
            try {
                int value = Integer.decode("#"+textField.getText()).intValue();
                if (brm.getMinimum() <= value && value <= brm.getMaximum()) {
                    brm.setValue(value);
                }
            } catch (NumberFormatException e) {
                // Don't change value if it isn't numeric.
            }
        }
    }
    public void stateChanged(ChangeEvent e) {
        if (! textField.hasFocus()) {
            String str = Integer.toHexString(
                    ccModel.getBoundedRangeModel(component).getValue()).
                    toUpperCase();
            textField.setText(str.length() == 2 ? str : "0"+str);
        }
    }
}
