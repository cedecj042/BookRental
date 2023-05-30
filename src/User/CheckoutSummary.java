/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package User;

import Classes.Book;
import Classes.BookDB;
import Classes.Checkout;
import Classes.CheckoutDB;
import Classes.DeliveryAddress;
import Classes.DeliveryDB;
import Classes.DeliveryPanels;
import Classes.RentalDB;
import Classes.Transaction;
import Classes.TransactionDB;
import Classes.UserCartDB;
import Classes.UserCartPanels;
import Classes.UserSession;
import CustomizedClasses.GlassPanePopup;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.ImageIcon;

/**
 *
 * @author Aesthetics
 */
public class CheckoutSummary extends javax.swing.JPanel {

    CheckoutDB cdb;
    UserCartDB ucdb;
    RentalDB rdb;
    DeliveryDB ddb;
    TransactionDB tdb;
    BookDB bdb;

    double libraryfee, deliveryfee;
    String transactionID;
    List<String> checkoutID = new ArrayList<>();
    ArrayList<Checkout> checkouts = new ArrayList<>();

    Transaction transaction;
    int totalbooks = 0;
    double totalinitialamount;
    String deliveryMethod;
    String deliveryaddress;
    String deliverylandmark;
    private ArrayList<CheckoutBooks> checkoutBooksList = new ArrayList<>();

    public CheckoutSummary(List<String> checkoutID, String transactionId) {
        initComponents();
        setOpaque(false);

        this.transactionID = transactionId;
        this.checkoutID = checkoutID;

        //setting db
        cdb = new CheckoutDB();
        ucdb = new UserCartDB();
        rdb = new RentalDB();
        ddb = new DeliveryDB();
        tdb = new TransactionDB();
        bdb = new BookDB();

        //getting data
        for (String cID : checkoutID) {
            Checkout checkout = cdb.getCheckout(cID, UserSession.getInstance().getUser().getUserID());
            DeliveryAddress da = ddb.getAddress(checkout.getAddressID());
            checkouts.add(checkout);
            totalbooks++;
            totalinitialamount += checkout.getTotalAmount();
            libraryfee = checkout.getLibraryFee();
            deliveryfee = checkout.getDeliveryFee();
            if (checkout.getAddressID() >= 1) {
                deliveryMethod = "Delivery";
                deliveryaddress = da.getAddress();
                deliverylandmark = da.getLandmark();
            } else {
                deliveryMethod = "Pickup";
                deliveryaddress = "City Hall Lapu-Lapu City Cebu";
                deliverylandmark = "Library City Hall public";
            }
            Book bk = bdb.getBook(checkout.getBookID());
            CheckoutBooks cb = new CheckoutBooks(bk);
            checkoutBooksList.add(cb);

        }
        if (transactionID != null) {
            //setting up transaction
            transaction = tdb.getTransaction(transactionId);
            pmethod.setText(transaction.getPaymentMethod());
            amountpaid.setText(Double.toString(transaction.getAmountToPay()) + "0");
            status.setText("Status: " + transaction.getTransactionStatus());
            initialAmount.setText(Double.toString(totalinitialamount) + "0");
            totalAmount.setText(Double.toString(transaction.getAmountToPay()) + "0");
        }else {
            pmethod.setText("N/A");
            amountpaid.setText("N/A");
            status.setText("N/A");
            totalAmount.setText("N/A");
        }

        //setting up delivery address
        method.setText(deliveryMethod);
        address.setText(deliveryaddress);
        landmark.setText(deliverylandmark);

        
        //setting up order details 
        totalBooks.setText(Integer.toString(totalbooks));
        libraryFee.setText(Double.toString(libraryfee) + "0");
        deliveryFee.setText(Double.toString(deliveryfee) + "0");
        initialAmount.setText(Double.toString(totalinitialamount) + "0");
        

        //setting up close button
        ImageIcon icon = (ImageIcon) close.getIcon();
        Image originalImage = icon.getImage();
        int newWidth = 15;
        int newHeight = 15;
        Image scaledImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        close.setIcon(scaledIcon);

        bookslist.setPreferredSize(new Dimension(460, calculatePanelHeight(loadBooks(bookslist, this.checkoutBooksList))));

        // seeting up arrow
    }

