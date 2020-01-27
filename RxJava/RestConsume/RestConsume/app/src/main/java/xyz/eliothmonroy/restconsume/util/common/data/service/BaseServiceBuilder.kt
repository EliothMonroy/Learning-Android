package xyz.eliothmonroy.restconsume.util.common.data.service

import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object BaseServiceBuilder {
    private var retrofitService:Retrofit = Retrofit.Builder()
        .baseUrl(ConstantsCommons.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .build()

    fun getRetrofitInstance():Retrofit{
        return retrofitService
    }
}