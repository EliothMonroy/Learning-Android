package xyz.eliothmonroy.mvpdemo.reto.model

import xyz.eliothmonroy.mvpdemo.reto.model.data.Usuario

interface RegistroUsuario {
    fun registrarUsuario(nombre:String, email:String, telefono:String):Boolean
    fun obtenerUltimoUsuario():Usuario
    fun obtenerUsuarios():MutableList<Usuario>
}