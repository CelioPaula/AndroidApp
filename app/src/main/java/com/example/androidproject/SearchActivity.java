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
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    private APIManager apiManager = new APIManager(SearchActivity.this);
    public TextView pageNumber;
    private String title;
    private Button btnNext;
    private Button btnBefore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        pageNumber = findViewById(R.id.pages);

        final SearchView searchBar = findViewById(R.id.searchBar);

        searchBar.setFocusable(true);
        searchBar.setIconified(false);

        btnNext = findViewById(R.id.nextBtn);
        btnBefore = findViewById(R.id.beforeBtn);


        searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                showProgressBar(true);
                apiManager.page = 1;
                apiManager.getMovies(query);
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

    public void showProgressBar(boolean show){
        final ProgressBar bar = findViewById(R.id.progressBar);
        if(show) bar.setVisibility(View.VISIBLE);
        else bar.setVisibility(View.INVISIBLE);
    }

    public void showButtons(boolean show){
        if(show) {
            btnBefore.setVisibility(View.VISIBLE);
            btnNext.setVisibility(View.VISIBLE);
        }else{
            btnBefore.setVisibility(View.INVISIBLE);
            btnNext.setVisibility(View.INVISIBLE);
        }
    }
}
