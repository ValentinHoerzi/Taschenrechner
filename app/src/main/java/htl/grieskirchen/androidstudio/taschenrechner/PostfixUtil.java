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
                    while(pop != "("){
                        if(pop!=")"){
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
        return (token.equals("+") || token.equals("-") ? 1 : 2);
    }

    private static boolean isOperator(String token){
        return token == "+" || token =="-" || token=="/" || token=="*" || token=="("||token==")";
    }

    private static boolean isNumber(String token) {
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
                while(numberStack.size() >1){
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


             List<String> out = new ArrayList<>();
        for (int i = 0; i < infix.size(); i++) {
            String token = infix.get(i);
            if(isNumber(token)){
                out.add(token);
            }
            if(isOperator(token)){
                whlie (pop-operator > operator)
                        pop-operator -> out
                      push operator
                      if(token = "(" push "(")
                          if token ")"
                    while !"(" pop -> out


                   while !stack.empty pop -> out
            }
        }
 */