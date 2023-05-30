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
public class ConfirmationDialog extends javax.swing.JPanel {

    AdminExecutable executable;
    BookDB bdb;
    String bookID;
    public ConfirmationDialog(String bookID,AdminExecutable executable) {
        initComponents();
        setOpaque(false);
        this.executable = executable;
        this.bookID = bookID;
        bdb = new BookDB();
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

        cancel = new CustomizedClasses.RoundedBorderButton();
        jLabel23 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        remove = new CustomizedClasses.RoundedBorderButton();
        jLabel1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cancel.setBackground(new java.awt.Color(254, 254, 254));
        cancel.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(206, 206, 206), 1, true), javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        cancel.setForeground(new java.awt.Color(82, 82, 82));
        cancel.setText("No");
        cancel.setFocusable(false);
        cancel.setFont(new java.awt.Font("Roboto Medium", 0, 18)); // NOI18N
        cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelActionPerformed(evt);
            }
        });
        add(cancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 170, 180, 50));

        jLabel23.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("Are you sure about that?");
        jLabel23.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 430, 20));

        jLabel20.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("You are about to remove this book.");
        jLabel20.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 430, 30));

        jLabel18.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(42, 42, 42));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Remove Book");
        add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 430, 30));

        remove.setBackground(new java.awt.Color(210, 42, 129));
        remove.setForeground(new java.awt.Color(255, 255, 255));
        remove.setText("Yes");
        remove.setFocusable(false);
        remove.setFont(new java.awt.Font("Roboto Medium", 0, 18)); // NOI18N
        remove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeActionPerformed(evt);
            }
        });
        add(remove, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 180, 50));
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 430, 260));
    }// </editor-fold>//GEN-END:initComponents

    private void cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelActionPerformed
        // TODO add your handling code here:
        GlassPanePopup.closePopupLast();
    }//GEN-LAST:event_cancelActionPerformed

    private void removeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeActionPerformed
        bdb.removeBook(this.bookID);
        this.executable.executeBookPanels();
        GlassPanePopup.closePopupAll();
    }//GEN-LAST:event_removeActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private CustomizedClasses.RoundedBorderButton cancel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel23;
    private CustomizedClasses.RoundedBorderButton remove;
    // End of variables declaration//GEN-END:variables
}
