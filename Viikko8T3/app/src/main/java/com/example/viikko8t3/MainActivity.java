package com.example.viikko8t3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Scanner;

import static java.lang.Math.round;

public class MainActivity extends AppCompatActivity {

    public TextView textViewNotif = null;
    public TextView textViewSeekbar;
    public SeekBar seekbar;

    public Integer sum=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewNotif = (TextView) findViewById(R.id.textViewNotif);
        textViewSeekbar = (TextView) findViewById(R.id.textViewSeekbar);

        seekbar = (SeekBar) findViewById(R.id.seekBar);
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                sum = (progress/20);
                textViewSeekbar.setText(""+sum+" €");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }


    BottleDispenser automaatti = BottleDispenser.getInstance();

    public void addMoney(View V){
        automaatti.addMoney(textViewNotif,sum);
        seekbar.setProgress(0);
    }

    public void returnMoney(View V){
        automaatti.returnMoney(textViewNotif);
    }

    public void buyBottle(View V){
        automaatti.buyBottle(textViewNotif,1);
    }


}
