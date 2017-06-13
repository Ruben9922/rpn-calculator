package uk.co.ruben9922.rpncalculator.evaluator;

import java.util.function.IntBinaryOperator;

public class OperatorNode implements Node {
    private IntBinaryOperator operator;
    private Node leftOperand;
    private Node rightOperand;

    public OperatorNode(IntBinaryOperator operator, Node leftOperand, Node rightOperand) {
        this.operator = operator;
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
    }

    public int evaluate() {
        return operator.applyAsInt(leftOperand.evaluate(), rightOperand.evaluate());
    }
}
