package com.example.androidproject;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    /*private PreferencesManager preferencesManager;
    private ArrayList<Movies> prefMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final Button btnCache = findViewById(R.id.btnCache);
        final Button btnResearch = findViewById(R.id.btnResearch);

        displayPushNotification();
        showPreferences();

        btnCache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "CACHE MEMORY CLEARED", Toast.LENGTH_LONG).show();
                preferencesManager.emptyPreferences();
                prefMovies.clear();
                showList(prefMovies);
            }
        });

        btnResearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MainActivity2.class);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public void onResume(){
        super.onResume();
        showPreferences();
    }

    public void showPreferences(){
        preferencesManager = new PreferencesManager(this);
        prefMovies = preferencesManager.getSharedPreferences();
        showList(prefMovies);
    }

    public void showList(ArrayList<Movies> movies) {
        final RecyclerView rv = (RecyclerView) findViewById(R.id.filmView);
        rv.setLayoutManager(new GridLayoutManager(this, 2));
        rv.setAdapter(new MyAdapter(movies, MainActivity.this));
    }

    public void displayPushNotification(){
        NotificationManager notif = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notify = new Notification.Builder(getApplicationContext()).setContentTitle("MOVIEAPP").setContentText("A new movie was released").setSmallIcon(R.drawable.ic_launcher_background).build();
        notify.flags |= Notification.FLAG_AUTO_CANCEL;
        notif.notify(0, notify);
    }*/
}
