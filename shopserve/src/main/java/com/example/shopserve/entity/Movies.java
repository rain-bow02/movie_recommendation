package com.example.shopserve.entity;

import java.io.Serializable;

public class Movies  implements Serializable {
    private Integer id;
    private String name;
    private Integer imdb_id;
    private String release_time;
    private String intro;
    private String director;
    private String writers;
    private String actors;
    private String time;
    private String genre;
    private double avg_rating;

    public double getAvg_rating() {
        return avg_rating;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getImdb_id() {
        return imdb_id;
    }

    public String getRelease_time() {
        return release_time;
    }

    public String getIntro() {
        return intro;
    }

    public String getDirector() {
        return director;
    }

    public String getWriters() {
        return writers;
    }

    public String getActors() {
        return actors;
    }

    public String getTime() {
        return time;
    }

    public String getGenre() {
        return genre;
    }
}
