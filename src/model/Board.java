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
}
