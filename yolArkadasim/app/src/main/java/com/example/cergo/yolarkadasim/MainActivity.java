package com.example.cergo.yolarkadasim;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText username,pass;
    String serverurl="http://192.168.1.9/yolArkadas/login.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username=(EditText)findViewById(R.id.username);
        pass=(EditText)findViewById(R.id.pass);
    }
    public void kayitEkle(View v)
    {
        startActivity(new Intent(MainActivity.this, kayitActivity.class));
    }
    public void login(View v)
    {
        StringRequest stringRequest=new StringRequest(Request.Method.POST, serverurl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //alınan response json formatındaki değeri parse ederiz
                            JSONObject jsonObj = new JSONObject(response);
                            String asd=jsonObj.getString("kadi");
                            if(asd.equals("null"))
                            {
                                Toast.makeText(getApplicationContext(),"Kullanıcı adı veya sifre hatalı",Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                String id[]={jsonObj.getString("id"),jsonObj.getString("kadi"),jsonObj.getString("email"),jsonObj.getString("uyeTuru")};
                                Intent i=new Intent(MainActivity.this, menuActivitiy.class);
                                i.putExtra("id" ,id);
                                startActivity(i);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
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
                return deger;
            }
        };
        MySingleton.getInstance(getApplicationContext()).addToRequest(stringRequest);
    }
}
