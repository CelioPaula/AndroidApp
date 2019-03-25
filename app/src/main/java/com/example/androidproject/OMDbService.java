package com.example.androidproject;


import com.google.gson.JsonElement;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface OMDbService {

    String BASE_URL = "http://www.omdbapi.com";
    String KEY_API = "/?apikey=2783c44f";


    @GET(KEY_API)
    Call<JsonElement> getMovies(@Query("s") String title, @Query("page") String page);

    @GET(KEY_API)
    Call<JsonElement> getMovieDetails(@Query("t") String title);
}
