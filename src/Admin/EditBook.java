/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Admin;

import Classes.Book;
import Classes.BookDB;
import Classes.Genre;
import Classes.GenreDB;
import CustomizedClasses.GlassPanePopup;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.border.Border;
import jnafilechooser.api.JnaFileChooser;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Aesthetics
 */
public class EditBook extends javax.swing.JPanel {

    Color mygrey = new Color(206, 206, 206);
    Color myblue = new Color(0, 145, 220);
    Color myred = new Color(255, 16, 16);

    AdminExecutable executable;
    AdminHome home;
    ArrayList<GenreItem> genreItems = new ArrayList<>();
    ArrayList<Genre> genres = new ArrayList<>();
    List<String> bookGenres = new ArrayList<>();
    GenreDB gdb;
    BookDB bdb;

    Book book;
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public EditBook(Book book, AdminHome home, AdminExecutable executable) {
        initComponents();
        setOpaque(false);
        this.executable = executable;
        gdb = new GenreDB();
        bdb = new BookDB();
        genres = gdb.getAllGenre();
        this.book = book;
        this.home = home;
        bookGenres = bdb.getBookGenres(book.getBookID());
        for (Genre genre : genres) {
            GenreItem gi = new GenreItem(genre);
            for (String id : bookGenres) {
                if (genre.getGenreID().equals(id)) {
                    gi.getCheckbox().setSelected(true);
                    gi.setBackground(new Color(246, 252, 255));
                }
            }
            genreItems.add(gi);
        }
        title.setText(book.getTitle());
        image.setText(book.getImagePath());
        Date pubdate;
        try {
            pubdate = dateFormat.parse(book.getPublicationDate());
            date.setValue(pubdate);
        } catch (ParseException ex) {
            Logger.getLogger(EditBook.class.getName()).log(Level.SEVERE, null, ex);
        }

        isbn.setText(book.getIsbn());
        firstname.setText(book.getAuthorFirstName());
        lastname.setText(book.getAuthorLastName());
        price.setValue(book.getPrice());
        inventory.setValue((int) book.getInventory());
        price.setForeground(Color.BLACK);
        date.setForeground(Color.BLACK);
        inventory.setForeground(Color.BLACK);
        rate.setForeground(Color.BLACK);
        rate.setValue((float) book.getRate());

        loadGenreItem(genreItems);
    }

