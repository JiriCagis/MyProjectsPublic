package iris;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * Analysis data about iris, from URL:
 * "https://archive.ics.uci.edu/ml/machine-learning-databases/iris/iris.data".
 * Calculate means and variance above columns(sepal length, sepal width, petal
 * width, petal length). Last part is decide distance between two point and
 * cosine similarity.
 * <p/>
 * test
 *
 * @author cag0008
 */
public class Iris {

    private enum Column {

        SEPAL_LENGTH, SEPAL_WIDTH, PETAL_WIDTH, PETAL_LENGTH
    }

    ;

    private static class Row {

        private float sepalLength;
        private float sepalWidth;
        private float petalLength;
        private float petalWidth;

        public Row() {

        }

        public float getSepalLength() {
            return sepalLength;
        }

        public void setSepalLength(float sepalLength) {
            this.sepalLength = sepalLength;
        }

        public float getSepalWidth() {
            return sepalWidth;
        }

        public void setSepalWidth(float sepalWidth) {
            this.sepalWidth = sepalWidth;
        }

        public float getPetalLength() {
            return petalLength;
        }

        public void setPetalLength(float petalLength) {
            this.petalLength = petalLength;
        }

        public float getPetalWidth() {
            return petalWidth;
        }

        public void setPetalWidth(float petalWidth) {
            this.petalWidth = petalWidth;
        }

    }

    public static void main(String[] args) {
        File file = new File("iris.data.txt");
        if (!file.exists()) {
            System.out.println("Downloading data source...");
            URL url;
            try {
                url = new URL("https://archive.ics.uci.edu/ml/machine-learning-databases/iris/iris.data");
                downloadFile(url, file);
            } catch (MalformedURLException ex) {
                System.err.println("Insert malformed url when try download data file.");
            }
        }
        List<Row> rows = loadDataFromCSV(file);

        System.out.print("\nCalculate means on attributes..\n");
        float avg1 = calculateAverage(rows, Column.SEPAL_LENGTH);
        float avg2 = calculateAverage(rows, Column.SEPAL_WIDTH);
        float avg3 = calculateAverage(rows, Column.PETAL_LENGTH);
        float avg4 = calculateAverage(rows, Column.PETAL_WIDTH);

        System.out.println("Mean attribute Sepal Length: " + avg1);
        System.out.println("Mean attribute Sepal Width: " + avg2);
        System.out.println("Mean attribute Petal Length: " + avg3);
        System.out.println("Mean attribute Petal Width: " + avg4);

        System.out.print("\nCalculate variance on attributes..\n");
        System.out.println("Variance attribute Sepal Length: " + calculateVariance(rows, avg1, Column.SEPAL_LENGTH));
        System.out.println("Variance attribute Sepal Width: " + calculateVariance(rows, avg2, Column.SEPAL_WIDTH));
        System.out.println("Variance attribute Petal Length: " + calculateVariance(rows, avg3, Column.PETAL_LENGTH));
        System.out.println("Variance attribute Petal Width: " + calculateVariance(rows, avg4, Column.PETAL_WIDTH));

        System.out.println("\nCalculate distance between [row1] and [row2]..'n");
        System.out.println(calculateDistanceBetween(rows.get(0), rows.get(1)));

        System.out.println("\nCalculate cosine similarity rows [row1] and [row2]..'n");
        System.out.println(calculateCosineSimilarity(rows.get(0), rows.get(1)));

    }

    private static boolean downloadFile(URL url, File fileForSave) {
        try {
            ReadableByteChannel rbc = Channels.newChannel(url.openStream());
            FileOutputStream fos = new FileOutputStream(fileForSave);
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            return true;
        } catch (IOException ex) {
            System.err.println("Error with download file from URL:" + url.getPath());
            return false;
        }
    }

    private static List<Row> loadDataFromCSV(File file) {
        List<Row> result = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] attributes = line.split(",");
                if (attributes != null && attributes.length == 5) {
                    Row row = new Row();
                    row.setSepalLength(Float.parseFloat(attributes[0]));
                    row.setSepalWidth(Float.parseFloat(attributes[1]));
                    row.setPetalLength(Float.parseFloat(attributes[2]));
                    row.setPetalWidth(Float.parseFloat(attributes[3]));
                    result.add(row);
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static float calculateAverage(List<Row> rows, Column column) {
        float totalCount = 0;
        switch (column) {
            case SEPAL_LENGTH:
                for (Row row : rows) {
                    float value = row.getSepalLength();
                    totalCount += value;
                }
                break;
            case SEPAL_WIDTH:
                for (Row row : rows) {
                    float value = row.getSepalWidth();
                    totalCount += value;
                }
                break;
            case PETAL_LENGTH:
                for (Row row : rows) {
                    float value = row.getPetalLength();
                    totalCount += value;
                }
                break;
            case PETAL_WIDTH:
                for (Row row : rows) {
                    float value = row.getPetalWidth();
                    totalCount += value;
                }
                break;

        }
        return totalCount / rows.size();
    }

    private static float calculateVariance(List<Row> rows, float average, Column column) {
        float sum = 0;
        switch (column) {
            case SEPAL_LENGTH:
                for (Row row : rows) {
                    float value = row.getSepalLength() - average;
                    value = value * value;
                    sum += value;
                }
                break;
            case SEPAL_WIDTH:
                for (Row row : rows) {
                    float value = row.getSepalWidth() - average;
                    value = value * value;
                    sum += value;
                }
                break;
            case PETAL_LENGTH:
                for (Row row : rows) {
                    float value = row.getPetalLength() - average;
                    value = value * value;
                    sum += value;
                }
                break;
            case PETAL_WIDTH:
                for (Row row : rows) {
                    float value = row.getPetalWidth() - average;
                    value = value * value;
                    sum += value;
                }
                break;

        }
        return sum / rows.size();
    }

    /**
     * Calculate coal similarity by cosine. Scalar product divided by the
     * product of their size.
     *
     * @param row1 row for compare
     * @param row2 row 2 for compare
     * @return cosine between two coal
     */
    private static double calculateCosineSimilarity(Row row1, Row row2) {
        return scalarProduct(row1, row2) / (sizeVector(row1) * sizeVector(row2));
    }

    private static double calculateDistanceBetween(Row row1, Row row2) {
        Row vector = new Row(); //start from row1 and end in row2
        vector.setSepalLength(row1.getSepalLength() - row2.getSepalLength());
        vector.setSepalWidth(row1.getSepalWidth() - row2.getSepalWidth());
        vector.setPetalLength(row1.getPetalLength() - row2.getPetalLength());
        vector.setSepalWidth(row1.getPetalWidth() - row2.getPetalWidth());
        return sizeVector(vector);
    }

    private static double scalarProduct(Row row1, Row row2) {
        return (row1.getPetalLength() * row2.getPetalLength())
                + (row1.getPetalWidth() * row2.getPetalWidth())
                + (row1.getSepalLength() * row2.getSepalLength())
                + (row1.getSepalWidth() * row2.getSepalWidth());
    }

    private static double sizeVector(Row row) {
        return Math.sqrt((row.getPetalLength() * row.getPetalLength())
                + (row.getPetalWidth() * row.getPetalWidth())
                + (row.getSepalLength() * row.getSepalLength())
                + (row.getSepalWidth() * row.getSepalWidth()));
    }

}
