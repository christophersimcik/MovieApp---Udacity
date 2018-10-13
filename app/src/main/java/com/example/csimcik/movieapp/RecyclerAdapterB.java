package com.example.csimcik.movieapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeStandalonePlayer;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

;


/**
 * Created by csimcik on 2/10/2017.
 */
public class RecyclerAdapterB extends RecyclerView.Adapter<RecyclerAdapterB.ViewInfoHolder> {


    private Context trailerContext;
    static int countV = 0;
    float adder = 0;
    float remap;
    float vidNum;
    long taginc = 0;
    static ViewInfoHolder itemView;
    ArrayList<YouTubeThumbnailLoader> loaders;
    View view;
        public RecyclerAdapterB(Context context) {
            this.trailerContext = context;

        }



    @Override
        public ViewInfoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_trailer_player_b, parent, false);
            view.setVisibility(View.VISIBLE);
            itemView = new ViewInfoHolder(view);
            return itemView;

            };

        @Override
        public void onBindViewHolder(final ViewInfoHolder holder, final int position) {
            Log.d("isrunning ", "this");
            String youtubeKey = MainActivity.trailerKey.get(position);
            Log.d("this is the key ", youtubeKey);
            String youtubeImageUrl = "http://img.youtube.com/vi/";
            Uri imageUri = Uri.parse(youtubeImageUrl).buildUpon()
                    .appendPath(youtubeKey)
                    .appendPath("hqdefault.jpg")
                    .build();
            Picasso.with(trailerContext)
                    .load(imageUri)
                    .tag(trailerContext)
                    .into(holder.youTubeThumbnailView, new com.squareup.picasso.Callback() {
                        @Override
                        public void onSuccess() {
                                holder.playButton.setVisibility(View.VISIBLE);
                                holder.playButton.invalidate();


                            Log.d("k",String.valueOf(countV));

                        }

                        @Override
                        public void onError() {

                        }
                    });
            remap = getItemCount();
            Log.d("Size ", String.valueOf(remap));

        }

        @Override
        public int getItemCount() {
            return MainActivity.trailerKey.size();
        }


        public class ViewInfoHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

                protected RelativeLayout relativeLayoutOverYouTubeThumbnailView;
                ImageView youTubeThumbnailView;
                protected ImageView playButton;

                public ViewInfoHolder(View itemView) {
                    super(itemView);
                    playButton = (ImageView) itemView.findViewById(R.id.btnYoutube_player);
                    playButton.setVisibility(View.INVISIBLE);
                    playButton.setOnClickListener(this);
                    relativeLayoutOverYouTubeThumbnailView = (RelativeLayout) itemView.findViewById(R.id.relativeLayout_over_youtube_thumbnail);
                    youTubeThumbnailView = (ImageView) itemView.findViewById(R.id.youtube_thumbs);

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
