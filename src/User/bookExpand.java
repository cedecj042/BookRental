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
import Classes.UserCart;
import Classes.UserCartDB;
import Classes.UserSession;
import CustomizedClasses.GlassPanePopup;
import CustomizedClasses.ScrollBarWin11UI;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import static javax.swing.SwingConstants.CENTER;
import javax.swing.UIDefaults;
import javax.swing.UIManager;

/**
 *
 * @author Aesthetics
 */
public class BookExpand extends javax.swing.JFrame {

    int dropdowncounter = 0;
    int days;
    double rent;
    static BookPanels bp;
    static int num;

    //CardLayouts
    CardLayout bodyLayout;

    private ArrayList<BookPanels> bookpanel = new ArrayList<>();
    //Database implementation
    UserCartDB ucdb;
    GenreDB gdb;
    BookDB bdb;
    FavoriteDB fdb;

    public JLabel getNotiflabel() {
        return notiflabel;
    }

    public void setNotiflabel(JLabel notiflabel) {
        this.notiflabel = notiflabel;
    }

    UserCart addedbook;

    public void setAddedbook(UserCart addedbook) {
        this.addedbook = addedbook;
    }

    /**
     * Creates new form Book
     */
    public BookExpand(String bookID) {
        initComponents();

        ucdb = new UserCartDB();
        gdb = new GenreDB();
        bdb = new BookDB();
        fdb = new FavoriteDB();
        GlassPanePopup.install(this);
        bodyLayout = (CardLayout) (Body.getLayout());
        showNotif();
        execute(bookID);

        scrollpane.getViewport().setBackground(new Color(252, 252, 252));
        scrollpane.setScrollSpeed(5);
        int num = loadAllBooks();
        setMainPanelList(num);

    }

    public void setMainPanelList(int num) {
        MainPanelList.setPreferredSize(new Dimension(900, calculatePanelHeight(num)));
        MainPanelList.setMaximumSize(new Dimension(900, calculatePanelHeight(num)));
        MainPanelList.setMinimumSize(new Dimension(900, calculatePanelHeight(num)));
        MainPanelList.setSize(new Dimension(900, calculatePanelHeight(num)));
    }

