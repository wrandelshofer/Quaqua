/*
 * @(#)PaletteTest.java
 * Quaqua Look and Feel. Copyright 2020 © Werner Randelshofer, Switzerland. MIT License.
 */
package test;

import java.awt.*;
import javax.swing.*;

/**
 * PaletteTest.
 *
 * @author Werner Randelshofer
 * @version $Id$
 */
public class PaletteTest extends javax.swing.JPanel {

    private Window myWindow;
    private JDialog regularHPalette, regularVPalette;
    private JDialog smallHPalette, smallVPalette;
    private JDialog miniHPalette, miniVPalette;
    private final static int regularWidth = 200, smallWidth = 160, miniWidth = 130;

    /**
     * Creates a new instance.
     */
    public PaletteTest() {
        initComponents();
    }

    public void addNotify() {
        super.addNotify();
        myWindow = SwingUtilities.getWindowAncestor(this);
        FloatingPaletteHandler.getInstance().add(myWindow);
    }

    public void removeNotify() {
        super.removeNotify();
        FloatingPaletteHandler.getInstance().remove(myWindow);
        myWindow = null;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        regularHPaletteButton = new javax.swing.JButton();
        regularVPaletteButton = new javax.swing.JButton();
        smallHPaletteButton = new javax.swing.JButton();
        smallVPaletteButton = new javax.swing.JButton();
        miniHPaletteButton = new javax.swing.JButton();
        miniVPaletteButton = new javax.swing.JButton();

        FormListener formListener = new FormListener();

        setLayout(new java.awt.GridLayout(0, 1));

        regularHPaletteButton.setText("Regular Horizontal Palette");
        regularHPaletteButton.addActionListener(formListener);
        add(regularHPaletteButton);

        regularVPaletteButton.setText("Regular Vertical Palette");
        regularVPaletteButton.addActionListener(formListener);
        add(regularVPaletteButton);

        smallHPaletteButton.setText("Small Horizontal Palette");
        smallHPaletteButton.addActionListener(formListener);
        add(smallHPaletteButton);

        smallVPaletteButton.setText("Small Vertical Palette");
        smallVPaletteButton.addActionListener(formListener);
        add(smallVPaletteButton);

        miniHPaletteButton.setText("Mini Horizontal Palette");
        miniHPaletteButton.addActionListener(formListener);
        add(miniHPaletteButton);

        miniVPaletteButton.setText("Mini Vertical Palette");
        miniVPaletteButton.addActionListener(formListener);
        add(miniVPaletteButton);
    }

    // Code for dispatching events from components to event handlers.

