package database.model;

import java.io.Serializable;

/**
 * Entity represents person create Review on Recording.
 */
public class Reviewer extends Identify implements Serializable {
    private String name;

    /** CONSTRUCTORS */
    public Reviewer(){}

    public Reviewer(String name) {
        this.id = null;
        this.name = name;
    }

    public Reviewer(Integer id,String name) {
        this.id = id;
        this.name = name;
    }

    /** GETTERS AND SETTERS */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
