package com.example.csimcik.movieapp;

import android.app.ActionBar;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.StringBuilderPrinter;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.example.csimcik.movieapp.R.id.cancel_action;
import static com.example.csimcik.movieapp.R.id.favorite_text;
import static com.example.csimcik.movieapp.R.id.poster;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailSheetFragment extends Fragment implements View.OnClickListener {

    ImageView posterImg;
    TextView t;
    TextView synTxt;
    TextView titTxt;
    TextView yrTxt;
    PieChartView ratePie;
    TextView avgTxt;
    static String movieIdString;
    ImageView favBut;
    ImageView revBut;
    View v;
    public Boolean canAdd;
    static FavoriteDataB favdb;




    float tempRate = Float.parseFloat(ImageAdapter.voteTxt.get(MainActivityFragment.place));
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        favdb = new FavoriteDataB(getContext());
        //getContext().deleteDatabase("favorites");
        MainActivity.reviewsList.clear();
        MainActivity.authorsList.clear();
        MainActivity.trailerKey.clear();
        canAdd = true;
        Display display = ((WindowManager) getActivity().getSystemService(getActivity().WINDOW_SERVICE)).getDefaultDisplay();
        int rotation = display.getRotation();
        movieIdString = ImageAdapter.idList.get(MainActivityFragment.place).getMovieId();
        Log.i("fav_db size ", String.valueOf(favdb.getAllRecords().size()));
        for(int i = 0; i <favdb.getAllRecords().size(); i++) {
                if (favdb.getAllRecords().get(i).getMovieId().equals(movieIdString)) {
                    canAdd = false;
                    Log.d("its true", "this can be added" + canAdd);
                    Log.d("movieidstring", movieIdString + " " + favdb.getAllRecords().get(i).getMovieId());
                    break;
                }else{
                canAdd = true;
                Log.d("its true", "this can be added" + canAdd);
                Log.d("movieidstring", movieIdString + " " + favdb.getAllRecords().get(i).getMovieId());
            }

        }
       // canAdd = ImageAdapter.idList.get(MainActivityFragment.place).getCanAdd();
        // canAdd = MovieDB.movieDB.getAllRecords().get(MainActivityFragment.place).getCanAdd();
        if((rotation == 0)) {
             v = inflater.inflate(R.layout.detail_sheet, container, false);
        }else{
            v = inflater.inflate(R.layout.detail_sheet_2, container, false);
        }

        DisplayMetrics displaymetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int width = displaymetrics.widthPixels;
        double fetchWidth = (width/1.5)/3;
        t = (TextView)v.findViewById(R.id.favorite_text);
        int newWidth = (int)Math.round(fetchWidth);
        yrTxt = (TextView)v.findViewById(R.id.release_year);
        ratePie = (PieChartView) v.findViewById(R.id.pie_chart);
        avgTxt = (TextView) v.findViewById(R.id.avg_vote);
        yrTxt.getLayoutParams().width = newWidth + (newWidth-50);
        avgTxt.getLayoutParams().width = newWidth + (newWidth-50);
        Log.i("width", String.valueOf(newWidth));

        synTxt = (TextView) v.findViewById(R.id.synopsis);
        if(ImageAdapter.overTxt.get(MainActivityFragment.place).length() > 600){
            synTxt.setTextSize(14);
        }else {
            synTxt.setTextSize(16);
        }

        synTxt.setText("Synopsis: "+"\n"+"\n"+ImageAdapter.overTxt.get(MainActivityFragment.place));
        titTxt = (TextView) v.findViewById(R.id.title_header);
        int checkSize = ImageAdapter.titleTxt.get(MainActivityFragment.place).length();
        if(checkSize > 17){
            titTxt.setTextSize(20);
        }else{
            titTxt.setTextSize(25);
        }
        titTxt.setText(ImageAdapter.titleTxt.get(MainActivityFragment.place));
        //get movie database's movie ID
        String date = ImageAdapter.releaseTxt.get(MainActivityFragment.place);
        String dateYear[] = date.split("-");
        yrTxt.setText("Release Date:" + "\n" + dateYear[1] + "." + dateYear[2] + "." + dateYear[0]);

        if( tempRate < (float) 5.5){
            ratePie.setClrSlct(2);
        }else if(tempRate > (float) 8.1){
            ratePie.setClrSlct(1);
        }else{
            ratePie.setClrSlct(0);
        }
                ratePie.setDataPoints(tempRate);

        avgTxt.setText("Avg. Rating:"+ "\n" + ImageAdapter.voteTxt.get(MainActivityFragment.place));

        favBut = (ImageView) v.findViewById(R.id.asterix_button);
            revBut = (ImageView) v.findViewById(R.id.review_button);
        if(canAdd) {
            favBut.setImageResource(R.mipmap.favbut);
            t.setText("Add To Favorites");
        }else{
            favBut.setImageResource(R.mipmap.notfavbut);
            t.setText("Remove From Favorites");
        }
            revBut.setImageResource(R.mipmap.revbut);
            posterImg = (ImageView) v.findViewById(R.id.poster);
            favBut.setOnClickListener(this);
            revBut.setOnClickListener(this);
            posterImg.setOnClickListener(this);
        Picasso.with(getActivity())
                .load("http://image.tmdb.org/t/p/w500/" + ImageAdapter.posterImages.get(MainActivityFragment.place))
                .fit()
                .into(posterImg);





        return v;



    }




    @Override
    public void onClick(View v) {
switch(v.getId()){
    case R.id.asterix_button:
        Boolean okToAdd = false;
        final Intent detailView = new Intent(getActivity(),DetailSheet.class);
    if(canAdd) {
        MainActivity.favoritesList.add(new FavorItem(movieIdString, false));
        favdb.insertRecord(new FavorItem(movieIdString, false));
        ImageAdapter.idList.get(MainActivityFragment.place).changeVal();
        Toast addFav = Toast.makeText(getContext(), ImageAdapter.titleTxt.get(MainActivityFragment.place) + " was added to favorites", Toast.LENGTH_SHORT);
        addFav.show();
        favBut.setImageResource(R.mipmap.notfavbut);
        t.setText("Remove From Favorites");
        canAdd = false;
        v.invalidate();

        for(int i = 0; i < MainActivity.favoritesList.size(); i++) {
            String printFavs = String.valueOf(MainActivity.favoritesList.get(i));
            Log.e("Movie List", printFavs);

        }
    }else{
                Toast remFav = Toast.makeText(getContext(), ImageAdapter.titleTxt.get(MainActivityFragment.place) + " was removed from favorites", Toast.LENGTH_SHORT);
                remFav.show();
                favdb.deleteRecord(new FavorItem(movieIdString, null));
                canAdd = true;
                favBut.setImageResource(R.mipmap.favbut);
                t.setText("Add To Favorites");
                ImageAdapter.posterImages.remove(MainActivityFragment.place);
                v.invalidate();
                MainActivityFragment.imageAd.notifyDataSetChanged();




                //startActivity(detailView);
            }






        break;
    case R.id.review_button:
        final Intent reviewIntent = new Intent(getActivity(),ReviewDisplay.class);
        startActivity(reviewIntent);
        break;

    case poster:
        final Intent trailerIntent = new Intent(getActivity(),TrailerPlayer.class);
        trailerIntent.putExtra("trailerID",movieIdString);
        String toastText = movieIdString;
        //Toast toast = Toast.makeText(getContext(),toastText,Toast.LENGTH_SHORT);
        //toast.show();
        startActivity(trailerIntent);
        break;
}
    }



}
