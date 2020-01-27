package xyz.eliothmonroy.retrofitdemo.reto1.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface API {
    @GET("api.php")
    //action=query&prop=extracts&format=json&exintro=&titles=
    fun getValueApiWiki(@Query("action") action:String=CONSTANTS.API_ACTION,@Query("prop") prop:String=CONSTANTS.API_PROP, @Query("format") format:String=CONSTANTS.API_FORMAT, @Query("exintro") exintro:String=CONSTANTS.API_EXINTRO, @Query("titles") titles:String): Call<Any>
}