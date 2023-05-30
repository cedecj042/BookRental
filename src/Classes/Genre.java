/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author Aesthetics
 */
public class Genre {

    private String genreID;
    private String genreName;
    private String mainGenreID;
    private String imagePath;
    private ImageIcon coverImage;
    private Icon iconArrow = new ImageIcon(getClass().getResource("../img/images/arrow.png"));

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Icon getIconArrow() {
        return iconArrow;
    }

    public String getGenreID() {
        return genreID;
    }

    public void setGenreID(String genreID) {
        this.genreID = genreID;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    public Icon getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(ImageIcon coverImage) {
        this.coverImage = coverImage;
    }

    public String getMainGenreID() {
        return mainGenreID;
    }

    public void setMainGenreID(String mainGenreID) {
        this.mainGenreID = mainGenreID;
    }

    public Genre(String genreID, String genreName, ImageIcon coverImage, String mainGenreID) {
        this.genreID = genreID;
        this.genreName = genreName;
        this.coverImage = coverImage;
        this.mainGenreID = mainGenreID;
    }

    public Genre(String genreID, String genreName, String imagePath, String mainGenreID) {
        this.genreID = genreID;
        this.genreName = genreName;
        this.imagePath = imagePath;
        this.coverImage = createImageIcon(imagePath);
        this.mainGenreID = mainGenreID;
    }

    protected ImageIcon createImageIcon(String path) {
        try {
            Image image = ImageIO.read(new File(path));
            return new ImageIcon(image);
        } catch (IOException e) {
            System.err.println("Couldn't find file: " + path);
            e.printStackTrace();
            return null;
        }
    }
}
