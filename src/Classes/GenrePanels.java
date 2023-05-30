/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import User.GenrePanel;
import User.MenuItem;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * @author Aesthetics
 */
public class GenrePanels extends Genre {

    private GenrePanel subGenrePanel;
    private ArrayList<BookPanels> genrebooks;     
    
    public ArrayList<BookPanels> getBookPanel() {
        return this.genrebooks;
    }

    public void setBookPanel(ArrayList<BookPanels> bookpanel) {
        this.genrebooks = bookpanel;
    }

    public GenrePanel getSubGenrePanel() {
        return subGenrePanel;
    }
    public void setSubGenrePanel() {
        this.subGenrePanel = new GenrePanel(getGenreID(), getGenreName(), getCoverImage());
        this.subGenrePanel.setName(getGenreName());
    }

    public void setNumGenrePanel(int num) {
        this.subGenrePanel.setNum(num);
    }
    public int getNumGenrePanel() {
        return this.subGenrePanel.getNum();
    }

    private MenuItem subgenre;

    public MenuItem getSubgenre() {
        return subgenre;
    }

    public void setSubgenre(MenuItem menu) {
        this.subgenre = menu;
    }

    public GenrePanels(String genreID, String genreName, String imagePath, String mainGenreID) {
        super(genreID, genreName, imagePath, mainGenreID);
        this.subgenre = new MenuItem(getIconArrow(), getGenreName(), null);
        this.subGenrePanel = new GenrePanel(getGenreID(), getGenreName(), getCoverImage());
        this.subGenrePanel.setName(getGenreName());
        this.subGenrePanel.setMyProducts();
    }

}
