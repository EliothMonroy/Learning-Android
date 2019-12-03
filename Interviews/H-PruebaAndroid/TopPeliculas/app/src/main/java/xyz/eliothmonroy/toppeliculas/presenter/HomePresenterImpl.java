package xyz.eliothmonroy.toppeliculas.presenter;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.ArrayList;

import xyz.eliothmonroy.toppeliculas.model.pojos.Movie;
import xyz.eliothmonroy.toppeliculas.model.repository.HomeRepository;
import xyz.eliothmonroy.toppeliculas.model.repository.HomeRepositoryImpl;
import xyz.eliothmonroy.toppeliculas.view.HomeActivity;
import xyz.eliothmonroy.toppeliculas.view.HomeView;

/**
 * Clase que conecta la vista con el modelo
 */
public class HomePresenterImpl implements HomePresenter {
    
    private HomeRepository homeRepository;
    private HomeView homeView;
    
    public HomePresenterImpl(HomeView homeView) {
        this.homeView = homeView;
        homeRepository = new HomeRepositoryImpl(this);
    }
    
    /**
     * Función que indica al repositorio que debe obtener la lista del top de películas
     */
    public void getMovies() {
        if (isNetworkAvailable()) {
            homeRepository.getMovies();
        } else {
            homeView.showNoInternetConnection();
        }
    }
    
    
    /**
     * Función que verifica si el teléfono tiene wifi o datos encendidos
     * @return Verdadero o falso dependiendo si existe un provedor de internet activo
     */
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) ((HomeActivity)homeView).getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }
    
    /**
     *
     * Función que notifica a la vista que es posible mostrar el listado en pantalla
     * @param movies Lista de películas que fueron devueltas por el repositorio
     *               y deben ser presentadas en pantalla al usuario
     */
    @Override
    public void setMovies(ArrayList<Movie> movies) {
        homeView.hideProgressBar();
        homeView.setMovies(movies);
    }
}
