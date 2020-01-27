package xyz.eliothmonroy.restconsume.coroutines.domain

import kotlinx.coroutines.flow.Flow
import xyz.eliothmonroy.gs_test.test.data.model.LoginResponse

interface CoroutinesInteractor {
    fun getLoginResponse(email:String, password:String): Flow<LoginResponse>
}