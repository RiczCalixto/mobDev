package com.example.mecia.appnumbershapes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void descubraClick(View view) {
        EditText numeroEscolhido = findViewById(R.id.numeroEscolhido);
        String numeroEscolhidoInt = numeroEscolhido.getText().toString();
        String message = numeroEscolhido.getText().toString();

        if (numeroEscolhidoInt.isEmpty()){

            Toast.makeText(this, "Por favor digite um número", Toast.LENGTH_LONG).show();

        } else {

            class Numero {

                int testandoNumero;
                double raizQuadrada;

                public boolean seraQuadrado() {
                    int y = 1;
                    int squareNumber = 1;

                    while (squareNumber < testandoNumero) {
                        y+=2;
                        squareNumber = squareNumber + y;
                    }

                    if (squareNumber == testandoNumero) {

                        return true;
                    } else {
                        return false;
                    }
                }

                public boolean seraTriangular() {
                    int x = 1;

                    int triangularNumber = 1;



                    while (triangularNumber < testandoNumero) {
                        x++;
                        triangularNumber = triangularNumber + x;
                    }



                    if (triangularNumber == testandoNumero) {
                        return true;
                    } else {
                        return false;
                    }
                }

            }

            Numero numero = new Numero();
            numero.testandoNumero = Integer.parseInt(numeroEscolhidoInt);
            numero.raizQuadrada = Math.sqrt(numero.testandoNumero);




            if (numero.seraQuadrado() && numero.seraTriangular()){
                message += " é quadrado perfeito e triangular";
                //Toast.makeText(this, "é os dois", Toast.LENGTH_LONG).show();
            } else if (numero.seraQuadrado()){
                message += " é quadrado perfeito e sua raiz quadrada é " + numero.raizQuadrada;
                //Toast.makeText(this, "É quadrado perfeito e sua raiz quadrada é " + numero.raizQuadrada, Toast.LENGTH_LONG).show();
            } else if (numero.seraTriangular()) {
                message += " é triangular";
                //Toast.makeText(this, "É triangular", Toast.LENGTH_SHORT).show();
            } else {
                message += " não é quadrado perfeito nem triangular";
                //Toast.makeText(this, "nem quadrado perfeito nem triangular", Toast.LENGTH_LONG).show();
            }

        }

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }
}
