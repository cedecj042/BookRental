/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package User;

import java.awt.CardLayout;

/**
 *
 * @author Aesthetics
 */
public class Payment extends javax.swing.JFrame {

    /**
     * Creates new form Payment
     */
    CardLayout cardlayout;
    
    
    public Payment() {
        initComponents();
        
        cardlayout = (CardLayout) PaymentMain.getLayout();
        
        cardlayout.show(PaymentMain, "Login");
        PaymentMain.revalidate();
        PaymentMain.repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PaymentMain = new javax.swing.JPanel();
        Login = new javax.swing.JPanel();
        main = new javax.swing.JPanel();
        top = new javax.swing.JPanel();
        jMerchant = new javax.swing.JLabel();
        jAmount = new javax.swing.JLabel();
        merchant = new javax.swing.JLabel();
        amount = new javax.swing.JLabel();
        bottom = new javax.swing.JPanel();
        PHLabel = new javax.swing.JLabel();
        jLoginTitle = new javax.swing.JLabel();
        jPhoneNum = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        phoneNum = new javax.swing.JTextField();
        login = new CustomizedClasses.CustomButton();
        shadow = new javax.swing.JLabel();
        gradientPanel = new CustomizedClasses.GradientPanel();
        gcash = new javax.swing.JLabel();
        back = new javax.swing.JButton();
        Payment = new javax.swing.JPanel();
        main1 = new javax.swing.JPanel();
        merchantName = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jbalance = new javax.swing.JLabel();
        pay = new CustomizedClasses.CustomButton();
        jSeparator2 = new javax.swing.JSeparator();
        initialAmount = new javax.swing.JLabel();
        gcashLabel = new javax.swing.JLabel();
        jLoginTitle5 = new javax.swing.JLabel();
        radio = new CustomizedClasses.RadioButtonCustom();
        jLabel1 = new javax.swing.JLabel();
        note2 = new javax.swing.JLabel();
        totalAmount = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        note1 = new javax.swing.JLabel();
        shadow1 = new javax.swing.JLabel();
        gradientPanel1 = new CustomizedClasses.GradientPanel();
        gcash1 = new javax.swing.JLabel();
        back1 = new javax.swing.JButton();
        Receipt = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        TransactionID = new javax.swing.JLabel();
        DateandTime = new javax.swing.JLabel();
        receipt = new javax.swing.JLabel();
        gradientPanel2 = new CustomizedClasses.GradientPanel();
        checkoutSummary = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PaymentMain.setLayout(new java.awt.CardLayout());

        Login.setBackground(new java.awt.Color(255, 255, 255));
        Login.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        main.setBackground(new java.awt.Color(255, 255, 255));
        main.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        top.setBackground(new java.awt.Color(251, 251, 251));
        top.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jMerchant.setBackground(new java.awt.Color(155, 155, 155));
        jMerchant.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jMerchant.setForeground(new java.awt.Color(155, 155, 155));
        jMerchant.setText("Merchant");
        top.add(jMerchant, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 87, -1));

