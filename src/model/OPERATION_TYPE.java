package model;

public enum OPERATION_TYPE {
    MINUS,
    ADD,
    GOAL,
    START,
    MULT,
    POW,
    UNDEFINED;


    public static OPERATION_TYPE getOperation(String op) {
        switch (op) {
            case "-":
                return MINUS;
            case "+":
                return ADD;
            case "g":
                return GOAL;
            case "*":
                return MULT;
            case "^":
                return POW;
            case "s":
                return START;
            default:
                return UNDEFINED;

        }
    }
}
