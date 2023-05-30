/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

/**
 *
 * @author Aesthetics
 */
public class AdminSession {

    private static AdminSession instance = null;
    private Admin admin;

    private AdminSession() {
        // private constructor to prevent external instantiation
    }

    public static AdminSession getInstance() {
        if (instance == null) {
            instance = new AdminSession();
        }
        return instance;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void clearSession() {
        admin = null;
    }
}
