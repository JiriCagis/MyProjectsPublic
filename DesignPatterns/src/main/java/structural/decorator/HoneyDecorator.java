package structural.decorator;

/**
 * Created by osboxes on 07/01/18.
 */
public class HoneyDecorator extends IcecreamDecorator {


    public HoneyDecorator(Icecream specialIcecream) {
        super(specialIcecream);
    }

    @Override
    public String makeIcecream() {
        return super.makeIcecream() + addHoney();
    }

    private String addHoney() {
        return " + sweet honey";
    }
}
