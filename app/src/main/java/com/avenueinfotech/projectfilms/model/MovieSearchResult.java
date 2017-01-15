package com.avenueinfotech.projectfilms.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by suken on 11-01-2017.
 */

public class MovieSearchResult {
    @SerializedName("Search")
    public List<Movie> movies;
}
