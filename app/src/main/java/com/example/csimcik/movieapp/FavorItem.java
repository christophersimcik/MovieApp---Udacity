package com.example.csimcik.movieapp;

/**
 * Created by csimcik on 5/3/2017.
 */
public class FavorItem {
    private String movieId;
    public Boolean canAdd;

    public FavorItem(String movieid, Boolean canadd){
        this.movieId = movieid;
        this.canAdd = canadd;
    }
    public String getMovieId(){
        return this.movieId;
    }
    public Boolean getCanAdd(){
        return this.canAdd;
    }
    public void changeVal(){
        if(canAdd){
            canAdd = false;
        }else{
            canAdd = true;
        }

    }

}
