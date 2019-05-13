package com.example.android.weather_report;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private RequestQueue mRequestQueue;
    private JsonObjectRequest mRequest;

    //The url of the API and the appID
    private String url = "http://api.openweathermap.org/data/2.5/weather?q=",appID ="&APPID=1bb89f6a20c24592314448e64d0da4ab",URL;

    EditText city;
    private String city_name;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        city = (EditText)findViewById(R.id.editText);
        ImageView search = (ImageView) findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                city_name = city.getText().toString();
                URL = url+city_name+appID;
                sendAndRequestResponse();
            }
        });

    }
    private void sendAndRequestResponse() {

        mRequestQueue = Volley.newRequestQueue(this);

        Log.i("MainActivity",URL);

        mRequest = new JsonObjectRequest( Request.Method.GET, URL,null,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {

                    //The JSON Object containing the information on temperature and humidity
                    JSONObject obj = response.getJSONObject("main");

                    //Formating the temperature into double having one decimal place
                    String temp = String.format("%.1f",obj.getDouble("temp") - 273.15);

                    //Humidity
                    String humidity = obj.getString("humidity");

                    //Calling the activity SearchResult
                    Intent intent = new Intent(MainActivity.this,SearchResult.class);

                    //Extras
                    intent.putExtra("Temp",temp);
                    intent.putExtra("city",city_name);
                    intent.putExtra("humidity",humidity);

                    startActivity(intent);

                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(),"Sorry, unable to find the location.", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"Sorry, unable to find the location.", Toast.LENGTH_LONG).show();
                Log.i("MainActivity","Error :" + error.toString());
            }
        });

        mRequestQueue.add(mRequest);
    }
}