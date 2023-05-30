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
public class Book {

    private String bookID, title, authorLastName, authorFirstName, publicationDate, isbn, genreID;
    private float price, rate;
    private ImageIcon image;
    private boolean faveStatus;
    private int inventory;
    private String imagePath;

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorLastName() {
        return authorLastName;
    }

    public void setAuthorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
    }

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public void setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public ImageIcon getImage() {
        return image;
    }

    public void setImage(ImageIcon image) {
        this.image = image;
    }

    public boolean isFaveStatus() {
        return faveStatus;
    }

    public void setFaveStatus(boolean faveStatus) {
        this.faveStatus = faveStatus;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public String getGenreID() {
        return genreID;
    }

    public void setGenreID(String genreID) {
        this.genreID = genreID;
    }

    public Book getbook() {
        return Book.this;
    }

    public Book() {
    }

    public Book(String bookID, String title, String authorFirstName, String authorLastName, String publicationDate, String isbn, ImageIcon image, float price, float rate, boolean faveStatus, int inventory) {
        this.bookID = bookID;
        this.title = title;
        this.authorLastName = authorLastName;
        this.authorFirstName = authorFirstName;
        this.publicationDate = publicationDate;
        this.isbn = isbn;
        this.price = price;
        this.rate = rate;
        this.image = image;
        this.faveStatus = faveStatus;
        this.inventory = inventory;
    }

    public Book(String bookID, String title, String authorFirstName, String authorLastName, String publicationDate, int inventory, float rate, String isbn, float price, ImageIcon image) {
        this.bookID = bookID;
        this.title = title;
        this.authorLastName = authorLastName;
        this.authorFirstName = authorFirstName;
        this.publicationDate = publicationDate;
        this.isbn = isbn;
        this.price = price;
        this.rate = rate;
        this.image = image;
        this.inventory = inventory;
    }

    public Book(String bookID, String title, String authorFirstName, String authorLastName, String publicationDate, int inventory, float rate, String isbn, float price, String imagePath) {
        this.bookID = bookID;
        this.title = title;
        this.authorLastName = authorLastName;
        this.authorFirstName = authorFirstName;
        this.publicationDate = publicationDate;
        this.isbn = isbn;
        this.price = price;
        this.rate = rate;
        this.imagePath = imagePath;
        this.image = createImageIcon(this.imagePath);
        this.inventory = inventory;
    }

    public Book(String title, String authorFirstName, String authorLastName, String publicationDate, int inventory, float rate, String isbn, float price, String imagePath) {
        this.title = title;
        this.authorLastName = authorLastName;
        this.authorFirstName = authorFirstName;
        this.publicationDate = publicationDate;
        this.isbn = isbn;
        this.price = price;
        this.rate = rate;
        this.imagePath = imagePath;
        this.image = createImageIcon(this.imagePath);
        this.inventory = inventory;
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
