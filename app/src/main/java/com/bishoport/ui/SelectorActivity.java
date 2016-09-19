package com.bishoport.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class SelectorActivity extends AppCompatActivity implements CircularLines.OnSetNumberCircularLinesListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selector);


        CircularLines circularLines = (CircularLines) findViewById(R.id.circularLines);
        circularLines.setListener(this);

        circularLines.setiMaximumValue(40);
        circularLines.setInitialValue("20");
    }

    @Override
    public void onSetNumberCircularLines(String value) {

        Log.i("myApp","value in activity");
    }



}
