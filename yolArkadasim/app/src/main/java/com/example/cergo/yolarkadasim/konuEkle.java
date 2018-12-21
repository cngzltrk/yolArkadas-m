package com.example.cergo.yolarkadasim;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class konuEkle extends AppCompatActivity {
    String[] id;
    EditText etBaslik,etIcerik;
    Spinner sp;
    String serverurl="http://192.168.1.9/yolArkadas/konuEkle.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konu_ekle);
        etBaslik=(EditText)findViewById(R.id.etBaslik);
        etIcerik=(EditText)findViewById(R.id.etIcerik);
        sp=(Spinner)findViewById(R.id.konuTur);

        Intent i=getIntent();
        id=i.getStringArrayExtra("id");
    }

    public void konuEkle(View v)
    {
        StringRequest stringRequest=new StringRequest(Request.Method.POST, serverurl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(),"Konu eklendi",Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"hatalı islem",Toast.LENGTH_SHORT).show();
            }
        }){
            protected Map<String,String> getParams() throws AuthFailureError {
                Map<String,String> deger=new HashMap<>();
                deger.put("konuBaslik",etBaslik.getText().toString());
                deger.put("konuIcerik",etIcerik.getText().toString());
                deger.put("uyeID",id[0]);
                int s=sp.getSelectedItemPosition();
                s++;
                deger.put("konuTipi",s+"");
                return deger;
            }
        };
        MySingleton.getInstance(getApplicationContext()).addToRequest(stringRequest);
        Intent i=new Intent(this,menuActivitiy.class);
        i.putExtra("id",id);
        startActivity(i);
    }
}
