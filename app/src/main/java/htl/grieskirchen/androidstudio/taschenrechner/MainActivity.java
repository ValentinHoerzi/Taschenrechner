package htl.grieskirchen.androidstudio.taschenrechner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

    public void click(View view) {
        Button buttonClicked = (Button) view;
        if(buttonClicked.getText().toString().toLowerCase().equals("clear")){
            textView.setText("");
        }else{
            textView.append(buttonClicked.getText());
        }

    }
}
