package com.avenueinfotech.projectfilms;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.avenueinfotech.projectfilms.http.MoviesByIdTask;
import com.avenueinfotech.projectfilms.model.Movie;
import com.squareup.picasso.Picasso;

public class DetailMovieActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Movie> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        String mImdbId = getIntent().getStringExtra("imdbId");

        getSupportLoaderManager().initLoader(1, getIntent().getExtras(), this);
    }

    @Override
    public Loader<Movie> onCreateLoader(int id, Bundle args) {
        String imdbId = args.getString("imdbId");
        return new MoviesByIdTask(this, imdbId);
    }

    @Override
    public void onLoadFinished(Loader<Movie> loader, Movie data) {
        if (data != null){
            ImageView imageView = (ImageView)findViewById(R.id.image_poster);
            TextView txtTitle = (TextView)findViewById(R.id.text_title);
            TextView txtYear = (TextView)findViewById(R.id.text_year);
            TextView txtPlot = (TextView)findViewById(R.id.text_plot);
            TextView txtGenre = (TextView)findViewById(R.id.text_genre);
            TextView txtDirector = (TextView)findViewById(R.id.text_director);
            TextView txtRuntime = (TextView)findViewById(R.id.text_runtime);
            TextView txtActors = (TextView)findViewById(R.id.text_actors);

            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
            appBarLayout.setTitle(data.title);
            Picasso.with(this).load(data.poster).into(imageView);
            txtTitle.setText(data.title);
            txtYear.setText(data.year);
            txtPlot.setText(data.plot);
            txtGenre.setText(data.genre);
            txtDirector.setText(data.director);
            txtRuntime.setText(data.runtime);
            txtActors.setText(data.actors);

        }else {
            Toast.makeText(this, "Deu paul", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onLoaderReset(Loader<Movie> loader) {

    }
}
