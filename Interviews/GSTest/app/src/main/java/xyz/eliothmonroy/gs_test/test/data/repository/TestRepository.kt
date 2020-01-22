package xyz.eliothmonroy.gs_test.test.data.repository

import rx.Observable
import xyz.eliothmonroy.gs_test.test.data.model.LoginRequest
import xyz.eliothmonroy.gs_test.test.data.model.LoginResponse

interface TestRepository {
    fun getLoginResponse(request: LoginRequest):Observable<LoginResponse>
}