package com.example.viikko8t5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Math.round;

public class MainActivity extends AppCompatActivity{

    public TextView textViewNotif = null;
    public TextView textViewSeekbar;
    public SeekBar seekbar;

    public Integer sum=0;

    Context context = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;
        System.out.println("Tiedostosijainti "+context.getFilesDir());

        //Tekstiboksit
        textViewNotif = (TextView) findViewById(R.id.textViewNotif);
        textViewSeekbar = (TextView) findViewById(R.id.textViewSeekbar);
        //
        Spinner spinnerType = (Spinner)findViewById(R.id.spinnerType);
        Spinner spinnerSize = (Spinner)findViewById(R.id.spinnerSize);
        ////

        //Spinneriin tulevat listat
        ArrayList<String> typeList = new ArrayList<String>();
        typeList.add("Pepsi Max");
        typeList.add("Coca-Cola Zero");
        typeList.add("Fanta Zero");

        ArrayList<Double> sizeList = new ArrayList<Double>();
        sizeList.add(0.5);
        sizeList.add(1.5);
        //


        ArrayAdapter<String> adapterType = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, typeList);
        spinnerType.setAdapter(adapterType);

        ArrayAdapter<Double> adapterSize = new ArrayAdapter<Double>(
                this, android.R.layout.simple_spinner_item, sizeList);
        spinnerSize.setAdapter(adapterSize);


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
        Spinner spinnerType = (Spinner)findViewById(R.id.spinnerType);
        Spinner spinnerSize = (Spinner)findViewById(R.id.spinnerSize);

        String chosenType = spinnerType.getSelectedItem().toString();
        String chosenSize = spinnerSize.getSelectedItem().toString();
        automaatti.buyBottle(textViewNotif, chosenType, Double.parseDouble(chosenSize));

    }
    public void printReceipt(View V){
        Bottle bottle = automaatti.getLastTransaction();
        try {
            if(bottle==null){
                textViewNotif.setText("You must purchase something first!");
                return;
            }
            OutputStreamWriter osw = new OutputStreamWriter(context.openFileOutput("Receipt", MODE_PRIVATE));
            osw.write("Receipt from Limumaatti \n" +
                    "Item: "+bottle.name+" "+ bottle.size+", price: "+bottle.price+" € (incl. Vat 24%)\n" +
                    "Thank you come again!");
            osw.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            Log.e("IOexception", "Virhe syötteessä");
        }

        textViewNotif.setText("Receipt printed to file!");
    }

}