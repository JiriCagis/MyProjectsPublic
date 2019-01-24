package algorithm.invertedIndex;

import algorithm.invertedIndex.data.DocumentOccurrence;
import algorithm.invertedIndex.data.TotalOccurrence;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import util.MyHadoopUtil;
import util.stemmer.PorterStemmer;
import util.stemmer.Stemmer;

/**
 * Algorithm is use for find out count occurrence words in many text documents.
 * It produce list words with information about totally occurrence in all
 * documents and individually occurrence for concrete document.
 */
public class InvertedIndex {

    //substitute file name on number
    private static Map<String, Integer> fileMap;

    public InvertedIndex(String inputFile, String outputFolder) throws IOException, ClassNotFoundException, InterruptedException {
        this(new String[]{inputFile}, outputFolder);
    }

    public InvertedIndex(String[] inputFiles, String outputFolder) throws IOException, ClassNotFoundException, InterruptedException {

        Date startTime = new Date();
        //remove output direction before start Hadoop
        MyHadoopUtil.deleteDirectory(new File(outputFolder));

        //map filenames to numbers
        fileMap = new HashMap();
        for (int i = 0; i < inputFiles.length; i++) {
            fileMap.put(inputFiles[i], i + 1);
        }

        //Inicialize Hadoop enviroment
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "inverted index");
        job.setJarByClass(InvertedIndex.class);
        job.setMapperClass(MyMapper.class);
        job.setCombinerClass(MyReducer.class);
        job.setReducerClass(MyReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(TotalOccurrence.class);
        for (String inputFile : inputFiles) {
            FileInputFormat.addInputPath(job, new Path(inputFile));
        }
        FileOutputFormat.setOutputPath(job, new Path(outputFolder));

        //Run task calculate count word in text
        if (job.waitForCompletion(true)) {
            System.err.println("HADOOP WORK SUCCESSFUL :)");
            showDocumentLegend();
            MyHadoopUtil.showResult(new File(outputFolder), 30);
        } else {
            System.err.println("HADOOP FAILED :(");
        }

        Date stopTime = new Date();
        System.err.println("Operation consume: " + (stopTime.getTime() - startTime.getTime()) + "ms");
    }

    private void showDocumentLegend() {
        for (String documentName : fileMap.keySet()) {
            System.err.println("Document: " + documentName + "is substitute with number: " + fileMap.get(documentName));
        }
    }

    /**
     * Function divide line from file into words and save to map in format:
     * <word,<totally word occurence, document number, docuemnt occurence>>
     */
    public static class MyMapper extends Mapper<Object, Text, Text, TotalOccurrence> {

        private final IntWritable numberOne = new IntWritable(1);
        private final Stemmer stemmer = new PorterStemmer();

        @Override
        public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
            String text = value.toString();
            List<String> words = MyHadoopUtil.splitWordsFrom(text);

            //convert words to base form
            for (int i = 0; i < words.size(); i++) {
                String word = words.get(i);
                String baseWord = stemmer.stem(word); //get base word (fishing, fished -> fish)
                words.set(i, baseWord);
            }

            //map occurence word to shape: 
            //<'some word',[total count,document count, document number]> 
            for (String word : words) {
                DocumentOccurrence documentOccurence = new DocumentOccurrence(getDocumentNumber(context), 1);

                TotalOccurrence totalOccurence = new TotalOccurrence();
                totalOccurence.setTotalCount(numberOne);
                List<DocumentOccurrence> list = new ArrayList<>();
                list.add(documentOccurence);
                totalOccurence.setDocumentOccurences(list);

                context.write(new Text(word), totalOccurence);
            }
        }

        /**
         * Function on base context find path file and by map decide
         * substitution number to document.
         *
         * @param context application context
         * @return substitution number
         */
        private Integer getDocumentNumber(Context context) {
            String path = ((FileSplit) context.getInputSplit()).getPath().toString();
            String fileName = path.substring(path.lastIndexOf('/') + 1, path.length());
            return fileMap.get(fileName);
        }

    }

    /**
     * Reduction class unite map value for word. Example: word "pepa" ->
     * <1,1,1>,<1,1,,1>,<1,2,1> -> <3,<1,2>,<2,1>>
     */
    public static class MyReducer extends Reducer<Text, TotalOccurrence, Text, TotalOccurrence> {

        private Map<Integer, Integer> map = new HashMap(); //<documentNumber, count occurence>

        @Override
        public void reduce(Text key, Iterable<TotalOccurrence> values, Context context) throws IOException, InterruptedException {
            map.clear();
            int totalCount = 0;
            for (TotalOccurrence totalOccurence : values) {
                for (DocumentOccurrence documentOccurence : totalOccurence.getDocumentOccurences()) {
                    int documentNumber = documentOccurence.getDocumentNumber().get();
                    int count = documentOccurence.getCount().get();
                    saveToMap(documentNumber, count);
                    totalCount += count;
                }
            }

            TotalOccurrence totalOccurence = new TotalOccurrence();
            totalOccurence.setTotalCount(totalCount);
            totalOccurence.setDocumentOccurences(convertMap());
            context.write(key, totalOccurence);
        }

        private void saveToMap(int documentNumber, int count) {
            if (map.get(documentNumber) != null) {
                map.put(documentNumber, map.get(documentNumber) + count);
            } else {
                map.put(documentNumber, count);
            }
        }

        private List<DocumentOccurrence> convertMap() {
            List<DocumentOccurrence> result = new ArrayList<DocumentOccurrence>();
            for (int documentNumber : map.keySet()) {
                int count = map.get(documentNumber);
                DocumentOccurrence documentOccurence = new DocumentOccurrence();
                documentOccurence.setDocumentNumber(documentNumber);
                documentOccurence.setCount(count);
                result.add(documentOccurence);
            }
            return result;
        }
    }
}
