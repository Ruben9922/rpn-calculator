package uk.co.ruben9922.rpncalculator.evaluator;

import java.util.function.IntBinaryOperator;

public class Operator {
    private String operatorString;
    private IntBinaryOperator function;

    public Operator(String operatorString, IntBinaryOperator function) {
        this.operatorString = operatorString;
        this.function = function;
    }

    public String getOperatorString() {
        return operatorString;
    }

    public IntBinaryOperator getFunction() {
        return function;
    }
}
