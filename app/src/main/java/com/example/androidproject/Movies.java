package com.example.androidproject;

import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;

public class Movies {

    private String title;
    private String year;
    private String genre;
    private String director;
    private String actors;
    private String runTime;
    private String language;
    private URL urlPoster;
    private ArrayList<ArrayList<String>> rating;

    //private APIManager apiManager = new APIManager();


    public String getTitle(){return title;}

    public void setYear(String year){
        year = this.year;
    }

    public String getYear(){return year;}

    //public void getAl(){}

}
