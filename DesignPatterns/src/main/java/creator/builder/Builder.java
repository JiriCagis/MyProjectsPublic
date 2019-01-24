package creator.builder;

/**
 * Created by osboxes on 06/01/18.
 */
public interface Builder {

    void startNew();
    void buildFloor();
    void buildWalls();
    void buildRoof();
    Building getResult();

}