    private class FormListener implements java.awt.event.ActionListener {
        FormListener() {}
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            if (evt.getSource() == regularHPaletteButton) {
                PaletteTest.this.regularHorizontalPalette(evt);
            }
            else if (evt.getSource() == regularVPaletteButton) {
                PaletteTest.this.regularVerticalPalette(evt);
            }
            else if (evt.getSource() == smallHPaletteButton) {
                PaletteTest.this.smallHorizontalPalette(evt);
            }
            else if (evt.getSource() == smallVPaletteButton) {
                PaletteTest.this.smallVerticalPalette(evt);
            }
            else if (evt.getSource() == miniHPaletteButton) {
                PaletteTest.this.miniHorizontalPalette(evt);
            }
            else if (evt.getSource() == miniVPaletteButton) {
                PaletteTest.this.miniVerticalPalette(evt);
            }
        }
    }// </editor-fold>//GEN-END:initComponents

    private void miniHorizontalPalette(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miniHorizontalPalette
        if (miniHPalette == null) {
            JDialog d = new JDialog((Frame) SwingUtilities.getWindowAncestor(this));
            if (UIManager.getLookAndFeel().getSupportsWindowDecorations()) {
                d.setUndecorated(true);
            }
            d.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
            d.getRootPane().setFont(UIManager.getFont("MiniSystemFont"));
            d.getRootPane().putClientProperty("Quaqua.RootPane.isPalette", Boolean.TRUE);
            //d.getRootPane().putClientProperty("Window.style", "small");
            d.setAlwaysOnTop(true);
            FloatingPaletteHandler.getInstance().addPalette(d);
            d.setSize(miniWidth, miniWidth);
            Window w = SwingUtilities.getWindowAncestor(this);
            d.setLocation(w.getX() + w.getWidth(), w.getY() + regularWidth + smallWidth);
            d.setTitle("Mini Horizontal Palette");
            miniHPalette = d;
        }
        miniHPalette.setVisible(true);

    }//GEN-LAST:event_miniHorizontalPalette

    private void smallVerticalPalette(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_smallVerticalPalette
        if (smallVPalette == null) {

            JDialog d = new JDialog((Frame) SwingUtilities.getWindowAncestor(this));
            if (UIManager.getLookAndFeel().getSupportsWindowDecorations()) {
                d.setUndecorated(true);
            }
            d.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
            d.getRootPane().setFont(UIManager.getFont("SmallSystemFont"));
            d.getRootPane().putClientProperty("Quaqua.RootPane.isVertical", Boolean.TRUE);
            d.getRootPane().putClientProperty("Quaqua.RootPane.isPalette", Boolean.TRUE);
            //d.getRootPane().putClientProperty("Window.style", "small");
            d.setAlwaysOnTop(true);
            FloatingPaletteHandler.getInstance().addPalette(d);
            d.setSize(smallWidth, smallWidth);
            Window w = SwingUtilities.getWindowAncestor(this);
            d.setLocation(w.getX() + regularWidth, w.getY() + w.getHeight());
            d.setTitle("Small Vertical Palette");
            smallVPalette = d;
        }
        smallVPalette.setVisible(true);

    }//GEN-LAST:event_smallVerticalPalette

    private void smallHorizontalPalette(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_smallHorizontalPalette
        if (smallHPalette == null) {
            JDialog d = new JDialog((Frame) SwingUtilities.getWindowAncestor(this));
            if (UIManager.getLookAndFeel().getSupportsWindowDecorations()) {
                d.setUndecorated(true);
            }
            d.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
            d.getRootPane().setFont(UIManager.getFont("SmallSystemFont"));
            d.getRootPane().putClientProperty("Quaqua.RootPane.isVertical", Boolean.FALSE);
            d.getRootPane().putClientProperty("Quaqua.RootPane.isPalette", Boolean.TRUE);
            //d.getRootPane().putClientProperty("Window.style", "small");
            d.setAlwaysOnTop(true);
            FloatingPaletteHandler.getInstance().addPalette(d);
            d.setSize(smallWidth, smallWidth);
            Window w = SwingUtilities.getWindowAncestor(this);
            d.setLocation(w.getX() + w.getWidth(), w.getY() + regularWidth);
            d.setTitle("Small Horizontal Palette");
            smallHPalette = d;
        }
        smallHPalette.setVisible(true);
    }//GEN-LAST:event_smallHorizontalPalette

    private void regularVerticalPalette(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regularVerticalPalette
        if (regularVPalette == null) {
            JDialog d = new JDialog((Frame) SwingUtilities.getWindowAncestor(this));
            if (UIManager.getLookAndFeel().getSupportsWindowDecorations()) {
                d.setUndecorated(true);
            }
            d.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
            d.getRootPane().setFont(UIManager.getFont("SystemFont"));
            d.getRootPane().putClientProperty("Quaqua.RootPane.isVertical", Boolean.TRUE);
            d.getRootPane().putClientProperty("Quaqua.RootPane.isPalette", Boolean.TRUE);
            //d.getRootPane().putClientProperty("Window.style", "small");
            d.setAlwaysOnTop(true);
            FloatingPaletteHandler.getInstance().addPalette(d);
            d.setSize(regularWidth, regularWidth);
            Window w = SwingUtilities.getWindowAncestor(this);
            d.setLocation(w.getX(), w.getY() + w.getHeight());
            d.setTitle("Regular Vertical Palette");
            regularVPalette = d;
        }
        regularVPalette.setVisible(true);

    }//GEN-LAST:event_regularVerticalPalette

    private void regularHorizontalPalette(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regularHorizontalPalette
        if (regularHPalette == null) {
            JDialog d = new JDialog((Frame) SwingUtilities.getWindowAncestor(this));
            if (UIManager.getLookAndFeel().getSupportsWindowDecorations()) {
                d.setUndecorated(true);
            }
            d.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
            d.getRootPane().setFont(UIManager.getFont("SystemFont"));
            d.getRootPane().putClientProperty("Quaqua.RootPane.isVertical", Boolean.FALSE);
            d.getRootPane().putClientProperty("Quaqua.RootPane.isPalette", Boolean.TRUE);
            //d.getRootPane().putClientProperty("Window.style", "small");
            d.setAlwaysOnTop(true);
            FloatingPaletteHandler.getInstance().addPalette(d);
            d.setSize(regularWidth, regularWidth);
            Window w = SwingUtilities.getWindowAncestor(this);
            d.setLocation(w.getX() + w.getWidth(), w.getY());
            d.setTitle("Regular Horizontal Palette");
            regularHPalette = d;
        }
        regularHPalette.setVisible(true);

    }//GEN-LAST:event_regularHorizontalPalette

    private void miniVerticalPalette(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miniVerticalPalette
        if (miniVPalette == null) {
            JDialog d = new JDialog((Frame) SwingUtilities.getWindowAncestor(this));
            if (UIManager.getLookAndFeel().getSupportsWindowDecorations()) {
                d.setUndecorated(true);
            }
            d.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
            d.getRootPane().setFont(UIManager.getFont("MiniSystemFont"));
            d.getRootPane().putClientProperty("Quaqua.RootPane.isVertical", Boolean.TRUE);
            d.getRootPane().putClientProperty("Quaqua.RootPane.isPalette", Boolean.TRUE);
            //d.getRootPane().putClientProperty("Window.style", "small");
            d.setAlwaysOnTop(true);
            FloatingPaletteHandler.getInstance().addPalette(d);
            d.setSize(miniWidth, miniWidth);
            Window w = SwingUtilities.getWindowAncestor(this);
            d.setLocation(w.getX() + regularWidth + smallWidth, w.getY() + w.getHeight());
            d.setTitle("Mini Vertical Palette");
            miniVPalette = d;
        }
        miniVPalette.setVisible(true);
    }//GEN-LAST:event_miniVerticalPalette
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton miniHPaletteButton;
    private javax.swing.JButton miniVPaletteButton;
    private javax.swing.JButton regularHPaletteButton;
    private javax.swing.JButton regularVPaletteButton;
    private javax.swing.JButton smallHPaletteButton;
    private javax.swing.JButton smallVPaletteButton;
    // End of variables declaration//GEN-END:variables
}
