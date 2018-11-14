package br.edu.infnet.travelapp;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    final String NOT_SELECTED = "Selecione um local";
    final String ADD_NEW_PLACE = "Adicionar novo local";
    final int ADD_PLACE_REQUEST_CODE = 71;

    Spinner spinner;
    ListView listView;
    String selectedPlace;

    CustomArrayAdapter adapter;
    List<Place> placesList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // coloca os dados iniciais na lista de locais
        placesList = new ArrayList();
        String[] names = {NOT_SELECTED, ADD_NEW_PLACE, "Copacabana", "Pão de Açucar",
                "Corcovado", "Maracanã"};
        String[] locations = {"", "", "-22.9828371,-43.1891771", "-22.9512272,-43.1649618",
                                    "-22.9516917,-43.2096117", "-22.9125958,-43.2298439"};
        for (int i = 0; i < names.length; i++){
            placesList.add(new Place(names[i], locations[i]));
        }

        // recupera referencia pro spinner
        spinner = findViewById(R.id.spinner);
        listView = findViewById(R.id.listview);

        // cria o nosso adapter
        adapter = new CustomArrayAdapter(this,
                            R.layout.support_simple_spinner_dropdown_item,
                            placesList);

        // configura nosso adapter no spinner
        spinner.setAdapter(adapter);
        listView.setAdapter(adapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                TextView textView = (TextView) view;
                selectedPlace = textView.getText().toString();
                if (selectedPlace.equals(ADD_NEW_PLACE)){
                    Intent intent = new Intent(getApplicationContext(), AddPlaceActivity.class);
                    startActivityForResult(intent, ADD_PLACE_REQUEST_CODE);
                }
                //Toast.makeText(MainActivity.this, selectedPlace, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // nada por enquanto
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textView = (TextView) view;
                selectedPlace = textView.getText().toString();
                if (selectedPlace.equals(ADD_NEW_PLACE)){
                    Intent intent = new Intent(getApplicationContext(), AddPlaceActivity.class);
                    startActivityForResult(intent, ADD_PLACE_REQUEST_CODE);
                } else {
                    travelNow();
                }
            }
        });
    }


    public void travelToPlace(View view){
        travelNow();
    }

    private void travelNow(){
        if (selectedPlace.equals(NOT_SELECTED)){
            Toast.makeText(this, "Por favor, selecione um local!", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            //passar dados pra intent
            String latLong = adapter.getLatLong(selectedPlace);
            if (latLong != null){
                intent.setData(Uri.parse("google.streetview:cbll=" + latLong));
                startActivity(intent);
            } else {
                Toast.makeText(this, "LatLong NULL", Toast.LENGTH_SHORT).show();
            }

        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_PLACE_REQUEST_CODE && resultCode == RESULT_OK){
            String name = data.getStringExtra(AddPlaceActivity.PLACE_NAME);
            String latLong = data.getStringExtra(AddPlaceActivity.LAT_LONG);

            adapter.addAndUpdate(new Place(name, latLong));
        }

    }

}
