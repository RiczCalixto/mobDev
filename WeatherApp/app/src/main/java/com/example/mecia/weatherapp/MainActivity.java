package com.example.mecia.weatherapp;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    Button button;
    public TextView respostaR;


    public void checkWeather(View view){
        try {
            //Desaparecer o teclado
            InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            mgr.hideSoftInputFromWindow(editText.getWindowToken(), 0); //desaparecer o teclado

            //String nomeCidade = editText.getText().toString(); // substitui pela variavel do encodedCityName
            //Colocar espaço automatico caso de erro em transformar os espaços para %20 no browser
            String encodedCityName = URLEncoder.encode(editText.getText().toString(), "UTF-8");

            DownloadTask task = new DownloadTask();

            task.execute("https://openweathermap.org/data/2.5/weather?q="+ encodedCityName +"&appid=b6907d289e10d714a6e88b30761fae22").get();

            } catch (Exception e) {
            e.printStackTrace();
            respostaR.setText("Invalid city name");
            }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        editText = findViewById(R.id.editText);
        respostaR = findViewById(R.id.respostaR);
    }

    public class DownloadTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            String result = "";
            URL url;
            HttpURLConnection urlConnection = null;


            try{
                url = new URL(urls[0]);
                urlConnection = (HttpURLConnection) url.openConnection();

                InputStream in = urlConnection.getInputStream();

                InputStreamReader reader = new InputStreamReader(in);

                int data = reader.read();

                while (data != -1){

                    char current = (char) data;

                    result+= current;

                    data = reader.read();

                }

                return result;

            }catch (Exception e){
                e.printStackTrace();

                Toast.makeText(MainActivity.this, "Could not find weather", Toast.LENGTH_SHORT).show();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String s) {//essa string eh o resultado do que foi baixado
            super.onPostExecute(s);

            try {
                JSONObject jsonObject = new JSONObject(s);

                String weatherInfo = jsonObject.getString("weather");//pega a info que ta dentro do JSON. nesse caso a info se chamava weather

                Log.i("Weather info", weatherInfo);

                JSONArray array = new JSONArray(weatherInfo);

                String message = "";

                for(int i = 0; i < array.length(); i++){
                    JSONObject jsonPart = array.getJSONObject(i);

                    String main = jsonPart.getString("main");
                    String description = jsonPart.getString("description");

                    if(!main.equals("") && !description.equals("")){
                        message+=  main + ": " + description + "\r\n";
                    }
                }
                if (!message.equals("")){
                    respostaR.setText(message);
                } else {}


            }catch (Exception e){
                e.printStackTrace();
                Toast.makeText(MainActivity.this, "Could not find weather", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
