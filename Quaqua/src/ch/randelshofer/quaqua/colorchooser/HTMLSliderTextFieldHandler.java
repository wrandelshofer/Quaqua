/*
 * @(#)HTMLSliderTextFieldHandler.java
 * Quaqua Look and Feel. Copyright © 2020 Werner Randelshofer, Switzerland. MIT License.
 */

package ch.randelshofer.quaqua.colorchooser;

import javax.swing.*;
import javax.swing.event.*;
/**
 * This handler adjusts the value of a component in the HTML color slider model,
 * when the user enters text into the text field.
 *
 * @author  Werner Randelshofer
 * @version 1.0 November 22, 2005 Created.
 */
public class HTMLSliderTextFieldHandler implements DocumentListener, ChangeListener {
    private JTextField textField;
    private HTMLColorSliderModel ccModel;
    private int component;

    public HTMLSliderTextFieldHandler(JTextField textField, HTMLColorSliderModel ccModel, int component) {
        this.textField = textField;
        this.ccModel = ccModel;
        this.component = component;

        textField.getDocument().addDocumentListener(this);
        ccModel.getBoundedRangeModel(component).addChangeListener(this);
    }

    public void changedUpdate(DocumentEvent evt) {
        docChanged();
    }
    public void removeUpdate(DocumentEvent evt) {
        docChanged();
    }
    public void insertUpdate(DocumentEvent evt) {
        docChanged();
    }
    private void docChanged() {
        if (textField.hasFocus()) {
            BoundedRangeModel brm = ccModel.getBoundedRangeModel(component);
            try {
                int value = Integer.decode("#"+textField.getText()).intValue();
                if (brm.getMinimum() <= value && value <= brm.getMaximum()) {
                    brm.setValue(value);
                }
            } catch (NumberFormatException e) {
            }
        }
    }
    public void stateChanged(ChangeEvent e) {
        if (! textField.hasFocus()) {
            int value = ccModel.getBoundedRangeModel(2).getValue();
            if (ccModel.isWebSaveOnly()) {
                value = Math.round(value / 51f) * 51;
            }
            String hex = Integer.toHexString(value).toUpperCase();
            textField.setText((hex.length() == 1) ? "0"+hex : hex);
        }
    }
}

