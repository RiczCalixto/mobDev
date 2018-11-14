package com.example.mecia.listviewdemo;

import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        final ListView listView = findViewById(R.id.listView);
//
//        final ArrayList<String> familia = new ArrayList<String>();
//
//        familia.add("Ric");
//        familia.add("Alex");
//        familia.add("Margarete");
//        familia.add("José");
//
////estou passando os dados do arraylist familia para o layout simple_list_item
//        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, familia);
//
////estou passando o arrayAdapter pro listView
//        listView.setAdapter(arrayAdapter);
//
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                //parent é a listView que a gnt ve no app
//                //view é o item individual
//                //int é a posição do item dentro do arrayList - listView
//                //long é o id
//                // view que foi clicado ficara invisivel: view.setVisibility(View.GONE);
//
//                Log.i("person selected", familia.get(position));
//            }
//        });
//    }


        final ListView listView = findViewById(R.id.dynamic);
//        final ArrayList<String> amigos = new ArrayList<String>();
//
//        amigos.add("Criança");
//        amigos.add("Gente");
//        amigos.add("hahaha");

        final ArrayList<String> amigos = new ArrayList<String>(asList("Criança", "Gente", "hahaha"));

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, amigos);

        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), amigos.get(position), Toast.LENGTH_SHORT).show();

            }
        });
    }





}
