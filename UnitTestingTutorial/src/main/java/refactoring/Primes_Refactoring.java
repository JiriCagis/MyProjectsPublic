package refactoring;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Mathematical library class for get all primes numbers in given range(0..maxValue).
 * For generate primes numbers is used Sieve of Eratosthenes algorithm decide for generate small
 * prime numbers(say up to 10 000 000).
 * <p>
 * Note: Class is create for learning refactoring, one method is deprecated and will be removed
 * in next part development cycle.
 * <p>
 * Refactoring part 1:
 * 1. Hide method generateArray() set permission private.
 * 2. Copy code from generateArray to method generate and remove method generateArray.
 * 3. Rename variable (f --> isPrime)
 * 4. Collapse loops for place result to list
 * 5. Remove dead code, variable count primes not need, because we new use dynamic data structure list
 * 6. Collapse loop again (extend boundary algorithm from "Math.sqrt(s) + 1"  to "s" and join with add item to result
 * 7. Reduce local variable scope
 * 8. Replace temp variable "s" with query
 * 9. Remove code rid know primes for 0 and 1, because algorithm not access to them
 * 10. Simplify init isPrime with use build method Array.fill();
 * 11. Extract algorithm to own method call sieveAlgorithm()
 * <p>
 * Created by cagaj on 13.7.2016.
 */
public class Primes_Refactoring {

    public static List<Integer> generate(int maxValue) {
        if (maxValue < 2) {
            return Collections.emptyList();
        }

        return sieveAlgorithm(maxValue);
    }

    private static List<Integer> sieveAlgorithm(int maxValue) {
        List<Integer> result = new ArrayList();
        boolean[] isPrime = new boolean[maxValue + 1];
        Arrays.fill(isPrime, true);

        for (int i = 2; i < isPrime.length; i++) {
            if (isPrime[i]) {
                result.add(i);
                removeMultiples(i, isPrime);
            }
        }
        return result;
    }

    private static void removeMultiples(int prime, boolean[] isPrime) {
        for (int j = 2 * prime; j < isPrime.length; j += prime) {
            isPrime[j] = false;
        }
    }
}
