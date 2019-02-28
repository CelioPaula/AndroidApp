package com.example.androidproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private APIManager apiManager = new APIManager(MainActivity.this);
    public TextView pageNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pageNumber = findViewById(R.id.pages);

        final EditText enterTitle = findViewById(R.id.enterTitle);
        final Button btnNext = findViewById(R.id.nextBtn);
        final Button btnBefore = findViewById(R.id.beforeBtn);
        final Button btnCache = findViewById(R.id.btnCache);
        final PreferencesManager preferencesManager = new PreferencesManager(this);
        final ArrayList<Movies> prefMovies = preferencesManager.getSharedPreferences();
        showList(prefMovies);

        btnCache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "CACHE MEMORY CLEARED", Toast.LENGTH_LONG).show();
                preferencesManager.emptyPreferences();
                prefMovies.clear();
                showList(prefMovies);
            }
        });

        enterTitle.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    apiManager.page = 1;
                    apiManager.getMovies(enterTitle.getText().toString());
                    btnBefore.setVisibility(View.VISIBLE);
                    btnNext.setVisibility(View.VISIBLE);
                    return true;
                }
                return false;
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apiManager.page++;
                if(apiManager.page > apiManager.nbPages) apiManager.page = apiManager.nbPages;
                apiManager.getMovies(enterTitle.getText().toString());

            }
        });

        btnBefore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apiManager.page--;
                if(apiManager.page < 1)apiManager.page = 1;
                apiManager.getMovies(enterTitle.getText().toString());
            }
        });
    }

    public void showList(ArrayList<Movies> movies) {
        final RecyclerView rv = (RecyclerView) findViewById(R.id.filmView);
        rv.setLayoutManager(new GridLayoutManager(this, 2));
        rv.setAdapter(new MyAdapter(movies, MainActivity.this));
    }

    public void showTextSearch(boolean show){
        final TextView searchText = findViewById(R.id.searchText);
        if(show) searchText.setVisibility(View.VISIBLE);
        else searchText.setVisibility(View.INVISIBLE);
    }
}
