package calculator_package;
import calculator_package.view.CalculatorView;
import calculator_package.model.CalculatorModel;
import calculator_package.controller.CalculatorController;

public class Main{
    public static void main(String[] args) {
        CalculatorModel model = new CalculatorModel();
        CalculatorView view = new CalculatorView();
        CalculatorController controller = new CalculatorController(model, view);

        controller.run();
    }

}
