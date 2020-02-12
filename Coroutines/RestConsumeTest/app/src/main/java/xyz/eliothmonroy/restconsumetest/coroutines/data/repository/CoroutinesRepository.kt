package xyz.eliothmonroy.restconsumetest.coroutines.data.repository

import androidx.lifecycle.LiveData
import retrofit2.Response
import xyz.eliothmonroy.restconsumetest.async.data.model.Todo

interface CoroutinesRepository {
    suspend fun getTodo(id:Int): Response<Todo>
}