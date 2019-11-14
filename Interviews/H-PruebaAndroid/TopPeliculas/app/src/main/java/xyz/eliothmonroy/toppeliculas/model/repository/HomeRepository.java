package xyz.eliothmonroy.toppeliculas.model.repository;

import java.util.ArrayList;

import xyz.eliothmonroy.toppeliculas.model.pojos.Movie;

/**
 * Interfaz que indica las acciones disponibles para el repositorio de Home
 */
public interface HomeRepository {
    void getMovies();
    ArrayList<Movie> getTopTenRatedMovies(ArrayList<Movie> movies);
}
