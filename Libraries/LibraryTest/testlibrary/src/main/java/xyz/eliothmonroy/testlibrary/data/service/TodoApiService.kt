package xyz.eliothmonroy.testlibrary.data.service

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import xyz.eliothmonroy.testlibrary.data.model.Todo
import xyz.eliothmonroy.testlibrary.util.BaseServiceBuilder

interface TodoApiService{
    @GET("/todos/{id}")
    fun getTodo(@Path(value = "id") todoId: Int): Response<Todo>

    class Builder{
        fun build():TodoApiService{
            return BaseServiceBuilder.getRetrofitInstance().create(TodoApiService::class.java)
        }
    }
}