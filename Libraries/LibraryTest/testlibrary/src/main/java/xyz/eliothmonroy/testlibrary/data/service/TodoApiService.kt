package xyz.eliothmonroy.testlibrary.data.service

import retrofit2.Response
import retrofit2.http.*
import xyz.eliothmonroy.testlibrary.data.model.LoginRequest
import xyz.eliothmonroy.testlibrary.data.model.LoginResponse
import xyz.eliothmonroy.testlibrary.data.model.Todo
import xyz.eliothmonroy.testlibrary.util.BaseServiceBuilder

interface TodoApiService{
    @GET("/todos/{id}")
    suspend fun getTodo(@Path(value = "id") todoId: Int): Response<Todo>

    @Headers("Content-Type:text/plain")
    @POST("login")
    suspend fun login(@Body request: LoginRequest): LoginResponse

    class Builder{
        fun build():TodoApiService{
            return BaseServiceBuilder.getRetrofitInstance().create(TodoApiService::class.java)
        }
    }
}