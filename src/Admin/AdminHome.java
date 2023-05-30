/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Admin;

import Classes.AdminSession;
import Classes.BookDB;
import Classes.Genre;
import Classes.GenreDB;
import Classes.MainGenre;
import Classes.MainGenreDB;
import Classes.RentalDB;
import Classes.TransactionDB;
import Classes.User;
import Classes.UserDB;
import CustomizedClasses.GlassPanePopup;
import CustomizedClasses.MaterialTabbed;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Aesthetics
 */
public class AdminHome extends javax.swing.JFrame {

    int dropdowncounter = 0;

    Color lightblue = new Color(239, 244, 255);
    Color darkblue = new Color(50, 112, 241);
    Color black = new Color(42, 42, 42);

    public JPanel getBookBodyPanel() {
        return BookBodyPanel;
    }

    public void setBookBodyPanel(JPanel BookBodyPanel) {
        this.BookBodyPanel = BookBodyPanel;
    }

    UserDB udb;
    BookDB bdb;
    RentalDB rdb;
    TransactionDB tdb;
    GenreDB gdb;
    MainGenreDB mgdb;

    CardLayout cardlayout;
//    ArrayList<BookTabPanel> booktabpanels = new ArrayList<>();
    ArrayList<MainGenrePanel> maingenrePanels = new ArrayList<>();
    ArrayList<RentalTabPanels> rentalTabPanels = new ArrayList<>();
    ArrayList<TransactionTabPanels> transactionTabPanels = new ArrayList<>();
    ArrayList<GenrePanel> genrePanels = new ArrayList<>();
    ArrayList<UserPanel> userPanel = new ArrayList<>();
    List<String> months = new ArrayList<>();
    List<String> ryears = new ArrayList<>();
    List<String> tyears = new ArrayList<>();

    AdminExecutable executable = new AdminExecutable() {

        public void executeDashboardTotal() {

            double salesNum = tdb.getTotalSales();
            int bookNum = bdb.countTotalBook();
            int rentalNum = rdb.countTotalRental();
            AdminHome.this.saleNum.setText(Double.toString(salesNum) + "0");
            AdminHome.this.bookNum.setText(Integer.toString(bookNum));
            AdminHome.this.rentalNum.setText(Integer.toString(rentalNum));
        }

        public void executeSalesToday() {
        }

        public void executeUserList() {
            getAllUsers(AdminHome.this.userPanel);
            int num = loadPanels(AdminHome.this.UserPanelList, AdminHome.this.userPanel);
            setPanelHeight(800, calculatePanelHeight(70, num), AdminHome.this.UserPanelList);
        }

        public void executeBookPanels() {
            getAllMainTabPanels(AdminHome.this.maingenrePanels);
            addTabbedPane(AdminHome.this.maingenrePanels, AdminHome.this.BookTab);
        }

        public void executeRentalPanels() {
            String month = null;
            String year = null;
            Object selectedMonth = AdminHome.this.monthBox.getSelectedItem();
            Object selectedYear = AdminHome.this.yearBox.getSelectedItem();
            if (selectedMonth == null || AdminHome.this.monthBox.getSelectedIndex() < 1) {
                month = "All";
            } else {
                int monthNumber = months.indexOf(selectedMonth);
                String formattedMonth = (monthNumber < 10) ? String.format("%02d", monthNumber) : String.valueOf(monthNumber);
                month = formattedMonth;
            }
            if (selectedYear == null) {
                year = "All";
            } else {
                year = (String) selectedYear;
            }
            getAllRental(month, year, AdminHome.this.rentalTabPanels);
            addTabbedPane(AdminHome.this.rentalTabPanels, AdminHome.this.RentalTab);
        }

        public void executeTransactionPanels() {
            String month = null;
            String year = null;
            Object selectedMonth = AdminHome.this.monthBox1.getSelectedItem();
            Object selectedYear = AdminHome.this.yearBox1.getSelectedItem();
            if (selectedMonth == null || AdminHome.this.monthBox1.getSelectedIndex() < 1) {
                month = "All";
            } else {
                int monthNumber = months.indexOf(selectedMonth);
                String formattedMonth = (monthNumber < 10) ? String.format("%02d", monthNumber) : String.valueOf(monthNumber);
                month = formattedMonth;
            }
            if (selectedYear == null) {
                year = "All";
            } else {
                year = (String) selectedYear;
            }
            AdminHome.this.transactionTabPanels.clear();
            TransactionTabPanels transaction = new TransactionTabPanels("All Transaction", month, year);
            AdminHome.this.transactionTabPanels.add(transaction);
            addTabbedPane(AdminHome.this.transactionTabPanels, AdminHome.this.TransactionTab);
        }

        public void executeGenrePanels() {
            getAllGenre(AdminHome.this.genrePanels);
            int num = loadPanels(AdminHome.this.GenrePanels, AdminHome.this.genrePanels);
            setPanelHeight(800, calculatePanelHeight(90, num) + 160, AdminHome.this.GenrePanels);
            
        }
    };

