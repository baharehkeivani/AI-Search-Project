package model;

import core.Constants;

import java.util.ArrayList;

public class Node {
    public Board board;
    public static boolean startSet = false;
    public boolean isStart = false;
    public int sum = 0;
    public Node parent;
    public Cell currentCell;
    public ACTION_TYPE previousAction;
    private Cell[][] cells;

    public Node(Cell currentCell, int currentValue, Board board, Node parent, ACTION_TYPE previousAction) {
        if (!startSet && currentCell.getOperationType() == OPERATION_TYPE.START) {
            this.isStart = true;
            startSet = true;
        }
        this.currentCell = currentCell;
        this.sum = currentValue;
        this.board = board;
        this.cells = board.getCells();
        this.parent = parent;
        this.previousAction = previousAction;
        setGoalValue();
    }


    public ArrayList<Node> successor() {
        ArrayList<Node> result = new ArrayList<Node>();
        if (canMoveRight()) {
            Cell rightCell = this.cells[this.currentCell.i][this.currentCell.j + 1];
            if (rightCell != Cell.getStart() && !isWall(rightCell) && previousAction != ACTION_TYPE.RIGHT) {
                int calculatedValue = calculate(rightCell);
                Node rightNode = new Node(rightCell, calculatedValue, board, this, ACTION_TYPE.LEFT);
                result.add(rightNode);
            }
        }
        if (canMoveLeft()) {
            Cell leftCell = this.cells[this.currentCell.i][this.currentCell.j - 1];
            if (leftCell != Cell.getStart() && !isWall(leftCell) && previousAction != ACTION_TYPE.LEFT) {
                int calculatedValue = calculate(leftCell);
                Node leftNode = new Node(leftCell, calculatedValue, board, this, ACTION_TYPE.RIGHT);
                result.add(leftNode);
            }
        }
        if (canMoveDown()) {
            Cell downCell = this.cells[this.currentCell.i + 1][this.currentCell.j];
            if (downCell != Cell.getStart() && !isWall(downCell) && previousAction != ACTION_TYPE.DOWN) {
                int calculatedValue = calculate(downCell);
                Node downNode = new Node(downCell, calculatedValue, board, this, ACTION_TYPE.UP);
                result.add(downNode);
            }

        }
        if (canMoveUp()) {
            Cell upCell = this.cells[this.currentCell.i - 1][this.currentCell.j];
            if (upCell != Cell.getStart() && !isWall(upCell) && previousAction != ACTION_TYPE.UP) {
                int calculatedValue = calculate(upCell);
                Node upNode = new Node(upCell, calculatedValue, board, this, ACTION_TYPE.DOWN);
                result.add(upNode);
            }
        }
        return result;
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

    public boolean isGoal() {
        if (currentCell.getOperationType() == OPERATION_TYPE.GOAL) {
            return sum >= Cell.getGoal().getValue();
        }
        return false;
    }

    private int pathCost() {
        //todo return path cost
        return 1;
    }

    private int heuristic() {
        // TODO: 2/16/2022 implement heuristic function
        return 0;
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
            cells[currentCell.i][currentCell.j].setValue(-1 * currentCell.getValue());
        if (currentCell.getOperationType() == OPERATION_TYPE.INCREASE_GOAL)
            cells[currentCell.i][currentCell.j].setValue(currentCell.getValue());
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
