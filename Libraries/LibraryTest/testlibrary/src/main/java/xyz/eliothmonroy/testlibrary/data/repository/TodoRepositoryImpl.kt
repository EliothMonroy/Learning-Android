package xyz.eliothmonroy.testlibrary.data.repository

import retrofit2.Response
import xyz.eliothmonroy.testlibrary.data.model.Todo
import xyz.eliothmonroy.testlibrary.data.service.TodoApiService

class TodoRepositoryImpl : TodoRepository {

    private val todoApiService = TodoApiService.Builder().build()

    override suspend fun getTodo(id: Int): Response<Todo> {
        return todoApiService.getTodo(id)
    }
}