package kr.sadalmelik;

import org.junit.Test;

import static org.junit.Assert.*;

public class CalculatorJUnitTest {
    public void testAdd(){
        //given
        Calculator calculator = new Calculator();
        int a = 4;
        int b = 5;
        //when
        int sum = calculator.add(a,b);
        //then
        assertEquals(sum, 9);
    }

    @Test
    public void testSubtract(){
        //given
        Calculator calculator = new Calculator();
        int a = 9;
        int b = 5;
        //when
        int sum = calculator.subtract(a,b);
        //then
        assertEquals(sum, 4);
    }
}