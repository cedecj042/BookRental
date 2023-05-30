/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Aesthetics
 */
public class BookDB extends DBConnector {

    FavoriteDB fdb = new FavoriteDB();

    public BookDB() {
        super();
    }

    public String createBook(Book book) {
        connect();
        String generatedID = null;
        String insertFirst = "insert into book (bookID, bookName, authorFirstName, authorLastName, publicationDate, inventory, rate, isbn, price, bookImage) values (generateBookID(), ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String selectQuery = "select max(bookID) from book";
        try (PreparedStatement pst = sqlConn.prepareStatement(insertFirst, Statement.RETURN_GENERATED_KEYS)) {
            pst.setString(1, book.getTitle());
            pst.setString(2, book.getAuthorFirstName());
            pst.setString(3, book.getAuthorLastName());
            pst.setString(4, book.getPublicationDate());
            pst.setInt(5, book.getInventory());
            pst.setFloat(6, book.getRate());
            pst.setString(7, book.getIsbn());
            pst.setFloat(8, book.getPrice());
            pst.setString(9, book.getImagePath());
            System.out.println(book.getImagePath());
            pst.executeUpdate();
            try (PreparedStatement selectPst = sqlConn.prepareStatement(selectQuery)) {
                try (ResultSet rs = selectPst.executeQuery()) {
                    if (rs.next()) {
                        generatedID = rs.getString(1);
                    }
                }
            }
            sqlConn.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error adding book: " + ex.getMessage());
            JOptionPane.showMessageDialog(null, ex, "Try Again", JOptionPane.ERROR_MESSAGE);
        }
        return generatedID;
    }

