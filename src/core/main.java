package core;

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
        printBoard(board,m,n);
    }
}
