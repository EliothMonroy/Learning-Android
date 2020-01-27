package xyz.eliothmonroy.mvpdemo.reto.presenter

import xyz.eliothmonroy.mvpdemo.reto.model.RegistroUsuarioImp
import xyz.eliothmonroy.mvpdemo.reto.model.data.Usuario
import xyz.eliothmonroy.mvpdemo.reto.view.HomeView

class UsuarioPresenterImp(private val homeView: HomeView): UsuarioPresenter {

    private val registroUsuario=RegistroUsuarioImp()

    override fun validarDatos(nombre: String, email: String, telefono: String):Boolean {
        return nombre!="" && email!="" && telefono!=""
    }

    override fun iniciarSesion(nombre: String, email: String, telefono: String) {
        if(validarDatos(nombre, email, telefono)){
            val usuarioExiste=registroUsuario.registrarUsuario(nombre,email,telefono)
            if(!usuarioExiste){
                homeView.registroExitoso()
                homeView.mostrarUsuario(registroUsuario.obtenerUltimoUsuario())
            }else{
                homeView.registroNoExitoso()
            }
        }else{
            homeView.mostrarMensaje("Datos incorrectos")
        }
    }

    override fun mostrarUsuarios(listaUsuarios: MutableList<Usuario>) {
        homeView.mostrarUsuarios(listaUsuarios)
    }

    override fun obtenerUsuarios() {
        mostrarUsuarios(registroUsuario.obtenerUsuarios())
    }
}