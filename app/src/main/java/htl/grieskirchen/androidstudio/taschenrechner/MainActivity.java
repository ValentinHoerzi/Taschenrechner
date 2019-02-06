package htl.grieskirchen.androidstudio.taschenrechner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    boolean lastButtonWasEvaluate = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeUI();

    }

    private void initializeUI() {
        textView = findViewById(R.id.textView);
    }

    public void Calculate(View view) {
        lastButtonWasEvaluate = true;
        String[] toCalcArr = textView.getText().toString().split("");
        List<String> infix = new ArrayList<>();
        List<String> currentNumbers = new ArrayList<>();

        for (int i = 0; i < toCalcArr.length; i++) {
            if(i == 0){
                i++;
            }
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

        textView.setText("");
        List<String> postfix = PostfixUtil.infixToPostfix(infix);
        BigDecimal calculated = PostfixUtil.evaluate(postfix);
        textView.setText(calculated.toString());
    }

    public void click(View view) {
        Button buttonClicked = (Button) view;
        if(lastButtonWasEvaluate){
            textView.setText("");
        }
        if(buttonClicked.getText().toString().toLowerCase().equals("clear")){
            textView.setText("");
        }else {
            textView.append(buttonClicked.getText());
        }
        lastButtonWasEvaluate=false;
    }
}
