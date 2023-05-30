/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import Admin.AdminLogin;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Aesthetics
 */
public class AdminDB extends DBConnector {

    public AdminDB() {
    }

    public boolean checkUsername(String username) {
        connect();
        boolean exist = false;
        String selectQuery = "select * from admin where username = ?";
        try (PreparedStatement pst = sqlConn.prepareStatement(selectQuery)) {
            pst.setString(1, username);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    exist = true;
                }
            }
            sqlConn.close();
        } catch (SQLException ex) {
            Logger.getLogger(AdminDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(exist);
        return exist;

    }

    public boolean checkPassword(String username, String password) {
        connect();
        boolean check = false;
        String hashedpass = hashPassword(password);
        System.out.println(hashedpass);
        String selectQuery = "Select password from admin where username = ?";
        try (PreparedStatement pst = sqlConn.prepareStatement(selectQuery)) {
            pst.setString(1, username);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    check = rs.getString("password").equals(hashedpass);
                }
            }
            sqlConn.close();

        } catch (SQLException ex) {
            Logger.getLogger(AdminDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return check;
    }

    public Admin getAdmin(String username) {
        connect();
        Admin admin = null;
        String selectQuery = "select * from admin where username = ?";
        try (PreparedStatement pst = sqlConn.prepareStatement(selectQuery)) {
            pst.setString(1, username);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("adminID");
                    String firstName = rs.getString("firstName");
                    String lastName = rs.getString("lastName");
                    String pass = rs.getString("password");
                    Timestamp createdDate = rs.getTimestamp("createdAt");
                    Timestamp loginDate = rs.getTimestamp("lastLoginAt");
                    admin = new Admin(id, username, firstName, lastName, pass, createdDate, loginDate);
                }
            }
            sqlConn.close();

        } catch (SQLException ex) {
            Logger.getLogger(AdminDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return admin;
    }

    public String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] passwordbytes = password.getBytes();
            byte[] hashedpass = md.digest(passwordbytes);
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedpass) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException ex) {
            System.out.println("Password was not encrypted");
            JOptionPane.showMessageDialog(null, ex);
            return null;
        }
    }
}
