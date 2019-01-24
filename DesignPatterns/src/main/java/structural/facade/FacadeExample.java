package structural.facade;

/**
 * Created by osboxes on 07/01/18.
 */
public class FacadeExample {

    public static void main(String[] args){
        final Facade facade = new Facade();

        // = 5
        System.out.println(facade.negative(-5));

        // = 15
        System.out.println(facade.mean(10,20));

        // = 25
        System.out.println(facade.square(5));
    }
}
