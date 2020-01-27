package xyz.eliothmonroy.mvpdemo.presenter

import xyz.eliothmonroy.mvpdemo.model.Login
import xyz.eliothmonroy.mvpdemo.model.LoginImp
import xyz.eliothmonroy.mvpdemo.view.LoginView

class LoginPresenterImp(private val loginView: LoginView):LoginPresenter {
    private val modelo:Login=LoginImp()
    override fun login(nombre: String, password: String) {
        val inicioValido=modelo.login(nombre,password)
        if(inicioValido){
            loginView.onLoginSuccess()
        }else{
            loginView.onLoginFail()
        }
    }
}