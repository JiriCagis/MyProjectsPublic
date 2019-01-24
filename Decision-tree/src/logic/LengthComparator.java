package logic;

import java.util.Comparator;

public class LengthComparator implements Comparator<String> {

    @Override
    public int compare(String text1, String text2) {
        if (text1.length() > text2.length()) {
            return 1;
        } else if (text1.length() < text2.length()) {
            return -1;
        } else {
            return 0;
        }
    }

}
