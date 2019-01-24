package bayesClassification;

import model.Humidity;
import model.Outlook;
import model.Row;
import model.Temperature;

/**
 * @author Ji?í Cága (cagaajir)
 *         2015-10-28
 */
public class Filters {
    public final Row FILTER_SUNNY;
    public final Row FILTER_OVERCAST;
    public final Row FILTER_RAINY;

    public final Row FILTER_TEMPERATURE_HOT;
    public final Row FILTER_TEMPERATURE_MILD;
    public final Row FILTER_TEMPERATURE_COLD;

    public final Row FILTER_HUMIDITY_HIGH;
    public final Row FILTER_HUMIDITY_NORMAL;
    public final Row FILTER_HUMIDITY_LOW;

    public final Row FILTER_WINDY;
    public final Row FILTER_NOT_WINDY;

    public Filters(){
        FILTER_SUNNY = new Row();
        FILTER_SUNNY.setOutlook(Outlook.SUNNY);
        FILTER_OVERCAST = new Row();
        FILTER_OVERCAST.setOutlook(Outlook.OVERCAST);
        FILTER_RAINY = new Row();
        FILTER_RAINY.setOutlook(Outlook.RAINY);

        FILTER_TEMPERATURE_COLD = new Row();
        FILTER_TEMPERATURE_COLD.setTemperature(Temperature.COLD);
        FILTER_TEMPERATURE_HOT = new Row();
        FILTER_TEMPERATURE_HOT.setTemperature(Temperature.HOT);
        FILTER_TEMPERATURE_MILD = new Row();
        FILTER_TEMPERATURE_MILD.setTemperature(Temperature.MILD);

        FILTER_HUMIDITY_HIGH = new Row();
        FILTER_HUMIDITY_HIGH.setHumidity(Humidity.HIGH);
        FILTER_HUMIDITY_NORMAL = new Row();
        FILTER_HUMIDITY_NORMAL.setHumidity(Humidity.NORMAL);
        FILTER_HUMIDITY_LOW =new Row();
        FILTER_HUMIDITY_LOW.setHumidity(Humidity.LOW);

        FILTER_WINDY = new Row();
        FILTER_WINDY.setWindy(true);

        FILTER_NOT_WINDY = new Row();
        FILTER_NOT_WINDY.setWindy(false);
    }
}
