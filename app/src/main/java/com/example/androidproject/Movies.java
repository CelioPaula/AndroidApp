package com.example.androidproject;
public class Movies {

    String title;
    String year;
    private String genre;
    private String director;
    private String actors;
    private String runTime;
    private String language;
    String type;
    String urlPoster;
    String plot;
    String awards;
    String boxOffice;
    String production;

    //private ArrayList<ArrayList<String>> rating;

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getRunTime() {
        return runTime;
    }

    public void setRunTime(String runTime) {
        this.runTime = runTime;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrlPoster() {
        return urlPoster;
    }

    public void setUrlPoster(String urlPoster) {
        this.urlPoster = urlPoster;
    }

    public String getPlot() { return plot; }

    public void setPlot(String plot) { this.plot = plot; }

    public String getAwards() { return awards; }

    public void setAwards(String awards) { this.awards = awards; }

    public String getBoxOffice() { return boxOffice; }

    public void setBoxOffice(String boxOffice) { this.boxOffice = boxOffice; }

    public String getProduction() { return production; }

    public void setProduction(String production) { this.production = production; }
}
