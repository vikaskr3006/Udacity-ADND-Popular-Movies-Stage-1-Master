package com.example.vikaskumar.popularmovies;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Movie implements Parcelable {

    private String mOriginalTitle, mPosterPath, mReleaseDate, mOverview;
    private double mVoteAverage;
    private int mMovieId;
    private final String RESULTS_PARAM = "results";
    private final String ID_PARAM = "id";
    private final String POSTER_PATH_PARAM = "poster_path";
    private final String ORIGINAL_TITLE_PARAM = "original_title";
    private final String VOTE_AVERAGE_PARAM = "vote_average";
    private final String RELEASE_DATE_PARAM = "release_date";
    private final String OVERVIEW_PARAM = "overview";
    private ArrayList<Movie> moviesList;


    private Movie(){
    }

    public Movie(Context context, String jsonResponse){

        if(jsonResponse != null) {
            String basePosterPath = context.getString(R.string.image_base_url);
            moviesList = new ArrayList<>();
            Movie movie;
            try {
                JSONObject moviesDetails = new JSONObject(jsonResponse);
                JSONArray moviesListDetails = moviesDetails.getJSONArray(RESULTS_PARAM);
                for (int i = 0; i < moviesListDetails.length(); i++) {
                    movie = new Movie();
                    JSONObject movieDetails = moviesListDetails.getJSONObject(i);
                    movie.setMovieId(movieDetails.getInt(ID_PARAM));
                    movie.setPosterPath(basePosterPath + movieDetails.getString(POSTER_PATH_PARAM));
                    movie.setOriginalTitle(movieDetails.getString(ORIGINAL_TITLE_PARAM));
                    movie.setVoteAverage(movieDetails.getDouble(VOTE_AVERAGE_PARAM));
                    movie.setReleaseDate(movieDetails.getString(RELEASE_DATE_PARAM));
                    movie.setOverview(movieDetails.getString(OVERVIEW_PARAM));
                    moviesList.add(movie);
                }
            }catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void setOriginalTitle(String originalTitle) {
        mOriginalTitle = originalTitle;
    }

    public void setPosterPath(String posterPath) {
        mPosterPath = posterPath;
    }

    public void setVoteAverage(Double voteAverage) {
        mVoteAverage = voteAverage;
    }

    public void setReleaseDate(String releaseDate) {
        mReleaseDate = releaseDate;
    }

    public void setOverview(String overview) {
        mOverview = overview;
    }

    public void setMovieId(int movieId) {
        mMovieId = movieId;
    }

    public String getOriginalTitle() {
        return mOriginalTitle;
    }

    public String getPosterPath() {
        return mPosterPath;
    }

    public Double getVoteAverage() {
        return mVoteAverage;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public String getOverview() {
        return mOverview;
    }

    public int getMovieId() {
        return mMovieId;
    }

    public ArrayList<Movie> getMoviesList(){
        return moviesList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mOriginalTitle);
        dest.writeString(mPosterPath);
        dest.writeDouble(mVoteAverage);
        dest.writeString(mReleaseDate);
        dest.writeString(mOverview);
        dest.writeInt(mMovieId);
        dest.writeTypedList(moviesList);
    }

    private Movie(Parcel in) {
        mOriginalTitle = in.readString();
        mPosterPath = in.readString();
        mVoteAverage = in.readDouble();
        mReleaseDate = in.readString();
        mOverview = in.readString();
        mMovieId = in.readInt();
        moviesList = in.createTypedArrayList(Movie.CREATOR);
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel parcel) {
            return new Movie(parcel);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
