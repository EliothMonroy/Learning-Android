package xyz.eliothmonroy.examen.model.api

import retrofit2.Call
import retrofit2.http.GET
import xyz.eliothmonroy.examen.model.data.Usuario

interface API {
    @GET("banco")
    fun getUsuarios():Call<List<Usuario>>
}