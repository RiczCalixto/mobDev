package com.example.mecia.ric;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class BrainTrainner extends AppCompatActivity {

    private Button goButton;
    CountDownTimer countDownTimer;
    private TextView timerTextView;
    //    private int segundos;
//    private Random numeroAleatorio1;
//    private Random numeroAleatorio2;
    private int randomPick;
    private int randomPick2;
    private TextView questionTextView;
    //    private int respostaCorreta;
//    private int botaoEscolhido;
    private int locationCorrectAnswer;
    public Random rand = new Random();
    public Button button0;
    public Button button1;
    public Button button2;
    public Button button3;
    public Button playAgain;
    public ArrayList<Integer> answers = new ArrayList<>();
    private TextView score;
    private int currentScore;
    private int numberOfQuestions;
    private TextView feedBack;

    public void playAgain(View view){
        generateRandomNumber();
        noComeço();
        currentScore = 0;
        numberOfQuestions = 0;
        score.setText(Integer.toString(currentScore)+"/"+Integer.toString(numberOfQuestions));
        playAgain.setVisibility(View.INVISIBLE);


    }

    public void correctAnswer(View view){

        String botaoEscolhido = view.getTag().toString();
        String respostaCorreta = String.valueOf(locationCorrectAnswer);


        if (botaoEscolhido.equals(respostaCorreta)){
            Log.i("correto", "correto");
            currentScore+=1;
            feedBack.setText("Correct! :D");


        } else {
            feedBack.setText("Wrong =(");
        }
        numberOfQuestions+=1;
        score.setText(Integer.toString(currentScore)+"/"+Integer.toString(numberOfQuestions));
        score.setVisibility(View.VISIBLE);
        generateRandomNumber();
    };

    public void start(View view){
        noComeço();
        currentScore = 0;
        numberOfQuestions = 0;
        score.setText(Integer.toString(currentScore)+"/"+Integer.toString(numberOfQuestions));

    }

    public void updateTimer(int secondsLeft){
        int segundos = secondsLeft;
        String segundosString = Integer.toString(segundos);

        timerTextView.setText(segundosString+"s");
    }

    public void generateRandomNumber () {
        randomPick = rand.nextInt(20) + 1;
        randomPick2 = rand.nextInt(20)+1;
        locationCorrectAnswer = rand.nextInt(4);
        nextQuestion();
    }

    public void nextQuestion() {
        answers.clear();

        for (int i = 0; i<4; i++){
            if(locationCorrectAnswer == i){
                answers.add(randomPick+randomPick2);
            } else {
                int wrongAnswer = rand.nextInt(41);
                while (wrongAnswer == randomPick+randomPick2){
                    wrongAnswer = rand.nextInt(41);
                }
                answers.add(wrongAnswer);
            }
        }
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
        questionTextView.setText(Integer.toString(randomPick)+" + "+Integer.toString(randomPick2));

    }

    public void noComeço () {
        timerTextView.setVisibility(View.VISIBLE);
        goButton.setVisibility(View.INVISIBLE);
        questionTextView.setVisibility(View.VISIBLE);
        button0.setVisibility(View.VISIBLE);
        button1.setVisibility(View.VISIBLE);
        button2.setVisibility(View.VISIBLE);
        button3.setVisibility(View.VISIBLE);
        score.setVisibility(View.VISIBLE);

        countDownTimer = new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                updateTimer((int) millisUntilFinished / 1000);

            }

            @Override
            public void onFinish() {
                playAgain.setVisibility(View.VISIBLE);
                feedBack.setText("");
                button0.setVisibility(View.INVISIBLE);
                button1.setVisibility(View.INVISIBLE);
                button2.setVisibility(View.INVISIBLE);
                button3.setVisibility(View.INVISIBLE);

            }
        }.start();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brain_trainner);

        timerTextView = findViewById(R.id.timerTextView);
        goButton = findViewById(R.id.goButton);
        questionTextView = findViewById(R.id.questionTextView);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        playAgain = findViewById(R.id.playAgain);
        score = findViewById(R.id.scoreTextView);
        feedBack = findViewById(R.id.feedbackTextView);

        button0.setVisibility(View.INVISIBLE);
        button1.setVisibility(View.INVISIBLE);
        button2.setVisibility(View.INVISIBLE);
        button3.setVisibility(View.INVISIBLE);
        timerTextView.setVisibility(View.INVISIBLE);
        questionTextView.setVisibility(View.INVISIBLE);
        playAgain.setVisibility(View.INVISIBLE);
        score.setVisibility(View.INVISIBLE);

        generateRandomNumber();


    }


}
