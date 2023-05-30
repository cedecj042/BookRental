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
public class Checkout {

    private String checkoutID, userID, bookID, transactionID, checkoutStatus;
    private Timestamp deliveryDateandTime, pickupDateandTime,checkoutDate;
    private double deliveryFee, libraryFee, totalAmount;
    private int days, addressID, quantity;

    public String getCheckoutID() {
        return checkoutID;
    }

    public void setCheckoutID(String checkoutID) {
        this.checkoutID = checkoutID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public String getCheckoutStatus() {
        return checkoutStatus;
    }

    public void setCheckoutStatus(String checkoutStatus) {
        this.checkoutStatus = checkoutStatus;
    }

    public Timestamp getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(Timestamp checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public Timestamp getDeliveryDateandTime() {
        return deliveryDateandTime;
    }

    public void setDeliveryDateandTime(Timestamp deliveryDateandTime) {
        this.deliveryDateandTime = deliveryDateandTime;
    }

    public Timestamp getPickupDateandTime() {
        return pickupDateandTime;
    }

    public void setPickupDateandTime(Timestamp pickupDateandTime) {
        this.pickupDateandTime = pickupDateandTime;
    }


    public double getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(double deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public double getLibraryFee() {
        return libraryFee;
    }

    public void setLibraryFee(double libraryFee) {
        this.libraryFee = libraryFee;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public int getAddressID() {
        return addressID;
    }

    public void setAddressID(int addressID) {
        this.addressID = addressID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Checkout(String checkoutID, String userID, String bookID, int addressID, int quantity, int days, double deliveryFee, double libraryFee, double totalAmount, Timestamp deliveryDateandTime, Timestamp pickupDateandTime, Timestamp checkoutDate, String checkoutStatus) {
        this.checkoutID = checkoutID;
        this.userID = userID;
        this.bookID = bookID;
        this.checkoutStatus = checkoutStatus;
        this.checkoutDate = checkoutDate;
        this.deliveryDateandTime = deliveryDateandTime;
        this.pickupDateandTime = pickupDateandTime;
        this.deliveryFee = deliveryFee;
        this.libraryFee = libraryFee;
        this.totalAmount = totalAmount;
        this.days = days;
        this.addressID = addressID;
        this.quantity = quantity;
    }
        public Checkout(String checkoutID, String userID, String bookID,String transactionID, int addressID, int quantity, int days, double deliveryFee, double libraryFee, double totalAmount, Timestamp deliveryDateandTime, Timestamp pickupDateandTime, Timestamp checkoutDate, String checkoutStatus) {
        this.checkoutID = checkoutID;
        this.userID = userID;
        this.bookID = bookID;
        this.checkoutStatus = checkoutStatus;
        this.transactionID = transactionID;
        this.checkoutDate = checkoutDate;
        this.deliveryDateandTime = deliveryDateandTime;
        this.pickupDateandTime = pickupDateandTime;
        this.deliveryFee = deliveryFee;
        this.libraryFee = libraryFee;
        this.totalAmount = totalAmount;
        this.days = days;
        this.addressID = addressID;
        this.quantity = quantity;
    }

    public Checkout(String checkoutID, String userID, String bookID, String checkoutStatus, Timestamp checkoutDate, double totalAmount, int days) {
        this.checkoutID = checkoutID;
        this.userID = userID;
        this.bookID = bookID;
        this.checkoutStatus = checkoutStatus;
        this.checkoutDate = checkoutDate;
        this.totalAmount = totalAmount;
        this.days = days;
    }

    public Checkout() {
    }

}
