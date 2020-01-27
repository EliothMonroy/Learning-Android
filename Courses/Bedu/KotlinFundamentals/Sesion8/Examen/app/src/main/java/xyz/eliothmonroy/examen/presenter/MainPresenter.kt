package xyz.eliothmonroy.examen.presenter

import xyz.eliothmonroy.examen.model.data.Usuario

interface MainPresenter {
    fun mostrarUsuarios(listaUsuarios:List<Usuario>)
    fun obtenerUsuarios()
    fun falloConexion(mensaje:String)
    fun noHayUsuarios(mensaje: String)
}