package database.model;

import java.io.Serializable;

/**
 * The entity is the name of the person or group that is the performer.
 */
public class Artist extends Identify implements Serializable {
    private String name;

    /**
     * CONSTRUCTORS
     */
    public Artist() {
    }

    public Artist(String name) {
        this.id = null;
        this.name = name;
    }

    public Artist(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * GETTERS AND SETTERS
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "name='" + name + '\'' +
                '}';
    }
}
