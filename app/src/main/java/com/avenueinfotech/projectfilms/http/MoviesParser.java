package com.avenueinfotech.projectfilms.http;

import com.avenueinfotech.projectfilms.model.Movie;
import com.avenueinfotech.projectfilms.model.MovieSearchResult;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by suken on 11-01-2017.
 */

public class MoviesParser {

    public static final String URL_SEARCH = "http://www.omdbapi.com/?s=%s&r=json";
    public static final String URL_BY_ID = "http://www.omdbapi.com/?i=%s&plot=full&r=json";

    public static List<Movie> searchByTitle(String q) throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(String.format(URL_SEARCH, q)).build();

//        Response response = client.newCall(request).execute();
        Response response = client.newCall(request).execute();
        if (response.networkResponse().code() == HttpURLConnection.HTTP_OK) {
            String json = response.body().string();

            Gson gson = new Gson();
            MovieSearchResult result = gson.fromJson(json, MovieSearchResult.class);
            if (result != null) {
                return result.movies;
            }

        }
        return null;
    }

    public static Movie searchbyImdbId(String id) throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(String.format(URL_BY_ID, id)).build();

//        Response response = client.newCall(request).execute();
        Response response = client.newCall(request).execute();
        if (response.networkResponse().code() == HttpURLConnection.HTTP_OK) {
            String json = response.body().string();

            Gson gson = new Gson();
            Movie result = gson.fromJson(json, Movie.class);
            return result;
        }
        return null;

    }
}