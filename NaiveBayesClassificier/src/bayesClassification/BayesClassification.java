package bayesClassification;

import model.Humidity;
import model.Outlook;
import model.Row;
import model.Temperature;
import java.util.ArrayList;
import java.util.List;

/**
 * Naive Bayes is a simple technique for constructing classifiers: models that assign class labels to problem instances,
 * represented as vectors of feature values, where the class labels are drawn from some finite set. It is not a single
 * algorithm for training such classifiers, but a family of algorithms based on a common principle: all naive Bayes
 * classifiers assume that the value of a particular feature is independent of the value of any other feature, given
 * the class variable. For example, a fruit may be considered to be an apple if it is red, round, and about 10 cm
 * in diameter. A naive Bayes classifier considers each of these features to contribute independently to the probability
 * that this fruit is an apple, regardless of any possible correlations between the color, roundness and diameter features.
 *
 * For some types of probability models, naive Bayes classifiers can be trained very efficiently in a supervised
 * learning setting. In many practical applications, parameter estimation for naive Bayes models uses the method
 * of maximum likelihood; in other words, one can work with the naive Bayes model without accepting Bayesian
 * probability or using any Bayesian methods.
 *
 * @author Ji?í Cága (cagaajir)
 *         2015-10-28
 */
public class BayesClassification {
    private Probabilities probabilities_play;
    private Probabilities probabilities_not_play;

    public BayesClassification(List<Row> rows) {
        Filters filters = new Filters();

        probabilities_play = new Probabilities();
        probabilities_play.general = calculateProbability(rows, true);
        probabilities_play.sunny = calculateProbability(filter(rows, filters.FILTER_SUNNY), true);
        probabilities_play.overcast = calculateProbability(filter(rows, filters.FILTER_OVERCAST), true);
        probabilities_play.rainy = calculateProbability(filter(rows, filters.FILTER_RAINY), true);
        probabilities_play.temperature_cold = calculateProbability(filter(rows, filters.FILTER_TEMPERATURE_COLD), true);
        probabilities_play.temperature_mild = calculateProbability(filter(rows, filters.FILTER_TEMPERATURE_MILD), true);
        probabilities_play.temperature_hot = calculateProbability(filter(rows, filters.FILTER_TEMPERATURE_HOT), true);
        probabilities_play.humidity_high = calculateProbability(filter(rows, filters.FILTER_HUMIDITY_HIGH), true);
        probabilities_play.humidity_normal = calculateProbability(filter(rows, filters.FILTER_HUMIDITY_NORMAL), true);
        probabilities_play.humidity_low = calculateProbability(filter(rows, filters.FILTER_HUMIDITY_LOW), true);
        probabilities_play.windy = calculateProbability(filter(rows, filters.FILTER_WINDY), true);
        probabilities_play.not_windy = calculateProbability(filter(rows, filters.FILTER_NOT_WINDY), true);

        probabilities_not_play = new Probabilities();
        probabilities_not_play.general = calculateProbability(rows, false);
        probabilities_not_play.sunny = calculateProbability(filter(rows, filters.FILTER_SUNNY), false);
        probabilities_not_play.overcast = calculateProbability(filter(rows, filters.FILTER_OVERCAST), false);
        probabilities_not_play.rainy = calculateProbability(filter(rows, filters.FILTER_RAINY), false);
        probabilities_not_play.temperature_cold = calculateProbability(filter(rows, filters.FILTER_TEMPERATURE_COLD), false);
        probabilities_not_play.temperature_mild = calculateProbability(filter(rows, filters.FILTER_TEMPERATURE_MILD), false);
        probabilities_not_play.temperature_hot = calculateProbability(filter(rows, filters.FILTER_TEMPERATURE_HOT), false);
        probabilities_not_play.humidity_high = calculateProbability(filter(rows, filters.FILTER_HUMIDITY_HIGH), false);
        probabilities_not_play.humidity_normal = calculateProbability(filter(rows, filters.FILTER_HUMIDITY_NORMAL), false);
        probabilities_not_play.humidity_low = calculateProbability(filter(rows, filters.FILTER_HUMIDITY_LOW), false);
        probabilities_not_play.windy = calculateProbability(filter(rows, filters.FILTER_WINDY), false);
        probabilities_not_play.not_windy = calculateProbability(filter(rows, filters.FILTER_NOT_WINDY), false);
    }

    private List<Row> filter(List<Row> rows, Row filter) {
        List<Row> result = new ArrayList<>();
        for (Row row : rows) {
            if ((filter.getOutlook() == null || filter.getOutlook().equals(row.getOutlook())) &&
                    (filter.getTemperature() == null || filter.getTemperature().equals(row.getTemperature())) &&
                    (filter.getHumidity() == null || filter.getHumidity().equals(row.getHumidity())) &&
                    (filter.getWindy() == null || filter.getWindy().equals(row.getWindy()))) {
                result.add(row);
            }
        }
        return result;
    }

    private float calculateProbability(List<Row> rows, boolean play) {
        int totalCount = rows.size();
        int occurrenceCount = 0;
        for (Row row : rows) {
            if (row.getPlay().equals(play)) {
                occurrenceCount++;
            }
        }
        return totalCount / (float) occurrenceCount;
    }

    public boolean classification(Outlook outlook, Temperature temperature, Humidity humidity, boolean windy) {
        float probabilityPlay = probabilities_play.general;
        float probabilityNotPlay = probabilities_not_play.general;

        switch (outlook) {
            case SUNNY:
                probabilityPlay *= probabilities_play.sunny;
                probabilityNotPlay *= probabilities_not_play.sunny;
                break;
            case OVERCAST:
                probabilityPlay *= probabilities_play.overcast;
                probabilityNotPlay *= probabilities_not_play.overcast;
                break;
            case RAINY:
                probabilityPlay *= probabilities_play.rainy;
                probabilityNotPlay *= probabilities_not_play.rainy;
                break;
        }

        switch (temperature) {
            case COLD:
                probabilityPlay *= probabilities_play.temperature_cold;
                probabilityNotPlay *= probabilities_not_play.temperature_cold;
                break;
            case MILD:
                probabilityPlay *= probabilities_play.temperature_mild;
                probabilityNotPlay *= probabilities_not_play.temperature_mild;
                break;
            case HOT:
                probabilityPlay *= probabilities_play.temperature_hot;
                probabilityNotPlay *= probabilities_not_play.temperature_hot;
                break;
        }

        switch (humidity) {
            case HIGH:
                probabilityPlay *= probabilities_play.humidity_high;
                probabilityNotPlay *= probabilities_not_play.humidity_high;
                break;
            case NORMAL:
                probabilityPlay *= probabilities_play.humidity_normal;
                probabilityNotPlay *= probabilities_not_play.humidity_normal;
                break;
            case LOW:
                probabilityPlay *= probabilities_play.humidity_low;
                probabilityNotPlay *= probabilities_not_play.humidity_low;
                break;
        }

        if (windy) {
            probabilityPlay *= probabilities_play.windy;
            probabilityNotPlay *= probabilities_not_play.windy;
        } else {
            probabilityPlay *= probabilities_play.not_windy;
            probabilityNotPlay *= probabilities_not_play.not_windy;
        }

        return (probabilityPlay > probabilityNotPlay);
    }

}


