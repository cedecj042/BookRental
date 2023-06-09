/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Admin;

import Classes.Rental;
import java.text.SimpleDateFormat;

/**
 *
 * @author Aesthetics
 */
public class RentalPanel extends javax.swing.JPanel {

    SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy");
    String formattedDate;

    public RentalPanel(Rental rental) {
        initComponents();
        rentalID.setText(rental.getRentalID());
        userID.setText(rental.getUserID());
        bookID.setText(rental.getBookID());
        formattedDate = dateFormat.format(rental.getEndDate());
        endDate.setText(formattedDate);
        formattedDate = dateFormat.format(rental.getStartDate());
        startDate.setText(formattedDate);
        if (rental.getReturnDate() != null) {
            formattedDate = dateFormat.format(rental.getReturnDate());
            returnDate.setText(formattedDate);
        } else {
            returnDate.setText("---");
        }
        status.setText(rental.getRentStatus());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        rentalID = new javax.swing.JLabel();
        returnDate = new javax.swing.JLabel();
        status = new javax.swing.JLabel();
        userID = new javax.swing.JLabel();
        bookID = new javax.swing.JLabel();
        startDate = new javax.swing.JLabel();
        endDate = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        rentalID.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        rentalID.setForeground(new java.awt.Color(0, 156, 220));
        rentalID.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rentalID.setText("Rental ID");
        jPanel1.add(rentalID, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 80, 90));

        returnDate.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        returnDate.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        returnDate.setText("Return Date");
        jPanel1.add(returnDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 0, 140, 90));

        status.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        status.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        status.setText("Rental Status");
        jPanel1.add(status, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 0, 110, 90));

        userID.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        userID.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        userID.setText("User ID");
        jPanel1.add(userID, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 0, 90, 90));

        bookID.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        bookID.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        bookID.setText("Book ID");
        jPanel1.add(bookID, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 0, 90, 90));

        startDate.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        startDate.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        startDate.setText("December 13");
        jPanel1.add(startDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 0, 140, 90));

        endDate.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        endDate.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        endDate.setText("December 20");
        jPanel1.add(endDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 0, 130, 90));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 90));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bookID;
    private javax.swing.JLabel endDate;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel rentalID;
    private javax.swing.JLabel returnDate;
    private javax.swing.JLabel startDate;
    private javax.swing.JLabel status;
    private javax.swing.JLabel userID;
    // End of variables declaration//GEN-END:variables
}
