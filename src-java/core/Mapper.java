package core;

import model.Board;
import model.Cell;
import model.OPERATION_TYPE;

public class Mapper {
    private int g_i , g_j , s_i , s_j;

    public Cell[][] createCells(String[][] board, int m, int n) {
        Cell[][] cells = new Cell[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                String op = (board[i][j].split("")[0]);
                int value = Integer.parseInt(board[i][j].substring(1));
                cells[i][j] = new Cell(i, j, value, op);
                if (cells[i][j].getOperationType() == OPERATION_TYPE.GOAL) {
                    g_i=i;
                    g_j=j;
                }
                if (cells[i][j].getOperationType() == OPERATION_TYPE.START) {
                    s_i=i;
                    s_j=j;
                }
            }
        }
        return cells;
    }

    public Board createBoard(Cell[][] cells, int row, int col, Boolean reverse) {
        return !reverse
                ? new Board(row, col, cells, cells[s_i][s_j], cells[g_i][g_j])
                : new Board(row, col, cells, cells[g_i][g_j] ,cells[s_i][s_j]);
    }

}
