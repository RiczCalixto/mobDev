package com.example.mecia.ric;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class AppNews extends AppCompatActivity {
    ListView listView;
    ArrayAdapter adapter;
    ArrayList<String> titlesArray = new ArrayList<>();
    ArrayList<String> contentArray = new ArrayList<>();
    SQLiteDatabase articlesDataBase;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_news);

        articlesDataBase = this.openOrCreateDatabase("Articles", MODE_PRIVATE, null);
        articlesDataBase.execSQL("CREATE TABLE IF NOT EXISTS articles (id INTEGER PRIMARY KEY, articleId, INTEGER, title VARCHAR, contentHtml VARCHAR)");

        progressBar = (ProgressBar) findViewById(R.id.progressBar2);

        DownloadTask task = new DownloadTask();
        try {
            task.execute("https://hacker-news.firebaseio.com/v0/topstories.json?print=pretty");

        } catch (Exception e){

        }


        listView = findViewById(R.id.listViewId);
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, titlesArray);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ArticleActivity.class);
                intent.putExtra("content", contentArray.get(position));
                startActivity(intent);

            }
        });

        updateListView();

    }



    public void updateListView() {
        Cursor c = articlesDataBase.rawQuery("SELECT * FROM articles", null);

        int contentIndex = c.getColumnIndex("contentHtml");
        int titleIndex = c.getColumnIndex("title");

        if (c.moveToFirst()){
            titlesArray.clear();
            contentArray.clear();

            do {

                titlesArray.add(c.getString(titleIndex));
                contentArray.add(c.getString(contentIndex));

            } while (c.moveToNext() );

            adapter.notifyDataSetChanged();

        }
    }

    public class DownloadTask extends AsyncTask<String, Void, String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);

        }

        @Override
        protected String doInBackground(String... urls) {

            String result = "";
            URL url;
            HttpURLConnection urlConnection = null;

            try {

                url = new URL(urls[0]);

                urlConnection = (HttpURLConnection) url.openConnection();

                InputStream inputStream = urlConnection.getInputStream();

                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

                int data = inputStreamReader.read();

                while (data != -1) {
                    char current = (char) data;
                    result += current;
                    data = inputStreamReader.read();
                }

                JSONArray jsonArray = new JSONArray(result);

                int numberOfItems = 1;

                if (jsonArray.length() < 1) {
                    numberOfItems = jsonArray.length();
                }

                articlesDataBase.execSQL("DELETE FROM articles");



                for (int i=0;i < numberOfItems; i++) {
                    String articleId = jsonArray.getString(i);
                    url = new URL("https://hacker-news.firebaseio.com/v0/item/" + articleId + ".json?print=pretty");
                    urlConnection = (HttpURLConnection) url.openConnection();

                    inputStream = urlConnection.getInputStream();
                    inputStreamReader = new InputStreamReader(inputStream);

                    data = inputStreamReader.read();

                    String articleInfo = "";

                    while (data != -1) {
                        char current = (char) data;
                        articleInfo += current;
                        data = inputStreamReader.read();
                    }

                    JSONObject jsonObject = new JSONObject(articleInfo);

                    if (!jsonObject.isNull("title") && !jsonObject.isNull("url")) {
                        String articleTitle = jsonObject.getString("title");
                        String articleUrl = jsonObject.getString("url");

                        url = new URL(articleUrl);
                        urlConnection = (HttpURLConnection) url.openConnection();
                        inputStream = urlConnection.getInputStream();
                        inputStreamReader = new InputStreamReader(inputStream);
                        data = inputStreamReader.read();
                        String articleContent = "";
                        while (data != -1) {
                            char current = (char) data;
                            articleContent += current;
                            data = inputStreamReader.read();
                        }

                        Log.i("HTML", articleContent);
                        String sql = "INSERT INTO articles (articleId, title, contentHtml) VALUES (?, ?, ?)";
                        SQLiteStatement statement = articlesDataBase.compileStatement(sql);
                        statement.bindString(1, articleId);
                        statement.bindString(2, articleTitle);
                        statement.bindString(3, articleContent);

                        statement.execute();

                    }
                }

                Log.i("URL Content", result);
                return result;

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            updateListView();
            progressBar.setVisibility(View.GONE);
        }
    }
}
