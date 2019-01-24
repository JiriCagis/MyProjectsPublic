package stack;

import java.util.ArrayList;
import java.util.List;

/**
 * My own data structure Stack for place generic object T. Stack is unlimited, you can place arbitrary count items.
 * Main function is push() for store items to stack and function pop() for get last item added on stack and remove it.
 * Others functions are supported, for example top() return last item added on stack, but not remove item from stack or
 * function isEmpty() use for getting information about cluttered stack.
 *
 * Note: Implement for JUNIT testing purpose.
 * Created by cagaj on 11.7.2016.
 */
public class Stack<T> {

    private List<T> items;

    public Stack() {
        this.items = new ArrayList();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public void push(T item) {
        items.add(0, item);
    }

    public T pop() throws Exception {
        if (isEmpty()) {
            throw new Exception("Cannot pop an empty stack.");
        }

        T top = items.get(0);
        items.remove(0);
        return top;
    }

    public T top() throws Exception{
        if(isEmpty()){
            throw new Exception("Cannot top an empty stack.");
        }
        return items.get(0);
    }
}
