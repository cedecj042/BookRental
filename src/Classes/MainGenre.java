/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author Aesthetics
 */
public class MainGenre {

    private String mainGenreID, mainGenreName;
    private Icon icon;

    public void setIcon(String url) {
        icon = new ImageIcon(getClass().getResource(url));
    }

    public Icon getIcon() {
        return icon;
    }

    public String getMainGenreID() {
        return mainGenreID;
    }

    public void setMainGenreID(String mainGenreID) {
        this.mainGenreID = mainGenreID;
    }

    public String getMainGenreName() {
        return mainGenreName;
    }

    public void setMainGenreName(String mainGenreName) {
        this.mainGenreName = mainGenreName;
    }

    public MainGenre(String mainGenreID, String mainGenreName, Icon icon) {
        this.mainGenreID = mainGenreID;
        this.mainGenreName = mainGenreName;
        this.icon = icon;
    }
}
