package net.homecredit.jobsdev;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by osboxes on 28/05/17.
 */
public class MyMazeSolverRecursion implements MazeSolver {

    private class Point{
        public int row;
        public int column;

        public Point(){}

        public Point(int row, int column){
            this.row = row;
            this.column = column;
        }
    }

    private boolean[][] mazeMatrix;
    private boolean[][] mazeMatrixVisited;

    @Override
    public boolean existsPath(boolean[][] mazeMatrix) {
        switch (mazeMatrix.length){
            case 0:
                return false;
            case 1:
                return mazeMatrix[0][0];
            default:
                for(Point startPoint : findStartPoints(mazeMatrix)){
                    this.mazeMatrix = mazeMatrix;
                    this.mazeMatrixVisited = new boolean[mazeMatrix.length][mazeMatrix.length];
                    boolean existPath = existsPathRecursion(startPoint);
                    if(existPath){
                        return true;
                    }
                }
                return false;
        }
    }

    private List<Point> findStartPoints(boolean[][] mazeMatrix){
        List<Point> positions = new ArrayList<>();
        for(int i=0;i<mazeMatrix.length;i++){
            if(mazeMatrix[0][i] == true){
                positions.add(new Point(0,i));
            }
        }
        return positions;
    }

    private boolean existsPathRecursion(Point point){
        final int N = mazeMatrix.length;
        if(point.row >= N || point.row < 0 || point.column >= N || point.column <0){
            return false;
        }
        if(mazeMatrixVisited[point.row][point.column] == true){
            return false;
        }
        if(point.row == N-1){
            return mazeMatrix[point.row][point.column];
        }

        if(mazeMatrix[point.row][point.column]==true){
            mazeMatrixVisited[point.row][point.column] = true;

            boolean existPathOnLeft = existsPathRecursion(new Point(point.row,point.column-1));
            boolean existPathOnRight =  existsPathRecursion(new Point(point.row,point.column+1));
            boolean existPathOnBottom = existsPathRecursion(new Point(point.row-1,point.column));
            boolean existPathOnTop =   existsPathRecursion(new Point(point.row+1,point.column));

            return existPathOnLeft || existPathOnBottom || existPathOnRight || existPathOnTop;
        } else {
            return false;
        }
    }
}
