/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package User;

import Classes.Penalty;
import java.awt.Color;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
/**
 *
 * @author Aesthetics
 */
public class PenaltyHistoryPanel extends javax.swing.JPanel {

    /**
     * Creates new form PenaltyHistoryPanel
     */
    Penalty penalty;
    Timestamp timestamp;
    SimpleDateFormat dateformat = new SimpleDateFormat("MMMM dd, yyyy");
    String formattedDate;
    
    public PenaltyHistoryPanel(Penalty penalty) {
        initComponents();
        this.penalty = penalty;
        penaltyID.setText(penalty.getPenaltyID());
        rentalID.setText(penalty.getRentalID());
        transactionID.setText(penalty.getTransactionID());
        amount.setText("Php " + penalty.getPenaltyAmount() + "0");
        type.setText(penalty.getPenaltyType());
        timestamp = penalty.getPenaltyDate();
        formattedDate = dateformat.format(timestamp);
        date.setText(formattedDate);
        if(penalty.getStatus().equals("Paid")){
            status.setForeground(new Color(21,180,106));
        }else{
            status.setForeground(new Color(227,17,104));
        }
        status.setText(penalty.getStatus());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        rentalID = new javax.swing.JLabel();
        penaltyID = new javax.swing.JLabel();
        type = new javax.swing.JLabel();
        date = new javax.swing.JLabel();
        amount = new javax.swing.JLabel();
        status = new javax.swing.JLabel();
        transactionID = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 810, 80));

        rentalID.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        rentalID.setForeground(new java.awt.Color(52, 52, 52));
        rentalID.setText("Rental ID");
        add(rentalID, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 30, 70, 20));

        penaltyID.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        penaltyID.setForeground(new java.awt.Color(0, 156, 220));
        penaltyID.setText("Penalty ID");
        add(penaltyID, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 70, 20));

        type.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        type.setForeground(new java.awt.Color(52, 52, 52));
        type.setText("Penalty Type");
        add(type, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 20, 90, 40));

        date.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        date.setForeground(new java.awt.Color(52, 52, 52));
        date.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        date.setText("Penalty Date");
        add(date, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 20, 110, 40));

        amount.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        amount.setForeground(new java.awt.Color(52, 52, 52));
        amount.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        amount.setText("Penalty Amount");
        add(amount, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 20, 110, 40));

        status.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        status.setForeground(new java.awt.Color(52, 52, 52));
        status.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        status.setText("Status");
        add(status, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 20, 90, 40));

        transactionID.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        transactionID.setForeground(new java.awt.Color(52, 52, 52));
        transactionID.setText("Transaction ID");
        add(transactionID, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 30, 100, 20));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel amount;
    private javax.swing.JLabel date;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel penaltyID;
    private javax.swing.JLabel rentalID;
    private javax.swing.JLabel status;
    private javax.swing.JLabel transactionID;
    private javax.swing.JLabel type;
    // End of variables declaration//GEN-END:variables
}
