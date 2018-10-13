package com.example.csimcik.movieapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ReviewDisplay extends AppCompatActivity {
 static ReviewAdapter myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainActivity.reviewsList.clear();
        new ReviewFetch().execute();
        setContentView(R.layout.activity_review_display);
        myAdapter = new ReviewAdapter(this,0,MainActivity.reviewsList);
        ListView listview = (ListView)findViewById(R.id.reviews);
        listview.setAdapter(myAdapter);










        }
    }


