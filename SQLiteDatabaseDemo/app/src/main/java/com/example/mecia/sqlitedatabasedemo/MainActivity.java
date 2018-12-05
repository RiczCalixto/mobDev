package com.example.mecia.sqlitedatabasedemo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        // Cria a database
//        SQLiteDatabase ricsDatabase = this.openOrCreateDatabase("Usuarios", MODE_PRIVATE, null);
//        //Cria a table da database
//        ricsDatabase.execSQL("CREATE TABLE IF NOT EXISTS usuarios (nome VARCHAR, idade INT(3))");
//        //Insere dados na table criada da database. o VARCHAR é como se fosse string e INT é int. O número 3 é referente a 3 digitos (100 anos por exemplo).
//        //A partir do momento que vc dá run app uma vez, é necessário dar comment nas linhas de código que inserem os dados na data base. Isso deve acontecer, pois, caso contrário, será adicionado um novo nome Ricardo e Winnie e uma nova idade 29 e 31.
//
//        /* ricsDatabase.execSQL("INSERT INTO usuarios (nome, idade) VALUES ('Ricardo', 29)");
//        ricsDatabase.execSQL("INSERT INTO usuarios (nome, idade) VALUES ('Winnie', 31)"); */
//
//        //Retirar dados da database. o Query é responsável por retirar qualquer informação da database. O * significa que é pra selecionar tudo que está na table usuarios.
//        Cursor c = ricsDatabase.rawQuery("SELECT * FROM usuarios", null);
//        //Para ter acesso aos dados é preciso saber o index das informações.
//        int nomeIndex = c.getColumnIndex("nome");
//        int idadeIndex = c.getColumnIndex("idade");
//        //Coloca o cursor para selecionar o primeiro item armazenado na database para começar a "ler" a partir dali.
//        c.moveToFirst();
//
//        //while loop pra passar por todos os dados inseridos na tabela e printar no log
//        while (c != null){
//            Log.i("Nome", c.getString(nomeIndex));
//            Log.i("Idade", c.getString(idadeIndex));
//            c.moveToNext();
//        }

        // CHALLENGE - TABLE FOR BIG EVENT

//        try {
//
//
//            SQLiteDatabase challengeDatabase = this.openOrCreateDatabase("Eventos Marcantes", MODE_PRIVATE, null);
//
//            challengeDatabase.execSQL("CREATE TABLE IF NOT EXISTS eventos (evento VARCHAR, ano INT(4))");
//            challengeDatabase.execSQL("INSERT INTO eventos (evento, ano) VALUES ('Ataque ao World trade center', 2011)");
//            challengeDatabase.execSQL("INSERT INTO eventos (evento, ano) VALUES ('Batalha em Pearl Harbor', 1941)");
//
//            Cursor cursors = challengeDatabase.rawQuery("SELECT * FROM eventos", null);
//            int eventoIndex = cursors.getColumnIndex("evento");
//            int anoIndex = cursors.getColumnIndex("ano");
//            cursors.moveToFirst();
//
//            while (cursors != null) {
//                Log.i("Evento", cursors.getString(eventoIndex));
//                Log.i("Ano", Integer.toString(cursors.getInt(anoIndex)));
//
//                cursors.moveToNext();
//            }
//
//        } catch (Exception e){
//            e.printStackTrace();
//
//    }

    try {

        SQLiteDatabase ricsDatabase = this.openOrCreateDatabase("Usuarios", MODE_PRIVATE, null);

        ricsDatabase.execSQL("CREATE TABLE IF NOT EXISTS novosUsuarios (nome VARCHAR, idade INT(3), id INTEGER PRIMARY KEY)");

//        ricsDatabase.execSQL("INSERT INTO novosUsuarios (nome, idade) VALUES ('Ricardo', 29)");
//        ricsDatabase.execSQL("INSERT INTO novosUsuarios (nome, idade) VALUES ('Winnie', 98)");
//        ricsDatabase.execSQL("INSERT INTO novosUsuarios (nome, idade) VALUES ('Robson', 57)");
//        ricsDatabase.execSQL("INSERT INTO novosUsuarios (nome, idade) VALUES ('Elia', 27)");
//        ricsDatabase.execSQL("INSERT INTO novosUsuarios (nome, idade) VALUES ('Jeniffer', 15)");
//        ricsDatabase.execSQL("INSERT INTO novosUsuarios (nome, idade) VALUES ('Jesus', 06)");
//        ricsDatabase.execSQL("INSERT INTO novosUsuarios (nome, idade) VALUES ('Carmen', 14)");

        ricsDatabase.execSQL("DELETE FROM novosUsuarios WHERE id = 2"); //vai deletar o id 2, no caso a Winnie



        Cursor c = ricsDatabase.rawQuery("SELECT * FROM novosUsuarios", null);

        int nomeIndex = c.getColumnIndex("nome");
        int idadeIndex = c.getColumnIndex("idade");
        int idIndex = c.getColumnIndex("id");

        c.moveToFirst();


        while (c != null){
            Log.i("Nome", c.getString(nomeIndex));
            Log.i("Idade", Integer.toString(c.getInt(idadeIndex)));
            Log.i("ID", Integer.toString(c.getInt(idIndex)));
            c.moveToNext();
        }

    }catch (Exception e){

    }

    }

}
