package xyz.eliothmonroy.mvpdemo.reto.model.data

object ListaUsuario{
    val usuario1=Usuario("Usuario1","email1@gmail.com","12345678")
    val usuario2=Usuario("Usuario2","email2@gmail.com","12345678")
    val usuario3=Usuario("Usuario3","email3@gmail.com","12345678")
    val usuario4=Usuario("Usuario4","email4@gmail.com","12345678")
    val usuario5=Usuario("Usuario5","email5@gmail.com","12345678")
    val usuario6=Usuario("Usuario6","email6@gmail.com","12345678")
    val usuario7=Usuario("Usuario7","email7@gmail.com","12345678")
    val usuario8=Usuario("Usuario8","email8@gmail.com","12345678")
    val usuario9=Usuario("Usuario9","email9@gmail.com","12345678")
    val usuario10=Usuario("Usuario10","email10@gmail.com","12345678")
    fun obtenerListaUsuarios():MutableList<Usuario>{
        return mutableListOf(usuario1, usuario2, usuario3, usuario4, usuario5, usuario6, usuario7,
            usuario8, usuario9, usuario10)
    }
}