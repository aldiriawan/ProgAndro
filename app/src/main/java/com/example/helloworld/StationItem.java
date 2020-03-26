package com.example.helloworld;

public class StationItem {
    private String title;
    private String urlimages;
    private String synopsis;

    public StationItem(String title, String urlimages, String synopsis){
        this.title = title;
        this.urlimages = urlimages;
        this.synopsis = synopsis;
    }

    public String getTitle() {
        return title;
    }

    public String getUrlimages() {
        return urlimages;
    }

    public String getSynopsis() {
        return synopsis;
    }
}
