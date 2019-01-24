package behavior.strategy;

import java.util.List;

/**
 * Created by osboxes on 08/01/18.
 */
public class HappyHourStrategy implements BillingStrategy {
    @Override
    public double sum(List<Integer> drinks) {
        int sum = 0;
        for(Integer drink:drinks){
            sum += drink;
        }
        return sum*0.9;
    }
}
