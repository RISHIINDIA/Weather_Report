package com.example.android.weather_report;

import android.content.Context;

import com.android.volley.RequestQueue;

public class VolleySingleton {
    private static VolleySingleton instance;
    private static Context context;
    private RequestQueue requestQueue;

    private VolleySingleton(Context context1){
        context = context1;
    }

    public static synchronized VolleySingleton getInstance(Context context1){
        if(instance == null)
            instance = new VolleySingleton(context1);
        return instance;
    }
    public RequestQueue getRequestQueue(){
        return requestQueue;
    }
}
