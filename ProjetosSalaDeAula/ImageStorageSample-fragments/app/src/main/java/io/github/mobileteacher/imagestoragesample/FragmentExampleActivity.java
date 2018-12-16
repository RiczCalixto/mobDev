package io.github.mobileteacher.imagestoragesample;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class FragmentExampleActivity extends AppCompatActivity {

    TextFragment textFragment;
    ImageFragment imageFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_example);

        textFragment = TextFragment.newInstance("Mensagem secreta!");
        imageFragment = new ImageFragment();
    }

    public void putTextFragment(View v){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();

        transaction.replace(R.id.container, textFragment);

        transaction.commit();
    }

    public void putImageFragment(View v){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();

        transaction.replace(R.id.container, imageFragment);

        transaction.commit();
    }
}
