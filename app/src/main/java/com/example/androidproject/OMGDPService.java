package com.example.androidproject;

import android.util.Pair;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface OMGDPService {

    String BASE_URL = "http://www.omdbapi.com/?";
    String KEY_API = "?apikey=2783c44f";

    @GET(KEY_API + "&s=" + "{title}")
    Call<List<Movies>> getMovies(@Path("title") String name);

    @GET(KEY_API + "&t=" + "{title}")
    Call<List<Movies>> getMoviesDetails(@Path("title") String name);
}
