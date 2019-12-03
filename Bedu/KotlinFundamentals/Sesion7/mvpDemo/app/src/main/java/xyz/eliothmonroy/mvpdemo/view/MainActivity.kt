package xyz.eliothmonroy.mvpdemo.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import xyz.eliothmonroy.mvpdemo.R
import xyz.eliothmonroy.mvpdemo.presenter.MensajePresenter
import xyz.eliothmonroy.mvpdemo.presenter.MensajePresenterImp

class MainActivity : AppCompatActivity(), MainView {

    val presenter:MensajePresenter=MensajePresenterImp(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.saludar("Elioth")
    }

    override fun mostrarSaludo(saludo: String) {
        txtSaludo.text=saludo
    }
}
