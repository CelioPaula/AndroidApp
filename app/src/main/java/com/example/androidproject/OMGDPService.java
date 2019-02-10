package com.example.androidproject;

import android.util.Pair;

import com.google.gson.JsonElement;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface OMGDPService {

    String BASE_URL = "http://www.omdbapi.com";
    String KEY_API = "/?apikey=2783c44f";


    @GET(KEY_API)
    Call<JsonElement> getMovies(@Query("s") String title, @Query("page") String page);

    @GET(KEY_API)
    Call<JsonElement> getMovieDetails(@Query("t") String title);
}
