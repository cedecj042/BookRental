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
public class Transaction {
    
    private String transactionID;
    private String userID;
    private Timestamp transactionDate;
    private double amountToPay,amountPaid;
    private String paymentMethod,transactionStatus;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public Timestamp getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Timestamp transactionDate) {
        this.transactionDate = transactionDate;
    }

    public double getAmountToPay() {
        return amountToPay;
    }

    public void setAmountToPay(double amountToPay) {
        this.amountToPay = amountToPay;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public Transaction(String transactionID,String userID, String method, double amountToPay, double amountPaid, Timestamp transactionDate,String transactionStatus) {
        this.transactionID = transactionID;
        this.userID = userID;
        this.transactionDate = transactionDate;
        this.amountToPay = amountToPay;
        this.amountPaid = amountPaid;
        this.paymentMethod = method;
        this.transactionStatus = transactionStatus;
    }
    
    
 
}
