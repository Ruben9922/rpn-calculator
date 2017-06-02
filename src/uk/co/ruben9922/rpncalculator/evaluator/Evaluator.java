package uk.co.ruben9922.rpncalculator.evaluator;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Evaluator {
    public static int evaluate(String rpnExpression) throws EvaluateException {
        Map<String, Operator> operators = new HashMap<>(4);
        operators.put("+", new Operator((x, y) -> x + y));
        operators.put("*", new Operator((x, y) -> x * y));
        operators.put("-", new Operator((x, y) -> x - y));
        operators.put("/", new Operator((x, y) -> (int) Math.round((double) x / y)));

        Deque<Integer> stack = new LinkedList<>();

        // Split string, using one or more consecutive spaces, into lexemes
        String[] lexemes = rpnExpression.split("\\s+");

        for (String lexeme : lexemes) {
            Operator operator = operators.get(lexeme);
            if (operator == null) {
                // Lexeme is not an operator
                // Check if lexeme is integer operand; if not then throw exception
                try {
                    int operand = Integer.parseInt(lexeme);
                    stack.offerFirst(operand);
                } catch (NumberFormatException e) {
                    throw new EvaluateException("Unrecognised token");
                }
            } else {
                // Lexeme is an operator
                // Pop two operands from stack; if stack empty (either operand is null) then throw exception
                Integer operand1;
                Integer operand2;
                if ((operand2 = stack.pollFirst()) == null || (operand1 = stack.pollFirst()) == null) {
                    throw new EvaluateException("Missing operands");
                }

                // Apply operator to non-null operands and push result to stack
                int result = operator.apply(operand1, operand2);
                stack.offerFirst(result);
            }
        }

        // Check there is exactly one (non-null) operand on stack
        Integer operand;
        if (stack.size() > 1) {
            throw new EvaluateException("Too many operands");
        } else if (stack.size() == 0) { // TODO: Fix errors when only whitespace entered
            return 0;
        } else if (stack.size() == 1 && (operand = stack.pollFirst()) != null) {
            return operand;
        } else {
            throw new EvaluateException("Unknown error");
        }
    }
}
