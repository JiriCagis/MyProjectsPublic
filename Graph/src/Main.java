import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jiri Caga on 5.11.15.
 */
public class Main {

    public static void main(String[] args){
        File dataSourceFile = new File("karate_club.csv");
        if(!dataSourceFile.exists()){
            downloadDataSource("http://www.cs.vsb.cz/ochodkova/courses/MADI/KarateClub.csv",dataSourceFile);
        }
        List<String[]> edges = parseCSV(dataSourceFile,";");


        System.out.println("Matrix graph");
        Graph matrixGraph = new MatrixGraph(34);
        for(String[] edge:edges){
            int vertex1 = Integer.parseInt(edge[0]);
            int vertex2 = Integer.parseInt(edge[1]);
            vertex1-=1;
            vertex2-=1;
            matrixGraph.addEdge(vertex1,vertex2);
            matrixGraph.addEdge(vertex2,vertex1);
        }



        System.out.println("Min vertex degree: "+matrixGraph.calculateMinDegreeVertex());
        System.out.println("Max vertex degree: "+matrixGraph.calculateMaxDegreeVertex());
        System.out.println("Average vertex degree: "+matrixGraph.calculateAverageDegreeVertex());
        System.out.println("Mean distance");
        System.out.println("Frequency degree");
        matrixGraph.showFrequencyDegree();

        System.out.println("Closeness centrality");
        double[] array = matrixGraph.closenessCentrality();
        for(int i=0;i<array.length;i++){
            System.out.println((i+1) + " - " + array[i]);
        }

        matrixGraph.calculateFloydAlgorithm();
        System.out.print(matrixGraph);


    }

    private static void downloadDataSource(String urlString,File fileForSave){
        System.out.println("Downloading data source...");
        URL url;
        try {
            url = new URL(urlString);
            ReadableByteChannel rbc = Channels.newChannel(url.openStream());
            FileOutputStream fos = new FileOutputStream(fileForSave);
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        } catch (MalformedURLException ex) {
            System.err.println("Insert malformed url when try download data file.");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<String[]> parseCSV(File file,String separator){
        List<String[]> result = new ArrayList<>();
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = null;
            while((line = br.readLine())!=null){
                String[] attributes = line.split(separator);
                result.add(attributes);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }
}
