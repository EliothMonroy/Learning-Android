package xyz.eliothmonroy.gs_test.util.common.data.service

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object BaseServiceBuilder {
    private var retrofitService:Retrofit
    init {
        val gson=GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()
        retrofitService=Retrofit.Builder()
            .baseUrl(ConstantsCommons.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build()
    }

    fun getRetrofitInstance():Retrofit{
        return retrofitService
    }
}