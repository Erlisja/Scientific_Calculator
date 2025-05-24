import controller.CalculatorController;
import model.CalculatorModel;
import view.CalculatorView;

import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        CalculatorModel model = new CalculatorModel();
        CalculatorView view = new CalculatorView();
        CalculatorController controller = new CalculatorController(model, view);

        controller.run();
    }

}
