package behavior.iterator;

/**
 * Created by osboxes on 08/01/18.
 */
public class MyNumbersArray implements Iterator<Integer> {

    private int[] array;
    private int iterationPosition = 0;

    public MyNumbersArray(int size){
        array = new int[size];
    }

    public void setItem(int index, int value){
        if(index >= 0 && index<array.length){
            array[index] = value;
        }
    }

    public void resetIterator(){
        iterationPosition = 0;
    }

    @Override
    public boolean hasNext() {
        return iterationPosition < array.length;
    }

    @Override
    public Integer next() {
        return array[iterationPosition++];
    }
}
