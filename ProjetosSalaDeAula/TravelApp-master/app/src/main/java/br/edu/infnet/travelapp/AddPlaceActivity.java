package br.edu.infnet.travelapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddPlaceActivity extends AppCompatActivity {

    public static final String PLACE_NAME = "name";
    public static final String LAT_LONG = "latLong";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_place);
    }


    public void saveNewPlace(View view){
        EditText nameField = findViewById(R.id.name_field);
        EditText latField = findViewById(R.id.lat_field);
        EditText longField = findViewById(R.id.long_field);

        String name = nameField.getText().toString();
        String latLong = String.format("%s,%s",
                                        latField.getText().toString(),
                                        longField.getText().toString());

        // cria intent com construtor vazio
        Intent intent = new Intent();
        // passa os dados que serão transmitidos
        intent.putExtra(PLACE_NAME, name);
        intent.putExtra(LAT_LONG, latLong);
        // informa que a ação foi concluída com sucesso
        setResult(RESULT_OK, intent);
        // encerra a Activity
        finish();
    }
}
