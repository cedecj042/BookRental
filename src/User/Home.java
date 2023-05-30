/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package User;

import Classes.Book;
import Classes.BookDB;
import Classes.BookPanels;
import Classes.FavoriteDB;
import Classes.GenreDB;
import Classes.GenrePanels;
import Classes.MainGenreDB;
import Classes.MainGenrePanels;
import Classes.RentalDB;
import Classes.UserCart;
import Classes.UserCartDB;
import Classes.UserSession;
import CustomizedClasses.GlassPanePopup;
import CustomizedClasses.ProgressBarCustom;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import static javax.swing.SwingConstants.CENTER;

/**
 *
 * @author Aesthetics
 */
public final class Home extends javax.swing.JFrame {

    int days;
    double rent;
    ImageIcon iconFavorites, iconGenre, iconHome;
    int dropdowncounter = 0;
    UserCart addedbook;
    ProgressBarCustom progressBar;
    LoadingPanel loadingPanel;

    //cardlayouts
    CardLayout cardLayout;
    CardLayout mainLayout;

    //arrays
    private ArrayList<BookPanels> faveBook = new ArrayList<>();
    private ArrayList<BookPanels> books = new ArrayList<>();
    private ArrayList<MainGenrePanels> mainGenre = new ArrayList<>();
    private ArrayList<GenrePanels> subGenre = new ArrayList<>();

    //Database implementation
    GenreDB gdb;
    MainGenreDB mgdb;
    BookDB bdb;
    FavoriteDB fdb;
    UserCartDB ucdb;
    RentalDB rdb;

    /**
     * Creates new form homepage
     */
    public void setAddedbook(UserCart addedbook) {
        this.addedbook = addedbook;
    }

    public JLabel getNotiflabel() {
        return notiflabel;
    }

    public void setNotiflabel(JLabel notiflabel) {
        this.notiflabel = notiflabel;
    }

    public Home() {
        initComponents();
        GlassPanePopup.install(Home.this);
        gdb = new GenreDB();
        mgdb = new MainGenreDB();
        bdb = new BookDB();
        fdb = new FavoriteDB();
        ucdb = new UserCartDB();
        rdb = new RentalDB();
        showNotif();

        this.cardLayout = (CardLayout) (jPanelBody.getLayout());
        this.mainLayout = (CardLayout) (MainBody.getLayout());

        // Create loading panel and add it to home panel
        loadingPanel = new LoadingPanel();
        this.progressBar = loadingPanel.getProgress();
        MainBody.add(loadingPanel, "loading");
        mainLayout.show(MainBody, "loading");
        MainBody.repaint();
        MainBody.revalidate();

        new Thread(() -> {
            execute();
            MainPanelList.setPreferredSize(new Dimension(910, calculatePanelHeight(loadBooks(MainPanelList, books))));
            FavoritePanel.setPreferredSize(new Dimension(910, calculatePanelHeight(loadBooks(FavoritePanel, faveBook))));
            books.clear();
            mainGenre.clear();
            subGenre.clear();
//            faveBook.clear();

            // Remove loading panel and show main content
            MainBody.remove(loadingPanel);
            mainLayout.show(MainBody, "main");
            MainBody.repaint();
            MainBody.revalidate();

            cardLayout.show(jPanelBody, "library");
            jPanelBody.repaint();
            jPanelBody.revalidate();

        }).start();

        notiflabel.setHorizontalAlignment(SwingConstants.CENTER);
        jScrollPane2.setViewportView(mylibrary);
        jScrollPane2.setScrollSpeed(5);
        jScrollPane3.setScrollSpeed(5);
        jScrollPane4.setScrollSpeed(5);

    }

