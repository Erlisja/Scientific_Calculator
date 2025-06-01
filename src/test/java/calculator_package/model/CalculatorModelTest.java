package calculator_package.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorModelTest {
    @Test
    public void testAddition() {
        double result = CalculatorModel.calculate(5.0,3.0,'+');
        assertEquals(8.0,result,0.0001);
    }

    @Test
    public void testSubtraction() {
        double result = CalculatorModel.calculate(10.0,4.0,'-');
        assertEquals(6.0,result);
    }

    @Test
    public void testMultiplication() {
        double result = CalculatorModel.calculate(3.0,9.0,'*');
        assertEquals(27.0,result);
    }

    @Test
    public void testDivision() {
        double result = CalculatorModel.calculate(12.0,3.0,'/');
        assertEquals(4.0,result);
    }
    @Test
    public void divisonByZero() {
       Exception exception = assertThrows(ArithmeticException.class, () -> {
           CalculatorModel.calculate(5.0,0.0,'/');
       });
       assertEquals("Division by zero", exception.getMessage());
    }
    @Test
    public void testInvalidOperator() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            CalculatorModel.calculate(5.0,3.0,'%');
        });
        assertTrue(exception.getMessage().contains("Invalid operator"));
    }
}
