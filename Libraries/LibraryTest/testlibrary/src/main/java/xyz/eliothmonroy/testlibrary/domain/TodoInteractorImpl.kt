package xyz.eliothmonroy.testlibrary.domain

import xyz.eliothmonroy.testlibrary.data.model.Response
import xyz.eliothmonroy.testlibrary.data.repository.TodoRepository
import xyz.eliothmonroy.testlibrary.data.repository.TodoRepositoryImpl

class TodoInteractorImpl : TodoInteractor{

    private val todoRepository: TodoRepository = TodoRepositoryImpl()

    override suspend fun getTodo(id: Int): Response {
        val todoRepo=todoRepository.getTodo(id)
        return Response(todoRepo.code(),todoRepo.body()!!)
    }
}