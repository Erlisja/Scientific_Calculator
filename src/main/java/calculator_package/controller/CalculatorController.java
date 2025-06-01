package calculator_package.controller;

import calculator_package.model.CalculatorModel;
import calculator_package.utils.HistoryManager;
import calculator_package.view.CalculatorView;

public class CalculatorController {
    private final CalculatorModel model;
    private final CalculatorView view;
    private final HistoryManager historyManager;

    public CalculatorController(CalculatorModel model, CalculatorView view) {
        this.model = model;
        this.view = view;
        this.historyManager = new HistoryManager("calculation_history.txt");
    }

    public void run() {
        boolean running = true;

        while (running) {
            view.showMenu();
            String choice = view.getInput("Please choose an option: ");

            switch (choice) {
                case "1": {
                    double num1 = view.getDoubleInput("Please enter a number: ");
                    double num2 = view.getDoubleInput("Please enter a number: ");
                    String operation = view.getInput("Please enter a operation (+, -, *, /): ");

                    try {
                        double result = model.calculate(num1, num2, operation.charAt(0));
                        view.displayResult(result);
                        historyManager.addHistoryEntry(num1 + " " + operation + " " + num2, result);
                    } catch (ArithmeticException e) {
                        view.displayMessage("Error:" + e.getMessage());
                    }
                    break;
                }
                case "2":
                    view.displayMessage(" Calculation History: ");
                    for (String entry : historyManager.getHistory()) {
                        view.displayMessage(entry);
                    }
                    break;
                case "3":
                    running = false;
                    view.displayMessage("Calculator exited. Goodbye!");
                    break;
                default:
                    view.displayMessage("Invalid option. Try again.");
            }
        }
    }
}

