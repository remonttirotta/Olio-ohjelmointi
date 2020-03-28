package com.example.viikko9t2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class MainActivity extends AppCompatActivity {
    Finnkino finnkino = new Finnkino();
    ArrayList<String> nameList = null;
    Context context = null;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


    }

    public void readXML(View V){
        Spinner spinnerTheatreName = (Spinner) findViewById(R.id.spinnerTheatre);
        nameList = finnkino.readXML();
        ArrayAdapter<String> adapterType = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, nameList);
        spinnerTheatreName.setAdapter(adapterType);
    }

}
