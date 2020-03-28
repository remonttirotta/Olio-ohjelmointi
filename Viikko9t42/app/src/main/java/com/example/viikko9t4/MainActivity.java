package com.example.viikko9t4;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
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
        EditText dateEdit = (EditText) findViewById(R.id.editTextDate);
        EditText startEdit = (EditText) findViewById(R.id.editTextStartTime);
        EditText endEdit = (EditText) findViewById(R.id.editTextEndTime);


        SimpleDateFormat dateFormatUser = new SimpleDateFormat("dd.MM.yyyy");
        String currentDate = dateFormatUser.format(Calendar.getInstance().getTime());
        //

        //Haetaan käyttäjän antamat aikaa koskevat rajoitteet
        String dateString = dateEdit.getText().toString();
        String startFromString = startEdit.getText().toString();
        String startToString = endEdit.getText().toString();

        if (dateString.equals("")){dateString=currentDate;}
        if (startFromString.equals("")){startFromString="00:01";}
        if (startToString.equals("")){startToString="23:59";}

        //Valittu teatteri
        Spinner spinnerTheatreName = (Spinner) findViewById(R.id.spinnerTheatre);
        Integer index = spinnerTheatreName.getSelectedItemPosition();

        //Aliohjelmakutsu
        ArrayList<String> movieInfoList = finnkino.readScheduleXML(dateString, startFromString, startToString, index);

        ListView listView;
        listView= (ListView)findViewById(R.id.movieListView);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, movieInfoList);
        listView.setAdapter(arrayAdapter);
    }


}