/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import java.security.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.*;
import javax.swing.JOptionPane;

/**
 *
 * @author joopb15
 */
public class UserDB extends DBConnector{

    //Establish connection
    
    public UserDB() {
        super();
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

    public void createUser(String username, String firstName, String lastName, String phoneNum, String email, String password) throws SQLException{
        connect();
        String insertQuery = "INSERT INTO user (userID,username, firstName,lastName,phoneNum,email,password,image) values (generateUserID(),?, ?, ?, ?, ?, ?,?)";
        String hashedpass = hashPassword(password);
        try (PreparedStatement pst = sqlConn.prepareStatement(insertQuery)) {
            pst.setString(1, username);
            pst.setString(2, firstName);
            pst.setString(3, lastName);
            pst.setString(4, phoneNum);
            pst.setString(5, email);
            pst.setString(6, hashedpass);
            pst.setString(7,"../img/images/userIcon.png");
            pst.executeUpdate();
            pst.close();
            sqlConn.close();
        }
    }
    public ArrayList<User> getAllUser(){
        connect();
        ArrayList<User> users = new ArrayList<>();
        String selectQuery = "select * from user";
        
        try (PreparedStatement ps = sqlConn.prepareStatement(selectQuery)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String userID = rs.getString("userID");
                    String firstName = rs.getString("firstName");
                    String lastName = rs.getString("lastName");
                    String username = rs.getString("username");
                    String phoneNum = rs.getString("phoneNum");
                    String email = rs.getString("email");
                    String password = rs.getString("password");
                    String image = rs.getString("image");
                    User u = new User(userID,username, firstName, lastName, phoneNum, email, password, image);
                    users.add(u);
                }
            }
            sqlConn.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return users;
    }

    public User getUser(String user) {
        connect();
        String selectQuery = "select * from user where username = ?";
        User u = null;
        try (PreparedStatement ps = sqlConn.prepareStatement(selectQuery)) {
            ps.setString(1, user);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String userID = rs.getString("userID");
                    String firstName = rs.getString("firstName");
                    String lastName = rs.getString("lastName");
                    String username = rs.getString("username");
                    String phoneNum = rs.getString("phoneNum");
                    String email = rs.getString("email");
                    String password = rs.getString("password");
                    String image = rs.getString("image");
                    u = new User(userID,username, firstName, lastName, phoneNum, email, password, image);
                }
            }
            sqlConn.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
    }

    public boolean isPhoneNumberUnique(String phoneNumber){
        connect();
        boolean isUnique = true;
        String selectQuery = "select count(*) from user where phoneNum = ?";
        try (PreparedStatement stmt = sqlConn.prepareStatement(selectQuery)) {
            stmt.setString(1, phoneNumber);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    if (count > 0) {
                        isUnique = false;
                    }
                }
            }
            sqlConn.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isUnique;
    }
    
    
    public boolean checkPassword(String username, String password){
        connect();
        boolean isEqual = false;
        String selectQuery = "select password from user where username = ?";
        try(PreparedStatement pst = sqlConn.prepareStatement(selectQuery)){
            pst.setString(1, username);
            try(ResultSet rs = pst.executeQuery()){
                if(rs.next()){
                    if(password.equals(rs.getString("password"))){
                        isEqual = true;
                    }
                }
            }
            sqlConn.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isEqual;
    }
    
    public void updatePassword(String username, String password){
        connect();
        String updateQuery = "Update user set password = ? where username = ?";
        try(PreparedStatement pst = sqlConn.prepareStatement(updateQuery)){
            pst.setString(1, hashPassword(password));
            pst.setString(2,username);
            pst.executeUpdate();
            sqlConn.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
