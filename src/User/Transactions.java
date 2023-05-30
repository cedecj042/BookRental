/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package User;

import Classes.Checkout;
import Classes.CheckoutDB;
import Classes.Penalty;
import Classes.PenaltyDB;
import Classes.Rental;
import Classes.RentalDB;
import Classes.Transaction;
import Classes.TransactionDB;
import Classes.UserCartDB;
import Classes.UserSession;
import CustomizedClasses.GlassPanePopup;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.sql.Timestamp;
import static javax.swing.SwingConstants.CENTER;

/**
 *
 * @author Aesthetics
 */
public class Transactions extends javax.swing.JFrame {

    CardLayout cardLayout;
    int dropdowncounter = 0;
    RentalDB rdb;
    CheckoutDB cdb;
    TransactionDB tdb;
    PenaltyDB pdb;
    UserCartDB ucdb;

    ArrayList<CurrentRentedBooks> crbooks = new ArrayList<>();
    ArrayList<RentalBooks> rentalBooks = new ArrayList<>();
    ArrayList<PendingCheckout> pendingCheckout = new ArrayList<>();
    ArrayList<CheckoutHistoryPanel> checkoutHistory = new ArrayList<>();
    ArrayList<TransactionPanel> transacts = new ArrayList<>();
    ArrayList<PenaltyHistoryPanel> penaltyHistory = new ArrayList<>();

    Executable executable = new Executable() {
        public void executeCurrentRentals() {
            // your code here
            Transactions.this.crbooks = getCurrentRentals();
            if (Transactions.this.crbooks.isEmpty()) {
                NoDataFound ndf = new NoDataFound("Current Rental");
                addPanel(ndf, Transactions.this.CurrentRentedPanel, 810, 250);
            } else {
                int num = loadBooks(Transactions.this.CurrentRentedPanel, Transactions.this.crbooks);
                setPanelHeight(820, calculatePanelHeight(125, num), Transactions.this.CurrentRentedPanel);
            }
        }

        public void executeAllRentals() {
            Transactions.this.rentalBooks = getAllRentals();
            int num = loadBooks(Transactions.this.RentalPanel, Transactions.this.rentalBooks);
            setPanelHeight(810, calculatePanelHeight(80, num), Transactions.this.RentalPanel);

        }

        public void executePendingCheckout() {
            Transactions.this.pendingCheckout = getPendingCheckout();
            if (Transactions.this.pendingCheckout.isEmpty()) {
                NoDataFound ndf = new NoDataFound("Pending Checkout");
                addPanel(ndf, Transactions.this.PendingCheckoutPanel, 810, 250);
            } else {
                int num = loadBooks(Transactions.this.PendingCheckoutPanel, Transactions.this.pendingCheckout);
                setPanelHeight(810, calculatePanelHeight(90, num), Transactions.this.PendingCheckoutPanel);
            }
        }

        public void executeCheckoutHistory() {
            Transactions.this.checkoutHistory = getAllCheckout();
            int num = loadBooks(Transactions.this.CheckoutHistoryPanel, Transactions.this.checkoutHistory);
            setPanelHeight(810, calculatePanelHeight(80, num), Transactions.this.CheckoutHistoryPanel);

        }

        public void executeTransaction() {
            // your code here
            Transactions.this.transacts = getAllTransactions();
            int num = loadBooks(Transactions.this.TransactionHistoryPanel, Transactions.this.transacts);
            setPanelHeight(810, calculatePanelHeight(80, num), Transactions.this.TransactionHistoryPanel);
        }

        public void executePenaltyHistory() {
            Transactions.this.penaltyHistory = getAllPenalty();
            if (Transactions.this.penaltyHistory.isEmpty()) {
                penaltyHeader.setVisible(false);
                NoDataFound ndf = new NoDataFound("Penalties");
                ndf.setIcon(createImageIcon("../img/images/thumbsup.png"));
                addPanel(ndf, Transactions.this.PenaltyPanel, 810, 500);

            } else {
                penaltyHeader.setVisible(true);
                int num = loadBooks(Transactions.this.PenaltyPanel, Transactions.this.penaltyHistory);
                setPanelHeight(810, calculatePanelHeight(80, num), Transactions.this.PenaltyPanel);
            }

        }

        public void executeDeliveryPanel() {
        }
    };

    public Transactions() {
        initComponents();
        rdb = new RentalDB();
        cdb = new CheckoutDB();
        tdb = new TransactionDB();
        pdb = new PenaltyDB();
        ucdb = new UserCartDB();

        cardLayout = (CardLayout) jPanelBody.getLayout();
        scrollpane1.setScrollSpeed(5);
        GlassPanePopup.install(this);
        LoadMenu();
        executable.executeCurrentRentals();
        executable.executeAllRentals();
        executable.executePendingCheckout();
        executable.executeCheckoutHistory();
        executable.executePenaltyHistory();
        executable.executeTransaction();
        scrollpane2.setScrollSpeed(5);
        showNotif();

    }

