package com.example.android.weather_report;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

public class SearchResult extends AppCompatActivity {

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_result);

        //Accessing the extras provided
        Intent i = getIntent();
        Bundle extras = i.getExtras();
        String city = extras.getString("city");
        String temp = extras.getString("Temp");
        String humid = extras.getString("humidity");

        //Setting the texviews
        TextView tempView = (TextView)findViewById(R.id.temp);
        TextView humidView = (TextView)findViewById(R.id.humidity);
        tempView.setText(temp + (char) 0x00B0 + "C");
        humidView.setText(humid);

        TextView place = (TextView) findViewById(R.id.place);
        place.setText(city);
    }
}