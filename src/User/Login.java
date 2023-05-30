/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package User;

import Admin.AdminLogin;
import Classes.UserDB;
import Classes.User;
import Classes.UserSession;
import CustomizedClasses.RoundedBorderButton;
import static User.SignUp.setCompoundBorder;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JOptionPane;

/**
 *
 * @author Aesthetics
 */
public class Login extends javax.swing.JFrame {

    Color mygrey = new Color(206, 206, 206);
    Color myblue = new Color(0, 145, 220);
    Color myred = new Color(255, 16, 16);
    User user;

    UserDB db;
    /**
     * Creates new form
     */
    public Login() {
        initComponents();
        this.db = new UserDB();
        Login = new RoundedBorderButton();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jUsername = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jPassword = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jSignUpButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        LoginAsAdmin = new javax.swing.JButton();
        Login = new CustomizedClasses.RoundedBorderButton();
        background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Poppins", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(26, 26, 26));
        jLabel2.setText("Sign In");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 150, 210, 50));

        jLabel4.setFont(new java.awt.Font("Roboto", 0, 20)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(26, 26, 26));
        jLabel4.setText("Username");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 240, 120, 30));

        jUsername.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        jUsername.setForeground(new java.awt.Color(206, 206, 206));
        jUsername.setText("Enter username");
        jUsername.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(206, 206, 206), 1, true), javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        jUsername.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jUsernameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jUsernameFocusLost(evt);
            }
        });
        jUsername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jUsernameActionPerformed(evt);
            }
        });
        jPanel2.add(jUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 270, 370, 54));

        jLabel5.setFont(new java.awt.Font("Roboto", 0, 20)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(26, 26, 26));
        jLabel5.setText("Password");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 340, 90, 30));

        jPassword.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        jPassword.setForeground(new java.awt.Color(206, 206, 206));
        jPassword.setText("******");
        jPassword.setToolTipText("");
        jPassword.setActionCommand("<Not Set>");
        jPassword.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(206, 206, 206), 1, true), javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        jPassword.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jPassword.setDoubleBuffered(true);
        jPassword.setDragEnabled(true);
        jPassword.setMargin(new java.awt.Insets(2, 10, 2, 10));
        jPassword.setName(""); // NOI18N
        jPassword.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jPasswordFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jPasswordFocusLost(evt);
            }
        });
        jPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordActionPerformed(evt);
            }
        });
        jPanel2.add(jPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 370, 370, 54));

        jButton1.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 156, 220));
        jButton1.setText("Forgot your password?");
        jButton1.setBorder(null);
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 450, -1, 50));

        jLabel6.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(26, 26, 26));
        jLabel6.setText("Not yet registered?");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 650, -1, -1));

        jSignUpButton.setFont(new java.awt.Font("Roboto Medium", 0, 18)); // NOI18N
        jSignUpButton.setForeground(new java.awt.Color(0, 156, 220));
        jSignUpButton.setText("Sign Up");
        jSignUpButton.setBorderPainted(false);
        jSignUpButton.setContentAreaFilled(false);
        jSignUpButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jSignUpButton.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jSignUpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSignUpButtonActionPerformed(evt);
            }
        });
        jPanel2.add(jSignUpButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 650, 100, 20));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/images/upbook logo.png"))); // NOI18N
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 70, 180, 60));

        LoginAsAdmin.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        LoginAsAdmin.setForeground(new java.awt.Color(42, 42, 42));
        LoginAsAdmin.setText("Sign In as Admin");
        LoginAsAdmin.setBorderPainted(false);
        LoginAsAdmin.setContentAreaFilled(false);
        LoginAsAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoginAsAdminActionPerformed(evt);
            }
        });
        jPanel2.add(LoginAsAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 690, 480, -1));

        Login.setBackground(new java.awt.Color(0, 156, 220));
        Login.setForeground(new java.awt.Color(255, 255, 255));
        Login.setText("Login");
        Login.setFont(new java.awt.Font("Roboto Medium", 0, 18)); // NOI18N
        Login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoginActionPerformed(evt);
            }
        });
        jPanel2.add(Login, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 450, 130, 50));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 0, 480, 790));

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/images/sidebackground.png"))); // NOI18N
        getContentPane().add(background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -10, 720, 800));

        setSize(new java.awt.Dimension(1216, 798));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jUsernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jUsernameActionPerformed

    private void jPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        ForgotPassword forgotpass = new ForgotPassword();
        forgotpass.show();
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jSignUpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSignUpButtonActionPerformed
        SignUp mysignup = new SignUp();
        mysignup.show();
        dispose();
    }//GEN-LAST:event_jSignUpButtonActionPerformed

    private void LoginAsAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoginAsAdminActionPerformed
        // TODO add your handling code here:
        AdminLogin myadmin = new AdminLogin();
        myadmin.show();
        dispose();
    }//GEN-LAST:event_LoginAsAdminActionPerformed

    private void jUsernameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jUsernameFocusGained
        // TODO add your handling code here:
        if (jUsername.getText().equals("Enter username")) {
            jUsername.setText("");
            jUsername.setForeground(Color.BLACK);
        }
        setCompoundBorder(jUsername, myblue);
    }//GEN-LAST:event_jUsernameFocusGained

    private void jPasswordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPasswordFocusGained
        // TODO add your handling code here:
        if (jPassword.getText().equals("******")) {
            jPassword.setText("");
            jPassword.setForeground(Color.BLACK);
        }
        setCompoundBorder(jPassword, myblue);
    }//GEN-LAST:event_jPasswordFocusGained

    private void jUsernameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jUsernameFocusLost
        // TODO add your handling code here:
        if (jUsername.getText().isEmpty()) {
            jUsername.setForeground(mygrey);
            jUsername.setText("Enter username");
            setCompoundBorder(jUsername, mygrey);
        } else {
            setCompoundBorder(jUsername, myblue);
        }

    }//GEN-LAST:event_jUsernameFocusLost

    private void jPasswordFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPasswordFocusLost
        // TODO add your handling code here:
        if (jPassword.getText().isEmpty()) {
            jPassword.setForeground(mygrey);
            jPassword.setText("******");
            setCompoundBorder(jPassword, mygrey);
        } else {
            setCompoundBorder(jPassword, myblue);
        }
    }//GEN-LAST:event_jPasswordFocusLost

    private void LoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoginActionPerformed
        // TODO add your handling code here:
        String username = jUsername.getText();
        String password = String.valueOf(jPassword.getPassword());
        String hashedPass = db.hashPassword(password);

        if (username.isEmpty() || password.isEmpty()) {
            if (username.isEmpty()) {
                setCompoundBorder(jUsername, myred);
            }
            if (password.isEmpty()) {
                setCompoundBorder(jPassword, myred);
            }
            JOptionPane.showMessageDialog(this, "Please enter all fields.", "Try Again", JOptionPane.ERROR_MESSAGE);
        } else {
            
            if (db.getUser(username) != null) {
                if (db.checkPassword(username,hashedPass)) {
                    user = db.getUser(username);
                    UserSession.getInstance().setUser(user);
                    JOptionPane.showMessageDialog(null, "Login Successful");
                    Home myHome = new Home();
                    myHome.show();
                    dispose();
                } else {
                    setCompoundBorder(jUsername, myred);
                    setCompoundBorder(jPassword, myred);
                    JOptionPane.showMessageDialog(this, "Your username or password is incorrect. Please try again", "Try Again", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                setCompoundBorder(jUsername, myred);
                setCompoundBorder(jPassword, myred);
                JOptionPane.showMessageDialog(this, "Your username or password is incorrect. Please try again", "Try Again", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_LoginActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private CustomizedClasses.RoundedBorderButton Login;
    private javax.swing.JButton LoginAsAdmin;
    private javax.swing.JLabel background;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPasswordField jPassword;
    private javax.swing.JButton jSignUpButton;
    private javax.swing.JTextField jUsername;
    // End of variables declaration//GEN-END:variables
}
