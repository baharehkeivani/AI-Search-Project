package model;

public class Cell {
    public int i;
    public int j;
    private int value;
    public String op;

    public Cell(int i, int j, int value, String op) {
        this.i = i;
        this.j = j;
        this.op = op;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public OPERATION_TYPE getOperationType() {
        return OPERATION_TYPE.getOperation(op);
    }

    public void setOperationType(String op) {
        this.op = op;
    }

    @Override
    public String toString() {
        return "(" + this.i + "," + this.j + ")";
    }
}
