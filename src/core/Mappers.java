package core;

import model.Board;
import model.Cell;
import model.OPERATION_TYPE;

public class Mappers {
    public Cell[][] createCells(String[][] board, int m, int n) {
        Cell[][] cells = new Cell[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int value = Integer.parseInt(board[i][j].split("")[1]);
                String op = (board[i][j].split("")[0]);
                cells[i][j] = new Cell(value, OPERATION_TYPE.getOperation(op));
            }
        }
        return cells;
    }

    public Board createBoard(Cell[][] cells,int row ,int col ){
        return new Board(row,col,cells);
    }

}
