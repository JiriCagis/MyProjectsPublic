package algorithm.kMeans.mapreduce;

import algorithm.kMeans.data.Vertex;
import algorithm.kMeans.utils.Mathematic;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Map reduce function for calculate potential centroids from data set with know previous centroids.
 * Explain:
 * Function map, assign each point to nearest previous centroids.
 * Function reduce, compute sum all vertices in group and divide it count items in group.
 */
public class PotentialCentroidsMapReduce extends MapReduce<Vertex>{
    private static final String SEPARATOR = "\t";
    private String previousCentroids; // line with all centroids for map reduce task as global variable

    @Override
    public void configureJob() {
        try{
            Configuration conf = new Configuration();
            job = Job.getInstance(conf, "Potential centroids");
            job.setJarByClass(MapReduce.class);
            job.setMapperClass(MyMapper.class);
            job.setCombinerClass(MyCombiner.class);
            job.setReducerClass(MyReducer.class);
            job.setOutputKeyClass(Vertex.class);
            job.setOutputValueClass(Text.class);
            job.getConfiguration().set("PREV_CENTROIDS",previousCentroids);
            FileInputFormat.addInputPath(job, new Path(inputPath));
            FileOutputFormat.setOutputPath(job, new Path(outputPath));
        } catch (Exception e){
            showFailMessage();
        }
    }



    @Override
    public Vertex[] getResult(int MAX) {
        List<Vertex> result = new ArrayList<>();
        List<String> lines = loadLines(MAX);
        for(String line:lines){
            String[] vertices = line.split("\t");
            String[] coordinatesString = vertices[1].split(",");
            float[] coordinates = new float[coordinatesString.length];
            for(int k=0;k<coordinates.length;k++){
                coordinates[k] = Float.parseFloat(coordinatesString[k]);
            }
            Vertex vertex = new Vertex(coordinates);
            result.add(vertex);
        }
        return result.toArray(new Vertex[result.size()]);
    }

    public static class MyMapper extends Mapper<Object, Text, Vertex, Text> {
        private Vertex[] PREVIOUS_CENTROIDS;

        public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
            Vertex vertex = Mathematic.convert(value.toString());

            float minDistance = Float.MAX_VALUE;
            Vertex minCentroid = null;
            for (int i = 0; i < PREVIOUS_CENTROIDS.length; i++) {
                float distance = Mathematic.calculateDistance(vertex, PREVIOUS_CENTROIDS[i]);
                if (distance < minDistance) {
                    minDistance = distance;
                    minCentroid = PREVIOUS_CENTROIDS[i];
                }
            }

            context.write(minCentroid, new Text(vertex.toString()));
        }

        @Override
        protected void setup(Context context) throws IOException, InterruptedException {
            super.setup(context);
            Configuration conf = context.getConfiguration();
            String[] centroidsString = conf.get("PREV_CENTROIDS").split(SEPARATOR);
            PREVIOUS_CENTROIDS = new Vertex[centroidsString.length];
            for(int i=0;i<centroidsString.length;i++){
                PREVIOUS_CENTROIDS[i] = Mathematic.convert(centroidsString[i]);
            }
        }
    }

    public static class MyCombiner extends Reducer<Vertex, Text, Vertex, Text> {

        @Override
        public void reduce(Vertex centroid, Iterable<Text> vertices, Context context) throws IOException, InterruptedException {
            Vertex sum = new Vertex(centroid.getSize());
            int count = 0;
            for (Text vertexStr : vertices) {
                Vertex vertex = Mathematic.convert(vertexStr.toString());
                sum.add(vertex);
                count++;
            }
            Text intermediate = new Text(sum.toString() + SEPARATOR + count);
            context.write(centroid,intermediate);

            /*
            float sumX = 0;
            float sumY = 0;
            int count = 0;
            for (Text vertexStr : vertices) {
                Vertex vertex = Mathematic.convert(vertexStr.toString());
                vertex.ge
                sumX += vertex.getX().get();
                sumY += vertex.getY().get();
                count++;
            }
            Text intermediate = new Text(sumX + "\t" + sumY + "\t" + count);
            context.write(centroid, intermediate);
            */
        }
    }

    public static class MyReducer extends Reducer<Vertex, Text, Vertex, Vertex> {

        @Override
        public void reduce(Vertex centroid, Iterable<Text> intermediates, Context context) throws IOException, InterruptedException {
            Vertex totalSum = new Vertex(centroid.getSize());
            int totalCount = 0;

            for (Text intermediate:intermediates){
                String[] items = intermediate.toString().split(SEPARATOR);
                Vertex sum = Mathematic.convert(items[0]);
                int count = Integer.parseInt(items[1]);
                totalSum.add(sum);
                totalCount+=count;
            }

            Vertex newCentroid = totalSum;
            newCentroid.div(totalCount);
            context.write(centroid,newCentroid);
            /*
            float sumX = 0;
            float sumY = 0;
            int count = 0;
            for (Text intermediate : intermediates) {
                String items[] = intermediate.toString().split("\t");
                sumX += Float.parseFloat(items[0]);
                sumY += Float.parseFloat(items[1]);
                count += Float.parseFloat(items[2]);
            }
            context.write(centroid, new Vertex(sumX / count, sumY / count));
            */
        }
    }

    public void setPreviousCentroids(Vertex[] previousCentroids) {
        StringBuilder builder = new StringBuilder();
        for(int i=0;i<previousCentroids.length-1;i++){
            builder.append(previousCentroids[i]+SEPARATOR);
        }
        builder.append(previousCentroids[previousCentroids.length-1]);
        this.previousCentroids = builder.toString();
    }
}
