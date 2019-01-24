/**
 * Created by admin on 5.11.15.
 */
public class MatrixGraph implements Graph {

    private int[][] matrix;
    private final int COUNT_VERTEX;

    public MatrixGraph(int countVertex) {
        this.COUNT_VERTEX = countVertex;
        matrix = new int[countVertex][countVertex];
        clearMatrix();
    }

    private void clearMatrix() {
        for (int x = 0; x < COUNT_VERTEX; x++) {
            for (int y = 0; y < COUNT_VERTEX; y++) {
                matrix[x][y] = 0;
            }
        }
    }

    public void addEdge(int vertex1, int vertex2) {
        addEdge(vertex1, vertex2, 1);
    }

    public void addEdge(int vertex1, int vertex2, int evaluate) {
        matrix[vertex1][vertex2] = evaluate;
    }

    public int calculateMinDegreeVertex() {
        int min = Integer.MAX_VALUE;
        for (int indexVertex = 0; indexVertex < COUNT_VERTEX; indexVertex++) {
            int count = 0;
            for (int k = 0; k < COUNT_VERTEX; k++) {
                count += matrix[indexVertex][k];
            }
            if (count < min) {
                min = count;
            }

        }
        return min;
    }

    public int calculateMaxDegreeVertex() {
        int max = Integer.MIN_VALUE;
        for (int indexVertex = 0; indexVertex < COUNT_VERTEX; indexVertex++) {
            int count = 0;
            for (int k = 0; k < COUNT_VERTEX; k++) {
                count += matrix[indexVertex][k];
            }
            if (count > max) {
                max = count;
            }

        }
        return max;
    }

    public float calculateAverageDegreeVertex() {
        int totalSum = 0;
        for (int indexVertex = 0; indexVertex < COUNT_VERTEX; indexVertex++) {
            int count = 0;
            for (int k = 0; k < COUNT_VERTEX; k++) {
                totalSum += matrix[indexVertex][k];
            }
        }
        return totalSum / (float) COUNT_VERTEX;
    }

    public void showFrequencyDegree() {
        int minDegree = calculateMinDegreeVertex();
        int maxDegree = calculateMaxDegreeVertex();

        for (int degree = minDegree; degree <= maxDegree; degree++) {
            int countForDegree = 0;

            for (int indexVertex = 0; indexVertex < COUNT_VERTEX; indexVertex++) {
                int count = 0;
                for (int k = 0; k < COUNT_VERTEX; k++) {
                    count += matrix[indexVertex][k];
                }
                if (count == degree) {
                    countForDegree++;
                }
            }

            System.out.println(degree + ":" + countForDegree);
        }
    }

    public void calculateFloydAlgorithm() {
        prepareMatrix();
        for (int k = 0; k < COUNT_VERTEX; k++) {
            for (int row = 0; row < COUNT_VERTEX; row++) {
                for (int column = 0; column < COUNT_VERTEX; column++) {
                    if (matrix[row][k] == Integer.MAX_VALUE || matrix[k][column] == Integer.MAX_VALUE)
                        continue;

                    if (matrix[row][column] > matrix[row][k] + matrix[k][column]) {
                        matrix[row][column] = matrix[row][k] + matrix[k][column];
                    }

                }
            }
        }
    }

    private void prepareMatrix() {
        for (int row = 0; row < COUNT_VERTEX; row++) {
            for (int column = 0; column < COUNT_VERTEX; column++) {
                if (matrix[row][column] == 0 && row != column) {
                    matrix[row][column] = Integer.MAX_VALUE;
                }
            }
        }
    }

    private void showMatrix(int[][] matrix, int length) {
        for (int row = 0; row < length; row++) {
            for (int column = 0; column < length; column++) {
                if (matrix[row][column] == 10000) {
                    System.out.print("N ");
                } else
                    System.out.print(matrix[row][column] + " ");

            }
            System.out.println("\n");
        }
    }

    public double[] calculateMeanDistance() {
        double meanDistanceArray[] = new double[COUNT_VERTEX];
        for (int i = 0; i < COUNT_VERTEX; i++) {
            int sum = 0;
            for (int j = 0; j < COUNT_VERTEX; j++) {
                sum += matrix[i][j];
            }

            double meanDistance = sum / (double) 34;
            meanDistanceArray[i] = meanDistance;
        }
        return meanDistanceArray;
    }

    @Override
    public double[] closenessCentrality() {
        double array[] = calculateMeanDistance();
        for (int i = 0; i < array.length; i++) {
            double cetrality = 1 / array[i];
            array[i]=cetrality;
        }
        return array;
    }

    @Override
    public String toString() {
        String result = "";
        for (int row = 0; row < COUNT_VERTEX; row++) {
            for (int column = 0; column < COUNT_VERTEX; column++) {
                if (matrix[row][column] == Integer.MAX_VALUE) {
                    result+=("N ");
                } else
                    result+=(matrix[row][column] + " ");

            }
            result+="\n";
        }
        return result;
    }
}
