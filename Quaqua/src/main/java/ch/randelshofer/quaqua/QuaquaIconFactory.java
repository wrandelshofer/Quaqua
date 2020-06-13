/*
 * @(#)QuaquaIconFactory.java
 * Quaqua Look and Feel. Copyright © 2020 Werner Randelshofer, Switzerland. MIT License.
 */
package ch.randelshofer.quaqua;

import ch.randelshofer.quaqua.icon.ButtonFocusIcon;
import ch.randelshofer.quaqua.icon.ButtonStateIcon;
import ch.randelshofer.quaqua.icon.FocusedIcon;
import ch.randelshofer.quaqua.icon.FrameButtonStateIcon;
import ch.randelshofer.quaqua.icon.ListStateIcon;
import ch.randelshofer.quaqua.icon.OverlayIcon;
import ch.randelshofer.quaqua.icon.QuaquaNativeButtonStateIcon;
import ch.randelshofer.quaqua.icon.ShiftedIcon;
import ch.randelshofer.quaqua.icon.SliderThumbIcon;
import ch.randelshofer.quaqua.osx.OSXApplication;
import ch.randelshofer.quaqua.osx.OSXAquaPainter;
import ch.randelshofer.quaqua.osx.OSXImageIO;
import ch.randelshofer.quaqua.util.Images;
import ch.randelshofer.quaqua.util.ScaledImageIcon;
import ch.randelshofer.quaqua.util.Worker;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.plaf.IconUIResource;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

import static ch.randelshofer.quaqua.util.RetinaDisplays.getDeviceScaleFactor;

/**
 * QuaquaIconFactory.
 *
 * @author Werner Randelshofer, Christopher Atlan
 * @version $Id$
 */
public class QuaquaIconFactory {

    private static BufferedImage applicationImage64;
    private static BufferedImage applicationImage32;

    /**
     * Lazy option pane icon.
     * The creation of an option pane icon is a potentially slow operation.
     * This icon class will load the icon image in a worker thread and paint it,
     * when it is ready.
     */
    private static class LazyOptionPaneIcon implements Icon {

        private ImageIcon realIcon;
        private Worker<ImageIcon> worker;
        private HashSet repaintMe = new HashSet();

        public LazyOptionPaneIcon(final int messageType) {
            worker = new Worker<ImageIcon>() {

                public ImageIcon construct() {
                    switch (messageType) {
                    case JOptionPane.WARNING_MESSAGE:
                        return createWarningIcon();
                    case JOptionPane.ERROR_MESSAGE:
                        return createErrorIcon();
                    default:
                        return createApplicationIcon();
                    }
                }

                @Override
                public void done(ImageIcon result) {
                    realIcon = result;

                    // Repaint all components that tried to display the icon
                    // while it was being constructed.
                    for (Iterator i = repaintMe.iterator(); i.hasNext(); ) {
                        Component c = (Component) i.next();
                        c.repaint();
                    }

                    // Get rid of the worker and of the repaint list
                    repaintMe = null;
                    worker = null;
                }
            };
            worker.start();
        }

        public int getIconHeight() {
            return 64;
        }

        public int getIconWidth() {
            return 64;
        }

        public void paintIcon(final Component c, Graphics g, int x, int y) {
            if (realIcon == null && worker != null) {
                // If the value is still being constructed, we repaint the
                // component when the icon becomes available.
                repaintMe.add(c);
            }
            if (realIcon != null) {
                realIcon.paintIcon(c, g, x, y);
            }
        }
    }

    /**
     * Prevent instance creation.
     */
    private QuaquaIconFactory() {
    }

    public static URL getResource(String location) {
        URL url = QuaquaIconFactory.class.getResource(location);
        if (url == null) {
            throw new InternalError("image resource missing: " + location);
        }
        return url;
    }

    public static Image createImage(String location) {
        return Images.createImage(QuaquaIconFactory.class, location);
    }

    public static Image createImage(Class baseClass, String location) {
        return Images.createImage(baseClass, location);
    }

    public static BufferedImage createBufferedImage(String location) {
        return Images.toBufferedImage(createImage(location));
    }

    public static BufferedImage createBufferedImage(String location, Rectangle subimage) {
        BufferedImage img = Images.toBufferedImage(createImage(location));
        return img.getSubimage(subimage.x, subimage.y, subimage.width, subimage.height);
    }

    public static Icon[] createIcons(String location, int count, boolean horizontal) {
        Icon[] icons = new Icon[count];

        BufferedImage[] images = Images.split(
                (Image) createImage(location),
                count, horizontal);

        for (int i = 0; i < count; i++) {
            icons[i] = new IconUIResource(new ImageIcon(images[i]));
        }
        return icons;
    }

    public static Icon[] createIcons(String location, Rectangle subimage, int count, boolean horizontal) {
        Icon[] icons = new Icon[count];
        BufferedImage img = createBufferedImage(location, subimage);

        BufferedImage[] images = Images.split(
                img,
                count, horizontal);

        for (int i = 0; i < count; i++) {
            icons[i] = new IconUIResource(new ImageIcon(images[i]));
        }
        return icons;
    }

