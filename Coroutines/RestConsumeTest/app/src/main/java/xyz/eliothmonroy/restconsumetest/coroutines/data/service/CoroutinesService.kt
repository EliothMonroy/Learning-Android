package xyz.eliothmonroy.restconsumetest.coroutines.data.service

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import xyz.eliothmonroy.restconsumetest.async.data.model.Todo
import xyz.eliothmonroy.restconsumetest.util.BaseServiceBuilder

interface CoroutinesService {
    @GET("/todos/{id}")
    suspend fun getTodo(@Path(value = "id") todoId: Int): Response<Todo>

    class Builder{
        fun build():CoroutinesService{
            return BaseServiceBuilder.getRetrofitInstance().create(CoroutinesService::class.java)
        }
    }
}