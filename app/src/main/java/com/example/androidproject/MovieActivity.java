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
        TextView director = findViewById(R.id.director);
        TextView released = findViewById(R.id.released);
        TextView genre = findViewById(R.id.genre);
        TextView actors = findViewById(R.id.actors);

        title.setText(movie.getTitle());
        director.setText("Director : " + movie.getDirector());
        released.setText("Released : " + movie.getYear());
        genre.setText("Genre : "  + movie.getGenre());
        actors.setText("Actors : " + movie.getActors());

        ImageView poster = findViewById(R.id.poster);
        if(!movie.getUrlPoster().equals("N/A"))Picasso.with(poster.getContext()).load(movie.getUrlPoster()).centerCrop().fit().into(poster);
        else Picasso.with(poster.getContext()).load("http://staging1.lebanesefeast.com.au/wp-content/uploads/2018/10/picture-not-available.jpg").centerCrop().fit().into(poster);
    }
}
