package com.example.viikko9t3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class MainActivity extends AppCompatActivity{
    Finnkino finnkino = new Finnkino();
    ArrayList<String> theatreNameList = null;
    ArrayList<Theatre> theatreList = null;
    Context context = null;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        readTheatreXML();
    }


    public void readTheatreXML(){
        Spinner spinnerTheatreName = (Spinner) findViewById(R.id.spinnerTheatre);
        theatreNameList = finnkino.readTheatreXML();
        ArrayAdapter<String> adapterType = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, theatreNameList);
        spinnerTheatreName.setAdapter(adapterType);
    }

    public void readScheduleXML(View V){
        //Toimii
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String currentDate = dateFormat.format(Calendar.getInstance().getTime());
        //

        Spinner spinnerTheatreName = (Spinner) findViewById(R.id.spinnerTheatre);
        Integer index = spinnerTheatreName.getSelectedItemPosition();

        ArrayList<String> movieInfoList = finnkino.readScheduleXML("22.04.2020", index);

        ListView listView;
        listView= (ListView)findViewById(R.id.movieListView);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, movieInfoList);
        listView.setAdapter(arrayAdapter);
    }


}
