package com.example.csimcik.movieapp;

import android.os.AsyncTask;
import android.util.Log;

import com.google.android.youtube.player.internal.s;

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
 * Created by csimcik on 5/6/2017.
 */
public class FavDB extends AsyncTask<String,Void,String> {
    String favID;

    public FavDB(String s) {
        favID = s;
    }
   

    @Override

    protected String doInBackground(String... urls) {
        try {
            URL url = new URL("http://api.themoviedb.org/3/movie/"+favID+"?api_key=90523ea53ebd8f5341a39e92d5fa925c");
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
        if(response == null) {
            response = "THERE WAS AN ERROR";
        }
        Log.i("INFO", response);
        JSONObject json = null;
        try {
            json = new JSONObject(response);

                JSONObject jsonChildNode = new JSONObject(response);

            String poster   = jsonChildNode.optString("poster_path").toString();
                    posterImages.add(poster);
            String synopsis   = jsonChildNode.optString("overview").toString();
                    overTxt.add(synopsis);
            String title   = jsonChildNode.optString("title").toString();
                    titleTxt.add(title);
            String release   = jsonChildNode.optString("release_date").toString();
                    releaseTxt.add(release);
            String voteAvg   = jsonChildNode.optString("vote_average").toString();
                    voteTxt.add(voteAvg);
            String movieID   = jsonChildNode.optString("id").toString();
                    idList.add(new FavorItem(movieID,false));

            Log.i("Poster", poster);
            Log.i("video", movieID);

            MainActivityFragment.imageAd.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
