package xyz.eliothmonroy.toppeliculas.view;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.eliothmonroy.toppeliculas.R;
import xyz.eliothmonroy.toppeliculas.model.pojos.Movie;
import xyz.eliothmonroy.toppeliculas.presenter.HomePresenter;
import xyz.eliothmonroy.toppeliculas.presenter.HomePresenterImpl;
import xyz.eliothmonroy.toppeliculas.utils.Functions;

/**
 * Activity principal de la aplicación
 * Muestra en forma de carrousel de imagenes el top 10 de películas del sitio themoviedb.org
 */
public class HomeActivity extends AppCompatActivity implements HomeView {
    
    private HomePresenter homePresenter;
    private ArrayList<Movie> movies;
    MovieAdapter movieAdapter;
    int heightScreenDp;
    @BindView(R.id.recyclerMovies)
    RecyclerView recyclerViewMovies;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.textViewNoInternet)
    TextView textViewNoInternet;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        showProgressBar();
        textViewNoInternet.setVisibility(View.GONE);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerViewMovies.setLayoutManager(linearLayoutManager);
        getScreenSize();
        homePresenter = new HomePresenterImpl(this);
        
        //Verificamos si el activity viene de un cambio de orientación
        if (savedInstanceState != null) {
            ArrayList<Movie> moviesBundle = savedInstanceState.getParcelableArrayList("movies");
            if (moviesBundle != null) {
                hideProgressBar();
                setMovies(moviesBundle);
            } else {
                getMovies();
            }
        } else {
            getMovies();
        }
    }
    
    
    /**
     * Función que se ejecuta al cambiar de orientación el teléfono, nos permite guardar información
     * en este caso, el listado de películas para no tener que volver a ir por ellas
     * @param outState Bundle donde se almacena el listado de películas
     */
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("movies", movies);
    }
    
    /**
     * Función que obtiene el alto de la pantalla en dps
     * para posteriormente calcular un tamaño adecuado para la imagen
     */
    @Override
    public void getScreenSize() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int heightScreenPixels = displayMetrics.heightPixels;
        heightScreenDp = Functions.pxToDp(heightScreenPixels);
    }
    
    /**
     * Función que muestra el progressbar en pantalla al momento de obtener las películas
     */
    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }
    
    /**
     * Función que oculta el progressbar una vez que se obtuvieron las películas
     */
    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }
    
    /**
     * Función que muestra un mensaje al usuario indicandole que no se puede acceder a internet
     */
    @Override
    public void showNoInternetConnection() {
        hideProgressBar();
        textViewNoInternet.setVisibility(View.VISIBLE);
    }
    
    /**
     * Función que solicita al presenter que obtenga el listado de películas para mostrar
     * al usuario
     */
    @Override
    public void getMovies() {
        homePresenter.getMovies();
    }
    
    /**
     * Función que proporciona información al recycler view para que muestre el listado de películas
     * en pantalla
     * @param movies Listado de películas top
     */
    @Override
    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
        movieAdapter = new MovieAdapter(this.movies, R.layout.cardview_movie, this, heightScreenDp);
        recyclerViewMovies.setAdapter(movieAdapter);
        recyclerViewMovies.setVisibility(View.VISIBLE);
    }
    
}
