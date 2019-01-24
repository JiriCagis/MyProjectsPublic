package algorithm.kMeans;

import algorithm.kMeans.data.Vertex;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by admin on 31/03/16.
 */
public class Report2 {

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        Kmeans kmeans = null;
        int count_cluster = 7;
        StringBuilder builder = new StringBuilder();

        kmeans = new Kmeans("inputs/iris.data.txt", "output", count_cluster);
        if (kmeans.execute() == true) {
            Vertex[] centroids = kmeans.getFindCentroids();
            for (Vertex centroid : centroids) {
                builder.append(centroid+" ");
            }
            builder.deleteCharAt(builder.length()-1);
            builder.append("\n");
            for(String cluster:kmeans.getClusters()){
                cluster = cluster.split("\t")[1];
                String[] vertices = cluster.split(" ");
                for(String vertex:vertices){
                    builder.append(vertex+"\n");
                }
                builder.append("\n");
            }
        }

        PrintWriter writer = new PrintWriter("irisdataset_kmeans_hadoop_"+count_cluster+"clusters.txt", "UTF-8");
        writer.print(builder.toString());
        writer.close();

        System.out.print(builder.toString());
    }
}
