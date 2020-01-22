package xyz.eliothmonroy.gs_test.test.data.service

import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import rx.Observable
import xyz.eliothmonroy.gs_test.test.data.model.LoginRequest
import xyz.eliothmonroy.gs_test.test.data.model.LoginResponse
import xyz.eliothmonroy.gs_test.util.common.data.service.BaseServiceBuilder

interface TestApiService {

    @Headers("Content-Type:text/plain")
    @POST("login")
    fun getLoginResponse(@Body request:LoginRequest):Observable<LoginResponse>

    class Builder{
        fun build():TestApiService{
            return BaseServiceBuilder.getRetrofitInstance().create(TestApiService::class.java)
        }
    }
}