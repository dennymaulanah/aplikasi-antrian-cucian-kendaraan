package com.den.mageracuke;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.TextView;

import com.den.mageracuke.fragment.About;
import com.den.mageracuke.fragment.Antrian;
import com.den.mageracuke.fragment.Home;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    String id, token, pemilik, status, no_kendaraan, kendaraan, tipe_kendaran, waktu_keluar;
    SharedPreferences sharedpreferences;

    public static final String TAG_ID = "id";
    public static final String TAG_TOKEN = "token";
    public static final String TAG_PEMILIK = "pemilik";
    public final static String TAG_STATUS = "status";
    public final static String TAG_NO = "no_kendaraan";
    public final static String TAG_KENDARAAN = "kendaraan";
    public final static String TAG_TIPE = "tipe_kendaraan";
    public final static String TAG_WAKTU = "waktu_keluar";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedpreferences = getSharedPreferences(Login.my_shared_preferences, Context.MODE_PRIVATE);

        id = getIntent().getStringExtra(TAG_ID);
        token = getIntent().getStringExtra(TAG_TOKEN);
        pemilik = getIntent().getStringExtra(TAG_PEMILIK);
        status = getIntent().getStringExtra(TAG_STATUS);
        no_kendaraan = getIntent().getStringExtra(TAG_NO);
        kendaraan = getIntent().getStringExtra(TAG_KENDARAAN);
        tipe_kendaran = getIntent().getStringExtra(TAG_TIPE);
        waktu_keluar = getIntent().getStringExtra(TAG_WAKTU);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        View headerView = navigationView.getHeaderView(0);
        TextView nav_token = headerView.findViewById(R.id.nav_token);
        TextView nav_pemilik = headerView.findViewById(R.id.nav_pemilik);
        nav_token.setText(token);
        nav_pemilik.setText(pemilik);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, new Home());
        ft.commit();
        navigationView.setCheckedItem(R.id.nav_home);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
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
        displaySelectedScreen(item.getItemId());
        return true;
    }
    private void displaySelectedScreen(int itemId) {
        // Handle navigation view item clicks here.
        Fragment fragment = null;

        switch (itemId) {
            case R.id.nav_home:
                fragment = new Home();
                break;
            case R.id.nav_antrian:
                fragment = new Antrian();
                break;
            case R.id.nav_about:
                fragment = new About();
                break;
            case R.id.nav_logout:
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putBoolean(Login.session_status, false);
                editor.putString(TAG_ID, null);
                editor.putString(TAG_TOKEN, null);
                editor.putString(TAG_PEMILIK, null);
                editor.commit();

                Intent intent = new Intent(MainActivity.this, Login.class);
                finish();
                startActivity(intent);
                break;
        }
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

    }

}
