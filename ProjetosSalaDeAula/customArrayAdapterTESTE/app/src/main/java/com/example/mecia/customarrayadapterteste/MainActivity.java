package com.example.mecia.customarrayadapterteste;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private MovieCustomArrayAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);


        ArrayList<Filmes> movieList = new ArrayList<Filmes>();

        movieList.add(new Filmes(R.drawable.ic_launcher_foreground, "after", "break"));

        movieList.add(new Filmes(R.drawable.ic_launcher_background, "After Earth" , "2013"));
        movieList.add(new Filmes(R.drawable.ic_launcher_background, "Baby Driver" , "2017"));
        movieList.add(new Filmes(R.drawable.ic_launcher_background, "Deadpool" , "2016"));
        movieList.add(new Filmes(R.drawable.ic_launcher_background, "Divergent" , "2014"));
        movieList.add(new Filmes(R.drawable.ic_launcher_background, "Fight Club" , "1999"));
        movieList.add(new Filmes(R.drawable.ic_launcher_background, "Jaws" , "1975"));
        movieList.add(new Filmes(R.drawable.ic_launcher_background, "Pirates of the Caribbean" , "2011"));
        movieList.add(new Filmes(R.drawable.ic_launcher_background, "Star Wars" , "2016"));
        movieList.add(new Filmes(R.drawable.ic_launcher_background, "The Grey" , "2011"));

        mAdapter = new MovieCustomArrayAdapter(this, movieList);

        listView.setAdapter(mAdapter);



    }
}
