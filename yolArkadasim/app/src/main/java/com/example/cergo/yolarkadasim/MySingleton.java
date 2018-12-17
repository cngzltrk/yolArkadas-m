package com.example.cergo.yolarkadasim;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class MySingleton {
    private Context context;
    private RequestQueue requestQueue;

    MySingleton(Context ctx)
    {
        context=ctx;
        requestQueue=Volley.newRequestQueue(context);
    }
    public static synchronized MySingleton getInstance(Context context)
    {
        return new MySingleton(context);
    }
    public void addToRequest (Request rqs)
    {
        requestQueue.add(rqs);
    }
}
