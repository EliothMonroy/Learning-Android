package xyz.eliothmonroy.restconsume.coroutines.domain

import kotlinx.coroutines.flow.Flow
import xyz.eliothmonroy.gs_test.test.data.model.LoginResponse
import xyz.eliothmonroy.restconsume.coroutines.data.model.LoginRequest
import xyz.eliothmonroy.restconsume.coroutines.data.repository.CoroutinesRepository
import xyz.eliothmonroy.restconsume.coroutines.data.repository.CoroutinesRepositoryImpl

class CoroutinesInteractorImpl:CoroutinesInteractor {

    private val coroutinesRepository:CoroutinesRepository=CoroutinesRepositoryImpl()

    override fun getLoginResponse(email: String, password: String): Flow<LoginResponse> {
        return coroutinesRepository.getLoginResponse(LoginRequest(email, password))
    }


}