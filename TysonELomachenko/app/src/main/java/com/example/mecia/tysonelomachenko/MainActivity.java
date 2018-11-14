package com.example.mecia.tysonelomachenko;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    boolean  tysonIsShowing = true;

    public void fade(View view) {

        ImageView tyson = findViewById(R.id.tyson);
        ImageView lomachenko = findViewById(R.id.lomachenko);

        if (tysonIsShowing) {
            tyson.animate().alpha(0).setDuration(2000);
            lomachenko.animate().alpha(1).setDuration(2000);
            tysonIsShowing = false;
        } else {
            tyson.animate().alpha(1).setDuration(2000);
            lomachenko.animate().alpha(0).setDuration(2000);
            tysonIsShowing = true;
        }
    }


}
