package uk.co.ruben9922.rpncalculator.evaluator;

import java.util.function.IntBinaryOperator;

public class Operator {
    private IntBinaryOperator function;

    public Operator(IntBinaryOperator function) {
        this.function = function;
    }

    public int apply(int operand1, int operand2) {
        return function.applyAsInt(operand1, operand2);
    }
}
