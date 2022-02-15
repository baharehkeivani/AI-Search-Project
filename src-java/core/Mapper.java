package core;

import model.Board;
import model.Cell;

public class Mapper {
    public Cell[][] createCells(String[][] board, int m, int n) {
        Cell[][] cells = new Cell[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                String op = (board[i][j].split("")[0]);
                int value = Integer.parseInt(board[i][j].substring(1));
                cells[i][j] = new Cell(i, j, value, op);
            }
        }
        return cells;
    }

    public Board createBoard(Cell[][] cells, int row, int col) {
        return new Board(row, col, cells);
    }

}
