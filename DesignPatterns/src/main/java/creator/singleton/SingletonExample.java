package creator.singleton;

/**
 * Created by osboxes on 06/01/18.
 */
public class SingletonExample {

    public static void main(String[] args){
        Canvas canvas = Canvas.getInstance();

        canvas.addShape("circle");
        canvas.addShape("rectangle");
        canvas.addShape("curve");

        // verify global variable
        Canvas.getInstance().draw();
    }

}
