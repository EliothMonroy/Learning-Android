package xyz.eliothmonroy.toppeliculas.view;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.eliothmonroy.toppeliculas.R;
import xyz.eliothmonroy.toppeliculas.model.pojos.Movie;
import xyz.eliothmonroy.toppeliculas.utils.Constants;
import xyz.eliothmonroy.toppeliculas.utils.Functions;

/**
 * Activity que muestra el detalle de una película seleccionada
 */
public class DetailActivity extends AppCompatActivity implements DetailView {
    
    Movie movie;
    int heightScreenDp;
    @BindView(R.id.detailMovieImage)
    ImageView movieImage;
    @BindView(R.id.detailMovieTitle)
    TextView movieTitle;
    @BindView(R.id.detailMovieRate)
    TextView movieRate;
    @BindView(R.id.detailMovieOverview)
    TextView movieOverview;
    @BindView(R.id.detailMovieReleaseDate)
    TextView movieReleaseDate;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ButterKnife.bind(this);
        getScreenSize();
        if (getIntent().getExtras() != null) {
            movie = getIntent().getParcelableExtra("movie");
            setMovieInfo();
        }
    }
    
    /**
     * Función que obtiene el alto de la pantalla en dps
     * para posteriormente calcular un tamaño adecuado para la imagen
     */
    private void getScreenSize() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int heightScreenPixels = displayMetrics.heightPixels;
        heightScreenDp = Functions.pxToDp(heightScreenPixels);
    }
    
    /**
     * Función que muestra la información de la película seleccionada en pantalla
     */
    @Override
    public void setMovieInfo() {
        getSupportActionBar().setTitle(movie.getTitle());
        movieImage.getLayoutParams().height = Functions.calculateHeightDetail(heightScreenDp);
        movieImage.getLayoutParams().width = Functions.calculateWidthDetail(heightScreenDp);
        movieTitle.setText(movie.getTitle());
        movieRate.setText(getString(R.string.movie_rate_placeholder, movie.getVote_average()));
        movieOverview.setText(getString(R.string.movie_overview_placeholder, movie.getOverview()));
        movieReleaseDate.setText(getString(R.string.movie_release_date_placeholder, movie.getRelease_date()));
        Picasso.get().load(Constants.BASE_URL_IMAGES_MOVIES_API + movie.getPoster_path()).into(movieImage);
    }
}
