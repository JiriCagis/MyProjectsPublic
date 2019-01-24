package creator.abstractfactory;

/**
 * Created by osboxes on 06/01/18.
 */
public class AbstractFactoryExample {

    //gui components
    interface Button{
        void paint();
    }

    class WinButton implements Button{

        @Override
        public void paint() {
            System.out.println("I'm a WinButton.");
        }
    }

    class OSXButton implements Button{

        @Override
        public void paint() {
            System.out.println("I'm a OSXButton.");
        }
    }



    // Abstract factory
    interface GUIFactory{
        Button createButton();
    }

    // Concrete factories
    class WinFactory implements GUIFactory{

        @Override
        public Button createButton() {
            return new WinButton();
        }
    }

    class OSXFactory implements GUIFactory{

        @Override
        public Button createButton() {
            return new OSXButton();
        }
    }



    // Example
    public static void main(String[] args){
        new AbstractFactoryExample();
    }

    public AbstractFactoryExample(){
        GUIFactory guiFactory = createOsSpecificFactory();
        Button button = guiFactory.createButton();
        button.paint();
    }

    public GUIFactory createOsSpecificFactory(){
        int sys = readFromConfigFile("OS_TYPE");
        if (sys == 0){
            return new WinFactory();
        } else {
            return new OSXFactory();
        }
    }

    public int readFromConfigFile(String attribute){
        // it is mock implementation
        return 0;
    }
}
