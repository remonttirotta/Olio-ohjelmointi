package com.example.viikko7t3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    String userInput = "";

    EditText editText = null;
    TextView textView= null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void printText(View V){
        editText = (EditText) findViewById(R.id.editText);
        userInput = editText.getText().toString();

        textView = (TextView) findViewById(R.id.textView);
        textView.setText(userInput);
    }


}
