package com.example.mecia.acerteosfamosos;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    private Button button0;
    private Button button1;
    private Button button2;
    private Button button3;
    private int chosenCeleb;
    String[] answers = new String[4];
    private int locationOfCorrectAnswer;
    private int incorrectAnswerLocation;
    private ArrayList<String> celebURLs = new ArrayList<String>();
    private ArrayList<String> celebNames = new ArrayList<String>();

    public void downloadingInformation(){
        DownloadTask task = new DownloadTask();
        String result = null;

        try{
            result = task.execute("http://www.posh24.se/kandisar").get();

            String[] splitResult = result.split("listedArticles");

            Pattern p = Pattern.compile("img src=\"(.*?)\"");
            Matcher m = p.matcher(splitResult[0]);

            while (m.find()) {
                celebURLs.add(m.group(1));
            }

            p = Pattern.compile("alt=\"(.*?)\"");
            m = p.matcher(splitResult[0]);

            while (m.find()) {
                celebNames.add(m.group(1));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void generateRandomImageAndAnswers(){
        try {

            Random rand = new Random();
            chosenCeleb = rand.nextInt(celebURLs.size()); //como o .size é na base de começar com 0, nao precisa add 1 no final

            ImageDownloader imageTask = new ImageDownloader();

            Bitmap celebImage = imageTask.execute(celebURLs.get(chosenCeleb)).get();

            imageView.setImageBitmap(celebImage);

            locationOfCorrectAnswer = rand.nextInt(4);

            for(int i=0; 1<4; i++){
                if(i == locationOfCorrectAnswer){
                    answers[i] = celebNames.get(chosenCeleb);

                } else {

                    incorrectAnswerLocation = rand.nextInt(celebURLs.size());

                    while (incorrectAnswerLocation == chosenCeleb){

                        incorrectAnswerLocation = rand.nextInt(celebURLs.size());
                    }

                    answers[i] = celebNames.get(incorrectAnswerLocation);
                }
            }

        } catch (Exception e){
            e.printStackTrace();
        }

        button0.setText(answers[0]);
        button1.setText(answers[1]);
        button2.setText(answers[2]);
        button3.setText(answers[3]);

    }

    public void clickResposta (View view){

        String botaoEscolhido = view.getTag().toString();
        String respostaCorreta = String.valueOf(locationOfCorrectAnswer);
        try{
            if (botaoEscolhido.equals(respostaCorreta)){
                Toast.makeText(this, "ACERTO MIZERAVI", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "ERROU", Toast.LENGTH_SHORT).show();
            }
        generateRandomImageAndAnswers();
        }catch(Exception e){
            e.printStackTrace();
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);

        downloadingInformation();

        generateRandomImageAndAnswers();


    }
}
