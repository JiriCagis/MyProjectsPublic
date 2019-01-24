package database.model;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * This entity is something that is publicly released by a music company on some type of media (compact disc,
 * audiotape, vinyl, and so on)
 */
public class Recording extends Identify implements Serializable{
    private String title;
    private LocalDate releaseDate;
    private Artist artist;
    private Label label;

    /** CONSTRUCTORS **/
    public Recording(){}

    public Recording(String title, LocalDate releaseDate, Artist artist, Label label) {
        this.id = null;
        this.title = title;
        this.releaseDate = releaseDate;
        this.artist = artist;
        this.label = label;
    }

    public Recording(Integer id,String title, LocalDate releaseDate, Artist artist, Label label) {
        this.id = id;
        this.title = title;
        this.releaseDate = releaseDate;
        this.artist = artist;
        this.label = label;
    }

    /** GETTERS AND SETTERS */
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "Recording{" +
                "title='" + title + '\'' +
                ", releaseDate=" + releaseDate +
                ", artist=" + artist +
                ", label=" + label +
                '}';
    }
}
