import java.util.*;

/**
 * Created by admin on 5.11.15.
 */
public class ListGraph implements Graph{

    private Map<Integer,Set<Integer>> neighbors;

    public ListGraph(){
        neighbors=new HashMap<>();
    }

    public void addEdge(int vertex1,int vertex2){
        Set<Integer> set = neighbors.get(vertex1);

        if(set==null){
            set = new HashSet<>();
            set.add(vertex2);
            neighbors.put(vertex1,set);
        } else{
            set.add(vertex2);
        }
    }

    @Override
    public void addEdge(int vertex1, int vertex2, int evaluate) {
        //not supported now
    }

    public int calculateMinDegreeVertex() {
        int min = Integer.MAX_VALUE;
        for(Integer key:neighbors.keySet()){
            Set<Integer> set = neighbors.get(key);
            if(set!=null && set.size()<min){
                min = set.size();
            }
        }
        return min;
    }

    public int calculateMaxDegreeVertex() {
        int max = Integer.MIN_VALUE;
        for(Integer key:neighbors.keySet()){
            Set<Integer> set = neighbors.get(key);
            if(set!=null && set.size()>max){
                max = set.size();
            }
        }
        return max;
    }

    public float calculateAverageDegreeVertex() {
        int totalSum = 0;
        for(Integer key:neighbors.keySet()) {
            Set<Integer> set = neighbors.get(key);
            if(set!=null){
                totalSum+=set.size();
            }
        }
        return totalSum/(float) neighbors.size();
    }

    @Override
    public void showFrequencyDegree() {
        int minDegree = calculateMinDegreeVertex();
        int maxDegree = calculateMaxDegreeVertex();

        for(int degree = minDegree;degree<=maxDegree;degree++){
            int countForDegree = 0;

            for(Integer key:neighbors.keySet()){
                Set<Integer> set = neighbors.get(key);
                if(set!=null && set.size()==degree){
                    countForDegree++;
                }
            }

            System.out.println(degree + ":" + countForDegree);
        }
    }

    @Override
    public void calculateFloydAlgorithm() {

    }

    @Override
    public double[] calculateMeanDistance() {
        return new double[0];
    }

    @Override
    public double[] closenessCentrality() {
        return new double[0];
    }


}


