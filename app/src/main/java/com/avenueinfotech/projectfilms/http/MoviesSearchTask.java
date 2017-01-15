package com.avenueinfotech.projectfilms.http;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.avenueinfotech.projectfilms.model.Movie;

import java.io.IOException;
import java.util.List;

/**
 * Created by suken on 11-01-2017.
 */

public class MoviesSearchTask extends AsyncTaskLoader<List<Movie>> {

    List<Movie> mMovies;
    String mQuery;


    public MoviesSearchTask(Context context, String q) {
        super(context);
        mQuery = q;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        if(mMovies == null) {
//            Log.d("NGVL", "forceloaded");
            forceLoad();
        } else {
//            Log.d("NGVL", "deliver Result");
            deliverResult(mMovies);
        }
    }

    @Override
    public List<Movie> loadInBackground() {
        try {
            mMovies = MoviesParser.searchByTitle(mQuery);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mMovies;
    }
}
