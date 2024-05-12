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

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImdb_id(Integer imdb_id) {
        this.imdb_id = imdb_id;
    }

    public void setRelease_time(String release_time) {
        this.release_time = release_time;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setWriters(String writers) {
        this.writers = writers;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setAvg_rating(double avg_rating) {
        this.avg_rating = avg_rating;
    }
}
