package com.example.csimcik.movieapp;

/**
 * Created by csimcik on 5/13/2017.
 */
public class MovieFavorite {
    private String moviesId;
    private Boolean canAdd;

    public MovieFavorite(String movId, Boolean cAdd){
        this.moviesId = movId;
        this.canAdd = cAdd;
    }
    public String getmovId(){
        return this.moviesId;
    }
    public Boolean getCanAdd(){return this.canAdd;}

}

