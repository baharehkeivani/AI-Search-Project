package model;

import core.Constants;

import java.util.ArrayList;
import java.util.Hashtable;

import static java.lang.Math.abs;
import static java.lang.Math.min;

public class Node {
    public Board board;
    public int sum = 0;
    public int h = 0;
    public int g = 0;
    public int g_idsa = 0;
    public Node parent;
    public Cell currentCell;
    private Cell[][] cells;
    private int goalValue;
    private Hashtable<String, Boolean> repeatedStates;

    public Node(Cell currentCell, int currentValue, int goalValue, Board board, Node parent, Hashtable<String, Boolean> repeated) {
        this.currentCell = currentCell;
        this.sum = currentValue;
        this.board = board;
        this.cells = board.getCells();
        this.parent = parent;
        this.goalValue = goalValue;
        Hashtable<String, Boolean> hashtableTemp = new Hashtable<String, Boolean>(repeated);
        hashtableTemp.put(this.toString(), true);
        this.repeatedStates = hashtableTemp;
        setGoalValue();
        this.h = heuristic();
    }

    public ArrayList<Node> successor() {
        ArrayList<Node> result = new ArrayList<Node>();
        if (canMoveRight()) {
            Cell rightCell = this.cells[this.currentCell.i][this.currentCell.j + 1];
            if (isValidMove(rightCell)) {
                int calculatedValue = calculate(rightCell);
                Node rightNode = new Node(rightCell, calculatedValue, goalValue, board, this, repeatedStates);
                rightNode.g = pathCost(rightCell);
                rightNode.g_idsa = g_idsa + 1;
                result.add(rightNode);
            }
        }
        if (canMoveLeft()) {
            Cell leftCell = this.cells[this.currentCell.i][this.currentCell.j - 1];
            if (isValidMove(leftCell)) {
                int calculatedValue = calculate(leftCell);
                Node leftNode = new Node(leftCell, calculatedValue, goalValue, board, this, repeatedStates);
                leftNode.g = pathCost(leftCell);
                leftNode.g_idsa = g_idsa + 1;
                result.add(leftNode);
            }
        }
        if (canMoveDown()) {
            Cell downCell = this.cells[this.currentCell.i + 1][this.currentCell.j];
            if (isValidMove(downCell)) {
                int calculatedValue = calculate(downCell);
                Node downNode = new Node(downCell, calculatedValue, goalValue, board, this, repeatedStates);
                downNode.g = pathCost(downCell);
                downNode.g_idsa = g_idsa + 1;
                result.add(downNode);
            }

        }
        if (canMoveUp()) {
            Cell upCell = this.cells[this.currentCell.i - 1][this.currentCell.j];
            if (isValidMove(upCell)) {
                int calculatedValue = calculate(upCell);
                Node upNode = new Node(upCell, calculatedValue, goalValue, board, this, repeatedStates);
                upNode.g = pathCost(upCell);
                upNode.g_idsa = g_idsa + 1;
                result.add(upNode);
            }
        }
        return result;
    }

    private boolean canEnterGoal(Cell downCell) {
        if (downCell != board.getGoal()) return true;
        else {
            return sum >= goalValue;
        }
    }

    private int calculate(Cell cell) {
        return switch (cell.getOperationType()) {
            case MINUS -> sum - cell.getValue();
            case ADD -> sum + cell.getValue();
            case POW -> (int) Math.pow(sum, cell.getValue());
            case MULT -> sum * cell.getValue();
            default -> sum;
        };
    }

    private boolean isWall(Cell cell) {
        return cell.getOperationType() == OPERATION_TYPE.WALL;
    }

    private boolean canMoveRight() {
        return this.currentCell.j < this.board.getRow() - 1;
    }

    private boolean canMoveLeft() {
        return this.currentCell.j > 0;
    }

    private boolean canMoveUp() {
        return this.currentCell.i > 0;
    }

    private boolean canMoveDown() {
        return this.currentCell.i < this.board.getCol() - 1;
    }

    private Boolean isValidMove(Cell destCell) {
        return destCell != board.getStart() && canEnterGoal(destCell) && !isWall(destCell) && !repeatedStates.containsKey(destCell.toString());
    }

    public boolean isGoal() {
        if (currentCell.getOperationType() == OPERATION_TYPE.GOAL) {
            return sum > goalValue;
        }
        return false;
    }

    public int pathCost(Cell cell) {
        return switch (cell.getOperationType()) {
            case MINUS, DECREASE_GOAL, GOAL -> g + 1;
            case ADD, INCREASE_GOAL -> g + 2;
            case MULT -> g + 5;
            case POW -> g + 11;
            default -> 0;
        };
    }

    // Manhattan distance --> sum of the distance between rows and the distance between columns.
    // x(i,j) and goal(r,c)
    // x == currentNode
    // h(x) = abs(r-i)  +  abs(c-j)
    // we can only move up , down , right , left
    // each cell's value and op = *1
    private int heuristic() {
        return abs(board.getGoal().i - currentCell.i) + abs(board.getGoal().j - currentCell.j);
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

    public void drawState() {

        for (int i = 0; i < board.getRow(); i++) {
            for (int j = 0; j < board.getCol(); j++) {
                if (cells[i][j].getOperationType() == OPERATION_TYPE.GOAL) {
                    System.out.print(Constants.ANSI_BRIGHT_GREEN +
                            OPERATION_TYPE.getOperationTag(cells[i][j].getOperationType())
                            + goalValue + spaceRequired(cells[i][j])
                    );
                    continue;
                }
                if (currentCell.j == j && currentCell.i == i) {
                    System.out.print(Constants.ANSI_BRIGHT_GREEN + Constants.PLAYER + sum + spaceRequired(cells[i][j]));
                } else {
                    System.out.print(Constants.ANSI_BRIGHT_GREEN +
                            OPERATION_TYPE.getOperationTag(cells[i][j].getOperationType())
                            + cells[i][j].getValue() + spaceRequired(cells[i][j])
                    );
                }
            }
            System.out.println();

        }
        System.out.println("-----------------------------------------");

    }

    private void setGoalValue() {
        if (currentCell.getOperationType() == OPERATION_TYPE.DECREASE_GOAL)
            goalValue -= currentCell.getValue();
        if (currentCell.getOperationType() == OPERATION_TYPE.INCREASE_GOAL)
            goalValue += currentCell.getValue();
    }

    private String spaceRequired(Cell cell) {
        int length = String.valueOf(cell.getValue()).length();
        String result = " ".repeat(5 - length);
        if (cell.op.equals("+") || cell.op.equals("-") || cell.op.equals("*") || cell.op.equals("^")) {
            result += " ";
        }
        return result;
    }

    @Override
    public String toString() {
        return "(" + this.currentCell.i + "," + this.currentCell.j + ")";
    }
}
