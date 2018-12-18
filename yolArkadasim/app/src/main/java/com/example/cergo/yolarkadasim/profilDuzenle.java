package com.example.cergo.yolarkadasim;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class profilDuzenle extends AppCompatActivity {
    String id[];
    TextView username,pass,email;
    String serverurl="http://192.168.1.9/yolArkadas/guncelle.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_duzenle);
        id=getIntent().getStringArrayExtra("id");
        username=(EditText)findViewById(R.id.usernamee);
        pass=(EditText)findViewById(R.id.passs);
        email=(EditText)findViewById(R.id.emaill);


    }
    public void Save(View v)
    {
        StringRequest stringRequest=new StringRequest(Request.Method.POST, serverurl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(),"Kayıt  guncellendi",Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"hatalı islem",Toast.LENGTH_SHORT).show();
            }
        }){
            protected Map<String,String> getParams() throws AuthFailureError {
                Map<String,String> deger=new HashMap<>();
                deger.put("id",id[0]);
                deger.put("kullaniciAdi",username.getText().toString());
                deger.put("sifre",pass.getText().toString());
                deger.put("email",email.getText().toString());
                return deger;
            }
        };
        id[1]=username.getText().toString();
        id[2]=email.getText().toString();
        MySingleton.getInstance(getApplicationContext()).addToRequest(stringRequest);
        Intent i=new Intent(this,menuActivitiy.class);
        i.putExtra("id",id);
        startActivity(i);

    }
}
