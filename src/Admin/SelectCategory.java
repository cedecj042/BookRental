/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Admin;

import Classes.BookDB;
import CustomizedClasses.GlassPanePopup;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;

/**
 *
 * @author Aesthetics
 */
public class SelectCategory extends javax.swing.JPanel {

    public SelectCategory() {
        initComponents();
        setOpaque(false);

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

        jLabel23 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        remove = new CustomizedClasses.RoundedBorderButton();
        jLabel1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel23.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("genre. Try again.");
        jLabel23.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 430, 20));

        jLabel20.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("You did not select the category for this");
        jLabel20.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 430, 30));

        jLabel18.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(42, 42, 42));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Please Select a Category");
        add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 430, 30));

        remove.setBackground(new java.awt.Color(210, 42, 129));
        remove.setForeground(new java.awt.Color(255, 255, 255));
        remove.setText("Okay");
        remove.setFocusable(false);
        remove.setFont(new java.awt.Font("Roboto Medium", 0, 18)); // NOI18N
        remove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeActionPerformed(evt);
            }
        });
        add(remove, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 360, 50));
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 430, 260));
    }// </editor-fold>//GEN-END:initComponents

    private void removeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeActionPerformed
        GlassPanePopup.closePopupAll();
    }//GEN-LAST:event_removeActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel23;
    private CustomizedClasses.RoundedBorderButton remove;
    // End of variables declaration//GEN-END:variables
}
