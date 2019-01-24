import algorithm.invertedIndex.InvertedIndex;
import algorithm.WordCount;
import java.io.File;
import java.io.IOException;
import util.stemmer.PorterStemmer;
import util.stemmer.Stemmer;

public class Main {

    public static void main(String[] args) {
        String[] inputFiles = {"input.txt"};
        String outputFolder = "output";
        try {
            new InvertedIndex(inputFiles, outputFolder);
        } catch (IOException | ClassNotFoundException | InterruptedException e) {
            e.printStackTrace();
        }
        
    }
}
