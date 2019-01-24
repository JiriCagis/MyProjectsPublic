package database.model;

import java.io.Serializable;

/**
 * This entity represents different musical styles, for example, rock, classical,
 * pop, hip hop, and so on.
 */
public class Genre extends Identify implements Serializable {
    private String name;

    /**
     * CONSTRUCTORS
     */
    public Genre() {
    }

    public Genre(String name) {
        this.id = null;
        this.name = name;
    }

    public Genre(Integer id, String name) {
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
}
