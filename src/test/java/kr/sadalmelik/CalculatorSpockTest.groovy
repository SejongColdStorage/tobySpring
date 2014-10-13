package kr.sadalmelik

import org.junit.Test
import spock.lang.Specification

import static org.junit.Assert.assertEquals

/**
 * Created by SejongPark on 14. 10. 13..
 */
class CalculatorSpockTest extends Specification {

    def "더하기 테스트"(){
        setup :
        Calculator calculator = new Calculator();
        def a = 4;
        def b = 5;

        when :
        def sum = calculator.add(a,b);

        then :
        sum == 9;
    }
}
