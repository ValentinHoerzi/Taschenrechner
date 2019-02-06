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
public class testPostFixUtil {
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
    public void textCalc1(){ // A*B+c where A&B=2, C=3 = 7
        BigDecimal exp = new BigDecimal(7);
        BigDecimal result = PostfixUtil.evaluate(Arrays.asList("2", "2", "*","3","+"));
        assertEquals(exp,result);
    }

    @Test
    public void textCalc2(){ // A+B*c where A&B =2, C=3 = 8
        BigDecimal exp = new BigDecimal(8);
        BigDecimal result = PostfixUtil.evaluate(Arrays.asList("2", "2", "3","*","+"));
        assertEquals(exp,result);
    }

    @Test
    public void textCalc3(){
        BigDecimal exp = new BigDecimal(12); //A*(B+C) where A=2, B&C = 3 =12
        BigDecimal result = PostfixUtil.evaluate(PostfixUtil.infixToPostfix(Arrays.asList("2","*","(","3","+","3",")")));
        assertEquals(exp,result);
    }

    @Test
    public void textCalc4(){
        BigDecimal exp = new BigDecimal(3); //A-B+C Where each is 3 = 3
        BigDecimal result = PostfixUtil.evaluate(PostfixUtil.infixToPostfix(Arrays.asList("3","-","3","+","3")));
        assertEquals(exp,result);
    }

    @Test
    public void textCalc5(){ //A * (B + C * D) + E where A=2, C&D=3, B=10
        BigDecimal exp = new BigDecimal(38); //A-B+C Where each is 3 = 3
        BigDecimal result = PostfixUtil.evaluate(PostfixUtil.infixToPostfix(Arrays.asList("2","*","(","10","+","3","*","3",")")));
        assertEquals(exp,result);
    }

    @Test
    public void textMainCalculationAlgorythm1(){
        List<String> res = toInfix("2 + 2");
        List<String> exp = Arrays.asList("2","+","2");
        assertEquals(exp,res);
    }

    @Test
    public void textMainCalculationAlgorythm2(){
        List<String> res = toInfix("22 + 2");
        List<String> exp = Arrays.asList("22","+","2");
        assertEquals(exp,res);
    }

    @Test
    public void textMainCalculationAlgorythm3(){
        List<String> res = toInfix("222 + 222");
        List<String> exp = Arrays.asList("222","+","222");
        assertEquals(exp,res);
    }

    @Test
    public void textMainCalculationAlgorythm4(){
        List<String> res = toInfix("2.22 + 222");
        List<String> exp = Arrays.asList("2.22","+","222");
        assertEquals(exp,res);
    }

    @Test
    public void textMainCalculationAlgorythm5(){
        List<String> res = toInfix("34 +  ( 3 * 2 ) ");
        List<String> exp = Arrays.asList("34","+","(","3","*","2",")");
        assertEquals(exp,res);

        List<String> postfix = PostfixUtil.infixToPostfix(exp);
        BigDecimal evaluate = PostfixUtil.evaluate(postfix);

        assertEquals(BigDecimal.valueOf(40),evaluate);
    }

    @Test
    public void mainTest(){
        List<String> infix = toInfix("3 + 3");
        List<String> posfix = PostfixUtil.infixToPostfix(infix);
        BigDecimal res = PostfixUtil.evaluate(posfix);
        BigDecimal exp = BigDecimal.valueOf(6);
        assertEquals(res, exp);
    }

    private List<String> toInfix(String input){
        String[] toCalcArr = input.split("");
        List<String> infix = new ArrayList<>();
        List<String> currentNumbers = new ArrayList<>();

        for (int i = 0; i < toCalcArr.length; i++) {
            String current = toCalcArr[i];
            if(PostfixUtil.isNumber(current) || current.equals(".")){
                currentNumbers.add(current);
            }else{
                if(!current.equals(" ")){
                    infix.add(current);
                }else if (!currentNumbers.isEmpty()){
                    String temp = "";
                    for(String thatNumb : currentNumbers){
                        temp+=thatNumb;
                    }
                    infix.add(temp);
                    currentNumbers.clear();
                }
            }
        }
    if(!currentNumbers.isEmpty()){
        String temp = "";
        for(String thatNumb : currentNumbers){
            temp+=thatNumb;
        }
        infix.add(temp);
    }
        return infix;
    }
}