    //Other functions   
    private void execute() {

        try {
            iconFavorites = new ImageIcon(getClass().getResource("../img/images/favorites.png"));
            iconGenre = new ImageIcon(getClass().getResource("../img/images/genre.png"));
            iconHome = new ImageIcon(getClass().getResource("../img/images/home.png"));
        } catch (Exception e) {
            System.out.println("Image cant be found.");
        }

        mainGenre = mgdb.getMainMenu();
        subGenre = gdb.getSubGenre();
        books = setFrame(bdb.getAllBooks());
        faveBook = fdb.getFavoriteBooks();

        MenuItem menuGenre = new MenuItem(iconGenre, "Genre", null);
        MenuItem menuHome = new MenuItem(iconHome, "Home", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    cardLayout.show(jPanelBody, "library");
                    jPanelBody.repaint();
                    jPanelBody.revalidate();
                } catch (Exception ex) {
                    System.out.print("Panel can't be found.");
                }
            }
        });
        MenuItem menuFavorites = new MenuItem(iconFavorites, "Favorites", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    cardLayout.show(jPanelBody, "favorite");
                    jPanelBody.repaint();
                    jPanelBody.revalidate();
                } catch (Exception ex) {
                    System.out.print("Panel can't be found.");
                }
            }
        });

        // Group sub genres by main genre ID using a HashMap
        Map<String, List<GenrePanels>> HashMapGenres = new HashMap<>();
        for (GenrePanels subgenre : subGenre) {
            String mainGenreId = subgenre.getMainGenreID();
            List<GenrePanels> subGenres = HashMapGenres.getOrDefault(mainGenreId, new ArrayList<>());
            subGenres.add(subgenre);
            HashMapGenres.put(mainGenreId, subGenres);
        }
        int totalBooks = mainGenre.size() + subGenre.size() + books.size() + faveBook.size();
        int loadedBooks = 0;
        // Loop through main genres
        for (MainGenrePanels maingenre : mainGenre) {
            String mainGenreId = maingenre.getMainGenreID();

            // Loop through sub genres belonging to this main genre
            List<GenrePanels> subGenres = HashMapGenres.getOrDefault(mainGenreId, new ArrayList<>());
            for (GenrePanels subgenre : subGenres) {
                // setting up menu
                maingenre.addSubGenre(subgenre.getSubgenre(), jPanelBody, cardLayout);
                // setting up genrepanels
                maingenre.addGenrePanels(subgenre);

                // setting up height
                subgenre.setNumGenrePanel(gdb.countSubGenreBooks(subgenre.getGenreID()));
                subgenre.getSubGenrePanel().setBookProductsHeight();
                subgenre.getSubGenrePanel().setGenreNameHeight();
                subgenre.setBookPanel(setFrame(bdb.getGenreBooks(subgenre.getGenreID())));

                loadedBooks += subgenre.getBookPanel().size();
            }

            menuGenre.addSubMenu(maingenre.getMaingenre());
            addPanel(maingenre.getGenrePanels());
            addBookPanel(maingenre.getGenrePanels());

            loadedBooks += subGenres.size();
            int progress = (int) ((loadedBooks / (double) totalBooks) * 100);
            progressBar.setValue(progress);
            loadingPanel.setProgress(progressBar);
        }
        addMenu(menuHome,menuFavorites, menuGenre);

    }

    private void addPanel(ArrayList<GenrePanels> p) {

        for (int i = 0; i < p.size(); i++) {
            jPanelBody.add(p.get(i).getSubGenrePanel());
            cardLayout.addLayoutComponent(p.get(i).getSubGenrePanel().getName(), p.get(i).getSubGenrePanel());
        }
        jPanelBody.revalidate();
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

    private void addBookPanel(ArrayList<GenrePanels> p) {
        for (int i = 0; i < p.size(); i++) {
            JPanel myProductsPanel = p.get(i).getSubGenrePanel().getMyProducts();
            myProductsPanel.removeAll();
            List<BookPanels> bookPanels = p.get(i).getBookPanel();
            if (!bookPanels.isEmpty()) {
                for (int f = 0; f < bookPanels.size(); f++) {
                    BookPanel bp = bookPanels.get(f).getBookpanel();
                    myProductsPanel.add(bp);
                }
            }
        }
    }

    private int loadBooks(Container container, ArrayList<BookPanels> p) {
        int count = 0;
        container.removeAll();
        for (int i = 0; i < p.size(); i++) {
            container.add(p.get(i).getBookpanel());
            count++;
        }
        return count;
    }

    public int calculatePanelHeight(int numBooks) {
        if (numBooks == 0) {
            return 0;
        } else {
            int height = (numBooks / 4) * 360;
            if (numBooks % 4 != 0) {
                height += 360;
            }
            return height;
        }
    }

    public void showNotif() {
        notiflabel.setText(String.valueOf(ucdb.countUserCart(UserSession.getInstance().getUser().getUserID())));
        notiflabel.setHorizontalTextPosition(CENTER);
    }

    public ArrayList<BookPanels> setFrame(ArrayList<Book> books) {
        ArrayList<BookPanels> bookpanels = new ArrayList<>();
        for (Book book : books) {
            String id = book.getBookID();
            String title = book.getTitle();
            String authorFirstName = book.getAuthorFirstName();
            String authorLastName = book.getAuthorLastName();
            String date = book.getPublicationDate();
            String isbn = book.getIsbn();
            float price = book.getPrice();
            float rate = book.getRate();
            String image = book.getImagePath();
            int inventory = book.getInventory();
            BookPanels bp = new BookPanels(id, title, authorFirstName, authorLastName, date, inventory, rate, isbn, price, image);
            bp.setHome(Home.this);
            bookpanels.add(bp);
        }
        return bookpanels;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Main = new javax.swing.JPanel();
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
        MainBody = new javax.swing.JPanel();
        Body = new javax.swing.JPanel();
        jSplitPane1 = new javax.swing.JSplitPane();
        jPanelMenu = new javax.swing.JPanel();
        jScrollPane1 = new CustomizedClasses.ScrollPaneWin11();
        menus = new javax.swing.JPanel();
        jPanelBody = new javax.swing.JPanel();
        Library = new javax.swing.JPanel();
        jScrollPane2 = new CustomizedClasses.ScrollPaneWin11();
        mylibrary = new javax.swing.JPanel();
        header = new javax.swing.JLabel();
        MainPanelList = new javax.swing.JPanel();
        title = new javax.swing.JLabel();
        subTitle = new javax.swing.JLabel();
        jseparator = new javax.swing.JLabel();
        Favorite = new javax.swing.JPanel();
        jScrollPane3 = new CustomizedClasses.ScrollPaneWin11();
        myfavorite = new javax.swing.JPanel();
        FavoritePanel = new javax.swing.JPanel();
        title1 = new javax.swing.JLabel();
        jseparator1 = new javax.swing.JLabel();
        Rented = new javax.swing.JPanel();
        jScrollPane4 = new CustomizedClasses.ScrollPaneWin11();
        myrented = new javax.swing.JPanel();
        RentedPanel = new javax.swing.JPanel();
        title2 = new javax.swing.JLabel();
        jseparator2 = new javax.swing.JLabel();
        BookExpand = new javax.swing.JPanel();
        scrollpane = new CustomizedClasses.ScrollPaneWin11();
        mylibrary1 = new javax.swing.JPanel();
        MainPanelList1 = new javax.swing.JPanel();
        mybook = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1200, 790));
        setResizable(false);
        setSize(new java.awt.Dimension(1200, 790));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Main.setBackground(new java.awt.Color(252, 252, 252));
        Main.setMaximumSize(new java.awt.Dimension(1200, 790));
        Main.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Header.setBackground(new java.awt.Color(255, 255, 255));
        Header.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        notiflabel.setFont(new java.awt.Font("Poppins", 1, 16)); // NOI18N
        notiflabel.setForeground(new java.awt.Color(255, 255, 255));
        notiflabel.setText("1");
        notiflabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        notiflabel.setIconTextGap(0);
        Header.add(notiflabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(1007, 18, 30, 30));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/images/cartnotif.png"))); // NOI18N
        Header.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1007, 17, 40, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/images/homelogo.png"))); // NOI18N
        Header.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, 160, 90));

        jLibrary.setFont(new java.awt.Font("Poppins Medium", 0, 18)); // NOI18N
        jLibrary.setForeground(new java.awt.Color(254, 156, 44));
        jLibrary.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/images/lib-active.png"))); // NOI18N
        jLibrary.setText("Library");
        jLibrary.setContentAreaFilled(false);
        jLibrary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jLibraryActionPerformed(evt);
            }
        });
        Header.add(jLibrary, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 0, 170, 90));

        jTransaction.setFont(new java.awt.Font("Poppins Medium", 0, 18)); // NOI18N
        jTransaction.setForeground(new java.awt.Color(30, 30, 30));
        jTransaction.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/images/transact-unactive.png"))); // NOI18N
        jTransaction.setText("Transactions");
        jTransaction.setContentAreaFilled(false);
        jTransaction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTransactionActionPerformed(evt);
            }
        });
        Header.add(jTransaction, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 0, 210, 90));

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

        jSeparator3.setBackground(new java.awt.Color(252, 252, 252));
        jSeparator3.setForeground(new java.awt.Color(252, 252, 252));
        Header.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 1210, 100));

        Main.add(Header, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1200, 90));

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

        Main.add(jDropdown, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 90, 170, 0));

        MainBody.setLayout(new java.awt.CardLayout());

        Body.setBackground(new java.awt.Color(252, 252, 252));
        Body.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(252, 252, 252)));
        Body.setLayout(new java.awt.CardLayout());

        jSplitPane1.setDividerSize(0);

        jPanelMenu.setBackground(new java.awt.Color(255, 255, 255));
        jPanelMenu.setMaximumSize(new java.awt.Dimension(300, 700));
        jPanelMenu.setMinimumSize(new java.awt.Dimension(300, 700));
        jPanelMenu.setPreferredSize(new java.awt.Dimension(300, 700));
        jPanelMenu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane1.setAutoscrolls(true);
        jScrollPane1.setMinimumSize(new java.awt.Dimension(300, 700));
        jScrollPane1.setViewportView(menus);

        menus.setBackground(new java.awt.Color(252, 252, 255));
        menus.setAutoscrolls(true);
        menus.setLayout(new javax.swing.BoxLayout(menus, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane1.setViewportView(menus);

        jPanelMenu.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -1, 300, 670));

        jSplitPane1.setLeftComponent(jPanelMenu);

        jPanelBody.setBackground(new java.awt.Color(253, 252, 252));
        jPanelBody.setAutoscrolls(true);
        jPanelBody.setLayout(new java.awt.CardLayout());

        Library.setBackground(new java.awt.Color(252, 252, 252));
        Library.setAutoscrolls(true);
        Library.setMaximumSize(new java.awt.Dimension(900, 790));
        Library.setPreferredSize(new java.awt.Dimension(900, 700));
        Library.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LibraryMouseClicked(evt);
            }
        });
        Library.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane2.setBorder(null);
        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        mylibrary.setBackground(new java.awt.Color(252, 252, 252));
        mylibrary.setAutoscrolls(true);
        mylibrary.setMaximumSize(new java.awt.Dimension(900, 790));
        mylibrary.setMinimumSize(new java.awt.Dimension(900, 700));
        mylibrary.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        header.setIcon(new javax.swing.ImageIcon(getClass().getResource("/User/Header.png"))); // NOI18N
        mylibrary.add(header, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 143));

        MainPanelList.setBackground(new java.awt.Color(252, 252, 252));
        MainPanelList.setAutoscrolls(true);
        MainPanelList.setMaximumSize(new java.awt.Dimension(900, 790));
        MainPanelList.setMinimumSize(new java.awt.Dimension(900, 790));
        MainPanelList.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 3));
        mylibrary.add(MainPanelList, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 270, 900, -1));

        title.setFont(new java.awt.Font("Poppins", 1, 24)); // NOI18N
        title.setText("Hi, Cedric");
        mylibrary.add(title, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 180, 500, 40));

        subTitle.setFont(new java.awt.Font("Poppins Medium", 0, 18)); // NOI18N
        subTitle.setText("Top picks for today");
        mylibrary.add(subTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 230, 240, 40));

        jseparator.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jseparator.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/images/separator.png"))); // NOI18N
        mylibrary.add(jseparator, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 210, 800, 30));

        jScrollPane2.setViewportView(mylibrary);

        Library.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 670));

        jPanelBody.add(Library, "library");

        Favorite.setBackground(new java.awt.Color(252, 252, 252));
        Favorite.setAutoscrolls(true);
        Favorite.setMaximumSize(new java.awt.Dimension(900, 790));
        Favorite.setPreferredSize(new java.awt.Dimension(900, 700));
        Favorite.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                FavoriteMouseClicked(evt);
            }
        });
        Favorite.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane3.setBorder(null);
        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        myfavorite.setBackground(new java.awt.Color(252, 252, 252));
        myfavorite.setAutoscrolls(true);
        myfavorite.setMaximumSize(new java.awt.Dimension(900, 790));
        myfavorite.setMinimumSize(new java.awt.Dimension(900, 700));
        myfavorite.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        FavoritePanel.setBackground(new java.awt.Color(252, 252, 252));
        FavoritePanel.setAutoscrolls(true);
        FavoritePanel.setMaximumSize(new java.awt.Dimension(900, 790));
        FavoritePanel.setMinimumSize(new java.awt.Dimension(900, 790));
        FavoritePanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 3));
        myfavorite.add(FavoritePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 116, 900, -1));

        title1.setFont(new java.awt.Font("Poppins", 1, 24)); // NOI18N
        title1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/images/largefavorites.png"))); // NOI18N
        title1.setText("Your Favorite");
        title1.setIconTextGap(10);
        myfavorite.add(title1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, 500, 40));

        jseparator1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jseparator1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/images/separator.png"))); // NOI18N
        myfavorite.add(jseparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, 800, 30));

        jScrollPane3.setViewportView(myfavorite);

        Favorite.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 898, 670));

        jPanelBody.add(Favorite, "favorite");

        Rented.setBackground(new java.awt.Color(252, 252, 252));
        Rented.setAutoscrolls(true);
        Rented.setMaximumSize(new java.awt.Dimension(900, 790));
        Rented.setPreferredSize(new java.awt.Dimension(900, 700));
        Rented.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RentedMouseClicked(evt);
            }
        });
        Rented.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane4.setBorder(null);
        jScrollPane4.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        myrented.setBackground(new java.awt.Color(252, 252, 252));
        myrented.setAutoscrolls(true);
        myrented.setMaximumSize(new java.awt.Dimension(900, 790));
        myrented.setMinimumSize(new java.awt.Dimension(900, 700));
        myrented.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        RentedPanel.setBackground(new java.awt.Color(252, 252, 252));
        RentedPanel.setAutoscrolls(true);
        RentedPanel.setMaximumSize(new java.awt.Dimension(900, 790));
        RentedPanel.setMinimumSize(new java.awt.Dimension(900, 790));
        RentedPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 3));
        myrented.add(RentedPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 116, 900, -1));

        title2.setFont(new java.awt.Font("Poppins", 1, 24)); // NOI18N
        title2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/images/largefavorites.png"))); // NOI18N
        title2.setText("Rented Books");
        title2.setIconTextGap(10);
        myrented.add(title2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, 500, 40));

        jseparator2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jseparator2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/images/separator.png"))); // NOI18N
        myrented.add(jseparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, 800, 30));

        jScrollPane4.setViewportView(myrented);

        Rented.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 670));

        jPanelBody.add(Rented, "rented");

        jSplitPane1.setRightComponent(jPanelBody);

        Body.add(jSplitPane1, "split");

        MainBody.add(Body, "main");

        BookExpand.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        scrollpane.setBackground(new java.awt.Color(252, 252, 252));
        scrollpane.setBorder(null);
        scrollpane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        mylibrary1.setBackground(new java.awt.Color(252, 252, 252));
        mylibrary1.setMaximumSize(new java.awt.Dimension(900, 790));
        mylibrary1.setMinimumSize(new java.awt.Dimension(900, 700));
        mylibrary1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        MainPanelList1.setBackground(new java.awt.Color(252, 252, 252));
        MainPanelList1.setAutoscrolls(true);
        MainPanelList1.setMaximumSize(new java.awt.Dimension(900, 790));
        MainPanelList1.setMinimumSize(new java.awt.Dimension(900, 790));
        MainPanelList1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 3));
        mylibrary1.add(MainPanelList1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 450, 850, -1));

        mybook.setBackground(new java.awt.Color(252, 252, 252));

        org.jdesktop.layout.GroupLayout mybookLayout = new org.jdesktop.layout.GroupLayout(mybook);
        mybook.setLayout(mybookLayout);
        mybookLayout.setHorizontalGroup(
            mybookLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 0, Short.MAX_VALUE)
        );
        mybookLayout.setVerticalGroup(
            mybookLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 0, Short.MAX_VALUE)
        );

        mylibrary1.add(mybook, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 30, 860, 360));

        jPanel1.setBackground(new java.awt.Color(252, 252, 252));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Roboto Medium", 0, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("You may also like");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 50));

        mylibrary1.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 390, 180, 50));

        jSeparator4.setBackground(new java.awt.Color(252, 252, 252));
        jSeparator4.setForeground(new java.awt.Color(252, 252, 252));
        mylibrary1.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 410, 660, 10));

        scrollpane.setViewportView(mylibrary1);

        BookExpand.add(scrollpane, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1200, 670));

        MainBody.add(BookExpand, "card3");

        Main.add(MainBody, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 1200, 700));

        getContentPane().add(Main, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        setSize(new java.awt.Dimension(1216, 798));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void LibraryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LibraryMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_LibraryMouseClicked

    private void jDropdownMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jDropdownMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jDropdownMouseExited

    private void jDropdownMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jDropdownMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jDropdownMouseEntered

    private void jProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jProfileActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jProfileActionPerformed

    private void jLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jLogoutActionPerformed
        UserSession session = UserSession.getInstance();
        session.clearSession();
        Login mylogin;
        mylogin = new Login();
        mylogin.show();
        dispose();
    }//GEN-LAST:event_jLogoutActionPerformed

    private void jLogoutMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLogoutMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jLogoutMousePressed

    private void jSideMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSideMenuActionPerformed
        // TODO add your handling code here:
        dropdowncounter++;
        if (dropdowncounter % 2 != 0) {
            jDropdown.setSize(160, 130);
        } else {
            jDropdown.setSize(0, 0);
        }
    }//GEN-LAST:event_jSideMenuActionPerformed

    private void jSideMenuMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSideMenuMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jSideMenuMousePressed

    private void jSideMenuMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSideMenuMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jSideMenuMouseExited

    private void jSideMenuMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSideMenuMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jSideMenuMouseEntered

    private void jSideMenuFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jSideMenuFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jSideMenuFocusLost

    private void jTransactionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTransactionActionPerformed
        // TODO add your handling code here:
        Transactions mytransact = new Transactions();
        mytransact.show();
        dispose();
    }//GEN-LAST:event_jTransactionActionPerformed

    private void jLibraryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jLibraryActionPerformed
        // TODO add your handling code here:
        jScrollPane2.setViewportView(mylibrary);
        cardLayout.show(jPanelBody, "library");
        jPanelBody.repaint();
        jPanelBody.revalidate();
    }//GEN-LAST:event_jLibraryActionPerformed

    private void FavoriteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FavoriteMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_FavoriteMouseClicked

    private void jCartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCartActionPerformed
        // TODO add your handling code here:
        Cart cartpage = new Cart();
        cartpage.show();
        dispose();
    }//GEN-LAST:event_jCartActionPerformed

    private void RentedMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RentedMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_RentedMouseClicked

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }


        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Home().setVisible(true);

            }

        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Body;
    private javax.swing.JPanel BookExpand;
    public javax.swing.JPanel Favorite;
    private javax.swing.JPanel FavoritePanel;
    private javax.swing.JPanel Header;
    public javax.swing.JPanel Library;
    private javax.swing.JPanel Main;
    private javax.swing.JPanel MainBody;
    private javax.swing.JPanel MainPanelList;
    private javax.swing.JPanel MainPanelList1;
    public javax.swing.JPanel Rented;
    private javax.swing.JPanel RentedPanel;
    private javax.swing.JLabel header;
    private javax.swing.JButton jCart;
    private javax.swing.JPanel jDropdown;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JButton jLibrary;
    private javax.swing.JButton jLogout;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelBody;
    private javax.swing.JPanel jPanelMenu;
    private javax.swing.JButton jProfile;
    private javax.swing.JScrollPane jScrollPane1;
    private CustomizedClasses.ScrollPaneWin11 jScrollPane2;
    private CustomizedClasses.ScrollPaneWin11 jScrollPane3;
    private CustomizedClasses.ScrollPaneWin11 jScrollPane4;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JButton jSideMenu;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JButton jTransaction;
    private javax.swing.JLabel jseparator;
    private javax.swing.JLabel jseparator1;
    private javax.swing.JLabel jseparator2;
    private javax.swing.JPanel menus;
    private javax.swing.JPanel mybook;
    private javax.swing.JPanel myfavorite;
    private javax.swing.JPanel mylibrary;
    private javax.swing.JPanel mylibrary1;
    private javax.swing.JPanel myrented;
    private javax.swing.JLabel notiflabel;
    private CustomizedClasses.ScrollPaneWin11 scrollpane;
    private javax.swing.JLabel subTitle;
    private javax.swing.JLabel title;
    private javax.swing.JLabel title1;
    private javax.swing.JLabel title2;
    // End of variables declaration//GEN-END:variables
}
