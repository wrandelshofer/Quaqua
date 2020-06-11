/*
 * @(#)SheetListener.java
 * Quaqua Look and Feel. Copyright © 2020 Werner Randelshofer, Switzerland. MIT License.
 */

package ch.randelshofer.quaqua;

import java.util.EventListener;

/**
 * SheetListener.
 *
 * @author Werner Randelshofer
 * @version 1.0 26. September 2005 Created.
 */
public interface SheetListener extends EventListener {
    /**
     * This method is invoked, when the user selected an option on the
     * JOptionPane or the JFileChooser pane on the JSheet.
     */
    public void optionSelected(SheetEvent evt);
}
