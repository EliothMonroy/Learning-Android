package xyz.eliothmonroy.restconsumetest.async.domain

import androidx.lifecycle.LiveData
import xyz.eliothmonroy.restconsumetest.async.data.model.Todo
import xyz.eliothmonroy.restconsumetest.async.data.repository.AsyncRepository
import xyz.eliothmonroy.restconsumetest.async.data.repository.AsyncRepositoryImpl

class AsyncInteractorImpl: AsyncInteractor {
    private val asyncRepository:AsyncRepository=AsyncRepositoryImpl()

    override fun getTodo(id: Int): LiveData<Todo> {
        return asyncRepository.getTodo(id)
    }


}