package xyz.eliothmonroy.toppeliculas.utils;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Clase que sirve para crear una instancia de retrofit
 */
public class RetrofitUtils {
    public static Retrofit getInstance() {
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL_MOVIES_API)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }
}