    void execute(String bookID) {
        BookPanels book = bdb.getBook(bookID);
        BookExpand.bp = book;

        BookChoosen myBook = book.getChoosenbook();
        myBook.setBookexpand(BookExpand.this);
        myBook.setAct(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (book.getInventory() != 0) {
                    if (!ucdb.inCart(UserSession.getInstance().getUser().getUserID(), book.getBookID())) {
                        addedbook = new UserCart(book);
                        myBook.bookexpand.setAddedbook(addedbook);
                        AddCart ac = new AddCart(addedbook);
                        ac.setBookexpand(BookExpand.this);
                        GlassPanePopup.showPopup(ac);
                    } else {
                        JOptionPane.showMessageDialog(null, "You have already added this book to your cart. Check your cart at the cart menu.", "Already Added", JOptionPane.INFORMATION_MESSAGE);
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "We're sorry to tell you that the book is already rented.You can set this to favorite to get updates once it's available.", "Book Rented", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        myBook.setEst(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (myBook.isFaveStatus()) {
                    GlassPanePopup.showPopup(new FavoriteRemove(BookExpand.this));
                } else {
                    GlassPanePopup.showPopup(new FavoriteAdd(BookExpand.this));
                }
            }
        });
        myBook.setbst(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Home myhome = new Home();
                myhome.show();
                dispose();
            }
        });
        mybook.removeAll();
        mybook.add(myBook);
    }

    private int loadAllBooks() {
        int count = 0;
        MainPanelList.removeAll();
        bookpanel = setFrame(bdb.getGenreBooks(bdb.getGenreId(BookExpand.bp.getBookID())));
        for (int f = 0; f < bookpanel.size(); f++) {
            if (!bookpanel.get(f).getBookID().equals(BookExpand.this.bp.getBookID())) {
                MainPanelList.add(bookpanel.get(f).getBookpanel());
                count++;
            }

        }
        return count;
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
            bp.setBookexpand(BookExpand.this);
            bookpanels.add(bp);
        }
        return bookpanels;
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
        Body = new javax.swing.JPanel();
        book = new javax.swing.JPanel();
        scrollpane = new CustomizedClasses.ScrollPaneWin11();
        mylibrary = new javax.swing.JPanel();
        MainPanelList = new javax.swing.JPanel();
        mybook = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(252, 252, 252));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Main.setBackground(new java.awt.Color(252, 252, 252));
        Main.setMaximumSize(new java.awt.Dimension(1200, 790));
        Main.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Header.setBackground(new java.awt.Color(255, 255, 255));
        Header.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        notiflabel.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        notiflabel.setForeground(new java.awt.Color(255, 255, 255));
        notiflabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        notiflabel.setText("0");
        Header.add(notiflabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(1006, 18, 30, 30));

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

        Body.setBackground(new java.awt.Color(255, 255, 255));
        Body.setLayout(new java.awt.CardLayout());

        book.setBackground(new java.awt.Color(252, 252, 252));
        book.setAutoscrolls(true);
        book.setPreferredSize(new java.awt.Dimension(1200, 665));
        book.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        scrollpane.setBackground(new java.awt.Color(252, 252, 252));
        scrollpane.setBorder(null);
        scrollpane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        mylibrary.setBackground(new java.awt.Color(252, 252, 252));
        mylibrary.setMaximumSize(new java.awt.Dimension(900, 790));
        mylibrary.setMinimumSize(new java.awt.Dimension(900, 700));
        mylibrary.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        MainPanelList.setBackground(new java.awt.Color(252, 252, 252));
        MainPanelList.setAutoscrolls(true);
        MainPanelList.setMaximumSize(new java.awt.Dimension(900, 790));
        MainPanelList.setMinimumSize(new java.awt.Dimension(900, 790));
        MainPanelList.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 3));
        mylibrary.add(MainPanelList, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 450, 850, -1));

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

        mylibrary.add(mybook, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 30, 860, 360));

        jPanel1.setBackground(new java.awt.Color(252, 252, 252));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Roboto Medium", 0, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("You may also like");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 50));

        mylibrary.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 390, 180, 50));

        jSeparator4.setBackground(new java.awt.Color(252, 252, 252));
        jSeparator4.setForeground(new java.awt.Color(252, 252, 252));
        mylibrary.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 410, 660, 10));

        scrollpane.setViewportView(mylibrary);

        book.add(scrollpane, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1200, 670));

        Body.add(book, "card3");

        Main.add(Body, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 1200, 670));

        getContentPane().add(Main, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        setSize(new java.awt.Dimension(1216, 798));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLibraryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jLibraryActionPerformed
        // TODO add your handling code here:
        //        cardLayout.addLayoutComponent(jPanelBody, SOMEBITS);
        Home myHome = new Home();
        myHome.show();
        dispose();
    }//GEN-LAST:event_jLibraryActionPerformed

    private void jTransactionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTransactionActionPerformed
        // TODO add your handling code here:
        Transactions mytransact = new Transactions();
        mytransact.show();
        dispose();
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
            java.util.logging.Logger.getLogger(BookExpand.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BookExpand.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BookExpand.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BookExpand.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
        UIDefaults ui = UIManager.getDefaults();
        ui.put("ScrollBarUI", ScrollBarWin11UI.class.getCanonicalName());
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new BookExpand(bp.getBookID()).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Body;
    private javax.swing.JPanel Header;
    private javax.swing.JPanel Main;
    private javax.swing.JPanel MainPanelList;
    private javax.swing.JPanel book;
    private javax.swing.JButton jCart;
    private javax.swing.JPanel jDropdown;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JButton jLibrary;
    private javax.swing.JButton jLogout;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton jProfile;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JButton jSideMenu;
    private javax.swing.JButton jTransaction;
    private javax.swing.JPanel mybook;
    private javax.swing.JPanel mylibrary;
    private javax.swing.JLabel notiflabel;
    private CustomizedClasses.ScrollPaneWin11 scrollpane;
    // End of variables declaration//GEN-END:variables
}
