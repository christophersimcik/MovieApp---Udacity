package com.example.csimcik.movieapp;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by csimcik on 4/15/2016.
 */
public class ReviewAdapter extends ArrayAdapter<ReviewItem> {
    public Context context;
    public ArrayList<ReviewItem> reviewFetches;

    public ReviewAdapter(Context context, int layoutR, ArrayList<ReviewItem> object) {
        super(context, layoutR, object);
        this.context = context;
        this.reviewFetches = object;

    }

    public View getView(int position, View convertView, ViewGroup parent) {

        ReviewItem reviews = MainActivity.reviewsList.get(position);
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.reviews, null);
        }
        if (convertView != null) {
            if (reviewFetches.get(position) != null) {
                TextView authorTxt = (TextView) convertView.findViewById(R.id.authorfield);
                TextView reviewTxt = (TextView) convertView.findViewById(R.id.reviewfield);
                TextView errorTxt = (TextView) convertView.findViewById(R.id.error);
                authorTxt.setText(reviewFetches.get(position).getReviewAuthor());
                reviewTxt.setText(reviewFetches.get(position).getReviewTxt());
                errorTxt.setText(reviewFetches.get(position).getReviewError());

            }


        }

        return convertView;
    }
}
