package uk.co.ruben9922.rpncalculator.evaluator;

public interface Node {
    int evaluate();

    String toInfixString();

    String toPrefixString();
}
