package algorithm.kMeans.utils;

import algorithm.kMeans.data.Vertex;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * Class represent mathematical function often use with calculate k-means.
 */
public class Mathematic {

    /**
     * Prevent create this library class.
     */
    private Mathematic() {
    }

    /**
     * Function take line and convert to object Vertex.
     *
     * @param line linue must be formatted as "12.34,10.23,12.4,...."
     * @return object Vertex
     */
    public static Vertex convert(String line) {
        String[] items = line.split(",");
        float[] coordinates = new float[items.length];
        for (int k = 0; k < items.length; k++) {
            coordinates[k] = Float.parseFloat(items[k]);
        }
        return new Vertex(coordinates);
    }

    /**
     * Get first n (count) centroids from input file
     *
     * @param inputFile path to file
     * @param count     count centroids for get from input file
     * @return array of centroids
     */
    public static Vertex[] loadCentroids(String inputFile, int count) {
        Vertex[] centroids = new Vertex[count];
        try {
            FileInputStream fis = new FileInputStream(inputFile);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            for (int i = 0; i < count; i++) {
                centroids[i] = convert(reader.readLine());
            }
            reader.close();
            fis.close();
        } catch (Exception e) {
            System.err.print("Error with decide centroid from input file :( ... \n");
            e.printStackTrace();
        }

        return centroids;
    }

    /**
     * Function calculate euclide distance between two vertices.
     * When vertex contains different count coordinates return value -1
     *
     * @param vertex1
     * @param vertex2
     * @return distance
     */
    public static float calculateDistance(Vertex vertex1, Vertex vertex2) {
        float result = 0;
        float[] coordinates1 = vertex1.getCoordinates();
        float[] coordinates2 = vertex2.getCoordinates();

        if (coordinates1.length != coordinates2.length) {
            return -1;
        }

        for (int k = 0; k < coordinates1.length; k++) {
            float x1 = coordinates1[k];
            float x2 = coordinates2[k];
            result+=Math.pow(x1-x2,2);
        }
        return (float) Math.sqrt(result);
    }

}
