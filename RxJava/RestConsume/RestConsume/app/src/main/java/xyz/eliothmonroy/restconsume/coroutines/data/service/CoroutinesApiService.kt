package xyz.eliothmonroy.restconsume.coroutines.data.service

import kotlinx.coroutines.flow.Flow
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import xyz.eliothmonroy.gs_test.test.data.model.LoginResponse
import xyz.eliothmonroy.restconsume.coroutines.data.model.LoginRequest
import xyz.eliothmonroy.restconsume.util.common.data.service.BaseServiceBuilder

interface CoroutinesApiService {
    @Headers("Content-Type:text/plain")
    @POST("login")
    fun getLoginResponse(@Body request:LoginRequest):Flow<LoginResponse>

    class Builder{
        fun build():CoroutinesApiService{
            return BaseServiceBuilder.getRetrofitInstance().create(CoroutinesApiService::class.java)
        }
    }
}