package htl.grieskirchen.androidstudio.taschenrechner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
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
    }

    public void btn0OnClick(View view) {
        textView.append("0");
    }

    public void btn1OnClick(View view) {
        textView.append("1");
    }

    public void btn2OnClick(View view) {
        textView.append("2");
    }

    public void btn3OnClick(View view) {
        textView.append("3");
    }

    public void btn4OnClick(View view) {
        textView.append("4");
    }

    public void btn5OnClick(View view) {
        textView.append("5");
    }

    public void btn6OnClick(View view) {
        textView.append("6");
    }

    public void btn7OnClick(View view) {
        textView.append("7");
    }

    public void btn8OnClick(View view) {
        textView.append("8");
    }

    public void btn9OnClick(View view) {
        textView.append("9");
    }

    public void btnPlusOnClick(View view) {
        textView.append("+");
    }

    public void btnMinusOnClick(View view) {
        textView.append("-");
    }

    public void btnMultiplyOnClick(View view) {
        textView.append("*");
    }

    public void btnDevideOnClick(View view) {
        textView.append("/");
    }

    public void btnBracketOpenOnClick(View view) {
        textView.append("(");
    }

    public void btnBracketCloseOnClick(View view) {
        textView.append(")");
    }

    public void btnPointOnClick(View view) {
        textView.append(".");
    }

    public void btnClearOpenOnClick(View view) {
        textView.setText("");
    }
}
