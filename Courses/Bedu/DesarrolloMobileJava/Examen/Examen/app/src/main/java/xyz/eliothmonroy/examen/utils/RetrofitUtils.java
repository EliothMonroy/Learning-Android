package xyz.eliothmonroy.examen.utils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtils {
    public static Retrofit getRetrofitInstance(){
        return new Retrofit.Builder().baseUrl(CONSTANTS.BASE_URL_API)
                .addConverterFactory(GsonConverterFactory.create()).build();
    }
}
