package io.github.mobileteacher.carviewer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddNewCarActivity extends AppCompatActivity {

    public static final String MAKE_KEY = "make";
    public static final String MODEL_KEY = "model";
    public static final String YEAR_KEY = "year";
    public static final String PRICE_KEY = "price";

    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_car);


        firebaseDatabase = FirebaseDatabase.getInstance();
    }


    public void saveCar(View view){
        EditText makeEditText = findViewById(R.id.make_edittext);
        EditText modelEditText = findViewById(R.id.model_edittext);
        EditText yearEditText = findViewById(R.id.year_edittext);
        EditText priceEditText = findViewById(R.id.price_edittext);


        DatabaseReference databaseRef = firebaseDatabase.getReference();

        Car car = new Car(makeEditText.getText().toString(),
                modelEditText.getText().toString(),
                Integer.parseInt(yearEditText.getText().toString()),
                Double.parseDouble(priceEditText.getText().toString()));


        databaseRef.child("Cars").push().setValue(car);


        finish();

    }
}
