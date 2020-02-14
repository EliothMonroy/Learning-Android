package xyz.eliothmonroy.testlibrary.domain

import retrofit2.Response
import xyz.eliothmonroy.testlibrary.data.model.Todo

interface TodoInteractor {
    suspend fun getTodo(id:Int): Response<Todo>
}