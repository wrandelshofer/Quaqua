/*
 * @(#)QuaquaDesktopPaneUI.java
 * Quaqua Look and Feel. Copyright © 2020 Werner Randelshofer, Switzerland. MIT License.
 */

package ch.randelshofer.quaqua;

import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicDesktopPaneUI;

/**
 * QuaquaDesktopPaneUI.
 *
 * @author Werner Randelshofer
 * @version $Id$
 */
public class QuaquaDesktopPaneUI extends BasicDesktopPaneUI {

    /**
     * Creates a new instance.
     */
    public QuaquaDesktopPaneUI() {
    }

    public static ComponentUI createUI(JComponent c) {
        return new QuaquaDesktopPaneUI();
    }
}
