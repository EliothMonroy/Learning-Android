package xyz.eliothmonroy.gs_test.test.domain

import rx.Observable
import xyz.eliothmonroy.gs_test.test.data.model.LoginResponse

interface TestInteractor {
    fun getLoginResponse(email:String, password:String):Observable<LoginResponse>
}