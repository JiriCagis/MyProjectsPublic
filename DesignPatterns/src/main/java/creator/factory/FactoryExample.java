package creator.factory;

import creator.factory.Car;

/**
 * Created by osboxes on 06/01/18.
 */
public class FactoryExample {

    public static void main(String args[]){
        Car felicia = Car.Felicia();
        System.out.println(felicia);
    }
}
