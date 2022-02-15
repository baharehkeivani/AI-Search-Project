package model;

public class Cell {
    int i;
    int j;
    private int value;
    private OPERATION_TYPE operationType;
    public static Cell start;
    public static Cell goal;
    public String op;

    public Cell(int i, int j, int value, String op) {
        this.i = i;
        this.j = j;
        this.op=op;
        this.value = value;
        this.operationType=OPERATION_TYPE.getOperation(op);
        if (this.operationType == OPERATION_TYPE.GOAL) goal = this;
        if (this.operationType == OPERATION_TYPE.START) start = this;
    }

    public int calculate(int previousValue) {

        return switch (this.operationType) {
            case MINUS -> previousValue - value;
            case ADD -> previousValue + value;
            case POW -> (int) Math.pow(previousValue,  value);
            case MULT -> previousValue * value;
            case DECREASE_GOAL -> goal.value - value;
            case INCREASE_GOAL -> goal.value + value;
            default -> previousValue;
        };

    }

    public int getValue() {
        return value;
    }

    public OPERATION_TYPE getOperationType() {
        return operationType;
    }
}
