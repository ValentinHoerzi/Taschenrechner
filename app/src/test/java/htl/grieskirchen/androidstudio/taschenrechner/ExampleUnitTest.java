package htl.grieskirchen.androidstudio.taschenrechner;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void testPostfix1() {
        List<String> exp = Arrays.asList("1", "1", "*","1","+");
        List<String> result = PostfixUtil.infixToPostfix(Arrays.asList("1","*","1","+","1"));
        assertEquals(exp,result);
    }

    @Test
    public void testPostfix2() {
        List<String> exp = Arrays.asList("1", "1", "1","*","+");
        List<String> result = PostfixUtil.infixToPostfix(Arrays.asList("1","+","1","*","1"));
        assertEquals(exp,result);
    }

    @Test
    public void testPostfix3() {
        List<String> exp = Arrays.asList("1", "1", "1","+","*");
        List<String> result = PostfixUtil.infixToPostfix(Arrays.asList("1","*","(","1","+","1",")"));
        assertEquals(exp,result);
    }

    @Test
    public void testPostfix4() {
        List<String> exp = Arrays.asList("1", "1", "-","1","+");
        List<String> result = PostfixUtil.infixToPostfix(Arrays.asList("1","-","1","+","1"));
        assertEquals(exp,result);
    }

    @Test
    public void testPostfix5() {
        List<String> exp = Arrays.asList("1", "1", "1","1","*","+","*","1","+");
        List<String> result = PostfixUtil.infixToPostfix(Arrays.asList("1","*","(","1","+","1","*","1",")","+","1"));
        assertEquals(exp,result);
    }


    @Test
    public void textCalc1(){
        BigDecimal exp = new BigDecimal(7);
        BigDecimal result = PostfixUtil.evaluate(Arrays.asList("2", "2", "*","3","+"));
        assertEquals(exp,result);
    }

    @Test
    public void textCalc2(){
        BigDecimal exp = new BigDecimal(8);
        BigDecimal result = PostfixUtil.evaluate(Arrays.asList("2", "2", "3","*","+"));
        assertEquals(exp,result);
    }

    @Test
    public void textCalc3(){

    }

    @Test
    public void textCalc4(){

    }

    @Test
    public void textCalc5(){

    }
}