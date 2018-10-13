package com.example.csimcik.movieapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Created by csimcik on 2/9/2017.
 */
public class trailerFetch extends AsyncTask<Void,Void,String> {
    String thisMovie;
    static float vidNum;

    @Override
    protected String doInBackground(Void... urls) {
         thisMovie = DetailSheetFragment.movieIdString;


        try {
            URL url = new URL("https://api.themoviedb.org/3/movie/"+ thisMovie +"/videos?api_key=90523ea53ebd8f5341a39e92d5fa925c&language=en-US");
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

            int lengthJsonArr = jsonMainNode.length();
            vidNum = lengthJsonArr;
            // array list of posters

            for(int i=0; i < lengthJsonArr; i++)
            {
                JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);

                String youTubeskey   = jsonChildNode.optString("key").toString();
                if( MainActivity.trailerKey.size() < jsonMainNode.length()) {
                    MainActivity.trailerKey.add(youTubeskey);


                    Log.i("whats added", youTubeskey);
                    Log.i("WHAT IS WRONG", String.valueOf(MainActivity.trailerKey.size()));

                }






            }
            TrailerPlayer.trailerAdapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();


        }


    }



}