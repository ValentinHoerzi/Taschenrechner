package htl.grieskirchen.androidstudio.taschenrechner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class PostfixUtil {
    private PostfixUtil() {
    }

    public static List<String> infixToPostfix(List<String> infix){
        List<String> postfix = new ArrayList<>();
        Stack<String> operators = new Stack<>();
        for(String token : infix){
            if(isNumber(token)){
                postfix.add(token);
            }
            if(isOperator(token)){
                while(!operators.isEmpty() && (getPrecedence(operators.peek())>getPrecedence(token) || getPrecedence(operators.peek())==getPrecedence(token)) &&!operators.peek().equals("(")){
                    postfix.add(operators.pop());
                }
                operators.push(token);
                if(token.equals(")")){
                    String pop = operators.pop();
                    while(!pop.equals("(")){
                        if(!pop.equals(")")){
                            postfix.add(pop);
                        }
                        pop = operators.pop();
                    }
                }
            }
        }
        while(!operators.isEmpty()){
            postfix.add(operators.pop());
        }
        return postfix;
    }

    private static int getPrecedence(String token){
        if(token.equals("+") || token.equals("-")){
            return 1;
        }else if(token.equals("*") || token.equals("/")){
            return 2;
        }
        return 3;
    }

    public static boolean isOperator(String token){
        return token.equals("+") || token.equals("-") || token.equals("/") || token.equals("*") || token.equals("(") || token.equals(")") ;
    }

    public static boolean isNumber(String token) {
       try{
           Double.parseDouble(token);
       }catch(Exception r){
           return false;
       }
        return true;
    }

    public static BigDecimal evaluate(List<String> postfix){
        Stack<String> numberStack = new Stack<>();
        for(String token:postfix){
            if(isNumber(token)){
                numberStack.push(token);
            }else{
                    String right_operant = numberStack.pop();
                    String left_operant = numberStack.pop();
                    BigDecimal left_bigD = new BigDecimal(left_operant);
                    BigDecimal right_bigD = new BigDecimal(right_operant);
                    switch (token){
                        case "+":
                            numberStack.push(left_bigD.add(right_bigD).toString());
                            break;
                        case "-":numberStack.push(left_bigD.subtract(right_bigD).toString());
            //select new operator
                            break;
                        case "*":
                            numberStack.push(left_bigD.multiply(right_bigD).toString());
                            break;
                        case "/":
                            numberStack.push(left_bigD.divide(right_bigD).toString());
                            break;
                    }
            }
        }
        return new BigDecimal(numberStack.pop());
    }
}
/*
      -> Leerzeichen bei Operatoren, nicht bei uzahlen -> -(-7-+-11-)-*-4 dann split nach " "
      -> 7 11 + 4 *
      dann entscheidungsprozess -> ist 7 zahl? stack 1, nach 2 zahlen operator verlangt und ergebnis in stack
      minus zahlen in klammer
      Max lösung zu ungültige eingabe -> schauen, ob der nutzer 2 operanten hintereinander eingegeben hat
      ^[1-9]+\.?[0-9]*$ -> kontrolliert ob es eine gültige zahl ist
 */