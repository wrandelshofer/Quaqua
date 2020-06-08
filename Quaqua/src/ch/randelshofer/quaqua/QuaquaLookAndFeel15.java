/*
 * @(#)QuaquaLookAndFeel15.java
 * Quaqua Look and Feel. Copyright © 2020 Werner Randelshofer, Switzerland. MIT License.
 */

package ch.randelshofer.quaqua;

/**
 * A J2SE5 backwards compatible version of the {@link QuaquaLookAndFeel} class.
 *
 * @author Werner Randelshofer, Switzerland
 * @version $Id$
 */
public class QuaquaLookAndFeel15 extends LookAndFeelProxy15 {

    /** Creates a new instance of QuaquaLookAndFeel */
    public QuaquaLookAndFeel15() {
        super(QuaquaManager.getLookAndFeel());
    }

}
