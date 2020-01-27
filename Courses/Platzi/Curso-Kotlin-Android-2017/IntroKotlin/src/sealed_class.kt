sealed class Intencion{ //Clases selladas, solo podemos definir clases dentro de ellas
    //class Refrescar:Intencion()
    //class Recargar:Intencion()
    object Recargar1:Intencion(){
        override fun log() {
            println("Refrescado")
        }
    }
    object Cargar:Intencion(){
        override fun log() {
            println("Cargar más datos")
        }
    }
    data class Error(val razon:String) : Intencion(){
        override fun log() {
            println(razon)
        }
    }
    abstract fun log()
}

fun main() {
    /*val intencion:Intencion=Intencion.Refrescar()
    val output=when(intencion){
        is Intencion.Refrescar->"Refresh"
        is Intencion.Recargar->"Recargar"
    }
    println(output)*/

    val intencion:Intencion=Intencion.Recargar1
    val output=when(intencion){
        is Intencion.Cargar -> "Cargar más datos"
        is Intencion.Recargar1 -> "Recargar más datos"
        is Intencion.Error -> "Error"
    }
    intencion.log()
    println(output)

}