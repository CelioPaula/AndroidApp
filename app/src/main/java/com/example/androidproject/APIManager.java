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

    private final MainActivity mainActivity;
    private final MovieActivity movieActivity;
    public ArrayList<Movies> movie_list = new ArrayList<>();
    public int nbPages = 0;
    public int page;

    public APIManager(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        movieActivity = null;
    }

    public APIManager(MovieActivity movieActivity){
        this.movieActivity = movieActivity;
        mainActivity = null;
    }

    public void getMovies(String title) {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(OMGDPService.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            OMGDPService api = retrofit.create(OMGDPService.class);
            Call<JsonElement> call = api.getMovies(title, String.valueOf(page));
            call.enqueue(new Callback<JsonElement>() {
                @Override
                public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                    if (response.isSuccessful()) {
                        JsonElement json = response.body();
                        if (json.getAsJsonObject().get("Response").getAsString().replace("\"", "").equals("True")) {
                            movie_list.clear();
                            int nbResults = Integer.parseInt(json.getAsJsonObject().get("totalResults").getAsString().replace("\"", ""));
                            nbPages = nbResults - nbResults%10;
                            nbPages = nbResults/10;
                            setListDataMovieFromJSON(json.getAsJsonObject());
                            mainActivity.showList(movie_list);
                            mainActivity.pageNumber.setText(String.valueOf(page)+"/"+String.valueOf(nbPages));

                        }
                    }
                }

                @Override
                public void onFailure(Call<JsonElement> call, Throwable t) {
                    t.printStackTrace();
                }
            });
    }

    private void setListDataMovieFromJSON(JsonObject json){
        try {
            JsonArray movies_array = json.getAsJsonArray("Search");
            for(int i = 0; i<movies_array.size(); i++){
                JsonObject jsonMovie = (JsonObject) movies_array.get(i);
                Movies movie = new Movies();
                movie.setTitle(String.valueOf(jsonMovie.get("Title")).replace("\"", ""));
                movie.setYear(String.valueOf(jsonMovie.get("Year")).replace("\"", ""));
                movie.setType(String.valueOf(jsonMovie.get("Type")).replace("\"", ""));
                movie.setUrlPoster(String.valueOf(jsonMovie.get("Poster")).replace("\"", ""));
                movie_list.add(movie);
            }
        } catch (JsonIOException e) {
            Toast.makeText(mainActivity.getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    public void getMovieDetails(String title) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(OMGDPService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        OMGDPService api = retrofit.create(OMGDPService.class);
        Call<JsonElement> call = api.getMovieDetails(title);
        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                if (response.isSuccessful()) {
                    JsonElement json = response.body();
                    Movies movie = getDetailsFromJSON(json.getAsJsonObject());
                    movieActivity.displayMovieInfo(movie);
                }
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private Movies getDetailsFromJSON(JsonObject jsonObject){
        Movies movie = new Movies();
        movie.setTitle(String.valueOf(jsonObject.get("Title")).replace("\"", ""));
        movie.setYear(String.valueOf(jsonObject.get("Released")).replace("\"", ""));
        movie.setRunTime(String.valueOf(jsonObject.get("Runtime")).replace("\"", ""));
        movie.setType(String.valueOf(jsonObject.get("Type")).replace("\"", ""));
        movie.setUrlPoster(String.valueOf(jsonObject.get("Poster")).replace("\"", ""));
        movie.setActors(String.valueOf(jsonObject.get("Actors")).replace("\"", ""));
        movie.setAwards(String.valueOf(jsonObject.get("Awards")).replace("\"", ""));
        movie.setBoxOffice(String.valueOf(jsonObject.get("BoxOffice")).replace("\"", ""));
        movie.setDirector(String.valueOf(jsonObject.get("Director")).replace("\"", ""));
        movie.setGenre(String.valueOf(jsonObject.get("Genre")).replace("\"", ""));
        movie.setLanguage(String.valueOf(jsonObject.get("Language")).replace("\"", ""));
        movie.setPlot(String.valueOf(jsonObject.get("Plot")).replace("\"", ""));
        movie.setProduction(String.valueOf(jsonObject.get("Production")).replace("\"", ""));
        return movie;
    }
}