    public int countTotalBook() {
        connect();
        int count = 0;
        String selectQuery = "select count(*) from book";
        try (PreparedStatement pst = sqlConn.prepareStatement(selectQuery)) {
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    count = rs.getInt(1);
                }
            }
            sqlConn.close();
        } catch (SQLException ex) {
            Logger.getLogger(BookDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    public BookPanels getBook(String bookID) {
        connect();
        String selectQuery = "select * from book where bookID = ?";
        BookPanels book = null;
        try (PreparedStatement pst = sqlConn.prepareStatement(selectQuery)) {
            pst.setString(1, bookID);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    String id = rs.getString("bookID");
                    String bookTitle = rs.getString("bookName");
                    String authorFirstName = rs.getString("authorFirstName");
                    String authorLastName = rs.getString("authorLastName");
                    String date = rs.getString("publicationDate");
                    int inventory = rs.getInt("inventory");
                    float rate = rs.getFloat("rate");
                    String isbn = rs.getString("isbn");
                    float price = rs.getFloat("price");
                    String imagePath = rs.getString("bookImage");
                    book = new BookPanels(id, bookTitle, authorFirstName, authorLastName, date, inventory, rate, isbn, price, imagePath);
                }
            }
            sqlConn.close();
        } catch (SQLException ex) {
            Logger.getLogger(BookDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return book;
    }

    public ArrayList<Book> getAllBooks() {
        connect();
        ArrayList<Book> books = new ArrayList<>();
        String selectQuery = "select * from book";
        Book book;
        try (PreparedStatement pst = sqlConn.prepareStatement(selectQuery)) {
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    String id = rs.getString("bookID");
                    String bookTitle = rs.getString("bookName");
                    String authorFirstName = rs.getString("authorFirstName");
                    String authorLastName = rs.getString("authorLastName");
                    String date = rs.getString("publicationDate");
                    int inventory = rs.getInt("inventory");
                    float rate = rs.getFloat("rate");
                    String isbn = rs.getString("isbn");
                    float price = rs.getFloat("price");
                    String image = rs.getString("bookImage");

                    book = new Book(id, bookTitle, authorFirstName, authorLastName, date, inventory, rate, isbn, price, image);
                    books.add(book);
                }
            }
            sqlConn.close();
        } catch (SQLException ex) {
            Logger.getLogger(BookDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return books;
    }

    public ArrayList<Book> getGenreBooks(String GenreID) {
        connect();
        ArrayList<Book> genrebooks = new ArrayList<>();
        String selectQuery = "select * from bookGenre bg join book b on bg.bookID = b.bookID  where genreID = ?";
        Book book;
        try (PreparedStatement pst = sqlConn.prepareStatement(selectQuery)) {
            pst.setString(1, GenreID);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    String id = rs.getString("bookID");
                    String bookTitle = rs.getString("bookName");
                    String authorFirstName = rs.getString("authorFirstName");
                    String authorLastName = rs.getString("authorLastName");
                    String date = rs.getString("publicationDate");
                    int inventory = rs.getInt("inventory");
                    float rate = rs.getFloat("rate");
                    String isbn = rs.getString("isbn");
                    float price = rs.getFloat("price");
                    String image = rs.getString("bookImage");
                    book = new Book(id, bookTitle, authorFirstName, authorLastName, date, inventory, rate, isbn, price, image);
                    genrebooks.add(book);
                }
            }
            sqlConn.close();
        } catch (SQLException ex) {
            Logger.getLogger(BookDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return genrebooks;
    }

    public String getGenreId(String bookID) {
        connect();
        String genreID = null;
        String selectQuery = "select * from bookGenre bg join book b on b.bookID = bg.bookID where b.bookID = ?;";
        try (PreparedStatement pst = sqlConn.prepareStatement(selectQuery)) {
            pst.setString(1, bookID);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    genreID = rs.getString("genreID");
                }
            }
            sqlConn.close();
        } catch (SQLException ex) {
            Logger.getLogger(BookDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return genreID;
    }

    public void removeBookInventory(String bookID) {
        connect();
        String selectQuery = "update book set inventory = inventory - 1 where bookID = ?";
        try (PreparedStatement pst = sqlConn.prepareStatement(selectQuery)) {
            pst.setString(1, bookID);
            pst.executeUpdate();
            sqlConn.close();
        } catch (SQLException ex) {
            Logger.getLogger(BookDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addBookInventory(String bookID) {
        connect();
        String selectQuery = "update book set inventory = inventory + 1 where bookID = ?";
        try (PreparedStatement pst = sqlConn.prepareStatement(selectQuery)) {
            pst.setString(1, bookID);
            pst.executeUpdate();
            sqlConn.close();
        } catch (SQLException ex) {
            Logger.getLogger(BookDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addBookGenre(String bookID, String genreID) {
        connect();
        String insertQuery = "insert into bookgenre values(?,?)";
        try (PreparedStatement pst = sqlConn.prepareStatement(insertQuery)) {
            pst.setString(1, bookID);
            pst.setString(2, genreID);
            pst.executeUpdate();
            sqlConn.close();
        } catch (SQLException ex) {
            Logger.getLogger(BookDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void removeBook(String bookID) {
        connect();
        String deleteBookGenreQuery = "delete from bookgenre where bookID = ?";
        String deleteBookQuery = "delete from book where bookID = ?";
        try (PreparedStatement pst = sqlConn.prepareStatement(deleteBookGenreQuery)) {
            pst.setString(1, bookID);
            pst.executeUpdate();
            try (PreparedStatement ps = sqlConn.prepareStatement(deleteBookQuery)) {
                ps.setString(1, bookID);
                ps.executeUpdate();
            }
            sqlConn.close();
        } catch (SQLException ex) {
            Logger.getLogger(BookDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<String> getBookGenres(String bookID) {
        connect();
        List<String> genres = new ArrayList<>();
        String selectQuery = "select * from bookGenre bg join book b on b.bookID = bg.bookID where b.bookID = ?";
        try (PreparedStatement pst = sqlConn.prepareStatement(selectQuery)) {
            pst.setString(1, bookID);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    String genreID = rs.getString("genreID");
                    genres.add(genreID);
                }
            }
            sqlConn.close();
        } catch (SQLException ex) {
            Logger.getLogger(BookDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return genres;
    }
    
    public void updateBook(Book book){
        connect();
        String updateQuery = "update book set bookName = ?, authorFirstName = ?,authorLastName = ?, publicationDate = ?, inventory = ? , rate = ?, isbn = ?, price = ? ,bookImage = ? where bookID = ?";
        try (PreparedStatement pst = sqlConn.prepareStatement(updateQuery)) {
            pst.setString(1, book.getTitle());
            pst.setString(2, book.getAuthorFirstName());
            pst.setString(3, book.getAuthorLastName());
            pst.setString(4,book.getPublicationDate());
            pst.setInt(5, book.getInventory());
            pst.setFloat(6, book.getRate());
            pst.setString(7,book.getIsbn());
            pst.setFloat(8, book.getPrice());
            pst.setString(9, book.getImagePath());
            pst.setString(10, book.getBookID());
            pst.executeUpdate();
            sqlConn.close();
        } catch (SQLException ex) {
            Logger.getLogger(BookDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void removeBookGenre(String bookID){
        connect();
        String deleteQuery = " delete from bookGenre  where bookID = ?";
        try (PreparedStatement pst = sqlConn.prepareStatement(deleteQuery)) {
            pst.setString(1, bookID);
            pst.executeUpdate();
            sqlConn.close();
        } catch (SQLException ex) {
            Logger.getLogger(BookDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
