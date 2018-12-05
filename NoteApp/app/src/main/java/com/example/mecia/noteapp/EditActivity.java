package com.example.mecia.noteapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.ActionMenuItemView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toolbar;

import java.util.HashSet;

public class EditActivity extends AppCompatActivity {

    int noteID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        EditText editText = findViewById(R.id.editText);

        Intent intent = getIntent();
        noteID = intent.getIntExtra("noteID", -1);

        if(noteID != -1){
            editText.setText(MainActivity.notes.get(noteID));
        } else {
            MainActivity.notes.add("");
            noteID = MainActivity.notes.size() - 1;
        }

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                MainActivity.notes.set(noteID, String.valueOf(s));
                MainActivity.arrayAdapter.notifyDataSetChanged();

                SharedPreferences sharedPreferences = getApplicationContext()
                        .getSharedPreferences("com.example.mecia.noteapp"
                        , Context.MODE_PRIVATE);

                HashSet<String> set = new HashSet<>(MainActivity.notes); // convert this notes array list into a HashSet
                sharedPreferences.edit().putStringSet("notes", set).apply(); //salva o que foi convertido para hash no sharedpreference



            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }



}
