package br.edu.infnet.primeiroprojeto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView counterTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        counterTextView = findViewById(R.id.counter);

    }

    public void logText(View button){
        Log.d("MINHA TAG", "Deu certo! Sucesso");
    }

    public void decrementCounter(View button){

        Button clickedButton = (Button) button;

        String content = counterTextView.getText().toString();
        int result = Integer.parseInt(content) + -1;
        counterTextView.setText(String.valueOf(result));
    }

    public void incrementCounter(View button){

        Button clickedButton = (Button) button;

        String content = counterTextView.getText().toString();
        int result = Integer.parseInt(content) + 1;
        counterTextView.setText(String.valueOf(result));
    }


}
