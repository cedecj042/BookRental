/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import User.MenuItem;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Icon;

/**
 *
 * @author Aesthetics
 */
public class MainGenrePanels extends MainGenre {

    private MenuItem maingenre;
    private ArrayList<MenuItem> subGenre;
    private ArrayList<GenrePanels> genrepanel;
    
    
    public void addGenrePanels(GenrePanels genrepanel) {
        this.genrepanel.add(genrepanel);
    }

    public void setGenrePanels(ArrayList<GenrePanels> genrepanel) {
        this.genrepanel = genrepanel;
    }

    public ArrayList<GenrePanels> getGenrePanels() {
        return this.genrepanel;
    }
   
    
    //array for subgenre 
    public void addSubGenre(MenuItem menu,Container container,CardLayout cardLayout) {
        menu.setAct(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    cardLayout.show(container, menu.getGenreName());
                } catch (Exception ex) {
                    System.out.print("Panel can't be found.");
                }
            }
        });
        subGenre.add(menu);
        menu.setVisible(false);
        maingenre.setSubMenu(subGenre);
    }
    public ArrayList<MenuItem> getSubGenre() {
        return subGenre;
    }


    public MenuItem getMaingenre() {
        return maingenre;
    }

    public void setMaingenre() {
        this.maingenre = new MenuItem(getIcon(), getMainGenreName(), null);
    }
    public MainGenrePanels(String mainGenreID, String mainGenreName, Icon icon) {
        super(mainGenreID, mainGenreName, icon);
        this.maingenre = new MenuItem(getIcon(), getMainGenreName());
        this.subGenre = new ArrayList<>();
        this.genrepanel = new ArrayList<>();
    }

}
