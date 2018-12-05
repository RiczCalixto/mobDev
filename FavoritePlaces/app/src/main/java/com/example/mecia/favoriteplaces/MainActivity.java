package com.example.mecia.favoriteplaces;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private String ADD_NEW_PLACE = "Adicione novo local";
    final int ADD_PLACE_REQUEST_CODE = 92;
    static ArrayList<String> placesList = new ArrayList<String>(asList("Adicionar novo local.. "));
    static ArrayAdapter adapter;
    private String selectedPlace;
    private Intent intent;
    static ArrayList<LatLng> locationsLatLong = new ArrayList<LatLng>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences =
                this.getSharedPreferences("com.example.mecia.favoriteplaces",
                        Context.MODE_PRIVATE);

        ArrayList<String> latitudes = new ArrayList<>();
        ArrayList<String> longitudes = new ArrayList<>();

        placesList.clear();
        latitudes.clear();
        longitudes.clear();
        locationsLatLong.clear();

        try {

            placesList = (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString("placesSaving", ObjectSerializer.serialize(new ArrayList<String>())));
            latitudes = (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString("placesLats", ObjectSerializer.serialize(new ArrayList<String>())));
            longitudes = (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString("placesLongs", ObjectSerializer.serialize(new ArrayList<String>())));


        }catch (Exception e){
            e.printStackTrace();
        }

        //reconstroi a class LatLng e coloca as strings de latidudes e longitudes no locationLatLong
        if (placesList.size() > 0 && latitudes.size() > 0 && longitudes.size()>0){
            if (placesList.size() == latitudes.size() && placesList.size() == longitudes.size()){
                for (int i=0; i<latitudes.size(); i++){
                    locationsLatLong.add(new LatLng(Double.parseDouble(latitudes.get(i)), Double.parseDouble(longitudes.get(i))));

                }
            }
        } else {

            locationsLatLong.add(new LatLng(0, 0));
        }

        listView = findViewById(R.id.listView);
        String[] names = new String[0];

        //placesList = new ArrayList<String>(asList(ADD_NEW_PLACE));
        //locationsLatLong = new ArrayList<LatLng>();

//        for (int i = 0; i < names.length; i++){
//            placesList.add(new String(names[0]));
//        }

        locationsLatLong.add(new LatLng(0, 0));
//        placesList.add(new String("Adiciar novo local.."));
        adapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, placesList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textView = (TextView) view;
                selectedPlace = textView.getText().toString();
                if(selectedPlace.equals(ADD_NEW_PLACE)){
                    intent = new Intent(getApplicationContext(), MapActivity.class);
                    intent.putExtra("placeNumber", position);
                    startActivityForResult(intent, ADD_PLACE_REQUEST_CODE);
                } else {
                    travelNow(position);
                }
            }
        });

    }

    public void travelNow(int position) {
        intent = new Intent(getApplicationContext(), MapActivity.class);
        intent.putExtra("placeNumber", position);
        startActivity(intent);
    }
}