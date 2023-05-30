/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.sql.*;

/**
 *
 * @author Aesthetics
 */
public class MainGenreDB extends DBConnector {

    public MainGenreDB() {
    }

    public ArrayList<MainGenrePanels> getMainMenu() {
        ArrayList<MainGenrePanels> maingenres = new ArrayList<>();
        connect();
        String selectQuery = "select * from mainGenre";
        try (PreparedStatement pst = sqlConn.prepareStatement(selectQuery)) {
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    String id = rs.getString("mainGenreID");
                    String name = rs.getString("mainGenreName");
                    String cover = rs.getString("mainGenreCover");
                    MainGenrePanels maingenre = new MainGenrePanels(id, name, createImageIcon(cover));
                    maingenres.add(maingenre);
                }
            }
            sqlConn.close();
        } catch (SQLException ex) {
            Logger.getLogger(BookDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return maingenres;
    }
    
    public ArrayList<MainGenre> getAllGenre(){
         ArrayList<MainGenre> maingenres = new ArrayList<>();
         connect();
        String selectQuery = "select * from mainGenre";
        try (PreparedStatement pst = sqlConn.prepareStatement(selectQuery)) {
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    String id = rs.getString("mainGenreID");
                    String name = rs.getString("mainGenreName");
                    String cover = rs.getString("mainGenreCover");
                    MainGenre maingenre = new MainGenre(id, name, createImageIcon(cover));
                    maingenres.add(maingenre);
                }
            }
            sqlConn.close();
        } catch (SQLException ex) {
            Logger.getLogger(BookDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return maingenres;
         
    }
}
