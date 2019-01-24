package test;

import junit.framework.TestCase;
import main.data.CellState;
import main.data.Level;
import main.data.Sudoku;
import main.logic.SudokuManager;
import main.logic.SudokuManagerImpl;
import org.junit.Test;

public class TestSudokuManager extends TestCase {

    @Test
    public void testNextSudoku() {
        SudokuManager sudokuManager = new SudokuManagerImpl(Level.EASY);
        Sudoku sudoku1 = sudokuManager.next();
        Sudoku sudoku2 = sudokuManager.next();  
        assertNotSame(sudoku1, sudoku2);
    }
    
    @Test
    public void testRestartSudoku(){
        SudokuManager sudokuManager = new SudokuManagerImpl(Level.EASY);
        Sudoku sudoku = sudokuManager.next();
        
        //find editable field
        int test_row =0;
        int test_column =0;
        for(int row=0;row<9;row++)
            for(int column=0;column<9;column++)
            {
                if (sudoku.getState(row, column) == CellState.EDITABLE)
                {
                    test_row=row;
                    test_column=column;
                    break;
                }
            }
        
        //write to editable field
        sudoku.setCell(test_row, test_column, 4);
        
        //check if was removed number from field
        sudokuManager.restart(sudoku);
        assertEquals(0,sudoku.getValue(test_row, test_column));
    }
}
