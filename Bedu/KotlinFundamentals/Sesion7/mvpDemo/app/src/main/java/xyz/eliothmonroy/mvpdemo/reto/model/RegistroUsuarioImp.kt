package xyz.eliothmonroy.mvpdemo.reto.model

import xyz.eliothmonroy.mvpdemo.reto.model.data.ListaUsuario
import xyz.eliothmonroy.mvpdemo.reto.model.data.Usuario

class RegistroUsuarioImp:RegistroUsuario {

    private val listaUsuarios= ListaUsuario.obtenerListaUsuarios()

    override fun registrarUsuario(nombre: String, email: String, telefono: String):Boolean {
        var existe=false
        for (usuario in listaUsuarios){
            if(usuario.email==email){
                existe=true
                break
            }
        }
        if(!existe){
            listaUsuarios.add(Usuario(nombre,email,telefono))
        }
        return existe
    }

    override fun obtenerUltimoUsuario():Usuario {
        return listaUsuarios[listaUsuarios.lastIndex]
    }

    override fun obtenerUsuarios(): MutableList<Usuario> {
        return listaUsuarios
    }
}