/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package User;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import javax.swing.ImageIcon;

/**
 *
 * @author Aesthetics
 */
public class NoDataFound extends javax.swing.JPanel {

    public NoDataFound(String string) {
        initComponents();
        title.setText("No " + string + " Found");
    }

    public void setIcon(ImageIcon icon) {
        this.icon.setIcon(icon);
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 15, 15));
        g2.dispose();
        super.paintComponent(grphcs);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        icon = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        title = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(249, 249, 249));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        icon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/images/books.png"))); // NOI18N
        add(icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 20, 210, 120));

        jLabel20.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(106, 106, 106));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("We recommend that visit our library menu to ");
        jLabel20.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 170, 430, 30));

        jLabel23.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(106, 106, 106));
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("add books and rent in cart menu.");
        jLabel23.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 190, 430, 40));

        title.setFont(new java.awt.Font("Roboto", 1, 20)); // NOI18N
        title.setForeground(new java.awt.Color(82, 82, 82));
        title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title.setText("No Checkouts Found");
        add(title, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 140, 430, 30));
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 810, 250));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel icon;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables
}
