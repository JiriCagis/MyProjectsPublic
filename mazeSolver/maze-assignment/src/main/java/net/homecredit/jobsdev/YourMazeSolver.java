package net.homecredit.jobsdev;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Your implementation goes here.
 *
 * !! PLACE YOUR E-MAIL SOMEWHERE IN THE COMMENTS !!
 *
 * @author you@earth
 */
public class YourMazeSolver implements MazeSolver {

    private class Point{
        public int row;
        public int column;

        public Point(){}

        public Point(int row, int column){
            this.row = row;
            this.column = column;
        }
    }

    @Override
    public boolean existsPath(boolean[][] mazeMatrix) {
        switch (mazeMatrix.length){
            case 0:
                return false;
            case 1:
                return mazeMatrix[0][0];
            default:
                return existsPathWithoutRecursion(mazeMatrix);
        }
    }

    private boolean existsPathWithoutRecursion(boolean[][] mazeMatrix){
        int N = mazeMatrix.length;
        boolean[][] mazeMatrixVisited = new boolean[N][N];

        Stack<Point> possibleMoves = findStartPoints(mazeMatrix);
        while(!possibleMoves.empty()){
            Point actualPoint = possibleMoves.pop();
            mazeMatrixVisited[actualPoint.row][actualPoint.column] = true;

            //found end of maze
            if(actualPoint.row == N-1 && mazeMatrix[actualPoint.row][actualPoint.column] == true){
                return true;
            }

            Point leftPoint = new Point(actualPoint.row,actualPoint.column-1);
            Point rightPoint = new Point(actualPoint.row,actualPoint.column+1);
            Point topPoint = new Point(actualPoint.row-1,actualPoint.column);
            Point bottomPoint = new Point(actualPoint.row+1,actualPoint.column);

            Point[] points = {leftPoint,rightPoint,topPoint,bottomPoint};
            for(Point point:points){
                if(isPossibleMove(point,mazeMatrix,mazeMatrixVisited)){
                    possibleMoves.add(point);
                }
            }
        }
        return false;
    }

    private boolean isPossibleMove(Point point,boolean[][] mazeMatrix, boolean[][] mazeMatrixVisited){
        final int N = mazeMatrix.length;
        int row = point.row;
        int column = point.column;
        if(row >= N || row < 0 || column >= N || column <0){
            return false;
        }
        if(mazeMatrixVisited[row][column] == true){
            return false;
        }
        if(mazeMatrix[row][column]==false) {
            return false;
        }
        return true;
    }

    private Stack<Point> findStartPoints(boolean[][] mazeMatrix){
        Stack<Point> positions = new Stack<>();
        for(int i=0;i<mazeMatrix.length;i++){
            if(mazeMatrix[0][i] == true){
                positions.add(new Point(0,i));
            }
        }
        return positions;
    }
}
