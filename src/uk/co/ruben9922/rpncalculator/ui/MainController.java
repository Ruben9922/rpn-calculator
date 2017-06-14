package uk.co.ruben9922.rpncalculator.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import uk.co.ruben9922.rpncalculator.evaluator.Evaluator;
import uk.co.ruben9922.rpncalculator.evaluator.ParseException;

public class MainController {
    @FXML
    private TextField rpnExpressionTextField;
    @FXML
    private TextField resultTextField;
    @FXML
    private Label messageLabel;

    public void evaluateButtonAction(ActionEvent actionEvent) {
        String rpnExpression = rpnExpressionTextField.getText();
        try {
            // Evaluate RPN expression and display result in result text field
            int result = Evaluator.parse(rpnExpression).evaluate();
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

    public void clearButtonAction(ActionEvent actionEvent) {
        rpnExpressionTextField.setText("");
        resultTextField.setText("");
        messageLabel.setText("");
    }
}
