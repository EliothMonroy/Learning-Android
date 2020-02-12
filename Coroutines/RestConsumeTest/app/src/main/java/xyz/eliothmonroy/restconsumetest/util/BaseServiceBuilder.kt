package xyz.eliothmonroy.restconsumetest.util

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object BaseServiceBuilder {
    private var retrofitService: Retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        //.addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .build()

    fun getRetrofitInstance():Retrofit{
        return retrofitService
    }
}