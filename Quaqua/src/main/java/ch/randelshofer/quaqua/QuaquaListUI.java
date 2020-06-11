/*
 * @(#)QuaquaListUI.java
 * Quaqua Look and Feel. Copyright © 2020 Werner Randelshofer, Switzerland. MIT License.
 */
package ch.randelshofer.quaqua;

import ch.randelshofer.quaqua.color.ActivatableUIResource;
import ch.randelshofer.quaqua.color.PaintableColor;

import javax.swing.CellRendererPane;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.event.MouseInputListener;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicListUI;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.reflect.Method;

/**
 * QuaquaListUI for Java 1.4.
 *
 * @author Werner Randelshofer
 * @version $Id$
 */
public class QuaquaListUI extends BasicListUI {

    private boolean isStriped = false;
    private boolean isComboPopup = false;
    /**
     * This variable has the value of JList.VERTICAL, if Java 1.4 or higher is
     * present. In older Java VM's it has value 0.
     */
    private final static int VERTICAL;

    static {
        int value = 0;
        try {
            value = JList.class.getField("VERTICAL").getInt(null);
        } catch (Exception e) {
        }
        VERTICAL = value;
    }

    private final static Method getLayoutOrientation;

    static {
        Method value = null;
        try {
            value = JList.class.getMethod("getLayoutOrientation", (Class[]) null);
        } catch (Exception e) {
        }
        getLayoutOrientation = value;
    }

    /**
     * Creates a new instance.
     */
    public QuaquaListUI() {
    }

    private Color getAlternateColor(int modulo) {
        if (modulo == 0) {
            return UIManager.getColor("List.alternateBackground.0");
        } else {
            return UIManager.getColor("List.alternateBackground.1");
        }
    }

    private void updateStriped() {
        Object value = list.getClientProperty("Quaqua.List.style");
        isStriped = value != null && value.equals("striped") && getLayoutOrientation() == VERTICAL;
    }

    private int getLayoutOrientation() {
        if (getLayoutOrientation != null) {
            try {
                return ((Integer) getLayoutOrientation.invoke(list, (Object[]) null)).intValue();
            } catch (Exception e) {
            }
        }
        return VERTICAL;
    }

    public void paintStripes(Graphics g, JComponent c) {
        if (isStriped && list.getModel() != null //&& list.getLayoutOrientation() == JList.VERTICAL
        ) {
            // Now check if we need to paint some stripes
            Dimension vs = c.getSize();
            Dimension ts = list.getSize();


            Point p = list.getLocation();
            int rh = list.getFixedCellHeight();
            int n = list.getModel().getSize();
            if (rh <= 0) {
                rh = (n == 0) ? 12 : getCellBounds(list, 0, 0).height;
            }
            int row = Math.abs(p.y / rh);
            int th = n * rh - row * rh;

            // Fill the background of the list with stripe color 1
            g.setColor(getAlternateColor(1));
            g.fillRect(0, 0, ts.width, ts.height);

            // Fill rectangles with stripe color 0
            g.setColor(getAlternateColor(0));

            // Paint empty rows at the right to fill the viewport
            if (ts.width < vs.width) {
                int y = p.y + row * rh;
                while (y < th) {
                    if (row % 2 == 0) {
                        g.fillRect(0, y, vs.width, rh);
                    }
                    y += rh;
                    row++;
                }
            }


            // Paint empty rows at the bottom to fill the viewport
            if (th < vs.height) {
                row = n;
                int y = th;
                while (y < vs.height) {
                    if (row % 2 == 0) {
                        g.fillRect(0, y, vs.width, rh);
                    }
                    y += rh;
                    row++;
                }
            }
        }
    }

    /**
     * The layout orientation of the list.
     */
    private int layoutOrientation;

