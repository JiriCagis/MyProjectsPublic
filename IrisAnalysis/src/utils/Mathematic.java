package utils;

/**
 * Created by admin on 8.10.15.
 */
public class Mathematic {

    /**
     * Prevent create static class.
     */
    private Mathematic() {
    }

    public static double calculateAverage(double[] array) {
        double sum = 0;
        for (int i = 0; i < array.length; i++) {
            sum += array[i];
        }
        return sum / array.length;
    }

    public static double calculateVariance(double[] array) {
        double avg = calculateAverage(array);
        double sum = 0;
        for (int i = 0; i < array.length; i++) {
            double value = array[i] - avg;
            value = value * value;
            sum += value;
        }
        return sum / array.length;
    }

    public static double propability(double[] array, double value) {
        int countValue = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                countValue++;
            }
        }
        return countValue / array.length;
    }

    public static double binomialDistribution(double propabilityValue, int countSubSet, int countValue) {
        double part1 = factorial(countValue) / (double) (factorial(countSubSet) * factorial(countSubSet - countValue));
        double part2 = Math.pow(propabilityValue, countValue) * Math.pow(1 - propabilityValue, countValue - countSubSet);
        return part1 * part2;
    }

    public static int factorial(int number) {
        int fact = 1;
        for (int i = 1; i < number; i++) {
            fact = number * i;
        }
        return fact;
    }

    public static double scalarProduct(double[] vector1, double[] vector2){
        double scalarProduct = 0;
        for(int i=0;i<vector1.length;i++){
            scalarProduct+=vector1[i]*vector2[i];
        }
        return scalarProduct;
    }

    public static double vectorSize(double[] vector){
        double sum = 0;
        for(int i=0;i<vector.length;i++){
            sum+=Math.pow(vector[i],2);
        }
        return Math.sqrt(sum);
    }

    public double calculateDistanceBetween(double[] vector1, double[] vector2){
        double[] vector3 = new double[vector1.length];
        for(int i=0;i<vector1.length;i++){
            vector3[i]=vector1[i]-vector2[i];
        }
        return vectorSize(vector3);
    }

    private static double calculateCosineSimilarity(double[] vector1,double[] vector2) {
        return scalarProduct(vector1,vector2) / (vectorSize(vector1)*vectorSize(vector2));
    }


}
