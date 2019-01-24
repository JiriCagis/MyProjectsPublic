package behavior.strategy;

/**
 * Created by osboxes on 08/01/18.
 */
public class StrategyExample {

    public static void main(String[] args){
        CashDesk cashDesk = new CashDesk();
        cashDesk.setStrategy(new HappyHourStrategy());
        cashDesk.add(20);
        cashDesk.add(80);
        cashDesk.printBill();

        cashDesk.setStrategy(new NormalStrategy());
        cashDesk.add(20);
        cashDesk.add(80);
        cashDesk.printBill();
    }
}
