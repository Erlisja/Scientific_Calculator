package view;

import java.util.Scanner;

public class CalculatorView {
    private final Scanner scanner = new Scanner(System.in);

    public String getInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public void displayResult(double result) {
       System.out.println("Result: " + result);
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }
}

