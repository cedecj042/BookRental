/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

/**
 *
 * @author Aesthetics
 */
public class DeliveryAddress {
    
    private String userID, name,address,landmark,notes;
    private int addressID;

    public int getAddressID() {
        return this.addressID;
    }

    public void setAddressID(int addressID) {
        this.addressID = addressID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public DeliveryAddress(int addressID,String userID, String name, String address, String landmark, String notes) {
        this.addressID = addressID;
        this.name = name;
        this.userID = userID;
        this.address = address;
        this.landmark = landmark;
        this.notes = notes;
    }
    
    
    
}
