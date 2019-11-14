package xyz.eliothmonroy.toppeliculas.view;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.eliothmonroy.toppeliculas.R;
import xyz.eliothmonroy.toppeliculas.model.pojos.Movie;
import xyz.eliothmonroy.toppeliculas.utils.Constants;
import xyz.eliothmonroy.toppeliculas.utils.Functions;

/**
 * Clase que sirve como Adapter para el recyclerview de películas
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    
    private ArrayList<Movie> movies;
    private int resource;
    private Activity activity;
    private int screenSize;
    
    MovieAdapter(ArrayList<Movie> movies, int resource, Activity activity, int screenSize) {
        this.movies = movies;
        this.resource = resource;
        this.activity = activity;
        this.screenSize = screenSize;
    }
    
    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);
        return new MovieViewHolder(view);
    }
    
    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = movies.get(position);
        holder.movieTitle.setText(activity.getString(R.string.cardview_movie_title_placeholder, position + 1, movie.getTitle()));
        holder.movieRate.setText(activity.getString(R.string.cardview_movie_rate_placeholder, movie.getVote_average()));
        
        Picasso.get().load(Constants.BASE_URL_IMAGES_MOVIES_API + movie.getPoster_path()).into(holder.movieImage);
        holder.movieImage.setOnClickListener(v -> {
            
            Intent intent = new Intent(activity, DetailActivity.class);
            intent.putExtra("movie", movie);
            activity.startActivity(intent);
            
        });
    }
    
    @Override
    public int getItemCount() {
        return movies.size();
    }
    
    /**
     * Clase que representa a cada uno de los elementos contenidos en el recyclerview
     */
    class MovieViewHolder extends RecyclerView.ViewHolder {
        
        @BindView(R.id.cardviewMovieImage)
        ImageView movieImage;
        @BindView(R.id.cardviewMovieTitle)
        TextView movieTitle;
        @BindView(R.id.cardviewMovieRate)
        TextView movieRate;
        
        MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.getLayoutParams().height = Functions.calculateHeight(screenSize);
            itemView.getLayoutParams().width = Functions.calculateWidth(screenSize);
            ButterKnife.bind(this, itemView);
        }
    }
}
