package behavior.iterator;

/**
 * Created by osboxes on 08/01/18.
 */
public class IteratorExample {

    public static void main(String[] args){
        MyNumbersArray array = new MyNumbersArray(3);
        array.setItem(0,1);
        array.setItem(1,2);
        array.setItem(2,3);

        while(array.hasNext()){
            System.out.println(array.next());
        }
    }
}
