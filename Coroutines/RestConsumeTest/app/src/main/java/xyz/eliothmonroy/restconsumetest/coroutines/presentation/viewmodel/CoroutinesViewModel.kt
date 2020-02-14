package xyz.eliothmonroy.restconsumetest.coroutines.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import xyz.eliothmonroy.restconsumetest.async.data.model.Todo
import xyz.eliothmonroy.restconsumetest.coroutines.domain.CoroutinesInteractor
import xyz.eliothmonroy.restconsumetest.coroutines.domain.CoroutinesInteractorImpl

class CoroutinesViewModel:ViewModel(){
    private val coroutinesInteractor:CoroutinesInteractor=CoroutinesInteractorImpl()

    var response: LiveData<Response<Todo>> = MutableLiveData<Response<Todo>>()

    fun getTodo2(): LiveData<Response<Todo>> {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                //response = coroutinesInteractor.getTodo(201).
                (response as MutableLiveData).postValue(coroutinesInteractor.getTodo(201))
            } catch (e: Exception) {
                Log.e("Exception", "${e.message}")
            }
        }
        return response
    }



    val todo = liveData(Dispatchers.IO) {
        val retrievedTodo = coroutinesInteractor.getTodo(1)
        emit(retrievedTodo)
    }

}

