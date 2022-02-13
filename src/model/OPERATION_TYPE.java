package model;

public enum OPERATION_TYPE {
    MINUS,
    ADD,
    GOAL,
    START,
    MULT,
    POW,
    WALL,
    DECREASE_GOAL,
    INCREASE_GOAL,
    UNDEFINED;


    public static OPERATION_TYPE getOperation(String op) {
        return switch (op) {
            case "-" -> MINUS;
            case "+" -> ADD;
            case "g" -> GOAL;
            case "*" -> MULT;
            case "^" -> POW;
            case "s" -> START;
            case "w" -> WALL;
            case "a" -> INCREASE_GOAL;
            case "b" -> DECREASE_GOAL;
            default -> UNDEFINED;
        };
    }
}
