package model;

public class Board {
    private int row;
    private int col;
    private Cell[][] cells;

    public Board(int row, int col, Cell[][] cells) {
        this.row = row;
        this.col = col;
        this.cells = cells;

    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public Cell[][] getCells() {
        return cells;
    }
}
