package database.model;

import java.io.Serializable;

/**
 * Entity represent complete song with title and duration. Tracks are grouped to Records.
 */
public class Track extends Identify implements Serializable {
    private String title;
    private Integer duration;
    private Genre genre;
    private Artist artist;
    private Recording recording;

    /** CONSTRUCTORS */
    public Track(){}

    public Track(String title, Integer duration, Genre genre, Artist artist, Recording recording) {
        this.id = null;
        this.title = title;
        this.duration = duration;
        this.genre = genre;
        this.artist = artist;
        this.recording = recording;
    }

    public Track(Integer id,String title, Integer duration, Genre genre, Artist artist, Recording recording) {
        this.id = id;
        this.title = title;
        this.duration = duration;
        this.genre = genre;
        this.artist = artist;
        this.recording = recording;
    }

    /** GETTERS AND SETTERS */
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Recording getRecording() {
        return recording;
    }

    public void setRecording(Recording recording) {
        this.recording = recording;
    }
}
