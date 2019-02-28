package com.example.androidproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    private APIManager apiManager = new APIManager(SearchActivity.this);
    public TextView pageNumber;
    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        pageNumber = findViewById(R.id.pages);

        final Button btnNext = findViewById(R.id.nextBtn);
        final Button btnBefore = findViewById(R.id.beforeBtn);
        final SearchView searchBar = findViewById(R.id.searchBar);

        searchBar.setFocusable(true);
        searchBar.setIconified(false);


        searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                apiManager.page = 1;
                apiManager.getMovies(query);
                btnBefore.setVisibility(View.VISIBLE);
                btnNext.setVisibility(View.VISIBLE);
                title = query;
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apiManager.page++;
                if(apiManager.page > apiManager.nbPages) apiManager.page = apiManager.nbPages;
                apiManager.getMovies(title);

            }
        });

        btnBefore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apiManager.page--;
                if(apiManager.page < 1)apiManager.page = 1;
                apiManager.getMovies(title);
            }
        });

    }


    public void showList(ArrayList<Movies> movies) {
        final RecyclerView rv = (RecyclerView) findViewById(R.id.filmView);
        rv.setLayoutManager(new GridLayoutManager(this, 2));
        rv.setAdapter(new MyAdapter(movies, SearchActivity.this));
    }
}
