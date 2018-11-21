package com.example.mecia.downloadingwebcontent;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    public class DownloadTask extends AsyncTask<String, Void, String>{
        @Override
        protected String doInBackground(String... strings) {

            String result = "";
            URL url;
            HttpURLConnection urlConnection = null; // é o 'browser' que vai ler o http do site e fazer o download pro nosso app

            //convertendo a strings[0] em um objeto URL
            try {
                url = new URL(strings[0]); // armazena a URL e converte
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = urlConnection.getInputStream(); //pegar os dados que esta vindo pela url
                InputStreamReader reader = new InputStreamReader(in);//lê os dados que foram buscados pelo inputStream
                int data = reader.read();//lê os caracteres dos dados de forma individual (letra por letra)

                while (data != -1){//enquanto data não for igual a -1, ele vai ler todos os caracteres
                    char current = (char) data; //transformar o dado em caracteres
                    result += current; // add letra por letra
                    data = reader.read();
                }

                return result;


            } catch (Exception e) {
                e.printStackTrace();
                return "Failed cuz of error";
            }
            //Lembrar de colocar a permissão de usar a internet para fazer download alterar no manifest
            //Log.i("URL", strings[0]); //vai passar a primeira URL q entrou no task.execute

            //return "sex";
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DownloadTask task = new DownloadTask();
        String result = null; // criei a variavel result para printar o que foi retornado no doInBackground (sex no caso).

        try {
            result = task.execute("https://zappycode.com/").get();//posso adicionar quantas strings quiser
        } catch(Exception e){
            e.printStackTrace(); //printa o erro
        }

        Log.i("Result", result);//printa o que foi retornado dentro do doInBackground

    }
}
