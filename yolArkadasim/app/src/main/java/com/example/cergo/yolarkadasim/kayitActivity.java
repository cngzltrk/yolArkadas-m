package com.example.cergo.yolarkadasim;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class kayitActivity extends AppCompatActivity {
    EditText username,pass,email;
    String serverurl="http://192.168.1.9/yolArkadas/kayitEkle.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayit);
        username=(EditText)findViewById(R.id.username);
        pass=(EditText)findViewById(R.id.pass);
        email=(EditText)findViewById(R.id.email);


    }
    public void kayitEkle(View v)
    {
        StringRequest stringRequest=new StringRequest(Request.Method.POST, serverurl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(),"Kayıt  gerçekleşti",Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"hatalı islem",Toast.LENGTH_SHORT).show();
            }
        }){
            protected Map<String,String> getParams() throws AuthFailureError {
                Map<String,String> deger=new HashMap<>();
                deger.put("kullaniciAdi",username.getText().toString());
                deger.put("sifre",pass.getText().toString());
                deger.put("email",email.getText().toString());
                return deger;
            }
        };
        MySingleton.getInstance(getApplicationContext()).addToRequest(stringRequest);
        startActivity(new Intent(this, MainActivity.class));
    }
}
