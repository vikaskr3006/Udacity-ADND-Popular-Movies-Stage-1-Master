package com.example.vikaskumar.popularmovies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


public class DetailActivity extends AppCompatActivity {

    private ImageView mPosterIv;
    private TextView mRatingLabelTv, mReleaseDateLabelTv;
    private TextView mDetailActivityTitleTv, mMovieTitleTv, mRatingTv, mReleaseDateTv, mSynopsisTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_detail);
        setTitle(getString(R.string.app_title));
        mDetailActivityTitleTv = findViewById(R.id.detail_activity_title_tv);
        mPosterIv = findViewById(R.id.poster_iv);
        mMovieTitleTv = findViewById(R.id.movie_title_tv);
        mRatingLabelTv = findViewById(R.id.rating_label_tv);
        mRatingTv = findViewById(R.id.rating_tv);
        mReleaseDateLabelTv = findViewById(R.id.release_date_label_tv);
        mReleaseDateTv = findViewById(R.id.release_date_tv);
        mSynopsisTv = findViewById(R.id.synopsis_tv);

        Intent intent = getIntent();
        if(intent.hasExtra(getString(R.string.parcel_movie))){
            Movie movie = intent.getParcelableExtra(getString(R.string.parcel_movie));
            String movieTitle = movie.getOriginalTitle();
            mDetailActivityTitleTv.setText(getString(R.string.detail_activity_title));
            Picasso.with(getBaseContext()).load(movie.getPosterPath())
                    .resize(getResources().getInteger(R.integer.movie_poster_w185_width),getResources().getInteger(R.integer.movie_poster_w185_height))
                    .error(R.drawable.network_error).placeholder(R.drawable.loading_image).into(mPosterIv);
            mRatingTv.setText(movie.getVoteAverage().toString() + "/10");
            mReleaseDateTv.setText(movie.getReleaseDate());
            mMovieTitleTv.setText(movieTitle);
            mSynopsisTv.setText(movie.getOverview());
        }
    }
}