    public static Icon createIcon(String location, int count, boolean horizontal, int index) {
        return createIcons(location, count, horizontal)[index];
    }

    public static Icon createButtonStateIcon(String location, int states) {
        return new ButtonStateIcon(
                (Image) createImage(location),
                states, true);
    }

    public static Icon createButtonStateIcon(String location, int states, Point shift) {
        return new ShiftedIcon(
                new ButtonStateIcon(
                        (Image) createImage(location),
                        states, true),
                shift);
    }

    public static Icon createButtonStateIcon(String location, int states, Rectangle shift) {
        return new ShiftedIcon(
                new ButtonStateIcon(
                        (Image) createImage(location),
                        states, true),
                shift);
    }

    public static Icon createFrameButtonStateIcon(String location, int states) {
        return new FrameButtonStateIcon(
                (Image) createImage(location),
                states, true);
    }

    /**
     * Creates a button state icon overlaid with a button focus icon.
     */
    public static Icon createOverlaidButtonStateIcon(
            String location1, int states1,
            String location2, int states2,
            Rectangle layoutRect) {
        return new IconUIResource(
                new VisuallyLayoutableIcon(
                        new OverlayIcon(
                                createButtonStateIcon(location1, states1),
                                createButtonFocusIcon(location2, states2)), layoutRect));
    }

    public static Icon createButtonFocusIcon(String location, int states) {
        return new ButtonFocusIcon(
                (Image) createImage(location),
                states, true);
    }

    public static Icon createSliderThumbIcon(String location) {
        return new SliderThumbIcon(createImage(location), 6, true);
    }

    public static Icon createIcon(Class baseClass, String location) {
        return new ImageIcon(createImage(baseClass, location));
    }

    public static Icon createIcon(Class baseClass, String location, Point shift) {
        return new ShiftedIcon(
                new ImageIcon(createImage(baseClass, location)),
                shift);
    }

    public static Icon createIcon(Class baseClass, String location, Rectangle shiftAndSize) {
        return new ShiftedIcon(
                new ImageIcon(createImage(baseClass, location)),
                shiftAndSize);
    }

    public static Icon createNativeIcon(String path, int size) {
        try {
            Image img;
            img = OSXImageIO.read(new File(path), size, size);
            if (img == null) {
                return null;
            }
            return new ImageIcon(img);
        } catch (IOException ex) {
            return null;
        }
    }

    public static Icon createNativeIcon(String path, int width, int height) {
        try {
            Image img;
            img = OSXImageIO.read(new File(path), width, height);
            if (img == null) {
                return null;
            }
            return new ImageIcon(img);
        } catch (IOException ex) {
            return null;
        }
    }


    public static Icon createNativeYosemiteSidebarIcon(String path, int width, int height, Color color,
                                                       Color selectedColor, Color selectedAndFocusedColor) {
        try {
            int retinaScaleFactor = getDeviceScaleFactor();
            int scaledWidth = width *retinaScaleFactor;
            int scaledHeight =  height * retinaScaleFactor;

            BufferedImage img;
            img = Images.toBufferedImage((Image) OSXImageIO.read(new File(path), scaledWidth, scaledHeight));
            if (img == null) {
                return null;
            }

            RescaleOp rop = new RescaleOp(new float[]{1f, 1f, 1f, 1f}, new float[]{0f, 0f, 0f, 0f}, null);

            BufferedImage iconImg = new BufferedImage(scaledWidth, scaledHeight, BufferedImage.TYPE_INT_ARGB_PRE);
            Graphics2D g = iconImg.createGraphics();
            g.setComposite(AlphaComposite.Src);
            g.drawImage(img, rop, 0, 0);
            g.setComposite(AlphaComposite.SrcIn);
            g.setColor(color);
            g.fillRect(0, 0, scaledWidth, scaledHeight);
            g.dispose();

            BufferedImage selectedImg = new BufferedImage(scaledWidth, scaledHeight, BufferedImage.TYPE_INT_ARGB_PRE);
            g = selectedImg.createGraphics();
            g.setComposite(AlphaComposite.Src);
            g.drawImage(img, rop, 0, 0);
            g.setComposite(AlphaComposite.SrcIn);
            g.setColor(selectedColor);
            g.fillRect(0, 0, scaledWidth, scaledHeight);
            g.dispose();

            BufferedImage selectedAndFocusedImg = new BufferedImage(scaledWidth, scaledHeight, BufferedImage.TYPE_INT_ARGB_PRE);
            g = selectedAndFocusedImg.createGraphics();
            g.setComposite(AlphaComposite.Src);
            g.drawImage(img, rop, 0, 0);
            g.setComposite(AlphaComposite.SrcIn);
            g.setColor(selectedAndFocusedColor);
            g.fillRect(0, 0, scaledWidth, scaledHeight);
            g.dispose();

            return new ListStateIcon(new ScaledImageIcon(iconImg, width, height),
                    new ScaledImageIcon(selectedImg, width, height),
                    new ScaledImageIcon(selectedAndFocusedImg, width, height));
        } catch (IOException ex) {
            return null;
        }
    }

