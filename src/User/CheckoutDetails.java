/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package User;

import CustomizedClasses.RadioButtonCustom;
import Classes.BookDB;
import Classes.Checkout;
import Classes.CheckoutDB;
import Classes.DeliveryAddress;
import Classes.DeliveryDB;
import Classes.DeliveryPanels;
import Classes.Rental;
import Classes.RentalDB;
import Classes.TransactionDB;
import Classes.UserCart;
import Classes.UserCartDB;
import Classes.UserCartPanels;
import Classes.UserSession;
import CustomizedClasses.GlassPanePopup;
import CustomizedClasses.ScrollBarWin11UI;
import CustomizedClasses.ScrollPaneWin11;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JTextField;
import javax.swing.border.Border;

/**
 *
 * @author Aesthetics
 */
public class CheckoutDetails extends javax.swing.JPanel {

    Color mygrey = new Color(206, 206, 206);
    Color myblue = new Color(0, 145, 220);
    Color myred = new Color(255, 16, 16);

    private HashMap<String, UserCartPanels> selected = new HashMap<>();
    Cart cart;
    double amount, libraryfee, deliveryfee;
    int num, editAddressID;
    boolean edit;

    public ButtonGroup getAddressGroup() {
        return AddressGroup;
    }

    public void setAddressGroup(ButtonGroup Address) {
        this.AddressGroup = Address;
    }

    public void addAddressGroup(RadioButtonCustom btn) {
        this.AddressGroup.add(btn);
    }

