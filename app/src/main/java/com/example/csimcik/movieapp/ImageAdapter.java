package com.example.csimcik.movieapp;

import android.content.Context;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by csimcik on 4/15/2016.
 */
public class ImageAdapter extends BaseAdapter {
    int idVal;
    static ArrayList<FavorItem> idList = new ArrayList<>();
    static ArrayList<String> posterImages = new ArrayList<String>();
    static ArrayList<String> overTxt = new ArrayList<String>();
    static ArrayList<String> titleTxt = new ArrayList<String>();
    static ArrayList<String> releaseTxt = new ArrayList<String>();
    static ArrayList<String> voteTxt = new ArrayList<String>();
    public Context mContext;
    public ImageAdapter(Context c) {
        mContext = c;
    }

    @Override
    public int getCount() {
        return posterImages.size();
    }
    @Override
    public Object getItem(int position) {
        return null;
    }
    @Override
    public long getItemId(int position) {
        return 0;
    }
    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(250,375));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding (16, 8, 16, 8);
            imageView.setId(idVal);
            idVal ++;
        } else {
            imageView = (ImageView) convertView;
        }


        Picasso.with(mContext)
                .load("http://image.tmdb.org/t/p/w500" + posterImages.get(position))
                .fit()
                .into(imageView);
        return imageView;

    }


}
