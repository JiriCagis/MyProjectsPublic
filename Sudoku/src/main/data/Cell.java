package main.data;

import java.io.Serializable;

public class Cell implements Serializable {

    private int value;
    private CellState state;

    public Cell() {
        value = 0;
        state = CellState.EDITABLE;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public CellState getState() {
        return state;
    }

    public void setState(CellState state) {
        this.state = state;
    }

}
