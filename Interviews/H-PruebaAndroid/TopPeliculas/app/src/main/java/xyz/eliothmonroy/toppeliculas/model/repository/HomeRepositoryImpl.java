package xyz.eliothmonroy.toppeliculas.model.repository;

import android.util.Log;

import java.net.HttpURLConnection;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xyz.eliothmonroy.toppeliculas.App;
import xyz.eliothmonroy.toppeliculas.model.pojos.Movie;
import xyz.eliothmonroy.toppeliculas.model.pojos.Result;
import xyz.eliothmonroy.toppeliculas.presenter.HomePresenter;


/**
 * Fuente de datos primaria de la app
 * Nos permite consultar el top 20 de películas en themoviedb.org
 */
public class HomeRepositoryImpl implements HomeRepository {
    
    private final String TAG = HomeRepositoryImpl.class.getSimpleName();
    private RestClient restClient;
    private HomePresenter homePresenter;
    private Result result;
    private ArrayList<Movie> movies;
    
    public HomeRepositoryImpl(HomePresenter homePresenter) {
        this.homePresenter = homePresenter;
        restClient = App.getRestClientInstance();
    }
    
    /**
     * Método que obtiene las top 20 rated películas del sitio
     */
    @Override
    public void getMovies() {
        Call<Result> call = restClient.getMovies();
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                switch (response.code()) {
                    case HttpURLConnection.HTTP_OK:
                        result = response.body();
                        movies = result.getMovies();
                        movies = getTopTenRatedMovies(movies);
                        homePresenter.setMovies(movies);
                        break;
                    default:
                        break;
                }
            }
            
            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Log.e(TAG, "error: " + t.toString());
            }
        });
    }
    
    /**
     * Función que genera una nueva lista con el top 10 de películas
     * @param movies Lista de películas que el api devuelve, son 20 películas
     * @return El top 10 de pelícuñas
     */
    @Override
    public ArrayList<Movie> getTopTenRatedMovies(ArrayList<Movie> movies) {
        return new ArrayList<Movie>(movies.subList(0, 10));
    }
}