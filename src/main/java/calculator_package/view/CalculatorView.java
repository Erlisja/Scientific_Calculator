package calculator_package.view;

import java.util.Scanner;

public class CalculatorView {
    private final Scanner scanner = new Scanner(System.in);

    public void showMenu() {
        System.out.println("\n ====== Java Calculator ======");
        System.out.println("1. Perform Calculation");
        System.out.println("2. Show History");
        System.out.println("3. Exit");
    }

    public String getInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public double getDoubleInput(String prompt) {
        while (true) {
            try {
                return Double.parseDouble(getInput(prompt));
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Try again.");
            }
        }
    }

    public void displayResult(double result) {
       System.out.println("Result: " + result);
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }
}

