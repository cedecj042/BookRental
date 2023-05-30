/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Admin;

import Classes.MainGenre;
import CustomizedClasses.RadioButtonCustomized;

/**
 *
 * @author Aesthetics
 */
public class CategoryItem extends javax.swing.JPanel {

    MainGenre maingenre;

    public MainGenre getMaingenre() {
        return maingenre;
    }

    public void setMaingenre(MainGenre maingenre) {
        this.maingenre = maingenre;
    }

    public RadioButtonCustomized getRadio() {
        return radio;
    }

    public void setRadio(RadioButtonCustomized radio) {
        this.radio = radio;
    }
    
    
    public CategoryItem(MainGenre maingenre) {
        initComponents();
        this.maingenre = maingenre;
        this.radio.setText(maingenre.getMainGenreName());
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        radio = new CustomizedClasses.RadioButtonCustomized();

        setBackground(new java.awt.Color(255, 255, 255));

        radio.setBorder(null);
        radio.setText("Fiction");
        radio.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N
        radio.setIconTextGap(8);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(radio, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(radio, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private CustomizedClasses.RadioButtonCustomized radio;
    // End of variables declaration//GEN-END:variables
}
