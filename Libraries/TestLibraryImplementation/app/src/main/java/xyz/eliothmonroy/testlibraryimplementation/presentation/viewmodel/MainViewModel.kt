package xyz.eliothmonroy.testlibraryimplementation.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import xyz.eliothmonroy.testlibrary.domain.TodoInteractor
import xyz.eliothmonroy.testlibrary.domain.TodoInteractorImpl

class MainViewModel : ViewModel() {

    private val todoInteractor: TodoInteractor = TodoInteractorImpl()

    val todo = liveData(Dispatchers.IO) {
        val retrievedTodo = todoInteractor.getTodo(1)
        emit(retrievedTodo)
    }

}