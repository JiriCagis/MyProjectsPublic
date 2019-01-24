package database.model;

/**
 * Base rule for each entity in model for identify one object from others.
 * Created by cagaj on 23.7.2016.
 */
public abstract class Identify {
    protected Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Identify identify = (Identify) o;

        return id != null ? id.equals(identify.id) : identify.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