    public void setCompoundBorder(JTextField textField, Color color) {
        Border insideBorder = BorderFactory.createEmptyBorder(5, 10, 5, 10);
        Border outsideBorder = BorderFactory.createLineBorder(color, 1, true);
        Border compoundBorder = BorderFactory.createCompoundBorder(outsideBorder, insideBorder);
        textField.setBorder(compoundBorder);
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 15, 15));
        g2.dispose();
        super.paintComponent(grphcs);
    }

    public void loadGenreItem(ArrayList<GenreItem> g) {
        GenrePanel.removeAll();
        for (int i = 0; i < g.size(); i++) {
            GenrePanel.add(g.get(i));
        }
    }

    protected ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = this.getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Add = new javax.swing.JLabel();
        Back = new javax.swing.JButton();
        Book = new javax.swing.JLabel();
        title = new javax.swing.JTextField();
        firstname = new javax.swing.JTextField();
        lastname = new javax.swing.JTextField();
        isbn = new javax.swing.JTextField();
        date = new javax.swing.JFormattedTextField();
        uploadFile = new CustomizedClasses.RoundedBorderButton();
        scrollPaneWin111 = new CustomizedClasses.ScrollPaneWin11();
        image = new javax.swing.JTextArea();
        jTitle = new javax.swing.JLabel();
        jLastName = new javax.swing.JLabel();
        jIsbn = new javax.swing.JLabel();
        jDate = new javax.swing.JLabel();
        jFirstName = new javax.swing.JLabel();
        jImage = new javax.swing.JLabel();
        Submit = new CustomizedClasses.RoundedBorderButton();
        jBookGenre = new javax.swing.JLabel();
        GenreScroll = new CustomizedClasses.ScrollPaneWin11();
        GenrePanel = new javax.swing.JPanel();
        jPrice = new javax.swing.JLabel();
        jTitle1 = new javax.swing.JLabel();
        price = new javax.swing.JFormattedTextField();
        rate = new CustomizedClasses.Spinner();
        jPrice1 = new javax.swing.JLabel();
        inventory = new javax.swing.JFormattedTextField();
        jDate1 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Add.setFont(new java.awt.Font("Poppins", 1, 24)); // NOI18N
        Add.setForeground(new java.awt.Color(1, 87, 220));
        Add.setText("Edit");
        add(Add, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, 50, 40));

        Back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/images/backsmall.png"))); // NOI18N
        Back.setBorderPainted(false);
        Back.setContentAreaFilled(false);
        Back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackActionPerformed(evt);
            }
        });
        add(Back, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 30, 40));

        Book.setFont(new java.awt.Font("Poppins", 1, 24)); // NOI18N
        Book.setText("Book");
        add(Book, new org.netbeans.lib.awtextra.AbsoluteConstraints(115, 30, 150, 40));

        title.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        title.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(206, 206, 206), 1, true), javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        add(title, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 140, 480, 50));

        firstname.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        firstname.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(206, 206, 206), 1, true), javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        add(firstname, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 410, 230, 50));

        lastname.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        lastname.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(206, 206, 206), 1, true), javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        add(lastname, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 410, 230, 50));

        isbn.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        isbn.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(206, 206, 206), 1, true), javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        add(isbn, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 500, 480, 50));

        date.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(206, 206, 206), 1, true), javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        date.setForeground(new java.awt.Color(206, 206, 206));
        date.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("y-MM-dd"))));
        date.setText("2000-12-31");
        date.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        date.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                dateFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                dateFocusLost(evt);
            }
        });
        date.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateActionPerformed(evt);
            }
        });
        add(date, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 320, 480, 50));

        uploadFile.setBackground(new java.awt.Color(232, 245, 252));
        uploadFile.setBorder(null);
        uploadFile.setForeground(new java.awt.Color(0, 156, 220));
        uploadFile.setText("Upload File");
        uploadFile.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        uploadFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uploadFileActionPerformed(evt);
            }
        });
        add(uploadFile, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 230, 120, 50));

        scrollPaneWin111.setBackground(new java.awt.Color(252, 252, 252));
        scrollPaneWin111.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(206, 206, 206), 1, true), javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        scrollPaneWin111.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        image.setEditable(false);
        image.setBackground(new java.awt.Color(252, 252, 252));
        image.setColumns(60);
        image.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        image.setLineWrap(true);
        image.setRows(1);
        image.setWrapStyleWord(true);
        image.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
        image.setRequestFocusEnabled(false);
        scrollPaneWin111.setViewportView(image);

        add(scrollPaneWin111, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 230, 350, 50));

        jTitle.setFont(new java.awt.Font("Poppins Medium", 0, 18)); // NOI18N
        jTitle.setForeground(new java.awt.Color(52, 52, 52));
        jTitle.setText("Book Details");
        add(jTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 80, 240, 30));

        jLastName.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLastName.setForeground(new java.awt.Color(82, 82, 82));
        jLastName.setText("Author Last Name");
        add(jLastName, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 380, 230, 30));

        jIsbn.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jIsbn.setForeground(new java.awt.Color(82, 82, 82));
        jIsbn.setText("ISBN");
        add(jIsbn, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 470, 240, 30));

        jDate.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jDate.setForeground(new java.awt.Color(82, 82, 82));
        jDate.setText("Publication Date");
        add(jDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 290, 240, 30));

        jFirstName.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jFirstName.setForeground(new java.awt.Color(82, 82, 82));
        jFirstName.setText("Author First Name");
        add(jFirstName, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 380, 230, 30));

        jImage.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jImage.setForeground(new java.awt.Color(82, 82, 82));
        jImage.setText("Book Image");
        add(jImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 200, 240, 30));

        Submit.setBackground(new java.awt.Color(1, 87, 220));
        Submit.setForeground(new java.awt.Color(255, 255, 255));
        Submit.setText("Save");
        Submit.setFont(new java.awt.Font("Poppins Medium", 0, 18)); // NOI18N
        Submit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SubmitActionPerformed(evt);
            }
        });
        add(Submit, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 490, 340, 60));

        jBookGenre.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jBookGenre.setForeground(new java.awt.Color(82, 82, 82));
        jBookGenre.setText("Genres");
        add(jBookGenre, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 280, 240, 30));

        GenreScroll.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(206, 206, 206), 1, true));
        GenreScroll.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        GenrePanel.setBackground(new java.awt.Color(255, 255, 255));
        GenrePanel.setLayout(new javax.swing.BoxLayout(GenrePanel, javax.swing.BoxLayout.Y_AXIS));
        GenreScroll.setViewportView(GenrePanel);

        add(GenreScroll, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 310, 340, 170));

        jPrice.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jPrice.setForeground(new java.awt.Color(82, 82, 82));
        jPrice.setText("Price");
        add(jPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 100, 100, 30));

        jTitle1.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jTitle1.setForeground(new java.awt.Color(82, 82, 82));
        jTitle1.setText("Title");
        add(jTitle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 110, 240, 30));

        price.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(206, 206, 206), 1, true), javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        price.setForeground(new java.awt.Color(206, 206, 206));
        price.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        price.setText("0.00");
        price.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        price.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                priceFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                priceFocusLost(evt);
            }
        });
        price.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                priceActionPerformed(evt);
            }
        });
        add(price, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 130, 340, 50));

        rate.setBorder(null);
        rate.setModel(new javax.swing.SpinnerNumberModel(Float.valueOf(0.0f), Float.valueOf(0.0f), Float.valueOf(5.0f), Float.valueOf(0.5f)));
        rate.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        rate.setLabelText("");
        add(rate, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 220, 160, -1));

        jPrice1.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jPrice1.setForeground(new java.awt.Color(82, 82, 82));
        jPrice1.setText("Rate");
        add(jPrice1, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 190, -1, 30));

        inventory.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(206, 206, 206), 1, true), javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        inventory.setForeground(new java.awt.Color(206, 206, 206));
        inventory.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        inventory.setText("0");
        inventory.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        inventory.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                inventoryFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                inventoryFocusLost(evt);
            }
        });
        add(inventory, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 220, 160, 50));

        jDate1.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jDate1.setForeground(new java.awt.Color(82, 82, 82));
        jDate1.setText("Inventory");
        add(jDate1, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 190, 140, 30));

        jLabel1.setBackground(new java.awt.Color(252, 252, 252));
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 620));

        jPanel1.setBackground(new java.awt.Color(253, 253, 253));
        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 0, 2, 620));
    }// </editor-fold>//GEN-END:initComponents

    private void BackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackActionPerformed
        // TODO add your handling code here:
        GlassPanePopup.closePopupLast();
    }//GEN-LAST:event_BackActionPerformed

    private void uploadFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uploadFileActionPerformed
        // TODO add your handling code here:
        JnaFileChooser ch = new JnaFileChooser();
        boolean action = ch.showOpenDialog(this.home);
        if (action) {
            File file = ch.getSelectedFile();
            String path = file.getAbsolutePath();
            String newPath = path.replaceAll("\\\\", "\\\\\\\\");

            image.setText(newPath);

        }

    }//GEN-LAST:event_uploadFileActionPerformed

    private void inventoryFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inventoryFocusGained
        if (inventory.getText().equals("0")) {
            inventory.setText("");
            inventory.setForeground(Color.BLACK);
        }
        setCompoundBorder(inventory, myblue);
    }//GEN-LAST:event_inventoryFocusGained

    private void priceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_priceActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_priceActionPerformed

    private void priceFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_priceFocusLost
        if (price.getText().isEmpty()) {
            price.setForeground(mygrey);
            price.setText("0.00");
        }
        setCompoundBorder(price, mygrey);
    }//GEN-LAST:event_priceFocusLost

    private void inventoryFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inventoryFocusLost
        if (inventory.getText().isEmpty()) {
            inventory.setForeground(mygrey);
            inventory.setText("0");
        }
        setCompoundBorder(inventory, mygrey);
    }//GEN-LAST:event_inventoryFocusLost

    private void dateFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dateFocusGained
        if (date.getText().equals("2000-12-31")) {
            date.setText("");
            date.setForeground(Color.BLACK);
        }
        setCompoundBorder(date, myblue);
    }//GEN-LAST:event_dateFocusGained

    private void dateFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dateFocusLost
        if (date.getText().isEmpty()) {
            date.setForeground(mygrey);
            date.setText("2000-12-31");
        }
        setCompoundBorder(date, mygrey);
    }//GEN-LAST:event_dateFocusLost

    private void dateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dateActionPerformed

    private void priceFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_priceFocusGained
        if (price.getText().equals("0.00")) {
            price.setText("");
            price.setForeground(Color.BLACK);
        }
        setCompoundBorder(price, myblue);
    }//GEN-LAST:event_priceFocusGained

    private void SubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SubmitActionPerformed

        String btitle = title.getText();
        String bdate = date.getText();
        String fname = firstname.getText();
        String lname = lastname.getText();
        String bISBN = isbn.getText();
        String bimage = image.getText();
        float brate = (float) rate.getValue();
        float bprice = Float.parseFloat(price.getText());
        int binventory = Integer.parseInt(inventory.getText());
        List<String> selectedGenre = new ArrayList<>();

        for (GenreItem gi : this.genreItems) {
            if (gi.getCheckbox().isSelected()) {
                selectedGenre.add(gi.getGenre().getGenreID());
            }
        }

        if (btitle.isEmpty() || fname.isEmpty() || lname.isEmpty() || bdate.isEmpty() || bISBN.isEmpty() || bimage.isEmpty() || selectedGenre.isEmpty() || binventory < 0 || bprice <= 0.00) {
            JOptionPane.showMessageDialog(this, "Please enter all fields", "Try Again", JOptionPane.ERROR_MESSAGE);
        } else {
            this.book.setTitle(btitle);
            this.book.setAuthorFirstName(fname);
            this.book.setAuthorLastName(lname);
            this.book.setPublicationDate(bdate);
            this.book.setIsbn(bISBN);
            this.book.setInventory(binventory);
            this.book.setPrice(bprice);
            this.book.setRate(brate);
            this.book.setImagePath(bimage);
            bdb.updateBook(book);
            bdb.removeBookGenre(this.book.getBookID());
            for (String genreid : selectedGenre) {
                bdb.addBookGenre(this.book.getBookID(), genreid);
            }
            this.executable.executeBookPanels();
            GlassPanePopup.closePopupLast();
        }


    }//GEN-LAST:event_SubmitActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Add;
    private javax.swing.JButton Back;
    private javax.swing.JLabel Book;
    private javax.swing.JPanel GenrePanel;
    private CustomizedClasses.ScrollPaneWin11 GenreScroll;
    private CustomizedClasses.RoundedBorderButton Submit;
    private javax.swing.JFormattedTextField date;
    private javax.swing.JTextField firstname;
    private javax.swing.JTextArea image;
    private javax.swing.JFormattedTextField inventory;
    private javax.swing.JTextField isbn;
    private javax.swing.JLabel jBookGenre;
    private javax.swing.JLabel jDate;
    private javax.swing.JLabel jDate1;
    private javax.swing.JLabel jFirstName;
    private javax.swing.JLabel jImage;
    private javax.swing.JLabel jIsbn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLastName;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel jPrice;
    private javax.swing.JLabel jPrice1;
    private javax.swing.JLabel jTitle;
    private javax.swing.JLabel jTitle1;
    private javax.swing.JTextField lastname;
    private javax.swing.JFormattedTextField price;
    private CustomizedClasses.Spinner rate;
    private CustomizedClasses.ScrollPaneWin11 scrollPaneWin111;
    private javax.swing.JTextField title;
    private CustomizedClasses.RoundedBorderButton uploadFile;
    // End of variables declaration//GEN-END:variables
}
