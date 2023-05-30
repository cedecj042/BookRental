/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Aesthetics
 */
public class GenreDB extends DBConnector {

    public GenreDB() {
    }

    public ArrayList<GenrePanels> getSubGenre() {
        connect();
        ArrayList<GenrePanels> genre = new ArrayList<>();

        String selectQuery = "select * from genre";
        try (PreparedStatement pst = sqlConn.prepareStatement(selectQuery)) {
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    String id = rs.getString("genreID");
                    String name = rs.getString("genreName");
                    String cover = rs.getString("genreCover");
                    String mainGenre = rs.getString("mainGenreID");
                    GenrePanels subgenre = new GenrePanels(id, name, cover, mainGenre);
                    genre.add(subgenre);
                }
                sqlConn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return genre;
    }

    public ArrayList<Genre> getAllGenre() {
        connect();
        ArrayList<Genre> genres = new ArrayList<>();

        String selectQuery = "select * from genre";
        try (PreparedStatement pst = sqlConn.prepareStatement(selectQuery)) {
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    String id = rs.getString("genreID");
                    String name = rs.getString("genreName");
                    String cover = rs.getString("genreCover");
                    String mainGenre = rs.getString("mainGenreID");
                    Genre subgenre = new Genre(id, name, cover, mainGenre);
                    genres.add(subgenre);
                }
                sqlConn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return genres;
    }

    public ArrayList<Genre> getAllSubGenre(String maingenreID) {
        connect();
        ArrayList<Genre> genres = new ArrayList<>();

        String selectQuery = "select * from genre where mainGenreID = ?";
        try (PreparedStatement pst = sqlConn.prepareStatement(selectQuery)) {
            pst.setString(1, maingenreID);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    String id = rs.getString("genreID");
                    String name = rs.getString("genreName");
                    String cover = rs.getString("genreCover");
                    String mainGenre = rs.getString("mainGenreID");
                    Genre subgenre = new Genre(id, name, cover, mainGenre);
                    genres.add(subgenre);
                }
                sqlConn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return genres;
    }

    public int countSubGenreBooks(String genreID) {
        connect();
        String countQuery = "select count(*) 'Total Books' from bookGenre where genreID = ?";
        int count = 0;
        try (PreparedStatement pst = sqlConn.prepareStatement(countQuery)) {
            pst.setString(1, genreID);
            try (ResultSet rs = pst.executeQuery()) {
                rs.next();
                count = rs.getInt("Total Books");
                rs.close();
                pst.close();
            }
            sqlConn.close();
        } catch (SQLException ex) {
            Logger.getLogger(GenreDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    public boolean checkGenre(String genreID, String genreName) {
        connect();
        String countQuery = "select * from genre where genreID = ? or genreName = ?";
        boolean exist = false;
        try (PreparedStatement pst = sqlConn.prepareStatement(countQuery)) {
            pst.setString(1, genreID);
            pst.setString(2, genreName);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    exist = true;
                }
            }
            sqlConn.close();
        } catch (SQLException ex) {
            Logger.getLogger(GenreDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return exist;
    }

    public void createGenre(String genreID, String genreName,String maingenreid,String imagePath) {
        connect();
        String countQuery = "insert into genre (genreID,genreName,mainGenreID,genreCover)  (?,?,?,?)";
        try (PreparedStatement pst = sqlConn.prepareStatement(countQuery)) {
            pst.setString(1, genreID);
            pst.setString(2, genreName);
            pst.setString(3,maingenreid);
            pst.setString(4, imagePath);
            pst.executeUpdate();
            sqlConn.close();
        } catch (SQLException ex) {
            Logger.getLogger(GenreDB.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