    public void editAddress(int addressID) {
        choosePanel.setVisible(true);
        deliveryPanel.setVisible(true);
        deliveryselection.setVisible(false);

        DeliveryAddress del = ddb.getAddress(addressID);
        name.setText(del.getName());
        address.setText(del.getAddress());
        landmark.setText(del.getLandmark());
        notes.setText(del.getNotes());
        name.setForeground(Color.BLACK);
        address.setForeground(Color.BLACK);
        landmark.setForeground(Color.BLACK);
        notes.setForeground(Color.BLACK);
        this.editAddressID = del.getAddressID();
        this.edit = true;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
    TransactionDB tdb;
    BookDB bdb;
    CheckoutDB cdb;
    UserCartDB ucdb;
    RentalDB rdb;
    DeliveryDB ddb;
    double sum;
    DeliveryPanels dp;
    Executable executable = new Executable() {
        public void executeCurrentRentals() {}
        public void executeAllRentals() {}
        public void executeMyPenalties() {}
        public void executePenaltyHistory() {}
        public void executePendingCheckout() {}
        public void executeCheckoutHistory() {}
        public void executeTransaction() {}
        public void executeDeliveryPanel() {
            scrollpane.getVerticalScrollBar().setUI(new ScrollBarWin11UI());
            deliverypanels = ddb.getAllAddress(UserSession.getInstance().getUser().getUserID());
            setFrame(deliverypanels, CheckoutDetails.this.executable);
            int num = calculatePanelHeight(loadBooks(DeliveryPanelList, deliverypanels));
            if (num > 310) {
                scrollpane.setPreferredSize(new Dimension(440, 310));
                scrollpane.setMaximumSize(new Dimension(440, 310));
                scrollpane.setMinimumSize(new Dimension(440, 310));
                scrollpane.setSize(new Dimension(440, 310));
            } else {
                scrollpane.setPreferredSize(new Dimension(440, num));
                scrollpane.setMaximumSize(new Dimension(440, num));
                scrollpane.setMinimumSize(new Dimension(440, num));
                scrollpane.setSize(new Dimension(440, num));
            }
            DeliveryPanelList.setPreferredSize(new Dimension(440, num));
            DeliveryPanelList.setMinimumSize(new Dimension(440, num));
            DeliveryPanelList.setMaximumSize(new Dimension(440, num));
            DeliveryPanelList.setSize(new Dimension(440, num));
            scrollpane.setScrollSpeed(5);

            deliveryselection.repaint();
            deliveryselection.revalidate();
        }
    };
    private ArrayList<DeliveryPanels> deliverypanels = new ArrayList<>();

    public DeliveryPanels getDp() {
        return dp;
    }

    public void setDp(DeliveryPanels dp) {
        this.dp = dp;
    }

    public CheckoutDetails(double amount, int num) {
        initComponents();
        this.amount = amount;
        this.num = num;
        cdb = new CheckoutDB();
        ucdb = new UserCartDB();
        rdb = new RentalDB();
        ddb = new DeliveryDB();
        tdb = new TransactionDB();
        bdb = new BookDB();
        setOpaque(false);
        jScrollPane1 = new ScrollPaneWin11();
        jScrollPane3 = new ScrollPaneWin11();
        customer.setText(UserSession.getInstance().getUser().getFirstName() + " " + UserSession.getInstance().getUser().getLastName());
        email.setText(UserSession.getInstance().getUser().getEmail());
        phoneNum.setText(UserSession.getInstance().getUser().getPhoneNum());

        this.executable.executeDeliveryPanel();
        updateOrderDetails();
        deliveryPanel.setVisible(false);
        pickupPanel.setVisible(false);
        choosePanel.setVisible(false);
    }

    public ArrayList<DeliveryPanels> setFrame(ArrayList<DeliveryPanels> dp, Executable executable) {
        for (int i = 0; i < dp.size(); i++) {
            dp.get(i).setCd(CheckoutDetails.this);
            dp.get(i).addRadio(CheckoutDetails.this);
            dp.get(i).setExecutable(executable);
        }
        return dp;
    }

    private int loadBooks(Container container, ArrayList<DeliveryPanels> p) {
        int count = 0;
        container.removeAll();
        for (int i = 0; i < p.size(); i++) {
            container.add(p.get(i).getDelivery());
            count++;
        }
        return count;
    }

    public int calculatePanelHeight(int numBooks) {
        int height = numBooks * 110;
        return height;

    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 25, 25));
        g2.dispose();
        super.paintComponent(grphcs);
    }

    public void setCompoundBorder(JTextField textField, Color color) {
        Border insideBorder = BorderFactory.createEmptyBorder(5, 10, 5, 10);
        Border outsideBorder = BorderFactory.createLineBorder(color, 1, true);
        Border compoundBorder = BorderFactory.createCompoundBorder(outsideBorder, insideBorder);
        textField.setBorder(compoundBorder);
    }

    public void updateDeliveryPanel() {
        if (delivery.isSelected()) {
            deliveryPanel.setVisible(false);
            pickupPanel.setVisible(false);
            choosePanel.setVisible(true);
            deliveryPanel.setVisible(false);
            deliveryselection.setVisible(true);
        } else {
            deliveryPanel.setVisible(false);
            pickupPanel.setVisible(true);
            choosePanel.setVisible(false);
            deliveryPanel.setVisible(false);
            deliveryselection.setVisible(false);
        }
        updateOrderDetails();
    }

    public void updateOrderDetails() {
        if (delivery.isSelected()) {
            deliveryfee = 48.00;
        } else {
            deliveryfee = 00.00;
        }
        libraryfee = 25.00;
        this.sum = amount + libraryfee + deliveryfee;
        totalBooks.setText(Integer.toString(num) + " Books");
        libraryFee.setText(Double.toString(libraryfee) + "0");
        deliveryFee.setText(Double.toString(deliveryfee) + "0");
        initialAmount.setText(Double.toString(amount)+"0");
        totalAmount.setText(Double.toString(sum) + "0");

    }

    public void resetTextField() {
        name.setText("Eg. Home");
        address.setText("Street, City , Province");
        landmark.setText("Eg. Near barangay hall");
        notes.setText("Eg. Just call my number");
        name.setForeground(mygrey);
        address.setForeground(mygrey);
        landmark.setForeground(mygrey);
        notes.setForeground(mygrey);

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
        rent = new CustomizedClasses.RoundedBorderButton();
        PaymentMethod = new javax.swing.JLabel();
        UserInfo1 = new javax.swing.JLabel();
        jTotalBooks = new javax.swing.JLabel();
        jLibraryFee = new javax.swing.JLabel();
        jLibraryFee1 = new javax.swing.JLabel();
        jInitialAmount = new javax.swing.JLabel();
        initialAmount = new javax.swing.JLabel();
        libraryFee = new javax.swing.JLabel();
        totalBooks = new javax.swing.JLabel();
        deliveryFee = new javax.swing.JLabel();
        jEmail = new javax.swing.JLabel();
        email = new javax.swing.JLabel();
        phoneNum = new javax.swing.JLabel();
        jPhoneNum = new javax.swing.JLabel();
        jCust = new javax.swing.JLabel();
        UserInfo3 = new javax.swing.JLabel();
        customer = new javax.swing.JLabel();
        cash = new CustomizedClasses.RadioButtonCustom();
        gcash = new CustomizedClasses.RadioButtonCustom();
        cancel = new CustomizedClasses.RoundedBorderButton();
        jTotalAmount = new javax.swing.JLabel();
        totalAmount = new javax.swing.JLabel();
        jDeliveryDetails = new javax.swing.JLabel();
        pickup = new CustomizedClasses.RadioButtonCustom();
        delivery = new CustomizedClasses.RadioButtonCustom();
        choosePanel = new javax.swing.JPanel();
        deliveryselection = new javax.swing.JPanel();
        jDeliveryAddress3 = new javax.swing.JLabel();
        selectionPanel = new javax.swing.JPanel();
        scrollpane = new CustomizedClasses.ScrollPaneWin11();
        DeliveryPanelList = new javax.swing.JPanel();
        AddButton = new javax.swing.JPanel();
        add = new CustomizedClasses.RoundedBorderButton();
        deliveryPanel = new javax.swing.JPanel();
        name = new javax.swing.JTextField();
        address = new javax.swing.JTextField();
        landmark = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        notes = new javax.swing.JTextArea();
        save = new CustomizedClasses.RoundedBorderButton();
        back = new javax.swing.JButton();
        jDeliveryDetails1 = new javax.swing.JLabel();
        pickupPanel = new javax.swing.JPanel();
        jPickupDate = new javax.swing.JLabel();
        pickuplocation1 = new javax.swing.JTextField();
        jPickupLocation1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();

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
        add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 35, 30, 40));

        title1.setFont(new java.awt.Font("Poppins", 1, 24)); // NOI18N
        title1.setForeground(new java.awt.Color(0, 156, 252));
        title1.setText("Checkout");
        add(title1, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 35, 120, 40));

        title2.setFont(new java.awt.Font("Poppins", 1, 24)); // NOI18N
        title2.setText("Details");
        add(title2, new org.netbeans.lib.awtextra.AbsoluteConstraints(195, 35, 170, 40));

        jPanel1.setBackground(new java.awt.Color(252, 253, 255));
        jPanel1.setOpaque(false);
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        rent.setBackground(new java.awt.Color(0, 156, 220));
        rent.setForeground(new java.awt.Color(255, 255, 255));
        rent.setText("Rent");
        rent.setFont(new java.awt.Font("Roboto Medium", 0, 16)); // NOI18N
        rent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rentActionPerformed(evt);
            }
        });
        jPanel1.add(rent, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 580, 130, 50));

        PaymentMethod.setFont(new java.awt.Font("Poppins Medium", 0, 18)); // NOI18N
        PaymentMethod.setForeground(new java.awt.Color(32, 32, 32));
        PaymentMethod.setText("Payment Method");
        jPanel1.add(PaymentMethod, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 230, 240, 30));

        UserInfo1.setFont(new java.awt.Font("Poppins Medium", 0, 18)); // NOI18N
        UserInfo1.setForeground(new java.awt.Color(32, 32, 32));
        UserInfo1.setText("Order Details");
        jPanel1.add(UserInfo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 350, 240, 30));

        jTotalBooks.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jTotalBooks.setForeground(new java.awt.Color(104, 104, 104));
        jTotalBooks.setText("Total Books            :");
        jPanel1.add(jTotalBooks, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 380, -1, 30));

        jLibraryFee.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLibraryFee.setForeground(new java.awt.Color(104, 104, 104));
        jLibraryFee.setText("Library Fee             :");
        jPanel1.add(jLibraryFee, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 410, 130, 30));

        jLibraryFee1.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLibraryFee1.setForeground(new java.awt.Color(104, 104, 104));
        jLibraryFee1.setText("Delivery Fee          :");
        jPanel1.add(jLibraryFee1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 440, 130, 30));

        jInitialAmount.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jInitialAmount.setForeground(new java.awt.Color(104, 104, 104));
        jInitialAmount.setText("Initial Amount        :");
        jPanel1.add(jInitialAmount, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 470, 130, 30));

        initialAmount.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        initialAmount.setForeground(new java.awt.Color(104, 104, 104));
        jPanel1.add(initialAmount, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 470, 200, 30));

        libraryFee.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        libraryFee.setForeground(new java.awt.Color(104, 104, 104));
        jPanel1.add(libraryFee, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 410, 200, 30));

        totalBooks.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        totalBooks.setForeground(new java.awt.Color(104, 104, 104));
        jPanel1.add(totalBooks, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 380, 200, 30));

        deliveryFee.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        deliveryFee.setForeground(new java.awt.Color(104, 104, 104));
        jPanel1.add(deliveryFee, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 440, 200, 30));

        jEmail.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jEmail.setForeground(new java.awt.Color(104, 104, 104));
        jEmail.setText("Email                      :");
        jPanel1.add(jEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, 130, 30));

        email.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        email.setForeground(new java.awt.Color(104, 104, 104));
        jPanel1.add(email, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 170, 200, 30));

        phoneNum.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        phoneNum.setForeground(new java.awt.Color(104, 104, 104));
        jPanel1.add(phoneNum, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 140, 200, 30));

        jPhoneNum.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jPhoneNum.setForeground(new java.awt.Color(104, 104, 104));
        jPhoneNum.setText("Phone Number     :");
        jPanel1.add(jPhoneNum, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, 130, 30));

        jCust.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jCust.setForeground(new java.awt.Color(104, 104, 104));
        jCust.setText("Customer Name  :");
        jPanel1.add(jCust, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, 130, 30));

        UserInfo3.setFont(new java.awt.Font("Poppins Medium", 0, 18)); // NOI18N
        UserInfo3.setForeground(new java.awt.Color(32, 32, 32));
        UserInfo3.setText("Customer Information");
        jPanel1.add(UserInfo3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, 240, 30));

        customer.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        customer.setForeground(new java.awt.Color(104, 104, 104));
        jPanel1.add(customer, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 110, 200, 30));

        cash.setBackground(new java.awt.Color(0, 156, 220));
        PaymentGroup.add(cash);
        cash.setForeground(new java.awt.Color(82, 82, 82));
        cash.setText("Cash");
        cash.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        cash.setIconTextGap(6);
        cash.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cashActionPerformed(evt);
            }
        });
        jPanel1.add(cash, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 265, -1, -1));

        gcash.setBackground(new java.awt.Color(0, 156, 220));
        PaymentGroup.add(gcash);
        gcash.setForeground(new java.awt.Color(82, 82, 82));
        gcash.setText("GCash");
        gcash.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        gcash.setIconTextGap(6);
        gcash.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gcashActionPerformed(evt);
            }
        });
        jPanel1.add(gcash, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 295, -1, -1));

        cancel.setBackground(new java.awt.Color(252, 253, 255));
        cancel.setForeground(new java.awt.Color(0, 156, 220));
        cancel.setText("Cancel Checkout");
        cancel.setFont(new java.awt.Font("Roboto Medium", 0, 16)); // NOI18N
        cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelActionPerformed(evt);
            }
        });
        jPanel1.add(cancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 580, 180, 50));

        jTotalAmount.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jTotalAmount.setForeground(new java.awt.Color(104, 104, 104));
        jTotalAmount.setText("Total Amount        :");
        jPanel1.add(jTotalAmount, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 500, 130, 30));

        totalAmount.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        totalAmount.setForeground(new java.awt.Color(104, 104, 104));
        jPanel1.add(totalAmount, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 500, 200, 30));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(562, 0, 430, 670));

        jDeliveryDetails.setFont(new java.awt.Font("Poppins Medium", 0, 18)); // NOI18N
        jDeliveryDetails.setForeground(new java.awt.Color(32, 32, 32));
        jDeliveryDetails.setText("Delivery Method");
        add(jDeliveryDetails, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 90, 240, 30));

        pickup.setBackground(new java.awt.Color(0, 156, 220));
        DeliveryGroup.add(pickup);
        pickup.setForeground(new java.awt.Color(82, 82, 82));
        pickup.setText("Pick-up");
        pickup.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        pickup.setIconTextGap(6);
        pickup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pickupActionPerformed(evt);
            }
        });
        add(pickup, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 160, -1, -1));

        delivery.setBackground(new java.awt.Color(0, 156, 220));
        DeliveryGroup.add(delivery);
        delivery.setForeground(new java.awt.Color(82, 82, 82));
        delivery.setText("Delivery");
        delivery.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        delivery.setIconTextGap(6);
        delivery.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deliveryActionPerformed(evt);
            }
        });
        add(delivery, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 130, -1, -1));

        choosePanel.setBackground(new java.awt.Color(255, 255, 255));
        choosePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        deliveryselection.setBackground(new java.awt.Color(255, 255, 255));
        deliveryselection.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jDeliveryAddress3.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N
        jDeliveryAddress3.setForeground(new java.awt.Color(82, 82, 82));
        jDeliveryAddress3.setText("Delivery Addresses");
        deliveryselection.add(jDeliveryAddress3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 180, 30));

        selectionPanel.setBackground(new java.awt.Color(255, 255, 255));
        selectionPanel.setLayout(new java.awt.BorderLayout());

        scrollpane.setBackground(new java.awt.Color(255, 255, 255));
        scrollpane.setBorder(null);
        scrollpane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollpane.setViewportView(null);

        DeliveryPanelList.setBackground(new java.awt.Color(255, 255, 255));
        DeliveryPanelList.setLayout(new javax.swing.BoxLayout(DeliveryPanelList, javax.swing.BoxLayout.Y_AXIS));
        scrollpane.setViewportView(DeliveryPanelList);

        selectionPanel.add(scrollpane, java.awt.BorderLayout.PAGE_START);

        AddButton.setBackground(new java.awt.Color(255, 255, 255));
        AddButton.setPreferredSize(new java.awt.Dimension(440, 80));
        AddButton.setRequestFocusEnabled(false);
        AddButton.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        add.setBackground(new java.awt.Color(246, 252, 255));
        add.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(246, 252, 255), 1, true));
        add.setForeground(new java.awt.Color(0, 156, 220));
        add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/images/buttonaddblue.png"))); // NOI18N
        add.setText(" Add Address");
        add.setFont(new java.awt.Font("Roboto Medium", 0, 16)); // NOI18N
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });
        AddButton.add(add, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 420, 60));

        selectionPanel.add(AddButton, java.awt.BorderLayout.CENTER);

        deliveryselection.add(selectionPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 450, 400));

        choosePanel.add(deliveryselection, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 480, 440));

        deliveryPanel.setBackground(new java.awt.Color(255, 255, 255));
        deliveryPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        name.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        name.setForeground(new java.awt.Color(206, 206, 206));
        name.setText("Eg. Home");
        name.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(206, 206, 206), 1, true), javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10)), "Address Name", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Roboto", 0, 14), new java.awt.Color(52, 52, 52))); // NOI18N
        name.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                nameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                nameFocusLost(evt);
            }
        });
        deliveryPanel.add(name, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 420, 60));

        address.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        address.setForeground(new java.awt.Color(206, 206, 206));
        address.setText("Street, City , Province");
        address.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(206, 206, 206), 1, true), javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10)), "Address", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Roboto", 0, 14), new java.awt.Color(52, 52, 52))); // NOI18N
        address.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                addressFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                addressFocusLost(evt);
            }
        });
        deliveryPanel.add(address, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 420, 60));

        landmark.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        landmark.setForeground(new java.awt.Color(206, 206, 206));
        landmark.setText("Eg. Near barangay hall");
        landmark.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(206, 206, 206), 1, true), javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10)), "Near Landmark", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Roboto", 0, 14), new java.awt.Color(52, 52, 52))); // NOI18N
        landmark.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                landmarkFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                landmarkFocusLost(evt);
            }
        });
        deliveryPanel.add(landmark, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 420, 60));

        jScrollPane1.setBorder(null);
        jScrollPane1.setPreferredSize(new java.awt.Dimension(310, 30));

        notes.setColumns(20);
        notes.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        notes.setForeground(new java.awt.Color(206, 206, 206));
        notes.setRows(2);
        notes.setTabSize(5);
        notes.setText("Eg. Just call my number");
        notes.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(206, 206, 206), 1, true), javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10)), "Notes", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Roboto", 0, 14), new java.awt.Color(52, 52, 52))); // NOI18N
        notes.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                notesFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                notesFocusLost(evt);
            }
        });
        jScrollPane1.setViewportView(notes);

        deliveryPanel.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 420, 100));

        save.setBackground(new java.awt.Color(246, 252, 255));
        save.setForeground(new java.awt.Color(0, 156, 220));
        save.setText("Save Address");
        save.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });
        deliveryPanel.add(save, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 370, 180, 50));

        back.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        back.setForeground(new java.awt.Color(0, 156, 220));
        back.setText("Back to Selection");
        back.setBorderPainted(false);
        back.setContentAreaFilled(false);
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });
        deliveryPanel.add(back, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 370, 150, 50));

        jDeliveryDetails1.setFont(new java.awt.Font("Roboto Medium", 0, 18)); // NOI18N
        jDeliveryDetails1.setForeground(new java.awt.Color(32, 32, 32));
        jDeliveryDetails1.setText("Address Details");
        deliveryPanel.add(jDeliveryDetails1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 240, 50));

        choosePanel.add(deliveryPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 460, 430));

        add(choosePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 200, 480, 440));

        pickupPanel.setBackground(new java.awt.Color(255, 255, 255));
        pickupPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPickupDate.setFont(new java.awt.Font("Poppins", 1, 16)); // NOI18N
        jPickupDate.setForeground(new java.awt.Color(82, 82, 82));
        jPickupDate.setText("Note");
        pickupPanel.add(jPickupDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 140, 30));

        pickuplocation1.setEditable(false);
        pickuplocation1.setBackground(new java.awt.Color(248, 248, 248));
        pickuplocation1.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        pickuplocation1.setText("Library cityhall public");
        pickuplocation1.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(206, 206, 206), 1, true), javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        pickupPanel.add(pickuplocation1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 420, 50));

        jPickupLocation1.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N
        jPickupLocation1.setForeground(new java.awt.Color(82, 82, 82));
        jPickupLocation1.setText("Pickup Location");
        pickupPanel.add(jPickupLocation1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 140, 30));

        jScrollPane3.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
        jScrollPane3.setFocusable(false);

        jTextPane1.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jTextPane1.setText("You are given 5 days to pickup your books from our library. If books are still not picked up then this order will automatically be cancelled. Just show us screenshot of your receipt and checkout id.");
        jTextPane1.setToolTipText("");
        jScrollPane3.setViewportView(jTextPane1);

        pickupPanel.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 142, 470, 120));

        add(pickupPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 200, 480, 430));

        getAccessibleContext().setAccessibleParent(jPanel1);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        GlassPanePopup.closePopupLast();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void addressFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_addressFocusGained
        // TODO add your handling code here:
        if (address.getText().equals("Street, City , Province")) {
            address.setText("");
            address.setForeground(Color.BLACK);
        }
