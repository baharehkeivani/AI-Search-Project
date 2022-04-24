package core;

import AI.*;
import model.Board;
import model.Cell;
import model.Node;

import java.util.Hashtable;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        System.out.println(" pls enter rows and columns of your board : \n");
        Scanner sc = new Scanner(System.in);
        String mn = sc.nextLine();
        int rows = Integer.parseInt(mn.split(" ")[0]);
        int columns = Integer.parseInt(mn.split(" ")[1]);
        String[][] board = new String[rows][columns];
        String[] lines = new String[rows];
        for (int i = 0; i < rows; i++) {
            lines[i] = sc.nextLine();
            String[] line = lines[i].split(" ");
            if (columns >= 0) System.arraycopy(line, 0, board[i], 0, columns);
        }
        Mapper mapper = new Mapper();
        Cell[][] cells = mapper.createCells(board, rows, columns);
        //start node
        Board gameBoard = mapper.createBoard(cells, rows, columns, false);
        Hashtable<String, Boolean> initHash = new Hashtable<>();
        initHash.put(gameBoard.getStart().toString(), true);
        Node start = new Node(
                gameBoard.getStart(),
                gameBoard.getStart().getValue(),
                gameBoard.getGoal().getValue(),
                gameBoard, null, initHash);
        //goal node
        Board reverse_gameBoard = mapper.createBoard(cells, rows, columns, true);
        Hashtable<String, Boolean> r_initHash = new Hashtable<>();
        r_initHash.put(gameBoard.getStart().toString(), true);
        Node goal = new Node(
                reverse_gameBoard.getStart(),
                reverse_gameBoard.getStart().getValue(),
                0,
                reverse_gameBoard, null, r_initHash);

//        BFS bfs = new BFS(start);
//        bfs.search();

//        BDS bds = new BDS(start, goal);
//        bds.search();

//        IDS ids = new IDS();
//        ids.search(start);

        AS as = new AS();
        as.search(start);

//        IDAS idas = new IDAS();
//        idas.search(start);
    }

    public static void printResult(Node node, int depthCounter) {
        if (node.parent == null) {
            System.out.println("problem solved at a depth of  : " + depthCounter);
            return;
        }
        System.out.println(node.toString());
        printResult(node.parent, depthCounter + 1);
    }
}
