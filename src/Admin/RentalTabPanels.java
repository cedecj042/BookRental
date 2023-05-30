/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Admin;

import Classes.Rental;
import Classes.RentalDB;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author Aesthetics
 */
public class RentalTabPanels extends javax.swing.JPanel {

    RentalDB rdb;
    ArrayList<RentalPanel> rentalPanels = new ArrayList<>();
    ArrayList<Rental> rentals = new ArrayList<>();
    public RentalTabPanels(String tabname,String month,String year) {
        initComponents();
        rdb = new RentalDB();
        this.setName("  " + tabname + "  ");
        rentals = rdb.getFilteredRental(tabname,month,year);
        for(int i =0; i <rentals.size();i++){
            RentalPanel rp = new RentalPanel(rentals.get(i));
            if(i%2 ==0){
                rp.setBackground(new Color(252, 252, 252));
            }
            rentalPanels.add(rp);
        }
        int num = loadPanels(panels,rentalPanels);
        setPanelHeight(800,calculatePanelHeight(num),panels);
        if (rentals.isEmpty()) {
            NoData nd = new NoData();
            panels.add(nd);
            setPanelHeight(800, 400, panels);
        }
    }
    
    
    public void setPanelHeight(int width, int height, JPanel panel) {
        panel.setPreferredSize(new Dimension(width, height));
        panel.setSize(new Dimension(width, height));
        panel.setMinimumSize(new Dimension(width, height));
        panel.setMaximumSize(new Dimension(width, height));
    }
    
    public int calculatePanelHeight(int num) {
        return num * 90;
    }

    public int loadPanels(Container container, ArrayList<? extends JPanel> panels) {
        int count = 0;
        container.removeAll();
        for (JPanel panel : panels) {
            container.add(panel);
            count++;
        }
        return count;
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        header = new javax.swing.JPanel();
        userID = new javax.swing.JLabel();
        email = new javax.swing.JLabel();
        phoneNum = new javax.swing.JLabel();
        username1 = new javax.swing.JLabel();
        username3 = new javax.swing.JLabel();
        email2 = new javax.swing.JLabel();
        email3 = new javax.swing.JLabel();
        panels = new javax.swing.JPanel();

        setBackground(new java.awt.Color(249, 249, 249));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        header.setBackground(new java.awt.Color(255, 255, 255));
        header.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        userID.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        userID.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        userID.setText("Rental ID");
        header.add(userID, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 80, 60));

        email.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        email.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        email.setText("Return Date");
        header.add(email, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 0, 140, 60));

        phoneNum.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        phoneNum.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        phoneNum.setText("Rental Status");
        header.add(phoneNum, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 0, 110, 60));

        username1.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        username1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        username1.setText("User ID");
        header.add(username1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 0, 90, 60));

        username3.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        username3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        username3.setText("Book ID");
        header.add(username3, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 0, 90, 60));

        email2.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        email2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        email2.setText("Start Date");
        header.add(email2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 0, 140, 60));

        email3.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        email3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        email3.setText("End Date");
        header.add(email3, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 0, 130, 60));

        add(header, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 6, 800, 60));

        panels.setBackground(new java.awt.Color(249, 249, 249));
        panels.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 1, 1));
        add(panels, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 800, -1));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel email;
    private javax.swing.JLabel email2;
    private javax.swing.JLabel email3;
    private javax.swing.JPanel header;
    private javax.swing.JPanel panels;
    private javax.swing.JLabel phoneNum;
    private javax.swing.JLabel userID;
    private javax.swing.JLabel username1;
    private javax.swing.JLabel username3;
    // End of variables declaration//GEN-END:variables
}
