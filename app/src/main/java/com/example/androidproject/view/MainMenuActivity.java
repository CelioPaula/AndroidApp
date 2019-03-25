package com.example.androidproject.view;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidproject.R;
import com.example.androidproject.controller.PreferencesController;
import com.example.androidproject.model.Movies;

import java.util.ArrayList;

public class MainMenuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

   private DrawerLayout myDrawer;
    private ActionBarDrawerToggle myToggle;

    private PreferencesController preferencesController;
    private ArrayList<Movies> prefMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        setTitle("Last researches");

        myDrawer = (DrawerLayout)findViewById(R.id.myDrawer);
        myToggle = new ActionBarDrawerToggle(this, myDrawer, R.string.open, R.string.close);

        myDrawer.addDrawerListener(myToggle);
        myToggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        displayPushNotification();
        showPreferences();

        final TextView textInfo = findViewById(R.id.textInfo);
        if(prefMovies.isEmpty()) textInfo.setVisibility(View.VISIBLE);
        else textInfo.setVisibility(View.INVISIBLE);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(myToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.searchItem:
                Intent intent = new Intent(MainMenuActivity.this, SearchActivity.class);
                startActivity(intent);
                break;
            case R.id.emptyItem:
                Toast.makeText(MainMenuActivity.this, "CACHE MEMORY CLEARED", Toast.LENGTH_LONG).show();
                preferencesController.emptyPreferences();
                prefMovies.clear();
                showList(prefMovies);
                break;
            case R.id.infoItem:
                Intent intent2 = new Intent(MainMenuActivity.this, InfoActivity.class);
                startActivity(intent2);
                break;
        }

        return true;
    }

    @Override
    public void onResume(){
        super.onResume();
        showPreferences();
    }

    public void showPreferences(){
        preferencesController = new PreferencesController(this);
        prefMovies = preferencesController.getSharedPreferences();
        showList(prefMovies);
    }

    public void showList(ArrayList<Movies> movies) {
        final RecyclerView rv = (RecyclerView) findViewById(R.id.filmView);
        rv.setLayoutManager(new GridLayoutManager(this, 2));
        rv.setAdapter(new MyAdapter(movies, MainMenuActivity.this));
    }

    public void displayPushNotification(){
        NotificationManager notif = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notify = new Notification.Builder(getApplicationContext()).setContentTitle("MOVIEAPP").setContentText("A new movie was released").setSmallIcon(R.drawable.ic_launcher_background).build();
        notify.flags |= Notification.FLAG_AUTO_CANCEL;
        notif.notify(0, notify);
    }
}

