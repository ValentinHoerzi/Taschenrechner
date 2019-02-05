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

        return null;
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
        return null;
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