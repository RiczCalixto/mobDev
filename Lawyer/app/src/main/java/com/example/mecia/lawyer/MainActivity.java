package com.example.mecia.lawyer;

import android.content.Intent;
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
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    LawyerClienteAdapter adapter;
    LawyerCliente clienteEditado = null;

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
                EditText numeroInput = (EditText) findViewById(R.id.numeroInput);
                EditText faseInput = (EditText) findViewById(R.id.faseInput);
                EditText obsInput = (EditText) findViewById(R.id.obsInput);
                numeroInput.setText("");
                faseInput.setText("");
                obsInput.setText("");
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
                boolean sucesso;
                if (clienteEditado != null) {
                    sucesso = dao.salvar(clienteEditado.getId(), numero, fase, obs);
                } else {
                    sucesso = dao.salvar(numero, fase, obs);
                }

                    if (sucesso) {
                        if (numero == null || fase== null || obs== null || numero.equals("") || fase.equals("") || obs.equals("")) {
                            Snackbar.make(v, "Preencha todos os campos por favor", Snackbar.LENGTH_LONG).setAction("Action", null).show();

                        } else {
                        //atualiza o novo cliente salvo no banco de dados e coloca no recyclerview
                        LawyerCliente cliente = dao.retornarUltimo();
                        if (clienteEditado != null) {
                            adapter.atualizarCliente(cliente);
                        } else {
                            adapter.adicionarCliente(cliente);
                        }

                        //clear nos campos
                        clienteEditado = null;
                        numeroInput.setText("");
                        faseInput.setText("");
                        obsInput.setText("");

                        Snackbar.make(v, "Salvo!", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                        findViewById(R.id.includemain).setVisibility(View.VISIBLE);
                        findViewById(R.id.includecadastro).setVisibility(View.INVISIBLE);
                        findViewById(R.id.fab).setVisibility(View.VISIBLE);
                    }
                    } else {
                        Snackbar.make(v, "Erro ao salvar.", Snackbar.LENGTH_LONG).setAction("Action", null).show();

                    }
                }

        });

        Intent intent = getIntent();
        if(intent.hasExtra("cliente")){
            findViewById(R.id.includemain).setVisibility(View.INVISIBLE);
            findViewById(R.id.includecadastro).setVisibility(View.VISIBLE);
            findViewById(R.id.fab).setVisibility(View.INVISIBLE);
            clienteEditado = (LawyerCliente) intent.getSerializableExtra("cliente");
            EditText numeroInput = (EditText) findViewById(R.id.numeroInput);
            EditText faseInput = (EditText) findViewById(R.id.faseInput);
            EditText obsInput = (EditText) findViewById(R.id.obsInput);

            numeroInput.setText(clienteEditado.getNumero());
            faseInput.setText(clienteEditado.getFase());
            obsInput.setText(clienteEditado.getObs());

        }

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

    //Variável que guarda o cliente que será editado e um método utilitário para selecionar o item de um Spinner a partir de seu valor

    private int getIndex(Spinner spinner, String myString){
        int index = 0;
        for(int i=0; i<spinner.getCount();i++){
            if(spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)){
                index = i;
                break;
            }
        }
        return index;
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


        return super.onOptionsItemSelected(item);
    }
}
