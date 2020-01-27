package xyz.eliothmonroy.sesion7.reto1.utils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtils {

    public static Retrofit getRetrofitInstance(){
        return new Retrofit.Builder().baseUrl(Constants.BASE_URL_API_USERS)
                .addConverterFactory(GsonConverterFactory.create()).build();
    }

}
