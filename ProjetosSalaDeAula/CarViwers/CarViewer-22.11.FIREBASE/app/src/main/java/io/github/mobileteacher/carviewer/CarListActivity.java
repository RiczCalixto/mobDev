package io.github.mobileteacher.carviewer;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CarListActivity extends AppCompatActivity {


    private RecyclerView carRecyclerView;

    private final int ADD_CAR_REQUEST_CODE = 71;
    private ProgressBar progressBar;

    DatabaseReference databaseRef;
    ChildEventListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_list);

        carRecyclerView = findViewById(R.id.recyclerview);
        progressBar = findViewById(R.id.progressBar);

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        databaseRef = firebaseDatabase.getReference("Cars");


        //Criar um Adapter para a RecyclerView
        CarAdapter adapter = new CarAdapter(new ArrayList<DataSnapshot>());
        //associar RecyclerView a um Adapter
        carRecyclerView.setAdapter(adapter);
        //Dizer a "forma" da RecyclerView
        RecyclerView.LayoutManager lm = new LinearLayoutManager(getApplicationContext());
        carRecyclerView.setLayoutManager(lm);

        carRecyclerView.addItemDecoration(
                new DividerItemDecoration(getApplicationContext(),
                        DividerItemDecoration.VERTICAL));

        progressBar.setVisibility(View.VISIBLE);

        databaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot != null && dataSnapshot.exists()){

                } else {
                    Snackbar.make(findViewById(R.id.root),
                            "NAO HA NADA AQUI",
                            Snackbar.LENGTH_LONG).show();
                }

                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        listener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                CarAdapter adapter = (CarAdapter) carRecyclerView.getAdapter();
                adapter.addItem(dataSnapshot);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                CarAdapter adapter = (CarAdapter) carRecyclerView.getAdapter();
                adapter.changeItem(dataSnapshot);
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                CarAdapter adapter = (CarAdapter) carRecyclerView.getAdapter();
                adapter.removeItem(dataSnapshot);
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

        databaseRef.addChildEventListener(listener);

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    public void addCar(View view){
        Intent intent = new Intent(this, AddNewCarActivity.class);
        startActivity(intent);
    }

}
