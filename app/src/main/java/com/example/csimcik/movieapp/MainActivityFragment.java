package com.example.csimcik.movieapp;
//imports
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.csimcik.movieapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class MainActivityFragment extends Fragment {
    static ImageAdapter imageAd;
    static int place;
    GridView gridView;
    View view;




    public MainActivityFragment() {
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final Intent detailView = new Intent(getActivity(),DetailSheet.class);


        view = inflater.inflate(R.layout.fragment_main, container, false);
        gridView = (GridView) view.findViewById(R.id.gridview);

        imageAd = new ImageAdapter(view.getContext());
        gridView.setAdapter(imageAd);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                place = position;
                startActivity(detailView);



            }
        });
        return view;
    }
}






