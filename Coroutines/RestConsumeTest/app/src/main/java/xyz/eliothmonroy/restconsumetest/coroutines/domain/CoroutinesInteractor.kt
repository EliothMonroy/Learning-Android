package xyz.eliothmonroy.restconsumetest.coroutines.domain

import xyz.eliothmonroy.restconsumetest.async.data.model.Todo

interface CoroutinesInteractor {
    suspend fun getTodo(id:Int): Todo
}