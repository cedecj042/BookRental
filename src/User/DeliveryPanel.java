/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package User;

import Classes.DeliveryDB;
import Classes.DeliveryPanels;
import CustomizedClasses.RadioButtonCustom;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.util.Enumeration;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;

/**
 *
 * @author Aesthetics
 */
public class DeliveryPanel extends javax.swing.JPanel {

    DeliveryPanels dp;

    public DeliveryPanels getDp() {
        return dp;
    }

    public void setDp(DeliveryPanels dp) {
        this.dp = dp;
    }

    public RadioButtonCustom getRadio() {
        return radio;
    }

    public void setRadio(RadioButtonCustom radio) {
        this.radio = radio;
    }
    DeliveryDB ddb;

    /**
     * Creates new form delivery
     */
    public DeliveryPanel(DeliveryPanels dp) {
        initComponents();
        this.dp = dp;
        deliveryAddress.setText(dp.getAddress());
        deliveryName.setText(dp.getName());
        deliveryLandmark.setText(dp.getLandmark());
        ddb = new DeliveryDB();

    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 0, 0));
        g2.dispose();
        super.paintComponents(grphcs);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        deliveryName = new javax.swing.JLabel();
        deliveryAddress = new javax.swing.JLabel();
        deliveryLandmark = new javax.swing.JLabel();
        remove = new javax.swing.JButton();
        edit = new javax.swing.JButton();
        radio = new CustomizedClasses.RadioButtonCustom();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        deliveryName.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        deliveryName.setText("Home");
        add(deliveryName, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, 180, -1));

        deliveryAddress.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        deliveryAddress.setForeground(new java.awt.Color(155, 155, 155));
        deliveryAddress.setText("Delivery Address");
        add(deliveryAddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, 260, 20));

        deliveryLandmark.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        deliveryLandmark.setForeground(new java.awt.Color(155, 155, 155));
        deliveryLandmark.setText("Landmark:");
        add(deliveryLandmark, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, 180, 20));

        remove.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/images/remove.png"))); // NOI18N
        remove.setBorderPainted(false);
        remove.setContentAreaFilled(false);
        remove.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        remove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeActionPerformed(evt);
            }
        });
        add(remove, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 10, 50, 80));

        edit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/images/edit.png"))); // NOI18N
        edit.setBorderPainted(false);
        edit.setContentAreaFilled(false);
        edit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editActionPerformed(evt);
            }
        });
        add(edit, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 10, 50, 80));

        radio.setBackground(new java.awt.Color(0, 156, 220));
        radio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioActionPerformed(evt);
            }
        });
        add(radio, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 400, 100));
    }// </editor-fold>//GEN-END:initComponents

    private void editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editActionPerformed
        // TODO add your handling code here:
        dp.getCd().editAddress(dp.getAddressID());
    }//GEN-LAST:event_editActionPerformed

    private void radioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioActionPerformed
        // TODO add your handling code here:
        ButtonGroup buttonGroup = dp.getCd().getAddressGroup();

        Enumeration<AbstractButton> buttons = buttonGroup.getElements();
        while (buttons.hasMoreElements()) {
            AbstractButton button = buttons.nextElement();
            if (button instanceof RadioButtonCustom) {
                DeliveryPanels delivery = ((RadioButtonCustom) button).getDeliverypanel();
                if (delivery.getDelivery().getRadio().isSelected()) {
                    delivery.getDelivery().setBackground(new Color(246, 252, 255));
                } else {
                    delivery.getDelivery().setBackground(new Color(255, 255, 255));
                }
            }
        }
    }//GEN-LAST:event_radioActionPerformed

    private void removeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeActionPerformed
        // TODO add your handling code here:
        ddb.removeDeliveryAddress(this.dp.getAddressID());
        dp.getCd().executable.executeDeliveryPanel();

        ButtonGroup buttonGroup = dp.getCd().getAddressGroup();

        Enumeration<AbstractButton> buttons = buttonGroup.getElements();
        while (buttons.hasMoreElements()) {
            AbstractButton button = buttons.nextElement();
            if (button instanceof RadioButtonCustom) {
                DeliveryPanels delivery = ((RadioButtonCustom) button).getDeliverypanel();
                buttonGroup.remove(button);
                break;
            }
        }
    }//GEN-LAST:event_removeActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel deliveryAddress;
    private javax.swing.JLabel deliveryLandmark;
    private javax.swing.JLabel deliveryName;
    private javax.swing.JButton edit;
    private CustomizedClasses.RadioButtonCustom radio;
    private javax.swing.JButton remove;
    // End of variables declaration//GEN-END:variables
}
