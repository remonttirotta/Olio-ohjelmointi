package com.example.viikko8t2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    public TextView textViewNotif = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewNotif = (TextView) findViewById(R.id.textViewNotif);
    }


    BottleDispenser automaatti = BottleDispenser.getInstance();

    public void addMoney(View V){
        automaatti.addMoney(textViewNotif);
    }

    public void returnMoney(View V){
        automaatti.returnMoney(textViewNotif);
    }

    public void buyBottle(View V){
        automaatti.buyBottle(textViewNotif,1);
    }


}
