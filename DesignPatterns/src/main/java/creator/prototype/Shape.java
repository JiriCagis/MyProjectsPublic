package creator.prototype;

import sun.security.provider.SHA;

/**
 * Created by osboxes on 06/01/18.
 */
public class Shape implements Cloneable {

    private String id;
    protected String type;

    public Shape(String id,String type){
        this.id = id;
        this.type = type;
    }

    public String getType(){return this.type;}

    public String getId(){return this.id;}

    public void setId(String id){this.id = id;}

    public void draw(){
        System.out.println("Draw in canvas object:" + type + " with id:" + id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Shape shape = (Shape) o;

        if (id != null ? !id.equals(shape.id) : shape.id != null) return false;
        return type != null ? type.equals(shape.type) : shape.type == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    @Override
    public Object clone(){
        Object clone = null;

        try{
            clone = super.clone();
        } catch (CloneNotSupportedException e){
            e.printStackTrace();
        }

        return clone;
    }
}
