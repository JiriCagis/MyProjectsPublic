package behavior.observer;

/**
 * Created by osboxes on 08/01/18.
 */
public class ColorPanel implements Listener {

    private String selectedColor;

    public ColorPanel(){
        selectedColor = null;
    }

    @Override
    public void updateColor(String color) {
        selectedColor = color;
    }

    public String getSelectedColor() {
        return selectedColor;
    }
}
