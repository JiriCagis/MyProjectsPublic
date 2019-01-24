package behavior.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by osboxes on 08/01/18.
 */
public class BitmapColorSelector {

    private String selectedColor;
    private List<Listener> listeners;

    public BitmapColorSelector(){
        listeners = new ArrayList<>();
        selectedColor = null;
    }

    public void setColor(String color){
        selectedColor = color;
        alertAllListeners();
    }

    private void alertAllListeners(){
        for(Listener listener:listeners){
            listener.updateColor(this.selectedColor);
        }
    }

    public void addListener(Listener listener){
        listeners.add(listener);
    }

    public void removeListener(Listener listener){
        listeners.remove(listener);
    }

    public String getSelectedColor() {
        return selectedColor;
    }
}
