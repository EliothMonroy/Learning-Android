package xyz.eliothmonroy.restconsumetest.coroutines.data.repository

import androidx.lifecycle.LiveData
import xyz.eliothmonroy.restconsumetest.async.data.model.Todo

interface CoroutinesRepository {
    suspend fun getTodo(id:Int): Todo
}