package structural.adapter;

/**
 * Created by osboxes on 07/01/18.
 */
public class AdapterExample {

    public static void main(String[] args){
        Target adapter = new Adapter();
        adapter.newRequest();
    }
}
