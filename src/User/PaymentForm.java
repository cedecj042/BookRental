/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package User;

import Classes.BookDB;
import Classes.CheckoutDB;
import Classes.Rental;
import Classes.RentalDB;
import Classes.UserSession;
import Classes.TransactionDB;
import CustomizedClasses.GlassPanePopup;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Aesthetics
 */
public class PaymentForm extends javax.swing.JPanel {

    String paymentMethod;
    List<String> checkoutID;
    ArrayList<Rental> rentals = new ArrayList<>();
    Double amount, amountpaid;
    TransactionDB tdb;
    CheckoutDB cdb;
    RentalDB rdb;
    BookDB bdb;
    Executable executable;

    public Executable getExecutable() {
        return executable;
    }

    public void setExecutable(Executable executable) {
        this.executable = executable;
    }
    
    public PaymentForm(String paymentMethod, List<String> checkoutID, Double amount) {
        initComponents();
        tdb = new TransactionDB();
        cdb = new CheckoutDB();
        rdb = new RentalDB();
        bdb = new BookDB();

        this.paymentMethod = paymentMethod;
        this.checkoutID = checkoutID;
        this.amount = amount;
        String textamount = mycash.getText();
        String numericAmount = textamount.replaceAll("[^\\d.]", "");
        this.amountpaid = Double.valueOf(numericAmount);

        initialAmount.setText(Double.toString(amount) + "0");
        totalAmount.setText(Double.toString(amount) + "0");
        pay.setText("PAY PHP" + Double.toString(amount) + "0");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        main1 = new javax.swing.JPanel();
        merchantName = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jbalance = new javax.swing.JLabel();
        pay = new CustomizedClasses.CustomButton();
        jSeparator2 = new javax.swing.JSeparator();
        initialAmount = new javax.swing.JLabel();
        gcashLabel = new javax.swing.JLabel();
        mycash = new javax.swing.JLabel();
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
        bg = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        pay.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
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

        mycash.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        mycash.setForeground(new java.awt.Color(83, 83, 83));
        mycash.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        mycash.setText("PHP 5,494.00");
        main1.add(mycash, new org.netbeans.lib.awtextra.AbsoluteConstraints(232, 100, 100, 30));

        radio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioActionPerformed(evt);
            }
        });
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

        add(main1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 150, 380, 500));

        shadow1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        shadow1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/images/shadow.png"))); // NOI18N
        shadow1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        add(shadow1, new org.netbeans.lib.awtextra.AbsoluteConstraints(324, 147, 550, 550));

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
        back1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                back1ActionPerformed(evt);
            }
        });
        gradientPanel1.add(back1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 110, 40));

        add(gradientPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1200, 220));
        add(bg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1200, 790));
    }// </editor-fold>//GEN-END:initComponents

    private void payActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_payActionPerformed

        //setting up transaction
        String transactionID = tdb.createTransaction( UserSession.getInstance().getUser().getUserID(), this.paymentMethod, this.amount, this.amountpaid, "Done");
        cdb.setTransactionCheckout(transactionID, this.checkoutID, "Confirmed", UserSession.getInstance().getUser().getUserID());

        //create rentals
        rentals = cdb.getCheckouts(checkoutID,UserSession.getInstance().getUser().getUserID());
        for(Rental rental: rentals){
            rdb.createRental(rental);
            cdb.updateCheckout(rental.getCheckoutID(),"Confirmed");
            bdb.removeBookInventory(rental.getBookID());
        }
        if(this.executable != null){
            this.executable.executeCurrentRentals();
        this.executable.executePendingCheckout();
        }
        GlassPanePopup.closePopupLast();
        GlassPanePopup.showPopup(new PaymentReceipt(checkoutID,transactionID));
    }//GEN-LAST:event_payActionPerformed

    private void radioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioActionPerformed
        // TODO add your handling code here:
        if (radio.isSelected()) {
            this.pay.setEnabled(true);
        } else {
            this.pay.setEnabled(false);
        }
    }//GEN-LAST:event_radioActionPerformed

    private void back1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_back1ActionPerformed
        // TODO add your handling code here:
        GlassPanePopup.closePopupAll();
    }//GEN-LAST:event_back1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton back1;
    private javax.swing.JLabel bg;
    private javax.swing.JLabel gcash1;
    private javax.swing.JLabel gcashLabel;
    private CustomizedClasses.GradientPanel gradientPanel1;
    private javax.swing.JLabel initialAmount;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel jbalance;
    private javax.swing.JPanel main1;
    private javax.swing.JLabel merchantName;
    private javax.swing.JLabel mycash;
    private javax.swing.JLabel note1;
    private javax.swing.JLabel note2;
    private CustomizedClasses.CustomButton pay;
    private CustomizedClasses.RadioButtonCustom radio;
    private javax.swing.JLabel shadow1;
    private javax.swing.JLabel totalAmount;
    // End of variables declaration//GEN-END:variables
}