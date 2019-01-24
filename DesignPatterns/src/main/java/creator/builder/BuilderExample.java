package creator.builder;

/**
 * Created by osboxes on 06/01/18.
 */
public class BuilderExample {

    public static void main(String[] args){
        Director director = new Director();

        Building cheapBuilding = director.build(new CheapBuilder());
        System.out.println(cheapBuilding);

        Building luxuryBuilding = director.build(new LuxuryBuilder());
        System.out.println(luxuryBuilding);
    }
}
