/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package User;

import CustomizedClasses.GlassPanePopup;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;

/**
 *
 * @author Aesthetics
 */
public class PhoneError extends javax.swing.JPanel {

    /**
     * Creates new form NoSelectedBooks
     */
    public PhoneError() {
        initComponents();
    }
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 25, 25));
        g2.dispose();
        super.paintComponent(grphcs);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel18 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        add = new CustomizedClasses.RoundedBorderButton();
        bg = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel18.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(42, 42, 42));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Phone Number not Found");
        add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 430, 30));

        jLabel20.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Please enter the 11 digit Philippine phone number");
        jLabel20.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 430, 30));

        jLabel23.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("in order to proceed with the payment.");
        jLabel23.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 430, 60));

        add.setBackground(new java.awt.Color(0, 156, 220));
        add.setForeground(new java.awt.Color(255, 255, 255));
        add.setText("Noted");
        add.setFocusable(false);
        add.setFont(new java.awt.Font("Roboto Medium", 0, 18)); // NOI18N
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });
        add(add, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 370, 50));
        add(bg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 430, 270));
    }// </editor-fold>//GEN-END:initComponents

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
        // TODO add your handling code here:
        GlassPanePopup.closePopupLast();
    }//GEN-LAST:event_addActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private CustomizedClasses.RoundedBorderButton add;
    private javax.swing.JLabel bg;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel23;
    // End of variables declaration//GEN-END:variables
}