//        setCompoundBorder(address, myblue);
    }//GEN-LAST:event_addressFocusGained

    private void pickupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pickupActionPerformed
        // TODO add your handling code here:
        updateDeliveryPanel();
    }//GEN-LAST:event_pickupActionPerformed

    private void deliveryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deliveryActionPerformed
        // TODO add your handling code here:
        updateDeliveryPanel();

    }//GEN-LAST:event_deliveryActionPerformed

    private void addressFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_addressFocusLost
        // TODO add your handling code here:
        if (address.getText().isEmpty()) {
            address.setForeground(mygrey);
            address.setText("Street, City , Province");
        }
    }//GEN-LAST:event_addressFocusLost

    private void landmarkFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_landmarkFocusGained
        // TODO add your handling code here:
        if (landmark.getText().equals("Eg. Near barangay hall")) {
            landmark.setText("");
            landmark.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_landmarkFocusGained

    private void landmarkFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_landmarkFocusLost
        // TODO add your handling code here:
        if (landmark.getText().isEmpty()) {
            landmark.setForeground(mygrey);
            landmark.setText("Eg. Near barangay hall");
        }
    }//GEN-LAST:event_landmarkFocusLost

    private void notesFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_notesFocusGained
        // TODO add your handling code here:
        if (notes.getText().equals("Eg. Just call my number")) {
            notes.setText("");
            notes.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_notesFocusGained

    private void notesFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_notesFocusLost
        // TODO add your handling code here:

        if (notes.getText().isEmpty()) {
            notes.setForeground(mygrey);
            notes.setText("Eg. Just call my number");
        }
    }//GEN-LAST:event_notesFocusLost

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
        // TODO add your handling code here:
        if (edit) {
            ddb.updateDeliveryAddress(UserSession.getInstance().getUser().getUserID(), CheckoutDetails.this.editAddressID, name.getText(), address.getText(), landmark.getText(), notes.getText());
            this.edit = false;
        } else {
            ddb.createDeliveryAddress(UserSession.getInstance().getUser().getUserID(), name.getText(), address.getText(), landmark.getText(), notes.getText());
        }

        resetTextField();
        this.executable.executeDeliveryPanel();
        deliveryPanel.setVisible(false);
        deliveryselection.setVisible(true);
    }//GEN-LAST:event_saveActionPerformed

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        // TODO add your handling code here:
        this.edit = false;
        resetTextField();
        this.executable.executeDeliveryPanel();
        deliveryPanel.setVisible(false);
        deliveryselection.setVisible(true);
    }//GEN-LAST:event_backActionPerformed

    private void nameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nameFocusGained
        // TODO add your handling code here:Eg. Home

        if (name.getText().equals("Eg. Home")) {
            name.setText("");
            name.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_nameFocusGained

    private void nameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nameFocusLost
        // TODO add your handling code here:
        if (name.getText().isEmpty()) {
            name.setForeground(mygrey);
            name.setText("Eg. Home");
        }
    }//GEN-LAST:event_nameFocusLost

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
        // TODO add your handling code here:
        deliveryPanel.setVisible(true);
        deliveryselection.setVisible(false);
    }//GEN-LAST:event_addActionPerformed

    private void cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cancelActionPerformed

    private void gcashActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gcashActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_gcashActionPerformed

    private void cashActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cashActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cashActionPerformed

    private void rentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rentActionPerformed
        // TODO add your handling code here:
        //getting and setting up selected
        this.selected = cart.getSelected();
        String[] keys = this.selected.keySet().toArray(new String[0]);
        ArrayList<UserCartPanels> values = new ArrayList<>(this.selected.values());
        ArrayList<Rental> rentals = new ArrayList<>();
        List<String> checkoutid = new ArrayList<>();
        int addressid = 0;
        boolean flag = false;

        for (String id : checkoutid) {
            Checkout ck = cdb.getCheckout(id, UserSession.getInstance().getUser().getUserID());
            if (rdb.inActiveRental(UserSession.getInstance().getUser().getUserID(), ck.getBookID())) {
                flag = true;
                break;
            }
        }
        Timestamp current = Timestamp.from(Instant.now());
        Instant instant = current.toInstant().plus(Duration.ofHours(4));
        Timestamp deliveryTime = Timestamp.from(instant);
        if (delivery.isSelected()) {
            if (AddressGroup.getSelection() != null && AddressGroup.getSelection().isSelected()) {
                Enumeration<AbstractButton> buttons = AddressGroup.getElements();
                while (buttons.hasMoreElements()) {
                    AbstractButton button = buttons.nextElement();
                    if (button instanceof RadioButtonCustom radioButtonCustom) {
                        DeliveryPanels del = radioButtonCustom.getDeliverypanel();
                        if (del.getDelivery().getRadio().isSelected()) {
                            addressid = del.getAddressID();
                            break;
                        }
                    }
                }
            } else {
                GlassPanePopup.showPopup(new ErrorCheckout());
                flag = true;
            }
        }
        if (!flag) {
            if (DeliveryGroup.getSelection() != null && DeliveryGroup.getSelection().isSelected()) {
                if (PaymentGroup.getSelection() != null && PaymentGroup.getSelection().isSelected()) {
                    for (UserCart uc : values) {
                        String id = null;
                        System.out.println(id);
                        if (addressid != 0) {
                            id = cdb.createDeliveryCheckout( uc, addressid, deliveryfee, libraryfee, deliveryTime, current);
                        } else {
                            id = cdb.createPickupCheckout(uc, libraryfee, deliveryTime, current);
                        }
                        checkoutid.add(id);
                    }
                    System.out.print(checkoutid.size());
                    if (!checkoutid.isEmpty()) {
                        String pm = null;
                        if (cash.isSelected()) {
                            pm = "Cash";

                            String transactionID = tdb.createTransaction(UserSession.getInstance().getUser().getUserID(), pm, this.amount, this.amount, "Done");
                            cdb.setTransactionCheckout(transactionID, checkoutid, "Confirmed", UserSession.getInstance().getUser().getUserID());

                            //create rentals
                            rentals = cdb.getCheckouts(checkoutid, UserSession.getInstance().getUser().getUserID());
                            for (Rental rental : rentals) {
                                rdb.createRental(rental);
                                bdb.removeBookInventory(rental.getBookID());
                            }
                            GlassPanePopup.closePopupLast();
                            GlassPanePopup.showPopup(new CheckoutSummary(checkoutid, transactionID));
                        } else if (gcash.isSelected()) {
                            pm = "Gcash";

                            GlassPanePopup.showPopup(new PaymentLogin(pm, checkoutid, this.sum));
                        }
                        ucdb.removeSelectedCart(keys, UserSession.getInstance().getUser().getUserID()); //remove books from usercart
                        this.cart.removeAllSelected(); //clear all values in selected hashmap inside the frame cart
                        this.cart.execute(); //reload cart
                    } else {
                        System.out.println("No checkout id");
                    }
                } else {
                    GlassPanePopup.showPopup(new ErrorCheckout());
                }
            } else {
                GlassPanePopup.showPopup(new ErrorCheckout());
            }

        }


    }//GEN-LAST:event_rentActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel AddButton;
    private javax.swing.ButtonGroup AddressGroup;
    private javax.swing.ButtonGroup DeliveryGroup;
    private javax.swing.JPanel DeliveryPanelList;
    private javax.swing.ButtonGroup PaymentGroup;
    private javax.swing.JLabel PaymentMethod;
    private javax.swing.JLabel UserInfo1;
    private javax.swing.JLabel UserInfo3;
    private CustomizedClasses.RoundedBorderButton add;
    private javax.swing.JTextField address;
    private javax.swing.JButton back;
    private CustomizedClasses.RoundedBorderButton cancel;
    private CustomizedClasses.RadioButtonCustom cash;
    private javax.swing.JPanel choosePanel;
    private javax.swing.JLabel customer;
    private CustomizedClasses.RadioButtonCustom delivery;
    private javax.swing.JLabel deliveryFee;
    private javax.swing.JPanel deliveryPanel;
    private javax.swing.JPanel deliveryselection;
    private javax.swing.JLabel email;
    private CustomizedClasses.RadioButtonCustom gcash;
    private javax.swing.JLabel initialAmount;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jCust;
    private javax.swing.JLabel jDeliveryAddress3;
    private javax.swing.JLabel jDeliveryDetails;
    private javax.swing.JLabel jDeliveryDetails1;
    private javax.swing.JLabel jEmail;
    private javax.swing.JLabel jInitialAmount;
    private javax.swing.JLabel jLibraryFee;
    private javax.swing.JLabel jLibraryFee1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel jPhoneNum;
    private javax.swing.JLabel jPickupDate;
    private javax.swing.JLabel jPickupLocation1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JLabel jTotalAmount;
    private javax.swing.JLabel jTotalBooks;
    private javax.swing.JTextField landmark;
    private javax.swing.JLabel libraryFee;
    private javax.swing.JTextField name;
    private javax.swing.JTextArea notes;
    private javax.swing.JLabel phoneNum;
    private CustomizedClasses.RadioButtonCustom pickup;
    private javax.swing.JPanel pickupPanel;
    private javax.swing.JTextField pickuplocation1;
    private CustomizedClasses.RoundedBorderButton rent;
    private CustomizedClasses.RoundedBorderButton save;
    private CustomizedClasses.ScrollPaneWin11 scrollpane;
    private javax.swing.JPanel selectionPanel;
    private javax.swing.JLabel title1;
    private javax.swing.JLabel title2;
    private javax.swing.JLabel totalAmount;
    private javax.swing.JLabel totalBooks;
    // End of variables declaration//GEN-END:variables
}
