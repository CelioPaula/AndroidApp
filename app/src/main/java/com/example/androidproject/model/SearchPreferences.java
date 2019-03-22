package com.example.androidproject.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchPreferences {

    private String title;
    private String urlPoster;
    private String type;
    private String released;

    public SearchPreferences(){ }

    public SearchPreferences(String title, String urlPoster, String type, String released){
        this.title = title;
        this.urlPoster = urlPoster;
        this.type = type;
        this.released = released;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrlPoster() {
        return urlPoster;
    }

    public void setUrlPoster(String urlPoster) {
        this.urlPoster = urlPoster;
    }

    public String getType() {
        return type;
    }

    public void setGenre(String type) {
        this.type = type;
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }
}
