package com.example.csimcik.movieapp;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.example.csimcik.movieapp.ImageAdapter.idList;
import static com.example.csimcik.movieapp.ImageAdapter.overTxt;
import static com.example.csimcik.movieapp.ImageAdapter.posterImages;
import static com.example.csimcik.movieapp.ImageAdapter.releaseTxt;
import static com.example.csimcik.movieapp.ImageAdapter.titleTxt;
import static com.example.csimcik.movieapp.ImageAdapter.voteTxt;

/**
 * Created by csimcik on 5/3/2017.
 */
public class MovieDbFavs extends AsyncTask<Void,Void,String> {
    String poster;
    String synopsis;
    String title;
    String release;
    String voteAvg;
    String movieID;



    @Override
    protected String doInBackground(Void... urls) {

        try {
            URL url = new URL("http://api.themoviedb.org/3/"+MainActivity.searchSelect+"api_key=90523ea53ebd8f5341a39e92d5fa925c");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                bufferedReader.close();
                return stringBuilder.toString();
            }
            finally{
                urlConnection.disconnect();
            }
        }
        catch(Exception e) {
            Log.e("ERROR", e.getMessage(), e);
            return null;
        }
    }

    protected void onPostExecute(String response) {
        posterImages.clear();
        if(response == null) {
            response = "THERE WAS AN ERROR";
        }
        Log.i("INFO", response);
        JSONObject json = null;
        try {
            json = new JSONObject(response);
            JSONArray jsonMainNode = json.optJSONArray("results");

            int lengthJsonArr = jsonMainNode.length();
            // array list of posters

            for(int i=0; i < lengthJsonArr; i++)
            {
                JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);
                String poster   = jsonChildNode.optString("poster_path").toString();
                if( posterImages.size() < jsonMainNode.length()) {
                    posterImages.add(poster);
                }
                String synopsis   = jsonChildNode.optString("overview").toString();
                if( overTxt.size() < jsonMainNode.length()) {
                    overTxt.add(synopsis);
                }
                String title   = jsonChildNode.optString("title").toString();
                if( titleTxt.size() < jsonMainNode.length()) {
                    titleTxt.add(title);
                }
                String release   = jsonChildNode.optString("release_date").toString();
                if(releaseTxt.size() < jsonMainNode.length()) {
                    releaseTxt.add(release);
                }
                String voteAvg   = jsonChildNode.optString("vote_average").toString();
                if(voteTxt.size() < jsonMainNode.length()) {
                    voteTxt.add(voteAvg);
                }
                String movieID   = jsonChildNode.optString("id").toString();
                if( idList.size() < jsonMainNode.length()) {
                    idList.add(new FavorItem(movieID,false));
                }


                Log.i("Poster", poster);
                Log.i("video", movieID);




            }
            MainActivityFragment.imageAd.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();

        }


    }




}