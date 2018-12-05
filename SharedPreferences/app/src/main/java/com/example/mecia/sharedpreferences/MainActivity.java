package com.example.mecia.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = this.getSharedPreferences("com.example.mecia.sharedpreferences", Context.MODE_PRIVATE);

        ArrayList<String> placesListSaving = new ArrayList<String>();

        //ArrayList<LatLng> locationsLatLongSaving = new ArrayList<LatLng>();

        
    }
}
