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
public class Rental {

    private String rentalID, userID, bookID, checkoutID;
    private Timestamp startDate, endDate, returnDate;
    private String returnCondition, rentStatus;

    public Rental(String rentalID, String userID, String bookID, String checkoutID, Timestamp startDate, Timestamp endDate, Timestamp returnDate, String returnCondition, String rentStatus) {
        this.rentalID = rentalID;
        this.userID = userID;
        this.bookID = bookID;
        this.checkoutID = checkoutID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.returnDate = returnDate;
        this.returnCondition = returnCondition;
        this.rentStatus = rentStatus;
    }
    public Rental(String rentalID, String userID, String bookID, String checkoutID, Timestamp startDate, Timestamp endDate, String rentStatus) {
        this.rentalID = rentalID;
        this.userID = userID;
        this.bookID = bookID;
        this.checkoutID = checkoutID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.rentStatus = rentStatus;
    }
        public Rental(String userID, String bookID, String checkoutID, Timestamp startDate, Timestamp endDate, String rentStatus) {
        this.userID = userID;
        this.bookID = bookID;
        this.checkoutID = checkoutID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.rentStatus = rentStatus;
    }


    public String getRentalID() {
        return rentalID;
    }

    public void setRentalID(String rentalID) {
        this.rentalID = rentalID;
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

    public String getCheckoutID() {
        return checkoutID;
    }

    public void setCheckoutID(String checkoutID) {
        this.checkoutID = checkoutID;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public Timestamp getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Timestamp returnDate) {
        this.returnDate = returnDate;
    }

    public String getReturnCondition() {
        return returnCondition;
    }

    public void setReturnCondition(String returnCondition) {
        this.returnCondition = returnCondition;
    }

    public String getRentStatus() {
        return rentStatus;
    }

    public void setRentStatus(String rentStatus) {
        this.rentStatus = rentStatus;
    }

    

    public Rental() {
    }

}
