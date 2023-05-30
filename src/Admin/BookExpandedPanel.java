/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Admin;

import Classes.Book;
import Classes.BookDB;
import CustomizedClasses.GlassPanePopup;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import javax.swing.ImageIcon;

/**
 *
 * @author Aesthetics
 */
public class BookExpandedPanel extends javax.swing.JPanel {

    AdminExecutable executable;
    BookDB bdb;
    Book book;
    AdminHome home;
    public BookExpandedPanel(Book book,AdminHome home, AdminExecutable executable) {
        initComponents();
        setOpaque(false);
        bdb = new BookDB();
        this.book = book;
        this.home = home;
        this.executable = executable;
        bookID.setText("Book #" + book.getBookID());
        isbn.setText(book.getIsbn());
        String bookRate = "../img/images/" + book.getRate() + ".png";
        ratings.setIcon(createImageIcon(bookRate));        
        bookimage.setIcon(book.getImage());
        author.setText("by " + book.getAuthorFirstName() + " " + book.getAuthorLastName());
        title.setText(book.getTitle());
        pubDate.setText(book.getPublicationDate());
        price.setText("Php " + Float.toString(book.getPrice()) + "0");
        inventory.setText(Integer.toString(book.getInventory()));
        
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 25, 25));
        g2.dispose();
        super.paintComponent(grphcs);
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPublicationDate = new javax.swing.JLabel();
        bookID = new javax.swing.JLabel();
        pubDate = new javax.swing.JLabel();
        jisbn = new javax.swing.JLabel();
        isbn = new javax.swing.JLabel();
        author = new javax.swing.JLabel();
        jRatings = new javax.swing.JLabel();
        ratings = new javax.swing.JLabel();
        jBook = new javax.swing.JLabel();
        jDetails = new javax.swing.JLabel();
        back = new javax.swing.JButton();
        jInventory = new javax.swing.JLabel();
        inventory = new javax.swing.JLabel();
        by = new javax.swing.JLabel();
        bookimage = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        title = new javax.swing.JTextArea();
        jPrice = new javax.swing.JLabel();
        price = new javax.swing.JLabel();
        edit = new CustomizedClasses.RoundedBorderButton();
        remove = new CustomizedClasses.RoundedBorderButton();
        jLabel1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPublicationDate.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jPublicationDate.setForeground(new java.awt.Color(105, 105, 105));
        jPublicationDate.setText("Publication Date ");
        add(jPublicationDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 390, 120, 30));

        bookID.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        bookID.setForeground(new java.awt.Color(52, 52, 52));
        bookID.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        bookID.setText("Pickup");
        add(bookID, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 330, 150, 20));

        pubDate.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        pubDate.setForeground(new java.awt.Color(52, 52, 52));
        pubDate.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        pubDate.setText("Landmark:");
        add(pubDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 390, 240, 30));

        jisbn.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jisbn.setForeground(new java.awt.Color(105, 105, 105));
        jisbn.setText("ISBN  ");
        add(jisbn, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 420, 120, 30));

        isbn.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        isbn.setForeground(new java.awt.Color(52, 52, 52));
        isbn.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        isbn.setText("ISBN");
        add(isbn, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 420, 240, 30));

        author.setFont(new java.awt.Font("Roboto Medium", 0, 16)); // NOI18N
        author.setForeground(new java.awt.Color(82, 82, 82));
        author.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        author.setText("Location");
        add(author, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 360, 140, 20));

        jRatings.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jRatings.setForeground(new java.awt.Color(105, 105, 105));
        jRatings.setText("Ratings ");
        add(jRatings, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 450, 120, 30));

        ratings.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        ratings.setForeground(new java.awt.Color(104, 104, 104));
        ratings.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        ratings.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/images/4.0.png"))); // NOI18N
        add(ratings, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 450, 240, 30));

        jBook.setFont(new java.awt.Font("Poppins", 1, 24)); // NOI18N
        jBook.setForeground(new java.awt.Color(0, 156, 220));
        jBook.setText("Book");
        add(jBook, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, 80, 40));

        jDetails.setFont(new java.awt.Font("Poppins", 1, 24)); // NOI18N
        jDetails.setText("Details");
        add(jDetails, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 30, 140, 40));

        back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/images/backsmall.png"))); // NOI18N
        back.setBorderPainted(false);
        back.setContentAreaFilled(false);
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });
        add(back, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 30, 40));

        jInventory.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jInventory.setForeground(new java.awt.Color(105, 105, 105));
        jInventory.setText("Inventory ");
        add(jInventory, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 480, 120, 30));

        inventory.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        inventory.setForeground(new java.awt.Color(52, 52, 52));
        inventory.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        inventory.setText("1");
        add(inventory, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 480, 240, 30));

        by.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        by.setForeground(new java.awt.Color(105, 105, 105));
        by.setText("by");
        add(by, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 360, 20, 20));

        bookimage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        add(bookimage, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 80, 190, 190));

        jScrollPane2.setBorder(null);
        jScrollPane2.setInheritsPopupMenu(true);

        title.setColumns(20);
        title.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        title.setForeground(new java.awt.Color(52, 52, 52));
        title.setLineWrap(true);
        title.setRows(2);
        title.setText("Harry Potter and the Sorceres Stone\ndsadsad");
        title.setToolTipText("");
        title.setWrapStyleWord(true);
        title.setBorder(null);
        title.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jScrollPane2.setViewportView(title);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 280, 340, 50));

        jPrice.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jPrice.setForeground(new java.awt.Color(105, 105, 105));
        jPrice.setText("Price ");
        add(jPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 510, 120, 40));

        price.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        price.setForeground(new java.awt.Color(52, 52, 52));
        price.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        price.setText("1");
        add(price, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 510, 240, 40));

        edit.setBackground(new java.awt.Color(251, 253, 255));
        edit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/images/edit.png"))); // NOI18N
        edit.setText("Edit");
        edit.setFont(new java.awt.Font("Roboto Medium", 0, 16)); // NOI18N
        edit.setIconTextGap(8);
        edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editActionPerformed(evt);
            }
        });
        add(edit, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 560, 370, 50));

        remove.setBackground(new java.awt.Color(254, 254, 254));
        remove.setForeground(new java.awt.Color(82, 82, 82));
        remove.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/images/remove.png"))); // NOI18N
        remove.setText("Remove");
        remove.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        remove.setIconTextGap(8);
        remove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeActionPerformed(evt);
            }
        });
        add(remove, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 620, 370, 50));
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 490, 710));
    }// </editor-fold>//GEN-END:initComponents

    private void editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editActionPerformed
        GlassPanePopup.showPopup(new EditBook(this.book,this.home,this.executable));
    }//GEN-LAST:event_editActionPerformed

    private void removeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeActionPerformed
        
        GlassPanePopup.showPopup(new ConfirmationDialog(this.book.getBookID(),this.executable));
    }//GEN-LAST:event_removeActionPerformed

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        // TODO add your handling code here:
        GlassPanePopup.closePopupLast();
    }//GEN-LAST:event_backActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel author;
    private javax.swing.JButton back;
    private javax.swing.JLabel bookID;
    private javax.swing.JLabel bookimage;
    private javax.swing.JLabel by;
    private CustomizedClasses.RoundedBorderButton edit;
    private javax.swing.JLabel inventory;
    private javax.swing.JLabel isbn;
    private javax.swing.JLabel jBook;
    private javax.swing.JLabel jDetails;
    private javax.swing.JLabel jInventory;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jPrice;
    private javax.swing.JLabel jPublicationDate;
    private javax.swing.JLabel jRatings;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel jisbn;
    private javax.swing.JLabel price;
    private javax.swing.JLabel pubDate;
    private javax.swing.JLabel ratings;
    private CustomizedClasses.RoundedBorderButton remove;
    private javax.swing.JTextArea title;
    // End of variables declaration//GEN-END:variables
}
