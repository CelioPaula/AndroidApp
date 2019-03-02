package com.example.androidproject;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

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
        TextView type = findViewById(R.id.type);
        TextView actors = findViewById(R.id.actors);
        TextView production = findViewById(R.id.production);
        TextView boxoffice = findViewById(R.id.boxoffice);
        TextView awards = findViewById(R.id.awards);
        TextView writer = findViewById(R.id.writer);
        TextView country = findViewById(R.id.country);
        TextView plot = findViewById(R.id.plot);

        TextView runtime = findViewById(R.id.runtime);
        TextView language = findViewById(R.id.language);
        TextView dvd = findViewById(R.id.dvd);

        String genreStr = movie.getGenre().replace(",", "\n").replace(" ", "");
        title.setText(movie.getTitle());
        genre.setText(genreStr);
        director.setText(movie.getDirector());
        released.setText(movie.getYear());
        type.setText(movie.getType());
        actors.setText(movie.getActors());
        production.setText(movie.getProduction());
        boxoffice.setText(movie.getBoxOffice());
        awards.setText(movie.getAwards());
        writer.setText(movie.getWriter());
        country.setText(movie.getCountry());
        plot.setText(movie.getPlot());

        runtime.setText(movie.getRunTime());
        language.setText(movie.getLanguage());
        dvd.setText(movie.getDvd());

        btnWebsite(movie.getWebsite());

        ImageView poster = findViewById(R.id.poster);
        if(!movie.getUrlPoster().equals("N/A"))Picasso.with(poster.getContext()).load(movie.getUrlPoster()).centerCrop().fit().into(poster);
        else Picasso.with(poster.getContext()).load("http://staging1.lebanesefeast.com.au/wp-content/uploads/2018/10/picture-not-available.jpg").centerCrop().fit().into(poster);
    }

    public void btnWebsite(final String url){
        Button btnWebsite = findViewById(R.id.btnWebsite);
        btnWebsite.setText(url);
        btnWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uriUrl = Uri.parse(url);
                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);
            }
        });
    }

    public void showProgressBar(boolean show){
        final ProgressBar bar = findViewById(R.id.progressBarMovie);
        if(show) bar.setVisibility(View.VISIBLE);
        else bar.setVisibility(View.INVISIBLE);
    }

}
