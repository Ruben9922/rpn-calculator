package uk.co.ruben9922.rpncalculator.evaluator;

public class OperatorNode implements Node {
    private Operator operator;
    private Node leftOperand;
    private Node rightOperand;

    public OperatorNode(Operator operator, Node leftOperand, Node rightOperand) {
        this.operator = operator;
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
    }

    public int evaluate() {
        return operator.getFunction().applyAsInt(leftOperand.evaluate(), rightOperand.evaluate());
    }

    public String toInfixString() {
        return String.format("(%s %s %s)", leftOperand.toInfixString(), operator.getOperatorString(), rightOperand.toInfixString());
    }

    public String toPrefixString() {
        return String.format("%s %s %s", operator.getOperatorString(), leftOperand.toPrefixString(), rightOperand.toPrefixString());
    }
}
