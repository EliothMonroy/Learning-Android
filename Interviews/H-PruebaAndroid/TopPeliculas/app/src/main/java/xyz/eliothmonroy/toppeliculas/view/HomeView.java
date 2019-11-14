package xyz.eliothmonroy.toppeliculas.view;

import java.util.ArrayList;

import xyz.eliothmonroy.toppeliculas.model.pojos.Movie;

/**
 * Interfaz que indica la funcionalidad del HomeActivity
 */
public interface HomeView {
    void getMovies();
    
    void setMovies(ArrayList<Movie> movies);
    
    void getScreenSize();
    
    void showProgressBar();
    
    void hideProgressBar();
    
    void showNoInternetConnection();
}