        jAmount.setBackground(new java.awt.Color(155, 155, 155));
        jAmount.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jAmount.setForeground(new java.awt.Color(155, 155, 155));
        jAmount.setText("Amount Due");
        top.add(jAmount, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, -1, -1));

        merchant.setBackground(new java.awt.Color(155, 155, 155));
        merchant.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        merchant.setForeground(new java.awt.Color(155, 155, 155));
        merchant.setText("UpBook");
        top.add(merchant, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 30, 87, -1));

        amount.setBackground(new java.awt.Color(155, 155, 155));
        amount.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        amount.setForeground(new java.awt.Color(0, 125, 254));
        amount.setText("PHP 540.00");
        top.add(amount, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 60, -1, -1));

        main.add(top, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 380, 110));

        bottom.setBackground(new java.awt.Color(255, 255, 255));
        bottom.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PHLabel.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N
        PHLabel.setForeground(new java.awt.Color(155, 155, 155));
        PHLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        PHLabel.setText("+63");
        bottom.add(PHLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(77, 110, 40, 40));

        jLoginTitle.setFont(new java.awt.Font("Poppins Medium", 0, 20)); // NOI18N
        jLoginTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLoginTitle.setText("Login to pay with GCash");
        bottom.add(jLoginTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 380, 37));

        jPhoneNum.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jPhoneNum.setForeground(new java.awt.Color(155, 155, 155));
        jPhoneNum.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jPhoneNum.setText("Phone Number");
        bottom.add(jPhoneNum, new org.netbeans.lib.awtextra.AbsoluteConstraints(77, 90, 130, 20));

        jSeparator1.setForeground(new java.awt.Color(182, 182, 182));
        bottom.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(77, 150, 230, 10));

        phoneNum.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N
        phoneNum.setForeground(new java.awt.Color(155, 155, 155));
        phoneNum.setBorder(null);
        phoneNum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                phoneNumActionPerformed(evt);
            }
        });
        bottom.add(phoneNum, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 110, 160, 40));

        login.setBackground(new java.awt.Color(1, 89, 220));
        login.setForeground(new java.awt.Color(255, 255, 255));
        login.setText("NEXT");
        login.setFont(new java.awt.Font("Poppins", 1, 16)); // NOI18N
        login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginActionPerformed(evt);
            }
        });
        bottom.add(login, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, 300, 50));

        main.add(bottom, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 380, 290));

        Login.add(main, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 150, 380, 400));

        shadow.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        shadow.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/images/shadow.png"))); // NOI18N
        shadow.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        Login.add(shadow, new org.netbeans.lib.awtextra.AbsoluteConstraints(324, 147, 550, 430));

        gradientPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        gcash.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gcash.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/images/GCash_1.png"))); // NOI18N
        gcash.setToolTipText("");
        gradientPanel.add(gcash, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 50, 210, 60));

        back.setFont(new java.awt.Font("Roboto Medium", 0, 18)); // NOI18N
        back.setForeground(new java.awt.Color(255, 255, 255));
        back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/images/backwhite.png"))); // NOI18N
        back.setText("Back");
        back.setBorderPainted(false);
        back.setContentAreaFilled(false);
        back.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });
        gradientPanel.add(back, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 110, 40));

        Login.add(gradientPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1200, 220));

        PaymentMain.add(Login, "login");

        Payment.setBackground(new java.awt.Color(255, 255, 255));
        Payment.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        main1.setBackground(new java.awt.Color(255, 255, 255));
        main1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        merchantName.setBackground(new java.awt.Color(155, 155, 155));
        merchantName.setFont(new java.awt.Font("Poppins", 1, 16)); // NOI18N
        merchantName.setForeground(new java.awt.Color(0, 125, 254));
        merchantName.setText("UpBook");
        main1.add(merchantName, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 30, -1, -1));

        jLabel2.setFont(new java.awt.Font("Roboto Medium", 0, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(52, 52, 52));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("YOU ARE ABOUT TO PAY");
        main1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, 210, 37));

        jbalance.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jbalance.setForeground(new java.awt.Color(104, 104, 104));
        jbalance.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jbalance.setText("Available Balance");
        main1.add(jbalance, new org.netbeans.lib.awtextra.AbsoluteConstraints(152, 120, 180, 30));

        pay.setBackground(new java.awt.Color(1, 89, 220));
        pay.setForeground(new java.awt.Color(255, 255, 255));
        pay.setText("PAY PHP 540.00");
        pay.setFont(new java.awt.Font("Poppins", 1, 18)); // NOI18N
        pay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                payActionPerformed(evt);
            }
        });
        main1.add(pay, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 410, 290, 50));

        jSeparator2.setForeground(new java.awt.Color(182, 182, 182));
        main1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 265, 310, 10));

        initialAmount.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        initialAmount.setForeground(new java.awt.Color(155, 155, 155));
        initialAmount.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        initialAmount.setText("PHP 540.00");
        main1.add(initialAmount, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 240, 80, 20));

        gcashLabel.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        gcashLabel.setForeground(new java.awt.Color(83, 83, 83));
        gcashLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        gcashLabel.setText("GCash");
        main1.add(gcashLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, 90, 30));

        jLoginTitle5.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        jLoginTitle5.setForeground(new java.awt.Color(83, 83, 83));
        jLoginTitle5.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLoginTitle5.setText("PHP 5,494.00");
        main1.add(jLoginTitle5, new org.netbeans.lib.awtextra.AbsoluteConstraints(152, 100, 180, 30));
        main1.add(radio, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 100, -1, 30));

        jLabel1.setFont(new java.awt.Font("Roboto Medium", 0, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(52, 52, 52));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("PAY WITH");
        main1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 90, 37));

        note2.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        note2.setForeground(new java.awt.Color(144, 144, 144));
        note2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        note2.setText("correct before you proceed.");
        main1.add(note2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 380, 300, 20));

        totalAmount.setFont(new java.awt.Font("Poppins Medium", 0, 16)); // NOI18N
        totalAmount.setForeground(new java.awt.Color(55, 55, 55));
        totalAmount.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        totalAmount.setText("PHP 540.00");
        main1.add(totalAmount, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 280, 120, 20));

        jLabel3.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(55, 55, 55));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("Total");
        main1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 280, 50, 20));

        jLabel4.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(155, 155, 155));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("Amount");
        main1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 60, 20));

        note1.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        note1.setForeground(new java.awt.Color(144, 144, 144));
        note1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        note1.setText("Please review to ensure that the details are");
        main1.add(note1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 300, 20));

        Payment.add(main1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 150, 380, 500));

        shadow1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        shadow1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/images/shadow.png"))); // NOI18N
        shadow1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        Payment.add(shadow1, new org.netbeans.lib.awtextra.AbsoluteConstraints(324, 147, 550, 550));

        gradientPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        gcash1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gcash1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/images/GCash_1.png"))); // NOI18N
        gcash1.setToolTipText("");
        gradientPanel1.add(gcash1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 50, 210, 60));

        back1.setFont(new java.awt.Font("Roboto Medium", 0, 18)); // NOI18N
        back1.setForeground(new java.awt.Color(255, 255, 255));
        back1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/images/backwhite.png"))); // NOI18N
        back1.setText("Back");
        back1.setBorderPainted(false);
        back1.setContentAreaFilled(false);
        back1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        gradientPanel1.add(back1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 110, 40));

        Payment.add(gradientPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1200, 220));

        PaymentMain.add(Payment, "payment");

        Receipt.setBackground(new java.awt.Color(255, 255, 255));
        Receipt.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(1, 89, 223));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/images/download.png"))); // NOI18N
        jButton1.setText("Download Receipt");
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        Receipt.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 640, 230, 40));

        TransactionID.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        TransactionID.setForeground(new java.awt.Color(52, 52, 52));
        TransactionID.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TransactionID.setText("Reference No. 0112348840");
        Receipt.add(TransactionID, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 500, 250, 70));

        DateandTime.setFont(new java.awt.Font("Roboto", 0, 13)); // NOI18N
        DateandTime.setForeground(new java.awt.Color(26, 26, 26));
        DateandTime.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        DateandTime.setText("05/07/2025 07:18 PM");
        Receipt.add(DateandTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 245, 140, 50));

        receipt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        receipt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/images/receiptwithshadow.png"))); // NOI18N
        Receipt.add(receipt, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 20, 550, 750));

        gradientPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        checkoutSummary.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        checkoutSummary.setForeground(new java.awt.Color(255, 255, 255));
        checkoutSummary.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/images/backwhite.png"))); // NOI18N
        checkoutSummary.setText("Checkout Summary");
        checkoutSummary.setBorderPainted(false);
        checkoutSummary.setContentAreaFilled(false);
        checkoutSummary.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        checkoutSummary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkoutSummaryActionPerformed(evt);
            }
        });
        gradientPanel2.add(checkoutSummary, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 250, 40));

        Receipt.add(gradientPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1200, 220));

        PaymentMain.add(Receipt, "receipt");

        getContentPane().add(PaymentMain, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1200, 790));

        setSize(new java.awt.Dimension(1216, 798));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_backActionPerformed

    private void phoneNumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_phoneNumActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_phoneNumActionPerformed

    private void checkoutSummaryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkoutSummaryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkoutSummaryActionPerformed

    private void loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginActionPerformed
        // TODO add your handling code here:
        
        cardlayout.show(PaymentMain, "payment");
        PaymentMain.revalidate();
        PaymentMain.repaint();
    }//GEN-LAST:event_loginActionPerformed

    private void payActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_payActionPerformed
        // TODO add your handling code here:
        cardlayout.show(PaymentMain, "receipt");
        PaymentMain.revalidate();
        PaymentMain.repaint();
    }//GEN-LAST:event_payActionPerformed

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
            java.util.logging.Logger.getLogger(Payment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Payment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Payment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Payment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Payment().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel DateandTime;
    private javax.swing.JPanel Login;
    private javax.swing.JLabel PHLabel;
    private javax.swing.JPanel Payment;
    private javax.swing.JPanel PaymentMain;
    private javax.swing.JPanel Receipt;
    private javax.swing.JLabel TransactionID;
    private javax.swing.JLabel amount;
    private javax.swing.JButton back;
    private javax.swing.JButton back1;
    private javax.swing.JPanel bottom;
    private javax.swing.JButton checkoutSummary;
    private javax.swing.JLabel gcash;
    private javax.swing.JLabel gcash1;
    private javax.swing.JLabel gcashLabel;
    private CustomizedClasses.GradientPanel gradientPanel;
    private CustomizedClasses.GradientPanel gradientPanel1;
    private CustomizedClasses.GradientPanel gradientPanel2;
    private javax.swing.JLabel initialAmount;
    private javax.swing.JLabel jAmount;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLoginTitle;
    private javax.swing.JLabel jLoginTitle5;
    private javax.swing.JLabel jMerchant;
    private javax.swing.JLabel jPhoneNum;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel jbalance;
    private CustomizedClasses.CustomButton login;
    private javax.swing.JPanel main;
    private javax.swing.JPanel main1;
    private javax.swing.JLabel merchant;
    private javax.swing.JLabel merchantName;
    private javax.swing.JLabel note1;
    private javax.swing.JLabel note2;
    private CustomizedClasses.CustomButton pay;
    private javax.swing.JTextField phoneNum;
    private CustomizedClasses.RadioButtonCustom radio;
    private javax.swing.JLabel receipt;
    private javax.swing.JLabel shadow;
    private javax.swing.JLabel shadow1;
    private javax.swing.JPanel top;
    private javax.swing.JLabel totalAmount;
    // End of variables declaration//GEN-END:variables
}