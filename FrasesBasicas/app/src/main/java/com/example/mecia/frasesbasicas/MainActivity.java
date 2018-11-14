package com.example.mecia.frasesbasicas;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onTranslate(View view){
//        Button button1 = (Button) findViewById(R.id.button1);
//        Button button2 = (Button) findViewById(R.id.button2);
//        Button button3 = (Button) findViewById(R.id.button3);
//        Button button4 = (Button) findViewById(R.id.button4);
//        Button button5 = (Button) findViewById(R.id.button5);
//        Button button6 = (Button) findViewById(R.id.button6);
//        Button button7 = (Button) findViewById(R.id.button7);

        Button buttonPressed = (Button) view;
        MediaPlayer mediaPlayer = MediaPlayer.create(this, getResources().getIdentifier(buttonPressed.getTag().toString(), "raw", getPackageName()));
        mediaPlayer.start();
        final AudioManager audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        SeekBar barraVolume = (SeekBar) findViewById(R.id.seekBar2);
        int maxVol = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        barraVolume.setMax(maxVol);
        barraVolume.setProgress(currentVolume);

        barraVolume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, i, 0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });





//        int marcadorTag1 = Integer.parseInt(button1.getTag().toString());
//        int marcadorTag2 = Integer.parseInt(button2.getTag().toString());
//        int marcadorTag3 = Integer.parseInt(button3.getTag().toString());
//        int marcadorTag4 = Integer.parseInt(button4.getTag().toString());
//        int marcadorTag5 = Integer.parseInt(button5.getTag().toString());
//        int marcadorTag6 = Integer.parseInt(button6.getTag().toString());
//        int marcadorTag7 = Integer.parseInt(button7.getTag().toString());


//        MediaPlayer doyouspeak = MediaPlayer.create(this, R.raw.doyouspeakenglish);
//        MediaPlayer goodevening = MediaPlayer.create(this, R.raw.goodevening);
//        MediaPlayer hello = MediaPlayer.create(this, R.raw.doyouspeakenglish);
//        MediaPlayer howareyou = MediaPlayer.create(this, R.raw.howareyou);
//        MediaPlayer ilivein = MediaPlayer.create(this, R.raw.ilivein);
//        MediaPlayer mynameis = MediaPlayer.create(this,R.raw.mynameis);
//        MediaPlayer please = MediaPlayer.create(this, R.raw.please);
//        MediaPlayer welcome = MediaPlayer.create(this, R.raw.welcome);

//        if(buttonPressed){
//            doyouspeak.start();
//
//        } else if (marcadorTag2){
//            goodevening.start();
//
//        } else if (marcadorTag3){
//            hello.start();
//
//        } else if (marcadorTag4){
//            ilivein.start();
//
//        } else if (marcadorTag5){
//            mynameis.start();
//
//        } else if (marcadorTag6){
//            please.start();
//
//        } else if (marcadorTag7){
//            welcome.start();
//
//        } else{
//            howareyou.start();
//
//        }





    }
}
