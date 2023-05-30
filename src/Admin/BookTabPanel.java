/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Admin;

import Classes.Book;
import Classes.BookDB;
import java.awt.Container;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JPanel;

public class BookTabPanel extends javax.swing.JPanel {

    BookDB bdb;
    AdminExecutable executable;
    AdminHome home;
    int num;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
    ArrayList<BookPanel> bookPanels = new ArrayList<>();
    ArrayList<Book> books = new ArrayList<>();

    public BookTabPanel(String genrename, String genreID,AdminHome home, AdminExecutable executable) {
        initComponents();
        this.executable = executable;
        this.home = home;
        bdb = new BookDB();
        this.setName("  " + genrename + "  ");
        this.books = bdb.getGenreBooks(genreID);
        loadBooks();
    }

    public BookTabPanel(AdminExecutable executable) {
        initComponents();
        this.executable = executable;
        bdb = new BookDB();
        this.setName(" " + "Book" + " ");
        this.books = bdb.getAllBooks();
        loadBooks();
    }

    public void setPanelHeight(int width, int height, JPanel panel) {
        panel.setPreferredSize(new Dimension(width, height));
        panel.setSize(new Dimension(width, height));
        panel.setMinimumSize(new Dimension(width, height));
        panel.setMaximumSize(new Dimension(width, height));

    }

    public void loadBooks() {
        for (Book book : books) {
            BookPanel bp = new BookPanel(book,this.home, this.executable);
            bookPanels.add(bp);
        }
        int num = loadPanels(BookTabPanel.this.panels, bookPanels);
        this.num = num;
        setPanelHeight(800, calculatePanelHeight(num) + 40, BookTabPanel.this.panels);
        if (books.isEmpty()) {
            NoBookFound nbf = new NoBookFound();
            BookTabPanel.this.panels.add(nbf);
            setPanelHeight(800, 400, BookTabPanel.this.panels);
        }
    }



    public int calculatePanelHeight(int numBooks) {
        if (numBooks == 0) {
            return 0;
        } else {
            int height = (numBooks / 4) * 290;
            if (numBooks % 4 != 0) {
                height += 290;
            }
            return height;
        }
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panels = new javax.swing.JPanel();

        setBackground(new java.awt.Color(252, 252, 252));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panels.setBackground(new java.awt.Color(252, 252, 252));
        panels.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 4, 4));
        add(panels, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 15, 810, -1));

        getAccessibleContext().setAccessibleName("");
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel panels;
    // End of variables declaration//GEN-END:variables
}