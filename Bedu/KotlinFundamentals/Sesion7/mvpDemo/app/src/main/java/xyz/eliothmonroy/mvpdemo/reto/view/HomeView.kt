package xyz.eliothmonroy.mvpdemo.reto.view

import xyz.eliothmonroy.mvpdemo.reto.model.data.Usuario

interface HomeView {
    fun iniciarSesion(nombre: String, email:String, telefono: String)
    fun mostrarUsuario(usuario: Usuario)
    fun mostrarUsuarios(listaUsuarios:MutableList<Usuario>)
    fun mostrarMensaje(mensaje:String)
    fun registroExitoso()
    fun registroNoExitoso()
}