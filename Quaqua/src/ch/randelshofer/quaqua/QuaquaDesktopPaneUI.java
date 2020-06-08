/*
 * @(#)QuaquaDesktopPaneUI.java
 * Quaqua Look and Feel. Copyright © 2020 Werner Randelshofer, Switzerland. MIT License.
 */

package ch.randelshofer.quaqua;

import javax.swing.*;
import javax.swing.plaf.*;
import javax.swing.plaf.basic.*;
/**
 * QuaquaDesktopPaneUI.
 *
 * @author  Werner Randelshofer
 * @version $Id$
 */
public class QuaquaDesktopPaneUI extends BasicDesktopPaneUI {

    /** Creates a new instance. */
    public QuaquaDesktopPaneUI() {
    }

    public static ComponentUI createUI(JComponent c) {
        return new QuaquaDesktopPaneUI();
    }
}
