package xyz.eliothmonroy.testlibrary.util

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object BaseServiceBuilder {
    private var retrofitService: Retrofit = Retrofit.Builder()
        .baseUrl(CommonConstants.BASE_URL_LOGIN)
        .addConverterFactory(GsonConverterFactory.create())
        //.addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .build()

    fun getRetrofitInstance(): Retrofit {
        return retrofitService
    }

}