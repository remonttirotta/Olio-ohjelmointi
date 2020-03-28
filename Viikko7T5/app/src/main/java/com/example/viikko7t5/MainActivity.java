package com.example.viikko7t5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    EditText editTextFilename = null;
    EditText editTextInput = null;
    TextView textViewFile= null;

    String fileName = "";
    String textInput ="";

    Context context = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;
        System.out.println("Tiedostosijainti "+context.getFilesDir());
    }

    public void readFile(View v){
         try{
             editTextFilename = (EditText) findViewById(R.id.editTextFilename);
             fileName = editTextFilename.getText().toString();

             InputStream ins = context.openFileInput(fileName);
             BufferedReader br = new BufferedReader(new InputStreamReader(ins));

             String line = "";
             String all = "";
             while( (line = br.readLine())!= null){
                 all = all + "\n"+line;
             }
             editTextInput = (EditText) findViewById(R.id.editTextInput);
             editTextInput.setText(all);

            ins.close();
         }catch(IOException e){
             Log.e("IOexception", "Virhe syötteessä");
        } finally{
             System.out.println("Tiedosto luettu");
        }
    }

    public void writeFile(View v){
        try{
            editTextFilename = (EditText) findViewById(R.id.editTextFilename);
            fileName = editTextFilename.getText().toString();

            OutputStreamWriter osw = new OutputStreamWriter(context.openFileOutput(fileName, MODE_PRIVATE));

            editTextInput = (EditText) findViewById(R.id.editTextInput);
            textInput = editTextInput.getText().toString();
            osw.write(textInput);
            osw.close();



        }catch(IOException e){

        }finally{
            System.out.println("Tiedosto kirjoitettu");
            textViewFile = (TextView) findViewById(R.id.textViewFile);
            textViewFile.setText("Text written to "+fileName);
        }
    }



}
