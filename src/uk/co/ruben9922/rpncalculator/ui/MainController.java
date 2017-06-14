package uk.co.ruben9922.rpncalculator.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import uk.co.ruben9922.rpncalculator.evaluator.Evaluator;
import uk.co.ruben9922.rpncalculator.evaluator.Node;
import uk.co.ruben9922.rpncalculator.evaluator.ParseException;

public class MainController {
    @FXML
    private TextField rpnExpressionTextField;
    @FXML
    private TextField infixExpressionTextField;
    @FXML
    private TextField prefixExpressionTextField;
    @FXML
    private TextField resultTextField;
    @FXML
    private Label messageLabel;

    public void evaluateButtonAction() {
        String rpnExpression = rpnExpressionTextField.getText();
        try {
            // Produce parse tree from RPN expression
            Node parseTree = Evaluator.parse(rpnExpression);

            // Produce infix and prefix expression strings from parse tree, equivalent to original RPN expression
            String infixString = parseTree.toInfixString();
            infixExpressionTextField.setText(infixString);
            String prefixString = parseTree.toPrefixString();
            prefixExpressionTextField.setText(prefixString);

            // Evaluate parse tree and display result
            int result = parseTree.evaluate();
            resultTextField.setText(Integer.toString(result));

            // Display success message in message label
            messageLabel.setText("Evaluated successfully");
        } catch (ParseException e) {
            // Clear text of result text field
            resultTextField.setText("");

            // Display error message in message label
            messageLabel.setText("Error: " + e.getMessage());
        }
    }

    public void clearButtonAction() {
        rpnExpressionTextField.setText("");
        infixExpressionTextField.setText("");
        prefixExpressionTextField.setText("");
        resultTextField.setText("");
        messageLabel.setText("");
    }
}
