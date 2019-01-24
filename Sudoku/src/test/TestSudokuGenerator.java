package test;

import junit.framework.TestCase;
import main.logic.SudokuGenerator;
import org.junit.Test;

public class TestSudokuGenerator extends TestCase {
    
    @Test
    public void testCreateSudoku() {
        int SUM = 9 + 8 + 7 + 6 + 5 + 4 + 3 + 2 + 1; //all numbers in row, column or grid

        int[][] sudoku = SudokuGenerator.create(0);

        //test rows
        for (int row = 0; row < 9; row++) {
            int test_sum = 0;
            for (int column = 0; column < 9; column++) {
                test_sum += sudoku[row][column];
            }
            assertEquals(SUM, test_sum);
        }

        //test columns
        for (int column = 0; column < 9; column++) {
            int test_sum = 0;
            for (int row = 0; row < 9; row++) {
                test_sum += sudoku[row][column];
            }
            assertEquals(SUM, test_sum);
        }

        //test squares
        for (int rowOffset = 0; rowOffset <= 6; rowOffset += 3) {
            for (int columnOffset = 0; columnOffset <= 6; columnOffset += 3) {
                int test_sum = 0;
                for (int row = rowOffset; row < rowOffset + 3; row++) {
                    for (int column = columnOffset; column < columnOffset + 3; column++) {
                        test_sum += sudoku[row][column];
                    }
                }
                assertEquals(SUM, test_sum);
            }
        }
    }
    
    @Test
    public void testRemoveFields() {
        int[][] sudoku = SudokuGenerator.create(1);
        boolean constainBlankField = false;
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                if (sudoku[row][column] == 0) {
                    constainBlankField = true;
                    break;
                }
            }
        }        
        assertEquals(true, constainBlankField);
    }
    
}
