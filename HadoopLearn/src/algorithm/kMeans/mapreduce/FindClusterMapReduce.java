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
import java.util.List;

/**
 * Map reduce function calculate clusters by centroids on data set.
 * Explain:
 * Map function assign each vertex to nearest centroid.
 * Reduce function bind all vertices for centroid.
 */
public class FindClusterMapReduce extends MapReduce<String> {
    private static final String SEPARATOR = "\t";
    private String centroids; // line with all centroids for map reduce task as global variable


    @Override
    public void configureJob() {
        try {
            Configuration conf = new Configuration();
            job = Job.getInstance(conf, "New centroids");
            job.setJarByClass(MapReduce.class);
            job.setMapperClass(MyMapper.class);
            job.setCombinerClass(MyReducer.class);
            job.setReducerClass(MyReducer.class);
            job.setOutputKeyClass(Vertex.class);
            job.setOutputValueClass(Text.class);
            job.getConfiguration().set("CENTROIDS", centroids);
            FileInputFormat.addInputPath(job, new Path(inputPath));
            FileOutputFormat.setOutputPath(job, new Path(outputPath));
        } catch (Exception e) {
            showFailMessage();
        }
    }

    @Override
    public String[] getResult(int MAX) {
        List<String> lines = loadLines(MAX);
        return lines.toArray(new String[lines.size()]);
    }

    public void setCentroids(Vertex[] centroids) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < centroids.length - 1; i++) {
            builder.append(centroids[i] + SEPARATOR);
        }
        builder.append(centroids[centroids.length - 1]);
        this.centroids = builder.toString();
    }

    public static class MyMapper extends Mapper<Object, Text, Vertex, Text> {
        private Vertex[] CENTROIDS;

        public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
            Vertex vertex = Mathematic.convert(value.toString());
            float minDistance = Float.MAX_VALUE;
            Vertex minCentroid = null;
            for (Vertex centroid : CENTROIDS) {
                float distance = Mathematic.calculateDistance(vertex, centroid);
                if (distance < minDistance) {
                    minCentroid = centroid;
                    minDistance = distance;
                }
            }
            String value2 = "(" + vertex.toString() + ")";
            context.write(minCentroid, new Text(vertex.toString()));
        }

        @Override
        protected void setup(Context context) throws IOException, InterruptedException {
            super.setup(context);
            Configuration conf = context.getConfiguration();
            String[] centroidsString = conf.get("CENTROIDS").split(SEPARATOR);
            CENTROIDS = new Vertex[centroidsString.length];
            for (int i = 0; i < centroidsString.length; i++) {
                CENTROIDS[i] = Mathematic.convert(centroidsString[i]);
            }
        }
    }

    public static class MyReducer extends Reducer<Vertex, Text, Vertex, Text> {

        @Override
        public void reduce(Vertex centroid, Iterable<Text> intermediate, Context context) throws IOException, InterruptedException {
            StringBuilder builder = new StringBuilder();
            for (Text item : intermediate) {
                builder.append(item.toString()+" ");
            }
            builder.deleteCharAt(builder.length()-1);

            Text result = new Text(builder.toString());
            context.write(centroid, result);
        }

    }

}
