package structural.adapter;

/**
 * Created by osboxes on 07/01/18.
 */
public class Adapter implements Target {

    private Adaptee adaptee;

    public Adapter(){
        this.adaptee = new Adaptee();
    }

    @Override
    public void newRequest() {
        adaptee.oldRequest();
    }
}
