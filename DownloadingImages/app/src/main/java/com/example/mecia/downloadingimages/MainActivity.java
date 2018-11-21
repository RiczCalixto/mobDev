package com.example.mecia.downloadingimages;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;

    public void downloadImage(View view){
        ImageDownloader task = new ImageDownloader();//fazer o download de imagens
        Bitmap myImage;
        try {
            myImage = task.execute("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRcPAI0Q8HXDwL-gprkZes3z5NEl3_bFcESyMTff7bD1QNSb_acAQ").get();//retorna o bitmap

            imageView.setImageBitmap(myImage);
        } catch (Exception e){
            e.printStackTrace();
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);


    }

    public class ImageDownloader extends AsyncTask<String, Void, Bitmap>{//o Bitmap é usado pra retornar imagens

        @Override
        protected Bitmap doInBackground(String... urls) {//renomeei strings por urls
            try {
                URL url = new URL(urls[0]);

                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                connection.connect();

                InputStream in = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(in);//pega a imagem

                return myBitmap;

            } catch (Exception e){
                e.printStackTrace();
                return null;

            }
        }
    }
}
