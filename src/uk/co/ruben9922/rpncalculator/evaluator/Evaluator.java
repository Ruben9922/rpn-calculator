package uk.co.ruben9922.rpncalculator.evaluator;

import org.jetbrains.annotations.NotNull;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Evaluator {
    @NotNull
    public static Node parse(String rpnExpression) throws ParseException {
        // Create operators
        Map<String, Operator> operators = new HashMap<>(4);
        operators.put("+", new Operator("+", (x, y) -> x + y));
        operators.put("*", new Operator("*", (x, y) -> x * y));
        operators.put("-", new Operator("-", (x, y) -> x - y));
        operators.put("/", new Operator("/", (x, y) -> (int) Math.round((double) x / y)));

        // Create stack of trees
        Deque<Node> stack = new LinkedList<>();

        // Split string, using one or more consecutive spaces, into lexemes
        String[] lexemes = rpnExpression.split("\\s+");

        for (String lexeme : lexemes) {
            Operator operator = operators.get(lexeme);
            if (operator == null) {
                // Lexeme is not an operator, so it should be an integer operand
                // Try to parse as integer, create new node wrapping this integer, then push to stack
                try {
                    int operand = Integer.parseInt(lexeme);
                    OperandNode operandNode = new OperandNode(operand);
                    stack.offerFirst(operandNode);
                } catch (NumberFormatException e) {
                    throw new ParseException("Unrecognised token");
                }
            } else {
                // Lexeme is an operator
                // Pop two nodes from stack; if stack empty (either operand is null) then throw exception
                Node leftNode;
                Node rightNode;
                if ((rightNode = stack.pollFirst()) == null || (leftNode = stack.pollFirst()) == null) {
                    throw new ParseException("Missing operands");
                }

                // Create new node for operator with popped operand nodes as children and push to stack
                OperatorNode operatorNode = new OperatorNode(operator, leftNode, rightNode);
                stack.offerFirst(operatorNode);
            }
        }

        // Ensure there is exactly one tree on stack; if so evaluate it
        if (stack.size() == 0) { // TODO: Fix errors when only whitespace entered
            return new OperandNode(0);
        } else if (stack.size() == 1) {
            return stack.pollFirst();
        } else {
            throw new ParseException("Too many operands");
        }
    }
}