    /**
     * Paint one List cell: compute the relevant state, get the "rubber stamp"
     * cell renderer component, and then use the CellRendererPane to paint it.
     * Subclasses may want to override this method rather than paint().
     *
     * @see #paint
     */
    @Override
    protected void paintCell(
            Graphics g,
            int row,
            Rectangle rowBounds,
            ListCellRenderer cellRenderer,
            ListModel dataModel,
            ListSelectionModel selModel,
            int leadIndex) {
        Object value = dataModel.getElementAt(row);
        boolean isEnabled = list.isEnabled();
        boolean isFocused = isEnabled
                && QuaquaUtilities.isFocused(list);
        boolean cellHasFocus = isFocused && (row == leadIndex);
        boolean isSelected = selModel.isSelectedIndex(row);

        Component rendererComponent =
                cellRenderer.getListCellRendererComponent(list, value, row, isSelected, cellHasFocus);

        int cx = rowBounds.x;
        int cy = rowBounds.y;
        int cw = rowBounds.width;
        int ch = rowBounds.height;

        if (list.isSelectedIndex(row)) {
            ((Graphics2D) g).setPaint(PaintableColor.getPaint(UIManager.getColor(isComboPopup ? "ComboBox.selectionBackground" : "List.selectionBackground"), rendererComponent, cx, cy, cw, ch));
            g.fillRect(cx, cy, cw, ch);
        } else {
            if (isStriped) {
                //rendererComponent.setBackground(getAlternateColor(row % 2));
                g.setColor(getAlternateColor(row % 2));
                g.fillRect(cx, cy, cw, ch);
            }
        }

        if (isComboPopup) {
            cx += 7;
            cw -= 14;
        }
        rendererPane.paintComponent(g, rendererComponent, list, cx, cy, cw, ch, true);
    }

    /**
     * Paint the rows that intersect the Graphics objects clipRect.  This
     * method calls paintCell as necessary.  Subclasses
     * may want to override these methods.
     *
     * @see #paintCell
     */
    @Override
    public void paint(Graphics g, JComponent c) {
        paintStripes(g, c);

        boolean isFocused = isComboPopup || QuaquaUtilities.isFocused(c);
        Object value = c.getClientProperty("Quaqua.List.style");
        isComboPopup = value != null && value.equals("comboPopup");
        Color selectionBackground = UIManager.getColor(isComboPopup ? "ComboBox.selectionBackground" : "List.selectionBackground");
        Color selectionForeground = UIManager.getColor(isComboPopup ? "ComboBox.selectionForeground" : "List.selectionForeground");
        if (selectionBackground instanceof ActivatableUIResource) {
            ((ActivatableUIResource) selectionBackground).setActive(isFocused);
        }
        if (selectionForeground instanceof ActivatableUIResource) {
            ((ActivatableUIResource) selectionForeground).setActive(isFocused);
        }
        // We need to mess with tree selection colors here, in case someone
        // is nesting a TreeCellRenderer into a ListCellRenderer.
        Color treeSelectionBackground = UIManager.getColor("Tree.selectionBackground");
        Color treeSelectionForeground = UIManager.getColor("Tree.selectionForeground");
        if (treeSelectionBackground instanceof ActivatableUIResource) {
            ((ActivatableUIResource) treeSelectionBackground).setActive(isFocused);
        }

        if (treeSelectionForeground instanceof ActivatableUIResource) {
            ((ActivatableUIResource) treeSelectionForeground).setActive(isFocused);
        }

        super.paint(g, c);
        if (selectionBackground instanceof ActivatableUIResource) {
            ((ActivatableUIResource) selectionBackground).setActive(true);
        }
        if (selectionForeground instanceof ActivatableUIResource) {
            ((ActivatableUIResource) selectionForeground).setActive(true);
        }
        if (treeSelectionBackground instanceof ActivatableUIResource) {
            ((ActivatableUIResource) treeSelectionBackground).setActive(true);
        }

        if (treeSelectionForeground instanceof ActivatableUIResource) {
            ((ActivatableUIResource) treeSelectionForeground).setActive(true);
        }
    }

