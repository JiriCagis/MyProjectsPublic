package database.model;

import java.io.Serializable;

/**
 * This entity is the company that released the recording.
 */
public class Label extends Identify implements Serializable{
    private String name;

    /**CONSTRUCTORS */
    public Label(){}

    public Label(String name){
        this.id = null;
        this.name = name;
    }

    public Label(Integer id,String name){
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
