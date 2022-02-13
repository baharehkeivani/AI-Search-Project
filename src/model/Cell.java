package model;

public class Cell {
    int i;
    int j;
    private int value;
    private OPERATION_TYPE operationType;
    public static Cell start;
    public static Cell goal;

    public Cell(int i, int j, int value, OPERATION_TYPE op) {
        this.i = i;
        this.j = j;
        this.value = value;
        this.operationType = op;
        if (op == OPERATION_TYPE.GOAL) goal = this;
        if (op == OPERATION_TYPE.START) start = this;
    }

    public int calculate(int previousValue) {

        return switch (this.operationType) {
            case MINUS -> previousValue - value;
            case ADD -> previousValue + value;
            case POW -> (int) Math.pow((double) previousValue, (double) value);
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
