/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import User.AddCart;
import User.BookExpand;
import User.Home;
import User.BookPanel;
import User.BookChoosen;
import CustomizedClasses.GlassPanePopup;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.sql.Timestamp;

/**
 *
 * @author Aesthetics
 */
public class BookPanels extends Book {

    private BookPanel bookpanel;
    private BookChoosen choosenbook;
    BookDB bdb = new BookDB();
    FavoriteDB fdb = new FavoriteDB();
    UserCartDB ucdb = new UserCartDB();
    RentalDB rdb = new RentalDB();
    CheckoutDB cdb = new CheckoutDB();
    UserCart addedBook;
    Home home;
    BookExpand bookexpand;

    public UserCart getAddedBook() {
        return addedBook;
    }

    public void setBookexpand(BookExpand bookexpand) {
        this.bookexpand = bookexpand;
    }

    public void setHome(Home home) {
        this.home = home;
    }

    public BookChoosen getChoosenbook() {
        return choosenbook;
    }

    public void setChoosenbook(BookChoosen choosenbook) {
        this.choosenbook = choosenbook;
    }

    public BookPanel getBookpanel() {
        return bookpanel;
    }

    public void setBookpanel(BookPanel bookpanel) {
        this.bookpanel = bookpanel;
    }

    public BookPanels getBookPanels() {
        return this;
    }

    public BookPanels() {
    }

    public BookPanels(String bookID, String title, String firstName, String lastName, String date, int inventory, float rate, String isbn, float price, String imagePath) {
        super(bookID, title, firstName, lastName, date, inventory, rate, isbn, price, imagePath);
        this.bookpanel = new BookPanel(BookPanels.this);
        this.choosenbook = new BookChoosen(BookPanels.this);
        this.setGenreID(bdb.getGenreId(BookPanels.this.getBookID()));
        this.setFaveStatus(fdb.checkFavoriteBook(BookPanels.this.getBookID(),UserSession.getInstance().getUser().getUserID()));
        this.choosenbook.setFaveStatus(this.isFaveStatus());
        this.bookpanel.setAct(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (BookPanels.this.getInventory() != 0) {
                    if (!cdb.inCheckout(UserSession.getInstance().getUser().getUserID(), BookPanels.this.getBookID())) {
                        if (!ucdb.inCart(UserSession.getInstance().getUser().getUserID(), BookPanels.this.getBookID())) {
                            if (!rdb.inRental(UserSession.getInstance().getUser().getUserID(), BookPanels.this.getBookID())) {
                                addedBook = new UserCart(BookPanels.this);
                                AddCart ac = new AddCart(addedBook);
                                if (BookPanels.this.bookexpand != null) {
                                    BookPanels.this.bookexpand.setAddedbook(addedBook);
                                    ac.setBookexpand(BookPanels.this.bookexpand);
                                }
                                if (BookPanels.this.home != null) {
                                    BookPanels.this.home.setAddedbook(addedBook);
                                    ac.setHome(BookPanels.this.home);
                                }
                                GlassPanePopup.showPopup(ac);
                            } else {
                                JOptionPane.showMessageDialog(null, "You have already rented this book.", "Already Rented", JOptionPane.INFORMATION_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "You have already added this book to your cart. Check your cart.", "Already Added", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "This book is already pending in your checkout.", "Pending Checkout", JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "We're sorry to tell you that the book is already rented.You can set this to favorite to get updates once it's available.", "Book Rented", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        this.bookpanel.setEst(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                BookExpand mybook = new BookExpand(BookPanels.this.getBookID());
                Window[] windows = Window.getWindows();
                for (Window window : windows) {
                    if (window.isActive()) {
                        window.dispose();
                    }
                }
                mybook.show();

            }
        });
    }

}
