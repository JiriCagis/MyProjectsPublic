package algorithm.kMeans;

import algorithm.kMeans.data.Vertex;

import java.io.IOException;
import java.util.Date;

/**
 * Created by admin on 28/02/16.
 */
public class Report {

    public static void main(String[] args) throws InterruptedException, IOException, ClassNotFoundException {
        Date startTime = new Date();
        StringBuilder builder = new StringBuilder();
        builder.append("  /\\ /\\     _ __ ___   ___  __ _ _ __  ___  \n" +
                " / //_/____| '_ ` _ \\ / _ \\/ _` | '_ \\/ __| \n" +
                "/ __ \\_____| | | | | |  __/ (_| | | | \\__ \\ \n" +
                "\\/  \\/     |_| |_| |_|\\___|\\__,_|_| |_|___/ \n" +
                "                                            \n" +
                "                 _                          \n" +
                "  /\\  /\\__ _  __| | ___   ___  _ __         \n" +
                " / /_/ / _` |/ _` |/ _ \\ / _ \\| '_ \\        \n" +
                "/ __  / (_| | (_| | (_) | (_) | |_) |       \n" +
                "\\/ /_/ \\__,_|\\__,_|\\___/ \\___/| .__/        \n" +
                "                              |_|     by Jiri Caga\n");

        for(int i=1;i<=5;i++){
            builder.append(generateSeparator(120));
            String dataset = "inputs/graph.txt";
            builder.append("Dataset: " + dataset + "\t\t count centroids: "+ i + "\n");
            Kmeans kmeans = new Kmeans(dataset,"output",i);
            kmeans.execute();
            Vertex[] result = kmeans.getFindCentroids();
            for (Vertex centroid : result) {
                String textCentroid = centroid.toString();
                textCentroid = textCentroid.replace(",","\t");
                builder.append(textCentroid+"\n");
            }
        }

        for(int i=1;i<=5;i++){
            builder.append(generateSeparator(120));
            String dataset = "inputs/iris.data.txt";
            builder.append("Dataset: " + dataset + "\t\t count centroids: "+ i + "\n");
            Kmeans kmeans = new Kmeans(dataset,"output",i);
            kmeans.execute();
            Vertex[] result = kmeans.getFindCentroids();
            for (Vertex centroid : result) {
                String textCentroid = centroid.toString();
                textCentroid = textCentroid.replace(",","\t");
                builder.append(textCentroid+"\n");
            }
        }
        Date stopTime = new Date();
        builder.append("Algorithm find all centroids in :" + (stopTime.getTime() - startTime.getTime()) + "ms.");

        System.out.print(builder.toString());
    }

    public static String generateSeparator(int length){
        StringBuilder builder = new StringBuilder();
        for(int i=0;i<length;i++){
            builder.append("-");
        }
        builder.append("\n");
        return builder.toString();
    }
}
