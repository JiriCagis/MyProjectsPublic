package structural.facade;

/**
 * Created by osboxes on 07/01/18.
 */
public class Facade {

    private final Adder adder = new Adder();
    private final Multiplier multiplier = new Multiplier();

    public double negative(final double number){
        return adder.subtract(0,number);
    }

    public double mean(final double number1, final double number2){
        return multiplier.divide(adder.add(number1,number2),2.0);
    }

    public double square(final double number){
        return multiplier.multiply(number,number);
    }

}
