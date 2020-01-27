package xyz.eliothmonroy.mvpdemo.reto.presenter

import xyz.eliothmonroy.mvpdemo.reto.model.data.Usuario

interface UsuarioPresenter {
    fun validarDatos(nombre:String, email:String, telefono:String):Boolean
    fun iniciarSesion(nombre:String, email:String, telefono: String)
    fun mostrarUsuarios(listaUsuarios:MutableList<Usuario>)
    fun obtenerUsuarios()
}