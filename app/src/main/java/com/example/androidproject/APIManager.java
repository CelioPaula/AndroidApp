package com.example.androidproject;

import android.app.DownloadManager;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIManager {

    public static final String ENDPOINT = "http://www.omdbapi.com/";
    private final MainActivity activity;

    public APIManager(MainActivity mainActivity) {
        this.activity = mainActivity;
    }

    public void getDataFromAPI(String title) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(OMGDPService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        OMGDPService api = retrofit.create(OMGDPService.class);
        Call<List<Movies>> call = api.getMovies(title);

        call.enqueue(new Callback<List<Movies>>(){
            @Override
            public void onResponse(Call<List<Movies>> call, Response<List<Movies>> response){
                List<Movies> movies = response.body();
                activity.showList(movies);
            }

            @Override
            public void onFailure(Call<List<Movies>> call, Throwable t){
                //Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*public void getDataDetailsFromAPI(String title) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(OMGDPService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        OMGDPService api = retrofit.create(OMGDPService.class);
        Call<List<Movies>> call = api.getMoviesDetails(title);

        call.enqueue(new Callback<List<Movies>>(){
            @Override
            public void onResponse(Call<List<Movies>> call, Response<List<Movies>> response){
                List<Movies> movies = response.body();
                activity.showList(movies);
            }

            @Override
            public void onFailure(Call<List<Movies>> call, Throwable t){
                Toast.makeText(getDataFromAPI(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }*/
    }
