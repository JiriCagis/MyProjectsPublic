package behavior.strategy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by osboxes on 08/01/18.
 */
public class CashDesk {

    private List<Integer> drinks;
    private BillingStrategy billingStrategy;

    public CashDesk(){
        this.drinks = new ArrayList<>();
    }

    public void add(int price){
        drinks.add(price);
    }

    public void printBill(){
        System.out.println("Total price to pay: " + billingStrategy.sum(drinks));
        System.out.println("Thank you for your visit.");
        drinks.clear();
    }

    public void setStrategy(BillingStrategy strategy){
        this.billingStrategy = strategy;
    }
}
