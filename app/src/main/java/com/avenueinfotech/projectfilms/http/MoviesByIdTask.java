package com.avenueinfotech.projectfilms.http;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.avenueinfotech.projectfilms.model.Movie;

import java.io.IOException;

/**
 * Created by suken on 11-01-2017.
 */

public class MoviesByIdTask extends AsyncTaskLoader<Movie> {

    Movie mMovie;
    String mImdbId;


    public MoviesByIdTask(Context context, String id) {
        super(context);
        mImdbId = id;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        if(mImdbId == null) return;
        if(mMovie == null) {
//            Log.d("NGVL", "forceloaded");
            forceLoad();
        } else {
//            Log.d("NGVL", "deliver Result");
            deliverResult(mMovie);
        }
    }

    @Override
    public Movie loadInBackground() {
        try {
            mMovie = MoviesParser.searchbyImdbId(mImdbId);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mMovie;
    }
}
