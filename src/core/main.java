package core;

import AI.BFS;
import model.Board;
import model.Cell;
import model.Node;

import java.util.Scanner;

public class main {
    private static void printBoard(String[][] board, int m, int n) {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(board[i][j] + " ");

            }
            System.out.println();

        }
    }

    public static void main(String[] args) {
        System.out.println("hi !! pls enter m and n : \n");
        Scanner sc = new Scanner(System.in);
        String mn = sc.nextLine();
        int m = Integer.parseInt(mn.split(" ")[0]);
        int n = Integer.parseInt(mn.split(" ")[1]);
        String[][] board = new String[m][n];
        String[] lines = new String[m];
        for (int i = 0; i < m; i++) {
            lines[i] = sc.nextLine();
            String[] line = lines[i].split(" ");
            for (int j = 0; j < n; j++) {
                board[i][j] = line[j];
            }
        }
        Mapper mapper = new Mapper();
        Cell[][] cells = mapper.createCells(board, m, n);
        Board gameBoard=mapper.createBoard(cells,m,n);
        System.out.println("salam");
        System.out.print(gameBoard.toString());
        Node start =new Node(Cell.start,Cell.start.getValue(),gameBoard);
        BFS bfs=new BFS();
        bfs.search(start);
        System.out.println("done");

    }
}
