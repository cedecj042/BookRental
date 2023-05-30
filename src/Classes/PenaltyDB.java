/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import java.util.ArrayList;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Aesthetics
 */
public class PenaltyDB extends DBConnector {

    public PenaltyDB() {
    }

    public ArrayList<Penalty> getAllPenalty(String userID) {
        connect();
        ArrayList<Penalty> penalties = new ArrayList<>();
        String selectQuery = "select * from penalty where userID = ?";
        try (PreparedStatement pst = sqlConn.prepareStatement(selectQuery)) {
            pst.setString(1, userID);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    String penaltyID = rs.getString("penaltyID");
                    String rentalID = rs.getString("rentalID");
                    String transactionID = rs.getString("transactionID");
                    String penaltyType = rs.getString("paymentType");
                    Double penaltyAmount = rs.getDouble("penaltyAmount");
                    Timestamp penaltyDate = rs.getTimestamp("penaltyDate");
                    String status = rs.getString("paymentStatus");
                    Penalty penalty = new Penalty(penaltyID,userID,rentalID,transactionID,penaltyType,penaltyAmount,penaltyDate,status);
                    penalties.add(penalty);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PenaltyDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return penalties;
    }
}
