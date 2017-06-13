package uk.co.ruben9922.rpncalculator.evaluator;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.function.IntBinaryOperator;

public class Evaluator {
    public static int evaluate(String rpnExpression) throws EvaluateException {
        // Create operators
        Map<String, IntBinaryOperator> operators = new HashMap<>(4);
        operators.put("+", (x, y) -> x + y);
        operators.put("*", (x, y) -> x * y);
        operators.put("-", (x, y) -> x - y);
        operators.put("/", (x, y) -> (int) Math.round((double) x / y));

        // Create stack of trees
        Deque<Node> stack = new LinkedList<>();

        // Split string, using one or more consecutive spaces, into lexemes
        String[] lexemes = rpnExpression.split("\\s+");

        for (String lexeme : lexemes) {
            IntBinaryOperator operator = operators.get(lexeme);
            if (operator == null) {
                // Lexeme is not an operator, so it should be an integer operand
                // Try to parse as integer, create new node wrapping this integer, then push to stack
                try {
                    int operand = Integer.parseInt(lexeme);
                    OperandNode operandNode = new OperandNode(operand);
                    stack.offerFirst(operandNode);
                } catch (NumberFormatException e) {
                    throw new EvaluateException("Unrecognised token");
                }
            } else {
                // Lexeme is an operator
                // Pop two nodes from stack; if stack empty (either operand is null) then throw exception
                Node leftNode;
                Node rightNode;
                if ((rightNode = stack.pollFirst()) == null || (leftNode = stack.pollFirst()) == null) {
                    throw new EvaluateException("Missing operands");
                }

                // Create new node for operator with popped operand nodes as children and push to stack
                OperatorNode operatorNode = new OperatorNode(operator, leftNode, rightNode);
                stack.offerFirst(operatorNode);
            }
        }

        // Ensure there is exactly one tree on stack; if so evaluate it
        if (stack.size() == 0) { // TODO: Fix errors when only whitespace entered
            return 0;
        } else if (stack.size() == 1) {
            return stack.pollFirst().evaluate();
        } else {
            throw new EvaluateException("Too many operands");
        }
    }
}
