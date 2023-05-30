/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Admin;

import Classes.Book;
import CustomizedClasses.GlassPanePopup;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import javax.swing.ImageIcon;
import static javax.swing.SwingConstants.CENTER;

/**
 *
 * @author Aesthetics
 */
public class BookPanel extends javax.swing.JPanel {

    Book book;
    AdminExecutable executable;
    AdminHome home;

    public BookPanel(Book book, AdminHome home, AdminExecutable executable) {
        initComponents();
        setProperties();
        this.book = book;
        this.home = home;
        this.executable = executable;
        image.setIcon(book.getImage());
        bookName.setText(book.getTitle());
        name.setText(book.getAuthorFirstName() + " " + book.getAuthorLastName());
        year.setText(book.getPublicationDate());
        if (book.getInventory() != 0) {
            status.setText("Available");
        } else {
            status.setText("Rented");
        }

        String bookRate = "../img/images/" + book.getRate() + ".png";
        rate.setIcon(createImageIcon(bookRate));
        ID.setText(String.valueOf(book.getBookID()));
    }

    public void setProperties() {
        image.setHorizontalAlignment(CENTER);
        image.setHorizontalTextPosition(CENTER);
        status.setHorizontalAlignment(CENTER);
        status.setHorizontalTextPosition(CENTER);
    }

    protected ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = this.getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.out.println("Couldn't find file: " + path);
            return null;
        }
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 20, 20));
        g2.dispose();
        super.paintComponent(grphcs);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        ID = new javax.swing.JLabel();
        bookName = new javax.swing.JLabel();
        author = new javax.swing.JLabel();
        name = new javax.swing.JLabel();
        rate = new javax.swing.JLabel();
        publicationyear = new javax.swing.JLabel();
        year = new javax.swing.JLabel();
        status = new javax.swing.JLabel();
        image = new javax.swing.JLabel();
        bg = new javax.swing.JLabel();

        setBackground(new java.awt.Color(252, 252, 252));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel1MousePressed(evt);
            }
        });
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 190, 290));

        ID.setFont(new java.awt.Font("Roboto Medium", 0, 10)); // NOI18N
        ID.setForeground(new java.awt.Color(52, 52, 52));
        add(ID, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 140, 10));

        bookName.setFont(new java.awt.Font("Roboto Medium", 0, 16)); // NOI18N
        bookName.setForeground(new java.awt.Color(52, 52, 52));
        add(bookName, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 150, 30));

        author.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        author.setForeground(new java.awt.Color(82, 82, 82));
        author.setText("Author: ");
        add(author, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 50, 20));

        name.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        name.setForeground(new java.awt.Color(52, 52, 52));
        name.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        add(name, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 200, 120, 20));

        rate.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        add(rate, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 90, 20));

        publicationyear.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        publicationyear.setForeground(new java.awt.Color(82, 82, 82));
        publicationyear.setText("Published Year:");
        add(publicationyear, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 90, 20));

        year.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        year.setForeground(new java.awt.Color(52, 52, 52));
        year.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        add(year, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 220, 70, 20));

        status.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        status.setForeground(new java.awt.Color(52, 52, 52));
        add(status, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 240, 60, 20));

        image.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        add(image, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 150, 150));

        bg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/images/smallbox.png"))); // NOI18N
        bg.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                bgMousePressed(evt);
            }
        });
        add(bg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 190, 290));
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MousePressed
        // TODO add your handling code here:
        GlassPanePopup.showPopup(new BookExpandedPanel(this.book, this.home, this.executable));
    }//GEN-LAST:event_jLabel1MousePressed

    private void bgMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bgMousePressed
        // TODO add your handling code here:
        GlassPanePopup.showPopup(new BookExpandedPanel(this.book, this.home, this.executable));
    }//GEN-LAST:event_bgMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ID;
    private javax.swing.JLabel author;
    private javax.swing.JLabel bg;
    private javax.swing.JLabel bookName;
    private javax.swing.JLabel image;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel name;
    private javax.swing.JLabel publicationyear;
    private javax.swing.JLabel rate;
    private javax.swing.JLabel status;
    private javax.swing.JLabel year;
    // End of variables declaration//GEN-END:variables
}
