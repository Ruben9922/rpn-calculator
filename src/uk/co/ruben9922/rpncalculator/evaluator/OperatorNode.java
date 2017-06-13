package uk.co.ruben9922.rpncalculator.evaluator;

public class OperatorNode implements Node {
    private Operator operator;
    private Node left;
    private Node right;

    public OperatorNode(Operator operator, Node left, Node right) {
        this.operator = operator;
        this.left = left;
        this.right = right;
    }

    public int evaluate() {
        return operator.apply(left.evaluate(), right.evaluate());
    }
}
