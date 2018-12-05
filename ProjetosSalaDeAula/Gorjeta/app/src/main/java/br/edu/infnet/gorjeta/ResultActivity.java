package br.edu.infnet.gorjeta;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);


        Intent intent = getIntent();
        float billValue = intent.getFloatExtra(MainActivity.BILL_KEY, 0.0f);
        float tipValue = intent.getFloatExtra(MainActivity.TIP_KEY, 0.0f);


        float total = billValue + tipValue;

        TextView billTextView = findViewById(R.id.bill_textview);
        billTextView.setText(String.format("R$ %.2f", billValue));

        TextView tipTextView = findViewById(R.id.tip_textview);
        tipTextView.setText(String.format("R$ %.2f", tipValue));

        TextView totalTextView = findViewById(R.id.total_textview);
        totalTextView.setText(String.format("R$ %.2f", total));

    }
}
