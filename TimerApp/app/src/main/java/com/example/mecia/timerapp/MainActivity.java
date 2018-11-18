package com.example.mecia.timerapp;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private SeekBar timerBar;
    private Button button;
    private TextView textView;
    private MediaPlayer mediaPlayer;
    boolean ativo = false;
    CountDownTimer countDownTimer;

    public void resetTimer(){
        button.setText("GO!");
        timerBar.setEnabled(true);
        timerBar.setProgress(0);

        countDownTimer.cancel();

        ativo = false;
    }

    public void onStart(View view){

        if (ativo) {
            resetTimer();

        } else {
            ativo = true;
            timerBar.setEnabled(false);
            button.setText("STOP!");

            countDownTimer = new  CountDownTimer (timerBar.getProgress()*1000+100, 1000){

                @Override
                public void onTick(long millisUntilFinished) {
                    updateTimer((int) millisUntilFinished / 1000);
                }

                @Override
                public void onFinish() {
                    mediaPlayer.start();
                    resetTimer();
                }
            }.start();

        }
    }

    public void updateTimer(int secondsLeft){
        int minutos = secondsLeft / 60;
        int segundos = secondsLeft - (minutos*60);
        String segundosString = Integer.toString(segundos);

        if (segundos <= 9){
            segundosString = "0" + segundosString;
        }

        textView.setText(Integer.toString(minutos) + ":" + segundosString);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerBar = (SeekBar) findViewById(R.id.timerBar);
        button = (Button) findViewById(R.id.button);
        textView = (TextView) findViewById(R.id.textView);
        mediaPlayer = MediaPlayer.create(this, R.raw.templebell);

        button.setText("GO!");

        int maxEmSegundo = 1800;
        final int secInicial = 0;

        timerBar.setMax(maxEmSegundo);
        timerBar.setProgress(secInicial);
        textView.setText(Integer.toString(secInicial)+":00");

        timerBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateTimer(progress);
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
