package xyz.eliothmonroy.restconsume.coroutines.data.repository

import kotlinx.coroutines.flow.Flow
import xyz.eliothmonroy.gs_test.test.data.model.LoginResponse
import xyz.eliothmonroy.restconsume.coroutines.data.model.LoginRequest
import xyz.eliothmonroy.restconsume.coroutines.data.service.CoroutinesApiService

class CoroutinesRepositoryImpl: CoroutinesRepository {

    private val coroutinesApiService=CoroutinesApiService.Builder().build()

    override fun getLoginResponse(request: LoginRequest): Flow<LoginResponse>{
        return coroutinesApiService.getLoginResponse(request)
    }
}