    public static Icon createNativeSidebarIcon(String path, int width, int height, Color color, Color selectedColor) {
        int scaleFactor = getDeviceScaleFactor();
        int scaledWidth =  width * scaleFactor;
        int scaledHeight =  height * scaleFactor;

        try {
            BufferedImage img;
            img = Images.toBufferedImage((Image) OSXImageIO.read(new File(path), scaledWidth, scaledHeight));
            if (img == null) {
                return null;
            }

            RescaleOp rop = new RescaleOp(new float[]{1f, 1f, 1f, 1f}, new float[]{0f, 0f, 0f, 0f}, null);

            BufferedImage iconImg = new BufferedImage(scaledWidth, scaledHeight, BufferedImage.TYPE_INT_ARGB_PRE);
            Graphics2D g = iconImg.createGraphics();
            g.setComposite(AlphaComposite.Src);
            g.drawImage(img, rop, 0, 0);
            g.setComposite(AlphaComposite.SrcIn);
            g.setColor(color);
            g.fillRect(0, 0, scaledWidth, scaledHeight);
            g.dispose();

            BufferedImage whiteImg = new BufferedImage(scaledWidth, scaledHeight, BufferedImage.TYPE_INT_ARGB_PRE);
            g = whiteImg.createGraphics();
            g.setComposite(AlphaComposite.Src);
            g.drawImage(img, rop, 0, 0);
            g.setComposite(AlphaComposite.SrcIn);
            g.setColor(selectedColor);
            g.fillRect(0, 0, scaledWidth, scaledHeight);
            g.dispose();

            BufferedImage selectedImg = new BufferedImage(scaledWidth, scaledHeight + 1, BufferedImage.TYPE_INT_ARGB_PRE);
            g = selectedImg.createGraphics();
            g.drawImage(iconImg, 0, 1, null);
            g.drawImage(whiteImg, 0, 0, null);
            g.dispose();
            whiteImg.flush();

            return new ListStateIcon(new ScaledImageIcon(iconImg, width, height), new ScaledImageIcon(selectedImg, width, height));
        } catch (IOException ex) {
            return null;
        }
    }

    public static Icon createNativeButtonStateIcon(OSXAquaPainter.Widget widget, int xoffset, int yoffset, int width, int height,
                                                   boolean drawFocusRing) {
        return createNativeButtonStateIconX(widget,xoffset,yoffset,width,height,drawFocusRing, Collections.emptyMap());
    }
    public static Icon createNativeButtonStateIconX(OSXAquaPainter.Widget widget, int xoffset, int yoffset, int width, int height,
                                                   boolean drawFocusRing,
                                                   Map<OSXAquaPainter.Key,Double> properties) {
        Icon icon =
                new QuaquaNativeButtonStateIcon(widget, xoffset, yoffset, width, height,properties);
        if (drawFocusRing) {
            icon = new FocusedIcon(icon);
        }
        return icon;
    }

    public static Icon createOptionPaneIcon(int messageType) {
        return new LazyOptionPaneIcon(messageType);
    }

    private static ImageIcon createApplicationIcon() {
        // Workaround for a bug in method getScaledInstance() in Apple's Java VM.
        // Instead of using getScaledInstance(), we create a temporary image
        // and do the scaling using drawImage().

        /*
        BufferedImage image = getApplicationIconImage();
        return new ImageIcon(image.getScaledInstance(64, 64, Image.SCALE_SMOOTH));
         */
        /*
        BufferedImage result = new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = result.createGraphics();
        g.drawImage(getApplicationIconImage(), 0, 0, 64, 64, null);
        g.dispose();
        return new ImageIcon(result);
         */
        return new ImageIcon(getApplicationIconImage());
    }

    private static ImageIcon createWarningIcon() {
        return composeOptionPaneIcon(UIManager.getString("OptionPane.warningIconResource"));
    }

    private static ImageIcon createErrorIcon() {
        return composeOptionPaneIcon(UIManager.getString("OptionPane.errorIconResource"));
    }

    private static ImageIcon composeOptionPaneIcon(String resource) {
        BufferedImage result = new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = result.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);


        BufferedImage warningImage = Images.toBufferedImage(
                Images.createImage(
                        QuaquaIconFactory.class.getResource(resource)));
        g.drawImage(warningImage, 0, 0, 58, 58, null);

        BufferedImage appImage = OSXApplication.getIconImage(32);
        g.drawImage(appImage, 32, 32, 32, 32, null);


        g.dispose();
        return new ImageIcon(result);
    }

    /**
     * Gets the application image. This is a buffered image of size 64x64.
     * If the Quaqua JNI code and the ImageIO API are present, this will get
     * the image from the OS X application bundle.
     * In all other cases this will return a default application image.
     */
    public static BufferedImage getApplicationIconImage() {
        if (applicationImage64 == null) {
            applicationImage64 = OSXApplication.getIconImage(64);
        }
        return applicationImage64;
    }
}
