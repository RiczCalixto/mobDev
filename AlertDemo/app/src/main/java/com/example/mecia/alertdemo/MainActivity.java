package com.example.mecia.alertdemo;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    TextView textView;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case R.id.idenglish:
                setLanguage("Ingles");
                return true;
            case R.id.idspanish:
                setLanguage("Portugues");
                return false;
            default:
                return false;
        }
    }

    public void setLanguage(String language) {
        sharedPreferences.edit().putString("language", language).apply();
        textView.setText(language);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        sharedPreferences = this.getSharedPreferences
                ("com.example.mecia.alertdemo", Context.MODE_PRIVATE);

        String language = sharedPreferences.getString("language", "Error");

        if (language.equals("Error")) {

            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_info)
                    .setTitle("Please choose your app language preference")
                    .setMessage("Pick between english and portuguese")
                    .setPositiveButton("English", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            setLanguage("English");

                        }
                    })
                    .setNegativeButton("Portuguese", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            setLanguage("Portuguese");

                        }
                    })
                    .show();
        } else {
            textView.setText(language);
        }
    }
}
