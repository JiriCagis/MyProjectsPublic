package refactoring;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * Example using framework JUNIT for testing my generator primes numbers.
 * Goal this testing is verify successful behavior all methods and mathematical logic.
 * In later time unit tests are useful when you add functionality or refactor.
 * Because re-run tests uncover errors causing by your changes in code class Stack.
 *
 * Refactoring part1:
 * 1. Remove unneeded code (remove array based tests)
 * 2. Rename method name
 * 3. Add a test for max value 1
 *
 * Created by cagaj on 13.7.2016.
 */
public class PrimesTest_Refactoring {

    private int[] knowPrimes_Refactoring = new int[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29};

    @Test
    public void zero1() {
        List<Integer> primes = Primes_Refactoring.generate(0);
        assertEquals(0, primes.size());
    }

    @Test
    public void zero2(){
        List<Integer> primes = Primes_Refactoring.generate(1);
        assertEquals(0,primes.size());
    }

    @Test
    public void single() {
        List primes = Primes_Refactoring.generate(2);
        assertEquals(1, primes.size());
    }

    @Test
    public void primes() {
        List centArray = Primes_Refactoring.generate(100);
        assertEquals(25, centArray.size());
        assertEquals(97, centArray.get(24));
    }

    @Test
    public void basic() {
        List primes = Primes_Refactoring.generate(knowPrimes_Refactoring[knowPrimes_Refactoring.length - 1]);
        assertEquals(knowPrimes_Refactoring.length, primes.size());

        for (int i = 0; i < primes.size(); i++) {
            assertEquals(knowPrimes_Refactoring[i], primes.get(i));
        }
    }

    @Test
    public void lots(){
        int bound = 10000;
        List<Integer> primes = Primes_Refactoring.generate(bound);

        for (int prime : primes) {
            assertTrue(isPrime(prime));
        }
    }

    private boolean isPrime(int number) {
        if (number < 2) {
            return false;
        }

        boolean result = true;
        double x = Math.sqrt(number);
        int i = 2;
        while (result && i <= x) {
            result = (0 != number % i);
            i += 1;
        }
        return result;
    }


}