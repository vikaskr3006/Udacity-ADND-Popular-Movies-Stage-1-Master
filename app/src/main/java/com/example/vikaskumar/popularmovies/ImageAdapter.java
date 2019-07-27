package com.example.vikaskumar.popularmovies;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<Movie> moviesList;

    public ImageAdapter(Context context, ArrayList<Movie> movies) {
        mContext = context;
        moviesList = movies;
    }

    @Override
    public int getCount() {
        if (moviesList == null || moviesList.size() == 0) {
            return -1;
        }
        return moviesList.size();
    }

    @Override
    public Movie getItem(int position) {
        if (moviesList == null || moviesList.size() == 0) {
            return null;
        }
        return moviesList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if(convertView == null){
            imageView = new ImageView(mContext);
            imageView.setAdjustViewBounds(true);
        }else{
            imageView = (ImageView) convertView;
        }
        Picasso.with(mContext).load(moviesList.get(position).getPosterPath())
                .resize(mContext.getResources().getInteger(R.integer.movie_poster_w185_width),mContext.getResources().getInteger(R.integer.movie_poster_w185_height))
                .error(R.drawable.network_error).placeholder(R.drawable.loading_image).into(imageView);

        return imageView;
    }
}
