package xyz.eliothmonroy.restconsume.coroutines.data.repository

import kotlinx.coroutines.flow.Flow
import xyz.eliothmonroy.gs_test.test.data.model.LoginResponse
import xyz.eliothmonroy.restconsume.coroutines.data.model.LoginRequest

interface CoroutinesRepository {
    fun getLoginResponse(request: LoginRequest): Flow<LoginResponse>
}