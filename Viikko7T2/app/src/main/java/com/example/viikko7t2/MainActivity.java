package com.example.viikko7t2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView ikkuna;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Tässä on itsetehtyä koodia
        ikkuna = (TextView) findViewById(R.id.textView);


    }

    public void testi(View v){
        ikkuna.setText("Hello world!");
    }

}
