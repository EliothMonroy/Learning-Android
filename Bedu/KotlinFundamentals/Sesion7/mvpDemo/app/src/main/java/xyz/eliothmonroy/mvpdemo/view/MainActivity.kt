package xyz.eliothmonroy.mvpdemo.view

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import xyz.eliothmonroy.mvpdemo.R
import xyz.eliothmonroy.mvpdemo.presenter.LoginPresenter
import xyz.eliothmonroy.mvpdemo.presenter.LoginPresenterImp

class MainActivity : AppCompatActivity(), LoginView {

    private val presenter:LoginPresenter= LoginPresenterImp(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        iniciar.setOnClickListener {
            iniciar()
        }
    }

    override fun onLoginSuccess() {
        val t=Toast.makeText(this,"Inicio de sesión exitoso",Toast.LENGTH_SHORT)
        t.setGravity(Gravity.CENTER_VERTICAL,0,0)
        t.show()
    }

    override fun onLoginFail() {
        val t=Toast.makeText(this,"Usuario o password incorrectos",Toast.LENGTH_SHORT)
        t.setGravity(Gravity.CENTER_VERTICAL,0,0)
        t.view.setBackgroundColor(Color.RED)
        t.show()
    }

    private fun iniciar(){
        val usuario=txtUsuario.text.toString()
        val password=txtPassword.text.toString()
        presenter.login(usuario,password)
    }

}
