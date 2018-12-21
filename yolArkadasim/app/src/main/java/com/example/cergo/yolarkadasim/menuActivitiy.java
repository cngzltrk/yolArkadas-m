package com.example.cergo.yolarkadasim;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
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

public class menuActivitiy extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener {
    private ListView listView;
    TextView profil;
    String serverurl="http://192.168.1.9/yolArkadas/konuListele.php";
    String id[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_activitiy);
        listView=findViewById(R.id.lvMain);
        setupListView();
        Intent i=getIntent();
        id=i.getStringArrayExtra("id");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
    private void setupListView(){

        String[] title = getResources().getStringArray(R.array.Main);

        SimpleAdapter simpleAdapter = new SimpleAdapter(this, title);
        listView.setAdapter(simpleAdapter);

        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                        switch(position){
                            case 0: {
                                //String a=String.valueOf(parent.getItemAtPosition(position));
                                //Toast.makeText(getApplicationContext(),a,Toast.LENGTH_SHORT).show();

                                veriGetir("1");
                                break;
                            }
                            case 1: {
                                veriGetir("2");;
                                break;
                            }
                            case 2: {
                                veriGetir("3");

                                break;
                            }
                            case 3: {
                                veriGetir("4");
                                break;
                            }
                            case 4: {
                                veriGetir("5");
                                break;
                            }
                        }

                    }
                });
    }
    private void veriGetir(final String konuid)
    {
        StringRequest stringRequest=new StringRequest(Request.Method.POST, serverurl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //alınan response json formatındaki değeri parse ederiz

                            JSONArray jsonArray = new JSONArray(response);
                            String konu[]=new String[jsonArray.length()];
                            String konuID[]=new String[jsonArray.length()];
                            for (int i=0; i<jsonArray.length(); i++) {
                                JSONObject actor = jsonArray.getJSONObject(i);
                                String name = actor.getString("adi");
                                String kid = actor.getString("id");
                                konu[i]=name;
                                konuID[i]=kid;
                            }
                            Intent intent = new Intent(getApplicationContext(), KonuActivity.class);
                            intent.putExtra("konu",konu);
                            intent.putExtra("konuID",konuID);
                            startActivity(intent);
                            /*String asd=jsonObj.getString("kadi");
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
                            }*/

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
                deger.put("konuTipi",konuid);

                return deger;
            }
        };
        MySingleton.getInstance(getApplicationContext()).addToRequest(stringRequest);
    }
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id2 = item.getItemId();
        if (id2 == R.id.nav_account) {
            Intent in=new Intent(this, profil.class);
            in.putExtra("id" ,id);
            startActivity(in);
            // Handle the camera action
        }
        else if(id2==R.id.nav_settings)
        {
            Intent in=new Intent(this, profilDuzenle.class);
            in.putExtra("id" ,id);
            startActivity(in);
        }
        else if(id2==R.id.nav_add)
        {
            if(id[3].equals("2"))
                Toast.makeText(getApplicationContext(),"Yetkisiz giris",Toast.LENGTH_SHORT).show();
            else
            {
                Intent in=new Intent(this, konuEkle.class);
                in.putExtra("id" ,id);
                startActivity(in);
            }

        }
        else if(id2==R.id.nav_logout)
        {
            Intent in=new Intent(this, MainActivity.class);
            startActivity(in);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
class SimpleAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater layoutInflater;
    private TextView title, description;
    private String[] titleArray;
    private ImageView imageView;

    public SimpleAdapter(Context context, String[] title){
        mContext = context;
        titleArray = title;
        layoutInflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return titleArray.length;
    }

    @Override
    public Object getItem(int position) {
        return titleArray[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = layoutInflater.inflate(R.layout.layout, null);
        }

        title = (TextView)convertView.findViewById(R.id.tvMain);
        description = (TextView)convertView.findViewById(R.id.tvDescription);
        imageView = (ImageView)convertView.findViewById(R.id.ivMain);

        title.setText(titleArray[position]);

        if(titleArray[position].equalsIgnoreCase("Gramer")){
            imageView.setImageResource(R.drawable.timetable);
        }else if(titleArray[position].equalsIgnoreCase("Film")){
            imageView.setImageResource(R.drawable.play_i);
        }else if(titleArray[position].equalsIgnoreCase("Muzik")){
            imageView.setImageResource(R.drawable.play_i);
        }else if(titleArray[position].equalsIgnoreCase("Kitap")){
            imageView.setImageResource(R.drawable.book);
        }else{
            imageView.setImageResource(R.drawable.contact);
        }

        return convertView;

    }
}
