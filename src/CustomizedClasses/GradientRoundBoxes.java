/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CustomizedClasses;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JPanel;

/**
 *
 * @author Aesthetics
 */
public class GradientRoundBoxes extends JPanel {

    private Color gradientStart;
    private Color gradientEnd;

    public Color getGradientStart() {
        return gradientStart;
    }

    public void setGradientStart(Color gradientStart) {
        this.gradientStart = gradientStart;
    }

    public Color getGradientEnd() {
        return gradientEnd;
    }

    public void setGradientEnd(Color gradientEnd) {
        this.gradientEnd = gradientEnd;
    }

    public GradientRoundBoxes() {
        this.gradientStart = new Color(52, 178, 225);
        this.gradientEnd = new Color(52, 153, 225);
    }

    @Override
    public void paintComponent(Graphics g) {
        int width = getWidth();
        int height = getHeight();
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Create a rounded rectangle shape with desired corner radius
        int cornerRadius = 40;
        RoundRectangle2D roundedRect = new RoundRectangle2D.Double(0, 0, width, height, cornerRadius, cornerRadius);

        // Set the rounded rectangle shape as the clipping region
        g2.setClip(roundedRect);

        // Perform the painting with the rounded clipping region
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint painter = new GradientPaint(0, 0, gradientStart,
                0, height, gradientEnd);
        Paint oldPainter = g2.getPaint();
        g2.setPaint(painter);
        g2.fill(g2.getClip());
        painter = new GradientPaint(0, 0, gradientEnd,
                0, height / 2, gradientStart);
        g2.setPaint(painter);
        g2.fill(g2.getClip());

        painter = new GradientPaint(0, height / 2, gradientStart,
                0, height, gradientEnd);
        g2.setPaint(painter);
        g2.fill(g2.getClip());

        g2.setPaint(oldPainter);
        
    }

}
