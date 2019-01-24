package creator.builder;

/**
 * Created by osboxes on 06/01/18.
 */
public class Director {

    public Building build(Builder builder){
        builder.startNew();
        builder.buildFloor();
        builder.buildWalls();
        builder.buildRoof();
        return builder.getResult();
    }
}
