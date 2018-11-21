package com.example.mecia.materialdesigntests;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }

    //Add popu-up menu à toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    //select each item of the menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id==R.id.item_1){
            Toast.makeText(getApplicationContext(), "ITEM 1 SELECTED", Toast.LENGTH_SHORT).show();
        } else if (id==R.id.item_2){

            Toast.makeText(getApplicationContext(), "ITEM 2 SELECTED", Toast.LENGTH_SHORT).show();
        } else if (id==R.id.item_3){

            Toast.makeText(getApplicationContext(), "ITEM 3 SELECTED", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }
}