    public AdminHome() {
        initComponents();
        cardlayout = (CardLayout) MenuBody.getLayout();
        cardlayout.show(MenuBody, "dashboard");
        udb = new UserDB();
        bdb = new BookDB();
        rdb = new RentalDB();
        tdb = new TransactionDB();
        gdb = new GenreDB();
        mgdb = new MainGenreDB();
        GlassPanePopup.install(this);
        LoadMenu();
        setMonth(this.months);
        setUpTab();
        this.executable.executeTransactionPanels();
        this.executable.executeRentalPanels();
        this.executable.executeDashboardTotal();
        this.executable.executeUserList();
        this.executable.executeGenrePanels();
        TransactionTab.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Component selectedComponent = RentalTab.getSelectedComponent();
                if (selectedComponent != null) { // Check if a valid component is selected
                    Dimension size = selectedComponent.getPreferredSize();
                    int width = size.width;
                    int height = size.height;
                    setPanelHeight(width, height + 160, TransactionBody);
                }
            }
        });
        RentalTab.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Component selectedComponent = RentalTab.getSelectedComponent();
                if (selectedComponent != null) { // Check if a valid component is selected
                    Dimension size = selectedComponent.getPreferredSize();
                    int width = size.width;
                    int height = size.height;
                    setPanelHeight(width, height + 160, RentalBody);
                }
            }
        });

        BookTab.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Component selectedComponent = BookTab.getSelectedComponent();
                if (selectedComponent != null) { // Check if a valid component is selected
                    Dimension size = selectedComponent.getPreferredSize();
                    int width = size.width;
                    int height = size.height;
                    setPanelHeight(width, height + 160, BookBodyPanel);
                }
            }
        });
        this.executable.executeBookPanels();
    }

    public void LoadMenu() {
        ImageIcon inactiveDash = createImageIcon("../img/images/genreInactive.png");
        ImageIcon inactiveUser = createImageIcon("../img/images/userInactive.png");
        ImageIcon inactiveBook = createImageIcon("../img/images/bookInactive.png");
        ImageIcon inactiveRental = createImageIcon("../img/images/rentalInactive.png");
        ImageIcon inactiveTransactions = createImageIcon("../img/images/transactionsInactive.png");
        ImageIcon inactiveGenre = createImageIcon("../img/images/inactiveGenre.png");

        ImageIcon activeDash = createImageIcon("../img/images/genreActive.png");
        ImageIcon activeUser = createImageIcon("../img/images/userActive.png");
        ImageIcon activeBook = createImageIcon("../img/images/bookActive.png");
        ImageIcon activeRental = createImageIcon("../img/images/rentalActive.png");
        ImageIcon activeTransactions = createImageIcon("../img/images/transactionsActive.png");
        ImageIcon activeGenre = createImageIcon("../img/images/activeGenre.png");

        AdminMenu menuDashboard = new AdminMenu(inactiveDash, "Dashboard", null);
        menuDashboard.setClicked(lightblue, darkblue, activeDash);
        AdminMenu menuUser = new AdminMenu(inactiveUser, "User", null);
        AdminMenu menuBooks = new AdminMenu(inactiveBook, "Books", null);
        AdminMenu menuGenre = new AdminMenu(inactiveGenre, "Genre", null);
        AdminMenu menuRentals = new AdminMenu(inactiveRental, "Rentals", null);
        AdminMenu menuTransaction = new AdminMenu(inactiveTransactions, "Transaction", null);

        menuDashboard.setAct(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    menuDashboard.setClicked(lightblue, darkblue, activeDash);
                    menuUser.setDefault(black, inactiveUser);
                    menuBooks.setDefault(black, inactiveBook);
                    menuGenre.setDefault(black, inactiveGenre);
                    menuRentals.setDefault(black, inactiveRental);
                    menuTransaction.setDefault(black, inactiveTransactions);
                    cardlayout.show(MenuBody, "dashboard");
                    MenuBody.repaint();
                    MenuBody.revalidate();
                } catch (Exception ex) {
                    System.out.print("Panel can't be found.");
                }
            }
        });
        menuUser.setAct(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    menuDashboard.setDefault(black, inactiveDash);
                    menuUser.setClicked(lightblue, darkblue, activeUser);
                    menuBooks.setDefault(black, inactiveBook);
                    menuGenre.setDefault(black, inactiveGenre);
                    menuRentals.setDefault(black, inactiveRental);
                    menuTransaction.setDefault(black, inactiveTransactions);
                    cardlayout.show(MenuBody, "user");
                    MenuBody.repaint();
                    MenuBody.revalidate();
                } catch (Exception ex) {
                    System.out.print("Panel can't be found.");
                }
            }
        });
        menuBooks.setAct(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    menuDashboard.setDefault(black, inactiveDash);
                    menuUser.setDefault(black, inactiveUser);
                    menuBooks.setClicked(lightblue, darkblue, activeBook);
                    menuGenre.setDefault(black, inactiveGenre);
                    menuRentals.setDefault(black, inactiveRental);
                    menuTransaction.setDefault(black, inactiveTransactions);
                    cardlayout.show(MenuBody, "book");
                    MenuBody.repaint();
                    MenuBody.revalidate();
                } catch (Exception ex) {
                    System.out.print("Panel can't be found.");
                }
            }
        });
        menuGenre.setAct(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    menuDashboard.setDefault(black, inactiveDash);
                    menuUser.setDefault(black, inactiveUser);
                    menuBooks.setDefault(black, inactiveBook);
                    menuGenre.setClicked(lightblue, darkblue, activeGenre);
                    menuRentals.setDefault(black, inactiveRental);
                    menuTransaction.setDefault(black, inactiveTransactions);
                    cardlayout.show(MenuBody, "genre");
                    MenuBody.repaint();
                    MenuBody.revalidate();
                } catch (Exception ex) {
                    System.out.print("Panel can't be found.");
                }
            }
        });
        menuRentals.setAct(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    menuDashboard.setDefault(black, inactiveDash);
                    menuUser.setDefault(black, inactiveUser);
                    menuBooks.setDefault(black, inactiveBook);
                    menuGenre.setDefault(black, inactiveGenre);
                    menuRentals.setClicked(lightblue, darkblue, activeRental);
                    menuTransaction.setDefault(black, inactiveTransactions);
                    cardlayout.show(MenuBody, "rental");
                    MenuBody.repaint();
                    MenuBody.revalidate();
                } catch (Exception ex) {
                    System.out.print("Panel can't be found.");
                }
            }
        });
        menuTransaction.setAct(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    menuDashboard.setDefault(black, inactiveDash);
                    menuUser.setDefault(black, inactiveUser);
                    menuBooks.setDefault(black, inactiveBook);
                    menuGenre.setDefault(black, inactiveGenre);
                    menuRentals.setDefault(black, inactiveRental);
                    menuTransaction.setClicked(lightblue, darkblue, activeTransactions);
                    cardlayout.show(MenuBody, "transaction");
                    MenuBody.repaint();
                    MenuBody.revalidate();
                } catch (Exception ex) {
                    System.out.print("Panel can't be found.");
                }
            }
        });
        addMenu(menuDashboard, menuUser, menuBooks,menuGenre, menuRentals, menuTransaction);
    }

    public void setUpTab() {
        this.ryears = rdb.getRentalYears();
        this.ryears.add(0, "All");
        this.monthBox.setModel(new DefaultComboBoxModel<String>(this.months.toArray(new String[0])));
        this.yearBox.setModel(new DefaultComboBoxModel<String>(this.ryears.toArray(new String[0])));
        this.tyears = rdb.getRentalYears();
        this.tyears.add(0, "All");
        this.monthBox1.setModel(new DefaultComboBoxModel<String>(this.months.toArray(new String[0])));
        this.yearBox1.setModel(new DefaultComboBoxModel<String>(this.tyears.toArray(new String[0])));
        this.monthBox.setSelectedIndex(-1);
        this.yearBox.setSelectedIndex(-1);
        this.monthBox1.setSelectedIndex(-1);
        this.yearBox1.setSelectedIndex(-1);
    }

    public void addMenu(AdminMenu... menu) {
        for (AdminMenu menu1 : menu) {
            SideMenu.add(menu1);
        }
        SideMenu.revalidate();
    }

    public void setMonth(List<String> months) {
        months.add(0, "All");
        months.add("January");
        months.add("February");
        months.add("March");
        months.add("April");
        months.add("May");
        months.add("June");
        months.add("July");
        months.add("August");
        months.add("September");
        months.add("October");
        months.add("November");
        months.add("December");
    }

    public void addTabbedPane(ArrayList<? extends Component> component, MaterialTabbed tab) {
        tab.removeAll();
        for (Component c : component) {
            tab.add(c);
        }
        tab.repaint();
        tab.revalidate();
    }

    public void getAllMainTabPanels(ArrayList<MainGenrePanel> maingenrepanels) {
        maingenrepanels.clear();
        ArrayList<MainGenre> maingenres = mgdb.getAllGenre();
        MainGenrePanel books = new MainGenrePanel(this.executable, AdminHome.this);
        maingenrepanels.add(books);
        for (MainGenre maingenre : maingenres) {
            MainGenrePanel mgp = new MainGenrePanel(maingenre, this.executable, AdminHome.this);
            maingenrepanels.add(mgp);
        }
    }

    public void getAllUsers(ArrayList<UserPanel> userPanels) {
        ArrayList<User> users = udb.getAllUser();
        for (int i = 0; i < users.size(); i++) {
            UserPanel up = new UserPanel(users.get(i));
            if (i % 2 == 0) {
                up.setBackground(new Color(252, 252, 252));
            } else {
                up.setBackground(new Color(255, 255, 255));
            }
            userPanels.add(up);
        }
    }

    public void getAllGenre(ArrayList<GenrePanel> genrePanels) {
        ArrayList<Genre> genres = gdb.getAllGenre();
        for (int i = 0; i < genres.size(); i++) {
            GenrePanel gp = new GenrePanel(genres.get(i));
            if (i % 2 == 0) {
                gp.setBackground(new Color(252, 252, 252));
            } else {
                gp.setBackground(new Color(255, 255, 255));
            }
            genrePanels.add(gp);
        }
    }

    public void getAllRental(String month, String year, ArrayList<RentalTabPanels> rtp) {
        rtp.clear();
        List<String> tabs = rdb.getRentalStatus();
        RentalTabPanels rental = new RentalTabPanels("All Rentals", month, year);
        rtp.add(rental);
        for (String tab : tabs) {
            rental = new RentalTabPanels(tab, month, year);
            rtp.add(rental);
        }
    }

    public void getAllTransaction(String month, String year, ArrayList<TransactionTabPanels> ttp) {
        ttp.clear();
        List<String> tabs = tdb.getTransactionStatus();
        TransactionTabPanels transaction = new TransactionTabPanels("All Transaction", month, year);
        ttp.add(transaction);
        for (String tab : tabs) {
            transaction = new TransactionTabPanels(tab, month, year);
            ttp.add(transaction);
        }
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

    public int calculatePanelHeight(int x, int y) {
        int height = x * y;
        return height;
    }

    public int loadPanels(Container container, ArrayList<? extends JPanel> panels) {
        int count = 0;
        container.removeAll();
        for (JPanel panel : panels) {
            container.add(panel);
            count++;
        }
        return count;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDropdown = new javax.swing.JPanel();
        jLogout = new javax.swing.JButton();
        jProfile = new javax.swing.JButton();
        jSplitPane1 = new javax.swing.JSplitPane();
        SideNav = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        SideMenu = new javax.swing.JPanel();
        MainBody = new javax.swing.JPanel();
        TopNav = new javax.swing.JPanel();
        jSideMenu = new javax.swing.JButton();
        comboBox1 = new CustomizedClasses.ComboBox();
        MenuBody = new javax.swing.JPanel();
        Dashboard = new javax.swing.JPanel();
        DashboardScroll = new CustomizedClasses.ScrollPaneWin11();
        Body = new javax.swing.JPanel();
        DashboardTotal = new javax.swing.JPanel();
        jDashBoard = new javax.swing.JLabel();
        TotalRentalPanel = new CustomizedClasses.GradientRoundBoxes();
        rentalNum = new javax.swing.JLabel();
        totalRental = new javax.swing.JLabel();
        TotalBookPanel = new CustomizedClasses.GradientRoundBoxes();
        bookNum = new javax.swing.JLabel();
        totalbooks = new javax.swing.JLabel();
        TotalSalesPanel = new CustomizedClasses.GradientRoundBoxes();
        totalSales = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        saleNum = new javax.swing.JLabel();
        php = new javax.swing.JLabel();
        shadow3 = new javax.swing.JLabel();
        shadow2 = new javax.swing.JLabel();
        shadow1 = new javax.swing.JLabel();
        Sales = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        User = new javax.swing.JPanel();
        UserScroll = new CustomizedClasses.ScrollPaneWin11();
        UserBody = new javax.swing.JPanel();
        UserList = new javax.swing.JLabel();
        UserPanelList = new javax.swing.JPanel();
        UserTableHeader = new javax.swing.JPanel();
        userID = new javax.swing.JLabel();
        username = new javax.swing.JLabel();
        name = new javax.swing.JLabel();
        email = new javax.swing.JLabel();
        phoneNum = new javax.swing.JLabel();
        Book = new javax.swing.JPanel();
        BookScroll = new CustomizedClasses.ScrollPaneWin11();
        BookBodyPanel = new javax.swing.JPanel();
        jBooks = new javax.swing.JLabel();
        AddBook = new CustomizedClasses.RoundedBorderButton();
        BookTab = new CustomizedClasses.MaterialTabbed();
        BookTabBody = new javax.swing.JPanel();
        BookListPanel = new javax.swing.JPanel();
        Rental = new javax.swing.JPanel();
        RentalScroll = new CustomizedClasses.ScrollPaneWin11();
        RentalBody = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        RentalTab = new CustomizedClasses.MaterialTabbed();
        monthBox = new CustomizedClasses.ComboBox();
        yearBox = new CustomizedClasses.ComboBox();
        sort = new CustomizedClasses.RoundedBorderButton();
        Transaction = new javax.swing.JPanel();
        TransactionScroll = new CustomizedClasses.ScrollPaneWin11();
        TransactionBody = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        TransactionTab = new CustomizedClasses.MaterialTabbed();
        monthBox1 = new CustomizedClasses.ComboBox();
        yearBox1 = new CustomizedClasses.ComboBox();
        sort1 = new CustomizedClasses.RoundedBorderButton();
        Genre = new javax.swing.JPanel();
        GenreScroll = new CustomizedClasses.ScrollPaneWin11();
        GenreBody = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        GenrePanels = new javax.swing.JPanel();
        roundedBorderButton1 = new CustomizedClasses.RoundedBorderButton();
        jPanel4 = new javax.swing.JPanel();
        genreID = new javax.swing.JLabel();
        genrename = new javax.swing.JLabel();
        mgID = new javax.swing.JLabel();
        cover = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        getContentPane().add(jDropdown, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 80, 170, 0));

        jSplitPane1.setBackground(new java.awt.Color(255, 255, 255));
        jSplitPane1.setDividerLocation(300);
        jSplitPane1.setDividerSize(1);

        SideNav.setBackground(new java.awt.Color(252, 253, 255));
        SideNav.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/images/upbook logo.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 230, 100));

        SideNav.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 300, 130));

        SideMenu.setBackground(new java.awt.Color(255, 255, 255));
        SideMenu.setLayout(new javax.swing.BoxLayout(SideMenu, javax.swing.BoxLayout.Y_AXIS));
        SideNav.add(SideMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 300, 630));

        jSplitPane1.setLeftComponent(SideNav);

        MainBody.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TopNav.setBackground(new java.awt.Color(1, 87, 220));
        TopNav.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jSideMenu.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        jSideMenu.setForeground(new java.awt.Color(146, 146, 146));
        jSideMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/images/adminicon.png"))); // NOI18N
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
        TopNav.add(jSideMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 0, 120, 80));
        TopNav.add(comboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 120, -1, -1));

        MainBody.add(TopNav, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 80));

        MenuBody.setBackground(new java.awt.Color(249, 249, 249));
        MenuBody.setLayout(new java.awt.CardLayout());

        Dashboard.setBackground(new java.awt.Color(255, 255, 255));
        Dashboard.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        DashboardScroll.setBackground(new java.awt.Color(249, 249, 249));
        DashboardScroll.setBorder(null);

        Body.setBackground(new java.awt.Color(255, 255, 255));
        Body.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        DashboardTotal.setBackground(new java.awt.Color(252, 252, 252));
        DashboardTotal.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jDashBoard.setFont(new java.awt.Font("Poppins", 1, 24)); // NOI18N
        jDashBoard.setText("Dashboard");
        DashboardTotal.add(jDashBoard, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, 153, -1));

        TotalRentalPanel.setBackground(new java.awt.Color(252, 252, 252));
        TotalRentalPanel.setGradientEnd(new java.awt.Color(220, 88, 156));
        TotalRentalPanel.setGradientStart(new java.awt.Color(210, 42, 129));
        TotalRentalPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        rentalNum.setFont(new java.awt.Font("Poppins ExtraBold", 0, 32)); // NOI18N
        rentalNum.setForeground(new java.awt.Color(255, 255, 255));
        rentalNum.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        rentalNum.setText("14");
        TotalRentalPanel.add(rentalNum, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 201, 40));

        totalRental.setFont(new java.awt.Font("Poppins", 1, 16)); // NOI18N
        totalRental.setForeground(new java.awt.Color(255, 255, 255));
        totalRental.setText("Total Rental");
        TotalRentalPanel.add(totalRental, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 25, 122, -1));

        DashboardTotal.add(TotalRentalPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 70, 250, 150));

        TotalBookPanel.setBackground(new java.awt.Color(252, 252, 252));
        TotalBookPanel.setGradientEnd(new java.awt.Color(245, 134, 54));
        TotalBookPanel.setGradientStart(new java.awt.Color(228, 89, 70));
        TotalBookPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bookNum.setFont(new java.awt.Font("Poppins ExtraBold", 0, 32)); // NOI18N
        bookNum.setForeground(new java.awt.Color(255, 255, 255));
        bookNum.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        bookNum.setText("14");
        TotalBookPanel.add(bookNum, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 200, 40));

        totalbooks.setFont(new java.awt.Font("Poppins", 1, 16)); // NOI18N
        totalbooks.setForeground(new java.awt.Color(255, 255, 255));
        totalbooks.setText("Total Book");
        TotalBookPanel.add(totalbooks, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 25, 122, -1));

        DashboardTotal.add(TotalBookPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, 250, 150));

        TotalSalesPanel.setBackground(new java.awt.Color(252, 252, 252));
        TotalSalesPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        totalSales.setFont(new java.awt.Font("Poppins", 1, 16)); // NOI18N
        totalSales.setForeground(new java.awt.Color(255, 255, 255));
        totalSales.setText("Total Sales");
        TotalSalesPanel.add(totalSales, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 25, 122, -1));

        jPanel2.setOpaque(false);
        java.awt.FlowLayout flowLayout1 = new java.awt.FlowLayout(java.awt.FlowLayout.TRAILING, 1, 1);
        flowLayout1.setAlignOnBaseline(true);
        jPanel2.setLayout(flowLayout1);

        saleNum.setFont(new java.awt.Font("Poppins ExtraBold", 0, 32)); // NOI18N
        saleNum.setForeground(new java.awt.Color(255, 255, 255));
        saleNum.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        saleNum.setText("23423.00");
        jPanel2.add(saleNum);

        php.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        php.setForeground(new java.awt.Color(255, 255, 255));
        php.setText("PHP");
        jPanel2.add(php);

        TotalSalesPanel.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 85, 210, 40));

        DashboardTotal.add(TotalSalesPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 70, 250, 150));

        shadow3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        shadow3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/images/gradientpanelshadow.png"))); // NOI18N
        shadow3.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        DashboardTotal.add(shadow3, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 70, 270, 165));

        shadow2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        shadow2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/images/gradientpanelshadow.png"))); // NOI18N
        shadow2.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        DashboardTotal.add(shadow2, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 70, 270, 165));

        shadow1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        shadow1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/images/gradientpanelshadow.png"))); // NOI18N
        shadow1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        DashboardTotal.add(shadow1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 270, 165));

        Body.add(DashboardTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 260));

        Sales.setBackground(new java.awt.Color(255, 255, 255));
        Sales.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Poppins", 1, 24)); // NOI18N
        jLabel2.setText("Latest Sales Chart");
        Sales.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 42, 244, 41));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 794, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 271, Short.MAX_VALUE)
        );

        Sales.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 89, -1, -1));

        Body.add(Sales, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 260, 900, 420));

        DashboardScroll.setViewportView(Body);

        Dashboard.add(DashboardScroll, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 680));

        MenuBody.add(Dashboard, "dashboard");

        UserScroll.setBorder(null);

        UserBody.setBackground(new java.awt.Color(249, 249, 249));
        UserBody.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        UserList.setFont(new java.awt.Font("Poppins", 1, 24)); // NOI18N
        UserList.setText("User List");
        UserBody.add(UserList, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, 110, -1));

        UserPanelList.setBackground(new java.awt.Color(255, 255, 255));
        UserPanelList.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 1, 1));
        UserBody.add(UserPanelList, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 143, 800, -1));

        UserTableHeader.setBackground(new java.awt.Color(255, 255, 255));
        UserTableHeader.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        userID.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        userID.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        userID.setText("User ID");
        UserTableHeader.add(userID, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, 80, 50));

        username.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        username.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        username.setText("Username");
        UserTableHeader.add(username, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 0, 90, 50));

        name.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        name.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        name.setText("Name");
        UserTableHeader.add(name, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 0, 160, 50));

        email.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        email.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        email.setText("Email");
        UserTableHeader.add(email, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 0, 190, 50));

        phoneNum.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        phoneNum.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        phoneNum.setText("Phone Number");
        UserTableHeader.add(phoneNum, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 0, 150, 50));

        UserBody.add(UserTableHeader, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, 800, 50));

        UserScroll.setViewportView(UserBody);

        org.jdesktop.layout.GroupLayout UserLayout = new org.jdesktop.layout.GroupLayout(User);
        User.setLayout(UserLayout);
        UserLayout.setHorizontalGroup(
            UserLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(UserScroll, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 900, Short.MAX_VALUE)
        );
        UserLayout.setVerticalGroup(
            UserLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(UserLayout.createSequentialGroup()
                .add(UserScroll, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 711, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(0, 0, Short.MAX_VALUE))
        );

        MenuBody.add(User, "user");

        Book.setBackground(new java.awt.Color(252, 252, 252));
        Book.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BookScroll.setBorder(null);
        BookScroll.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        BookBodyPanel.setBackground(new java.awt.Color(252, 252, 252));
        BookBodyPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jBooks.setFont(new java.awt.Font("Poppins", 1, 24)); // NOI18N
        jBooks.setText("Books");
        BookBodyPanel.add(jBooks, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 80, 50));

        AddBook.setBackground(new java.awt.Color(1, 87, 220));
        AddBook.setForeground(new java.awt.Color(255, 255, 255));
        AddBook.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/images/buttonaddwhite.png"))); // NOI18N
        AddBook.setText("Add Books");
        AddBook.setFocusable(false);
        AddBook.setFont(new java.awt.Font("Roboto Medium", 0, 15)); // NOI18N
        AddBook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddBookActionPerformed(evt);
            }
        });
        BookBodyPanel.add(AddBook, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 40, 150, 40));

        BookTab.setBackground(new java.awt.Color(252, 252, 252));
        BookTab.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        BookTab.setToolTipText("");
        BookTab.setFont(new java.awt.Font("Poppins Medium", 0, 16)); // NOI18N

        BookTabBody.setBackground(new java.awt.Color(252, 252, 252));
        BookTabBody.setFocusable(false);
        BookTabBody.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        BookTabBody.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BookListPanel.setBackground(new java.awt.Color(252, 252, 252));
        BookListPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 1, 1));
        BookTabBody.add(BookListPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 15, 810, -1));

        BookTab.addTab("  Book  ", BookTabBody);

        BookBodyPanel.add(BookTab, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 820, -1));

        BookScroll.setViewportView(BookBodyPanel);

        Book.add(BookScroll, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 680));

        MenuBody.add(Book, "book");

        RentalScroll.setBorder(null);

        RentalBody.setBackground(new java.awt.Color(249, 249, 249));
        RentalBody.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Poppins", 1, 24)); // NOI18N
        jLabel3.setText("Rental History");
        RentalBody.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, -1, -1));

        RentalTab.setFont(new java.awt.Font("Roboto Medium", 0, 16)); // NOI18N
        RentalBody.add(RentalTab, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, 800, -1));

        monthBox.setBackground(new java.awt.Color(249, 249, 249));
        monthBox.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        monthBox.setLabeText("Month");
        monthBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                monthBoxActionPerformed(evt);
            }
        });
        RentalBody.add(monthBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 40, 100, -1));

        yearBox.setBackground(new java.awt.Color(249, 249, 249));
        yearBox.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        yearBox.setLabeText("Year");
        RentalBody.add(yearBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 40, 100, -1));

        sort.setBackground(new java.awt.Color(1, 87, 220));
        sort.setForeground(new java.awt.Color(255, 255, 255));
        sort.setText("Sort");
        sort.setFont(new java.awt.Font("Roboto Medium", 0, 16)); // NOI18N
        sort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sortActionPerformed(evt);
            }
        });
        RentalBody.add(sort, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 43, 80, 40));

        RentalScroll.setViewportView(RentalBody);

        org.jdesktop.layout.GroupLayout RentalLayout = new org.jdesktop.layout.GroupLayout(Rental);
        Rental.setLayout(RentalLayout);
        RentalLayout.setHorizontalGroup(
            RentalLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(RentalScroll, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 900, Short.MAX_VALUE)
        );
        RentalLayout.setVerticalGroup(
            RentalLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(RentalScroll, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        MenuBody.add(Rental, "rental");

        TransactionScroll.setBorder(null);

        TransactionBody.setBackground(new java.awt.Color(249, 249, 249));

        jLabel4.setFont(new java.awt.Font("Poppins", 1, 24)); // NOI18N
        jLabel4.setText("Transaction History");

        TransactionTab.setFont(new java.awt.Font("Roboto Medium", 0, 16)); // NOI18N

        monthBox1.setBackground(new java.awt.Color(249, 249, 249));
        monthBox1.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        monthBox1.setLabeText("Month");
        monthBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                monthBox1ActionPerformed(evt);
            }
        });

        yearBox1.setBackground(new java.awt.Color(249, 249, 249));
        yearBox1.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        yearBox1.setLabeText("Year");

        sort1.setBackground(new java.awt.Color(1, 87, 220));
        sort1.setForeground(new java.awt.Color(255, 255, 255));
        sort1.setText("Sort");
        sort1.setFont(new java.awt.Font("Roboto Medium", 0, 16)); // NOI18N
        sort1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sort1ActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout TransactionBodyLayout = new org.jdesktop.layout.GroupLayout(TransactionBody);
        TransactionBody.setLayout(TransactionBodyLayout);
        TransactionBodyLayout.setHorizontalGroup(
            TransactionBodyLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(TransactionBodyLayout.createSequentialGroup()
                .add(50, 50, 50)
                .add(TransactionBodyLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(TransactionBodyLayout.createSequentialGroup()
                        .add(jLabel4)
                        .add(259, 259, 259)
                        .add(monthBox1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 100, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(10, 10, 10)
                        .add(yearBox1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 100, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(10, 10, 10)
                        .add(sort1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 80, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(TransactionTab, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 800, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
        );
        TransactionBodyLayout.setVerticalGroup(
            TransactionBodyLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(TransactionBodyLayout.createSequentialGroup()
                .add(40, 40, 40)
                .add(TransactionBodyLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(TransactionBodyLayout.createSequentialGroup()
                        .add(10, 10, 10)
                        .add(jLabel4))
                    .add(monthBox1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(yearBox1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(TransactionBodyLayout.createSequentialGroup()
                        .add(3, 3, 3)
                        .add(sort1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 40, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .add(13, 13, 13)
                .add(TransactionTab, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );

        TransactionScroll.setViewportView(TransactionBody);

        org.jdesktop.layout.GroupLayout TransactionLayout = new org.jdesktop.layout.GroupLayout(Transaction);
        Transaction.setLayout(TransactionLayout);
        TransactionLayout.setHorizontalGroup(
            TransactionLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(TransactionScroll, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 900, Short.MAX_VALUE)
        );
        TransactionLayout.setVerticalGroup(
            TransactionLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(TransactionScroll, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        MenuBody.add(Transaction, "transaction");

        GenreScroll.setBorder(null);

        GenreBody.setBackground(new java.awt.Color(249, 249, 249));
        GenreBody.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Poppins", 1, 24)); // NOI18N
        jLabel5.setText("Genres");
        GenreBody.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, -1, -1));

        GenrePanels.setBackground(new java.awt.Color(249, 249, 249));
        GenrePanels.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 1, 1));
        GenreBody.add(GenrePanels, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 147, 800, -1));

        roundedBorderButton1.setBackground(new java.awt.Color(1, 87, 220));
        roundedBorderButton1.setForeground(new java.awt.Color(255, 255, 255));
        roundedBorderButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/images/buttonaddwhite.png"))); // NOI18N
        roundedBorderButton1.setText("Add Genre");
        roundedBorderButton1.setFont(new java.awt.Font("Roboto Medium", 0, 15)); // NOI18N
        roundedBorderButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundedBorderButton1ActionPerformed(evt);
            }
        });
        GenreBody.add(roundedBorderButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 40, 130, 50));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        genreID.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        genreID.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        genreID.setText("Genre ID");
        jPanel4.add(genreID, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, 110, 50));

        genrename.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        genrename.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        genrename.setText("Genre Name");
        jPanel4.add(genrename, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 0, 150, 50));

        mgID.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        mgID.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        mgID.setText("MainGenre ID");
        jPanel4.add(mgID, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 0, 120, 50));

        cover.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        cover.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cover.setText("Image Cover");
        jPanel4.add(cover, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 0, 340, 50));

        GenreBody.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 95, 800, -1));

        GenreScroll.setViewportView(GenreBody);

        org.jdesktop.layout.GroupLayout GenreLayout = new org.jdesktop.layout.GroupLayout(Genre);
        Genre.setLayout(GenreLayout);
        GenreLayout.setHorizontalGroup(
            GenreLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(GenreScroll, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        GenreLayout.setVerticalGroup(
            GenreLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(GenreScroll, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        MenuBody.add(Genre, "genre");

        MainBody.add(MenuBody, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 900, 680));

        jSplitPane1.setRightComponent(MainBody);

        getContentPane().add(jSplitPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1200, 760));

        setSize(new java.awt.Dimension(1216, 798));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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
        AdminSession session = AdminSession.getInstance();
        session.clearSession();
        AdminLogin mylogin = new AdminLogin();
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

    private void AddBookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddBookActionPerformed
        // TODO add your handling code here:
        GlassPanePopup.showPopup(new AddBook(AdminHome.this, AdminHome.this.executable));
    }//GEN-LAST:event_AddBookActionPerformed

    private void monthBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_monthBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_monthBoxActionPerformed

    private void sortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sortActionPerformed
        this.executable.executeRentalPanels();
    }//GEN-LAST:event_sortActionPerformed

    private void monthBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_monthBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_monthBox1ActionPerformed

    private void sort1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sort1ActionPerformed
        // TODO add your handling code here:
        this.executable.executeTransactionPanels();
    }//GEN-LAST:event_sort1ActionPerformed

    private void roundedBorderButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundedBorderButton1ActionPerformed
        GlassPanePopup.showPopup(new AddGenres(AdminHome.this, AdminHome.this.executable));
    }//GEN-LAST:event_roundedBorderButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(AdminHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminHome().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private CustomizedClasses.RoundedBorderButton AddBook;
    private javax.swing.JPanel Body;
    private javax.swing.JPanel Book;
    private javax.swing.JPanel BookBodyPanel;
    private javax.swing.JPanel BookListPanel;
    private CustomizedClasses.ScrollPaneWin11 BookScroll;
    private CustomizedClasses.MaterialTabbed BookTab;
    private javax.swing.JPanel BookTabBody;
    private javax.swing.JPanel Dashboard;
    private CustomizedClasses.ScrollPaneWin11 DashboardScroll;
    private javax.swing.JPanel DashboardTotal;
    private javax.swing.JPanel Genre;
    private javax.swing.JPanel GenreBody;
    private javax.swing.JPanel GenrePanels;
    private CustomizedClasses.ScrollPaneWin11 GenreScroll;
    private javax.swing.JPanel MainBody;
    private javax.swing.JPanel MenuBody;
    private javax.swing.JPanel Rental;
    private javax.swing.JPanel RentalBody;
    private CustomizedClasses.ScrollPaneWin11 RentalScroll;
    private CustomizedClasses.MaterialTabbed RentalTab;
    private javax.swing.JPanel Sales;
    private javax.swing.JPanel SideMenu;
    private javax.swing.JPanel SideNav;
    private javax.swing.JPanel TopNav;
    private CustomizedClasses.GradientRoundBoxes TotalBookPanel;
    private CustomizedClasses.GradientRoundBoxes TotalRentalPanel;
    private CustomizedClasses.GradientRoundBoxes TotalSalesPanel;
    private javax.swing.JPanel Transaction;
    private javax.swing.JPanel TransactionBody;
    private CustomizedClasses.ScrollPaneWin11 TransactionScroll;
    private CustomizedClasses.MaterialTabbed TransactionTab;
    private javax.swing.JPanel User;
    private javax.swing.JPanel UserBody;
    private javax.swing.JLabel UserList;
    private javax.swing.JPanel UserPanelList;
    private CustomizedClasses.ScrollPaneWin11 UserScroll;
    private javax.swing.JPanel UserTableHeader;
    private javax.swing.JLabel bookNum;
    private CustomizedClasses.ComboBox comboBox1;
    private javax.swing.JLabel cover;
    private javax.swing.JLabel email;
    private javax.swing.JLabel genreID;
    private javax.swing.JLabel genrename;
    private javax.swing.JLabel jBooks;
    private javax.swing.JLabel jDashBoard;
    private javax.swing.JPanel jDropdown;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JButton jLogout;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JButton jProfile;
    private javax.swing.JButton jSideMenu;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JLabel mgID;
    private CustomizedClasses.ComboBox monthBox;
    private CustomizedClasses.ComboBox monthBox1;
    private javax.swing.JLabel name;
    private javax.swing.JLabel phoneNum;
    private javax.swing.JLabel php;
    private javax.swing.JLabel rentalNum;
    private CustomizedClasses.RoundedBorderButton roundedBorderButton1;
    private javax.swing.JLabel saleNum;
    private javax.swing.JLabel shadow1;
    private javax.swing.JLabel shadow2;
    private javax.swing.JLabel shadow3;
    private CustomizedClasses.RoundedBorderButton sort;
    private CustomizedClasses.RoundedBorderButton sort1;
    private javax.swing.JLabel totalRental;
    private javax.swing.JLabel totalSales;
    private javax.swing.JLabel totalbooks;
    private javax.swing.JLabel userID;
    private javax.swing.JLabel username;
    private CustomizedClasses.ComboBox yearBox;
    private CustomizedClasses.ComboBox yearBox1;
    // End of variables declaration//GEN-END:variables
}
