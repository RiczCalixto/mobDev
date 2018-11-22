package com.example.mecia.prototipo1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddNewSongActivity extends AppCompatActivity {

    public static final String MAKE_SONG = "song";
    public static final String MAKE_ALBUM = "album";
    public static final String MAKE_BAND = "band";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_song);


    }

    public void saveBand(View view){
        EditText bandInput = findViewById(R.id.bandInput);
        EditText songInput = findViewById(R.id.songInput);
        EditText albumInput = findViewById(R.id.albumInput);

//        Intent i = getIntent();
//        Bundle b = i.getExtras();
//        Song s = (Song)b.getParcelable("mySong");
//        String song = s.getSongName();
//        String album = s.getAlbumName();
//        String band = s.getBandName();
//
//        i.getExtras(MAKE_SONG, bandInput.getText().toString());
//        i.getExtras(MAKE_ALBUM,)

        Intent intent = new Intent();
        intent.putExtra(MAKE_SONG, songInput.getText().toString());
        intent.putExtra(MAKE_ALBUM, albumInput.getText().toString());
        intent.putExtra(MAKE_BAND, bandInput.getText().toString());

        setResult(RESULT_OK, intent);

        finish();


    }
}
