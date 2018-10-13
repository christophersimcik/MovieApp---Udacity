package com.example.csimcik.movieapp;

import android.app.Application;
import android.content.Context;

/**
 * Created by csimcik on 6/1/2017.
 */
public class GetApp extends Application {
    public static Context context;

    @Override public void onCreate() {
        super.onCreate();
        context = this.getApplicationContext();
    }
}
