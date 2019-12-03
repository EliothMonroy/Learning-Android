package xyz.eliothmonroy.mvpdemo.model

class MensajeImp:Mensaje{
    override fun saludar(nombre: String): String {
        return "Hola $nombre, estás usando MVP"
    }
}