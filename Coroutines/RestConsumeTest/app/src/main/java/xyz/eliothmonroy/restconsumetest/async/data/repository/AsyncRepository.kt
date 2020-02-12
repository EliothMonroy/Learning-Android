package xyz.eliothmonroy.restconsumetest.async.data.repository

import androidx.lifecycle.LiveData
import xyz.eliothmonroy.restconsumetest.async.data.model.Todo

interface AsyncRepository {
    fun getTodo(id:Int): LiveData<Todo>
}