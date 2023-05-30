/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import java.sql.Timestamp;

/**
 *
 * @author Aesthetics
 */
public class Admin{
    private String firstName, lastName, username, password;
    private int adminID;
    Timestamp createdAt, lastLoginAt;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAdminID() {
        return adminID;
    }

    public void setAdminID(int adminID) {
        this.adminID = adminID;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getLastLoginAt() {
        return lastLoginAt;
    }

    public void setLastLoginAt(Timestamp lastLoginAt) {
        this.lastLoginAt = lastLoginAt;
    }

    public Admin(int adminID, String username, String firstName, String lastName, String password, Timestamp createdAt, Timestamp lastLoginAt) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.adminID = adminID;
        this.createdAt = createdAt;
        this.lastLoginAt = lastLoginAt;
    }


    
    public Admin(){};
    
    
}
