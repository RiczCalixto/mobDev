package com.example.mecia.prototipo1;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private final int ADD_SONG_REQUEST_CODE = 91;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recyclerView);


        List<Song> songsNames = readSongsFromFile();

        SongAdapter adapter = new SongAdapter(songsNames);
        recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(lm);
        recyclerView.addItemDecoration(
                new DividerItemDecoration(
                        this,
                        DividerItemDecoration.VERTICAL));



    }

    public void addSong(View view){

        //abrir outra Intent
        Intent intent = new Intent(this, AddNewSongActivity.class);
        startActivityForResult(intent, ADD_SONG_REQUEST_CODE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == ADD_SONG_REQUEST_CODE && resultCode == RESULT_OK){
            String song = data.getStringExtra(AddNewSongActivity.MAKE_SONG);
            String album = data.getStringExtra(AddNewSongActivity.MAKE_ALBUM);
            String band = data.getStringExtra(AddNewSongActivity.MAKE_BAND);

            Song newSong = new Song(song, album, band);

            SongAdapter adapter = (SongAdapter) recyclerView.getAdapter();
            adapter.addItem(newSong);

            saveSongToFile(newSong);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public void saveSongToFile(Song song){
        File myFile = new File(getFilesDir(), "savedSongs.txt");
        try {
            FileOutputStream outputStream = new FileOutputStream(myFile, true);
            outputStream.write(song.toString().getBytes());
            outputStream.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public List<Song> readSongsFromFile(){
        File myFile = new File(getFilesDir(), "savedSongs.txt");
        List<Song> songs = new ArrayList<>();

        try {
            FileReader reader = new FileReader(myFile);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line = bufferedReader.readLine();
            while (line != null){
                if (line.equals("#")){
                    String songName = bufferedReader.readLine();
                    String albumName = bufferedReader.readLine();
                    String bandName = bufferedReader.readLine();

                    Song newSong = new Song(songName, albumName, bandName);
                    songs.add(newSong);
                }
                line = bufferedReader.readLine();
            }
        } catch (FileNotFoundException exception){
            //
        } catch (IOException exception){

        }

        return songs;
    }
}
