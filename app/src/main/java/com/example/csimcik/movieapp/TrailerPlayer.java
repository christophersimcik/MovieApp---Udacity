package com.example.csimcik.movieapp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

public class TrailerPlayer extends Activity{
    static RecyclerAdapterB trailerAdapter;
    public static Context mContext;
    public static PieChartView progPi;
    public static RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mContext = this;


        //MainActivity.trailerKey.clear();
        super.onCreate(savedInstanceState);
        new trailerFetch().execute();
        setContentView(R.layout.trailer_player);
        progPi=(PieChartView)findViewById(R.id.prog_pie);
        recyclerView=(RecyclerView)findViewById(R.id.list);
        recyclerView.setVisibility(View.VISIBLE);
        progPi.setVisibility(View.VISIBLE);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        trailerAdapter = new RecyclerAdapterB(mContext);
        recyclerView.setAdapter(trailerAdapter);
    }
}

