package com.example.mecia.appdatabuada;

import android.media.AudioManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class MainActivity extends AppCompatActivity {
    ListView listView;

    public void arrayListExe(int numeroNaBarra) {
        final ArrayList<String> multiplos = new ArrayList<String>();

        for (int i = 1; i <=10; i++){
            multiplos.add(Integer.toString(i*numeroNaBarra));
        }

        final ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, multiplos);

        listView.setAdapter(arrayAdapter);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
        final SeekBar barra = findViewById(R.id.barra);

        int max = 20;
        int startingPoint = 2;
        barra.setMax(max);
        barra.setProgress(startingPoint);

        arrayListExe(startingPoint);


        barra.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int min = 1;
                int numeroNaBarra;

                if (progress < min){
                    numeroNaBarra = min;
                    barra.setProgress(min);
                } else {
                    numeroNaBarra = progress;
                }
                arrayListExe(numeroNaBarra);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }








}
