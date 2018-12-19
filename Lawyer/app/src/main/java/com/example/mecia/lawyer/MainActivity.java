package com.example.mecia.lawyer;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    LawyerClienteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findViewById(R.id.includemain).setVisibility(View.INVISIBLE);
                findViewById(R.id.includecadastro).setVisibility(View.VISIBLE);
                findViewById(R.id.fab).setVisibility(View.INVISIBLE);
            }
        });

        FloatingActionButton btnCancelar = (FloatingActionButton) findViewById(R.id.btnCancel);
        btnCancelar.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.includemain).setVisibility(View.VISIBLE);
                findViewById(R.id.includecadastro).setVisibility(View.INVISIBLE);
                findViewById(R.id.fab).setVisibility(View.VISIBLE);
            }
        });

        FloatingActionButton btnSalvar = (FloatingActionButton) findViewById(R.id.btnSalvar);
        btnSalvar.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                //para carregar os inputs
                EditText numeroInput = (EditText) findViewById(R.id.numeroInput);
                EditText faseInput = (EditText) findViewById(R.id.faseInput);
                EditText obsInput = (EditText) findViewById(R.id.obsInput);

                //para adquirir os valores escritos
                String numero = numeroInput.getText().toString();
                String fase = faseInput.getText().toString();
                String obs = obsInput.getText().toString();

                //salvar os dados no LawyerClienteDAO
                LawyerClienteDAO dao = new LawyerClienteDAO(getBaseContext());
                boolean sucesso = dao.salvar(numero, fase, obs);
                if(sucesso){
                    //clear nos campos
                    numeroInput.setText("");
                    faseInput.setText("");
                    obsInput.setText("");

                    Snackbar.make(v, "Salvo!", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                    findViewById(R.id.includemain).setVisibility(View.VISIBLE);
                    findViewById(R.id.includecadastro).setVisibility(View.INVISIBLE);
                    findViewById(R.id.fab).setVisibility(View.VISIBLE);
                }else {
                    Snackbar.make(v, "Erro ao salvar.", Snackbar.LENGTH_LONG).setAction("Action", null).show();

                }
            }
        });

        configurarRecycler();
    }

    private void configurarRecycler(){
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        LawyerClienteDAO dao = new LawyerClienteDAO(this);
        adapter = new LawyerClienteAdapter(dao.retornarTodos());
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
