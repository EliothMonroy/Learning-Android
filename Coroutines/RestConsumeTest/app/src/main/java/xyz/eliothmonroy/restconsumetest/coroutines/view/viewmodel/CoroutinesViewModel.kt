package xyz.eliothmonroy.restconsumetest.coroutines.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import xyz.eliothmonroy.restconsumetest.coroutines.domain.CoroutinesInteractor
import xyz.eliothmonroy.restconsumetest.coroutines.domain.CoroutinesInteractorImpl

class CoroutinesViewModel:ViewModel(){
    private val coroutinesInteractor:CoroutinesInteractor=CoroutinesInteractorImpl()


    init {
        viewModelScope.launch {
            
        }
    }

    val getTodo =
        liveData(Dispatchers.IO) {
        val retrivedTodo = coroutinesInteractor.getTodo(1)

        emit(retrivedTodo)
    }

}

