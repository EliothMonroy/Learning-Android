package xyz.eliothmonroy.restconsumetest.async.domain

import androidx.lifecycle.LiveData
import xyz.eliothmonroy.restconsumetest.async.data.model.Todo

interface AsyncInteractor {
    fun getTodo(id:Int): LiveData<Todo>
}