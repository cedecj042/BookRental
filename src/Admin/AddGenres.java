/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Admin;

import Classes.GenreDB;
import Classes.MainGenre;
import Classes.MainGenreDB;
import CustomizedClasses.GlassPanePopup;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.swing.AbstractButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import jnafilechooser.api.JnaFileChooser;

/**
 *
 * @author Aesthetics
 */
public class AddGenres extends javax.swing.JPanel {

    /**
     * Creates new form AddGenres
     */
    AdminHome home;
    AdminExecutable executable;
    MainGenreDB mgdb;
    GenreDB gdb;

    ArrayList<MainGenre> maingenres = new ArrayList<>();
    ArrayList<CategoryItem> categories = new ArrayList<>();

    public AddGenres(AdminHome home, AdminExecutable executable) {
        initComponents();
        setOpaque(false);
        this.home = home;
        this.executable = executable;
        mgdb = new MainGenreDB();
        gdb = new GenreDB();
        this.maingenres = mgdb.getAllGenre();
        for (MainGenre mg : maingenres) {
            CategoryItem ci = new CategoryItem(mg);
            categories.add(ci);
            categorygroup.add(ci.getRadio());
        }
        loadCategoryItem(categories);

    }

    public void loadCategoryItem(ArrayList<CategoryItem> ci) {
        categorypanels.removeAll();
        for (int i = 0; i < ci.size(); i++) {
            categorypanels.add(ci.get(i));
        }
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

    private CategoryItem findCategoryItemByRadioButton(AbstractButton radioButton) {
        for (CategoryItem categoryItem : categories) {
            if (categoryItem.getRadio() == radioButton) {
                return categoryItem;
            }
        }
        return null; // Return null if no matching CategoryItem is found
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        categorygroup = new javax.swing.ButtonGroup();
        scrollPaneWin111 = new CustomizedClasses.ScrollPaneWin11();
        Image = new javax.swing.JTextArea();
        jImage = new javax.swing.JLabel();
        jTitle = new javax.swing.JLabel();
        jTitle1 = new javax.swing.JLabel();
        name = new javax.swing.JTextField();
        Add = new javax.swing.JLabel();
        Book = new javax.swing.JLabel();
        Back = new javax.swing.JButton();
        id = new javax.swing.JTextField();
        jTitle2 = new javax.swing.JLabel();
        uploadFile = new CustomizedClasses.RoundedBorderButton();
        Submit = new CustomizedClasses.RoundedBorderButton();
        jTitle3 = new javax.swing.JLabel();
        jImage1 = new javax.swing.JLabel();
        categorypanels = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        scrollPaneWin111.setBackground(new java.awt.Color(252, 252, 252));
        scrollPaneWin111.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(206, 206, 206), 1, true), javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        scrollPaneWin111.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        Image.setEditable(false);
        Image.setBackground(new java.awt.Color(255, 255, 255));
        Image.setColumns(60);
        Image.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        Image.setLineWrap(true);
        Image.setRows(1);
        Image.setWrapStyleWord(true);
        Image.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
        Image.setRequestFocusEnabled(false);
        scrollPaneWin111.setViewportView(Image);

        add(scrollPaneWin111, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 240, 390, 50));

