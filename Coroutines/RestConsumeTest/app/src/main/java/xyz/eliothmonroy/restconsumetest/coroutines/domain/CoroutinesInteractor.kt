package xyz.eliothmonroy.restconsumetest.coroutines.domain

import retrofit2.Response
import xyz.eliothmonroy.restconsumetest.async.data.model.Todo

interface CoroutinesInteractor {
    suspend fun getTodo(id:Int): Response<Todo>
}