package com.example.mecia.conversomoeda;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void convertToDolar (View view){
        EditText realToDolar = findViewById(R.id.realToDolar);
        String valorEmReal = realToDolar.getText().toString();
        double valorEmRealDouble = Double.parseDouble(valorEmReal);
        double valorEmDolarDouble = valorEmRealDouble/3.69;
        String valorEmDolar = String.format("%.2f", valorEmDolarDouble);

        Toast.makeText(this, "R$"+valorEmReal+" vale $ "+valorEmDolar, Toast.LENGTH_LONG).show();

    }

    public void convertToReal (View view){
        EditText dolarToReal = findViewById(R.id.dolarToReal);
        String valorEmDolar = dolarToReal.getText().toString();
        double valorEmDolarDouble = Double.parseDouble(valorEmDolar);
        double valorEmRealDouble = valorEmDolarDouble * 3.69;
        String valorEmReal = String.format("%.2f", valorEmRealDouble);

        Toast.makeText(this, "$"+valorEmDolar+" vale R$ "+valorEmReal, Toast.LENGTH_LONG).show();
    }
}
