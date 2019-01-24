package algorithm.kMeans.mapreduce;

import org.apache.hadoop.mapreduce.Job;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Template class for easy implement map reduce algorithm and their encapsulating.
 * <p>
 * HOW USE TEMPLATE:  IMPORTANT!!!
 * 1.   First create object and set all attributes as (inputPath,outputPath, etc...)
 * 2.   Call method configureJob()
 * 3.   Call method execute and wait then method return flag(true successful finish)
 * 4.   After return flag method execute, you can call method getResult()
 */
public abstract class MapReduce<T> {
    protected String inputPath;
    protected String outputPath;
    protected Job job;

    /**
     * Constructor
     */
    public MapReduce() {
        inputPath = "";
        outputPath = "";
    }

    /**
     * Method init mapreduce algorithm, as Map class, Combine class and
     * Reduce class.
     */
    public abstract void configureJob();

    /**
     * Function get set of result records. You can call this method after finish method execute()
     *
     * @param MAX max count load records
     * @return array of records
     */
    public abstract T[] getResult(int MAX);

    /**
     * Main function starts Map Reduce algorithm on Hadoop.
     * IMPORTANT: Function take a lot of time(According size input), wait then it return boolean flag before call another method.
     */
    public boolean execute() {
        deleteDirectory(new File(outputPath)); //remove output directory before start Hadoop
        try {
            if (job.waitForCompletion(true)) {
                showSuccessfulMessage();
                return true;
            } else {
                showFailMessage();
            }
        } catch (Exception e) {
            showFailMessage();
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Helpful function for load result lines from output directory
     *
     * @param MAX max count line get from file
     * @return list of lines
     */
    protected List<String> loadLines(int MAX) {
        List<String> lines = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(outputPath + "/part-r-00000"));
            for (int i = 0; i < MAX; i++) {
                String line;
                if ((line = reader.readLine()) != null) {
                    lines.add(line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lines;
    }

    /**
     * Function delete directory
     *
     * @param directory for remove
     * @return true after successful delete, else return false
     */
    public static boolean deleteDirectory(File directory) {
        if (directory.exists()) {
            File[] files = directory.listFiles();
            if (null != files) {
                for (int i = 0; i < files.length; i++) {
                    if (files[i].isDirectory()) {
                        deleteDirectory(files[i]);
                    } else {
                        files[i].delete();
                    }
                }
            }
        }
        return (directory.delete());
    }

    protected void showSuccessfulMessage() {
        System.err.println("MSG: " + this.getClass().getName() + " WORK SUCCESSFUL :)");
    }

    protected void showFailMessage() {
        System.err.println("MSG: " + this.getClass().getName() + " FAILED :( ");
    }

    public void setInputPath(String inputPath) {
        this.inputPath = inputPath;
    }

    public void setOutputPath(String outputPath) {
        this.outputPath = outputPath;
    }
}
