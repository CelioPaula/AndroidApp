package com.example.androidproject;

import android.app.Activity;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIManager extends Activity {

    private final MainActivity activity;

    public APIManager(MainActivity mainActivity) {
        this.activity = mainActivity;
    }

    public void getMovies(String title) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(OMGDPService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        OMGDPService api = retrofit.create(OMGDPService.class);
        Call<JsonElement> call = api.getMovies(title);
        call.enqueue(new Callback<JsonElement>(){
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response){
                if(response.isSuccessful()) {
                    JsonElement json = response.body();
                    ArrayList<Movies> movies = (ArrayList<Movies>) setListDataMovieFromJSON(json.getAsJsonObject());
                    activity.showList(movies);
                }
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t){
                Toast.makeText(activity.getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }



    public ArrayList<Movies> setListDataMovieFromJSON(JsonObject json){
        ArrayList<Movies> movies_list = null;
        try {
            movies_list = new ArrayList<>();
            JsonArray movies_array = json.getAsJsonArray("Search");
            for(int i = 0; i<movies_array.size(); i++){
                JsonObject jsonMovie = (JsonObject) movies_array.get(i);
                Movies movie = new Movies();
                movie.setTitle(String.valueOf(jsonMovie.get("Title")).replace("\"", ""));
                movie.setYear(String.valueOf(jsonMovie.get("Year")).replace("\"", ""));
                movie.setType(String.valueOf(jsonMovie.get("Type")).replace("\"", ""));
                movie.setUrlPoster(String.valueOf(jsonMovie.get("Poster")).replace("\"", ""));
                movies_list.add(movie);
            }
        } catch (JsonIOException e) {
            Toast.makeText(activity.getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        return movies_list;
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
