/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Aesthetics
 */
public class RentalDB extends DBConnector {

    public RentalDB() {
    }

    public int countTotalRental() {
        connect();
        int count = 0;
        String selectQuery = "select count(*) from rental";
        try (PreparedStatement pst = sqlConn.prepareStatement(selectQuery)) {
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    count = rs.getInt(1);
                }
            }
            sqlConn.close();
        } catch (SQLException ex) {
            Logger.getLogger(BookDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    public void createRental(Rental rental) {
        connect();
        String insertQuery = "insert into rental (rentalID,userID,bookID,checkoutID,startDate,endDate,rentStatus) values(generateRentalID(),?,?,?,?,?,?)";
        try (PreparedStatement pst = sqlConn.prepareStatement(insertQuery)) {
            pst.setString(1, rental.getUserID());
            pst.setString(2, rental.getBookID());
            pst.setString(3, rental.getCheckoutID());
            pst.setTimestamp(4, rental.getStartDate());
            pst.setTimestamp(5, rental.getEndDate());
            pst.setString(6, rental.getRentStatus());
            pst.executeUpdate();
            sqlConn.close();
        } catch (SQLException ex) {
            Logger.getLogger(RentalDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Rental> getRentalHistory(String UserID) {
        connect();
        ArrayList<Rental> rental = new ArrayList<>();
        String insertQuery = "select * from rental where userID = ? and rentStatus != 'Checked Out'";
        try (PreparedStatement pst = sqlConn.prepareStatement(insertQuery)) {
            pst.setString(1, UserID);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    String id = rs.getString("rentalID");
                    String userID = rs.getString("userID");
                    String bookID = rs.getString("bookID");
                    String checkoutID = rs.getString("checkoutID");
                    Timestamp start = rs.getTimestamp("startDate");
                    Timestamp end = rs.getTimestamp("endDate");
                    Timestamp returndate = rs.getTimestamp("returnDate");
                    String condition = rs.getString("returnCondition");
                    String status = rs.getString("rentStatus");
                    Rental rent = new Rental(id, userID, bookID, checkoutID, start, end, returndate, condition, status);
                    rental.add(rent);
                }
            }
            sqlConn.close();
        } catch (SQLException ex) {
            Logger.getLogger(RentalDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rental;
    }

    public ArrayList<Rental> getActiveRental(String UserID) {
        connect();
        ArrayList<Rental> rental = new ArrayList<>();
        String insertQuery = "select * from rental where userID = ? and rentStatus = 'Checked Out'";
        try (PreparedStatement pst = sqlConn.prepareStatement(insertQuery)) {
            pst.setString(1, UserID);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    String id = rs.getString("rentalID");
                    String userID = rs.getString("userID");
                    String bookID = rs.getString("bookID");
                    String checkoutID = rs.getString("checkoutID");
                    Timestamp start = rs.getTimestamp("startDate");
                    Timestamp end = rs.getTimestamp("endDate");
                    String status = rs.getString("rentStatus");
                    Rental rent = new Rental(id, userID, bookID, checkoutID, start, end, status);
                    rental.add(rent);
                }
            }
            sqlConn.close();
        } catch (SQLException ex) {
            Logger.getLogger(RentalDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rental;
    }

    public boolean inRental(String userID, String bookID) {
        connect();
        boolean exist = false;
        String selectQuery = "select * from rental where userID = ? and bookID = ? and rentStatus != 'Returned' ";
        try (PreparedStatement pst = sqlConn.prepareStatement(selectQuery)) {
            pst.setString(1, userID);
            pst.setString(2, bookID);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    exist = true;
                }

            }
            sqlConn.close();
        } catch (SQLException ex) {
            Logger.getLogger(RentalDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return exist;
    }

    public boolean inActiveRental(String userID, String bookID) {
        connect();
        boolean exist = false;
        String selectQuery = "select * from rental where userID = ? and bookID = ? and rentStatus = 'Checked Out' ";
        try (PreparedStatement pst = sqlConn.prepareStatement(selectQuery)) {
            pst.setString(1, userID);
            pst.setString(2, bookID);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    exist = true;
                }

            }
            sqlConn.close();
        } catch (SQLException ex) {
            Logger.getLogger(RentalDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return exist;
    }

    public int countRentalActive(String userID) {
        connect();
        int count = 0;
        String selectQuery = "select count(*) from rental where userID = ? and rentStatus = 'Checked Out'";
        try (PreparedStatement pst = sqlConn.prepareStatement(selectQuery)) {
            pst.setString(1, userID);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    count = rs.getInt(1);
                }
            }
            sqlConn.close();
        } catch (SQLException ex) {
            Logger.getLogger(RentalDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    public void completeRental(String userID, String rentalID, Timestamp returnDate, String returnCondition) {
        connect();
        String removeQuery = "update rental set returnDate = ? , returnCondition = ?,rentStatus = 'Returned' where rentalID = ? and userID = ?";
        try (PreparedStatement pst = sqlConn.prepareStatement(removeQuery)) {
            pst.setTimestamp(1, returnDate);
            pst.setString(2, returnCondition);
            pst.setString(3, rentalID);
            pst.setString(4, userID);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RentalDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Rental> getAllRental() {
        connect();
        ArrayList<Rental> rental = new ArrayList<>();
        String selectQuery = "select * from rental order by rentalID desc";
        try (PreparedStatement pst = sqlConn.prepareStatement(selectQuery)) {
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    String id = rs.getString("rentalID");
                    String userID = rs.getString("userID");
                    String bookID = rs.getString("bookID");
                    String checkoutID = rs.getString("checkoutID");
                    Timestamp start = rs.getTimestamp("startDate");
                    Timestamp end = rs.getTimestamp("endDate");
                    Timestamp returndate = rs.getTimestamp("returnDate");
                    String condition = rs.getString("returnCondition");
                    String status = rs.getString("rentStatus");
                    Rental rent = new Rental(id, userID, bookID, checkoutID, start, end, returndate, condition, status);
                    rental.add(rent);
                }
            }
            sqlConn.close();
        } catch (SQLException ex) {
            Logger.getLogger(RentalDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rental;
    }

    public List<String> getRentalYears() {
        connect();
        List<String> years = new ArrayList<>();
        String selectQuery = "select distinct year(startDate) as year from rental";
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

    public List<String> getRentalStatus() {
        connect();
        List<String> statuses = new ArrayList<>();
        String selectQuery = "select distinct rentStatus as status from rental";
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

    public ArrayList<Rental> getFilteredRental(String rentStatus,String month, String year) {
        connect();
        ArrayList<Rental> rentals = new ArrayList<>();
        String query = "select * from rental where 1=1";

        if(!rentStatus.equals("All Rentals")){
             query += " and rentStatus = '" + rentStatus + "'";
        }
        if (!year.equals("All")) {
            query += " and year(startDate) = '" + year + "'";
        }
        if (!month.equals("All")) {
            query += " and month(startDate) = '" + month + "'";
        }
        
        // Execute the query and populate the filteredRentals list
        try (PreparedStatement pst = sqlConn.prepareStatement(query)) {
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    String id = rs.getString("rentalID");
                    String userID = rs.getString("userID");
                    String bookID = rs.getString("bookID");
                    String checkoutID = rs.getString("checkoutID");
                    Timestamp start = rs.getTimestamp("startDate");
                    Timestamp end = rs.getTimestamp("endDate");
                    Timestamp returndate = rs.getTimestamp("returnDate");
                    String condition = rs.getString("returnCondition");
                    String status = rs.getString("rentStatus");
                    Rental rent = new Rental(id, userID, bookID, checkoutID, start, end, returndate, condition, status);
                    rentals.add(rent);
                }
            }
            sqlConn.close();
        } catch (SQLException ex) {
            Logger.getLogger(RentalDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rentals;
    }

}
