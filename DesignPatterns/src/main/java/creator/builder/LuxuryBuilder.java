package creator.builder;

/**
 * Created by osboxes on 06/01/18.
 */
public class LuxuryBuilder implements Builder {

    private Building building;

    @Override
    public void startNew() {
        building = new Building();
    }

    @Override
    public void buildFloor() {
        building.setFloor("wooden floor");
    }

    @Override
    public void buildWalls() {
        building.setWalls("brick walls");
    }

    @Override
    public void buildRoof() {
        building.setRoof("shindel roof");
    }

    @Override
    public Building getResult() {
        return building;
    }
}
