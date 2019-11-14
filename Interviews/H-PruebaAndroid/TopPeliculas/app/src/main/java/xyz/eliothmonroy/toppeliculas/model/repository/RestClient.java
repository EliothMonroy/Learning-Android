package xyz.eliothmonroy.toppeliculas.model.repository;

import retrofit2.Call;
import retrofit2.http.GET;
import xyz.eliothmonroy.toppeliculas.model.pojos.Result;
import xyz.eliothmonroy.toppeliculas.utils.Constants;

/**
 * Interface donde indicamos el verbo http y el endpoint
 * para retrofit
 */
public interface RestClient {
    @GET("top_rated?api_key=" + Constants.API_KEY + "&language=es-MX&page=1")
    Call<Result> getMovies();
}
