/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Aesthetics
 */
public class CheckoutDB extends DBConnector {

    public CheckoutDB() {
    }

    public String createDeliveryCheckout(UserCart uc, int addressID, double deliveryFee, double libraryFee, Timestamp deliveryDateandTime, Timestamp checkoutDate) {
        connect();
        String generatedID = null;
        String insertQuery = "insert into checkout (checkoutID,userID,bookID,addressID,quantity,rentedDays,deliveryFee,libraryFee,amount,deliveryDateandTime,checkoutDate,checkoutStatus) values(generateCheckoutID(),?,?,?,?,?,?,?,?,?,?,?)";
        String selectQuery = "select max(checkoutID) from checkout";
        try (PreparedStatement pst = sqlConn.prepareStatement(insertQuery)) {
            pst.setString(1, uc.getUser().getUserID());
            pst.setString(2, uc.getBook().getBookID());
            pst.setInt(3, addressID);
            pst.setInt(4, uc.getQuantity());
            pst.setInt(5, uc.getRentedDays());
            pst.setDouble(6, deliveryFee);
            pst.setDouble(7, libraryFee);
            pst.setDouble(8, uc.getAmount());
            pst.setTimestamp(9, deliveryDateandTime);
            pst.setTimestamp(10, checkoutDate);
            pst.setString(11, "In Progress");
            pst.executeUpdate();
            try (PreparedStatement selectPst = sqlConn.prepareStatement(selectQuery)) {
                try (ResultSet rs = selectPst.executeQuery()) {
                    if (rs.next()) {
                        generatedID = rs.getString(1);
                    }
                }
            }

            sqlConn.close();
        } catch (SQLException ex) {
            Logger.getLogger(CheckoutDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return generatedID;
    }

    public String createPickupCheckout(UserCart uc, double libraryFee, Timestamp pickupDateandTime, Timestamp checkoutDate) {
        connect();
        String generatedID = null;
        String insertQuery = "insert into checkout (checkoutID,userID,bookID,quantity,rentedDays,libraryFee,amount,pickupDateandTime,checkoutDate,checkoutStatus) values(generateCheckoutID(),?,?,?,?,?,?,?,?,?)";
        String selectQuery = "select max(checkoutID) from checkout";
        try (PreparedStatement pst = sqlConn.prepareStatement(insertQuery)) {
            pst.setString(1, uc.getUser().getUserID());
            pst.setString(2, uc.getBook().getBookID());
            pst.setInt(3, uc.getQuantity());
            pst.setInt(4, uc.getRentedDays());
            pst.setDouble(5, libraryFee);
            pst.setDouble(6, uc.getAmount());
            pst.setTimestamp(7, pickupDateandTime);
            pst.setTimestamp(8, checkoutDate);
            pst.setString(9, "In Progress");
            pst.executeUpdate();
            try (PreparedStatement selectPst = sqlConn.prepareStatement(selectQuery)) {
                try (ResultSet rs = selectPst.executeQuery()) {
                    if (rs.next()) {
                        generatedID = rs.getString(1);
                    }
                }
            }
            sqlConn.close();
        } catch (SQLException ex) {
            Logger.getLogger(CheckoutDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return generatedID;
    }

    public ArrayList<Checkout> getAllCheckouts(String UserID) {
        connect();
        ArrayList<Checkout> checkouts = new ArrayList<>();
        String selectQuery = "select * from checkout where userID = ?";
        try (PreparedStatement pst = sqlConn.prepareStatement(selectQuery)) {
            pst.setString(1, UserID);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    String id = rs.getString("checkoutID");
                    String userID = rs.getString("userID");
                    String bookID = rs.getString("bookID");
                    String status = rs.getString("checkoutStatus");
                    Timestamp date = rs.getTimestamp("checkoutDate");
                    int days = rs.getInt("rentedDays");
                    double amount = rs.getDouble("amount");
                    Checkout checkout = new Checkout(id, userID, bookID, status, date, amount, days);
                    checkouts.add(checkout);
                }
                sqlConn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(CheckoutDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return checkouts;
    }

    public ArrayList<Rental> getCheckouts(List<String> checkoutID, String UserID) {
        connect();
        ArrayList<Rental> rentals = new ArrayList<>();
        String selectQuery = "select * from checkout where userID = ? and checkoutID = ?";
        try (PreparedStatement pst = sqlConn.prepareStatement(selectQuery)) {
            pst.setString(1, UserID);
            for (String id : checkoutID) {
                pst.setString(2, id);
                try (ResultSet rs = pst.executeQuery()) {
                    if (rs.next()) {
                        String checkid = rs.getString("checkoutID");
                        String userID = rs.getString("userID");
                        String bookID = rs.getString("bookID");
                        Timestamp date = rs.getTimestamp("deliveryDateandTime");
                        if (date == null) {
                            date = rs.getTimestamp("pickupDateandTime");
                        }
                        int days = rs.getInt("rentedDays");

                        Calendar cal = Calendar.getInstance();
                        cal.setTime(date);
                        cal.add(Calendar.DATE, days);
                        Timestamp newDate = new Timestamp(cal.getTimeInMillis());
                        String status = rs.getString("checkoutStatus");
                        String rentstatus = null;
                        if (status.equals("Confirmed")) {
                            rentstatus = "Checked Out";
                        } else if (status.equals("In Progress")) {
                            rentstatus = "Reserved";
                        }
                        Rental rental = new Rental(userID, bookID, checkid, date, newDate, rentstatus);
                        rentals.add(rental);
                    }

                }
            }
            sqlConn.close();
        } catch (SQLException ex) {
            Logger.getLogger(CheckoutDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rentals;
    }

    public void setTransactionCheckout(String transactionID, List<String> checkoutid, String status, String userID) {
        connect();
        String insertQuery = "update checkout set transactionID = ? , checkoutStatus = ? where checkoutID = ? and userID = ?";
        try (PreparedStatement pst = sqlConn.prepareStatement(insertQuery)) {
            for (String id : checkoutid) {
                pst.setString(1, transactionID);
                pst.setString(2, status);
                pst.setString(3, id);
                pst.setString(4, userID);
                pst.executeUpdate();
            }
            sqlConn.close();
        } catch (SQLException ex) {
            Logger.getLogger(CheckoutDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Checkout getCheckout(String checkoutID, String userID) {
        Checkout checkout = null;
        connect();
        String selectQuery = "select * from checkout where checkoutID = ? and userID = ?";
        try (PreparedStatement pst = sqlConn.prepareStatement(selectQuery)) {
            pst.setString(1, checkoutID);
            pst.setString(2, userID);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    String id = rs.getString("checkoutID");
                    String user = rs.getString("userID");
                    String bookID = rs.getString("bookID");
                    int addressID = rs.getInt("addressID");
                    int quantity = rs.getInt("quantity");
                    int days = rs.getInt("rentedDays");
                    double df = rs.getDouble("deliveryFee");
                    double lf = rs.getDouble("libraryFee");
                    double amount = rs.getDouble("amount");
                    Timestamp ddt = rs.getTimestamp("deliveryDateandTime");
                    Timestamp pdt = rs.getTimestamp("pickupDateandTime");
                    Timestamp date = rs.getTimestamp("checkoutDate");
                    String status = rs.getString("checkoutStatus");
                    checkout = new Checkout(id, user, bookID, addressID, quantity, days, df, lf, amount, ddt, pdt, date, status);
                }
                sqlConn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(CheckoutDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return checkout;
    }

    public int countCheckoutBooks(String checkoutID) {
        connect();
        int books = 0;
        String selectQuery = "Select count(*) from checkout where checkoutID = ?";
        try (PreparedStatement pst = sqlConn.prepareStatement(selectQuery)) {
            pst.setString(1, checkoutID);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    books = rs.getInt(1);
                }
                sqlConn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(CheckoutDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return books;
    }

    public double getAmount(String checkoutID) {
        connect();
        double amount = 0;
        String selectQuery = "Select sum(amount) from checkout where checkoutID = ? group by checkoutID";
        try (PreparedStatement pst = sqlConn.prepareStatement(selectQuery)) {
            pst.setString(1, checkoutID);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    amount = rs.getInt(1);
                }
                sqlConn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(CheckoutDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return amount;

    }

    public boolean inCheckout(String userID, String bookID) {
        connect();
        boolean exist = false;
        String selectQuery = "select * from checkout where userID = ? and bookID = ? and checkoutStatus = 'In Progress' ";
        try (PreparedStatement pst = sqlConn.prepareStatement(selectQuery)) {
            pst.setString(1, userID);
            pst.setString(2, bookID);
            try (ResultSet rs = pst.executeQuery()) {
                exist = rs.next();
            }
            sqlConn.close();
        } catch (SQLException ex) {
            Logger.getLogger(RentalDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return exist;
    }

    public ArrayList<Checkout> getConfirmedCheckout(String userID) {
        connect();
        ArrayList<Checkout> checkouts = new ArrayList<>();
        String selectQuery = "select * from checkout where userID = ? and checkoutStatus != 'In Progress'";
        try (PreparedStatement pst = sqlConn.prepareStatement(selectQuery)) {
            pst.setString(1, userID);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    String id = rs.getString("checkoutID");
                    String user = rs.getString("userID");
                    String bookID = rs.getString("bookID");
                    int addressID = rs.getInt("addressID");
                    int quantity = rs.getInt("quantity");
                    int days = rs.getInt("rentedDays");
                    double df = rs.getDouble("deliveryFee");
                    double lf = rs.getDouble("libraryFee");
                    double amount = rs.getDouble("amount");
                    Timestamp ddt = rs.getTimestamp("deliveryDateandTime");
                    Timestamp pdt = rs.getTimestamp("pickupDateandTime");
                    Timestamp date = rs.getTimestamp("checkoutDate");
                    String status = rs.getString("checkoutStatus");
                    Checkout checkout = new Checkout(id, user, bookID, addressID, quantity, days, df, lf, amount, ddt, pdt, date, status);
                    checkouts.add(checkout);
                }
            }
            sqlConn.close();
        } catch (SQLException ex) {
            Logger.getLogger(CheckoutDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return checkouts;
    }

    public Map<Timestamp, ArrayList<Checkout>> getPendingCheckoutByDate(String userID) {
        connect();
        Timestamp currdate = null;
        Map<Timestamp, ArrayList<Checkout>> checkMap = new HashMap<>();
        String selectQuery = """
                             select checkoutDate, checkoutID, userID, bookID, transactionID, addressID, quantity, amount, deliveryFee, libraryFee, rentedDays, deliveryDateandTime, pickupDateandTime, checkoutStatus
                             from checkout
                             where checkoutStatus like 'In Progress%' and userID = ?
                             group by checkoutDate, checkoutID, userID, bookID, transactionID, addressID, quantity, amount, deliveryFee, libraryFee, rentedDays, deliveryDateandTime, pickupDateandTime, checkoutStatus
                             """;
        try (PreparedStatement pst = sqlConn.prepareStatement(selectQuery)) {
            pst.setString(1, userID);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    String id = rs.getString("checkoutID");
                    String user = rs.getString("userID");
                    String bookID = rs.getString("bookID");
                    int addressID = rs.getInt("addressID");
                    int quantity = rs.getInt("quantity");
                    int days = rs.getInt("rentedDays");
                    double df = rs.getDouble("deliveryFee");
                    double lf = rs.getDouble("libraryFee");
                    double amount = rs.getDouble("amount");
                    Timestamp ddt = rs.getTimestamp("deliveryDateandTime");
                    Timestamp pdt = rs.getTimestamp("pickupDateandTime");
                    Timestamp date = rs.getTimestamp("checkoutDate");
                    String status = rs.getString("checkoutStatus");
                    Checkout checkout = new Checkout(id, user, bookID, addressID, quantity, days, df, lf, amount, ddt, pdt, date, status);
                    ArrayList<Checkout> checkouts = checkMap.get(date);
                    if (checkouts == null) {
                        checkouts = new ArrayList<>();
                        checkMap.put(date, checkouts);
                    }
                    checkouts.add(checkout);
                }
            }
            sqlConn.close();
        } catch (SQLException ex) {
            Logger.getLogger(CheckoutDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (Timestamp key : checkMap.keySet()) {
            System.out.println("Key: " + key);
        }
        for (ArrayList<Checkout> ck : checkMap.values()) {
            System.out.println("Key: " + ck.size());
        }
        return checkMap;
    }

    public Map<Timestamp, ArrayList<Checkout>> getAllCheckoutByDate(String userID) {
        connect();
        Timestamp currdate = null;
        Map<Timestamp, ArrayList<Checkout>> checkMap = new HashMap<>();
        String selectQuery = """
                             select checkoutDate, checkoutID, userID, bookID, transactionID, addressID, quantity, amount, deliveryFee, libraryFee, rentedDays, deliveryDateandTime, pickupDateandTime, checkoutStatus
                             from checkout
                             where checkoutStatus = 'Confirmed' or checkoutStatus = 'Failed' and userID = ?
                             group by checkoutDate, checkoutID, userID, bookID, transactionID, addressID, quantity, amount, deliveryFee, libraryFee, rentedDays, deliveryDateandTime, pickupDateandTime, checkoutStatus
                             """;
        try (PreparedStatement pst = sqlConn.prepareStatement(selectQuery)) {
            pst.setString(1, userID);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    String id = rs.getString("checkoutID");
                    String user = rs.getString("userID");
                    String bookID = rs.getString("bookID");
                    int addressID = rs.getInt("addressID");
                    int quantity = rs.getInt("quantity");
                    int days = rs.getInt("rentedDays");
                    double df = rs.getDouble("deliveryFee");
                    double lf = rs.getDouble("libraryFee");
                    double amount = rs.getDouble("amount");
                    String transactID = rs.getString("transactionID");
                    Timestamp ddt = rs.getTimestamp("deliveryDateandTime");
                    Timestamp pdt = rs.getTimestamp("pickupDateandTime");
                    Timestamp date = rs.getTimestamp("checkoutDate");
                    String status = rs.getString("checkoutStatus");
                    Checkout checkout = new Checkout(id, user, bookID, transactID, addressID, quantity, days, df, lf, amount, ddt, pdt, date, status);
                    ArrayList<Checkout> checkouts = checkMap.get(date);
                    if (checkouts == null) {
                        checkouts = new ArrayList<>();
                        checkMap.put(date, checkouts);
                    }
                    checkouts.add(checkout);
                }
            }
            sqlConn.close();
        } catch (SQLException ex) {
            Logger.getLogger(CheckoutDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return checkMap;
    }

    public void removeCheckout(String checkoutid) {
        connect();
        String deleteQuery = " delete from checkout where checkoutID= ?";
        try (PreparedStatement pst = sqlConn.prepareStatement(deleteQuery)) {
            pst.setString(1, checkoutid);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CheckoutDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateCheckout(String checkoutid, String status) {
        connect();
        String updateQuery = "update checkout set checkoutStatus = ? where checkoutID = ?";
        try (PreparedStatement pst = sqlConn.prepareStatement(updateQuery)) {
            pst.setString(1, status);
            pst.setString(2, checkoutid);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CheckoutDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
