package com.example.cergo.yolarkadasim;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class profil extends AppCompatActivity {
    TextView profilAdi,profilemail,profilturu;
    String id[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        profilAdi=(TextView) findViewById(R.id.profilAdi);
        profilemail=(TextView) findViewById(R.id.profilemail);
        profilturu=(TextView) findViewById(R.id.profilTuru);
        id=getIntent().getStringArrayExtra("id");
        profilAdi.setText(id[1]);
        profilemail.setText(id[2]);
        if(id[3].equals("2"))
        {
            profilturu.setText("Normal Kullanıcı");
        }
        else
        {
            profilturu.setText("Admin");
        }
    }
}
