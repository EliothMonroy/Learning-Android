package xyz.eliothmonroy.examen.view

import xyz.eliothmonroy.examen.model.data.Usuario

interface MainView {
    fun mostrarUsuarios(listaUsuarios:List<Usuario>)
    fun falloConexion(mensaje:String)
    fun noHayUsuarios(mensaje: String)
}