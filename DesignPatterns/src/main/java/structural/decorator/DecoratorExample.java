package structural.decorator;

/**
 * Created by osboxes on 07/01/18.
 */
public class DecoratorExample {

    public static void main(String[] args){
        Icecream honeyIcecream = new HoneyDecorator( new SimpleIcecream());
        System.out.println(honeyIcecream.makeIcecream());

        Icecream nuttyIcecream = new NuttyDecorator( new SimpleIcecream());
        System.out.println(nuttyIcecream.makeIcecream());
    }
}
