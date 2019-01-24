package behavior.strategy;

import behavior.strategy.BillingStrategy;

import java.util.List;

/**
 * Created by osboxes on 08/01/18.
 */
public class NormalStrategy implements BillingStrategy {
    @Override
    public double sum(List<Integer> drinks) {
        int sum = 0;
        for(Integer drink:drinks){
            sum += drink;
        }
        return sum;
    }
}
