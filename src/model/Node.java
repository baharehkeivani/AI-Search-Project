package model;

import java.util.ArrayList;

public class Node {
    public Board board;
    public static boolean startSet = false;
    public boolean isStart = false;
    int value = 0;
    private Node parent;
    private Cell currentCell; //pointer
    public OPERATION_TYPE previousOperation;
    private Cell[][] cells;

    public Node(Cell currentCell, int currentValue, Board board) {
        if (!startSet && currentCell.getOperationType() == OPERATION_TYPE.START) {
            this.isStart = true;
            startSet = true;
        }
        this.currentCell = currentCell;
        this.value = currentValue;
        this.board = board;
        this.cells = board.getCells();
    }


    public ArrayList<Node> successor() {
        ArrayList<Node> result = new ArrayList<Node>();
        if (canMoveRight()) {
            Cell rightCell = this.cells[this.currentCell.i][this.currentCell.j + 1];
            int calculatedValue = rightCell.calculate(this.value);
            Node rightNode = new Node(rightCell, calculatedValue, board);
            result.add(rightNode);
        }
        if (canMoveLeft()) {
            Cell leftCell = this.cells[this.currentCell.i][this.currentCell.j - 1];
            int calculatedValue = leftCell.calculate(this.value);
            Node leftNode = new Node(leftCell, calculatedValue, board);
            result.add(leftNode);
        }
        if (canMoveDown()) {
            Cell downCell = this.cells[this.currentCell.i + 1][this.currentCell.j];
            int calculatedValue = downCell.calculate(this.value);
            Node downNode = new Node(downCell, calculatedValue, board);
            result.add(downNode);

        }
        if (canMoveUp()) {
            Cell upCell = this.cells[this.currentCell.i - 1][this.currentCell.j];
            int calculatedValue = upCell.calculate(this.value);
            Node upNode = new Node(upCell, calculatedValue, board);
            result.add(upNode);
        }
        return result;
    }


    public static void createGraph(Board board) {
        Cell[][] cells = board.getCells();

    }


    public boolean canMoveRight() {
        return this.currentCell.j < this.board.getRow();
    }

    public boolean canMoveLeft() {
        return this.currentCell.j > 0;
    }

    public boolean canMoveUp() {
        return this.currentCell.i > 0;
    }

    public boolean canMoveDown() {
        return this.currentCell.i < this.board.getCol();
    }

    public boolean isGoal() {
        if (currentCell.getOperationType() == OPERATION_TYPE.GOAL) {
            if (value >= Cell.goal.getValue()) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public int pathCost(){
        //todo return past cost
        return  1;
    }

}
