package xyz.eliothmonroy.restconsumetest.async.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import xyz.eliothmonroy.restconsumetest.async.data.model.Todo
import xyz.eliothmonroy.restconsumetest.async.domain.AsyncInteractor
import xyz.eliothmonroy.restconsumetest.async.domain.AsyncInteractorImpl

class AsyncViewModel : ViewModel() {
    private val asyncInteractor:AsyncInteractor=AsyncInteractorImpl()

    var test=1

    fun getTodo(): LiveData<Todo>{
        return asyncInteractor.getTodo(1)
    }

}