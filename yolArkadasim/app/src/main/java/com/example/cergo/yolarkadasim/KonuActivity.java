package com.example.cergo.yolarkadasim;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class KonuActivity extends AppCompatActivity {
    private ListView listView;
    String serverurl="http://192.168.1.9/yolArkadas/konugetir.php";
    String[] konu,konuID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konu);

        Intent i=getIntent();
        konu=i.getStringArrayExtra("konu");
        konuID=i.getStringArrayExtra("konuID");

        listView=(ListView)findViewById(R.id.lvKonu);
        setupListView();
    }

    private void setupListView(){
        String[] week = konu;

        KonuAdapter adapter = new KonuAdapter(this,  week);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                konuGetir(position);
            }
        });

    }
    private void konuGetir(final int position)
    {
        StringRequest stringRequest=new StringRequest(Request.Method.POST, serverurl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //alınan response json formatındaki değeri parse ederiz

                            JSONObject jsonObj = new JSONObject(response);
                            String konugetir[]={jsonObj.getString("id"),jsonObj.getString("adi"),jsonObj.getString("icerik")};
                            Intent intent = new Intent(getApplicationContext(), KonuDetay.class);
                            intent.putExtra("konugetir",konugetir);
                            startActivity(intent);
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
                deger.put("konuID",konuID[position]);

                return deger;
            }
        };
        MySingleton.getInstance(getApplicationContext()).addToRequest(stringRequest);
    }
}


class KonuAdapter extends ArrayAdapter {

    private LayoutInflater layoutInflater;
    private String[] week=new String[]{} ;

    public KonuAdapter(Context context, String[] objects) {
        super(context, R.layout.konulayout, objects);
        this.week = objects;
        layoutInflater = LayoutInflater.from(getContext());
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.konulayout,parent, false);
            holder.ivLogo = (LetterImageView)convertView.findViewById(R.id.ivLetter);
            holder.tvWeek = (TextView)convertView.findViewById(R.id.tvMain);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }

        holder.ivLogo.setOval(true);
        holder.ivLogo.setLetter(week[position].charAt(0));
        holder.tvWeek.setText(week[position]);

        return convertView;
    }

    class ViewHolder{
        private LetterImageView ivLogo;
        private TextView tvWeek;
    }

}