        jImage.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jImage.setForeground(new java.awt.Color(82, 82, 82));
        jImage.setText("Category");
        add(jImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 300, 240, 30));

        jTitle.setFont(new java.awt.Font("Poppins Medium", 0, 18)); // NOI18N
        jTitle.setForeground(new java.awt.Color(52, 52, 52));
        jTitle.setText("Book Details");
        add(jTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 90, 240, 30));

        jTitle1.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jTitle1.setForeground(new java.awt.Color(82, 82, 82));
        jTitle1.setText("Genre Name");
        add(jTitle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 120, 240, 30));

        name.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        name.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(206, 206, 206), 1, true), javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        add(name, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 150, 370, 50));

        Add.setFont(new java.awt.Font("Poppins", 1, 24)); // NOI18N
        Add.setForeground(new java.awt.Color(1, 87, 220));
        Add.setText("Add");
        add(Add, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 40, 50, 40));

        Book.setFont(new java.awt.Font("Poppins", 1, 24)); // NOI18N
        Book.setText("Genre");
        add(Book, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 40, 140, 40));

        Back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/images/backsmall.png"))); // NOI18N
        Back.setBorderPainted(false);
        Back.setContentAreaFilled(false);
        Back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackActionPerformed(evt);
            }
        });
        add(Back, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 30, 40));

        id.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        id.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(206, 206, 206), 1, true), javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        add(id, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 150, 140, 50));

        jTitle2.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jTitle2.setForeground(new java.awt.Color(106, 106, 106));
        jTitle2.setText("Input 3 letters only.");
        add(jTitle2, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 200, -1, 20));

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
        add(uploadFile, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 240, 120, 50));

        Submit.setBackground(new java.awt.Color(1, 87, 220));
        Submit.setForeground(new java.awt.Color(255, 255, 255));
        Submit.setText("Proceed");
        Submit.setFont(new java.awt.Font("Poppins Medium", 0, 18)); // NOI18N
        Submit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SubmitActionPerformed(evt);
            }
        });
        add(Submit, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 480, 520, 60));

        jTitle3.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jTitle3.setForeground(new java.awt.Color(82, 82, 82));
        jTitle3.setText("Genre ID ");
        add(jTitle3, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 120, -1, 30));

        jImage1.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jImage1.setForeground(new java.awt.Color(82, 82, 82));
        jImage1.setText("Genre Cover");
        add(jImage1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 210, 240, 30));

        categorypanels.setBackground(new java.awt.Color(255, 255, 255));
        categorypanels.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 0));
        add(categorypanels, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 330, 520, 130));
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 650, 590));
    }// </editor-fold>//GEN-END:initComponents

    private void uploadFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uploadFileActionPerformed
        // TODO add your handling code here:
        JnaFileChooser ch = new JnaFileChooser();
        boolean action = ch.showOpenDialog(this.home);
        if (action) {
            File file = ch.getSelectedFile();
            String path = file.getAbsolutePath();
            String newPath = path.replaceAll("\\\\", "\\\\\\\\");
            Image.setText(newPath);
        }
    }//GEN-LAST:event_uploadFileActionPerformed

    private void SubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SubmitActionPerformed
        int flag = 0;
        String cg = "";
        String image = Image.getText();
        String genrename = name.getText();
        String genreid = id.getText();
        MainGenre selectedMainGenre = null;
        
        Enumeration<AbstractButton> buttons = categorygroup.getElements();
        while (buttons.hasMoreElements()) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                CategoryItem selectedCategoryItem = findCategoryItemByRadioButton(button);
                selectedMainGenre = selectedCategoryItem.getMaingenre();
                flag = 1;
                break; 
            }
        }
        if (flag != 0 && selectedMainGenre != null) {
            if (!gdb.checkGenre(genreid, genrename)) {
                gdb.createGenre(genreid, genrename, cg, image);
                this.executable.executeBookPanels();
                this.executable.executeGenrePanels();
                GlassPanePopup.closePopupAll();
            } else {
                JOptionPane.showMessageDialog(this, "Genre Already Added", "Try Again", JOptionPane.ERROR_MESSAGE);
            }

        }


    }//GEN-LAST:event_SubmitActionPerformed

    private void BackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackActionPerformed
        // TODO add your handling code here:
        GlassPanePopup.closePopupLast();
    }//GEN-LAST:event_BackActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Add;
    private javax.swing.JButton Back;
    private javax.swing.JLabel Book;
    private javax.swing.JTextArea Image;
    private CustomizedClasses.RoundedBorderButton Submit;
    private javax.swing.ButtonGroup categorygroup;
    private javax.swing.JPanel categorypanels;
    private javax.swing.JTextField id;
    private javax.swing.JLabel jImage;
    private javax.swing.JLabel jImage1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jTitle;
    private javax.swing.JLabel jTitle1;
    private javax.swing.JLabel jTitle2;
    private javax.swing.JLabel jTitle3;
    private javax.swing.JTextField name;
    private CustomizedClasses.ScrollPaneWin11 scrollPaneWin111;
    private CustomizedClasses.RoundedBorderButton uploadFile;
    // End of variables declaration//GEN-END:variables
}
