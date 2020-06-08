/*
 * @(#)PaletteEntry.java
 * Quaqua Look and Feel. Copyright © 2020 Werner Randelshofer, Switzerland. MIT License.
 */

package ch.randelshofer.quaqua.colorchooser;

import java.awt.*;
/**
 * PaletteEntry.
 *
 * @author  Werner Randelshofer
 * @version 1.0 19 septembre 2005 Created.
 */
public class PaletteEntry {
    private String name;
    private Color color;

    /**
     * Creates a new instance.
     */
    public PaletteEntry(String name, Color color) {
        this.name = name;
        this.color = color;
    }
    public String getName() {
        return name;
    }
    public String toString() {
        return name;
    }
    public Color getColor() {
        return color;
    }
}
