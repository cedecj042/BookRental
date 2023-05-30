/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import java.sql.*;
import java.util.logging.*;
import javax.swing.ImageIcon;

/**
 *
 * @author Aesthetics
 */
public class DBConnector {

    String urlConn = "jdbc:mysql://localhost:3306/bookrental";
    String userConn = "root";
    String passConn = "Kraken3451%";

    public Connection sqlConn;

    public DBConnector() {}
    protected ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = this.getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
    public void connect() {
        try {
            //trying to connect to themysql database
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.sqlConn = DriverManager.getConnection(urlConn, userConn, passConn);
//            System.out.println("Connected");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Cannot log-in");
        }
    }
}
