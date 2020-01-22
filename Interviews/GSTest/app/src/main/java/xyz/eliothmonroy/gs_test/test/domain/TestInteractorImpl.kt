package xyz.eliothmonroy.gs_test.test.domain

import rx.Observable
import xyz.eliothmonroy.gs_test.test.data.model.LoginRequest
import xyz.eliothmonroy.gs_test.test.data.model.LoginResponse
import xyz.eliothmonroy.gs_test.test.data.repository.TestRepositoryImpl

class TestInteractorImpl:TestInteractor {

    private val testRepository=TestRepositoryImpl()

    override fun getLoginResponse(email: String, password: String): Observable<LoginResponse> {
        return testRepository.getLoginResponse(LoginRequest(email, password))
    }
}