    /**
     * Initialize JList properties, e.g. font, foreground, and background,
     * and add the CellRendererPane.  The font, foreground, and background
     * properties are only set if their current value is either null
     * or a UIResource, other properties are set if the current
     * value is null.
     *
     * @see #uninstallDefaults
     * @see #installUI
     * @see CellRendererPane
     */
    @Override
    protected void installDefaults() {
        super.installDefaults();
        updateStriped();
    }
    /*
    protected void installListeners() {
    list.addMouseListener(defaultDragRecognizer);
    list.addMouseMotionListener(defaultDragRecognizer);
    super.installListeners();

    // Remove the dreaded BasicDragGestureRecognizer from the list
    boolean removalSuccessful = false;
    MouseListener[] ml = list.getMouseListeners();
    for (int i = 0; i < ml.length; i++) {
    if (ml[i].getClass().getName().equals("javax.swing.plaf.basic.BasicListUI$ListDragGestureRecognizer")) {
    list.removeMouseListener(ml[i]);
    removalSuccessful = true;
    }
    }
    MouseMotionListener[] mml = list.getMouseMotionListeners();
    for (int i = 0; i < mml.length; i++) {
    if (mml[i].getClass().getName().equals("javax.swing.plaf.basic.BasicListUI$ListDragGestureRecognizer")) {
    list.removeMouseMotionListener(mml[i]);
    }
    }
    if (!removalSuccessful) {
    list.removeMouseListener(defaultDragRecognizer);
    list.removeMouseMotionListener(defaultDragRecognizer);
    }
    }

    protected void uninstallListeners() {
    super.uninstallListeners();
    list.removeMouseListener(defaultDragRecognizer);
    list.removeMouseMotionListener(defaultDragRecognizer);
    }
     */

    /**
     * Returns a new instance of QuaquaListUI.  QuaquaListUI delegates are
     * allocated one per JList.
     *
     * @return A new ListUI implementation for the Windows look and feel.
     */
    public static ComponentUI createUI(JComponent list) {
        return new QuaquaListUI();
    }

    @Override
    protected MouseInputListener createMouseInputListener() {
        return new QuaquaListMouseBehavior(list);
    }

    /**
     * This inner class is marked &quot;public&quot; due to a compiler bug.
     * This class should be treated as a &quot;protected&quot; inner class.
     * Instantiate it only within subclasses of BasicTableUI.
     */
    public class FocusHandler implements FocusListener {

        protected void repaintCellFocus() {
            Object[] cells = list.getSelectedValues();
            if (cells.length > 1) {
                list.repaint();
                return;
            }

            int leadIndex = list.getLeadSelectionIndex();
            if (leadIndex != -1) {
                Rectangle r = getCellBounds(list, leadIndex, leadIndex);
                if (r != null) {
                    list.repaint(r.x, r.y, r.width, r.height);
                }
            }
        }

        /* The focusGained() focusLost() methods run when the JList
         * focus changes.
         */
        public void focusGained(FocusEvent event) {
            // hasFocus = true;
            repaintCellFocus();
        }

        public void focusLost(FocusEvent event) {
            // hasFocus = false;
            repaintCellFocus();
        }
    }

    @Override
    protected FocusListener createFocusListener() {
        return new FocusHandler();
    }

    @Override
    protected ListDataListener createListDataListener() {
        return new ListDataHandler();
    }

    /**
     * The PropertyChangeListener that's added to the JList at
     * installUI time.  When the value of a JList property that
     * affects layout changes, we set a bit in updateLayoutStateNeeded.
     * If the JLists model changes we additionally remove our listeners
     * from the old model.  Likewise for the JList selectionModel.
     * <p>
     * <strong>Warning:</strong>
     * Serialized objects of this class will not be compatible with
     * future Swing releases. The current serialization support is
     * appropriate for short term storage or RMI between applications running
     * the same version of Swing.  As of 1.4, support for long term storage
     * of all JavaBeans<sup><font size="-2">TM</font></sup>
     * has been added to the <code>java.beans</code> package.
     * Please see {@link java.beans.XMLEncoder}.
     *
     * @see #maybeUpdateLayoutState
     * @see #createPropertyChangeListener
     * @see #installUI
     */
    public class PropertyChangeHandler extends BasicListUI.PropertyChangeHandler {

