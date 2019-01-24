package refactoring;

import java.util.ArrayList;
import java.util.List;

/**
 * Mathematical library class for get all primes numbers in given range(0..maxValue).
 * For generate primes numbers is used Sieve of Eratosthenes algorithm decide for generate small
 * prime numbers(say up to 10 000 000).
 *
 * Note: Class is create for learning refactoring, one method is deprecated and will be removed
 *       in next part development cycle.
 *
 * Created by cagaj on 13.7.2016.
 */
public class Primes {

    public static List<Integer> generate(int maxValue) {
        List<Integer> result = new ArrayList<Integer>();

        int[] primes = generateArray(maxValue);
        for (int prime : primes) {
            result.add(prime);
        }

        return result;
    }

    @Deprecated
    public static int[] generateArray(int maxValue) {
        if (maxValue >= 2) {
            //declarations
            int s = maxValue + 1;
            boolean[] f = new boolean[s];
            int i;

            //initialize the array to true
            for (i = 0; i < s; i++) {
                f[i] = true;
            }

            //get rid of know non-primes
            f[0] = f[1] = false;

            //Sieve algorithm
            int j;
            for (i = 2; i < Math.sqrt(s) + 1; i++) {
                for (j = 2 * i; j < s; j += i) {
                    f[j] = false;
                }
            }

            //How many primes there
            int count = 0;
            for (i = 0; i < s; i++) {
                if (f[i] == true) { //if prime
                    count++; //bump count
                }
            }
            int primes[] = new int[count];

            //move the primes into the result
            for(i=0,j=0;i<s;i++){
                if(f[i]){ //if prime
                    primes[j++]=i;
                }
            }
            return primes;
        } else { //max value < 2
            return new int[0]; //return null array
        }
    }
}
