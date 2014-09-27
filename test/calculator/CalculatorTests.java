package calculator;

//import junit.framework.Assert;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CalculatorTests {


    private Calculator calculator;

    @Before
    public void setUp() throws Exception {
        calculator = new CalculatorImpl();
    }

    @Test
    public void shouldReturnZeroWhenNoInput() {
        assertEquals(0, calculator.add(""));
    }

    @Test
    public void shouldReturnArgumentWhenOneParameter() {
        assertEquals(1, calculator.add("1"));
    }

    @Test
    public void shouldSumWhenTwoArguments() {
        // given

        // when
        int add = calculator.add("1,2");

        // then
        assertEquals(3, add);
    }

    @Test
    public void shouldSumWhenTwoArgumentsSplitedByNewLine() {
        // given

        // when
        int add = calculator.add("1\n2");

        // then
        assertEquals(3, add);
    }

    @Test
    public void shouldSumwhithManyArgumentsSplitedByNewLineAndComa() {
        // given

        // when
        int add = calculator.add("1\n2,3");

        // then
        assertEquals(6, add);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotNegative() {
        // given
        // when                                    ~
        int add = calculator.add("-1");

        // then
        fail("Should throw Illl...");
    }

    @Test()
    public void shouldNotAddBiggerThan1000() {
       // when                                    ~
        int add = calculator.add("1,1001");

        // then
        assertEquals(1, add);
    }

    @Test
    public void shouldChangeToNewDelimeter() {
        // given

        // when
        int add = calculator.add("//;\n1;3");

        // then
        assertEquals(4, add);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotSumWithTwoSeparators() {
        // given
        // when                                    ~
        int add = calculator.add("1,\n");

        // then
        fail("Should throw Illl...");
    }
    @Test
    public void shouldAllowMultiCharactersDelimiter(){

        int add = calculator.add("//[***]\n1***2***3");

        assertEquals(6, add);
    }

    @Test
    public void shouldAllowMultiDelimiters(){

        int add = calculator.add("//[***][$$$]\n1***2***3");

        assertEquals(6, add);
    }

}