        @Override
        public void propertyChange(PropertyChangeEvent e) {
            String name = e.getPropertyName();

            if (name.equals("Quaqua.List.style")) {
                updateStriped();
            } else if ("layoutOrientation".equals(name)) {
                layoutOrientation = ((Integer) e.getNewValue()).intValue();
                updateStriped();
            } else if (name.equals("JComponent.sizeVariant")) {
                QuaquaUtilities.applySizeVariant(list);
            }
            super.propertyChange(e);
        }
    }

    /**
     * Creates an instance of PropertyChangeHandler that's added to
     * the JList by installUI().  Subclasses can override this method
     * to return a custom PropertyChangeListener, e.g.
     * <pre>
     * class MyListUI extends QuaquaListUI {
     *    protected PropertyChangeListener <b>createPropertyChangeListener</b>() {
     *        return new MyPropertyChangeListener();
     *    }
     *    public class MyPropertyChangeListener extends PropertyChangeHandler {
     *        public void propertyChange(PropertyChangeEvent e) {
     *            if (e.getPropertyName().equals("model")) {
     *                // do some extra work when the model changes
     *            }
     *            super.propertyChange(e);
     *        }
     *    }
     * }
     * </pre>
     *
     * @see PropertyChangeListener
     * @see #installUI
     */
    @Override
    protected PropertyChangeListener createPropertyChangeListener() {
        return new PropertyChangeHandler();
    }

    protected class ListDataHandler implements ListDataListener {
        //
        // ListDataListener
        //

        public void intervalAdded(ListDataEvent e) {
            updateLayoutStateNeeded = modelChanged;

            int minIndex = Math.min(e.getIndex0(), e.getIndex1());
            int maxIndex = Math.max(e.getIndex0(), e.getIndex1());

            /* Sync the SelectionModel with the DataModel.
             */

            ListSelectionModel sm = list.getSelectionModel();
            if (sm != null && sm.getMinSelectionIndex() != -1) {
                sm.insertIndexInterval(minIndex, maxIndex - minIndex + 1, true);
            }

            /* Repaint the entire list, from the origin of
             * the first added cell, to the bottom of the
             * component.
             */
            redrawList();
        }

        public void intervalRemoved(ListDataEvent e) {
            updateLayoutStateNeeded = modelChanged;

            /* Sync the SelectionModel with the DataModel.
             */

            ListSelectionModel sm = list.getSelectionModel();
            if (sm != null) {
                sm.removeIndexInterval(e.getIndex0(), e.getIndex1());
            }

            /* Repaint the entire list, from the origin of
             * the first removed cell, to the bottom of the
             * component.
             */

            redrawList();
        }

        public void contentsChanged(ListDataEvent e) {
            updateLayoutStateNeeded = modelChanged;

            if (list.getFixedCellHeight() == -1) {
                redrawList();
            } else {
                Rectangle bounds = list.getCellBounds(e.getIndex0(), e.getIndex1());
                if (bounds == null) {
                    redrawList();
                } else {
                    list.repaint(bounds);
                }
            }
        }
    }

    private void redrawList() {
        list.revalidate();
        list.repaint();
    }
    /*
    private static final ListDragGestureRecognizer defaultDragRecognizer =
    new ListDragGestureRecognizer();

    /**
     * Drag gesture recognizer for JList components
     * /
    static class ListDragGestureRecognizer extends QuaquaDragGestureRecognizer {

    /**
     * Determines if the following are true:
     * <ul>
     * <li>the press event is located over a selection
     * <li>the dragEnabled property is true
     * <li>A TranferHandler is installed
     * </ul>
     * <p>
     * This is implemented to perform the superclass behavior
     * followed by a check if the dragEnabled
     * property is set and if the location picked is selected.
     * /
    protected boolean isDragPossible(MouseEvent e) {
    if (super.isDragPossible(e)) {
    JList list = (JList) this.getComponent(e);
    if (list.getDragEnabled()) {
    QuaquaListUI ui = (QuaquaListUI) list.getUI();
    int row = ui.locationToIndex(list, e.getPoint());
    if ((row != -1) && list.isSelectedIndex(row)) {
    return true;
    /*		    } else if (row != -1 && list.getCellBounds(row, row).contains(e.getPoint())) {
    return true;         * /
    }
    }
    }
    return false;
    }
    }*/
}
