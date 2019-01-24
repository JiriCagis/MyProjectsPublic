package main.logic;

import main.data.Level;
import main.data.Sudoku;

/**
 * Manager for control game Sudoku.Sudoku is logical number game 
 * for one player. 
 * 
 * Rules:
 * The classic Sudoku game involves a grid of 81 squares. The grid is divided into nine blocks, 
 * each containing nine squares. The rules of the game are simple: each of the nine blocks has to
 * contain all the numbers 1-9 within its squares. Each number can only appear once in a row, column
 * or box. The difficulty lies in that each vertical nine-square column, or horizontal nine-square 
 * line across, within the larger square, must also contain the numbers 1-9, without repetition or omission.
 */
public interface SudokuManager {  
   
    /**
     * Get next sudoku from generator.
     * @return 
     */
   Sudoku next();
   
   /**
    * Verify Sudoku if is fill successful.
    * Mark edited field green successful field and
    * red failure field.
    * @param sudoku 
    */
   void verify(Sudoku sudoku);
   
   /**
    * Clean field assign as edited.
    * @param sudoku 
    */
   void restart(Sudoku sudoku);
   
   void setSudokuLevel(Level l);
   /**
    * Load from xml file on disk.
    * @return 
    */
   Sudoku loadSaved();
   
   /**
    * Save to xml file to disk.
    * @param sudoku 
    */
   void save(Sudoku sudoku);
}
