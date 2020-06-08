/*
 * @(#)AlphaColorUIResource.java
 * Quaqua Look and Feel. Copyright © 2020 Werner Randelshofer, Switzerland. MIT License.
 */

package ch.randelshofer.quaqua.color;

import java.awt.*;
import javax.swing.plaf.*;
/**
 * A ColorUIResource whith an alpha channel.
 *
 * @author  Werner Randelshofer
 * @version $Id$
 */
public class AlphaColorUIResource extends Color implements UIResource {
    public AlphaColorUIResource(int r, int g, int b, int a) {
        super(r, g, b, a);
    }
    public AlphaColorUIResource(int rgba) {
        super(rgba, true);
    }
}
