/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

/**
 *
 * @author Aesthetics
 */
public class UserCart {
    private User user;
    private Book book;
    private int quantity,rentedDays;
    private double amount;
    
    public UserCart(Book book, int quantity,int rentedDays, double amount) {
        this.user = UserSession.getInstance().getUser();
        this.book = book;
        this.quantity = quantity;
        this.rentedDays = rentedDays;
        this.amount = amount;
    }

    public UserCart(Book book) {
        this.user = UserSession.getInstance().getUser();
        this.book = book;
    }
    public UserCart(){
        this.user = UserSession.getInstance().getUser();
    }

    public int getRentedDays() {
        return rentedDays;
    }

    public void setRentedDays(int rentedDays) {
        this.rentedDays = rentedDays;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
    
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    public Book getBook() {
        return book;
    }
    
    public void setBook(Book book) {
        this.book = book;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public UserCart getusercart(){
        return UserCart.this;
    }
    
}