package stack;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example using framework JUNIT for testing my own data structure Stack.
 * Goal this testing is verify successful behavior all methods and Stack logic.
 * In later time unit tests are useful when you add functionality or refactor.
 * Because re-run tests uncover errors causing by your changes in code class Stack.
 *
 * Base tests:
 *  - Create a Stack and verify that isEmpty is true
 *  - Push a single object on the Stack and verify that IsEmpty is false
 *  - Push a single object, Pop the object and verify that isEmpty is true
 *  - Push a single object, remembering what they are: Pop each one, and verify that they
 *    are removed in the correct order.
 *  - Pop a Stack that has no elements
 *  - ...
 *
 * Created by cagaj on 11.7.2016.
 */
public class StackTest {

    private Stack<String> stack;

    @Before
    public void init() {
        stack = new Stack();
    }

    @Test
    public void empty() {
        assertEquals("Stack will be empty after create.", true, stack.isEmpty());
    }

    @Test
    public void PushOne() {
        stack.push("item1");
        assertEquals("After push, isEmpty() should be false.", false, stack.isEmpty());
    }

    @Test
    public void Pop() throws Exception {
        stack.push("item1");
        stack.pop();
        assertEquals("After Push - Pop, IsEmpty() should be true", true, stack.isEmpty());
    }

    @Test
    public void pushPopContentCheck() throws Exception {
        String expected = "item1 - apple";
        stack.push(expected);
        String actual = stack.pop();
        assertEquals("When push item and pop item immediate should be same.", expected, actual);
    }

    @Test
    public void pushPopMultipleElements() throws Exception {
        final String pushed1 = "1";
        stack.push(pushed1);
        final String pushed2 = "2";
        stack.push(pushed2);
        final String pushed3 = "3";
        stack.push(pushed3);

        assertEquals(pushed3, stack.pop());
        assertEquals(pushed2, stack.pop());
        assertEquals(pushed1, stack.pop());
    }

    @Test
    public void popEmptyStack() throws Exception {
        boolean raiseExpection = false;
        try {
            stack.pop();
        } catch (Exception e) {
            if (e.getMessage().equals("Cannot pop an empty stack.")) {
                raiseExpection = true;
            }
        }
        assertEquals("Empty stack should be raise exception when call pop()", true, raiseExpection);
    }

    @Test
    public void pushTop() throws Exception {
        stack.push("item1");
        stack.top();
        Assert.assertFalse(stack.isEmpty());
    }

    @Test
    public void pushTopContentCheckOneElement() throws Exception {
        String pushed = "42";
        stack.push(pushed);
        String topped = stack.top();
        assertEquals(pushed, topped);
    }

    @Test
    public void pushTopContentCheckMultiples() throws Exception {
        String items[] = {"item1", "item2", "item3"};
        stack.push(items[0]);
        stack.push(items[1]);
        stack.push(items[2]);
        assertEquals(items[2], stack.top());
    }

    @Test
    public void pushTopNoStackChange() throws Exception {
        String pushed = "item1";
        stack.push(pushed);
        for (int i = 0; i < 10; i++) {
            assertEquals(pushed, stack.top());
        }
    }

    @Test
    public void topEmptyStack() {
        boolean raisedException = false;
        try {
            stack.top();
        } catch (Exception e) {
            if (e.getMessage().equals("Cannot top an empty stack.")) {
                raisedException = true;
            }
        }
        assertTrue("Empty stack raise exception when call top()",raisedException);
    }

    @Test
    public void pushNull(){
        stack.push(null);
        assertFalse(stack.isEmpty());
    }

    @Test
    public void pushNullCheckPop() throws Exception {
        stack.push(null);
        stack.pop();
        assertTrue(stack.isEmpty());
    }

    @Test
    public void pushNullCheckTop() throws Exception{
        stack.push(null);
        assertNull(stack.top());
        assertFalse(stack.isEmpty());
    }

}