package main.data;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

public class Sudoku implements Serializable {

    private static final int N = 9;
    private Level level;
    private Cell[][] cells;

    public Sudoku() {
        level = Level.EASY;
        cells = new Cell[N][N];
        for (int row = 0; row < N; row++) {
            for (int column = 0; column < N; column++) {
                cells[row][column] = new Cell();
            }
        }
    }

    public Sudoku(int[][] array, Level level) {
        this.level = level;
        cells = new Cell[N][N];
        for (int row = 0; row < N; row++) {
            for (int column = 0; column < N; column++) {
                Cell cell = new Cell();
                if (array[row][column] != 0) {
                    cell.setState(CellState.NOT_EDITABLE);
                    cell.setValue(array[row][column]);
                }
                cells[row][column] = cell;
            }
        }
    }

    public void setCell(int row, int column, int value) {
        cells[row][column].setValue(value);
    }

    public void setCell(int row, int column, CellState state) {
        cells[row][column].setState(state);
    }

    public void setCell(int row, int column, int value, CellState state) {
        cells[row][column].setValue(value);
        cells[row][column].setState(state);
    }

    public int getValue(int row, int column) {
        return cells[row][column].getValue();
    }

    public CellState getState(int row, int column) {
        return cells[row][column].getState();
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public int[][] getCellValues() {
        int[][] array = new int[N][N];
        for (int row = 0; row < N; row++) {
            for (int column = 0; column < N; column++) {
                array[row][column] = cells[row][column].getValue();
            }
        }
        return array;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Sudoku other = (Sudoku) obj;
        if (this.level != other.level) {
            return false;
        }
        if (!Arrays.deepEquals(this.cells, other.cells)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.level);
        hash = 97 * hash + Arrays.deepHashCode(this.cells);
        return hash;
    }

    public Cell[][] getCells() {
        return cells;
    }

    public void setCells(Cell[][] cells) {
        this.cells = cells;
    }

}
