/*
 * @(#)ResolutionIndependenceTest.java
 * Quaqua Look and Feel. Copyright © 2020 Werner Randelshofer, Switzerland. MIT License.
 */

package test;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 * ResolutionIndependenceTest.
 *
 * @author Werner Randelshofer
 *  @version 1.0 ResolutionIndependenceTest Created.
 */
public class ResolutionIndependenceTest extends javax.swing.JPanel {
    private Point mouseLocation;

    /**
     * Creates new form.
     */
    public ResolutionIndependenceTest() {
        initComponents();
        addMouseMotionListener(new MouseMotionListener() {

            public void mouseDragged(MouseEvent e) {
                mouseLocation = e.getPoint();
                repaint();
            }

            public void mouseMoved(MouseEvent e) {
                mouseLocation = e.getPoint();
                System.out.println(e);
                repaint();
            }

        });


        setPreferredSize(new Dimension(240, 240));
        setOpaque(true);
    }

    public void paintComponent(Graphics gr) {
        Graphics2D g = (Graphics2D) gr;

        g.clearRect(0, 0, getWidth(), getHeight());

        g.setFont(new Font("Dialog", Font.PLAIN, 12));
        g.drawString("This square is 200 pixels long", 20, 15);

        Rectangle r = new Rectangle(20, 20, 200, 200);

        g.draw(r);

        if (mouseLocation != null && r.contains(mouseLocation)) {
            g.drawLine(mouseLocation.x - 5, mouseLocation.y, mouseLocation.x + 5, mouseLocation.y);
            g.drawLine(mouseLocation.x, mouseLocation.y - 5, mouseLocation.x, mouseLocation.y + 5);
            g.drawOval(mouseLocation.x - 7, mouseLocation.y - 7, 14, 14);
        }

        g.drawString(g.getDeviceConfiguration().getDefaultTransform().toString(), 20, 235);
        System.out.println(g.getDeviceConfiguration().getNormalizingTransform());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame f = new JFrame("ResolutionIndependenceTest");
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f.add(new ResolutionIndependenceTest());
                f.pack();
                f.setVisible(true);
            }
        });
    }

    /**
     * This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setLayout(new java.awt.BorderLayout());
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

}
