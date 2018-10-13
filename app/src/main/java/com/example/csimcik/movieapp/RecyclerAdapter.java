package com.example.csimcik.movieapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeStandalonePlayer;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by csimcik on 2/10/2017.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.VideoInfoHolder> {


    private Context trailerContext;
    int countV= 0;
    float adder = 0;
    float remap = 0;
    float vidNum;
    long taginc = 0;
    static VideoInfoHolder itemView;
    ArrayList<YouTubeThumbnailLoader> loaders;
    View view;
        public RecyclerAdapter(Context context) {
            this.trailerContext = context;

        }



    @Override
        public VideoInfoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
             view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_trailer_player, parent, false);
        view.setVisibility(View.INVISIBLE);
              itemView = new VideoInfoHolder(view);





            return itemView;
            };



        @Override
        public void onBindViewHolder(final VideoInfoHolder holder, final int position) {
            MainActivity.trailerKey.get(position);
                holder.youTubeThumbnailView.initialize(MainActivity.trailerKey.get(position), new YouTubeThumbnailView.OnInitializedListener() {

                    @Override
                    public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, final YouTubeThumbnailLoader youTubeThumbnailLoader) {
                        youTubeThumbnailLoader.setVideo(MainActivity.trailerKey.get(position));
                        vidNum += 1;
                        taginc += 1;
                        youTubeThumbnailView.setTag(taginc);
                        Log.d("ItemId ", String.valueOf(holder.youTubeThumbnailView.getTag()));

                        youTubeThumbnailLoader.setOnThumbnailLoadedListener(new YouTubeThumbnailLoader.OnThumbnailLoadedListener(){


                            @Override
                            public void onThumbnailLoaded(YouTubeThumbnailView youTubeThumbnailView, String s) {
                                remap = (1 / vidNum) * 10;
                                youTubeThumbnailView.setVisibility(View.VISIBLE);
                                holder.relativeLayoutOverYouTubeThumbnailView.setVisibility(View.VISIBLE);
                                holder.playButton.setVisibility(View.VISIBLE);
                                TrailerPlayer.progPi.setClrSlct(2);
                                //TrailerPlayer.progPi.trailerColors(countV,MainActivity.trailerKey.size());
                                adder += remap;
                                TrailerPlayer.progPi.setDataPoints(adder);
                                if (countV < MainActivity.trailerKey.size()) {
                                    countV += 1;


                                }

                                if (countV == MainActivity.trailerKey.size()) {

                                    TrailerPlayer.progPi.setDataPoints(adder);
                                    TrailerPlayer.progPi.setVisibility(View.INVISIBLE);

                                    view.setVisibility(View.VISIBLE);
                                    countV += 1;
                                }

                                Log.d("This is countV ", String.valueOf(countV) + " " + String.valueOf(MainActivity.trailerKey.size()) + " " + remap);
                                Log.d("This is the data", " " + TrailerPlayer.progPi.scale() + " " + String.valueOf(adder) + " " + 1 / vidNum * 10);
                                TrailerPlayer.progPi.invalidate();
                                youTubeThumbnailLoader.release();
                            }

                            @Override
                            public void onThumbnailError(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader.ErrorReason errorReason) {

                            }
                        });
                    }


                    @Override
                    public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult) {
                    }
                });
        }


        @Override
        public int getItemCount() {
            return MainActivity.trailerKey.size();
        }


        public class VideoInfoHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

                protected RelativeLayout relativeLayoutOverYouTubeThumbnailView;
                YouTubeThumbnailView youTubeThumbnailView;
                protected ImageView playButton;

                public VideoInfoHolder(View itemView) {
                    super(itemView);
                    playButton = (ImageView) itemView.findViewById(R.id.btnYoutube_player);
                    playButton.setVisibility(View.INVISIBLE);
                    playButton.setOnClickListener(this);
                    relativeLayoutOverYouTubeThumbnailView = (RelativeLayout) itemView.findViewById(R.id.relativeLayout_over_youtube_thumbnail);
                    youTubeThumbnailView = (YouTubeThumbnailView) itemView.findViewById(R.id.youtube_thumbnail);

                }

            @Override
            public void onClick(View v) {
                Intent playVid = YouTubeStandalonePlayer.createVideoIntent((Activity)trailerContext,Config.YOUTUBE_API_KEY, MainActivity.trailerKey.get(getLayoutPosition()),//video id
                        100,
                        true,
                        false);
                trailerContext.startActivity(playVid);
            }
        }

    }
