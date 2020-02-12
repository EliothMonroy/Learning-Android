package xyz.eliothmonroy.restconsumetest.coroutines.data.repository

import androidx.lifecycle.LiveData
import xyz.eliothmonroy.restconsumetest.async.data.model.Todo
import xyz.eliothmonroy.restconsumetest.coroutines.data.service.CoroutinesService

class CoroutinesRepositoryImpl : CoroutinesRepository{

    private val coroutinesService=CoroutinesService.Builder().build()

    override suspend fun getTodo(id: Int): Todo {
        return coroutinesService.getTodo(id)
    }
}