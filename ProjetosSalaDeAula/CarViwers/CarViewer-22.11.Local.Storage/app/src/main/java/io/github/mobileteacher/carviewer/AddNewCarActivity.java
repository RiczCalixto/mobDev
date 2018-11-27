package io.github.mobileteacher.carviewer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddNewCarActivity extends AppCompatActivity {

    public static final String MAKE_KEY = "make";
    public static final String MODEL_KEY = "model";
    public static final String YEAR_KEY = "year";
    public static final String PRICE_KEY = "price";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_car);
    }


    public void saveCar(View view){
        EditText makeEditText = findViewById(R.id.make_edittext);
        EditText modelEditText = findViewById(R.id.model_edittext);
        EditText yearEditText = findViewById(R.id.year_edittext);
        EditText priceEditText = findViewById(R.id.price_edittext);

        Intent intent = new Intent();
        intent.putExtra(MAKE_KEY, makeEditText.getText().toString());
        intent.putExtra(MODEL_KEY, modelEditText.getText().toString());
        //converte para inteiro antes de enviar
        intent.putExtra(YEAR_KEY, Integer.parseInt(yearEditText.getText().toString()));
        // converte para double antes de enviar
        intent.putExtra(PRICE_KEY, Double.parseDouble(priceEditText.getText().toString()));

        setResult(RESULT_OK, intent);

        finish();

    }
}
