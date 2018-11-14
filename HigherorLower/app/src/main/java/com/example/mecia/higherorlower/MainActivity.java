package com.example.mecia.higherorlower;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int randomPick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        generateRandomNumber();
    }

    public void generateRandomNumber () {
        Random numeroAleatorio = new Random();
        randomPick = numeroAleatorio.nextInt(11) + 10;
    }


    public void acertoMizeravi (View view){
        EditText editText = findViewById(R.id.editText);
        //String numeroEscolhido = editText.getText().toString();
        double numeroEscolhidoInt = Double.parseDouble(editText.getText().toString());
        String message;

         if(numeroEscolhidoInt == randomPick){
             message = "acerto mizeravi. Jogue de novo!";
             //Toast.makeText(this, "acertou mizeravi", Toast.LENGTH_LONG).show();
             generateRandomNumber();
         }  else if (numeroEscolhidoInt < randomPick){
             message = "mais!!";
             //Toast.makeText(this, "mais", Toast.LENGTH_LONG).show();
         } else {
             message = "menos!!";
             //Toast.makeText(this, "menos!!!", Toast.LENGTH_LONG).show();
         }

         Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
