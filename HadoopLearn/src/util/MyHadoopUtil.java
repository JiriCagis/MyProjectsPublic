package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Useful method for easy work with framework Hadoop.
 */
public class MyHadoopUtil {

    /**
     * Prevent create this object.
     */
    private MyHadoopUtil() {

    }

    /**
     * Function go through all text file in outputFolder and show content result
     * file on terminal window.
     *
     * @param outputFolder
     */
    public static void showResult(File outputFolder, int maxRow) {
        System.err.println("------------------------------------");
        System.err.println("Result");
        System.err.println("------------------------------------");
        int row = 0;
        for (File file : outputFolder.listFiles()) {
            if (file.getName().equals("part-r-00000")) {
                BufferedReader br = null;
                try {

                    String sCurrentLine;
                    br = new BufferedReader(new FileReader(file));
                    while ((sCurrentLine = br.readLine()) != null && row++ < maxRow) {
                        System.err.println(sCurrentLine);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (br != null) {
                            br.close();
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
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

    /**
     * Function find all words on line and return list word. Word is text
     * contain only alphabet a-z or A-Z. Skip numbers and special chars.
     *
     * @param text line with many words
     * @return list words
     */
    public static List<String> splitWordsFrom(String text) {
        List<String> list = new ArrayList<String>();
        int start = 0;
        for (int i = 0; i < text.length(); i++) {
            char character = text.charAt(i);

            //last word on line
            if (i == text.length() - 1 && start != i) {
                String word = text.substring(start, text.length());
                list.add(word);
                break;
            }

            if (((character >= 'a' && character <= 'z')
                    || (character >= 'A' && character <= 'Z'))
                    && (i < text.length() - 1)) {
                continue;
            }

            if (start != i) {
                String word = text.substring(start, i);
                list.add(word);
            }
            start = i + 1;
        }

        return list;
    }
}
