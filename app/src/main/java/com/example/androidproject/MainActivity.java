package com.example.androidproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnFind = findViewById(R.id.btnFind);
        final EditText enterTitle = findViewById(R.id.enterTitle);
        btnFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                APIManager apiManager = new APIManager(MainActivity.this);
                apiManager.getMovies(enterTitle.getText().toString());
            }
        });
        //Movies movies = new Movies();
        //Movies.

        /*TextView text = findViewById(R.id.viewT);
        text.setText(pageData);*/
        /*ArrayList<Movies> moviesList = apiManager.getMainDataMovies(apiManager.strToJson(pageData));

        final RecyclerView rv = (RecyclerView) findViewById(R.id.filmView);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new MyAdapter());*/
    }


    public void showList(ArrayList<Movies> movies) {
        final RecyclerView rv = (RecyclerView) findViewById(R.id.filmView);
        rv.setLayoutManager(new GridLayoutManager(this, 2));
        rv.setAdapter(new MyAdapter(movies));
        /*TextView textV = findViewById(R.id.textV);
        textV.setText(movies.get(1).getYear());*/
    }
}
