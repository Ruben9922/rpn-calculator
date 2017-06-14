package uk.co.ruben9922.rpncalculator.evaluator;

public class OperandNode implements Node {
    private int operand;

    public OperandNode(int operand) {
        this.operand = operand;
    }

    public int evaluate() {
        return operand;
    }

    public String toInfixString() {
        return Integer.toString(operand);
    }

    public String toPrefixString() {
        return Integer.toString(operand);
    }
}