    public void LoadMenu() {
        ImageIcon iconRental = createImageIcon("../img/images/iconRental.png");
        ImageIcon iconCheckout = createImageIcon("../img/images/iconCheckout.png");
        ImageIcon iconTransactions = createImageIcon("../img/images/iconTransactions.png");
        ImageIcon iconPenalty = createImageIcon("../img/images/iconPenalty.png");
        MenuItem menuRental = new MenuItem(iconRental, "Rentals", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    cardLayout.show(jPanelBody, "rental");
                    jPanelBody.repaint();
                    jPanelBody.revalidate();
                } catch (Exception ex) {
                    System.out.print("Panel can't be found.");
                }
            }
        });
        MenuItem menuCheckout = new MenuItem(iconCheckout, "Checkout", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    cardLayout.show(jPanelBody, "checkout");
                    jPanelBody.repaint();
                    jPanelBody.revalidate();
                } catch (Exception ex) {
                    System.out.print("Panel can't be found.");
                }
            }
        });
        MenuItem menuTransaction = new MenuItem(iconTransactions, "Transaction", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    cardLayout.show(jPanelBody, "transaction");
                    jPanelBody.repaint();
                    jPanelBody.revalidate();
                } catch (Exception ex) {
                    System.out.print("Panel can't be found.");
                }
            }
        });
        MenuItem menuPenalty = new MenuItem(iconPenalty, "Penalty", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    cardLayout.show(jPanelBody, "penalty");
                    jPanelBody.repaint();
                    jPanelBody.revalidate();
                } catch (Exception ex) {
                    System.out.print("Panel can't be found.");
                }
            }
        });
        addMenu(menuRental, menuPenalty, menuCheckout, menuTransaction);
    }

    private void addMenu(MenuItem... menu) {
        for (MenuItem menu1 : menu) {
            menus.add(menu1);
            ArrayList<MenuItem> subMenu = menu1.getSubMenu();
            for (MenuItem m : subMenu) {
                addMenu(m);
            }
        }
        menus.revalidate();
    }

    protected ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = this.getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    public void setPanelHeight(int width, int height, JPanel panel) {
        panel.setPreferredSize(new Dimension(width, height));
        panel.setSize(new Dimension(width, height));
        panel.setMinimumSize(new Dimension(width, height));
        panel.setMaximumSize(new Dimension(width, height));

    }

    public void addPanel(JPanel p, JPanel list, int width, int height) {
        list.removeAll();
        list.add(p);
        setPanelHeight(width, height, list);
    }

    public int calculatePanelHeight(int x, int y) {
        int height = x * y;
        return height;
    }

    public int loadBooks(Container container, ArrayList<? extends JPanel> panels) {
        int count = 0;
        container.removeAll();
        for (JPanel panel : panels) {
            container.add(panel);
            count++;
        }
        return count;
    }

    public ArrayList<CurrentRentedBooks> getCurrentRentals() {
        ArrayList<CurrentRentedBooks> crb = new ArrayList<>();
        ArrayList<Rental> rentals = rdb.getActiveRental(UserSession.getInstance().getUser().getUserID());
        for (Rental rental : rentals) {
            CurrentRentedBooks curr = new CurrentRentedBooks(rental, this.executable);
            crb.add(curr);
        }

        return crb;
    }

    public ArrayList<TransactionPanel> getAllTransactions() {
        ArrayList<TransactionPanel> tp = new ArrayList<>();
        ArrayList<Transaction> transaction = tdb.getAllTransaction(UserSession.getInstance().getUser().getUserID());
        for (int i = 0; i < transaction.size(); i++) {
            TransactionPanel t = new TransactionPanel(transaction.get(i));
            if (i % 2 == 0) {
                t.setBackground(new Color(252, 252, 252));
            } else {
                t.setBackground(new Color(255, 255, 255));
            }
            tp.add(t);
        }
        return tp;
    }

    public ArrayList<PenaltyHistoryPanel> getAllPenalty() {
        ArrayList<PenaltyHistoryPanel> php = new ArrayList<>();
        ArrayList<Penalty> penalties = pdb.getAllPenalty(UserSession.getInstance().getUser().getUserID());
        for (int i = 0; i < penalties.size(); i++) {
            PenaltyHistoryPanel curr = new PenaltyHistoryPanel(penalties.get(i));
            if (i % 2 == 0) {
                curr.setBackground(new Color(252, 252, 252));
            } else {
                curr.setBackground(new Color(255, 255, 255));
            }
            php.add(curr);
        }
        return php;
    }

    public ArrayList<RentalBooks> getAllRentals() {
        ArrayList<RentalBooks> crb = new ArrayList<>();
        ArrayList<Rental> rentals = rdb.getRentalHistory(UserSession.getInstance().getUser().getUserID());
        for (int i = 0; i < rentals.size(); i++) {
            RentalBooks curr = new RentalBooks(rentals.get(i));
            if (i % 2 == 0) {
                curr.setBackground(new Color(252, 252, 252));
            } else {
                curr.setBackground(new Color(255, 255, 255));
            }
            crb.add(curr);
        }
        return crb;
    }

    public ArrayList<PendingCheckout> getPendingCheckout() {
        Map<Timestamp, ArrayList<Checkout>> checkMap = cdb.getPendingCheckoutByDate(UserSession.getInstance().getUser().getUserID());
        ArrayList<PendingCheckout> pendingCheckouts = new ArrayList<>();
        int i = 0;
        for (Map.Entry<Timestamp, ArrayList<Checkout>> check : checkMap.entrySet()) {
            PendingCheckout pc = new PendingCheckout(check.getKey(), check.getValue(), this.executable);
            if (i % 2 == 0) {
                pc.setBackground(new Color(252, 252, 252));
            } else {
                pc.setBackground(new Color(255, 255, 255));
            }
            pendingCheckouts.add(pc);
            i++;
        }

        return pendingCheckouts;
    }

    public ArrayList<CheckoutHistoryPanel> getAllCheckout() {
        Map<Timestamp, ArrayList<Checkout>> checkMap = cdb.getAllCheckoutByDate(UserSession.getInstance().getUser().getUserID());
        ArrayList<CheckoutHistoryPanel> allCheckouts = new ArrayList<>();
        int i = 0;
        for (Map.Entry<Timestamp, ArrayList<Checkout>> check : checkMap.entrySet()) {
            CheckoutHistoryPanel pc = new CheckoutHistoryPanel(check.getKey(), check.getValue());
            if (i % 2 == 0) {
                pc.setBackground(new Color(252, 252, 252));
            } else {
                pc.setBackground(new Color(255, 255, 255));
            }
            allCheckouts.add(pc);
            i++;
        }

        return allCheckouts;
    }

    public void showNotif() {
        notiflabel.setText(String.valueOf(ucdb.countUserCart(UserSession.getInstance().getUser().getUserID())));
        notiflabel.setHorizontalTextPosition(CENTER);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Header = new javax.swing.JPanel();
        notiflabel = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLibrary = new javax.swing.JButton();
        jTransaction = new javax.swing.JButton();
        jCart = new javax.swing.JButton();
        jSideMenu = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        jDropdown = new javax.swing.JPanel();
        jLogout = new javax.swing.JButton();
        jProfile = new javax.swing.JButton();
        Body = new javax.swing.JPanel();
        jSplitPane1 = new javax.swing.JSplitPane();
        menuBody = new javax.swing.JPanel();
        scrollpane1 = new CustomizedClasses.ScrollPaneWin11();
        menus = new javax.swing.JPanel();
        jPanelBody = new javax.swing.JPanel();
        Rentals = new javax.swing.JPanel();
        scrollpane2 = new CustomizedClasses.ScrollPaneWin11();
        rental = new javax.swing.JPanel();
        CurrentRentedBooks = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        CurrentRentedPanel = new javax.swing.JPanel();
        RentalHistory = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        RentalPanel = new javax.swing.JPanel();
        jCheckoutID = new javax.swing.JLabel();
        jBookID = new javax.swing.JLabel();
        jRentalID = new javax.swing.JLabel();
        jStartDate = new javax.swing.JLabel();
        jEndDate = new javax.swing.JLabel();
        jRentStatus = new javax.swing.JLabel();
        bg1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        Penalty = new javax.swing.JPanel();
        scrollpane3 = new CustomizedClasses.ScrollPaneWin11();
        penalty = new javax.swing.JPanel();
        PenaltyHistory = new javax.swing.JPanel();
        jPenaltyHistory = new javax.swing.JLabel();
        PenaltyPanel = new javax.swing.JPanel();
        penaltyHeader = new javax.swing.JPanel();
        jCheckoutID1 = new javax.swing.JLabel();
        jBookID1 = new javax.swing.JLabel();
        jRentalID1 = new javax.swing.JLabel();
        jStartDate1 = new javax.swing.JLabel();
        jEndDate1 = new javax.swing.JLabel();
        jRentStatus1 = new javax.swing.JLabel();
        bg2 = new javax.swing.JLabel();
        Checkout = new javax.swing.JPanel();
        scrollpane5 = new CustomizedClasses.ScrollPaneWin11();
        checkout = new javax.swing.JPanel();
        PendingCheckout = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        PendingCheckoutPanel = new javax.swing.JPanel();
        CheckoutHistory = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        CheckoutHistoryPanel = new javax.swing.JPanel();
        jCheckoutDate = new javax.swing.JLabel();
        jTransactionID = new javax.swing.JLabel();
        jTotalBooks = new javax.swing.JLabel();
        jAmount = new javax.swing.JLabel();
        jLibraryFee = new javax.swing.JLabel();
        jStatus = new javax.swing.JLabel();
        bg4 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        Transactions = new javax.swing.JPanel();
        scrollpane4 = new CustomizedClasses.ScrollPaneWin11();
        transactions = new javax.swing.JPanel();
        TransactionPanel = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        TransactionHistoryPanel = new javax.swing.JPanel();
        jCheckoutID2 = new javax.swing.JLabel();
        jBookID2 = new javax.swing.JLabel();
        jRentalID2 = new javax.swing.JLabel();
        jStartDate2 = new javax.swing.JLabel();
        jEndDate2 = new javax.swing.JLabel();
        jRentStatus2 = new javax.swing.JLabel();
        bg3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Header.setBackground(new java.awt.Color(255, 255, 255));
        Header.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        notiflabel.setFont(new java.awt.Font("Poppins", 1, 16)); // NOI18N
        notiflabel.setForeground(new java.awt.Color(255, 255, 255));
        notiflabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        notiflabel.setText("1");
        notiflabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        notiflabel.setIconTextGap(0);
        Header.add(notiflabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(1007, 18, 30, 30));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/images/cartnotif.png"))); // NOI18N
        Header.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1007, 17, 40, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/images/homelogo.png"))); // NOI18N
        Header.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, 160, 90));

        jLibrary.setFont(new java.awt.Font("Poppins Medium", 0, 18)); // NOI18N
        jLibrary.setForeground(new java.awt.Color(30, 30, 30));
        jLibrary.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/images/lib-unactive.png"))); // NOI18N
        jLibrary.setText("Library");
        jLibrary.setContentAreaFilled(false);
        jLibrary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jLibraryActionPerformed(evt);
            }
        });
        Header.add(jLibrary, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 0, 160, 90));

        jTransaction.setFont(new java.awt.Font("Poppins Medium", 0, 18)); // NOI18N
        jTransaction.setForeground(new java.awt.Color(254, 156, 44));
        jTransaction.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/images/transact-active.png"))); // NOI18N
        jTransaction.setText("Transactions");
        jTransaction.setContentAreaFilled(false);
        jTransaction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTransactionActionPerformed(evt);
            }
        });
        Header.add(jTransaction, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 0, 180, 90));

        jCart.setFont(new java.awt.Font("Poppins Medium", 0, 18)); // NOI18N
        jCart.setForeground(new java.awt.Color(30, 30, 30));
        jCart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/images/cart-unactive.png"))); // NOI18N
        jCart.setText("Cart");
        jCart.setContentAreaFilled(false);
        jCart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCartActionPerformed(evt);
            }
        });
        Header.add(jCart, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 0, 120, 90));

        jSideMenu.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        jSideMenu.setForeground(new java.awt.Color(146, 146, 146));
        jSideMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/images/sidemenu.png"))); // NOI18N
        jSideMenu.setContentAreaFilled(false);
        jSideMenu.setIconTextGap(10);
        jSideMenu.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jSideMenuFocusLost(evt);
            }
        });
        jSideMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jSideMenuMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jSideMenuMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jSideMenuMousePressed(evt);
            }
        });
        jSideMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSideMenuActionPerformed(evt);
            }
        });
        Header.add(jSideMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 0, 150, 90));
        Header.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 1210, 100));

        getContentPane().add(Header, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1200, 90));

        jDropdown.setBackground(new java.awt.Color(255, 255, 255));
        jDropdown.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jDropdownMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jDropdownMouseExited(evt);
            }
        });
        jDropdown.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLogout.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLogout.setForeground(new java.awt.Color(72, 72, 72));
        jLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/images/logout.png"))); // NOI18N
        jLogout.setText("Logout");
        jLogout.setContentAreaFilled(false);
        jLogout.setFocusPainted(false);
        jLogout.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLogout.setIconTextGap(10);
        jLogout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLogoutMousePressed(evt);
            }
        });
        jLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jLogoutActionPerformed(evt);
            }
        });
        jDropdown.add(jLogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 120, 50));

        jProfile.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jProfile.setForeground(new java.awt.Color(72, 72, 72));
        jProfile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/images/profile.png"))); // NOI18N
        jProfile.setText("Profile");
        jProfile.setContentAreaFilled(false);
        jProfile.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jProfile.setIconTextGap(13);
        jProfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jProfileActionPerformed(evt);
            }
        });
        jDropdown.add(jProfile, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 120, 50));

        getContentPane().add(jDropdown, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 90, 170, 0));

        Body.setBackground(new java.awt.Color(255, 255, 255));
        Body.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jSplitPane1.setDividerLocation(300);
        jSplitPane1.setDividerSize(1);

        menuBody.setBackground(new java.awt.Color(255, 255, 255));

        scrollpane1.setBorder(null);
        scrollpane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollpane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        menus.setBackground(new java.awt.Color(252, 253, 255));
        menus.setLayout(new javax.swing.BoxLayout(menus, javax.swing.BoxLayout.PAGE_AXIS));
        scrollpane1.setViewportView(menus);

        org.jdesktop.layout.GroupLayout menuBodyLayout = new org.jdesktop.layout.GroupLayout(menuBody);
        menuBody.setLayout(menuBodyLayout);
        menuBodyLayout.setHorizontalGroup(
            menuBodyLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(scrollpane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        menuBodyLayout.setVerticalGroup(
            menuBodyLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(scrollpane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 680, Short.MAX_VALUE)
        );

        jSplitPane1.setLeftComponent(menuBody);

        jPanelBody.setLayout(new java.awt.CardLayout());

        Rentals.setBackground(new java.awt.Color(255, 255, 255));
        Rentals.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        scrollpane2.setBorder(null);
        scrollpane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        rental.setBackground(new java.awt.Color(249, 249, 249));
        rental.setLayout(new javax.swing.BoxLayout(rental, javax.swing.BoxLayout.Y_AXIS));

        CurrentRentedBooks.setBackground(new java.awt.Color(249, 249, 249));
        CurrentRentedBooks.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Poppins", 1, 24)); // NOI18N
        jLabel3.setText("My Rentals");
        CurrentRentedBooks.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, 300, 30));

        CurrentRentedPanel.setBackground(new java.awt.Color(249, 249, 249));
        CurrentRentedPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 1, 1));
        CurrentRentedBooks.add(CurrentRentedPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 820, -1));

        rental.add(CurrentRentedBooks);

        RentalHistory.setBackground(new java.awt.Color(249, 249, 249));
        RentalHistory.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Poppins", 1, 24)); // NOI18N
        jLabel4.setText("Rental History");
        RentalHistory.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, 300, 50));

        RentalPanel.setBackground(new java.awt.Color(252, 252, 252));
        RentalPanel.setLayout(new javax.swing.BoxLayout(RentalPanel, javax.swing.BoxLayout.Y_AXIS));
        RentalHistory.add(RentalPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, 810, -1));

        jCheckoutID.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jCheckoutID.setForeground(new java.awt.Color(52, 52, 52));
        jCheckoutID.setText("Checkout ID");
        RentalHistory.add(jCheckoutID, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 110, 110, 10));

        jBookID.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jBookID.setForeground(new java.awt.Color(52, 52, 52));
        jBookID.setText("Book ID");
        RentalHistory.add(jBookID, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 110, 110, 10));

        jRentalID.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jRentalID.setForeground(new java.awt.Color(52, 52, 52));
        jRentalID.setText("Rental ID");
        RentalHistory.add(jRentalID, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 110, 60, 10));

        jStartDate.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jStartDate.setForeground(new java.awt.Color(52, 52, 52));
        jStartDate.setText("Start Date");
        RentalHistory.add(jStartDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 110, 120, 10));

        jEndDate.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jEndDate.setForeground(new java.awt.Color(52, 52, 52));
        jEndDate.setText("End Date");
        RentalHistory.add(jEndDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 110, 120, 10));

        jRentStatus.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jRentStatus.setForeground(new java.awt.Color(52, 52, 52));
        jRentStatus.setText("Status");
        RentalHistory.add(jRentStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 110, 80, 10));

        bg1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bg1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/images/rentalboxsummary.png"))); // NOI18N
        bg1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bg1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                bg1MousePressed(evt);
            }
        });
        RentalHistory.add(bg1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, 810, 47));

        rental.add(RentalHistory);

        jPanel1.setBackground(new java.awt.Color(249, 249, 249));

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 1739, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 50, Short.MAX_VALUE)
        );

        rental.add(jPanel1);

        scrollpane2.setViewportView(rental);

        Rentals.add(scrollpane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 680));

        jPanelBody.add(Rentals, "rental");

        scrollpane3.setBorder(null);
        scrollpane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        penalty.setBackground(new java.awt.Color(252, 252, 252));
        penalty.setLayout(new javax.swing.BoxLayout(penalty, javax.swing.BoxLayout.Y_AXIS));

        PenaltyHistory.setBackground(new java.awt.Color(249, 249, 249));
        PenaltyHistory.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPenaltyHistory.setFont(new java.awt.Font("Poppins", 1, 24)); // NOI18N
        jPenaltyHistory.setText("Penalty History");
        PenaltyHistory.add(jPenaltyHistory, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, 300, 50));

        PenaltyPanel.setBackground(new java.awt.Color(249, 249, 249));
        PenaltyPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 1, 1));
        PenaltyHistory.add(PenaltyPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, 820, -1));

        penaltyHeader.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jCheckoutID1.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jCheckoutID1.setForeground(new java.awt.Color(52, 52, 52));
        jCheckoutID1.setText("Rental ID");
        penaltyHeader.add(jCheckoutID1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 20, 70, 10));

        jBookID1.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jBookID1.setForeground(new java.awt.Color(52, 52, 52));
        jBookID1.setText("Penalty Type");
        penaltyHeader.add(jBookID1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 10, 110, 30));

        jRentalID1.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jRentalID1.setForeground(new java.awt.Color(52, 52, 52));
        jRentalID1.setText("Penalty ID");
        penaltyHeader.add(jRentalID1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 70, 10));

        jStartDate1.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jStartDate1.setForeground(new java.awt.Color(52, 52, 52));
        jStartDate1.setText("Penalty Date");
        penaltyHeader.add(jStartDate1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 10, 120, 30));

        jEndDate1.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jEndDate1.setForeground(new java.awt.Color(52, 52, 52));
        jEndDate1.setText("Penalty Amount");
        penaltyHeader.add(jEndDate1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 10, 120, 30));

        jRentStatus1.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jRentStatus1.setForeground(new java.awt.Color(52, 52, 52));
        jRentStatus1.setText("Payment Status");
        penaltyHeader.add(jRentStatus1, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 10, 100, 30));

        bg2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bg2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/images/rentalboxsummary.png"))); // NOI18N
        bg2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bg2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                bg2MousePressed(evt);
            }
        });
        penaltyHeader.add(bg2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 810, 50));

        PenaltyHistory.add(penaltyHeader, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, 810, 48));

        penalty.add(PenaltyHistory);

        scrollpane3.setViewportView(penalty);

        org.jdesktop.layout.GroupLayout PenaltyLayout = new org.jdesktop.layout.GroupLayout(Penalty);
        Penalty.setLayout(PenaltyLayout);
        PenaltyLayout.setHorizontalGroup(
            PenaltyLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 900, Short.MAX_VALUE)
            .add(PenaltyLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(PenaltyLayout.createSequentialGroup()
                    .add(0, 0, Short.MAX_VALUE)
                    .add(scrollpane3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 900, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(0, 0, Short.MAX_VALUE)))
        );
        PenaltyLayout.setVerticalGroup(
            PenaltyLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 680, Short.MAX_VALUE)
            .add(PenaltyLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(PenaltyLayout.createSequentialGroup()
                    .add(0, 0, Short.MAX_VALUE)
                    .add(scrollpane3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 680, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(0, 0, Short.MAX_VALUE)))
        );

        jPanelBody.add(Penalty, "penalty");

        scrollpane5.setBorder(null);
        scrollpane5.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        checkout.setBackground(new java.awt.Color(249, 249, 249));
        checkout.setLayout(new javax.swing.BoxLayout(checkout, javax.swing.BoxLayout.Y_AXIS));

        PendingCheckout.setBackground(new java.awt.Color(249, 249, 249));
        PendingCheckout.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Poppins", 1, 24)); // NOI18N
        jLabel8.setText("Pending Checkout");
        PendingCheckout.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, 300, 30));

        PendingCheckoutPanel.setBackground(new java.awt.Color(249, 249, 249));
        PendingCheckoutPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 1, 1));
        PendingCheckout.add(PendingCheckoutPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 820, -1));

        checkout.add(PendingCheckout);

        CheckoutHistory.setBackground(new java.awt.Color(249, 249, 249));
        CheckoutHistory.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setFont(new java.awt.Font("Poppins", 1, 24)); // NOI18N
        jLabel9.setText("Checkout History");
        CheckoutHistory.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, 300, 60));

        CheckoutHistoryPanel.setBackground(new java.awt.Color(252, 252, 252));
        CheckoutHistoryPanel.setLayout(new javax.swing.BoxLayout(CheckoutHistoryPanel, javax.swing.BoxLayout.Y_AXIS));
        CheckoutHistory.add(CheckoutHistoryPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, 810, -1));

        jCheckoutDate.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jCheckoutDate.setForeground(new java.awt.Color(82, 82, 82));
        jCheckoutDate.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jCheckoutDate.setText("Checkout Date");
        CheckoutHistory.add(jCheckoutDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 110, 110, 10));

        jTransactionID.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jTransactionID.setForeground(new java.awt.Color(82, 82, 82));
        jTransactionID.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jTransactionID.setText("Transaction ID");
        CheckoutHistory.add(jTransactionID, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 100, 120, 30));

        jTotalBooks.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jTotalBooks.setForeground(new java.awt.Color(82, 82, 82));
        jTotalBooks.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jTotalBooks.setText("Total Books");
        CheckoutHistory.add(jTotalBooks, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 100, 90, 30));

        jAmount.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jAmount.setForeground(new java.awt.Color(82, 82, 82));
        jAmount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jAmount.setText("Amount");
        CheckoutHistory.add(jAmount, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 100, 110, 30));

        jLibraryFee.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLibraryFee.setForeground(new java.awt.Color(82, 82, 82));
        jLibraryFee.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLibraryFee.setText("Library Fee");
        CheckoutHistory.add(jLibraryFee, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 100, 90, 30));

        jStatus.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jStatus.setForeground(new java.awt.Color(82, 82, 82));
        jStatus.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jStatus.setText("Status");
        CheckoutHistory.add(jStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 100, 90, 30));

        bg4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bg4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/images/rentalboxsummary.png"))); // NOI18N
        bg4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bg4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                bg4MousePressed(evt);
            }
        });
        CheckoutHistory.add(bg4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, 810, 47));

        checkout.add(CheckoutHistory);

        jPanel4.setBackground(new java.awt.Color(249, 249, 249));

        org.jdesktop.layout.GroupLayout jPanel4Layout = new org.jdesktop.layout.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 1739, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 50, Short.MAX_VALUE)
        );

        checkout.add(jPanel4);

        scrollpane5.setViewportView(checkout);

        org.jdesktop.layout.GroupLayout CheckoutLayout = new org.jdesktop.layout.GroupLayout(Checkout);
        Checkout.setLayout(CheckoutLayout);
        CheckoutLayout.setHorizontalGroup(
            CheckoutLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 900, Short.MAX_VALUE)
            .add(CheckoutLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(CheckoutLayout.createSequentialGroup()
                    .add(0, 0, Short.MAX_VALUE)
                    .add(scrollpane5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 900, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(0, 0, Short.MAX_VALUE)))
        );
        CheckoutLayout.setVerticalGroup(
            CheckoutLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 680, Short.MAX_VALUE)
            .add(CheckoutLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(CheckoutLayout.createSequentialGroup()
                    .add(0, 0, Short.MAX_VALUE)
                    .add(scrollpane5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 680, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(0, 0, Short.MAX_VALUE)))
        );

        jPanelBody.add(Checkout, "checkout");

        Transactions.setBackground(new java.awt.Color(255, 255, 255));
        Transactions.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        scrollpane4.setBorder(null);
        scrollpane4.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        transactions.setBackground(new java.awt.Color(249, 249, 249));
        transactions.setLayout(new javax.swing.BoxLayout(transactions, javax.swing.BoxLayout.Y_AXIS));

        TransactionPanel.setBackground(new java.awt.Color(249, 249, 249));
        TransactionPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Poppins", 1, 24)); // NOI18N
        jLabel7.setText("Transaction History");
        TransactionPanel.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, 300, 60));

        TransactionHistoryPanel.setBackground(new java.awt.Color(252, 252, 252));
        TransactionHistoryPanel.setLayout(new javax.swing.BoxLayout(TransactionHistoryPanel, javax.swing.BoxLayout.Y_AXIS));
        TransactionPanel.add(TransactionHistoryPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, 810, -1));

        jCheckoutID2.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jCheckoutID2.setForeground(new java.awt.Color(52, 52, 52));
        jCheckoutID2.setText("Payment Method");
        TransactionPanel.add(jCheckoutID2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 110, 120, 10));

        jBookID2.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jBookID2.setForeground(new java.awt.Color(52, 52, 52));
        jBookID2.setText("Transaction Date");
        TransactionPanel.add(jBookID2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 110, 110, 10));

        jRentalID2.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jRentalID2.setForeground(new java.awt.Color(52, 52, 52));
        jRentalID2.setText("Transaction ID");
        TransactionPanel.add(jRentalID2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 110, 110, 10));

        jStartDate2.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jStartDate2.setForeground(new java.awt.Color(52, 52, 52));
        jStartDate2.setText("Amount to Pay");
        TransactionPanel.add(jStartDate2, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 100, 110, 30));

        jEndDate2.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jEndDate2.setForeground(new java.awt.Color(52, 52, 52));
        jEndDate2.setText("Amount Paid");
        TransactionPanel.add(jEndDate2, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 110, 110, 10));

        jRentStatus2.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jRentStatus2.setForeground(new java.awt.Color(52, 52, 52));
        jRentStatus2.setText("Status");
        TransactionPanel.add(jRentStatus2, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 110, 80, 10));

        bg3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bg3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/images/rentalboxsummary.png"))); // NOI18N
        bg3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bg3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                bg3MousePressed(evt);
            }
        });
        TransactionPanel.add(bg3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, 810, 47));

        transactions.add(TransactionPanel);

        jPanel3.setBackground(new java.awt.Color(249, 249, 249));

        org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 1739, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 50, Short.MAX_VALUE)
        );

        transactions.add(jPanel3);

        scrollpane4.setViewportView(transactions);

        Transactions.add(scrollpane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 680));

        jPanelBody.add(Transactions, "transaction");

        jSplitPane1.setRightComponent(jPanelBody);

        Body.add(jSplitPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1200, 680));

        getContentPane().add(Body, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 1200, 680));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLibraryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jLibraryActionPerformed
        // TODO add your handling code here:
        Home myhome = new Home();
        myhome.show();
        dispose();
    }//GEN-LAST:event_jLibraryActionPerformed

    private void jTransactionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTransactionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTransactionActionPerformed

    private void jCartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCartActionPerformed
        // TODO add your handling code here:
        Cart cartpage = new Cart();
        cartpage.show();
        dispose();
    }//GEN-LAST:event_jCartActionPerformed

    private void jSideMenuFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jSideMenuFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jSideMenuFocusLost

    private void jSideMenuMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSideMenuMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jSideMenuMouseEntered

    private void jSideMenuMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSideMenuMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jSideMenuMouseExited

    private void jSideMenuMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSideMenuMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jSideMenuMousePressed

    private void jSideMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSideMenuActionPerformed
        // TODO add your handling code here:
        dropdowncounter++;
        if (dropdowncounter % 2 != 0) {
            jDropdown.setSize(160, 130);
        } else {
            jDropdown.setSize(0, 0);
        }
    }//GEN-LAST:event_jSideMenuActionPerformed

    private void jLogoutMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLogoutMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jLogoutMousePressed

    private void jLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jLogoutActionPerformed
        UserSession session = UserSession.getInstance();
        session.clearSession();
        Login mylogin;
        mylogin = new Login();
        mylogin.show();
        dispose();
    }//GEN-LAST:event_jLogoutActionPerformed

    private void jProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jProfileActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jProfileActionPerformed

    private void jDropdownMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jDropdownMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jDropdownMouseEntered

    private void jDropdownMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jDropdownMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jDropdownMouseExited

    private void bg1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bg1MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_bg1MousePressed

    private void bg2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bg2MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_bg2MousePressed

    private void bg3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bg3MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_bg3MousePressed

    private void bg4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bg4MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_bg4MousePressed

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
            java.util.logging.Logger.getLogger(Transactions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Transactions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Transactions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Transactions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Transactions().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Body;
    private javax.swing.JPanel Checkout;
    private javax.swing.JPanel CheckoutHistory;
    private javax.swing.JPanel CheckoutHistoryPanel;
    private javax.swing.JPanel CurrentRentedBooks;
    private javax.swing.JPanel CurrentRentedPanel;
    private javax.swing.JPanel Header;
    private javax.swing.JPanel Penalty;
    private javax.swing.JPanel PenaltyHistory;
    private javax.swing.JPanel PenaltyPanel;
    private javax.swing.JPanel PendingCheckout;
    private javax.swing.JPanel PendingCheckoutPanel;
    private javax.swing.JPanel RentalHistory;
    private javax.swing.JPanel RentalPanel;
    private javax.swing.JPanel Rentals;
    private javax.swing.JPanel TransactionHistoryPanel;
    private javax.swing.JPanel TransactionPanel;
    private javax.swing.JPanel Transactions;
    private javax.swing.JLabel bg1;
    private javax.swing.JLabel bg2;
    private javax.swing.JLabel bg3;
    private javax.swing.JLabel bg4;
    private javax.swing.JPanel checkout;
    private javax.swing.JLabel jAmount;
    private javax.swing.JLabel jBookID;
    private javax.swing.JLabel jBookID1;
    private javax.swing.JLabel jBookID2;
    private javax.swing.JButton jCart;
    private javax.swing.JLabel jCheckoutDate;
    private javax.swing.JLabel jCheckoutID;
    private javax.swing.JLabel jCheckoutID1;
    private javax.swing.JLabel jCheckoutID2;
    private javax.swing.JPanel jDropdown;
    private javax.swing.JLabel jEndDate;
    private javax.swing.JLabel jEndDate1;
    private javax.swing.JLabel jEndDate2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JButton jLibrary;
    private javax.swing.JLabel jLibraryFee;
    private javax.swing.JButton jLogout;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanelBody;
    private javax.swing.JLabel jPenaltyHistory;
    private javax.swing.JButton jProfile;
    private javax.swing.JLabel jRentStatus;
    private javax.swing.JLabel jRentStatus1;
    private javax.swing.JLabel jRentStatus2;
    private javax.swing.JLabel jRentalID;
    private javax.swing.JLabel jRentalID1;
    private javax.swing.JLabel jRentalID2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JButton jSideMenu;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JLabel jStartDate;
    private javax.swing.JLabel jStartDate1;
    private javax.swing.JLabel jStartDate2;
    private javax.swing.JLabel jStatus;
    private javax.swing.JLabel jTotalBooks;
    private javax.swing.JButton jTransaction;
    private javax.swing.JLabel jTransactionID;
    private javax.swing.JPanel menuBody;
    private javax.swing.JPanel menus;
    private javax.swing.JLabel notiflabel;
    private javax.swing.JPanel penalty;
    private javax.swing.JPanel penaltyHeader;
    private javax.swing.JPanel rental;
    private CustomizedClasses.ScrollPaneWin11 scrollpane1;
    private CustomizedClasses.ScrollPaneWin11 scrollpane2;
    private CustomizedClasses.ScrollPaneWin11 scrollpane3;
    private CustomizedClasses.ScrollPaneWin11 scrollpane4;
    private CustomizedClasses.ScrollPaneWin11 scrollpane5;
    private javax.swing.JPanel transactions;
    // End of variables declaration//GEN-END:variables
}
