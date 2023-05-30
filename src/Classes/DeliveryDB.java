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
public class DeliveryDB extends DBConnector {

    public DeliveryDB() {
    }

    public void createDeliveryAddress(String UserID, String name, String address, String landmark, String notes) {
        connect();
        String insertQuery = "insert into deliveryAddress(userID,name,address,landmark,notes) values(?,?,?,?,?)";
        try (PreparedStatement pst = sqlConn.prepareStatement(insertQuery)) {
            pst.setString(1, UserID);
            pst.setString(2, name);
            pst.setString(3, address);
            pst.setString(4, landmark);
            pst.setString(5, notes);
            pst.executeUpdate();
            sqlConn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DeliveryDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<DeliveryPanels> getAllAddress(String userID) {
        connect();
        ArrayList<DeliveryPanels> dp = new ArrayList<>();
        String selectQuery = "select * from deliveryAddress where userID = ?";
        try (PreparedStatement pst = sqlConn.prepareStatement(selectQuery)) {
            pst.setString(1, userID);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("addressID");
                    String user = rs.getString("userID");
                    String name = rs.getString("name");
                    String address = rs.getString("address");
                    String landmark = rs.getString("landmark");
                    String notes = rs.getString("notes");
                    DeliveryPanels da = new DeliveryPanels(id, user, name, address, landmark, notes);
                    dp.add(da);
                }
                sqlConn.close();
            }

        } catch (SQLException ex) {
            Logger.getLogger(DeliveryDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dp;
    }

    public DeliveryAddress getAddress(int addressID) {
        connect();
        DeliveryAddress del = null;
        String selectQuery = "select * from deliveryAddress where addressID = ?";
        try (PreparedStatement pst = sqlConn.prepareStatement(selectQuery)) {
            pst.setInt(1, addressID);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("addressID");
                    String user = rs.getString("userID");
                    String name = rs.getString("name");
                    String address = rs.getString("address");
                    String landmark = rs.getString("landmark");
                    String notes = rs.getString("notes");
                    del = new DeliveryAddress(id, user, name, address, landmark, notes);
                }
                sqlConn.close();
            }

        } catch (SQLException ex) {
            Logger.getLogger(DeliveryDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return del;
    }

    public void removeDeliveryAddress(int addressID) {
        connect();
        try {
            String deleteCheckoutQuery = "update checkout set addressID = null where addressID = ?";
            PreparedStatement deleteCheckoutStmt = sqlConn.prepareStatement(deleteCheckoutQuery);
            deleteCheckoutStmt.setInt(1, addressID);
            deleteCheckoutStmt.executeUpdate();

            String deleteDeliveryQuery = "delete from deliveryaddress where addressID = ?";
            PreparedStatement deleteDeliveryStmt = sqlConn.prepareStatement(deleteDeliveryQuery);
            deleteDeliveryStmt.setInt(1, addressID);
            deleteDeliveryStmt.executeUpdate();

            sqlConn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DeliveryDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateDeliveryAddress(String UserID,int addressID, String name, String address, String landmark, String notes) {
        connect();
        String insertQuery = "update from set name = ?, address = ?, landmark = ?, notes =? where  addressID= ? and userID = ?";
        try (PreparedStatement pst = sqlConn.prepareStatement(insertQuery)) {
            pst.setString(1, name);
            pst.setString(2, address);
            pst.setString(3, landmark);
            pst.setString(4, notes);
            pst.setInt(5, addressID);
            pst.setString(6, UserID);
            pst.executeUpdate();
            sqlConn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DeliveryDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
