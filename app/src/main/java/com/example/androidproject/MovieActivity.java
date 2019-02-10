package com.example.androidproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class MovieActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        Bundle extras = getIntent().getExtras();
        if (extras != null){
            APIManager api = new APIManager(this);
            api.getMovieDetails(extras.get("targetMovie").toString());

        }
    }

    public void displayMovieInfo(Movies movie){
        TextView title = findViewById(R.id.title);
        title.setText(movie.getTitle());
    }
}
