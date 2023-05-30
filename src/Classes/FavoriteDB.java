/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Aesthetics
 */
public class FavoriteDB extends DBConnector {

    public FavoriteDB() {
    }

    public void addFavorite(String bookID,String userID) {
        if (!this.checkFavoriteBook(bookID,userID)) {
            connect();
            String insertQuery = "insert into favorite (userID,bookID) values(?,?)";
            try (PreparedStatement pst = sqlConn.prepareStatement(insertQuery)) {
                pst.setString(1, UserSession.getInstance().getUser().getUserID());
                pst.setString(2, bookID);
                pst.executeUpdate();
                sqlConn.close();
            } catch (SQLException ex) {
                Logger.getLogger(FavoriteDB.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("Already added to Favorites.");
        }

    }

    public void removeFavorite(String bookID,String userID) {
        if (this.checkFavoriteBook(bookID,userID)) {
            connect();
            String removeQuery = "delete from favorite where userID = ? and bookID = ? ";
            try (PreparedStatement pst = sqlConn.prepareStatement(removeQuery)) {
                pst.setString(1, UserSession.getInstance().getUser().getUserID());
                pst.setString(2, bookID);
                pst.executeUpdate();
                sqlConn.close();
            } catch (SQLException ex) {
                Logger.getLogger(FavoriteDB.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("Already removed from Favorites.");
        }

    }

    public ArrayList<BookPanels> getFavoriteBooks() {
        connect();
        ArrayList<BookPanels> books = new ArrayList<>();
        BookPanels book;
        String selectQuery = "select * from favorite f join book b on f.bookID = b.bookID where userID = ? ";
        try (PreparedStatement pst = sqlConn.prepareStatement(selectQuery)) {
            pst.setString(1, UserSession.getInstance().getUser().getUserID());
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    String id = rs.getString("bookID");
                    String bookTitle = rs.getString("bookName");
                    String authorFirstName = rs.getString("authorFirstName");
                    String authorLastName = rs.getString("authorLastName");
                    String date = rs.getString("publicationDate");
                    int inventory = rs.getInt("inventory");
                    float rate = rs.getFloat("rate");
                    String isbn = rs.getString("isbn");
                    float price = rs.getFloat("price");
                    String image = rs.getString("bookImage");
                    book = new BookPanels(id, bookTitle, authorFirstName, authorLastName, date, inventory, rate, isbn, price, image);
                    books.add(book);
                }
            }
            sqlConn.close();
        } catch (SQLException ex) {
            Logger.getLogger(FavoriteDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return books;
    }

    public boolean checkFavoriteBook(String bookID,String userID) {
        connect();
        boolean exist = false;
        String selectQuery = "select * from favorite where userID = ? and bookID = ?";
        try (PreparedStatement pst = sqlConn.prepareStatement(selectQuery)) {
            pst.setString(1, userID);
            pst.setString(2, bookID);
            try (ResultSet rs = pst.executeQuery()) {
                exist = rs.next();
            }
            sqlConn.close();
        } catch (SQLException ex) {
            Logger.getLogger(FavoriteDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return exist;
    }
    

}
