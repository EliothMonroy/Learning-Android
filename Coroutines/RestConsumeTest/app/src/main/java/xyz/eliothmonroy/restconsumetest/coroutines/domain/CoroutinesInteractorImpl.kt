package xyz.eliothmonroy.restconsumetest.coroutines.domain

import xyz.eliothmonroy.restconsumetest.async.data.model.Todo
import xyz.eliothmonroy.restconsumetest.coroutines.data.repository.CoroutinesRepository
import xyz.eliothmonroy.restconsumetest.coroutines.data.repository.CoroutinesRepositoryImpl

class CoroutinesInteractorImpl : CoroutinesInteractor {

    private val coroutinesRepository: CoroutinesRepository=CoroutinesRepositoryImpl()

    override suspend fun getTodo(id: Int): Todo {
        return coroutinesRepository.getTodo(id)
    }
}