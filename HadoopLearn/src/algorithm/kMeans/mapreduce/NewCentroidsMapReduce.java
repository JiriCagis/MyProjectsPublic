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
 * Map reduce function for calculate new centroids from potential centroids on data set.
 * Explain:
 * Function map each vertex assign potential centroids with distance between them.
 * Function reduce select vertex for each potential centroid with minimal distance.
 */
public class NewCentroidsMapReduce extends MapReduce<Vertex> {
    private static final String SEPARATOR = "\t";
    private String potentialCentroid; // line with all centroids for map reduce task as global variable

    @Override
    public void configureJob() {
        try{
            Configuration conf = new Configuration();
            job = Job.getInstance(conf, "New centroids");
            job.setJarByClass(MapReduce.class);
            job.setMapperClass(MyMapper.class);
            job.setCombinerClass(MyReducer.class);
            job.setReducerClass(MyReducer.class);
            job.setOutputKeyClass(Vertex.class);
            job.setOutputValueClass(Text.class);
            job.getConfiguration().set("POTENTIAL_CENTROIDS",potentialCentroid);
            FileInputFormat.addInputPath(job, new Path(inputPath));
            FileOutputFormat.setOutputPath(job, new Path(outputPath));
        } catch (Exception e){
            showFailMessage();
        }
    }

    @Override
    public Vertex[] getResult(int MAX) {
        List<String> lines = loadLines(MAX);
        List<Vertex> centroids = new ArrayList<>();
        for (String line:lines) {
            String[] vertices = line.split("\t");
            String[] coordinatesString = vertices[1].split(",");
            float[] coordinates = new float[coordinatesString.length];
            for(int k=0;k<coordinates.length;k++){
                coordinates[k] = Float.parseFloat(coordinatesString[k]);
            }
            Vertex vertex = new Vertex(coordinates);
            centroids.add(vertex);
        }
        return centroids.toArray(new Vertex[centroids.size()]);
    }

    public static class MyMapper extends Mapper<Object, Text, Vertex, Text> {
        private Vertex[] POTENTIAL_CENTROIDS;

        public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
            Vertex vertex = Mathematic.convert(value.toString());
            for (Vertex centroid : POTENTIAL_CENTROIDS) {
                float distance = Mathematic.calculateDistance(vertex, centroid);
                Text intermediate = new Text(vertex.toString() + "\t" + distance);
                context.write(centroid, intermediate);
            }
        }

        @Override
        protected void setup(Context context) throws IOException, InterruptedException {
            super.setup(context);
            Configuration conf = context.getConfiguration();
            String[] centroidsString = conf.get("POTENTIAL_CENTROIDS").split(SEPARATOR);
            POTENTIAL_CENTROIDS = new Vertex[centroidsString.length];
            for(int i =0; i<centroidsString.length;i++){
                POTENTIAL_CENTROIDS[i] = Mathematic.convert(centroidsString[i]);
            }
        }
    }

    public static class MyReducer extends Reducer<Vertex, Text, Vertex, Text> {

        @Override
        public void reduce(Vertex centroid, Iterable<Text> intermediates, Context context) throws IOException, InterruptedException {
            float minDistance = Float.MAX_VALUE;
            Vertex newCentroid = null;
            for (Text intermediate : intermediates) {
                String[] items = intermediate.toString().split("\t");
                Vertex vertex = Mathematic.convert(items[0]);
                float distance = Float.parseFloat(items[1]);
                if (distance < minDistance) {
                    minDistance = distance;
                    newCentroid = vertex;
                }
            }
            Text result = new Text(newCentroid.toString() + "\t" + minDistance);
            context.write(centroid, result);
        }

    }

    public void setPotentialCentroids(Vertex[] potentialCentroids){
        StringBuilder builder = new StringBuilder();
        for(int i=0;i<potentialCentroids.length-1;i++){
            builder.append(potentialCentroids[i]+SEPARATOR);
        }
        builder.append(potentialCentroids[potentialCentroids.length-1]);
        this.potentialCentroid = builder.toString();
    }

}
