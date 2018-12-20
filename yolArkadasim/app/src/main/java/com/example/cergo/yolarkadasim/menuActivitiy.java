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

public class menuActivitiy extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener {
    private ListView listView;
    TextView profil;
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

        /*listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        switch(position){
                            case 0: {
                                //String a=String.valueOf(parent.getItemAtPosition(position));
                                //Toast.makeText(MainActivity.this,a,Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(MainActivity.this, gramerActivity.class);
                                startActivity(intent);
                                break;
                            }
                            case 1: {
                                break;
                            }
                            case 2: {
                                break;
                            }
                            case 3: {
                                break;
                            }
                        }
                    }
                });*/
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
