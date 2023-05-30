/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author Aesthetics
 */
public class User {

    private String userID, firstName, lastName, username, phoneNum, email, password, address, birthdate;
    private char gender;
    private int rentedBooks;
    private String imagePath;
    private ImageIcon image;

    public String getUserID() {
        return userID;
    }

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

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public int getRentedBooks() {
        return rentedBooks;
    }

    public void setRentedBooks(int rentedBooks) {
        this.rentedBooks = rentedBooks;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
        this.image = createImageIcon(this.imagePath);
    }

    public ImageIcon getImage() {
        return image;
    }

    public void setImage(ImageIcon image) {
        this.image = image;
    }

    public User() {
    }

    public User(String Username, String firstName, String lastName, String phoneNum, String email, String password) {
        this.username = Username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phoneNum = phoneNum;
    }

    public User(String userID, String Username, String firstName, String lastName, String phoneNum, String email, String password,String imagePath) {
        this.userID = userID;
        this.username = Username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.imagePath = imagePath;
        this.image = createImageIcon(this.imagePath);
        this.password = password;
        this.phoneNum = phoneNum;
    }
    protected ImageIcon createImageIcon(String path) {
        try {
            Image image = ImageIO.read(new File(path));
            return new ImageIcon(image);
        } catch (IOException e) {
            System.err.println("Couldn't find file: " + path);
            e.printStackTrace();
            return null;
        }
    }
}
