package xyz.eliothmonroy.restconsumetest.async.data.service

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import xyz.eliothmonroy.restconsumetest.async.data.model.Todo
import xyz.eliothmonroy.restconsumetest.util.BaseServiceBuilder

interface AsyncService {
    @GET("/todos/{id}")
    fun getTodo(@Path(value = "id") todoId: Int): Call<Todo>

    class Builder{
        fun build():AsyncService{
            return BaseServiceBuilder.getRetrofitInstance().create(AsyncService::class.java)
        }
    }

}