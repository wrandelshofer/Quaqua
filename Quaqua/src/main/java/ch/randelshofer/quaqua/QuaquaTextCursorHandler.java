/*
 * @(#)QuaquaTextCursorHandler.java
 * Quaqua Look and Feel. Copyright © 2020 Werner Randelshofer, Switzerland. MIT License.
 */
package ch.randelshofer.quaqua;

import javax.swing.plaf.UIResource;
import javax.swing.text.JTextComponent;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.MemoryImageSource;

/**
 * QuaquaTextCursorHandler hides the cursor when a key is pressed in
 * a JTextComponent and shows it again when the cursor is moved.
 *
 * @author Werner Randelshofer
 * @version $Id$
 */
public class QuaquaTextCursorHandler implements MouseMotionListener, KeyListener {

    /**
     * Holds the shared instance of the handler.
     */
    private static QuaquaTextCursorHandler instance;

    /**
     * CursorUIResource.
     */
    static class QuaquaCursor extends Cursor implements UIResource {

        QuaquaCursor(int type) {
            super(type);
        }

        QuaquaCursor(String name) {
            super(name);
        }
    }

    private static QuaquaCursor textCursor = new QuaquaCursor(Cursor.TEXT_CURSOR);
    /**
     * Holds the hidden cursor.
     */
    private static Cursor invisibleCursor;
    /**
     * Holds the JTextComponent on which we hid the cursor because a key was
     * typed.
     */
    private JTextComponent activeComponent;

    /**
     * Returns the shared instance of the handler.
     */
    public static QuaquaTextCursorHandler getInstance() {
        if (instance == null) {
            instance = new QuaquaTextCursorHandler();

            int[] pixels = new int[16 * 16];
            Image image = Toolkit.getDefaultToolkit().createImage(
                    new MemoryImageSource(16, 16, pixels, 0, 16));
            invisibleCursor =
                    Toolkit.getDefaultToolkit().createCustomCursor(image, new Point(0, 0), "invisibleCursor");
        }
        return instance;
    }

    /* Registers the handler as a listener on the specified JTextComponent. */
    public void installListeners(JTextComponent c) {
        c.addMouseMotionListener(this);
        c.addKeyListener(this);
    }
    /* Unregisters the handler as a listener from the specified JTextComponent. */

    public void uninstallListeners(JTextComponent c) {
        c.removeMouseMotionListener(this);
        c.removeKeyListener(this);
    }

    public void mouseDragged(MouseEvent e) {
    }

    public void mouseMoved(MouseEvent e) {
        if (activeComponent != null) {
            showCursor(activeComponent);
            activeComponent = null;
        }
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        if (e.getSource() != activeComponent) {
            if (e.getSource() instanceof JTextComponent) {
                if (activeComponent != null) {
                    showCursor(activeComponent);
                }
                activeComponent = (JTextComponent) e.getSource();
                hideCursor(activeComponent);
            } else {
                activeComponent = null;
            }
        }
    }

    public void keyReleased(KeyEvent e) {
    }

    /**
     * Hides the cursor.
     */
    private void hideCursor(JTextComponent c) {
        if (c.isEditable()) {
            c.setCursor(invisibleCursor);
        }
    }

    /**
     * Shows the cursor.
     */
    private void showCursor(JTextComponent c) {
        c.setCursor(c.isEditable() ? textCursor : null);
    }
}
