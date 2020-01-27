package xyz.eliothmonroy.examen.presenter

import xyz.eliothmonroy.examen.model.UsuarioModel
import xyz.eliothmonroy.examen.model.UsuarioModelImp
import xyz.eliothmonroy.examen.model.data.Usuario
import xyz.eliothmonroy.examen.view.MainView

class MainPresenterImp(private val mainView: MainView):MainPresenter {

    private val usuarioModel:UsuarioModel=UsuarioModelImp(this)

    override fun mostrarUsuarios(listaUsuarios: List<Usuario>) {
        mainView.mostrarUsuarios(listaUsuarios)
    }

    override fun obtenerUsuarios() {
        usuarioModel.obtenerUsuarios()
    }

    override fun falloConexion(mensaje:String) {
        mainView.falloConexion(mensaje)
    }

    override fun noHayUsuarios(mensaje:String) {
        mainView.noHayUsuarios(mensaje)
    }
}