    public int calculatePanelHeight(int numBooks) {
        int height = numBooks * 115;
        return height;

    }

    private int loadBooks(Container container, ArrayList<CheckoutBooks> p) {
        int count = 0;
        container.removeAll();
        for (int i = 0; i < p.size(); i++) {
            container.add(p.get(i));
            count++;
        }
        return count;
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PaymentGroup = new javax.swing.ButtonGroup();
        DeliveryGroup = new javax.swing.ButtonGroup();
        AddressGroup = new javax.swing.ButtonGroup();
        jButton1 = new javax.swing.JButton();
        title1 = new javax.swing.JLabel();
        title2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jTotalBooks = new javax.swing.JLabel();
        jLibraryFee = new javax.swing.JLabel();
        jLibraryFee1 = new javax.swing.JLabel();
        jinitialAmount = new javax.swing.JLabel();
        OrderTitle = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        totalAmount = new javax.swing.JLabel();
        close = new CustomizedClasses.RoundedBorderButton();
        PaymentTitle = new javax.swing.JLabel();
        jDeliveryMethod = new javax.swing.JLabel();
        jAddress = new javax.swing.JLabel();
        jLandmark = new javax.swing.JLabel();
        DeliveryTitle = new javax.swing.JLabel();
        pmethod = new javax.swing.JLabel();
        amountpaid = new javax.swing.JLabel();
        status = new javax.swing.JLabel();
        jStatus = new javax.swing.JLabel();
        jAmountPaid = new javax.swing.JLabel();
        jPaymentMethod = new javax.swing.JLabel();
        method = new javax.swing.JLabel();
        address = new javax.swing.JLabel();
        landmark = new javax.swing.JLabel();
        totalBooks = new javax.swing.JLabel();
        libraryFee = new javax.swing.JLabel();
        deliveryFee = new javax.swing.JLabel();
        initialAmount = new javax.swing.JLabel();
        jTotalAmount2 = new javax.swing.JLabel();
        scrollPaneWin111 = new CustomizedClasses.ScrollPaneWin11();
        jPanel2 = new javax.swing.JPanel();
        bookslist = new javax.swing.JPanel();
        DeliveryTitle1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/images/backsmall.png"))); // NOI18N
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 35, 30, 40));

        title1.setFont(new java.awt.Font("Poppins", 1, 24)); // NOI18N
        title1.setForeground(new java.awt.Color(0, 156, 252));
        title1.setText("Checkout");
        add(title1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 35, 120, 40));

        title2.setFont(new java.awt.Font("Poppins", 1, 24)); // NOI18N
        title2.setText("Summary");
        add(title2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 35, 170, 40));

        jPanel1.setBackground(new java.awt.Color(252, 253, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTotalBooks.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jTotalBooks.setForeground(new java.awt.Color(104, 104, 104));
        jTotalBooks.setText("Total Books            ");
        jPanel1.add(jTotalBooks, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 340, -1, 20));

        jLibraryFee.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLibraryFee.setForeground(new java.awt.Color(104, 104, 104));
        jLibraryFee.setText("Library Fee             ");
        jPanel1.add(jLibraryFee, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 365, 130, 20));

        jLibraryFee1.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLibraryFee1.setForeground(new java.awt.Color(104, 104, 104));
        jLibraryFee1.setText("Delivery Fee          ");
        jPanel1.add(jLibraryFee1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 390, 130, 20));

        jinitialAmount.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jinitialAmount.setForeground(new java.awt.Color(104, 104, 104));
        jinitialAmount.setText("Books Amount      ");
        jPanel1.add(jinitialAmount, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 415, 130, 20));

        OrderTitle.setFont(new java.awt.Font("Poppins Medium", 0, 16)); // NOI18N
        OrderTitle.setForeground(new java.awt.Color(32, 32, 32));
        OrderTitle.setText("Order Details");
        jPanel1.add(OrderTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 310, 240, 30));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 470, 340, 10));

        totalAmount.setFont(new java.awt.Font("Roboto Medium", 0, 16)); // NOI18N
        totalAmount.setForeground(new java.awt.Color(55, 55, 55));
        totalAmount.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        totalAmount.setText("Total Amount");
        jPanel1.add(totalAmount, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 480, 130, 30));

        close.setBackground(new java.awt.Color(252, 253, 255));
        close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/images/close.png"))); // NOI18N
        close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeActionPerformed(evt);
            }
        });
        jPanel1.add(close, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 20, 40, 40));

        PaymentTitle.setFont(new java.awt.Font("Poppins Medium", 0, 16)); // NOI18N
        PaymentTitle.setForeground(new java.awt.Color(32, 32, 32));
        PaymentTitle.setText("Payment Details");
        jPanel1.add(PaymentTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 190, 230, 20));

        jDeliveryMethod.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jDeliveryMethod.setForeground(new java.awt.Color(105, 105, 105));
        jDeliveryMethod.setText("Delivery Method");
        jPanel1.add(jDeliveryMethod, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 95, 150, 20));

        jAddress.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jAddress.setForeground(new java.awt.Color(105, 105, 105));
        jAddress.setText("Location");
        jPanel1.add(jAddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, 130, 20));

        jLandmark.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLandmark.setForeground(new java.awt.Color(105, 105, 105));
        jLandmark.setText("Landmark");
        jPanel1.add(jLandmark, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 145, 150, 20));

        DeliveryTitle.setFont(new java.awt.Font("Poppins Medium", 0, 16)); // NOI18N
        DeliveryTitle.setForeground(new java.awt.Color(32, 32, 32));
        DeliveryTitle.setText("Delivery Details");
        jPanel1.add(DeliveryTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, 230, 20));

        pmethod.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        pmethod.setForeground(new java.awt.Color(104, 104, 104));
        pmethod.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        pmethod.setText("Gcash");
        jPanel1.add(pmethod, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 215, 130, 20));

        amountpaid.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        amountpaid.setForeground(new java.awt.Color(104, 104, 104));
        amountpaid.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        amountpaid.setText("Amount Paid");
        jPanel1.add(amountpaid, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 240, 130, 20));

        status.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        status.setForeground(new java.awt.Color(104, 104, 104));
        status.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        status.setText("Status");
        jPanel1.add(status, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 265, 130, 20));

        jStatus.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jStatus.setForeground(new java.awt.Color(105, 105, 105));
        jStatus.setText("Status");
        jPanel1.add(jStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 265, 140, 20));

        jAmountPaid.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jAmountPaid.setForeground(new java.awt.Color(105, 105, 105));
        jAmountPaid.setText("Amount Paid");
        jPanel1.add(jAmountPaid, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 240, 140, 20));

        jPaymentMethod.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jPaymentMethod.setForeground(new java.awt.Color(105, 105, 105));
        jPaymentMethod.setText("Payment Method");
        jPanel1.add(jPaymentMethod, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 215, 140, 20));

        method.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        method.setForeground(new java.awt.Color(104, 104, 104));
        method.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        method.setText("Pickup");
        jPanel1.add(method, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 95, 150, 20));

        address.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        address.setForeground(new java.awt.Color(104, 104, 104));
        address.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        address.setText("Location");
        jPanel1.add(address, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 120, 220, 20));

        landmark.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        landmark.setForeground(new java.awt.Color(104, 104, 104));
        landmark.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        landmark.setText("Landmark:");
        jPanel1.add(landmark, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 145, 220, 20));

        totalBooks.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        totalBooks.setForeground(new java.awt.Color(104, 104, 104));
        totalBooks.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        totalBooks.setText("Total Books");
        jPanel1.add(totalBooks, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 340, 160, 20));

        libraryFee.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        libraryFee.setForeground(new java.awt.Color(104, 104, 104));
        libraryFee.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        libraryFee.setText("Library Fee");
        jPanel1.add(libraryFee, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 365, 160, 20));

        deliveryFee.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        deliveryFee.setForeground(new java.awt.Color(104, 104, 104));
        deliveryFee.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        deliveryFee.setText("Delivery Fee");
        jPanel1.add(deliveryFee, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 390, 160, 20));

        initialAmount.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        initialAmount.setForeground(new java.awt.Color(104, 104, 104));
        initialAmount.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        initialAmount.setText("Books Amount");
        jPanel1.add(initialAmount, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 415, 160, 20));

        jTotalAmount2.setFont(new java.awt.Font("Roboto Medium", 0, 16)); // NOI18N
        jTotalAmount2.setForeground(new java.awt.Color(55, 55, 55));
        jTotalAmount2.setText("Total Amount  ");
        jPanel1.add(jTotalAmount2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 480, 130, 30));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(562, 0, 430, 580));

        scrollPaneWin111.setBackground(new java.awt.Color(255, 255, 255));
        scrollPaneWin111.setBorder(null);
        scrollPaneWin111.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bookslist.setBackground(new java.awt.Color(255, 255, 255));
        java.awt.FlowLayout flowLayout1 = new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 0);
        flowLayout1.setAlignOnBaseline(true);
        bookslist.setLayout(flowLayout1);
        jPanel2.add(bookslist, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 460, 410));

        scrollPaneWin111.setViewportView(jPanel2);

        add(scrollPaneWin111, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, 460, 410));

        DeliveryTitle1.setFont(new java.awt.Font("Poppins Medium", 0, 16)); // NOI18N
        DeliveryTitle1.setForeground(new java.awt.Color(32, 32, 32));
        DeliveryTitle1.setText("Checkout Books");
        add(DeliveryTitle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 85, 230, -1));

        getAccessibleContext().setAccessibleParent(jPanel1);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        GlassPanePopup.closePopupLast();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void closeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeActionPerformed
        // TODO add your handling code here:
        GlassPanePopup.closePopupLast();
    }//GEN-LAST:event_closeActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup AddressGroup;
    private javax.swing.ButtonGroup DeliveryGroup;
    private javax.swing.JLabel DeliveryTitle;
    private javax.swing.JLabel DeliveryTitle1;
    private javax.swing.JLabel OrderTitle;
    private javax.swing.ButtonGroup PaymentGroup;
    private javax.swing.JLabel PaymentTitle;
    private javax.swing.JLabel address;
    private javax.swing.JLabel amountpaid;
    private javax.swing.JPanel bookslist;
    private CustomizedClasses.RoundedBorderButton close;
    private javax.swing.JLabel deliveryFee;
    private javax.swing.JLabel initialAmount;
    private javax.swing.JLabel jAddress;
    private javax.swing.JLabel jAmountPaid;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jDeliveryMethod;
    private javax.swing.JLabel jLandmark;
    private javax.swing.JLabel jLibraryFee;
    private javax.swing.JLabel jLibraryFee1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel jPaymentMethod;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel jStatus;
    private javax.swing.JLabel jTotalAmount2;
    private javax.swing.JLabel jTotalBooks;
    private javax.swing.JLabel jinitialAmount;
    private javax.swing.JLabel landmark;
    private javax.swing.JLabel libraryFee;
    private javax.swing.JLabel method;
    private javax.swing.JLabel pmethod;
    private CustomizedClasses.ScrollPaneWin11 scrollPaneWin111;
    private javax.swing.JLabel status;
    private javax.swing.JLabel title1;
    private javax.swing.JLabel title2;
    private javax.swing.JLabel totalAmount;
    private javax.swing.JLabel totalBooks;
    // End of variables declaration//GEN-END:variables
}
