package creator.prototype;

/**
 * Created by osboxes on 06/01/18.
 */
public class PrototypeExample {

    public static void main(String[] args){
        Shape circle = new Shape("1","circle");
        circle.draw();

        Shape circle2 = (Shape) circle.clone();
        circle2.draw();

        //result of clone must accomplish three conditions:
        if(circle != circle2 && circle.equals(circle2) && circle.getClass() == circle2.getClass()){
            System.out.println("Clone operation accomplished three conditions.");
        } else {
            System.out.println("It is not clone, because did not accomplish three conditions.");
        }
    }

}
