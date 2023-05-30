/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Aesthetics
 */
public class TransactionDB extends DBConnector {

    public TransactionDB() {
    }

    public Double getTotalSales() {
        connect();
        Double sum = 0.00;
        String selectQuery = "select * from transaction";
        try (PreparedStatement pst = sqlConn.prepareStatement(selectQuery)) {
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    Double amount = rs.getDouble("amountToPay");
                    sum += amount;
                }
            }
            sqlConn.close();
        } catch (SQLException ex) {
            Logger.getLogger(TransactionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sum;
    }

    public String createTransaction(String userID, String method, double amountToPay, double amountPaid, String transactionStatus) {
        connect();
        String generatedID = null;
        String insertQuery = " insert into transaction(transactionID , userID, paymentMethod,amountToPay,amountPaid,transactionStatus) values (generateTransactionID(),?,?,?,?,?)";
        String selectQuery = "select max(transactionID) from transaction";
        try (PreparedStatement pst = sqlConn.prepareStatement(insertQuery)) {
            pst.setString(1, userID);
            pst.setString(2, method);
            pst.setDouble(3, amountToPay);
            pst.setDouble(4, amountPaid);
            pst.setString(5, transactionStatus);
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
            Logger.getLogger(TransactionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return generatedID;
    }

    public Timestamp getTransactionDate(String transactionID) {
        connect();
        Timestamp date = null;
        String selectQuery = "Select * from transaction where transactionID = ?";
        try (PreparedStatement pst = sqlConn.prepareStatement(selectQuery)) {
            pst.setString(1, transactionID);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    date = rs.getTimestamp("transactionDate");
                }
                sqlConn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(TransactionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return date;
    }

    public Transaction getTransaction(String transactionID) {
        connect();
        Transaction transaction = null;
        String selectQuery = " Select * from transaction where transactionID = ?";
        try (PreparedStatement pst = sqlConn.prepareStatement(selectQuery)) {
            pst.setString(1, transactionID);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    String id = rs.getString("transactionID");
                    String user = rs.getString("userID");
                    Timestamp date = rs.getTimestamp("transactionDate");
                    Double atp = rs.getDouble("amountToPay");
                    Double ap = rs.getDouble("amountPaid");
                    String method = rs.getString("paymentMethod");
                    String status = rs.getString("transactionStatus");
                    transaction = new Transaction(id, user, method, atp, ap, date, status);
                }
                sqlConn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(TransactionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return transaction;
    }

    public ArrayList<Transaction> getAllTransaction(String userID) {
        connect();
        ArrayList<Transaction> transactions = new ArrayList<>();
        String selectQuery = " Select * from transaction where userID = ?";
        try (PreparedStatement pst = sqlConn.prepareStatement(selectQuery)) {
            pst.setString(1, userID);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    String id = rs.getString("transactionID");
                    String user = rs.getString("userID");
                    Timestamp date = rs.getTimestamp("transactionDate");
                    Double atp = rs.getDouble("amountToPay");
                    Double ap = rs.getDouble("amountPaid");
                    String method = rs.getString("paymentMethod");
                    String status = rs.getString("transactionStatus");
                    Transaction transaction = new Transaction(id, user, method, atp, ap, date, status);
                    transactions.add(transaction);
                }
                sqlConn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(TransactionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return transactions;
    }

    public ArrayList<Transaction> getFilteredTransaction (String transactStatus, String month, String year) {
        connect();
        ArrayList<Transaction> transactions = new ArrayList<>();
        String query = "select * from transaction where 1=1";

        if (!transactStatus.equals("All Transaction")) {
            query += " and transactionStatus = '" + transactStatus + "'";
        }
        if (!year.equals("All")) {
            query += " and year(transactionDate) = '" + year + "'";
        }
        if (!month.equals("All")) {
            query += " and month(transactionDate) = '" + month + "'";
        }

        // Execute the query and populate the filteredRentals list
        try (PreparedStatement pst = sqlConn.prepareStatement(query)) {
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    String id = rs.getString("transactionID");
                    String user = rs.getString("userID");
                    Timestamp date = rs.getTimestamp("transactionDate");
                    Double atp = rs.getDouble("amountToPay");
                    Double ap = rs.getDouble("amountPaid");
                    String method = rs.getString("paymentMethod");
                    String status = rs.getString("transactionStatus");
                    Transaction transaction = new Transaction(id, user, method, atp, ap, date, status);
                    transactions.add(transaction);
                }
            }
            sqlConn.close();
        } catch (SQLException ex) {
            Logger.getLogger(RentalDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return transactions;
    }
        public List<String> getTransactionYears() {
        connect();
        List<String> years = new ArrayList<>();
        String selectQuery = "select distinct year(transactionDate) as year from transaction";
        try (PreparedStatement pst = sqlConn.prepareStatement(selectQuery)) {
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    String year = rs.getString("year");
                    years.add(year);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(RentalDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return years;
    }

    public List<String> getTransactionStatus() {
        connect();
        List<String> statuses = new ArrayList<>();
        String selectQuery = "select distinct transactionStatus as status from transaction";
        try (PreparedStatement pst = sqlConn.prepareStatement(selectQuery)) {
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    String status = rs.getString("status");
                    statuses.add(status);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(RentalDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return statuses;
    }
}
