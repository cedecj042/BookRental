/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Admin;

import Classes.User;

/**
 *
 * @author Aesthetics
 */
public class UserPanel extends javax.swing.JPanel {

    public UserPanel(User user) {
        initComponents();
        userID.setText(user.getUserID());
        username.setText(user.getUsername());
        email.setText(user.getEmail());
        name.setText(user.getFirstName() + " " + user.getLastName());
        phoneNum.setText(user.getPhoneNum());
        Image.setIcon(user.getImage());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundpanel = new CustomizedClasses.RoundPanel();
        Image = new javax.swing.JLabel();
        email = new javax.swing.JLabel();
        phoneNum = new javax.swing.JLabel();
        name = new javax.swing.JLabel();
        userID = new javax.swing.JLabel();
        username = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        roundpanel.setBackground(new java.awt.Color(249, 249, 249));
        roundpanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Image.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        roundpanel.add(Image, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, -12, 70, 70));

        add(roundpanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 50, 50));

        email.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        email.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        email.setText("Email");
        add(email, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 0, 190, 70));

        phoneNum.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        phoneNum.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        phoneNum.setText("Phone Number");
        add(phoneNum, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 0, 150, 70));

        name.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        name.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        name.setText("Name");
        add(name, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 0, 160, 70));

        userID.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        userID.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        userID.setText("User ID");
        add(userID, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, 80, 70));

        username.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        username.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        username.setText("Username");
        add(username, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 0, 90, 70));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Image;
    private javax.swing.JLabel email;
    private javax.swing.JLabel name;
    private javax.swing.JLabel phoneNum;
    private CustomizedClasses.RoundPanel roundpanel;
    private javax.swing.JLabel userID;
    private javax.swing.JLabel username;
    // End of variables declaration//GEN-END:variables
}
