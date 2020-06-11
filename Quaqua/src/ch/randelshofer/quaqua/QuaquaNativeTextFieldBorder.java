/*
 * @(#)QuaquaNativeTextFieldBorder.java
 * Quaqua Look and Feel. Copyright © 2020 Werner Randelshofer, Switzerland. MIT License.
 */
package ch.randelshofer.quaqua;

import ch.randelshofer.quaqua.border.AbstractFocusedPainter;
import ch.randelshofer.quaqua.border.VisualMarginBorder;
import ch.randelshofer.quaqua.border.ImageBevelBorder;

import javax.swing.JComponent;

import ch.randelshofer.quaqua.border.BackgroundBorder;
import ch.randelshofer.quaqua.border.FocusedBorder;
import ch.randelshofer.quaqua.osx.OSXAquaPainter;
import ch.randelshofer.quaqua.util.CachedPainter;
import ch.randelshofer.quaqua.util.InsetsUtil;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Image;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.plaf.InsetsUIResource;
import javax.swing.plaf.UIResource;
import javax.swing.text.JTextComponent;

import static ch.randelshofer.quaqua.osx.OSXAquaPainter.*;

/**
 * Native Aqua border for text components.
 *
 * @author Werner Randelshofer
 * @version $Id$
 */
public class QuaquaNativeTextFieldBorder extends VisualMarginBorder implements Border, BackgroundBorder {

    private Insets imageInsets;
    private Insets imageBevelInsets;
    // private Insets borderInsets;
    private Border bgBorder;
    private final static int ARG_TEXT_FIELD = 2;
    private final static int ARG_SMALL_SIZE = 32;

    private class BGBorder implements Border {

        private Border textFieldBorder;

        private Border getActualBorder(Component c) {
                if (textFieldBorder == null) {
                    textFieldBorder = new BGTextFieldBorder();
                }
                return textFieldBorder;
        }

        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            getActualBorder(c).paintBorder(c, g, x, y, width, height);
        }

        public Insets getBorderInsets(Component c) {
            return getActualBorder(c).getBorderInsets(c);
        }

