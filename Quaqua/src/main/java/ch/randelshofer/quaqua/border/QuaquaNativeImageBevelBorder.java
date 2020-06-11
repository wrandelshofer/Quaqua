/*
 * @(#)QuaquaNativeImageBevelBorder.java
 * Quaqua Look and Feel. Copyright © 2020 Werner Randelshofer, Switzerland. MIT License.
 */
package ch.randelshofer.quaqua.border;

import ch.randelshofer.quaqua.osx.OSXAquaPainter;
import ch.randelshofer.quaqua.osx.OSXAquaPainter.Widget;
import ch.randelshofer.quaqua.util.InsetsUtil;

import javax.swing.plaf.UIResource;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.image.BufferedImage;

/**
 * {@code QuaquaNativeImageBevelBorder}.
 * <p>
 * Renders a native border once into an ImageBevelBorder and then uses
 * the ImageBevelBorder for further rendering.
 *
 * @author Werner Randelshofer
 * @version $Id$
 */
public class QuaquaNativeImageBevelBorder extends VisualMarginBorder implements UIResource {

    private OSXAquaPainter.Widget widget;
    private ImageBevelBorder imageBevelBorder;
    private Insets painterInsets;
    private Insets imageBevelInsets;
    private Insets borderInsets;
    private Insets visualMargin;
    private boolean fill;

    public QuaquaNativeImageBevelBorder(Widget widget, Insets painterInsets, Insets imageBevelInsets, Insets borderInsets, boolean fill) {
        this.widget = widget;
        this.painterInsets = painterInsets;
        this.imageBevelInsets = imageBevelInsets;
        this.borderInsets = borderInsets;
        this.visualMargin = new Insets(3, 3, 3, 3);
        this.fill = fill;
    }

    @Override
    public void paintBorder(Component cc, Graphics g, int x, int y, int width, int height) {
        if (imageBevelBorder == null) {
            imageBevelBorder = createImageBeveBorder();
        }
        imageBevelBorder.paintBorder(cc, g, x, y, width, height);
    }

    protected ImageBevelBorder createImageBeveBorder() {

        // PREPARE THE PAINTER
        // -------------------
        OSXAquaPainter painter = new OSXAquaPainter();
        painter.setWidget(widget);

        // Create an ImageBevelBorder
        // -------------------------------------------
        BufferedImage image;
        ImageBevelBorder ibb;
        int fixedWidth, fixedHeight;

        fixedWidth = 32;
        fixedHeight = 32;

        image = new BufferedImage(fixedWidth, fixedHeight, BufferedImage.TYPE_INT_ARGB_PRE);

        /*
        Graphics2D pg = painterImg.createGraphics();
        pg.setColor(new Color(0x0, true));
        pg.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC));
        pg.fillRect(0, 0, painterImg.getWidth(), painterImg.getHeight());
        pg.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
        pg.dispose();*/
        painter.paint(image,//
                painterInsets.left, painterInsets.top,//
                image.getWidth() - painterInsets.left - painterInsets.right,
                image.getHeight() - painterInsets.top - painterInsets.bottom);

        ibb = new ImageBevelBorder(image, imageBevelInsets, borderInsets, fill);


        return ibb;
    }

    @Override
    public Insets getBorderInsets(Component c, Insets insets) {
        Insets ins = super.getBorderInsets(c, insets);
        InsetsUtil.addTo(borderInsets, ins);
        return ins;
    }

    @Override
    public boolean isBorderOpaque() {
        return false;
    }

    public static class UIResource extends QuaquaNativeImageBevelBorder implements javax.swing.plaf.UIResource {

        public UIResource(Widget widget, Insets painterInsets, Insets imageBevelInsets, Insets borderInsets, boolean fill) {
            super(widget, painterInsets, imageBevelInsets, borderInsets, fill);
        }

    }
}