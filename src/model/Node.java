package model;

import java.util.ArrayList;

public class Node {
    public Board board;
    public static boolean startSet = false;
    public boolean isStart = false;
    public int sum = 0;
    public Node parent;
    public Cell currentCell; //pointer
    public OPERATION_TYPE previousOperation;
    private Cell[][] cells;

    public Node(Cell currentCell, int currentValue, Board board, Node parent) {
        if (!startSet && currentCell.getOperationType() == OPERATION_TYPE.START) {
            this.isStart = true;
            startSet = true;
        }
        this.currentCell = currentCell;
        this.sum = currentValue;
        this.board = board;
        this.cells = board.getCells();
        this.parent = parent;
    }


    public ArrayList<Node> successor() {
        ArrayList<Node> result = new ArrayList<Node>();
        if (canMoveRight()) {
            Cell rightCell = this.cells[this.currentCell.i][this.currentCell.j + 1];
            if (rightCell != Cell.start) {
                int calculatedValue = rightCell.calculate(this.sum);
                Node rightNode = new Node(rightCell, calculatedValue, board, this);
                result.add(rightNode);
            }
        }
        if (canMoveLeft()) {
            Cell leftCell = this.cells[this.currentCell.i][this.currentCell.j - 1];
            if (leftCell != Cell.start) {
                int calculatedValue = leftCell.calculate(this.sum);
                Node leftNode = new Node(leftCell, calculatedValue, board, this);
                result.add(leftNode);
            }
        }
        if (canMoveDown()) {
            Cell downCell = this.cells[this.currentCell.i + 1][this.currentCell.j];
            if (downCell != Cell.start) {
                int calculatedValue = downCell.calculate(this.sum);
                Node downNode = new Node(downCell, calculatedValue, board, this);
                result.add(downNode);
            }

        }
        if (canMoveUp()) {
            Cell upCell = this.cells[this.currentCell.i - 1][this.currentCell.j];
            if (upCell != Cell.start) {
                int calculatedValue = upCell.calculate(this.sum);
                Node upNode = new Node(upCell, calculatedValue, board, this);
                result.add(upNode);
            }
        }
        return result;
    }

    public boolean canMoveRight() {
        return this.currentCell.j < this.board.getRow() - 1;
    }

    public boolean canMoveLeft() {
        return this.currentCell.j > 0;
    }

    public boolean canMoveUp() {
        return this.currentCell.i > 0;
    }

    public boolean canMoveDown() {
        return this.currentCell.i < this.board.getCol() - 1;
    }

    public boolean isGoal() {
        if (currentCell.getOperationType() == OPERATION_TYPE.GOAL) {
            if (sum >= Cell.goal.getValue()) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public int pathCost() {
        //todo return past cost
        return 1;
    }

    public String hash() {
        StringBuilder hash = new StringBuilder();
        hash.append("i:")
                .append(currentCell.i)
                .append("j:")
                .append(currentCell.j)
                .append("sum:")
                .append(sum)
                .append("op:")
                .append(currentCell.op)
                .append("val:")
                .append(currentCell.getValue());

        return hash.toString();
    }


}
