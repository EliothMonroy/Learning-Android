package xyz.eliothmonroy.toppeliculas.presenter;

import java.util.ArrayList;

import xyz.eliothmonroy.toppeliculas.model.pojos.Movie;

/**
 * Interface que indica la funcionalidad disponible del presentador de Home
 */
public interface HomePresenter {
    void getMovies();
    void setMovies(ArrayList<Movie> movies);
}
