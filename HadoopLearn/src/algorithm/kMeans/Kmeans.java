package algorithm.kMeans;

import algorithm.kMeans.data.Vertex;
import algorithm.kMeans.mapreduce.FindClusterMapReduce;
import algorithm.kMeans.mapreduce.NewCentroidsMapReduce;
import algorithm.kMeans.mapreduce.PotentialCentroidsMapReduce;
import algorithm.kMeans.utils.Mathematic;

import java.util.List;

/**
 * K-means is used for machine learning or data analysis. Purpose is find clusters in data set.
 * This implementation support find clusters in 2d decimal vectors.
 * <p>
 * ASSUMPTIONS:
 * N ... user specify how many clusters want find by algorithm in data set
 * <p>
 * How work:
 * 1. Load first N vectors from input file and denote as previous clusters
 * 2. Divide all vertices to group by distance of previous centroids and calculate average vector in groups.
 * Denote this vector as potential centroid.
 * 3. For each potential centroid find nearest point from data set and mark as new centroid.
 * 4. Verify if previous and new centroids are same. If centroids are same, algorithm finish and show clusters.
 * Else assign to previous clusters variable new clusters and go back to step 2.
 */
public class Kmeans {
    private final String INPUT_PATH;
    private final String OUTPUT_PATH;
    private final int COUNT_CLUSTER;

    private Vertex[] result;

    /**
     * Constructor, after create instantly calculate clusters in data set
     *
     * @param inputPath     text file with 2D decimal vertices in form 1 line per 1 vector
     * @param outputPath    output folder for hadoop result
     * @param count_cluster how many clusters you can find
     *                      <p>
     *                      Input file example:
     *                      1.23,1.34
     *                      4.23,23.4
     *                      1,2
     */
    public Kmeans(String inputPath, String outputPath, int count_cluster) {
        INPUT_PATH = inputPath;
        OUTPUT_PATH = outputPath;
        COUNT_CLUSTER = count_cluster;
    }

    public boolean execute(){
        Vertex[] previousCentroids = Mathematic.loadCentroids(INPUT_PATH, COUNT_CLUSTER);
        while (true) {
            Vertex[] centroids = calculatePotentialCentroids(previousCentroids);
            Vertex[] new_centroids = centroids;
            if (isSame(previousCentroids, new_centroids)) {
                result = new_centroids;
                return true;
            } else {
                previousCentroids = new_centroids;
            }
        }
    }


    private Vertex[] calculatePotentialCentroids(Vertex[] previousCentroids) {
        PotentialCentroidsMapReduce mapReduce = new PotentialCentroidsMapReduce();
        mapReduce.setInputPath(INPUT_PATH);
        mapReduce.setOutputPath(OUTPUT_PATH);
        mapReduce.setPreviousCentroids(previousCentroids);

        mapReduce.configureJob();

        if (mapReduce.execute() == true) {
            return mapReduce.getResult(COUNT_CLUSTER);
        }
        return null;
    }

    private Vertex[] calculateNewCentroids(Vertex[] potentialCentroids) {
        NewCentroidsMapReduce mapReduce = new NewCentroidsMapReduce();
        mapReduce.setInputPath(INPUT_PATH);
        mapReduce.setOutputPath(OUTPUT_PATH);
        mapReduce.setPotentialCentroids(potentialCentroids);

        mapReduce.configureJob();

        if (mapReduce.execute() == true) {
            return mapReduce.getResult(COUNT_CLUSTER);
        }
        return null;
    }

    private String[] findClusters(Vertex[] centroids) {
        FindClusterMapReduce mapReduce = new FindClusterMapReduce();
        mapReduce.setInputPath(INPUT_PATH);
        mapReduce.setOutputPath(OUTPUT_PATH);
        mapReduce.setCentroids(centroids);

        mapReduce.configureJob();

        if(mapReduce.execute()==true){
            return mapReduce.getResult(COUNT_CLUSTER);
        }
        return null;
    }

    /**
     * Function decide if are lists of centroids same, compute difference between
     * X and Y coordinate. If difference is biggest then 0.5 list of centroids not
     * same
     *
     * @param oldCentroids array of centroids
     * @param newCentroids array of centroids
     * @return return true if difference coordinates is smaller then 0.5
     */
    private boolean isSame(Vertex[] oldCentroids, Vertex[] newCentroids) {
        for (int i = 0; i < oldCentroids.length; i++) {
            float[] oldCoordinates = oldCentroids[i].getCoordinates();
            float[] newCoordinates = newCentroids[i].getCoordinates();

            for (int k = 0; k < oldCoordinates.length; k++) {
                float x1 = oldCoordinates[k];
                float x2 = newCoordinates[k];
                float differenceX = Math.abs(x1 - x2);
                if (differenceX > 0.5)
                    return false;
            }
        }
        return true;
    }

    public Vertex[] getFindCentroids() {
        return result;
    }

    public String[] getClusters(){
        return findClusters(result);
    }

    public static void main(String[] args) {
        Kmeans kmeans = new Kmeans("inputs/graph.txt", "output", 2);
        if(kmeans.execute()){
            for(Vertex vertex:kmeans.getFindCentroids()){
                System.out.println(vertex);
            }

            System.out.println("Clusters");
            for(String line:kmeans.getClusters()){
                System.out.println(line);
            }
        }
    }
}


