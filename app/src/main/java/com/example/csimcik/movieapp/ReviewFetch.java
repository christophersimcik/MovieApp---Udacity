package com.example.csimcik.movieapp;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.*;
import android.widget.ListAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by csimcik on 5/2/2017.
 */
public class ReviewFetch extends AsyncTask<Void,Void,String> {
        String thisMovie;

@Override
protected String doInBackground(Void... urls) {
        thisMovie = DetailSheetFragment.movieIdString;


        try {
        URL url = new URL("https://api.themoviedb.org/3/movie/"+ thisMovie +"/reviews?api_key=90523ea53ebd8f5341a39e92d5fa925c&language=en-US");
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
        } finally {
        urlConnection.disconnect();
        }
        } catch (Exception e) {
        Log.e("ERROR", e.getMessage(), e);
        Log.i("Shit","this is fucked up");
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
        JSONArray jsonMainNode = json.optJSONArray("results");
        /*********** Process each JSON Node ************/

        int lengthJsonArr = jsonMainNode.length();
        // array list of posters
// Evaluate if there is a response
            if(lengthJsonArr > 0){
        for(int i=0; i < lengthJsonArr; i++) {
            /****** Get Object for each JSON node.***********/
            JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);

            /******* Fetch node values **********/
            String review = jsonChildNode.optString("content").toString();
            String author = jsonChildNode.optString("author").toString();
            if (MainActivity.reviewsList.size() < jsonMainNode.length()) {
                if (lengthJsonArr > 0) {
                    MainActivity.reviewsList.add(new ReviewItem(review, author, null));
                    Log.d("Review Data", String.valueOf(lengthJsonArr));
                }
            }
        }
            }else{
                MainActivity.reviewsList.add(new ReviewItem(null,null,"No Reviews Found"));
                Log.d("Review Data", String.valueOf(lengthJsonArr));
            }


            if(ReviewDisplay.myAdapter != null) {
                if(MainActivity.reviewsList.size() > 0)
                ReviewDisplay.myAdapter.notifyDataSetChanged();
            }
        } catch (JSONException e) {
        e.printStackTrace();
        }
}
}
