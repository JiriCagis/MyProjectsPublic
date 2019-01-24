package main.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Algorithm generate Sudoku grid.
 * First initialize sequence numbers for filling fields.
 * After recursive try put one number from sequence numbers and
 * testing if number really belong to this field. Consecutive another 
 * try put numbers. If not be put nothing number from sequence, algorithm
 * return back to previous field and examine other number from sequence.
 * In last step remove number from grid on random positions. Programmer can 
 * set how much field will removed. Set count field to remove, programmer
 * set difficult game.For example if will removed only 10 fields. Sudoku is very 
 * easy for calculate that number assign to blank field.
 */
public class SudokuGenerator {

    private static int[] numberSequence = new int[9];

    private SudokuGenerator() {

    }

    public static int[][] create(int countRemovedField) {
        int[][] sudoku = new int[9][9];
        initNumberSequence();
        generatorSudoku(sudoku, 0, 0);
        randomRemoveFields(sudoku, countRemovedField);
        return sudoku;
    }

    private static void initNumberSequence() {
        List<Integer> list = new ArrayList<Integer>(9);
        for (int number = 1; number <= 9; number++) {
            list.add(number);
        }
        Collections.shuffle(list);
        for (int i = 0; i < 9; i++) {
            numberSequence[i] = list.get(i);
        }
    }

    public static Boolean generatorSudoku(int sudoku[][], int row, int column) {

        if (column == 9) {
            column = 0;
            row++;
        }

        if (row == 9) {
            return true;
        }

        if (sudoku[row][column] != 0) {
            if (generatorSudoku(sudoku, row, column + 1)) {
                return true;
            }
        } else {
            for (int number : numberSequence) {
                if (test(sudoku, number, row, column)) {
                    sudoku[row][column] = number;
                    if (generatorSudoku(sudoku, row, column + 1)) {
                        return true;
                    }
                }
            }
            sudoku[row][column] = 0;
        }
        return false;
    }

    private static boolean test(int sudoku[][], int number, int row, int column) {

        //test column
        for (int k = 0; k < 9; k++) {
            if (k == column) {
                continue;
            }
            if (sudoku[row][k] == number) {
                return false;
            }
        }

        //test row
        for (int k = 0; k < 9; k++) {
            if (k == row) {
                continue;
            }
            if (sudoku[k][column] == number) {
                return false;
            }
        }

        //decide start square coordinate
        int square_row = 0;
        int square_column = 0;
        for (int offset = 0; offset <= 6; offset += 3) {
            if (row >= offset && row <= 2 + offset) {
                square_row = offset;
                break;
            }
        }
        for (int offset = 0; offset <= 6; offset += 3) {
            if (column >= offset && column <= 2 + offset) {
                square_column = offset;
                break;
            }
        }

        //test square
        for (int testRow = square_row; testRow < square_row + 3; testRow++) {
            for (int testColumn = square_column; testColumn < square_column + 3; testColumn++) {
                if (testRow == row && testColumn == column) {
                    continue;
                }
                if (sudoku[testRow][testColumn] == number) {
                    return false;
                }
            }
        }

        return true;
    }

    private static void randomRemoveFields(int[][] sudoku, int countFieldToRemove) {
        while (countFieldToRemove > 0) {
            Random random = new Random();
            int row = random.nextInt(9);
            int column = random.nextInt(9);
            sudoku[row][column] = 0;
            countFieldToRemove--;
        }
    }

}
