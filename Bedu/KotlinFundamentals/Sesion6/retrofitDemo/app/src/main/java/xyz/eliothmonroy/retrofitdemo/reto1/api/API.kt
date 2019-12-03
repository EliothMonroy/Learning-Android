package xyz.eliothmonroy.retrofitdemo.reto1.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import xyz.eliothmonroy.retrofitdemo.reto1.data.Resultado

interface API {
    @GET("api.php")
    //actions=query&prop=extracts&format=json&exintro=&titles=
    fun getValueApiWiki(@Query("action") action:String,@Query("prop") prop:String, @Query("format") format:String, @Query("exintro") exintro:String, @Query("titles") titles:String): Call<Any>
}