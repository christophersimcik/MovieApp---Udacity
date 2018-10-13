package com.example.csimcik.movieapp;

/**
 * Created by csimcik on 5/2/2017.
 */

public class ReviewItem {
    private String reviewTxt;
    private String reviewAuthor;
    private String error;

    public ReviewItem(String rvwTxt, String rvwAuthor, String rverror){
        this.reviewTxt = rvwTxt;
        this.reviewAuthor = rvwAuthor;
        this.error = rverror;
    }
    public String getReviewTxt(){
        return this.reviewTxt;
    }
    public String getReviewAuthor(){return this.reviewAuthor;}
    public String getReviewError(){return this.error;}
}