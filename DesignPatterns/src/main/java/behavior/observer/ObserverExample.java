package behavior.observer;

import behavior.observer.BitmapColorSelector;
import behavior.observer.ColorPanel;

/**
 * Created by osboxes on 08/01/18.
 */
public class ObserverExample {

    public static void main(String args[]){
        BitmapColorSelector bitmapColorSelector = new BitmapColorSelector();
        ColorPanel colorPanel = new ColorPanel();

        bitmapColorSelector.addListener(colorPanel);

        bitmapColorSelector.setColor("blue");

        System.out.println("Color selected in bitmap: " + bitmapColorSelector.getSelectedColor());
        System.out.println("Color setted in color panel: " + colorPanel.getSelectedColor());
    }
}
