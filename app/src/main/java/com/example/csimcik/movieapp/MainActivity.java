package com.example.csimcik.movieapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import com.example.csimcik.movieapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
public class MainActivity extends AppCompatActivity {
    static ArrayList<ReviewItem> reviewsList = new ArrayList<>();
    static ArrayList<String> authorsList = new ArrayList<>();
    static ArrayList<String> trailerKey = new ArrayList<String>();
    static ArrayList<FavorItem> favoritesList = new ArrayList<FavorItem>();
    static String searchSelect;
    static FavoriteDataB favdatabase;
    private static MainActivity instance;
    public MainActivity(){
        instance = this;
    }
    public static Context getContext(){
        return instance;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        favdatabase = new FavoriteDataB(this);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(searchSelect == null) {
            searchSelect = "movie/popular?";
            new MovieDB(this).execute();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.action_popular) {
            ImageAdapter.idList.clear();
            MovieDB.movieDB.deleteAll();
            ImageAdapter.posterImages.clear();
            ImageAdapter.overTxt.clear();
            ImageAdapter.titleTxt.clear();
            ImageAdapter.releaseTxt.clear();
            ImageAdapter.voteTxt.clear();
            MainActivity.authorsList.clear();
            MainActivity.reviewsList.clear();
            MainActivity.trailerKey.clear();
            favoritesList.clear();
            searchSelect = "movie/popular?";
            new MovieDB(this).execute();
            return true;
        }
        if (id == R.id.action_money) {
            ImageAdapter.idList.clear();
            ImageAdapter.posterImages.clear();
            ImageAdapter.overTxt.clear();
            ImageAdapter.titleTxt.clear();
            ImageAdapter.releaseTxt.clear();
            ImageAdapter.voteTxt.clear();
            MainActivity.authorsList.clear();
            MainActivity.reviewsList.clear();
            MainActivity.trailerKey.clear();
            favoritesList.clear();
            searchSelect = "movie/top_rated?";
            new MovieDB(this).execute();
            return true;
        }
        if (id == R.id.action_favorites) {
            ImageAdapter.idList.clear();
            ImageAdapter.posterImages.clear();
            ImageAdapter.overTxt.clear();
            ImageAdapter.titleTxt.clear();
            ImageAdapter.releaseTxt.clear();
            ImageAdapter.voteTxt.clear();
            MainActivity.authorsList.clear();
            MainActivity.reviewsList.clear();
            MainActivity.trailerKey.clear();
            favoritesList.clear();
            searchSelect = "favorites";
            favoritesList =  favdatabase.getAllRecords();
            for(int i = 0; i < favoritesList.size(); i++){
                Log.d("The Movie Added", favoritesList.get(i).getMovieId());
                new FavDB(favoritesList.get(i).getMovieId()).execute();
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
