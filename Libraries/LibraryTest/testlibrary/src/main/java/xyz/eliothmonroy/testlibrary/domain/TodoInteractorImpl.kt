package xyz.eliothmonroy.testlibrary.domain

import retrofit2.Response
import xyz.eliothmonroy.testlibrary.data.model.Todo
import xyz.eliothmonroy.testlibrary.data.repository.TodoRepository
import xyz.eliothmonroy.testlibrary.data.repository.TodoRepositoryImpl

class TodoInteractorImpl : TodoInteractor{

    private val todoRepository: TodoRepository = TodoRepositoryImpl()

    override suspend fun getTodo(id: Int): Response<Todo> {
        return todoRepository.getTodo(id)
    }
}