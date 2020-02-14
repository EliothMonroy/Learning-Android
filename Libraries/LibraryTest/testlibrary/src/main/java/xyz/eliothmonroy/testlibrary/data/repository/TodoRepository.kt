package xyz.eliothmonroy.testlibrary.data.repository

import retrofit2.Response
import xyz.eliothmonroy.testlibrary.data.model.Todo

interface TodoRepository {
    suspend fun getTodo(id:Int): Response<Todo>
}