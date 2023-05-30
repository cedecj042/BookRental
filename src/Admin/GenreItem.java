/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Admin;

import Classes.Genre;
import CustomizedClasses.CheckBoxCustom;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import javax.swing.ImageIcon;

/**
 *
 * @author Aesthetics
 */
public class GenreItem extends javax.swing.JPanel {

    Genre genre;

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
    
    public CheckBoxCustom getCheckbox(){
        return this.checkbox;
    }
    
    public GenreItem(Genre genre) {
        initComponents();
        setOpaque(false);
        this.genre = genre;
        checkbox.setText(genre.getGenreName());
    }
    
    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 0, 0));
        g2.dispose();
        super.paintComponent(grphcs);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        checkbox = new CustomizedClasses.CheckBoxCustom();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        checkbox.setBackground(new java.awt.Color(0, 156, 220));
        checkbox.setBorder(null);
        checkbox.setText("Horror");
        checkbox.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        checkbox.setIconTextGap(8);
        checkbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkboxActionPerformed(evt);
            }
        });
        add(checkbox, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 310, 50));
    }// </editor-fold>//GEN-END:initComponents

    private void checkboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkboxActionPerformed
        // TODO add your handling code here:
        if(this.checkbox.isSelected()){
            this.setBackground(new Color(246, 252, 255));
        }else {
            this.setBackground(new Color(255, 255, 255));
        }
        
    }//GEN-LAST:event_checkboxActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private CustomizedClasses.CheckBoxCustom checkbox;
    // End of variables declaration//GEN-END:variables
}
