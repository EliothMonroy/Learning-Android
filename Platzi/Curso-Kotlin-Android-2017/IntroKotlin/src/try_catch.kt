fun fail(mensaje: String):Nothing { //No regresa nada
    throw IllegalArgumentException(mensaje)
}

fun main() {
    println("Ingresa cualquier cosa")
    val num:Any?=try {
        readLine()?.toInt()
    }catch (e:NumberFormatException){
        "No Int"
    }
    println(num)

    var name=""
    val persona:Persona2?=null
    try{
        name=persona?.name ?: fail("Nombre requerido")
    }catch (e:IllegalArgumentException){
        println(e)
        println(name)
    }
}