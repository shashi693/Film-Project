package com.avenueinfotech.projectfilms.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.avenueinfotech.projectfilms.R;
import com.avenueinfotech.projectfilms.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by suken on 11-01-2017.
 */

public class MoviesAdapter extends ArrayAdapter<Movie>{

    public MoviesAdapter(Context context, List<Movie> movies) {
        super(context, 0, movies);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Movie movie = getItem(position);

        if (convertView == null){
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.item_movie, parent, false);
        }
        ImageView imgPoster = (ImageView)convertView.findViewById(R.id.item_movie_poster);
        TextView txtTitle = (TextView)convertView.findViewById(R.id.item_movie_title);
        TextView txtYear = (TextView)convertView.findViewById(R.id.item_movie_year);

        Picasso.with(getContext())
                .load(movie.poster)
                .into(imgPoster);

        txtTitle.setText(movie.title);
        txtYear.setText(movie.year);

        return convertView;
    }
}
