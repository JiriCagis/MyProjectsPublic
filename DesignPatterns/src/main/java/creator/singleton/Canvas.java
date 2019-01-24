package creator.singleton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by osboxes on 06/01/18.
 */
public class Canvas {

    private static Canvas instance = null;
    private List<String> shapes;

    private Canvas(){
        shapes = new ArrayList<>();
    }

    public static Canvas getInstance(){
        if (instance == null){
            synchronized (Canvas.class){
                if(instance == null){
                    instance = new Canvas();
                }
            }
        }

        return instance;
    }

    public void addShape(String shape){
        this.shapes.add(shape);
    }

    public void draw(){
        shapes.forEach(shape -> System.out.println(shape));
    }
}
