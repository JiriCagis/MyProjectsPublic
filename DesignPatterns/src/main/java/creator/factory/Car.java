package creator.factory;

/**
 * Created by osboxes on 06/01/18.
 */
public class Car {

    private String brand;
    private String model;

    public Car(String brand, String model){
        this.brand = brand;
        this.model = model;
    }

    public static Car Felicia(){
        return new Car("Skoda","Felicia");
    }

    public static Car Fabia(){
        return new Car("Skoda","Fabia");
    }

    public static Car Superb(){
        return new Car("Skoda","Superb");
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                '}';
    }
}
