package xyz.eliothmonroy.mvpdemo.presenter

import xyz.eliothmonroy.mvpdemo.model.Mensaje
import xyz.eliothmonroy.mvpdemo.model.MensajeImp
import xyz.eliothmonroy.mvpdemo.view.MainView

class MensajePresenterImp(var view: MainView):MensajePresenter {
    val modelo:Mensaje = MensajeImp()
    override fun saludar(nombre: String){
        val saludo= modelo.saludar(nombre)
        view.mostrarSaludo(saludo)
    }
}