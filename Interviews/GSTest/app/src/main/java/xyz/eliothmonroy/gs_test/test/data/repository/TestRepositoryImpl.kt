package xyz.eliothmonroy.gs_test.test.data.repository

import rx.Observable
import xyz.eliothmonroy.gs_test.test.data.model.LoginRequest
import xyz.eliothmonroy.gs_test.test.data.model.LoginResponse
import xyz.eliothmonroy.gs_test.test.data.service.TestApiService

class TestRepositoryImpl: TestRepository{

    private val testApiService=TestApiService.Builder().build()

    override fun getLoginResponse(request: LoginRequest): Observable<LoginResponse> {
        return testApiService.getLoginResponse(request)
    }
}