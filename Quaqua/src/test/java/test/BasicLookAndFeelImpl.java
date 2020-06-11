/*
 * @(#)BasicLookAndFeelImpl.java
 * Quaqua Look and Feel. Copyright © 2020 Werner Randelshofer, Switzerland. MIT License.
 */

package test;

import javax.swing.plaf.basic.BasicLookAndFeel;

/**
 * BasicLookAndFeelImpl.
 * <p>
 * Note: This class must be Java 1.4 compatible.
 *
 * @author Werner Randelshofer
 * @version 1.0 2008-11-17 Created.
 */
public class BasicLookAndFeelImpl extends BasicLookAndFeel {

    //@Override
    public String getName() {
        return "Basic";
    }

    //@Override
    public String getID() {
        return "Basic";
    }

    //@Override
    public String getDescription() {
        return "A basic look and feel";
    }

    //@Override
    public boolean isNativeLookAndFeel() {
        return false;
    }

    //@Override
    public boolean isSupportedLookAndFeel() {
        return true;
    }

}