        public boolean isBorderOpaque() {
            return false;
        }
    }

    private class BGTextFieldBorder implements Border {

        private BufferedImage regularPainterImage;
        private BufferedImage smallPainterImage;
        private BufferedImage regularFocusImage;
        private BufferedImage smallFocusImage;
        private ImageBevelBorder regularIbb;
        private ImageBevelBorder smallIbb;
        private OSXAquaPainter painter;

        public BGTextFieldBorder() {
            painter = new OSXAquaPainter();
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            int args = 0;
            JTextComponent b = (JTextComponent) c;
            boolean isEditable = b.isEditable();

            // PREPARE THE PAINTER
            // -------------------
            {
                State state;
                if (QuaquaUtilities.isOnActiveWindow(c)) {
                    state = State.active;
                    args |= 1;
                } else {
                    state = State.inactive;
                }

                Widget widget;
                if (isSearchField(b)) {
                    widget = Widget.frameTextFieldRound;
                } else {
                    args |= ARG_TEXT_FIELD;
                    widget = Widget.frameTextField;
                }

                painter.setWidget(widget);

                if (!b.isEnabled() || !isEditable) {
                    state = State.disabled;
                    args |= 4;
                }
                painter.setState(state);

                boolean isFocusedAndEditable = QuaquaUtilities.isFocused(c) && isEditable;
                args |= (isFocusedAndEditable) ? 16 : 0;
                painter.setValueByKey(OSXAquaPainter.Key.focused, isFocusedAndEditable ? 1 : 0);

                Size size;
                switch (QuaquaUtilities.getSizeVariant(c)) {
                case REGULAR:
                default:
                    size = Size.regular;
                    break;
                case SMALL:
                case MINI:// paint mini with small artwork
                    size = Size.small;
                    args |= ARG_SMALL_SIZE;
                    break;
                }
                painter.setSize(size);
            }
            // Create an ImageBevelBorder
            // FIXME - We have a caching opportunity here!
            // -------------------------------------------
            {
                Insets vm = getVisualMargin(c);

                // The painter can not render text fields in arbitrary sizes.
                // We render it first into an ImageBevelBorder, and then onto the
                // image.
                BufferedImage painterImg;
                BufferedImage ibbImg;
                BufferedImage focusImg;
                ImageBevelBorder ibb;
                int fixedWidth, fixedHeight, fixedYOffset;
                int slack = 6;
                if ((args & ARG_SMALL_SIZE) == ARG_SMALL_SIZE) {
                    fixedWidth = 40 + slack * 2;
                    fixedHeight = 19 + slack * 2;
                    fixedYOffset = 3;

                    if (smallPainterImage == null) {
                        smallPainterImage = new BufferedImage(fixedWidth, fixedHeight, BufferedImage.TYPE_INT_ARGB_PRE);
                    }
                    painterImg = smallPainterImage;
                    if (smallFocusImage == null) {
                        smallFocusImage = new BufferedImage(fixedWidth, fixedHeight, BufferedImage.TYPE_INT_ARGB_PRE);
                    }
                    focusImg = smallFocusImage;
                    if (smallIbb == null) {
                        ibbImg = new BufferedImage(fixedWidth, fixedHeight, BufferedImage.TYPE_INT_ARGB_PRE);
                        ibb = smallIbb = new ImageBevelBorder(ibbImg,
                                InsetsUtil.add(slack,imageBevelInsets),
                                new Insets(4 + slack, 4 + slack, 4 + slack, 4 + slack));
                    } else {
                        ibb = smallIbb;
                    }
                } else {
                    fixedWidth = 40 + slack * 2;
                    fixedHeight = 22 + slack * 2;
                    fixedYOffset = 3;

                    if (regularPainterImage == null) {
                        regularPainterImage = new BufferedImage(fixedWidth, fixedHeight, BufferedImage.TYPE_INT_ARGB_PRE);
                    }
                    painterImg = regularPainterImage;
                    if (regularFocusImage == null) {
                        regularFocusImage = new BufferedImage(fixedWidth, fixedHeight, BufferedImage.TYPE_INT_ARGB_PRE);
                    }
                    focusImg = regularFocusImage;
                    if (regularIbb == null) {
                        ibbImg = new BufferedImage(fixedWidth, fixedHeight, BufferedImage.TYPE_INT_ARGB_PRE);
                        ibb = regularIbb = new ImageBevelBorder(ibbImg,
                                InsetsUtil.add(slack,imageBevelInsets),
                                new Insets(8 + slack, 8 + slack, 8 + slack, 8 + slack));
                    } else {
                        ibb = regularIbb;
                    }
                }
                ibbImg = (BufferedImage) ibb.getImage();

                Graphics2D pg = painterImg.createGraphics();
                pg.setColor(new Color(0x0, true));
                pg.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC));
                pg.fillRect(0, 0, painterImg.getWidth(), painterImg.getHeight());
                pg.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
                pg.dispose();
                painter.paint(painterImg,//
                        slack, fixedYOffset + slack,//
                        painterImg.getWidth() - 2 * slack, painterImg.getHeight() - 2 * slack);


                Graphics2D ibbg = ibbImg.createGraphics();
                ibbg.setColor(new Color(0x0, true));
                ibbg.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC));
                ibbg.fillRect(0, 0, painterImg.getWidth(), painterImg.getHeight());
                ibbg.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
                ibbg.drawImage(painterImg, 0, 0, null);

                if (QuaquaUtilities.isFocused(c) && isEditable) {
                    AbstractFocusedPainter.paintFocusRing(painterImg, focusImg, ibbg, 0, 0);
                }

                ibbg.dispose();

                ibb.paintBorder(c, g,//
                        imageInsets.left - slack + vm.left, //
                        imageInsets.top - slack + vm.top,//
                        width - imageInsets.left - imageInsets.right + 2 * slack - vm.left - vm.right, //
                        height - imageInsets.top - imageInsets.bottom + 2 * slack - vm.top - vm.bottom);
                ibbg.dispose();

            }
        }

        public Insets getBorderInsets(Component c) {
            return new Insets(0, 0, 0, 0);
        }

        public boolean isBorderOpaque() {
            return false;
        }
    }

    public QuaquaNativeTextFieldBorder() {
        this(new Insets(0, 0, 0, 0), new Insets(0, 0, 0, 0), true);
    }

    public QuaquaNativeTextFieldBorder(Insets imageInsets, Insets imageBevelInsets, boolean fill) {
        super(new Insets(0, 0, 0, 0));
        this.imageInsets = imageInsets;
        this.imageBevelInsets=imageBevelInsets;
    }

    private boolean isSearchField(JComponent b) {
        Object variant =
                b.getClientProperty("Quaqua.TextField.style");

        if (variant == null) {
            variant = b.getClientProperty("JTextField.variant");
        }
        return variant != null && variant.equals("search");
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        // empty
    }

    public Border getBackgroundBorder() {
        if (bgBorder == null) {
            this.bgBorder = new BGBorder();
        }
        return bgBorder;
    }

    @Override
    public Insets getBorderInsets(Component c, Insets insets) {

        Insets vm = getVisualMargin(c);
        Insets bm;
        if (isSearchField((JComponent) c)) {
            bm = UIManager.getInsets("TextField.searchBorderInsets");
        } else {
            switch (QuaquaUtilities.getSizeVariant(c)) {
            default:
                bm = UIManager.getInsets("TextField.borderInsets");
                break;
            case SMALL:
                bm = UIManager.getInsets("TextField.smallBorderInsets");
                break;
            case MINI:
                bm = UIManager.getInsets("TextField.miniBorderInsets");
                break;
            }
        }
        if (bm != null) {
            InsetsUtil.setTo(bm, insets);
        } else {
            InsetsUtil.clear(insets);
        }
        if (vm != null) {
            InsetsUtil.addTo(vm, insets);
        }


        if (c instanceof JTextComponent) {
            Insets margin = ((JTextComponent) c).getMargin();
            if (margin != null) {
                InsetsUtil.addTo(margin, insets);
            }
        }

        return insets;
    }

    @Override
    public boolean isBorderOpaque() {
        return false;
    }

    public static class UIResource extends QuaquaNativeTextFieldBorder implements javax.swing.plaf.UIResource {

        public UIResource() {
            super();
        }

        /**
         * Creates a new instance.
         * All borders must have the same dimensions.
         */
        public UIResource(Insets imageInsets, Insets borderInsets, boolean fill) {
            super(imageInsets, borderInsets, fill);
        }
    }
}
