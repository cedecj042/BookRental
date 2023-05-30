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
public class Penalty {
    private String penaltyID ,userID, rentalID, transactionID,penaltyType;
    private Double penaltyAmount;
    private Timestamp penaltyDate;
    private String status;

    public String getPenaltyID() {
        return penaltyID;
    }

    public void setPenaltyID(String penaltyID) {
        this.penaltyID = penaltyID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getRentalID() {
        return rentalID;
    }

    public void setRentalID(String rentalID) {
        this.rentalID = rentalID;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public String getPenaltyType() {
        return penaltyType;
    }

    public void setPenaltyType(String penaltyType) {
        this.penaltyType = penaltyType;
    }

    public Double getPenaltyAmount() {
        return penaltyAmount;
    }

    public void setPenaltyAmount(Double penaltyAmount) {
        this.penaltyAmount = penaltyAmount;
    }

    public Timestamp getPenaltyDate() {
        return penaltyDate;
    }

    public void setPenaltyDate(Timestamp penaltyDate) {
        this.penaltyDate = penaltyDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Penalty(String penaltyID, String userID, String rentalID, String transactionID, String penaltyType, Double penaltyAmount, Timestamp penaltyDate, String status) {
        this.penaltyID = penaltyID;
        this.userID = userID;
        this.rentalID = rentalID;
        this.transactionID = transactionID;
        this.penaltyType = penaltyType;
        this.penaltyAmount = penaltyAmount;
        this.penaltyDate = penaltyDate;
        this.status = status;
    }
    
    
}
