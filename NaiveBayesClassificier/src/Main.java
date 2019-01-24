import java.util.ArrayList;
import java.util.List;

import bayesClassification.BayesClassification;
import model.Humidity;
import model.Outlook;
import model.Row;
import model.Temperature;

public class Main {

    public static void main(String[] args) {
        List<Row> table = createTable();

        BayesClassification bayesClassification = new BayesClassification(table);
        boolean play = bayesClassification.classification(Outlook.SUNNY, Temperature.HOT, Humidity.NORMAL, false);

        System.out.println("When will sunny, temperature hot, humidity normal and not windy: ");
        if (play) {
            System.out.println("Game probability will play.");
        } else {
            System.out.println("Game probability will not play.");
        }
    }

    private static List<Row> createTable() {
        List<Row> table = new ArrayList<>();
        table.add(new Row(Outlook.SUNNY, Temperature.HOT, Humidity.HIGH, false, false));
        table.add(new Row(Outlook.SUNNY, Temperature.HOT, Humidity.HIGH, true, false));
        table.add(new Row(Outlook.OVERCAST, Temperature.HOT, Humidity.HIGH, false, true));
        table.add(new Row(Outlook.RAINY, Temperature.MILD, Humidity.HIGH, false, true));
        table.add(new Row(Outlook.RAINY, Temperature.COLD, Humidity.NORMAL, false, true));
        table.add(new Row(Outlook.RAINY, Temperature.COLD, Humidity.NORMAL, true, false));
        table.add(new Row(Outlook.OVERCAST, Temperature.COLD, Humidity.NORMAL, true, true));
        table.add(new Row(Outlook.SUNNY, Temperature.MILD, Humidity.HIGH, false, false));
        table.add(new Row(Outlook.SUNNY, Temperature.COLD, Humidity.NORMAL, false, true));
        table.add(new Row(Outlook.RAINY, Temperature.MILD, Humidity.NORMAL, false, true));
        table.add(new Row(Outlook.SUNNY, Temperature.MILD, Humidity.NORMAL, true, true));
        table.add(new Row(Outlook.OVERCAST, Temperature.MILD, Humidity.HIGH, true, true));
        table.add(new Row(Outlook.OVERCAST, Temperature.HOT, Humidity.NORMAL, false, true));
        table.add(new Row(Outlook.RAINY, Temperature.MILD, Humidity.HIGH, true, false));
        return table;
    }

}
