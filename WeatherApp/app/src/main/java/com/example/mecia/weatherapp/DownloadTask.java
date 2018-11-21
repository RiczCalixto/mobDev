package com.example.mecia.weatherapp;

import android.net.UrlQuerySanitizer;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.BreakIterator;

//public class DownloadTask extends AsyncTask<String, Void, String> {
//
//    @Override
//    protected String doInBackground(String... urls) {
//        String result = "";
//        URL url;
//        HttpURLConnection urlConnection = null;
//
//
//        try{
//            url = new URL(urls[0]);
//            urlConnection = (HttpURLConnection) url.openConnection();
//
//            InputStream in = urlConnection.getInputStream();
//
//            InputStreamReader reader = new InputStreamReader(in);
//
//            int data = reader.read();
//
//            while (data != -1){
//
//                char current = (char) data;
//
//                result+= current;
//
//                data = reader.read();
//
//            }
//
//            return result;
//
//        }catch (Exception e){
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    @Override
//    protected void onPostExecute(String s) {//essa string eh o resultado do que foi baixado
//        super.onPostExecute(s);
//
//        try {
//            JSONObject jsonObject = new JSONObject(s);
//
//            String weatherInfo = jsonObject.getString("weather");//pega a info que ta dentro do JSON. nesse caso a info se chamava weather
//
//            Log.i("Weather info", weatherInfo);
//
//            JSONArray array = new JSONArray(weatherInfo);
//
//            String message = "";
//
//            for(int i = 0; i < array.length(); i++){
//                JSONObject jsonPart = array.getJSONObject(i);
//
//                String main = jsonPart.getString("main");
//                String description = jsonPart.getString("description");
//
//                if(!main.equals("") && !description.equals("")){
//                    message+= main + ": " + description;
//                }
//            }
//            if (!message.equals("")){
//                respostaR.setText(message);
//            }
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//}
