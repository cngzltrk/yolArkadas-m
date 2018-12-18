package com.example.cergo.yolarkadasim;

import android.content.ClipData;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class menuActivitiy extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener {
    TextView tv;
    TextView profil;
    String id[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_activitiy);
        tv=(TextView)findViewById(R.id.main);
        Intent i=getIntent();
        id=i.getStringArrayExtra("id");
        tv.setText(id[0]);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
