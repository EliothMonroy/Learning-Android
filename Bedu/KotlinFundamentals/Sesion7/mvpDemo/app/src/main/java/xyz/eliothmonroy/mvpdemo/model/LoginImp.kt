package xyz.eliothmonroy.mvpdemo.model

class LoginImp: Login {

    override fun login(nombre: String, password: String): Boolean {
        return nombre==usuario && password==contra
    }

    companion object {
        const val usuario="admi"
        const val contra="123456"
    }
}