package br.edu.infnet.gorjeta;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    RadioGroup radioGroup;
    Button computeButton;
    EditText billEditText;
    TextView tipTextView;

    public static final String TIP_KEY ="tip";
    public static final String BILL_KEY = "bill";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        radioGroup = findViewById(R.id.radio_group);
        computeButton = findViewById(R.id.compute_button);
        billEditText = findViewById(R.id.bill_edittext);
        tipTextView = findViewById(R.id.tip_label);


//        computeButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });


    }

    public void computeTip(View view){
        // calcular gorjeta
        //pegar o valor da conta
        String content = billEditText.getText().toString();
        float billValue = Float.parseFloat(content);
        //radiobutton escolhido
        int checkedRadioButtonId = radioGroup.getCheckedRadioButtonId();
        float percent = 0.0f;
        switch (checkedRadioButtonId){
            case R.id.rb10:
                percent = 0.1f;
                break;
            case R.id.rb12:
                percent = 0.12f;
                break;
            case R.id.rb15:
                percent = 0.15f;
                break;
        }
        //fazer a conta
        float tipValue = billValue*percent;

//        //exibir o resultado
//        tipTextView.setText(String.format("R$ %.2f", tip));

        //abrir uma nova tela (Activity)
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra(BILL_KEY, billValue);
        intent.putExtra(TIP_KEY, tipValue);
        startActivity(intent);

    }

}
