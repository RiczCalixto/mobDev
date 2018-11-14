package com.example.mecia.gameconect;

import android.media.Image;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //0: yellow; 1:red; 2:em branco
    int jogadorAtivo = 0;
    int[] estadoDoJogo = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] condicoesVitoria = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {2, 5, 8}, {1, 4, 7}, {0, 4, 8}, {2, 4, 6}};
    boolean jogoAtivo = true;



    public void dropIn (View view){
        ImageView counter = (ImageView) view;
        int marcadorTag = Integer.parseInt(counter.getTag().toString());
        String message;
        TextView playerHasWon = findViewById(R.id.playerHasWon);
        Button playAgain = (Button) findViewById(R.id.playAgain);
        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.poker);
        MediaPlayer mediaPlayer2 = MediaPlayer.create(this, R.raw.victory);





        if(jogoAtivo && estadoDoJogo[marcadorTag] == 2) {

            estadoDoJogo[marcadorTag] = jogadorAtivo;

            counter.setTranslationY(-1500);

            if (jogadorAtivo==0) {

                counter.setImageResource(R.drawable.yellow);
                counter.animate().translationYBy(1500).setDuration(500);
                mediaPlayer.start();

                jogadorAtivo = 1;

            } else {
                counter.setImageResource(R.drawable.red);
                counter.animate().translationYBy(1500).setDuration(1000).rotation(360);
                jogadorAtivo = 0;
                mediaPlayer.start();
            }

            for (int [] condicaoVitoria : condicoesVitoria){
                if(estadoDoJogo[condicaoVitoria[0]] == estadoDoJogo[condicaoVitoria[1]] &&
                        estadoDoJogo[condicaoVitoria[1]] == estadoDoJogo[condicaoVitoria[2]] &&
                        estadoDoJogo[condicaoVitoria[0]] != 2) {

                    jogoAtivo = false;

                    if (jogadorAtivo == 0){
                        message = "Vermelho";

                    } else {
                        message = "Amarelo";
                    }
                    mediaPlayer2.start();

                    playerHasWon.setText(message + " ganhou!");

                    playAgain.setVisibility(View.VISIBLE);
                    playerHasWon.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    public void playAgain (View view) {

        TextView playerHasWon = findViewById(R.id.playerHasWon);
        Button playAgain = findViewById(R.id.playAgain);
        playAgain.setVisibility(View.INVISIBLE);
        playerHasWon.setVisibility(View.INVISIBLE);
        android.support.v7.widget.GridLayout gridLayout = findViewById(R.id.gridLayout);

        for (int i = 0; i < gridLayout.getChildCount(); i++){

            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }

        jogadorAtivo = 0;
        jogoAtivo = true;

        for(int i=0; i < estadoDoJogo.length; i++){
            estadoDoJogo[i] = 2;
        }


    }
}
