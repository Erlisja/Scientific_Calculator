package controller;

import model.CalculatorModel;
import view.CalculatorView;

public class CalculatorController {
    private final CalculatorModel model;
    private final CalculatorView view;

    public CalculatorController(CalculatorModel model, CalculatorView view) {
        this.model = model;
        this.view = view;
    }

    public void run() {
        view.displayMessage("Welcome to Java Calculator");
        view.displayMessage("Type 'exit' anytime to quit. \n");

        while (true) {
            try {
                String input1 = view.getInput("Enter first number: ");
                if (input1.equalsIgnoreCase("exit")) break;
                double number1 = Double.parseDouble(input1);

                String operator = view.getInput("Enter operator (+, -, *, /): ");
                if (operator.equalsIgnoreCase("exit")) break;
                if (!operator.matches("[+\\-*/]")) {
                    view.displayMessage("Invalid operator! Try again.");
                    continue;
                }
                String input2 = view.getInput("Enter second number: ");
                if (input2.equalsIgnoreCase("exit")) break;
                double number2 = Double.parseDouble(input2);

                double result = model.calculate(number1,number2,operator.charAt(0));
                view.displayResult(result);
                view.displayMessage("________________________________");
            } catch (NumberFormatException e) {
                view.displayMessage("Invalid number! Try again.");
            } catch (ArithmeticException | IllegalArgumentException e) {
                view.displayMessage("Error:" + e.getMessage());
            }
        }
        view.displayMessage("Bye!");
    }
}
