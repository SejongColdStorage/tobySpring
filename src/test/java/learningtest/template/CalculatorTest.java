package learningtest.template;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class CalculatorTest {

    private Calculator calculator;
    private String filePath;

    @Before
    public void setup(){
        calculator = new Calculator();
        filePath = getClass().getResource("numbers.txt").getPath();
    }

    @Test
    public void sumOfNumber() throws IOException {

        assertThat(calculator.calcSum(filePath), is(10));
    }


    @Test
    public void multiplyOfNumber() throws IOException {

        assertThat(calculator.calcMultiply(filePath), is(24));
    }

    @Test
    public void concatnate() throws IOException {
        assertThat(calculator.concatnate(filePath), is("1234"));
    }

}