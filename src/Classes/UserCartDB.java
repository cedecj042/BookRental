/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import User.Cart;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Aesthetics
 */
public class UserCartDB extends DBConnector {

    public UserCartDB() {
    }

    public boolean inCart(String userID, String bookID) {
        connect();
        boolean exist = false;
        String selectQuery = "select * from userCart where userID = ? and bookID = ?";
        try (PreparedStatement pst = sqlConn.prepareStatement(selectQuery)) {
            pst.setString(1, userID);
            pst.setString(2, bookID);
            try (ResultSet rs = pst.executeQuery()) {
                exist = rs.next();
            }
            sqlConn.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserCartDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return exist;
    }

    public void addCartBook(UserCart usercart) {
        connect();
        String insertQuery = "insert into userCart (userID, bookID, amount, rentedDays) values (?,?,?,?)";
        try (PreparedStatement pst = sqlConn.prepareStatement(insertQuery)) {
            pst.setString(1, usercart.getUser().getUserID());
            pst.setString(2, usercart.getBook().getBookID());
            pst.setDouble(3, usercart.getAmount());
            pst.setInt(4, usercart.getRentedDays());
            pst.executeUpdate();
            sqlConn.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserCartDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public UserCartPanels getCartBook(String userID,String bookID) {
        connect();
        String selectQuery = "select * from userCart where bookID = ? and userID = ?";
        UserCartPanels usercart = null;
        try (PreparedStatement pst = sqlConn.prepareStatement(selectQuery)) {
            pst.setString(1, bookID);
            pst.setString(2, userID);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
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
                    int rented = rs.getInt("rentedDays");
                    int quantity = rs.getInt("quantity");
                    double amount = rs.getFloat("amount");
                    Book book = new Book(id, bookTitle, authorFirstName, authorLastName, date, inventory, rate, isbn, price,image);
                    usercart = new UserCartPanels(book, quantity, rented, amount);
                }
            }
            sqlConn.close();
        } catch (SQLException ex) {
            Logger.getLogger(BookDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usercart;
    }

    public ArrayList<UserCartPanels> getAllCartBook(Cart cartFrame, String userID) {
        connect();
        String selectQuery = "select * from userCart uc join book b on b.bookID = uc.bookID where uc.userID = ?";
        ArrayList<UserCartPanels> usercartpanels = new ArrayList<>();
        try (PreparedStatement pst = sqlConn.prepareStatement(selectQuery)) {
            pst.setString(1, userID);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    String id = rs.getString("uc.bookID");
                    String bookTitle = rs.getString("b.bookName");
                    String authorFirstName = rs.getString("b.authorFirstName");
                    String authorLastName = rs.getString("b.authorLastName");
                    String date = rs.getString("b.publicationDate");
                    int inventory = rs.getInt("b.inventory");
                    float rate = rs.getFloat("b.rate");
                    String isbn = rs.getString("b.isbn");
                    float price = rs.getFloat("b.price");
                    String image = rs.getString("b.bookImage");
                    int rented = rs.getInt("uc.rentedDays");
                    int quantity = rs.getInt("uc.quantity");
                    double amount = rs.getFloat("uc.amount");
                    Book book = new Book(id, bookTitle, authorFirstName, authorLastName, date, inventory, rate, isbn, price,image);
                    UserCartPanels cart = new UserCartPanels(book, quantity, rented, amount);
                    cart.setCart(cartFrame);
                    usercartpanels.add(cart);
                }
            }
            sqlConn.close();
        } catch (SQLException ex) {
            Logger.getLogger(BookDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usercartpanels;
    }

    public int countUserCart(String userID) {
        connect();
        int count = 0;
        String selectQuery = "Select count(*) Count from userCart where userID = ?";
        try (PreparedStatement pst = sqlConn.prepareStatement(selectQuery)) {
            pst.setString(1, userID);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    count = rs.getInt("Count");
                }
            }
            sqlConn.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserCartDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    public int addRentedDays(String userID,String bookID) {
      int days=1;
        connect();
        String getQuery = "select rentedDays from userCart where userID = ? and bookID = ?";
        String addQuery = "update userCart uc join book b on uc.bookID = b.bookID set uc.rentedDays = uc.rentedDays + 1, uc.amount = uc.rentedDays * b.price where uc.userID = ? and uc.bookID = ?";
        try (PreparedStatement pst = sqlConn.prepareStatement(addQuery)) {
            pst.setString(1, userID);
            pst.setString(2, bookID);
            pst.executeUpdate();
            try (PreparedStatement ps = sqlConn.prepareStatement(getQuery)) {
                ps.setString(1, userID);
                ps.setString(2, bookID);
                try(ResultSet rs = ps.executeQuery()){
                    if(rs.next()){
                        days = rs.getInt("rentedDays");
                    }
                }
                sqlConn.close();
            }
            sqlConn.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserCartDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return days;
    }

    public int minusRentedDays(String userID,String bookID) {
        int days=1;
        connect();
        String getQuery = "select rentedDays from userCart where userID = ? and bookID = ?";
        String addQuery = "update userCart uc join book b on uc.bookID = b.bookID set uc.rentedDays = uc.rentedDays - 1, uc.amount = uc.rentedDays * b.price where uc.userID = ? and uc.bookID = ?";
        try (PreparedStatement pst = sqlConn.prepareStatement(addQuery)) {
            pst.setString(1, userID);
            pst.setString(2, bookID);
            pst.executeUpdate();
            try (PreparedStatement ps = sqlConn.prepareStatement(getQuery)) {
                ps.setString(1, UserSession.getInstance().getUser().getUserID());
                ps.setString(2, bookID);
                try(ResultSet rs = ps.executeQuery()){
                    if(rs.next()){
                        days = rs.getInt("rentedDays");
                    }
                }
                sqlConn.close();
            }
            sqlConn.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserCartDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return days;
    }

    public void removeCart(String userID,String bookID) {
        connect();
        String removeQuery = "delete from userCart where userID = ? and bookID = ?";
        try (PreparedStatement pst = sqlConn.prepareStatement(removeQuery)) {
            pst.setString(1, userID);
            pst.setString(2, bookID);
            pst.executeUpdate();
            sqlConn.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserCartDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void removeSelectedCart(String[] keys,String userID) {
        connect();
        String removeQuery = "delete from userCart where userID = ? and bookID = ?";
        try (PreparedStatement pst = sqlConn.prepareStatement(removeQuery)) {
            for (String key : keys) {
                pst.setString(1, userID);
                pst.setString(2, key);
                pst.executeUpdate();
            }

            sqlConn.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserCartDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
