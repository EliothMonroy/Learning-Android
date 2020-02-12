package xyz.eliothmonroy.restconsumetest.coroutines.view.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import xyz.eliothmonroy.restconsumetest.coroutines.domain.CoroutinesInteractor
import xyz.eliothmonroy.restconsumetest.coroutines.domain.CoroutinesInteractorImpl

class CoroutinesViewModel:ViewModel(){
    private val coroutinesInteractor:CoroutinesInteractor=CoroutinesInteractorImpl()

    val getTodo =
        liveData(Dispatchers.IO) {
        val retrievedTodo = coroutinesInteractor.getTodo(1)

        emit(retrievedTodo)
    }

}

