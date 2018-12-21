package com.example.cergo.yolarkadasim;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class KonuDetay extends AppCompatActivity {
    TextView tvBaslik,tvIcerik;
    String konugetir[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konu_detay);
        tvBaslik=(TextView)findViewById(R.id.tvBaslik);
        tvIcerik=(TextView)findViewById(R.id.tvIcerik);
        Intent i=getIntent();
        konugetir=i.getStringArrayExtra("konugetir");

        tvBaslik.setText(konugetir[1]);
        tvIcerik.setText(konugetir[2]);
    }
}
