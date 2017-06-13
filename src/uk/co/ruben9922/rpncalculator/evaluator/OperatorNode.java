package uk.co.ruben9922.rpncalculator.evaluator;

import java.util.function.IntBinaryOperator;

public class OperatorNode implements Node {
    private IntBinaryOperator operator;
    private Node left;
    private Node right;

    public OperatorNode(IntBinaryOperator operator, Node left, Node right) {
        this.operator = operator;
        this.left = left;
        this.right = right;
    }

    public int evaluate() {
        return operator.applyAsInt(left.evaluate(), right.evaluate());
    }
}
