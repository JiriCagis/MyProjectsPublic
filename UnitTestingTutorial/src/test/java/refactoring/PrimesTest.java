package refactoring;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;


/**
 * Example using framework JUNIT for testing my generator primes numbers.
 * Goal this testing is verify successful behavior all methods and mathematical logic.
 * In later time unit tests are useful when you add functionality or refactor.
 * Because re-run tests uncover errors causing by your changes in code class Stack.
 * <p>
 * Created by cagaj on 13.7.2016.
 */
public class PrimesTest {

    private int[] knowPrimes = new int[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29};

    @Test
    public void zero() {
        int[] primes = Primes.generateArray(0);
        assertEquals(0, primes.length);
    }

    @Test
    public void listZero() {
        List<Integer> primes = Primes.generate(0);
        assertEquals(0, primes.size());
    }

    @Test
    public void single() {
        int primes[] = Primes.generateArray(2);
        assertEquals(1, primes.length);
    }

    @Test
    public void listSingle() {
        List primes = Primes.generate(2);
        assertEquals(1, primes.size());
    }

    @Test
    public void primes() {
        int[] centArray = Primes.generateArray(100);
        assertEquals(25, centArray.length);
        assertEquals(97, centArray[24]);
    }

    @Test
    public void listPrimes() {
        List centArray = Primes.generate(100);
        assertEquals(25, centArray.size());
        assertEquals(97, centArray.get(24));
    }

    @Test
    public void basic() {
        int primes[] = Primes.generateArray(knowPrimes[knowPrimes.length - 1]);
        assertEquals(knowPrimes.length, primes.length);

        for (int i = 0; i < primes.length; i++) {
            assertEquals(knowPrimes[i], primes[i]);
        }
    }

    @Test
    public void listBasic() {
        List primes = Primes.generate(knowPrimes[knowPrimes.length - 1]);
        assertEquals(knowPrimes.length, primes.size());

        for (int i = 0; i < primes.size(); i++) {
            assertEquals(knowPrimes[i], primes.get(i));
        }
    }

    @Test
    public void lots() {
        int bound = 10000;
        int primes[] = Primes.generateArray(bound);

        for (int prime : primes) {
            assertTrue(isPrime(prime));
        }
    }

    @Test
    public void listLots(){
        int bound = 10000;
        List<Integer> primes = Primes.generate(bound);

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