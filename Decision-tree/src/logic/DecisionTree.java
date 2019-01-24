package logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import model.Humidity;
import model.Outlook;
import model.Row;
import model.Temperature;

/**
 * Class represent n-arity decision tree for vocation rules in table about weather.
 * On top tree is partition by attribute "Outlook", in another floor divide by attribute 
 * "Humidity", another floor divide by attribute "Windy" and last floor divide by attribute
 * "Temperature". Tree is scan downwards and in each floor extended search filter add attribute.
 * 
 * Example: On start records are divide by column "Outlook". If rows with value sunny in column "outlook"
 * have all same value in column play write this filter as rule. In next step filter divide rows by two
 * attributes "outlook" and "humidity". For another passage tree to depth is filter extended with attributes.
 * 
 * Tree representation: -->
 *                                        ____________
 *                                        -   ROOT   -
 *                                        -          -
 *                                        ------------
 *                                    *         *       *
 *                                 *            *            *
 *                              *               *                * 
 *                   _________*__          _____*______         ____*_______
 *                   -  SUNNY   -          - OVERCAST -         -  RAINY   - 
 *                   -          -          -          -         -          -
 *                   ------------          ------------         ------------
 *               *         *       *
 *             *           *           *
 *      _____*__       ____*___      _____*__
 *      - HIGH -       -NORMAL-      - LOW  -
 *      --------       --------      --------
 * 
 *          ... And so on
 * 
 * @author and licence by:  Jiri Caga
 */
public class DecisionTree {

    /**
     * Prevent create static class.
     */
    private DecisionTree() {

    }

    /**
     * Method search rules in table about weather.
     * @param table table with data about weather.
     * @return rules
     */
    public static List<String> findRules(List<Row> table) {
        List<String> rules = new ArrayList<>();
        String rule = null;

        for (Outlook outlook : Outlook.values()) { //floor 1 tree
            Row filter = new Row();
            filter.setOutlook(outlook);
            if ((rule = findRuleFor(table, filter)) != null) {
                rules.add(rule);
            }

            for (Humidity humidity : Humidity.values()) { //floor 2 in tree
                filter.setHumidity(humidity);
                if ((rule = findRuleFor(table, filter)) != null) {
                    rules.add(rule);
                }

                for (boolean windy : new boolean[]{true, false}) { //floor 3 in tree
                    filter.setWindy(windy);
                    if ((rule = findRuleFor(table, filter)) != null) {
                        rules.add(rule);
                    }

                    for (Temperature temperature : Temperature.values()) { //floor 4 in tree
                        filter.setTemperature(temperature);
                        if ((rule = findRuleFor(table, filter)) != null) {
                            rules.add(rule);
                        }
                        filter.setTemperature(null);
                    }
                    filter.setWindy(null);
                }
                filter.setHumidity(null);
            }
            filter.setOutlook(null);
        }

        Collections.sort(rules, new LengthComparator()); //sort rules by text lenght
        return rules;
    }

    /**
     * Method get from table only rows fulfilling conditions filter and look on 
     * filtered rows if have same value in column "Play". If filtered rows have
     * same value, create rule from filter.
     * @param table table with data about weather
     * @param filter filter for obtain rows from table
     * @return if find rule return it else return null
     */
    private static String findRuleFor(List<Row> table, Row filter) {
        List<Row> list = divideBy(filter, table);
        Boolean samePlayValue = decideSamePlayValue(list);
        if (samePlayValue != null) {
            return createTextRule(filter, samePlayValue);
        }
        return null;
    }

    /**
     * Method get from table only rows fulfill filter condition.
     * @param filter filter for obtain rows from table
     * @param rows rows for filter
     * @return filtered rows
     */
    private static List<Row> divideBy(Row filter, List<Row> rows) {
        List<Row> result = new ArrayList<>();
        if (filter != null) {
            for (Row row : rows) {
                if ((filter.getOutlook() == null || filter.getOutlook() == row.getOutlook())
                        && (filter.getHumidity() == null || filter.getHumidity() == row.getHumidity())
                        && (filter.getWindy() == null || filter.getWindy().equals(row.getWindy()))) {
                    result.add(row);
                }
            }
        }
        return result;
    }

    /**
     * Method decide if all rows have same value in column "Play".
     * @param rows rows for test
     * @return If have all rows same value in column "Play" return this value,
     * else return null.
     */
    private static Boolean decideSamePlayValue(List<Row> rows) {
        if (rows.isEmpty()) {
            return null;
        }
        boolean playAttribute = rows.get(0).getPlay();
        for (Row row : rows) {
            if (row.getPlay() != playAttribute) {
                return null;
            }
        }
        return playAttribute;
    }

    /**
     * Method create on base filter and play value text rule possible display
     * in terminal.
     * @param filter
     * @param playValue
     * @return
     */
    private static String createTextRule(Row filter, boolean playValue) {
        StringBuilder builder = new StringBuilder();
        builder.append("IF");

        //Outlook
        if (filter.getOutlook() != null) {
            if (builder.length() > 2) {
                builder.append(" AND");
            }
            builder.append(" OUTLOOK = ");
            builder.append(filter.getOutlook());
        }

        //Temperature
        if (filter.getTemperature() != null) {
            if (builder.length() > 2) {
                builder.append(" AND");
            }
            builder.append(" TEMPERATURE = ");
            builder.append(filter.getTemperature());
        }

        //Humidity
        if (filter.getHumidity() != null) {
            if (builder.length() > 2) {
                builder.append(" AND");
            }
            builder.append(" HUMIDITY = ");
            builder.append(filter.getHumidity());
        }

        //Windy
        if (filter.getWindy() != null) {
            if (builder.length() > 2) {
                builder.append(" AND");
            }
            builder.append(" WINDY = ");
            builder.append(filter.getWindy());
        }

        //play value
        builder.append(" THEN PLAY = ");
        builder.append(playValue);
        return builder.toString();
    }

}
