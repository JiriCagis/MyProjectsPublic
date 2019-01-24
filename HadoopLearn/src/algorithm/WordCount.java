package algorithm;

import java.io.File;
import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import util.MyHadoopUtil;
import util.stemmer.PorterStemmer;
import util.stemmer.Stemmer;

/**
 * Program calculate count words in text. For calculate is used algorithm Map
 * Reduce which divide task to several small part calculate parallel. After
 * integrate output part to result. Algorithm Map reduce is execute in framework
 * Hadoop. Result is save into folder output and withal show first 50 word in
 * terminal.
 *
 * @author adminuser
 */
public class WordCount {

    /**
     * Constructor Steps: 1. Delete content output folder 2. Init framework
     * Hadoop 3. Execute calculation 4. Show result from task
     *
     * @param inputFile text file with words to calculate
     * @param outputFolder direction for write result
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws InterruptedException
     */
    public WordCount(String inputFile, String outputFolder) throws IOException, ClassNotFoundException, InterruptedException {
        //remove output direction before start Hadoop
        MyHadoopUtil.deleteDirectory(new File(outputFolder));

        //Init Hadoop environment
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "word count");
        job.setJarByClass(WordCount.class);
        job.setMapperClass(TokenizerMapper.class);
        job.setCombinerClass(IntSumReducer.class);
        job.setReducerClass(IntSumReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        FileInputFormat.addInputPath(job, new Path(inputFile));
        FileOutputFormat.setOutputPath(job, new Path(outputFolder));

        //Run task calculate count word in text
        if (job.waitForCompletion(true)) {
            System.err.println("HADOOP WORK SUCCESSFUL :)");
            MyHadoopUtil.showResult(new File(outputFolder), 30);
        } else {
            System.err.println("HADOOP FAILED :(");
        }
    }

    /**
     * Function go through all line text and individually word save to map.
     * Example: INPUT: ahoj svete ahoj svete OUTPUT: <ahoj,1> <svete,1> <ahoj,1>
     *  <svete,1>
     */
    public static class TokenizerMapper extends Mapper<Object, Text, Text, IntWritable> {

        private final IntWritable one = new IntWritable(1);
        private final Text word = new Text();
        private final Stemmer stemmer = new PorterStemmer();

        public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
            String line = value.toString();
            for(String word:MyHadoopUtil.splitWordsFrom(line)){
                String baseWord = stemmer.stem(word); //reduce word build on same base (fishing, fished -> fish)
                context.write(new Text(baseWord),one);
            }          
        }
    }

    /**
     * Function reduct records. Count sum word in text. Example: INPUT:
     * <ahoj,1,1>
     * OUTPUT: <ahoj,2>
     */
    public static class IntSumReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

        private IntWritable result = new IntWritable();

        @Override
        public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
            int sum = 0;
            for (IntWritable value : values) {
                sum += value.get();
            }
            result.set(sum);
            context.write(key, result);
        }

    }
}
