package main.logic;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import main.data.CellState;
import main.data.Level;
import main.data.Sudoku;
import utils.xmlParser.XmlParser;
import utils.xmlParser.XmlParserImpl;

public class SudokuManagerImpl implements SudokuManager {

    private Level level;
    private final File xmlFile = new File("savedGame.xml");
    private final XmlParser<Sudoku> xmlParser;

    public SudokuManagerImpl(Level level) {
        this.level = level;
        xmlParser = new XmlParserImpl<Sudoku>();
    }

    @Override
    public Sudoku next() {
        int countFieldToRemove = 0;
        switch(level){
            case EASY: countFieldToRemove = 20; break;
            case MEDIUM: countFieldToRemove= 40; break;
            case HARD: countFieldToRemove = 60; break;
        }
        int[][] array = SudokuGenerator.create(countFieldToRemove);
        return new Sudoku(array,level);
    }

    @Override
    public void verify(Sudoku sudoku) {
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                if (sudoku.getState(row, column) == CellState.EDITABLE) {
                    int value = sudoku.getValue(row, column);
                    if (testField(sudoku.getCellValues(), value, row, column)) {
                        sudoku.setCell(row, column, CellState.SUCCESSFUL);
                    } else {
                        sudoku.setCell(row, column, CellState.BAD);
                    }
                }
            }
        }
    }

    @Override
    public void restart(Sudoku sudoku
    ) {
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                CellState state = sudoku.getState(row, column);
                if (state == CellState.EDITABLE
                        || state == CellState.SUCCESSFUL
                        || state == CellState.BAD) {
                    sudoku.setCell(row, column, 0, CellState.EDITABLE);
                }
            }
        }
    }

    @Override
    public void setSudokuLevel(Level l) {
        this.level = l;
    }

    private boolean testField(int sudoku[][], int number, int row, int column) {

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

    @Override
    public Sudoku loadSaved() {
        Sudoku sudoku = null;
        List<Sudoku> list = xmlParser.parse(xmlFile);
        if(!list.isEmpty()){
           sudoku = list.get(0);
           level  = sudoku.getLevel();
        }
        return sudoku;
    }

    @Override
    public void save(Sudoku sudoku) {
        List<Sudoku> list = new ArrayList<Sudoku>();
        list.add(sudoku);
        xmlParser.save(list, xmlFile);
    }
}
