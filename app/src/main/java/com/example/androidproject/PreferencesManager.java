package com.example.androidproject;

import android.content.SharedPreferences;
import android.widget.Toast;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class PreferencesManager {
    private static final String SEARCH_KEY = "SEARCH_KEY";
    private static final String MYPREFS = "MYPREFS";
    private MainActivity mainActivity;
    private SearchActivity searchActivity;
    private SharedPreferences sharedPreferences;

    public PreferencesManager(MainActivity mainActivity){
        this.mainActivity = mainActivity;
        searchActivity = null;
    }

    public PreferencesManager(SearchActivity searchActivity){
        this.searchActivity = searchActivity;
        mainActivity = null;
    }

    public void addSharedPreferences(Movies movie){
        sharedPreferences = searchActivity.getBaseContext().getSharedPreferences(MYPREFS, MODE_PRIVATE);
        if(sharedPreferences.contains(SEARCH_KEY)) {
            List<SearchPreferences> listPrefs = getListFromJSON(sharedPreferences.getString(SEARCH_KEY, null));
            if(!checkPrefsContainsMovie(listPrefs, movie)) listPrefs.add(new SearchPreferences(movie.getTitle(), movie.getUrlPoster(), movie.getType(), movie.getYear()));
            try {
                sharedPreferences
                        .edit()
                        .putString(SEARCH_KEY, new ObjectMapper().writeValueAsString(listPrefs))
                        .apply();
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }else{
            List<SearchPreferences> listPrefs = new ArrayList<>();
            listPrefs.add(new SearchPreferences(movie.getTitle(), movie.getUrlPoster(), movie.getGenre(), movie.getYear()));
            sharedPreferences
                    .edit()
                    .putString(SEARCH_KEY, new Gson().toJson(listPrefs))
                    .apply();
        }
    }

    public ArrayList<Movies> getSharedPreferences(){

        ArrayList<Movies> lastSearch = new ArrayList<>();
        sharedPreferences = mainActivity.getBaseContext().getSharedPreferences(MYPREFS, MODE_PRIVATE);
        List<SearchPreferences> listPrefs = getListFromJSON(sharedPreferences.getString(SEARCH_KEY, null));
        for (SearchPreferences pref : listPrefs) {
            Movies movie = new Movies();
            movie.setType(pref.getType());
            movie.setTitle(pref.getTitle());
            movie.setUrlPoster(pref.getUrlPoster());
            movie.setYear(pref.getReleased());
            lastSearch.add(movie);
        }
        return lastSearch;
    }

    private List<SearchPreferences> getListFromJSON(String jsonString){
        ObjectMapper jsonMapper = new ObjectMapper();
        List<SearchPreferences> list = new ArrayList<>();
        if(jsonString != null) {
            try {
                list = jsonMapper.readValue(jsonString, new TypeReference<List<SearchPreferences>>() {
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    private boolean checkPrefsContainsMovie(List<SearchPreferences> listPrefs, Movies movie){
        boolean contain = false;
        for(SearchPreferences pref : listPrefs){
            if(pref.getTitle().equals(movie.getTitle())) contain = true;
        }
        return contain;
    }

    public void emptyPreferences(){
        sharedPreferences = mainActivity.getApplicationContext().getSharedPreferences(MYPREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("SEARCH_KEY");
        editor.commit();
    }
}
